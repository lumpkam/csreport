/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package csreporteditor;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import org.jdesktop.application.Application;
import org.jdesktop.application.ResourceMap;

/**
 *
 * @author jalvarez
 */
public class cEditor {

    private JPanel m_editor = null;
    private JPanel m_rule = null;
    private JPanel m_report = null;

    public cEditor(JPanel editor, JPanel rule, JPanel report) {
        ResourceMap resourceMap = Application.getInstance(csreporteditor.CSReportEditorApp.class).getContext().getResourceMap(CSReportEditorView.class);
        
        m_editor = editor;
        m_editor.setPreferredSize(new Dimension(G.mp(210+53), G.mp(297+2)));
        m_editor.setBackground(resourceMap.getColor("pnEditor1.background"));
        
        m_rule = rule;
        m_rule.setBounds(G.mp(1), G.mp(1), G.mp(50), G.mp(297));
        m_rule.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        m_report = report;
        m_report.setBounds(G.mp(50) + G.mp(1), G.mp(1), G.mp(210), G.mp(297));
        m_report.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        m_report.setBackground(Color.white);
    }

    private cEditor() {};
}
