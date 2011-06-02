/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cskernelclient;

import org.apache.commons.collections.map.LinkedMap;

public class mCollection {

//Option Explicit

    //--------------------------------------------------------------------------------
    // mCollection
    // 05-01-00

    //--------------------------------------------------------------------------------
    // notas:

    //--------------------------------------------------------------------------------
    // api win32
    // constantes
    // estructuras
    // funciones

    //--------------------------------------------------------------------------------

    // constantes
    // estructuras
    // variables privadas
    // propiedades publicas
    // propiedades privadas
    // funciones publicas
    public static void collClear(LinkedMap coll) { 
        if (coll == null) { return; }
        coll.clear();
    }
    public static boolean existsStandarTypeInColl(Object objectColl, String item) { 
        LinkedMap coll = (LinkedMap)objectColl;
        return coll.containsKey(item);
    }
    public static boolean existsObjectInColl(Object objectColl, String item) { 
        LinkedMap coll = (LinkedMap)objectColl;
        return coll.containsKey(item);
    }
    public static Object getKey(Object value) { 
        if (G.isNumeric(value)) {
            return "K" + value;
        } 
        else {
            return value;
        }
    }
    public static int getIdFromKey(String value) {
        return Integer.parseInt(value.substring(2));
    }
    public static int getIndexFromKey(LinkedMap coll, String kItem) { 
        int i = 0;
        for (i = 1; i <= coll.size(); i++) {
            if (coll.get(kItem) == coll.get(i)) {
                return i;
            }
        }
        return 0;
    }
    // funciones privadas
    // construccion - destruccion
}