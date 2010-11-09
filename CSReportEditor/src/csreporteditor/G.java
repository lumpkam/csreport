/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package csreporteditor;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author jalvarez
 */
public class G {

    public static int tp(int twips) {
        final int nTwipsPerInch = 1440;
        int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
        return (twips / nTwipsPerInch) * dpi;
    }

    public static int pt(int pixels) {
        final int nTwipsPerInch = 1440;
        int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
        return (pixels / dpi) * nTwipsPerInch;
    }

    public static int mt(int millimeters) {
        final int nTwipsPerInch = 1440;
        return (int)(mi(millimeters) * nTwipsPerInch);
    }

    public static int mp(int millimeters) {
        int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
        return (int)(mi(millimeters) * dpi);
    }

    public static double mi(double millimeters) {
        return (millimeters * .03937);
    }
}
