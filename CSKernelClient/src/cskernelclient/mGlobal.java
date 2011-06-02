/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cskernelclient;

/**
 *
 * @author jalvarez
 */
public class mGlobal {

//Option Explicit

    //--------------------------------------------------------------------------------
    // mGlobal
    // 10-06-00

    //--------------------------------------------------------------------------------
    // notas:

    //--------------------------------------------------------------------------------
    // api win32
    // constantes
    // estructuras
    // Funciones

    //--------------------------------------------------------------------------------

    // constantes
    // estructuras
    // variables privadas
    // propiedades publicas
    public static cWindow gWindow = new cWindow(); 
    public static String gAppName = "";
    public static String gAppPath = "";
    public static String gDefaultHelpFile = "";

    public static String gErrorDB = "";

    // Para enviar el email por errores
    //
    public static String gEmailServer = "";
    public static String gEmailAddress = "";
    public static int gEmailPort = 0;
    public static String gEmailUser = "";
    public static String gEmailPwd = "";

    public static String gEmailErrDescrip = "";

    public static boolean G_FormResult = false;
    //' Usada por fEditar para devolver el resultado.
    public static String G_InputValue = "";

    public static boolean gNoChangeMouseCursor = false;

    // propiedades privadas
    // Funciones publicas

}