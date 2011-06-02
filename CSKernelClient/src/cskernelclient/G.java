/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cskernelclient;

/**
 *
 * @author jalvarez
 */
public class G {
    
    /**
     *
     * @param value
     * @return
     */
    public static boolean isNumeric(Object value) {
        try {
            double d = Double.parseDouble(value.toString());
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
