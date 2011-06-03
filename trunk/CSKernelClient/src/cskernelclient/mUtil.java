/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cskernelclient;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.DefaultListModel;

/**
 *
 * @author jalvarez
 */
public class mUtil {

//Option Explicit

    //--------------------------------------------------------------------------------
    // mUtil
    // 31-01-00

    //--------------------------------------------------------------------------------
    // notas:

    //--------------------------------------------------------------------------------
    // api win32
    // constantes
    // estructuras
    // Funciones

    public static final int HWND_TOPMOST = -1;
    public static final int HWND_NOTOPMOST = -2;
    public static final int SWP_NOACTIVATE = 0x10;
    public static final int SWP_SHOWWINDOW = 0x40;

    private static final int CTBEXTERNALIMAGELIST = 2;
    private static final int CTBDRAWOFFICEXPSTYLE = 2;
    private static final int CTBSEPARATOR = 1;

    private static final int HH_DISPLAY_TOPIC = 0x0;
    private static final int HH_CLOSE_ALL = 0x12;
    private static final int HH_HELP_CONTEXT = 0xF;

    // estructuras
//*TODO:** type is translated as a new class at the end of the file Public Type RECT

//*TODO:** type is translated as a new class at the end of the file Private Type OSVERSIONINFO

    // Funciones
// *TODO: API Private Declare Function GetComputerName2 Lib "kernel32" Alias "GetComputerNameA" (ByVal lpBuffer As String, nSize As Long) As Long
// *TODO: API Public Declare Function GetClientRect Lib "user32" (ByVal hwnd As Long, lpRect As RECT) As Long
// *TODO: API Public Declare Function GetParent Lib "user32" (ByVal hwnd As Long) As Long
// *TODO: API Public Declare Function GetWindowRect Lib "user32" (ByVal hwnd As Long, lpRect As RECT) As Long
// *TODO: API Private Declare Function GetVersionEx Lib "kernel32" Alias "GetVersionExA" (lpVersionInformation As OSVERSIONINFO) As Long
// *TODO: API Private Declare Function HTMLHelp Lib "hhctrl.ocx" Alias "HtmlHelpA" (ByVal hwndCaller As Long, ByVal pszFile As String, ByVal uCommand As Long, ByVal dwData As Long) As Long

    //--------------------------------------------------------------------------------

    private static final String C_CRLF = "@;";
    private static final String C_CRLF2 = ";";

    // constantes
    // estructuras
    static class Record {
	private String value;  
	private int id;  
		
	public Record(String value, int id) {  
            this.value = value;  
            this.id = id;  
	}  
	public int getId() {  
            return id;  
	}  
        @Override
	public String toString() {  
            return value;  
	}  
    }      
    // privados

    // variables privadas
    // propiedades publicas
    // propiedades privadas
    // Funciones publicas

    //
    //-- Lists
    //
    public static void listAdd_(Object list, String value) { 
        listAdd_(list, value, 0);
    }
        
    public static void listAdd_(Object list, String value, int id) { 
        if (list instanceof JList) {
            JList jlist = (JList)list;
            DefaultListModel model = (DefaultListModel)jlist.getModel();
            Record record = new Record(value, id);
            model.addElement(record);
        }
        else if (list instanceof JComboBox) {
            JComboBox combo = (JComboBox)list;
            Record record = new Record(value, id);
            combo.addItem(record);
        }
    }
    public static int listID_(Object list) {
        Record record = null;
        if (list instanceof JList) {
            JList jlist = (JList)list;
            record = (Record)jlist.getSelectedValue();
        }
        else if (list instanceof JComboBox) {
            JComboBox combo = (JComboBox)list;
            record = (Record)combo.getSelectedItem();
        }
        if (record == null) 
            return 0;
        else
            return record.getId();
    }
    public static int listItemData_(Object list, int index) {
        int _rtn = 0;
        if (list.ListCount - 1 < index) { return _rtn; }
        if (index == -1) {
            _rtn = listID_(list);
        } 
        else {
            _rtn = list.ItemData(index);
        }
        return _rtn;
    }
    public static void listSetListIndex_(Object list, int idx) { // TODO: Use of ByRef founded
        if (list.ListCount < 1) { return; }
        if (list.ListCount > idx) { list.ListIndex = idx; }
    }
    public static void listSetListIndexForId_(Object list, int id) { // TODO: Use of ByRef founded
        int i = 0;
        for (i = 0; i <= list.ListCount - 1; i++) {
            if (list.ItemData(i) == id) {
                list.ListIndex = i;
                break;
            }
        }
    }
    public static void listSetListIndexForText_(Object list, String text) { // TODO: Use of ByRef founded
        int i = 0;
        for (i = 0; i <= list.ListCount - 1; i++) {
            if (text.equals(list.list(i))) {
                list.ListIndex = i;
                break;
            }
        }
    }
    public static void listChangeTextForSelected_(Object list, String value) { // TODO: Use of ByRef founded
        listChangeText_(list, list.ListIndex, value);
    }
    public static void listChangeText_(Object list, int idx, String value) { // TODO: Use of ByRef founded
        int itemD = 0;
        if (idx > list.ListCount || idx < 0) { return; }
        itemD = list.ItemData(idx);
        list.RemoveItem(idx);
        listAdd_(list, value, itemD);
    }
    public static int listGetIndexFromItemData_(Object list, int valueItemData) {
        int _rtn = 0;
        int i = 0;

        for (i = 0; i <= list.ListCount - 1; i++) {

            if (list.ItemData(i) == valueItemData) {
                _rtn = i;
                return _rtn;
            }
        }

        _rtn = -1;
        return _rtn;
    }
    //
    //-- InfoString
    //
    public static String setInfoString_(String fuente, String key, String value) {
        String _rtn = "";
        int i = 0;
        int j = 0;
        int k = 0;

        key = "#" + key;
        i = fuente.toLowerCase().indexOf(key.toLowerCase(), 1);
        // la Key no puede estar repetida
        if (fuente.toLowerCase().indexOf(key.toLowerCase(), i + 1) != 0) { VBA.ex.Raise(csErrorSetInfoString, "CSOAPI", "SetInfoString_: Se intento Save un Value de Password en una cadena invalida, la Password esta repetida."); }

        // si aun no existe la agrego al final
        if (i == 0) {
            _rtn = fuente + key + "=" + value + ";";
        } 
        else {

            j = fuente.toLowerCase().indexOf(";".toLowerCase(), i);
            if (j == 0) { VBA.ex.Raise(csErrorSetInfoString, "CSOAPI", "SetInfoString_: Se intento Save un Value de Password en una cadena invalida, la cadena esta corrupta, falta el signo ;."); }

            k = fuente.substring(i, j).toLowerCase().indexOf("=".toLowerCase(), 1);
            if (k == 0) { VBA.ex.Raise(csErrorSetInfoString, "CSOAPI", "SetInfoString_: Se intento Save un Value de Password en una cadena invalida, la cadena esta corrupta, falta el signo =."); }
            k = k + i - 1;
            _rtn = fuente.substring(1, k) + value + fuente.substring(j);
        }
        return _rtn;
    }

    public static String getInfoString_(String fuente, String key, String default) {
        String _rtn = "";
        int i = 0;
        int j = 0;
        int k = 0;

        key = "#" + key;

        i = fuente.toLowerCase().indexOf(key.toLowerCase(), 1);
        // la Key no puede estar repetida
        if (fuente.toLowerCase().indexOf(key.toLowerCase(), i + 1) != 0) { VBA.ex.Raise(csErrorSetInfoString, "CSOAPI", "GetInfoString_: Se intento obtener un Value de una cadena invalida, la Password esta repetida."); }

        // si la Key no existe devuelvo el default
        if (i == 0) {
            _rtn = default;
        } 
        else {

            "GetInfoString_: Se intento obtener un valor de una cadena invalida, la cadena esta corrupta, falta el signo "
.equals(Const c_errorstr);

            j = fuente.toLowerCase().indexOf(";".toLowerCase(), i);
            if (j == 0) { VBA.ex.Raise(csErrorSetInfoString, "CSOAPI", c_errorstr + ";."); }

            k = fuente.substring(i, j).toLowerCase().indexOf("=".toLowerCase(), 1);
            if (k == 0) { VBA.ex.Raise(csErrorSetInfoString, "CSOAPI", c_errorstr + "=."); }
            k = k + i - 1;
            _rtn = fuente.substring(k + 1, j - k - 1);
        }
        return _rtn;
    }

    //
    //-- Trees
    //
    public static void setNodeForId_(Object tree, int id) { // TODO: Use of ByRef founded
        Object node = null;
        for (int _i = 0; _i < tree.Nodes.size(); _i++) {
            Node = Tree.Nodes.getItem(_i);
            if (mCollection.getIdFromKey(node.Key) == id) {
                node.Selected = true;
                break;
            }
        }
    }

    //-- configuraciones en el registry
    public static String getRegistry_(csSeccionSetting seccion, String key, String default) {
        String sSeccion = "";
        switch (seccion) {
            case  CSConfig:
                sSeccion = "CONFIG";
                break;
            case  csInterface:
                sSeccion = "INTERFACE";
                break;
            case  csLogin:
                sSeccion = "LOGIN";
                break;
        }
        return GetSetting(mGlobal.gAppName, sSeccion, key, default);
    }

    public static void setRegistry_(csSeccionSetting seccion, String key, String value) {
        String sSeccion = "";
        switch (seccion) {
            case  CSConfig:
                sSeccion = "CONFIG";
                break;
            case  csInterface:
                sSeccion = "INTERFACE";
                break;
            case  csLogin:
                sSeccion = "LOGIN";
                break;
        }
        
            SaveSetting(mGlobal.gAppName, sSeccion, key, value);
        }
    }
    *#End If

    *#If PREPROC_KERNEL_CLIENT Then
    //-- barra de herramientas
    public static void showToolBarButton_(csButtons button, Object tBBar, boolean show) { // TODO: Use of ByRef founded
        String key = "";
        key = pShowToolBarButtonAux(button);
        tBBar.Buttons(key).Visible = show;
    }

    public static void showToolBarButtonEx_(csButtons button, Object tBBar, boolean show) { // TODO: Use of ByRef founded
        String key = "";
        key = pShowToolBarButtonAux(button);
        tBBar.ButtonVisible(key) = show;
    }

    private static String pShowToolBarButtonAux(csButtons button) {
        String key = "";

        switch (button) {
            case  BUTTON_NEW:
                key = "NEW";
                break;
            case  BUTTON_EDIT:
                key = "EDIT";
                break;
            case  BUTTON_REVOKE:
                key = "REVOKE";
                break;
            case  BUTTON_DELETE:
                key = "DELETE";
                break;
            case  BUTTON_CUT:
                key = "CUT";
                break;
            case  BUTTON_COPY:
                key = "COPY";
                break;
            case  BUTTON_PASTE:
                key = "PASTE";
                break;
            case  BUTTON_SEARCH:
                key = "SEARCH";
                break;
            case  BUTTON_PRINTOBJ:
                key = "PRINTOBJ";
                break;
            case  BUTTON_PREVIEW:
                key = "PREVIEW";
                break;
            case  BUTTON_GRID:
                key = "GRID";
                break;
            case  BUTTON_DEACTIVE:
                key = "DEACTIVE";
                break;
            case  BUTTON_EXIT:
                key = "EXIT";
                break;
            case  BUTTON_ROLS:
                key = "ROLS";
                break;
            case  BUTTON_PERMISSIONS:
                key = "PERMISSIONS";
                break;
            case  BUTTON_SAVE:
                key = "SAVE";
                break;
            case  BUTTON_WITH_PARAMS:
                key = "WITH_PARAMS";
                break;
            case  BUTTON_WITHOUT_PARAMS:
                key = "WITHOUT_PARAMS";
                break;
            case  BUTTON_UPDATE:
                key = "UPDATE";
                break;
        }

        return key;
    }

    private static void pAddButton(boolean isEx, Object tBBar, int buttons, int idxButton, String key, String toolTip, int idxImage, boolean bHaveSeparator, boolean bSize24, String name, boolean bShowNames) { // TODO: Use of ByRef founded

        if (buttons && idxButton) {

            bHaveSeparator = pPutSeparetor(isEx, tBBar, bHaveSeparator);
            // With tBBar;
                if (isEx) {

                    //*TODO:** can't found type for with block
                    //*With fControls
                    __TYPE_NOT_FOUND w___TYPE_NOT_FOUND = tBBar.fControls;
                        if (idxImage + 1 > fControls.ImgTbBarEx.ImageCount) {
                            if (idxImage <= w___TYPE_NOT_FOUND.ImgTbBar.ListImages.cDateNames.count()) {
                                w___TYPE_NOT_FOUND.ImgTbBarEx.AddFromHandle(w___TYPE_NOT_FOUND.ImgTbBar.ListImages(idxImage + 1).Picture.Handle, IMAGE_ICON);
                                idxImage = w___TYPE_NOT_FOUND.ImgTbBarEx.ImageCount - 1;
                            }
                        }
                    // {end with: w___TYPE_NOT_FOUND}

                    tBBar.AddButton(, idxImage, , , , , key);
                    tBBar.ButtonToolTip(key) = toolTip;
                } 
                else {
                    if (bSize24) {
                        if (bShowNames) {
                            tBBar.buttons.Add(, key, name, , idxImage + 1);
                        } 
                        else {
                            tBBar.buttons.Add(, key, "", , idxImage + 1);
                        }
                        tBBar.buttons(key).ToolTipText = toolTip;
                    } 
                    else {
                        if (bShowNames) {
                            tBBar.buttons.Add(, key, name, , idxImage + 1);
                        } 
                        else {
                            tBBar.buttons.Add(, key, "", , idxImage + 1);
                        }
                        tBBar.buttons(key).ToolTipText = toolTip;
                    }
                }
            // {end with: tBBar}
        }

    }

    public static void setToolBar_(Object tBBar, int buttons1, int buttons2, int buttons3) { // TODO: Use of ByRef founded

        pSetToolBarAux_(false, false, tBBar, buttons1, buttons2, buttons3);
    }

    public static void setToolBar24_(Object tBBar, int buttons1, int buttons2, int buttons3, boolean bShowNames, boolean bIsDoc) { // TODO: Use of ByRef founded

        pSetToolBarAux_(false, true, tBBar, buttons1, buttons2, buttons3, , , bShowNames, bIsDoc);
    }

    public static void setToolBar16_(Object tBBar, int buttons1, int buttons2, int buttons3, boolean bShowNames, boolean bIsDoc) { // TODO: Use of ByRef founded

        pSetToolBarAux_(false, false, tBBar, buttons1, buttons2, buttons3, , , bShowNames, bIsDoc);
    }

    public static void setToolBarEx_(Object tBBar, int buttons1, int buttons2, int buttons3) { // TODO: Use of ByRef founded

        pSetToolBarAux_(true, false, tBBar, buttons1, buttons2, buttons3);
    }

    private static void pSetToolBarAux_(boolean isEx, boolean bSize24, Object tBBar, int buttons1, int buttons2, int buttons3, String accion, String accionToolTip, boolean bShowNames, boolean bIsDoc) { // TODO: Use of ByRef founded

        boolean bHaveSeparator = false;

        // With tBBar;
            if (isEx) {
                tBBar.ImageSource = CTBEXTERNALIMAGELIST;

                if (getSysVersion() == 4) {
                    tBBar.DrawStyle = CTBDRAWOFFICEXPSTYLE;
                }

                tBBar.CreateToolbar(16, , , true);
                tBBar.SetImageList(fControls.ImgTbBarEx);
            } 
            else {
                if (bSize24) {
                    tBBar.Buttons.cDateNames.clear();
                    tBBar.ImageList = fControls.ImgTbBar24;
                    tBBar.Style = tbrFlat;
                    tBBar.BorderStyle = ccFixedSingle;
                } 
                else {
                    tBBar.Buttons.cDateNames.clear();
                    tBBar.ImageList = fControls.ImgTbBar;
                    tBBar.Style = tbrFlat;
                    tBBar.BorderStyle = ccFixedSingle;
                }
            }
        // {end with: tBBar}

        bHaveSeparator = true;

        String elemento = "";
        if (bIsDoc) {
            elemento = "comprobante";
        } 
        else {
            elemento = "registro";
        }

        pPutSeparetorEx(isEx, tBBar);

        pAddButton(isEx, tBBar, buttons1, BUTTON_WITH_PARAMS, "WITH_PARAMS", "Clic para mostrar los parámetros", IMAGEN_WITH_PARAMS, bHaveSeparator, bSize24, "Ver", bShowNames);
        pAddButton(isEx, tBBar, buttons1, BUTTON_WITHOUT_PARAMS, "WITHOUT_PARAMS", "Clic para ocultar los parámetros", IMAGEN_WITHOUT_PARAMS, bHaveSeparator, bSize24, "Ocultar", bShowNames);

        //-------
        bHaveSeparator = false;

        pAddButton(isEx, tBBar, buttons1, BUTTON_UPDATE, "UPDATE", "Clic para refrescar la grilla", IMAGEN_REFRESH, bHaveSeparator, bSize24, "Refrescar", bShowNames);

        //-------
        bHaveSeparator = false;

        pAddButton(isEx, tBBar, buttons1, BUTTON_NEW, "NEW", "Clic para crear un " + elemento + " nuevo", IMAGEN_NEW, bHaveSeparator, bSize24, "Nuevo", bShowNames);
        pAddButton(isEx, tBBar, buttons1, BUTTON_EDIT, "EDIT", "Clic para editar un " + elemento, IMAGEN_EDIT, bHaveSeparator, bSize24, "Editar", bShowNames);
        pAddButton(isEx, tBBar, buttons1, BUTTON_SAVE, "SAVE", "Clic para guardar el " + elemento, IMAGEN_SAVE, bHaveSeparator, bSize24, "Guardar", bShowNames);
        pAddButton(isEx, tBBar, buttons2, BUTTON_SAVE_AS, "SAVE_AS", "Guardar como presupuesto el " + elemento, IMAGEN_BUTTON_SAVE_AS, bHaveSeparator, bSize24, "Guardar Como", bShowNames);

        //-------
        bHaveSeparator = false;

        pAddButton(isEx, tBBar, buttons2, BUTTON_DOC_EDIT, "DOC_EDIT", "Clic editar el " + elemento, IMAGEN_DOC_EDIT, bHaveSeparator, bSize24, "Editar", bShowNames);

        //-------
        bHaveSeparator = false;

        pAddButton(isEx, tBBar, buttons2, BUTTON_DOC_ACTION, "DOC_ACTION", accionToolTip, IMAGEN_DOC_ACTION, bHaveSeparator, bSize24, accion, bShowNames);

        //-------
        bHaveSeparator = false;

        pAddButton(isEx, tBBar, buttons1, BUTTON_RELOAD, "RELOAD", "Clic para descartar todos los cambios realizados al " + elemento, IMAGEN_RELOAD, bHaveSeparator, bSize24, "Descartar", bShowNames);

        //-------
        bHaveSeparator = false;

        pAddButton(isEx, tBBar, buttons1, BUTTON_ANULAR, "ANULAR",String.valueOf("Clic para anular o des-anular el ") + elemento, IMAGEN_ANULAR, bHaveSeparator, bSize24, "Anular", bShowNames);

        //-------
        bHaveSeparator = false;

        pAddButton(isEx, tBBar, buttons1, BUTTON_CUT, "CUT", "Cortar", IMAGEN_CUT, bHaveSeparator, bSize24, "Cortar", bShowNames);
        pAddButton(isEx, tBBar, buttons1, BUTTON_COPY, "COPY", "Copiar", IMAGEN_COPY, bHaveSeparator, bSize24, "Copiar", bShowNames);
        pAddButton(isEx, tBBar, buttons1, BUTTON_PASTE, "PASTE", "Pegar", IMAGEN_PASTE, bHaveSeparator, bSize24, "Pegar", bShowNames);
        pAddButton(isEx, tBBar, buttons1, BUTTON_SEARCH, "SEARCH", "Clic para acceder a la ventana de busqueda", IMAGEN_SEARCH, bHaveSeparator, bSize24, "Buscar", bShowNames);

        //-------
        bHaveSeparator = false;

        pAddButton(isEx, tBBar, buttons1, BUTTON_DOC_FIRST, "FIRST", "Clic para ver el primer " + elemento, IMAGEN_DOC_FIRST, bHaveSeparator, bSize24, "Primero", bShowNames);
        pAddButton(isEx, tBBar, buttons1, BUTTON_DOC_PREVIOUS, "PREVIOUS", "Clic para ver el " + elemento + " anterior", IMAGEN_DOC_PREVIOUS, bHaveSeparator, bSize24, "Anterior", bShowNames);
        pAddButton(isEx, tBBar, buttons1, BUTTON_DOC_NEXT, "NEXT", "Clic para ver el siguiente " + elemento, IMAGEN_DOC_NEXT, bHaveSeparator, bSize24, "Siguiente", bShowNames);
        pAddButton(isEx, tBBar, buttons1, BUTTON_DOC_LAST, "LAST", "Clic para ver el ultimo " + elemento, IMAGEN_DOC_LAST, bHaveSeparator, bSize24, "Ultimo", bShowNames);

        //-------
        bHaveSeparator = false;

        pAddButton(isEx, tBBar, buttons1, BUTTON_DEACTIVE, "DEACTIVE", "Clic para desactivar", IMAGEN_DEACTIVE, bHaveSeparator, bSize24, "Desactivar", bShowNames);
        pAddButton(isEx, tBBar, buttons1, BUTTON_REVOKE, "REVOKE", "Clic para anular", IMAGEN_REVOKE, bHaveSeparator, bSize24, "Anular", bShowNames);
        pAddButton(isEx, tBBar, buttons1, BUTTON_DELETE, "DELETE", "Clic para borrar", IMAGEN_DELETE, bHaveSeparator, bSize24, "Borrar", bShowNames);

        //-------
        bHaveSeparator = false;

        pAddButton(isEx, tBBar, buttons1, BUTTON_PRINTOBJ, "PRINTOBJ", "Clic para enviar el listado o el " + elemento + " a la impresora", IMAGEN_PRINTOBJ, bHaveSeparator, bSize24, "Imprimir", bShowNames);
        pAddButton(isEx, tBBar, buttons1, BUTTON_PREVIEW, "PREVIEW", "Clic para ver la vista previa del informe", IMAGEN_PREVIEW, bHaveSeparator, bSize24, "Vista Previa", bShowNames);
        pAddButton(isEx, tBBar, buttons1, BUTTON_GRID, "GRID", "Clic para ver el informe en grilla", IMAGEN_GRID, bHaveSeparator, bSize24, "Vista en Grilla", bShowNames);

        //-------
        bHaveSeparator = false;

        pAddButton(isEx, tBBar, buttons2, BUTTON_DOC_MAIL, "SENDEMAIL", "Clic para enviar el listado o el " + elemento + String.valueOf(" por e-mail"), IMAGEN_DOC_MAIL, bHaveSeparator, bSize24, "E-mail", bShowNames);

        //-----------
        bHaveSeparator = false;

        pAddButton(isEx, tBBar, buttons1, BUTTON_ROLS, "ROLS", "Roles", IMAGEN_ROLS, bHaveSeparator, bSize24, "Roles", bShowNames);
        pAddButton(isEx, tBBar, buttons1, BUTTON_PERMISSIONS, "PERMISSIONS", "Permisos", IMAGEN_PERMISSIONS, bHaveSeparator, bSize24, "Permisos", bShowNames);

        //-------
        bHaveSeparator = false;

        pAddButton(isEx, tBBar, buttons1, BUTTON_DOC_SIGNATURE, "SIGNATURE", "Clic para firmar el " + elemento, IMAGEN_DOC_SIGNATURE, bHaveSeparator, bSize24, "Firmar", bShowNames);
        pAddButton(isEx, tBBar, buttons1, BUTTON_DOC_MODIFY, "HISTORY", "Clic para ver quienes modificaron el " + elemento, IMAGEN_DOC_MODIFY, bHaveSeparator, bSize24, "Historial", bShowNames);
        pAddButton(isEx, tBBar, buttons1, BUTTON_DOC_APLIC, "APPLY",String.valueOf("Clic para ver/modificar las aplicaciones del ") + elemento, IMAGEN_DOC_APLIC, bHaveSeparator, bSize24, "Aplicación", bShowNames);
        pAddButton(isEx, tBBar, buttons2, BUTTON_DOC_AUX, "DOC_AUX", "Clic para ver los documentos asociados a este " + elemento + " (Asiento contable y Transferencia de Stock)", IMAGEN_DOC_AUX, bHaveSeparator, bSize24, "Doc. Asoc.", bShowNames);
        pAddButton(isEx, tBBar, buttons2, BUTTON_DOC_MERGE, "DOC_MERGE", "Clic para compenzar stock entre depositos", IMAGEN_DOC_MERGE, bHaveSeparator, bSize24, "Compenzar", bShowNames);

        //-------
        bHaveSeparator = false;
        pAddButton(isEx, tBBar, buttons1, BUTTON_ATTACH, "ATTACH", "Clic para asociar archivos al " + elemento, IMAGEN_ATTACH, bHaveSeparator, bSize24, "Asociar", bShowNames);

        //-------
        bHaveSeparator = false;
        pAddButton(isEx, tBBar, buttons1, BUTTON_EDIT_STATE, "EDIT_STATE", "Clic para ver el estado de edición del " + elemento, IMAGEN_EDIT_STATE, bHaveSeparator, bSize24, "Estado", bShowNames);

        //-------
        bHaveSeparator = false;
        pAddButton(isEx, tBBar, buttons1, BUTTON_DOC_HELP, "HELP", "Clic para ver la ayuda del usuario", IMAGEN_DOC_HELP, bHaveSeparator, bSize24, "Ayuda", bShowNames);

        //-------
        bHaveSeparator = false;

        pAddButton(isEx, tBBar, buttons2, BUTTON_DOC_TIP, "DOC_TIP", "Clic para enviar una sugerencia a CrowSoft", IMAGEN_DOC_TIP, bHaveSeparator, bSize24, "Sugerencias", bShowNames);
        pAddButton(isEx, tBBar, buttons2, BUTTON_DOC_ALERT, "DOC_ALERT", "Clic para ver alertas para este " + elemento, IMAGEN_DOC_ALERT, bHaveSeparator, bSize24, "Alertas", bShowNames);

        //-------
        bHaveSeparator = false;

        pAddButton(isEx, tBBar, buttons2, BUTTON_SAVE_PARAMS, "SAVE_PARAMS", "Clic para guardar los parámetros", IMAGEN_SAVE_PARAMS, bHaveSeparator, bSize24, "Guardar", bShowNames);
        pAddButton(isEx, tBBar, buttons2, BUTTON_RELOAD_PARAMS, "RELOAD_PARAMS", "Clic para recargar los párametros guardados", IMAGEN_RELOAD_PARAMS, bHaveSeparator, bSize24, "Recargar", bShowNames);

        //-----------
        // Salir siempre va al final
        bHaveSeparator = false;

        pAddButton(isEx, tBBar, buttons1, BUTTON_EXIT, "EXIT", "Clic para cerrar la ventana", IMAGEN_EXIT, bHaveSeparator, bSize24, "Salir", bShowNames);

    }

    public static int getSysVersion() {
        int _rtn = 0;
        OSVERSIONINFO tVer = null;
        tVer..dwOSVersionInfoSize = tVer.length();

        GetVersionEx(tVer);

        switch (tVer..dwPlatformId) {
            case  0:
                _rtn = 31;
                break;
            case  1:
                //get minor version info
                if (tVer..dwMinorVersion == 0) {
                    //' sOS = "Microsoft Windows 95"
                    _rtn = 95;
                } 
                else if (tVer..dwMinorVersion == 10) {
                    //' sOS = "Microsoft Windows 98"
                    _rtn = 98;
                } 
                else if (tVer..dwMinorVersion == 90) {
                    //' sOS = "Microsoft Windows Millenium"
                    _rtn = 1000;
                } 
                else {
                    _rtn = 1000;
                }
                break;
            case  2:
                _rtn = 4;
                break;
        }
        return _rtn;
    }

    public static boolean presButtonToolbarEx_(String sKeyButton, Object f, String nameFunction) { // TODO: Use of ByRef founded
        return pPresButtonToolbarAux_(true, sKeyButton, f, nameFunction);
    }

    public static boolean presButtonToolbar_(String sKeyButton, Object f, String nameFunction) { // TODO: Use of ByRef founded
        return pPresButtonToolbarAux_(false, sKeyButton, f, nameFunction);
    }

    private static boolean pPresButtonToolbarAux_(boolean isEx, String sKeyButton, Object f, String nameFunction) { // TODO: Use of ByRef founded
        boolean _rtn = false;
        try {
            switch (sKeyButton) {
                case  "NEW":
                    f.NewObj;
                    break;
                case  "EDIT":
                    f.Edit;
                    break;
                case  "REVOKE":
                    f.Revoke;
                    break;
                case  "DELETE":
                    f.Delete;
                    break;
                case  "PRINTOBJ":
                    f.PrintObj;
                    break;
                case  "PREVIEW":
                    f.Preview;
                    break;
                case  "CUT":
                    f.Cut;
                    break;
                case  "COPY":
                    f.Copy;
                    break;
                case  "PASTE":
                    f.Paste;
                    break;
                case  "SEARCH":
                    f.Search;
                    break;
                case  "DOC_AUX":
                    f.ShowDocAux;
                    break;
                case  "DOC_MERGE":
                    f.DocMerge;
                    break;
                case  "DOC_TIP":
                    f.SendTip;
                    break;
                case  "DOC_ALERT":
                    f.ShowAlert;
                    break;
                case  "DEACTIVE":
                    f.Deactive;
                    break;
                case  "EXIT":
                    f.CloseForm;
                    break;
                case  "ROLS":
                    f.Rols;
                    break;
                case  "SAVE":
                    f.Save;
                    break;
                case  "WITHOUT_PARAMS":
                    f.HideParameters;
                    break;
                case  "WITH_PARAMS":
                    f.ShowParameters;
                    break;
                case  "UPDATE":
                    f.Update;
                    break;
                case  "RELOAD_PARAMS":
                    f.ReloadParams;
                    break;
                case  "SAVE_PARAMS":
                    f.SaveParams;
                    break;
            }
            _rtn = true;
            return _rtn;
        } catch (Exception ex) {
            // 438 = objeto no soporta esta porpiedad o metodo
            if (VBA.ex.Number == 438) {
                mGlobal.gWindow.MsgWarning("Esta Funcionalidad no esta implementada", "Barra de Herramientas");
            } 
            else {
                mError.mngError_(VBA.ex, nameFunction, f.Name, "", "Error en click barra de herramientas, boton " + sKeyButton, csErrorWarning, csErrorVba);
            }
        }
        return _rtn;
    }

    //-------------
    // Obtener Properties de un Parent
    public static boolean getPropertyFromParent_(Object retValue, Object o, String oProperty) { // TODO: Use of ByRef founded
        boolean _rtn = false;
        try {

            do            if (TypeOf(o Is Form)) {

                switch (oProperty) {
                    case  "WindowState":
                        retValue = o.WindowState;
                        break;
                    case  "Height":
                        retValue = o.Height;
                        break;
                    case  "Width":
                        retValue = o.Width;
                        break;
                }
                break;
            }
            o = o.Parent;
        }

        _rtn = true;
    } catch (Exception ex) {
        // no se informa al User
        }
        return _rtn;
    }
    public static boolean getWindowState_(Object retValue, Object o) { // TODO: Use of ByRef founded
        return getPropertyFromParent_(retValue, o, "WindowState");
    }
    //-------------
    public static boolean getInput_(Object value, String descrip) { // TODO: Use of ByRef founded
        boolean _rtn = false;
        boolean oldformResult = false;
        String inputvalue = "";

        oldformResult = mGlobal.G_FormResult;

        mGlobal.G_FormResult = false;

        //*TODO:** can't found type for with block
        //*With fEdit
        __TYPE_NOT_FOUND w___TYPE_NOT_FOUND = fEdit;

            if (!valEmpty_(descrip, csText)) {
                w___TYPE_NOT_FOUND.lbDescrip = descrip;
            }

            w___TYPE_NOT_FOUND.Height = 2900;

            //*TODO:** can't found type for with block
            //*With .Line1
            __TYPE_NOT_FOUND w___TYPE_NOT_FOUND = w___TYPE_NOT_FOUND.Line1;
                w___TYPE_NOT_FOUND.Y1 = 1780;
                w___TYPE_NOT_FOUND.Y2 = 1780;
            // {end with: w___TYPE_NOT_FOUND}
            //*TODO:** can't found type for with block
            //*With .Line2
            __TYPE_NOT_FOUND w___TYPE_NOT_FOUND = w___TYPE_NOT_FOUND.Line2;
                w___TYPE_NOT_FOUND.Y1 = 1800;
                w___TYPE_NOT_FOUND.Y2 = 1800;
            // {end with: w___TYPE_NOT_FOUND}

            w___TYPE_NOT_FOUND.cmdOk..top = 1900;
            w___TYPE_NOT_FOUND.cmdCancel..top = 1900;

            w___TYPE_NOT_FOUND.TxValue.Text = value;

            w___TYPE_NOT_FOUND.Show(vbModal);

        // {end with: w___TYPE_NOT_FOUND}

        if (mGlobal.G_FormResult) {
            value = mGlobal.G_InputValue;
        }

        _rtn = mGlobal.G_FormResult;

        mGlobal.G_FormResult = oldformResult;
        return _rtn;
    }

    public static boolean getInputEx_(String value, String descrip) { // TODO: Use of ByRef founded
        boolean _rtn = false;
        boolean oldformResult = false;
        String inputvalue = "";

        oldformResult = mGlobal.G_FormResult;

        mGlobal.G_FormResult = false;
        fEdit.TxValue.Visible = false;
        fEdit.Shape1.Visible = false;
        fEdit.txValueMemo.Visible = true;
        fEdit.txValueMemo.Text = value;
        fEdit.cmdOk.default = false;
        fEdit.Shape2.Visible = true;
        fEdit.fEdit.setBMemo(true);
        if (!valEmpty_(descrip, csText)) {
            fEdit.lbDescrip = descrip;
        }
        fEdit.Show(vbModal);
        if (mGlobal.G_FormResult) {
            value = mGlobal.G_InputValue;
        }

        _rtn = mGlobal.G_FormResult;

        mGlobal.G_FormResult = oldformResult;
        return _rtn;
    }

    private static Object pPutSeparetor(boolean isEx, Object tBBar, boolean haveSeparator) { // TODO: Use of ByRef founded
        Object _rtn = null;
        _rtn = haveSeparator;
        if (haveSeparator) { return _rtn; }

        if (isEx) {
            if (tBBar.ButtonCount > 0) {
                tBBar.AddButton(, , , , , CTBSEPARATOR);
                _rtn = true;
            }
        } 
        else {
            if (tBBar.Buttons.cDateNames.count()) {
                if (tBBar.Buttons.cDateNames.item(tBBar.Buttons.cDateNames.count()).Style != tbrSeparator) {
                    tBBar.Buttons.cDateNames.add(, , , tbrSeparator);
                }
                _rtn = true;
            }
        }
        return _rtn;
    }

    private static void pPutSeparetorEx(boolean isEx, Object tBBar) { // TODO: Use of ByRef founded
        if (isEx) {
            tBBar.AddButton(, , , , , CTBSEPARATOR);
        } 
        else {
            tBBar.Buttons.cDateNames.add(, , , tbrSeparator);
        }
    }

    private static void pMsgAux(String msg, VbMsgBoxStyle style, String title, String details) {
        if (!mError.gbSilent) {
            msg = pGetMessage(msg);
            title = pGetTitle(title);
            //MsgBox msg, Style, Title
            fMsg.fMsg.showDialog(msg, style, title, details);
            Unload(fMsg);
        } 
        else {
            mError.gLastErrDescription = pGetMessage(msg);
        }
    }

    private static String pGetTitle(String title) {
        if (title.equals("")) { title = "CrowSoft"; }
        if (title.equals("@@@@@")) { title = "CrowSoft"; }
        return title;
    }

    public static void msgError_(String msg, String title) {
        pMsgAux(msg, vbCritical, title, "");
    }

    public static void msgWarning_(String msg, String title, String details) {
        pMsgAux(msg, vbExclamation, title, details);
    }

    public static Object msgInfo_(String msg, String title) {
        pMsgAux(msg, vbInformation, title, "");
    }

    public static boolean ask_(String msg, VbMsgBoxResult default, String title) {
        boolean _rtn = false;
        if (!mError.gbSilent) {
            int n = 0;
            msg = pGetMessage(msg);
            title = pGetTitle(title);
            if (msg.indexOf("?", 1) == 0) { msg = "¿" + msg + "?"; }
            if (default == vbNo) { n = vbDefaultButton2; }
            pGetTitle(title);
            //Ask_ = vbYes = MsgBox(msg, vbYesNo + n + vbQuestion, Title)
            fMsg.fMsg.showDialog(msg, vbYesNo + n + vbQuestion, title, "");
            _rtn = fMsg.fMsg.getRslt() == vbYes;
            Unload(fMsg);
        }
        return _rtn;
    }

    public static void centerForm_(Object frm, Object fMain) { // TODO: Use of ByRef founded
        boolean bIsMDI = false;

        // MDIChild no es una propiedad de MDIForm
        

            bIsMDI = frm.MDIChild;

            if (bIsMDI) {
                centerMdiChild_(frm);
            } 
            else {
                centerNoMdiChild_(frm, fMain);
            }

            frm.Top = IIf(frm.Top > 0, frm.Top, 0);
            frm.Left = IIf(frm.Left > 0, frm.Left, 0);
        }
    }

    private static void centerMdiChild_(Form frm) { // TODO: Use of ByRef founded
        Form frm2 = null;
        int offLeft = 0;
        int offTop = 0;
        int offLeft2 = 0;
        int offTop2 = 0;

        // Datos del parent
        int parWidth = 0;
        int parHeight = 0;
        RECT rct = null;

        // si no tengo una referencia no hago nada
        if (Forms == null) { return; }

        // recorro la coleccion de forms para generar una cascada
        for (int _i = 0; _i < Forms.size(); _i++) {
            frm2 = Forms.getItem(_i);
            if (!(TypeOf frm2 Is MDIForm)) {
                if (frm2.MDIChild) {
                    if (frm2.Visible) {
                        offLeft = IIf(offLeft > frm2.Left, offLeft, frm2.Left);
                        offTop = IIf(offTop > frm2.Top, offTop, frm2.Top);
                    }
                }
            }
        }

        // Obtengo las coordenadas del MDI
        GetClientRect(GetParent(frm.hwnd), rct);

        parWidth = (rct..right - rct..left) * Screen.TwipsPerPixelY;
        parHeight = (rct..bottom - rct..top) * Screen.TwipsPerPixelX;

        frm.Top = (parHeight - frm.Height) / 2.5;
        frm.Left = (parWidth - frm.Width) / 2.5;

        // Para formularios ya mostrados no los muevo
        if (frm.Visible) { return; }

        offLeft2 = 200;
        offTop2 = 200;

        // si con el movimiento los dezplace fuera de main, lo reubico
        offLeft2 = IIf(offLeft + frm.Width + offLeft2 > parWidth, offLeft2 * -1, offLeft2);
        offTop2 = IIf(offTop + frm.Height + offTop2 > parHeight, offTop2 * -1, offTop2);


        frm.Left = IIf(offLeft >= frm.Left, offLeft + offLeft2, frm.Left);
        frm.Top = IIf(offTop >= frm.Top, offTop + offTop2, frm.Top);
    }

    private static void centerNoMdiChild_(Form frm, Form fMain) { // TODO: Use of ByRef founded
        // With frm;
            // Si no hay info de main me centro en NameFunction de la pantalla
            if (fMain == null) {
                frm.Top = (Screen.Height - frm.Height) * 0.4;
                frm.Left = (Screen.Width - frm.Width) * 0.5;

                // Main esta minimizado asi que centro en NameFunction de screen
            } 
            else if (fMain.WindowState == vbMinimized) {
                frm.Top = (Screen.Height - frm.Height) / 2.5;
                frm.Left = (Screen.Width - frm.Width) / 2.5;

                // Main esta en la pantalla asi que me centro en NameFunction de el
            } 
            else {
                // main produce un desplazamiento
                frm.Top = (Screen.Height - frm.Height) / 2.5 + fMain.Top;
                frm.Left = (Screen.Width - frm.Width) / 2.5 + fMain.Left;
            }
        // {end with: frm}
    }

    private static String pGetMessage(String msg) {
        msg = msg.replace(C_CRLF, "\\n");
        msg = msg.replace(C_CRLF2, "\\n");

        return msg;
    }

    public static String getComputerName() {
        String _rtn = "";
        String s = "";
        s = String(255, " ");
        int l = 0;
        l = s.length();

        if (GetComputerName2(s, l) != 0) {
            _rtn = s.substring(1, l);
        } 
        else {
            _rtn = "";
        }
        return _rtn;
    }

    *#End If

    //-------------
    // Varias
    public static boolean valEmpty_(Object value, csTypes varType) {
        boolean _rtn = false;
        

            switch (varType) {
                case  csText:
                    _rtn = Trim(value) == "";
                    break;
                case  csInteger:
                case  csCurrency:
                case  csDouble:
                case  csLong:
                case  csSingle:
                    if (!IsNumeric(value)) {
                        _rtn = true;
                    } 
                    else {
                        _rtn = value == 0;
                    }
                    break;
                case  csId:
                    if (!IsNumeric(value)) {
                        _rtn = true;
                    } 
                    else {
                        _rtn = value == csNO_ID;
                    }
                    break;
                case  csDate:
                    if (!IsDate(value)) {
                        _rtn = true;
                    } 
                    else {
                        Date noDate = null;
                        _rtn = value == G.getDateFromString("1/1/1900")  || value == noDate;
                    }
                    break;
            }
        }
        return _rtn;
    }

    public static String removeLastColon_(String list) {
        String _rtn = "";
        list = Trim(list);
        if (","
.equals(list.substring(list.length() - 1))) {
            _rtn = list.substring(1, list.length() - 1);
        } 
        else {
            _rtn = list;
        }
        return _rtn;
    }

    //--------------
    public static boolean existsFile_(String pathYName) {
        return Dir(pathYName) != "";
    }
    //--------------
    public static String arrayToString_(Object v) {
        int i = 0;
        String s = "";
        for (i = 0; i <= v.length; i++) {
            s = s + v(i) + ",";
        }
        return removeLastColon_(s);
    }

    public static String getGetToken(String token, String source) {
        int i = 0;
        String s = "";
        String c = "";
        int l = 0;

        if (!("=".equals(token.substring(token.length() - 1)))) { token = token + "="; }

        l = source.length();
        i = source.toLowerCase().indexOf(token.toLowerCase(), 1);
        if (i == 0) { Exit Property; }
        i = i + token.length() - 1;
        do        i = i + 1;
        if (i > l) { break; }
        c = source.substring(i, 1);
        if (!(c.equals(";"))) {
            s = s + c;
        } 
        else {
            break;
        }
    }

    return s;
    }

    public static Date vDGetDateById_(csDateEnum dateIndex, Date iniDate) {
        if (iniDate == 0) { iniDate = Date; }

        Date rtn = null;
        int dayNumber = 0;

        switch (dateIndex) {
            case  csYearLast_FirstDay:
                iniDate = DateAdd("yyyy", -1, iniDate);
                dateIndex = csYear_FirstDay;
                break;
            case  csYearLast_LastDay:
                iniDate = DateAdd("yyyy", -1, iniDate);
                dateIndex = csYear_LastDay;
                break;
            case  csYearNext_FirstDay:
                iniDate = DateAdd("yyyy", 1, iniDate);
                dateIndex = csYear_FirstDay;
                break;
            case  csYearNext_LastDay:
                iniDate = DateAdd("yyyy", 1, iniDate);
                dateIndex = csYear_LastDay;
                break;
        }

        switch (dateIndex) {
            case  csWeeckLast_FirstDay:
                iniDate = DateAdd("d", -7, iniDate);
                dateIndex = csWeeck_FirstDay;
                break;
            case  csWeeckLast_LastDay:
                iniDate = DateAdd("d", -7, iniDate);
                dateIndex = csWeeck_LastDay;
                break;
            case  csWeeckNext_FirstDay:
                iniDate = DateAdd("d", 7, iniDate);
                dateIndex = csWeeck_FirstDay;
                break;
            case  csWeeckNext_LastDay:
                iniDate = DateAdd("d", 7, iniDate);
                dateIndex = csWeeck_LastDay;

                break;
            case  csMonthLast_FirstDay:
                iniDate = DateAdd("m", -1, iniDate);
                dateIndex = csMonth_FirstDay;
                break;
            case  csMonthLast_LastDay:
                iniDate = DateAdd("m", -1, iniDate);
                dateIndex = csMonth_LastDay;
                break;
            case  csMonthNext_FirstDay:
                iniDate = DateAdd("m", 1, iniDate);
                dateIndex = csMonth_FirstDay;
                break;
            case  csMonthNext_LastDay:
                iniDate = DateAdd("m", 1, iniDate);
                dateIndex = csMonth_LastDay;

                break;
            case  csYear_FirstDay:
                iniDate = DateAdd("m", -Month(iniDate) + 1, iniDate);
                dateIndex = csMonth_FirstDay;
                break;
            case  csYear_LastDay:
                iniDate = DateAdd("yyyy", 1, iniDate);
                iniDate = DateAdd("m", -Month(iniDate), iniDate);
                dateIndex = csMonth_LastDay;
                break;
        }

        switch (dateIndex) {
            case  csToday:
                rtn = iniDate;

                break;
            case  csYesterday:
                rtn = DateAdd("d", -1, iniDate);

                break;
            case  csTomorrow:
                rtn = DateAdd("d", 1, iniDate);

                break;
            case  csWeeck_FirstDay:
                dayNumber = Weekday(iniDate, vbMonday);
                rtn = DateAdd("d", 1 - dayNumber, iniDate);

                break;
            case  csWeeck_LastDay:
                dayNumber = Weekday(iniDate, vbMonday);
                rtn = DateAdd("d", 7 - dayNumber, iniDate);

                break;
            case  csMonth_FirstDay:
                dayNumber = Day(iniDate);
                rtn = DateAdd("d", -dayNumber + 1, iniDate);

                break;
            case  csMonth_LastDay:
                iniDate = DateAdd("m", 1, iniDate);
                dayNumber = Day(iniDate);
                rtn = DateAdd("d", -dayNumber, iniDate);
                break;
        }

        return rtn;
    }

    public static boolean implementsInterface(Object objOne, Object interfaz) {
        
            VBA.ex.Clear;

            interfaz = objOne;

            return VBA.ex.Number == 0;
        }
    }

    *#If PREPROC_KERNEL_CLIENT Then
    public static Object showHelp_(int hwnd, String helpFileFullName, String helpFile, int contextId) {

        if (helpFile.equals("")) {
            helpFile = helpFileFullName;
        } 
        else {
            helpFile = pGetValidPath(mGlobal.gAppPath) + helpFile;
        }

        if (!(helpFile.equals(""))) {

            if (!existsFile_(helpFile)) {
                helpFile = mGlobal.gDefaultHelpFile;
            }
        } 
        else {
            helpFile = pGetValidPath(mGlobal.gAppPath) + mGlobal.gDefaultHelpFile;
        }

        if (!(helpFile.equals(""))) {

            if (!existsFile_(helpFile)) {
                msgWarning_("El archivo de ayuda " + helpFile + " no se encuentra");
            } 
            else {
                if (contextId) {
                    HTMLHelp(hwnd, helpFile, HH_HELP_CONTEXT, contextId);
                } 
                else {
                    HTMLHelp(hwnd, helpFile, HH_DISPLAY_TOPIC, 0);
                }
            }
        } 
        else {
            msgWarning_("El sistema no tiene definido un archivo de ayuda");
        }
    }

    private static String pGetValidPath(String path) {
        if (!("\\".equals(path.substring(path.length() - 1)))) { path = path + "\\"; }
        return path;
    }

    public static void sendEmailToCrowSoft_(String subject, String body) {
        try {

            Object mail = null;
            mail = cUtil.createObject("CSMail.cMail");

            if (mail.SendEmail("soporte@crowsoft.com.ar", mGlobal.gEmailAddress, mGlobal.gEmailAddress, mGlobal.gEmailServer, mGlobal.gEmailPort, mGlobal.gEmailUser, mGlobal.gEmailPwd, subject, body)) {
                msgInfo_("El mail se envio con éxito");
            } 
            else {
                msgWarning_("El mail fallo " + mail.errNumber + String.valueOf(" - ") + mail.ErrDescrip);
            }

            return;
        } catch (Exception ex) {
            msgError_(VBA.ex.Description);

        }
    }

    *#End If
    // construccion - destruccion

}

class RECT {
    public Long left;
    public Long top;
    public Long right;
    public Long bottom;
}


class OSVERSIONINFO {
    public Long dwOSVersionInfoSize;
    public Long dwMajorVersion;
    public Long dwMinorVersion;
    public Long dwBuildNumber;
    public Long dwPlatformId;
    public String szCSDVersion;//'  Maintenance string for PSS usage
}


class cIMAGENES {
    public static int IMAGEN_NEW = 0;
    public static int IMAGEN_SAVE = 1;
    public static int IMAGEN_PRINTOBJ = 2;
    public static int IMAGEN_COPY = 3;
    public static int IMAGEN_CUT = 4;
    public static int IMAGEN_PASTE = 5;
    public static int IMAGEN_DELETE = 6;
    public static int IMAGEN_EDIT = 7;
    public static int IMAGEN_PREVIEW = 8;
    public static int IMAGEN_EXIT = 9;
    public static int IMAGEN_WITHOUT_PARAMS = 10;
    public static int IMAGEN_WITH_PARAMS = 11;
    public static int IMAGEN_SEARCH = 12;
    public static int IMAGEN_REFRESH = 13;
    public static int IMAGEN_GRID = 14;
    public static int IMAGEN_ANULAR = 15;
    public static int IMAGEN_EDIT_STATE = 16;
    public static int IMAGEN_RELOAD = 17;
    public static int IMAGEN_ATTACH = 18;
    public static int IMAGEN_DOC_APLIC = 19;
    public static int IMAGEN_DOC_FIRST = 20;
    public static int IMAGEN_DOC_PREVIOUS = 21;
    public static int IMAGEN_DOC_NEXT = 22;
    public static int IMAGEN_DOC_LAST = 23;
    public static int IMAGEN_DOC_SIGNATURE = 24;
    public static int IMAGEN_DOC_HELP = 25;
    public static int IMAGEN_DOC_MODIFY = 26;
    public static int IMAGEN_DOC_AUX = 27;
    public static int IMAGEN_DOC_EDIT = 28;
    public static int IMAGEN_DOC_TIP = 29;
    public static int IMAGEN_DOC_ALERT = 30;
    public static int IMAGEN_DOC_MERGE = 31;
    public static int IMAGEN_DOC_ACTION = 33;
    public static int IMAGEN_DOC_MAIL = 34;
    public static int IMAGEN_ROLS = 30;
    public static int IMAGEN_PERMISSIONS = 32;
    public static int IMAGEN_REVOKE = 28;
    public static int IMAGEN_DEACTIVE = 28;
    public static int IMAGEN_SAVE_PARAMS = 35;
    public static int IMAGEN_RELOAD_PARAMS = 36;
    public static int IMAGEN_BUTTON_SAVE_AS = 35;
}


