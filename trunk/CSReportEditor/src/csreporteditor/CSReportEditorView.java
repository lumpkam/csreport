/*
 * CSReportEditorView.java
 */

package csreporteditor;

import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * The application's main frame.
 */
public class CSReportEditorView extends FrameView {

    public CSReportEditorView(SingleFrameApplication app) {
        super(app);

        initComponents();

        getFrame().setIconImage(new ImageIcon("C:\\dev\\proyectos.nb\\CSReport\\CSReportEditor\\src\\csreporteditor\\resources\\appIcon.png").getImage());

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = CSReportEditorApp.getApplication().getMainFrame();
            aboutBox = new CSReportEditorAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        CSReportEditorApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu mnuFile = new javax.swing.JMenu();
        mnuNew = new javax.swing.JMenuItem();
        mnuOpen = new javax.swing.JMenuItem();
        mnuSave = new javax.swing.JMenuItem();
        mnuSaveAs = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnuPageSetup = new javax.swing.JMenuItem();
        mnuPrinterSettings = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mnuPreview = new javax.swing.JMenuItem();
        mnuPrint = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        mnuOpenRecent = new javax.swing.JMenu();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem mnuExit = new javax.swing.JMenuItem();
        mnuEdit = new javax.swing.JMenu();
        mnuCopy = new javax.swing.JMenuItem();
        mnuCut = new javax.swing.JMenuItem();
        mnuPaste = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        mnuSearch = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        mnuSections = new javax.swing.JMenu();
        mnuAddHeader = new javax.swing.JMenuItem();
        mnuAddGroup = new javax.swing.JMenuItem();
        mnuAddFooter = new javax.swing.JMenuItem();
        mnuControls = new javax.swing.JMenu();
        mnuAddLabel = new javax.swing.JMenuItem();
        mnuAddLine = new javax.swing.JMenuItem();
        mnuAddDataBaseField = new javax.swing.JMenuItem();
        mnuAddImage = new javax.swing.JMenuItem();
        mnuAddChart = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        mnuMove = new javax.swing.JMenu();
        mnuMoveHorizontal = new javax.swing.JMenuItem();
        mnuMoveVertical = new javax.swing.JMenuItem();
        mnuLock = new javax.swing.JMenuItem();
        mnuMoveOnAllDirections = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        mnuSizeOfStepWithKeyboard = new javax.swing.JMenuItem();
        mnuView = new javax.swing.JMenu();
        mnuViewToolBox = new javax.swing.JMenuItem();
        mnuViewControlGrid = new javax.swing.JMenuItem();
        mnuViewControlTree = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        mnuViewGrid = new javax.swing.JMenu();
        mnuViewGridPoints = new javax.swing.JMenuItem();
        mnuViewGridLines = new javax.swing.JMenu();
        mnuViewGridLinesVertial = new javax.swing.JMenuItem();
        mnuViewGridLinesHorizontal = new javax.swing.JMenuItem();
        mnuViewGridLinesBoth = new javax.swing.JMenuItem();
        mnuViewGridHide = new javax.swing.JMenuItem();
        mnuDataBase = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        javax.swing.JMenu mnuHelp = new javax.swing.JMenu();
        javax.swing.JMenuItem mnuAbout = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        jSplitPane1.setBorder(null);
        jSplitPane1.setName("jSplitPane1"); // NOI18N

        jTabbedPane2.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        jTabbedPane2.setName("jTabbedPane2"); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(csreporteditor.CSReportEditorApp.class).getContext().getResourceMap(CSReportEditorView.class);
        jTabbedPane2.addTab(resourceMap.getString("jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jPanel3.setName("jPanel3"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab(resourceMap.getString("jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        jPanel4.setName("jPanel4"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab(resourceMap.getString("jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        jSplitPane1.setLeftComponent(jTabbedPane2);

        jTabbedPane1.setMinimumSize(new java.awt.Dimension(250, 128));
        jTabbedPane1.setName("jTabbedPane1"); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(250, 100));

        jScrollPane1.setName("jScrollPane1"); // NOI18N
        jTabbedPane1.addTab(resourceMap.getString("jScrollPane1.TabConstraints.tabTitle"), jScrollPane1); // NOI18N

        jSplitPane1.setRightComponent(jTabbedPane1);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
        );

        menuBar.setName("menuBar"); // NOI18N

        mnuFile.setMnemonic('F');
        mnuFile.setText(resourceMap.getString("mnuFile.text")); // NOI18N
        mnuFile.setName("mnuFile"); // NOI18N

        mnuNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        mnuNew.setMnemonic('N');
        mnuNew.setText(resourceMap.getString("mnuNew.text")); // NOI18N
        mnuNew.setName("mnuNew"); // NOI18N
        mnuFile.add(mnuNew);

        mnuOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        mnuOpen.setMnemonic('O');
        mnuOpen.setText(resourceMap.getString("mnuOpen.text")); // NOI18N
        mnuOpen.setName("mnuOpen"); // NOI18N
        mnuFile.add(mnuOpen);

        mnuSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        mnuSave.setMnemonic('S');
        mnuSave.setText(resourceMap.getString("mnuSave.text")); // NOI18N
        mnuSave.setName("mnuSave"); // NOI18N
        mnuFile.add(mnuSave);

        mnuSaveAs.setText(resourceMap.getString("mnuSaveAs.text")); // NOI18N
        mnuSaveAs.setName("mnuSaveAs"); // NOI18N
        mnuFile.add(mnuSaveAs);

        jSeparator1.setName("jSeparator1"); // NOI18N
        mnuFile.add(jSeparator1);

        mnuPageSetup.setText(resourceMap.getString("mnuPageSetup.text")); // NOI18N
        mnuPageSetup.setName("mnuPageSetup"); // NOI18N
        mnuFile.add(mnuPageSetup);

        mnuPrinterSettings.setText(resourceMap.getString("mnuPrinterSettings.text")); // NOI18N
        mnuPrinterSettings.setName("mnuPrinterSettings"); // NOI18N
        mnuFile.add(mnuPrinterSettings);

        jSeparator2.setName("jSeparator2"); // NOI18N
        mnuFile.add(jSeparator2);

        mnuPreview.setText(resourceMap.getString("mnuPreview.text")); // NOI18N
        mnuPreview.setName("mnuPreview"); // NOI18N
        mnuFile.add(mnuPreview);

        mnuPrint.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        mnuPrint.setText(resourceMap.getString("mnuPrint.text")); // NOI18N
        mnuPrint.setName("mnuPrint"); // NOI18N
        mnuFile.add(mnuPrint);

        jSeparator3.setName("jSeparator3"); // NOI18N
        mnuFile.add(jSeparator3);

        mnuOpenRecent.setText(resourceMap.getString("mnuOpenRecent.text")); // NOI18N
        mnuOpenRecent.setName("mnuOpenRecent"); // NOI18N
        mnuFile.add(mnuOpenRecent);

        jSeparator4.setName("jSeparator4"); // NOI18N
        mnuFile.add(jSeparator4);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(csreporteditor.CSReportEditorApp.class).getContext().getActionMap(CSReportEditorView.class, this);
        mnuExit.setAction(actionMap.get("quit")); // NOI18N
        mnuExit.setName("mnuExit"); // NOI18N
        mnuFile.add(mnuExit);

        menuBar.add(mnuFile);

        mnuEdit.setMnemonic('E');
        mnuEdit.setText(resourceMap.getString("mnuEdit.text")); // NOI18N
        mnuEdit.setName("mnuEdit"); // NOI18N

        mnuCopy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        mnuCopy.setMnemonic('C');
        mnuCopy.setText(resourceMap.getString("mnuCopy.text")); // NOI18N
        mnuCopy.setName("mnuCopy"); // NOI18N
        mnuEdit.add(mnuCopy);

        mnuCut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        mnuCut.setText(resourceMap.getString("mnuCut.text")); // NOI18N
        mnuCut.setName("mnuCut"); // NOI18N
        mnuEdit.add(mnuCut);

        mnuPaste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        mnuPaste.setText(resourceMap.getString("mnuPaste.text")); // NOI18N
        mnuPaste.setName("mnuPaste"); // NOI18N
        mnuEdit.add(mnuPaste);

        jSeparator5.setName("jSeparator5"); // NOI18N
        mnuEdit.add(jSeparator5);

        mnuSearch.setText(resourceMap.getString("mnuSearch.text")); // NOI18N
        mnuSearch.setName("mnuSearch"); // NOI18N
        mnuEdit.add(mnuSearch);

        jSeparator8.setName("jSeparator8"); // NOI18N
        mnuEdit.add(jSeparator8);

        mnuSections.setText(resourceMap.getString("mnuSections.text")); // NOI18N
        mnuSections.setName("mnuSections"); // NOI18N

        mnuAddHeader.setText(resourceMap.getString("mnuAddHeader.text")); // NOI18N
        mnuAddHeader.setName("mnuAddHeader"); // NOI18N
        mnuSections.add(mnuAddHeader);

        mnuAddGroup.setText(resourceMap.getString("mnuAddGroup.text")); // NOI18N
        mnuAddGroup.setName("mnuAddGroup"); // NOI18N
        mnuSections.add(mnuAddGroup);

        mnuAddFooter.setText(resourceMap.getString("mnuAddFooter.text")); // NOI18N
        mnuAddFooter.setName("mnuAddFooter"); // NOI18N
        mnuSections.add(mnuAddFooter);

        mnuEdit.add(mnuSections);

        mnuControls.setText(resourceMap.getString("mnuControls.text")); // NOI18N
        mnuControls.setName("mnuControls"); // NOI18N

        mnuAddLabel.setText(resourceMap.getString("mnuAddLabel.text")); // NOI18N
        mnuAddLabel.setName("mnuAddLabel"); // NOI18N
        mnuControls.add(mnuAddLabel);

        mnuAddLine.setText(resourceMap.getString("mnuAddLine.text")); // NOI18N
        mnuAddLine.setName("mnuAddLine"); // NOI18N
        mnuControls.add(mnuAddLine);

        mnuAddDataBaseField.setText(resourceMap.getString("mnuAddDataBaseField.text")); // NOI18N
        mnuAddDataBaseField.setName("mnuAddDataBaseField"); // NOI18N
        mnuControls.add(mnuAddDataBaseField);

        mnuAddImage.setText(resourceMap.getString("mnuAddImage.text")); // NOI18N
        mnuAddImage.setName("mnuAddImage"); // NOI18N
        mnuControls.add(mnuAddImage);

        mnuAddChart.setText(resourceMap.getString("mnuAddChart.text")); // NOI18N
        mnuAddChart.setName("mnuAddChart"); // NOI18N
        mnuControls.add(mnuAddChart);

        mnuEdit.add(mnuControls);

        jSeparator6.setName("jSeparator6"); // NOI18N
        mnuEdit.add(jSeparator6);

        mnuMove.setText(resourceMap.getString("mnuMove.text")); // NOI18N
        mnuMove.setName("mnuMove"); // NOI18N

        mnuMoveHorizontal.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F12, 0));
        mnuMoveHorizontal.setText(resourceMap.getString("mnuMoveHorizontal.text")); // NOI18N
        mnuMoveHorizontal.setName("mnuMoveHorizontal"); // NOI18N
        mnuMove.add(mnuMoveHorizontal);

        mnuMoveVertical.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, 0));
        mnuMoveVertical.setText(resourceMap.getString("mnuMoveVertical.text")); // NOI18N
        mnuMoveVertical.setName("mnuMoveVertical"); // NOI18N
        mnuMove.add(mnuMoveVertical);

        mnuLock.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, 0));
        mnuLock.setText(resourceMap.getString("mnuLock.text")); // NOI18N
        mnuLock.setName("mnuLock"); // NOI18N
        mnuMove.add(mnuLock);

        mnuMoveOnAllDirections.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, 0));
        mnuMoveOnAllDirections.setText(resourceMap.getString("mnuMoveOnAllDirections.text")); // NOI18N
        mnuMoveOnAllDirections.setName("mnuMoveOnAllDirections"); // NOI18N
        mnuMove.add(mnuMoveOnAllDirections);

        jSeparator7.setName("jSeparator7"); // NOI18N
        mnuMove.add(jSeparator7);

        mnuSizeOfStepWithKeyboard.setText(resourceMap.getString("mnuSizeOfStepWithKeyboard.text")); // NOI18N
        mnuSizeOfStepWithKeyboard.setName("mnuSizeOfStepWithKeyboard"); // NOI18N
        mnuMove.add(mnuSizeOfStepWithKeyboard);

        mnuEdit.add(mnuMove);

        menuBar.add(mnuEdit);

        mnuView.setMnemonic('V');
        mnuView.setText(resourceMap.getString("mnuView.text")); // NOI18N
        mnuView.setName("mnuView"); // NOI18N

        mnuViewToolBox.setText(resourceMap.getString("mnuViewToolBox.text")); // NOI18N
        mnuViewToolBox.setName("mnuViewToolBox"); // NOI18N
        mnuView.add(mnuViewToolBox);

        mnuViewControlGrid.setText(resourceMap.getString("mnuViewControlGrid.text")); // NOI18N
        mnuViewControlGrid.setName("mnuViewControlGrid"); // NOI18N
        mnuView.add(mnuViewControlGrid);

        mnuViewControlTree.setText(resourceMap.getString("mnuViewControlTree.text")); // NOI18N
        mnuViewControlTree.setName("mnuViewControlTree"); // NOI18N
        mnuView.add(mnuViewControlTree);

        jSeparator9.setName("jSeparator9"); // NOI18N
        mnuView.add(jSeparator9);

        mnuViewGrid.setText(resourceMap.getString("mnuViewGrid.text")); // NOI18N
        mnuViewGrid.setName("mnuViewGrid"); // NOI18N

        mnuViewGridPoints.setText(resourceMap.getString("mnuViewGridPoints.text")); // NOI18N
        mnuViewGridPoints.setName("mnuViewGridPoints"); // NOI18N
        mnuViewGrid.add(mnuViewGridPoints);

        mnuViewGridLines.setText(resourceMap.getString("mnuViewGridLines.text")); // NOI18N
        mnuViewGridLines.setName("mnuViewGridLines"); // NOI18N

        mnuViewGridLinesVertial.setText(resourceMap.getString("mnuViewGridLinesVertial.text")); // NOI18N
        mnuViewGridLinesVertial.setName("mnuViewGridLinesVertial"); // NOI18N
        mnuViewGridLines.add(mnuViewGridLinesVertial);

        mnuViewGridLinesHorizontal.setText(resourceMap.getString("mnuViewGridLinesHorizontal.text")); // NOI18N
        mnuViewGridLinesHorizontal.setName("mnuViewGridLinesHorizontal"); // NOI18N
        mnuViewGridLines.add(mnuViewGridLinesHorizontal);

        mnuViewGridLinesBoth.setText(resourceMap.getString("mnuViewGridLinesBoth.text")); // NOI18N
        mnuViewGridLinesBoth.setName("mnuViewGridLinesBoth"); // NOI18N
        mnuViewGridLines.add(mnuViewGridLinesBoth);

        mnuViewGrid.add(mnuViewGridLines);

        mnuViewGridHide.setText(resourceMap.getString("mnuViewGridHide.text")); // NOI18N
        mnuViewGridHide.setName("mnuViewGridHide"); // NOI18N
        mnuViewGrid.add(mnuViewGridHide);

        mnuView.add(mnuViewGrid);

        menuBar.add(mnuView);

        mnuDataBase.setMnemonic('D');
        mnuDataBase.setText(resourceMap.getString("mnuDataBase.text")); // NOI18N
        mnuDataBase.setName("mnuDataBase"); // NOI18N

        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        mnuDataBase.add(jMenuItem1);

        jMenuItem2.setText(resourceMap.getString("jMenuItem2.text")); // NOI18N
        jMenuItem2.setName("jMenuItem2"); // NOI18N
        mnuDataBase.add(jMenuItem2);

        jSeparator10.setName("jSeparator10"); // NOI18N
        mnuDataBase.add(jSeparator10);

        jMenuItem3.setText(resourceMap.getString("jMenuItem3.text")); // NOI18N
        jMenuItem3.setName("jMenuItem3"); // NOI18N
        mnuDataBase.add(jMenuItem3);

        jSeparator11.setName("jSeparator11"); // NOI18N
        mnuDataBase.add(jSeparator11);

        jMenuItem4.setText(resourceMap.getString("jMenuItem4.text")); // NOI18N
        jMenuItem4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenuItem4.setName("jMenuItem4"); // NOI18N
        mnuDataBase.add(jMenuItem4);

        jSeparator12.setName("jSeparator12"); // NOI18N
        mnuDataBase.add(jSeparator12);

        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N

        jMenuItem5.setText(resourceMap.getString("jMenuItem5.text")); // NOI18N
        jMenuItem5.setName("jMenuItem5"); // NOI18N
        jMenu1.add(jMenuItem5);

        jMenuItem6.setText(resourceMap.getString("jMenuItem6.text")); // NOI18N
        jMenuItem6.setName("jMenuItem6"); // NOI18N
        jMenu1.add(jMenuItem6);

        jMenuItem7.setText(resourceMap.getString("jMenuItem7.text")); // NOI18N
        jMenuItem7.setName("jMenuItem7"); // NOI18N
        jMenu1.add(jMenuItem7);

        mnuDataBase.add(jMenu1);

        jSeparator13.setName("jSeparator13"); // NOI18N
        mnuDataBase.add(jSeparator13);

        jMenuItem8.setText(resourceMap.getString("jMenuItem8.text")); // NOI18N
        jMenuItem8.setName("jMenuItem8"); // NOI18N
        mnuDataBase.add(jMenuItem8);

        menuBar.add(mnuDataBase);

        jMenu2.setText(resourceMap.getString("jMenu2.text")); // NOI18N
        jMenu2.setName("jMenu2"); // NOI18N

        jMenuItem9.setText(resourceMap.getString("jMenuItem9.text")); // NOI18N
        jMenuItem9.setName("jMenuItem9"); // NOI18N
        jMenu2.add(jMenuItem9);

        menuBar.add(jMenu2);

        mnuHelp.setText(resourceMap.getString("mnuHelp.text")); // NOI18N
        mnuHelp.setName("mnuHelp"); // NOI18N

        mnuAbout.setAction(actionMap.get("showAboutBox")); // NOI18N
        mnuAbout.setName("mnuAbout"); // NOI18N
        mnuHelp.add(mnuAbout);

        menuBar.add(mnuHelp);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 500, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem mnuAddChart;
    private javax.swing.JMenuItem mnuAddDataBaseField;
    private javax.swing.JMenuItem mnuAddFooter;
    private javax.swing.JMenuItem mnuAddGroup;
    private javax.swing.JMenuItem mnuAddHeader;
    private javax.swing.JMenuItem mnuAddImage;
    private javax.swing.JMenuItem mnuAddLabel;
    private javax.swing.JMenuItem mnuAddLine;
    private javax.swing.JMenu mnuControls;
    private javax.swing.JMenuItem mnuCopy;
    private javax.swing.JMenuItem mnuCut;
    private javax.swing.JMenu mnuDataBase;
    private javax.swing.JMenu mnuEdit;
    private javax.swing.JMenuItem mnuLock;
    private javax.swing.JMenu mnuMove;
    private javax.swing.JMenuItem mnuMoveHorizontal;
    private javax.swing.JMenuItem mnuMoveOnAllDirections;
    private javax.swing.JMenuItem mnuMoveVertical;
    private javax.swing.JMenuItem mnuNew;
    private javax.swing.JMenuItem mnuOpen;
    private javax.swing.JMenu mnuOpenRecent;
    private javax.swing.JMenuItem mnuPageSetup;
    private javax.swing.JMenuItem mnuPaste;
    private javax.swing.JMenuItem mnuPreview;
    private javax.swing.JMenuItem mnuPrint;
    private javax.swing.JMenuItem mnuPrinterSettings;
    private javax.swing.JMenuItem mnuSave;
    private javax.swing.JMenuItem mnuSaveAs;
    private javax.swing.JMenuItem mnuSearch;
    private javax.swing.JMenu mnuSections;
    private javax.swing.JMenuItem mnuSizeOfStepWithKeyboard;
    private javax.swing.JMenu mnuView;
    private javax.swing.JMenuItem mnuViewControlGrid;
    private javax.swing.JMenuItem mnuViewControlTree;
    private javax.swing.JMenu mnuViewGrid;
    private javax.swing.JMenuItem mnuViewGridHide;
    private javax.swing.JMenu mnuViewGridLines;
    private javax.swing.JMenuItem mnuViewGridLinesBoth;
    private javax.swing.JMenuItem mnuViewGridLinesHorizontal;
    private javax.swing.JMenuItem mnuViewGridLinesVertial;
    private javax.swing.JMenuItem mnuViewGridPoints;
    private javax.swing.JMenuItem mnuViewToolBox;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
