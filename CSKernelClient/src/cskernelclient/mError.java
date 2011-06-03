/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cskernelclient;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author jalvarez
 */
public class mError {

//Option Explicit

    //--------------------------------------------------------------------------------
    // mError
    // 27-12-99

    //--------------------------------------------------------------------------------
    // notas:

    //--------------------------------------------------------------------------------
    // api win32
    // constantes
    // estructuras
    // Funciones

    //--------------------------------------------------------------------------------

    // constantes
    private static final String C_ERRORSQLINFOADD = "@@ErrorSqlInfoAdd@@";

    // estructuras
    // variables privadas
    // Properties publicas
    public static String gTitle;
    
    public static String gLastErrDescription = "";
    public static int gLastErrorNumber = 0;
    public static boolean gbSilent = false;
    public static String gLastErrFunction = "";
    public static String gLastErrModule = "";
    public static String gLastErrInfoAdd = "";
    public static String gStackTrace = "";
    public static int gLastErrLine = 0;
    // Properties privadas
    // Funciones publicas

    public static void mngError_(
            Exception errObj, 
            String nameFunction, 
            String module, 
            String infoAdd, 
            String title, 
            int level, 
            int varType, 
            Object connectionObj) { // TODO: Use of ByRef founded

        gLastErrFunction = nameFunction;
        gLastErrModule = module;
        gLastErrInfoAdd = infoAdd;
        gLastErrLine = 0;
        gLastErrorNumber = 0;
        
        StringWriter errors = new StringWriter();
        errObj.printStackTrace(new PrintWriter(errors));
        gStackTrace = errors.toString();

        fErrores f = null;
        Object errorAdo = null;
        String strErr = "";

        if (pProcessError(errObj.getMessage(), strErr, connectionObj)) {

            gLastErrDescription = strErr;

            String errorDetails = "";
            int n = 0;
            n = strErr.indexOf(C_ERRORSQLINFOADD, 1);
            if (n > 0) {
                errorDetails = strErr.substring(n + 19);
                n = n - 1;
                if (n > 0) {
                    strErr = strErr.substring(1, n - 1);
                } 
                else {
                    strErr = "No hay información de error.";
                }
            }

            mUtil.msgWarning_(strErr, , errorDetails);

        } 
        else {

            gLastErrorNumber = 0;

            int errNumber = 0;
            String errSource = "";

            if (!gbSilent) {

                // Creo un formulario de errores
                f = new fErrores(new javax.swing.JFrame(), true);

                // Agrego informacion del error a la lista
                f.addDetail("Función: " + nameFunction.replace("\\n", "\\n"));
                f.addDetail("Modulo: " + module);
                f.addDetail("Descripción: " + strErr.replace("\\n", "\\n"));
                f.addDetail("Número de error: " + ((Integer) errNumber).toString());
                f.addDetail("Info Adicional: " + infoAdd.replace("\\n", "\\n"));
                f.addDetail("");
                f.addDetail("Source: " + errSource);
                f.addDetail("");
                f.addDetail("Stack:");
                f.addDetail(gStackTrace);

                if (title.isEmpty()) { title = gTitle; }

                f.setCaption(title);

                // Establezco el dibujito del formulario
                switch (level) {
                    case csErrorLevel.CSERRORWARNING:
                        f.setWarning();
                        break;
                    case csErrorLevel.CSERRORFATAL:
                        f.setFatal();
                    //'csErrorInformation
                        break;
                    default:
                        f.setInformation();
                        break;
                }

                // Establezco el contenido de la etiqueta detalle
                f.setDescrip("Ha ocurrido un error en la aplicación");
                gLastErrDescription = f.getDetail();
                f.setVisible(true);

            } 
            else {

                String errDescription = "";
                errDescription = errObj.getMessage() + "\\n";
                gLastErrDescription = strErr + "\\n" + errDescription;

            }
        }
    }
    // Funciones privadas
    private static String pReplaceAux(String source, String table, String column) { // TODO: Use of ByRef founded
        int n = 0;
        String strErr = "";

        n = source.indexOf("table", 1);
        source = source.replace("dbo.", "");
        strErr = source.substring(n, source.indexOf(".", n) - n + 1);

        strErr = strErr.replace("table", table);
        strErr = strErr.replace("column", column);
        return strErr;
    }

    private static String pReplaceAuxES(String source, String table, String column) { // TODO: Use of ByRef founded
        int n = 0;
        String strErr = "";

        n = source.indexOf("tabla", 1);
        source = source.replace("dbo.", "");
        strErr = source.substring(n, source.indexOf(".", n) - n + 1);

        strErr = strErr.replace("tabla", table);
        strErr = strErr.replace("column", column);
        return strErr;
    }

    private static boolean pProcessError(String strOriginalErr, String rtnMsg, Object connectionObj) { // TODO: Use of ByRef founded
        boolean _rtn = false;
        int p = 0;
        String strErr = "";
        int q = 0;

        p = strOriginalErr.indexOf("@@ERROR_SP:", 1);

        if (p > 0) {
            strErr = strOriginalErr.substring(p + 11);
        } 
        else {
            p = strOriginalErr.indexOf("@@ERROR_SP_RS:", 1);
            if (p > 0) {
                strErr = strOriginalErr.substring(p + 14);
            }
        }

        if (p > 0) {

            _rtn = true;
        } 
        else {

            Object errorAdo = null;
            String strOriginalErr2 = "";

            if (connectionObj != null) {
                for (int _i = 0; _i < connectionObj.Errors.size(); _i++) {
                    errorAdo = ConnectionObj.Errors.getItem(_i);
                    strOriginalErr2 = strOriginalErr2 + errorAdo;
                }
            }

            strOriginalErr2 = strOriginalErr2 + mGlobal.gErrorDB;

            p = strOriginalErr2.indexOf("@@ERROR_SP:", 1);
            if (p > 0) {
                strOriginalErr = strOriginalErr2;
                strErr = strOriginalErr.substring(p + 11);
                _rtn = true;
            }
        }

        if (strOriginalErr.equals("")) {
            strOriginalErr = strOriginalErr2;
        }

        mGlobal.gErrorDB = "";

        //-------------------
        if (strOriginalErr.indexOf("DELETE statement conflicted with COLUMN REFERENCE constraint ", 1) > 0 || strOriginalErr.indexOf("DELETE statement conflicted with COLUMN SAME TABLE REFERENCE constraint ", 1) > 0) {
            strErr = pReplaceAux(strOriginalErr, "Este registro esta relacionado con otros registros en la tabla: ", "Por medio de la columna:") + ";;No es posible borrarlo.";
        }

        if (strOriginalErr.indexOf("Instrucción DELETE en conflicto con la restricción COLUMN REFERENCE ", 1) > 0) {
            strErr = pReplaceAuxES(strOriginalErr, "Este registro esta relacionado con otros registros en la tabla: ", "Por medio de la columna:") + ";;No es posible borrarlo.";
        }

        if (strOriginalErr.indexOf("DELETE statement conflicted with the REFERENCE constraint ", 1) > 0) {
            strErr = pReplaceAux(strOriginalErr, "Este registro esta relacionado con otros registros en la tabla: ", "Por medio de la columna:") + ";;No es posible borrarlo.";
        }

        if (strOriginalErr.indexOf("Instrucción DELETE en conflicto con la restricción REFERENCE ", 1) > 0) {
            strErr = pReplaceAuxES(strOriginalErr, "Este registro esta relacionado con otros registros en la tabla: ", "Por medio de la columna:") + ";;No es posible borrarlo.";
        }

        //-------------------
        if (strOriginalErr.indexOf("UPDATE statement conflicted with COLUMN FOREIGN KEY constraint ", 1) > 0) {
            strErr = pReplaceAux(strOriginalErr, "Este registro depende de otro registro en la tabla: ", "Por medio de la columna:") + ";;No es posible modificar esta columna.";
        }

        if (strOriginalErr.indexOf("Instrucción UPDATE en conflicto con la restricción COLUMN FOREIGN KEY ", 1) > 0) {
            strErr = pReplaceAuxES(strOriginalErr, "Este registro depende de otro registro en la tabla: ", "Por medio de la columna:") + ";;No es posible modificar esta columna.";
        }

        //-------------------
        if (strOriginalErr.indexOf("INSERT statement conflicted with COLUMN FOREIGN KEY constraint ", 1) > 0) {
            strErr = pReplaceAux(strOriginalErr, "Este registro hace referencia a otro registro en la tabla: ", "Por medio de la columna:") + " que no existe.;;No es posible insertar el registro.";
        }

        if (strOriginalErr.indexOf("Instrucción INSERT en conflicto con la restricción COLUMN FOREIGN KEY ", 1) > 0) {
            strErr = pReplaceAuxES(strOriginalErr, "Este registro hace referencia a otro registro en la tabla: ", "Por medio de la columna:") + " que no existe.;;No es posible insertar el registro.";
        }

        //-------------------
        if (strOriginalErr.indexOf("UPDATE statement conflicted with COLUMN REFERENCE constraint ", 1) > 0) {
            strErr = pReplaceAux(strOriginalErr, "Este registro esta relacionado con otros registros en la tabla: ", "Por medio de la columna:") + ";;No es posible modificar esta columna.";
        }

        if (strOriginalErr.indexOf("Instrucción UPDATE en conflicto con la restricción COLUMN REFERENCE ", 1) > 0) {
            strErr = pReplaceAuxES(strOriginalErr, "Este registro esta relacionado con otros registros en la tabla: ", "Por medio de la columna:") + ";;No es posible modificar esta columna.";
        }

        //-------------------
        if (strOriginalErr.indexOf("Cannot insert the value NULL into column ", 1) > 0) {
            strErr = strOriginalErr;
            strErr = "El campo: " + strErr.substring(strErr.indexOf("column", 1) + 7, strErr.indexOf(";", 1) - strErr.indexOf("column", 1) - 7);

            strErr = strErr.substring(1, strErr.indexOf("table", 1) + 4) + strErr.substring(strErr.indexOf(".", 1) + 1);

            strErr = strErr.substring(1, strErr.indexOf("table", 1) + 4) + "'" + strErr.substring(strErr.indexOf(".", 1) + 1);

            strErr = strErr.replace("table", "de la tabla: ") + " no permite valores vacios.;;No es posible insertar el registro.";
        }

        if (strOriginalErr.indexOf("No se puede insertar el valor NULL en la columna ", 1) > 0) {
            strErr = strOriginalErr;
            strErr = "El campo: " + strErr.substring(strErr.indexOf("columna", 1) + 7, strErr.indexOf(";", 1) - strErr.indexOf("columna", 1) - 7);

            strErr = strErr.substring(1, strErr.indexOf("tabla", 1) + 4) + strErr.substring(strErr.indexOf(".", 1) + 1);

            strErr = strErr.substring(1, strErr.indexOf("tabla", 1) + 4) + "'" + strErr.substring(strErr.indexOf(".", 1) + 1);

            strErr = strErr.replace("tabla", "de la tabla: ") + " no permite valores vacios.;;No es posible insertar el registro.";
        }

        //-------------------
        if (strOriginalErr.indexOf("Violation of PRIMARY KEY constraint ", 1) > 0) {

            p = "Violation of PRIMARY KEY constraint ".length();
            q = strOriginalErr.indexOf("Violation of PRIMARY KEY constraint ", 1);

            strErr = "No se puede insertar un valor duplicado en el campo: " + strOriginalErr.substring(q + p, strOriginalErr.indexOf(".", q) - q - p);

            p = strOriginalErr.indexOf("in object ", 1) + "in object".length();
            q = strOriginalErr.indexOf(".", p);
            strErr = strErr + " de la tabla " + strOriginalErr.substring(p, q - p);

        }

        if (strOriginalErr.indexOf("Infracción de la restricción PRIMARY KEY ", 1) > 0) {

            p = "Infracción de la restricción PRIMARY KEY ".length();
            q = strOriginalErr.indexOf("Infracción de la restricción PRIMARY KEY ", 1);

            strErr = "No se puede insertar un valor duplicado en el campo: " + strOriginalErr.substring(q + p, strOriginalErr.indexOf(".", 1) - q - p);

            p = strOriginalErr.indexOf("en el objeto ", 1) + "en el objeto".length();
            q = strOriginalErr.indexOf(".", p);
            strErr = strErr + " de la tabla " + strOriginalErr.substring(p, q - p);
        }

        //-------------------
        if (strOriginalErr.indexOf("Cannot insert duplicate key row in object ", 1) > 0) {
            strErr = "No se puede insertar un valor duplicado para el";

            if (strOriginalErr.toLowerCase().indexOf("codigo".toLowerCase(), 1)) {
                strErr = strErr + " campo código de la tabla";
            } 
            else {
                strErr = strErr + " indice ";
                p = strOriginalErr.indexOf("with unique index ", 1) + "with unique index ".length();
                q = strOriginalErr.indexOf(".", p);
                strErr = strErr + strOriginalErr.substring(p, q - p) + " de la tabla";
            }
            p = strOriginalErr.indexOf("in object ", 1) + "in object".length();
            q = strOriginalErr.indexOf("with", p);
            strErr = strErr + strOriginalErr.substring(p, q - p);
        }

        if (strOriginalErr.indexOf("No se puede insertar una fila de claves duplicadas en el objeto ", 1) > 0) {
            strErr = "No se puede insertar un valor duplicado para el";

            if (strOriginalErr.toLowerCase().indexOf("codigo".toLowerCase(), 1)) {
                strErr = strErr + " campo código de la tabla";
            } 
            else {
                strErr = strErr + " indice ";
                p = strOriginalErr.indexOf("con índice único ", 1) + "con índice único ".length();
                q = strOriginalErr.indexOf(".", p);
                strErr = strErr + strOriginalErr.substring(p, q - p) + " de la tabla";
            }
            p = strOriginalErr.indexOf("en el objeto ", 1) + "en el objeto".length();
            q = strOriginalErr.indexOf("con", p);
            strErr = strErr + strOriginalErr.substring(p, q - p);
        }

        //-------------------
        if (strOriginalErr.indexOf("Violation of UNIQUE KEY constraint", 1) > 0) {
            strErr = "No se puede insertar un valor duplicado para la";

            strErr = strErr + " regla de validación ";
            p = strOriginalErr.indexOf("UNIQUE KEY constraint ", 1) + "UNIQUE KEY constraint ".length();
            q = strOriginalErr.indexOf(".", p);
            strErr = strErr + strOriginalErr.substring(p, q - p) + " de la tabla";

            p = strOriginalErr.indexOf("in object ", 1) + "in object".length();
            q = strOriginalErr.indexOf(".", p);
            strErr = strErr + strOriginalErr.substring(p, q - p);
        }

        if (strOriginalErr.indexOf("Violacion de restricción UNIQUE KEY", 1) > 0) {
            strErr = "No se puede insertar un valor duplicado para la";

            strErr = strErr + " regla de validación ";
            p = strOriginalErr.indexOf("restricción UNIQUE KEY ", 1) + "restricción UNIQUE KEY ".length();
            q = strOriginalErr.indexOf(".", p);
            strErr = strErr + strOriginalErr.substring(p, q - p) + " de la tabla";

            p = strOriginalErr.indexOf("en el objeto ", 1) + "en el objeto".length();
            q = strOriginalErr.indexOf(".", p);
            strErr = strErr + strOriginalErr.substring(p, q - p);
        }

        // Para sacar el texto 'changed database...' que aparece
        // cuando hacemos que gDB se reconecte
        //
        if (strOriginalErr.indexOf("[Microsoft][ODBC SQL Server Driver][SQL Server]Changed database context to ", 1)) {
            strOriginalErr = strOriginalErr.replace("[Microsoft][ODBC SQL Server Driver][SQL Server]Changed database context to ", "");
        }
        if (strOriginalErr.indexOf("[Microsoft][ODBC SQL Server Driver][SQL Server]Changed language setting to us_english.", 1)) {
            strOriginalErr = strOriginalErr.replace("[Microsoft][ODBC SQL Server Driver][SQL Server]Changed language setting to us_english.", "");
        }
        if (strOriginalErr.indexOf("[Microsoft][ODBC SQL Server Driver][SQL Server]", 1)) {
            strOriginalErr = strOriginalErr.replace("[Microsoft][ODBC SQL Server Driver][SQL Server]", "");
        }

        if (!(strErr.equals(""))) {
            rtnMsg = strErr;
        } 
        else {
            rtnMsg = strOriginalErr;
        }

        _rtn = !(strErr.equals(""));
        return _rtn;
    }
    // construccion - destruccion

}

