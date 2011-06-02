/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cskernelclient;

/**
 *
 * @author jalvarez
 */
public class cCollection {

//Option Explicit

    //--------------------------------------------------------------------------------
    // cCollection
    // 10-07-04

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
    public void collClear(Collection coll) {
        mCollection.CollClear(coll);
    }
    public boolean existsStandarTypeInColl(Object coll, String item) {
        return mCollection.ExistsStandarTypeInColl(coll, item);
    }
    public boolean existsObjectInColl(Object coll, String item) {
        return mCollection.ExistsObjectInColl(coll, item);
    }
    public Object getKey(Object vVal) {
        return mCollection.GetKey(vVal);
    }
    public long getIdFromKey(String sVal) {
        return mCollection.GetIdFromKey(sVal);
    }
    public int getIndexFromKey(Collection coll, String kItem) {
        return mCollection.GetIndexFromKey(coll, kItem);
    }
    // funciones privadas
    // construccion - destruccion


}