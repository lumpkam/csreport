/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cskernelclient;


import java.util.Date;

public class cDate {

//Option Explicit

    //--------------------------------------------------------------------------------
    // cDate
    // 11-05-2003

    //--------------------------------------------------------------------------------
    // notas:

    //--------------------------------------------------------------------------------
    // api win32
    // constantes
    // estructuras
    // funciones

    //--------------------------------------------------------------------------------

    // constantes
    private static final String C_MODULE = "cDate";

//*TODO:** enum is translated as a new class at the end of the file Public Enum csDateEnum
    // estructuras
    // variables privadas
    private cDateNames m_dateNames;
    // eventos
    // propiedadades publicas
    public cDateNames getDateNames() {
        return m_dateNames;
    }
    public void setDateNames(cDateNames rhs) {
        m_dateNames = rhs;
    }
    // propiedadades friend
    // propiedades privadas
    // funciones publicas
    public Date vDGetDate(String dateName, Date iniDate) {
        Date _rtn = null;
        if (G.isNumeric(dateName)) {
            _rtn = vDGetDateById(dateName, iniDate);
        } 
        else {
            _rtn = vDGetDateByName(dateName, iniDate);
        }
        return _rtn;
    }
    public Date vDGetDateByName(String dateName, Date iniDate) {
        Date _rtn = null;
        cDateName dn = null;

        dateName = dateName.toLowerCase();

        for (int _i = 0; _i < m_dateNames.size(); _i++) {
            dn = m_DateNames.getItem(_i);
            if (dn.getCode().equals(dateName) || dn.getName().equals(dateName)) {
                _rtn = vDGetDateById(dn.getId(), iniDate);
                break;
            }
        }
        return _rtn;
    }

    public Date vDGetDateById(csDateEnum dateIndex, Date iniDate) {
        return mUtil.vDGetDateById_(dateIndex, iniDate);
    }

    // funciones friend
    // funciones privadas
    // construccion - destruccion
    private void class_Initialize() {
        try {

            cDateName dn = null;

            m_dateNames = new cDateNames();

            dn = m_dateNames.add(null, "a");
            dn.setId(csDateEnum.CSYESTERDAY);
            dn.setName("Ayer");
            dn.setCode("a");
            dn.setGroup("Dias");

            dn = m_dateNames.add(null, "h");
            dn.setId(csDateEnum.CSTODAY);
            dn.setName("Hoy");
            dn.setCode("h");
            dn.setGroup("Dias");

            dn = m_dateNames.add(null, "m");
            dn.setId(csDateEnum.CSTOMORROW);
            dn.setName("Mañana");
            dn.setCode("m");
            dn.setGroup("Dias");

            dn = m_dateNames.add(null, "psa");
            dn.setId(csDateEnum.CSWEECKLAST_FIRSTDAY);
            dn.setName("Primer dia de la semana anterior");
            dn.setCode("psa");
            dn.setGroup("Semana");

            dn = m_dateNames.add(null, "usa");
            dn.setId(csDateEnum.CSWEECKLAST_LASTDAY);
            dn.setName("Ultimo dia de la semana anterior");
            dn.setCode("usa");
            dn.setGroup("Semana");

            dn = m_dateNames.add(null, "ps");
            dn.setId(csDateEnum.CSWEECK_FIRSTDAY);
            dn.setName("Primer dia de la semana");
            dn.setCode("ps");
            dn.setGroup("Semana");

            dn = m_dateNames.add(null, "us");
            dn.setId(csDateEnum.CSWEECK_LASTDAY);
            dn.setName("Ultimo dia de la semana");
            dn.setCode("us");
            dn.setGroup("Semana");

            dn = m_dateNames.add(null, "psp");
            dn.setId(csDateEnum.CSWEECKNEXT_FIRSTDAY);
            dn.setName("Primer dia de la semana proxima");
            dn.setCode("psp");
            dn.setGroup("Semana");

            dn = m_dateNames.add(null, "usp");
            dn.setId(csDateEnum.CSWEECKNEXT_LASTDAY);
            dn.setName("Ultimo dia de la semana proxima");
            dn.setCode("usp");
            dn.setGroup("Semana");

            dn = m_dateNames.add(null, "pma");
            dn.setId(csDateEnum.CSMONTHLAST_FIRSTDAY);
            dn.setName("Primer dia del mes anterior");
            dn.setCode("pma");
            dn.setGroup("Mes");

            dn = m_dateNames.add(null, "uma");
            dn.setId(csDateEnum.CSMONTHLAST_LASTDAY);
            dn.setName("Ultimo dia del mes anterior");
            dn.setCode("uma");
            dn.setGroup("Mes");

            dn = m_dateNames.add(null, "pm");
            dn.setId(csDateEnum.CSMONTH_FIRSTDAY);
            dn.setName("Primer dia del mes");
            dn.setCode("pm");
            dn.setGroup("Mes");

            dn = m_dateNames.add(null, "um");
            dn.setId(csDateEnum.CSMONTH_LASTDAY);
            dn.setName("Ultimo dia del mes");
            dn.setCode("um");
            dn.setGroup("Mes");

            dn = m_dateNames.add(null, "pmp");
            dn.setId(csDateEnum.CSMONTHNEXT_FIRSTDAY);
            dn.setName("Primer dia del mes proximo");
            dn.setCode("pmp");
            dn.setGroup("Mes");

            dn = m_dateNames.add(null, "ump");
            dn.setId(csDateEnum.CSMONTHNEXT_LASTDAY);
            dn.setName("Ultimo dia del mes proximo");
            dn.setCode("ump");
            dn.setGroup("Mes");

            dn = m_dateNames.add(null, "paa");
            dn.setId(csDateEnum.CSYEARLAST_FIRSTDAY);
            dn.setName("Primer dia del año anterior");
            dn.setCode("paa");
            dn.setGroup("Año");

            dn = m_dateNames.add(null, "uaa");
            dn.setId(csDateEnum.CSYEARLAST_LASTDAY);
            dn.setName("Ultimo dia del año anterior");
            dn.setCode("uaa");
            dn.setGroup("Año");

            dn = m_dateNames.add(null, "pa");
            dn.setId(csDateEnum.CSYEAR_FIRSTDAY);
            dn.setName("Primer dia del año");
            dn.setCode("pa");
            dn.setGroup("Año");

            dn = m_dateNames.add(null, "ua");
            dn.setId(csDateEnum.CSYEAR_LASTDAY);
            dn.setName("Ultimo dia del año");
            dn.setCode("ua");
            dn.setGroup("Año");

            dn = m_dateNames.add(null, "pap");
            dn.setId(csDateEnum.CSYEARNEXT_FIRSTDAY);
            dn.setName("Primer dia del año proximo");
            dn.setCode("pap");
            dn.setGroup("Año");

            dn = m_dateNames.add(null, "uap");
            dn.setId(csDateEnum.CSYEARNEXT_LASTDAY);
            dn.setName("Ultimo dia del año proximo");
            dn.setCode("uap");
            dn.setGroup("Año");

            //*TODO:** goto found: GoTo ExitProc;
        } catch (Exception ex) {
            mError.mngError_(ex, "Class_Initialize", C_MODULE, "");
            if (ex.Number) { /**TODO:** resume found: Resume(ExitProc)*/ }
            //*TODO:** label found: ExitProc:;
        
        }
    }

    private void class_Terminate() {
        try {

            m_dateNames = null;

            //*TODO:** goto found: GoTo ExitProc;
        } catch (Exception ex) {
            mError.mngError_(ex, "Class_Terminate", C_MODULE, "");
            if (ex.Number) { /**TODO:** resume found: Resume(ExitProc)*/ }
            //*TODO:** label found: ExitProc:;
        
        }
    }

    ////////////////////////////////
    //  Codigo estandar de errores
    //  On Error GoTo ControlError
    //
    //  GoTo ExitProc
    //ControlError:
    //  MngError err,"", C_Module, ""
    //  If Err.Number Then Resume ExitProc
    //ExitProc:
    //  On Error Resume Next


}

