/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sdk.connector.db;
 
 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXCollapsiblePane;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.openide.util.ImageUtilities;
import org.netbeans.api.settings.ConvertAsProperties;
import softvfc.com.sdk.Protocol;


/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//com.sdk.connector.db//Database//EN",
autostore = false)
public final class DatabaseTopComponent extends TopComponent {

    private static DatabaseTopComponent instance;
    /** path to the icon used by the component and its open action */
    static final String ICON_PATH = "com/sdk/connector/db/database.png"; //NOI18N
    private static final String PREFERRED_ID = "DatabaseTopComponent"; //NOI18N

    private final DefaultTableModel leftModel;
    private final DefaultTableModel rightModel;
    private static RecordingSamples recordingSamples;
    private final SimpleDateFormat sdf;
    private void debug(Exception ex){
        Logger.getLogger(DatabaseTopComponent.class.getName()).log(Level.INFO, null, ex);
    }
    public DatabaseTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(DatabaseTopComponent.class, "CTL_DatabaseTopComponent"));
        setToolTipText(NbBundle.getMessage(DatabaseTopComponent.class, "HINT_DatabaseTopComponent"));
        setIcon(ImageUtilities.loadImage(ICON_PATH, true));

         recordingSamples = new RecordingSamples();
        recordingSamples.run();

        sdf = Protocol.getInstance().getTimestampFormat();
        Date date = new Date();
        SpinnerDateModel shmodel = new SpinnerDateModel(date, null, null, Calendar.AM_PM);
        SpinnerDateModel smmodel = new SpinnerDateModel(date, null, null, Calendar.AM_PM);
        SpinnerDateModel ssmodel = new SpinnerDateModel(date, null, null, Calendar.AM_PM);
        SpinnerDateModel ehmodel = new SpinnerDateModel(date, null, null, Calendar.AM_PM);
        SpinnerDateModel emmodel = new SpinnerDateModel(date, null, null, Calendar.AM_PM);
        SpinnerDateModel esmodel = new SpinnerDateModel(date, null, null, Calendar.AM_PM);
        jspSH.setModel(shmodel);
        jspSM.setModel(smmodel);
        jspSS.setModel(ssmodel);
        jspEH.setModel(ehmodel);
        jspEM.setModel(emmodel);
        jspES.setModel(esmodel);
        JSpinner.DateEditor sh = new JSpinner.DateEditor(jspSH, "HH"); //NOI18N
        JSpinner.DateEditor sm = new JSpinner.DateEditor(jspSM, "mm"); //NOI18N
        JSpinner.DateEditor ss = new JSpinner.DateEditor(jspSS, "ss"); //NOI18N
        JSpinner.DateEditor eh = new JSpinner.DateEditor(jspEH, "HH"); //NOI18N
        JSpinner.DateEditor em = new JSpinner.DateEditor(jspEM, "mm"); //NOI18N
        JSpinner.DateEditor es = new JSpinner.DateEditor(jspES, "ss"); //NOI18N
        jspSH.setEditor(sh);
        jspSM.setEditor(sm);
        jspSS.setEditor(ss);
        jspEH.setEditor(eh);
        jspEM.setEditor(em);
        jspES.setEditor(es);


        String[] col1 = new String [] { java.util.ResourceBundle.getBundle("com/sdk/connector/db/Bundle").getString("LEFT TIMESTAMP"), java.util.ResourceBundle.getBundle("com/sdk/connector/db/Bundle").getString("LEFT RR")};
        leftModel = new DefaultTableModel(col1,0);
        jtLeftResponse.setModel(leftModel);

        String[] col2 = new String [] { java.util.ResourceBundle.getBundle("com/sdk/connector/db/Bundle").getString("RIGHT TIMESTAMP"), java.util.ResourceBundle.getBundle("com/sdk/connector/db/Bundle").getString("RIGHT RR")};
        rightModel = new DefaultTableModel(col2,0);
        jtRightResponse.setModel(rightModel);

        jXTaskPane1.addPropertyChangeListener(JXCollapsiblePane.ANIMATION_STATE_KEY, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                if(e.getNewValue().equals("expanded") ){
                    setVisibleResult(false);
                }
                else if(e.getNewValue().equals("collapsed") ){
                    setVisibleResult(true);
                }
            }
        });

        setVisibleResult(false);
        jScrollPane1.getViewport().setBackground(new Color(0,0,255, 24));
        jScrollPane2.getViewport().setBackground(new Color(255,0,0, 24));
    }


     private Color color1 = new Color(250,255,255);
    private Color color2 = color1.brighter();
    @Override
    protected void paintComponent( Graphics g ) {
        if ( !isOpaque( ) ) {
            super.paintComponent( g );
            return;
        }
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth( );
        int h = getHeight( );
        GradientPaint gp = new GradientPaint(
            0, 0, color1,
            0, h, color2 );

        g2d.setPaint( gp );
        g2d.fillRect( 0, 0, w, h );
        Dimension d = getSize();

        g2d.drawImage(new javax.swing.ImageIcon(getClass().getResource("/com/sdk/connector/resources/background.png")).getImage(), 0, 0,d.width,d.height, null);
        setOpaque( false );
        super.paintComponent( g );
        setOpaque( true );
    }

    public void updateCurrentTime(){

        Date date = new Date();

        SpinnerDateModel ehmodel = new SpinnerDateModel(date, null, null, Calendar.AM_PM);
        SpinnerDateModel emmodel = new SpinnerDateModel(date, null, null, Calendar.AM_PM);
        SpinnerDateModel esmodel = new SpinnerDateModel(date, null, null, Calendar.AM_PM);

        jspEH.setModel(ehmodel);
        jspEM.setModel(emmodel);
        jspES.setModel(esmodel);

        JSpinner.DateEditor eh = new JSpinner.DateEditor(jspEH, "HH");
        JSpinner.DateEditor em = new JSpinner.DateEditor(jspEM, "mm");
        JSpinner.DateEditor es = new JSpinner.DateEditor(jspES, "ss");

        jspEH.setEditor(eh);
        jspEM.setEditor(em);
        jspES.setEditor(es);
    }
    public static void update(){
        recordingSamples.update();
    }

    private void setVisibleResult(boolean value){
        jScrollPane1.setVisible(value);
        jbExportLeftResult.setVisible(value);
        jScrollPane2.setVisible(value);
        jbExportRightResult.setVisible(value);
    }

     public JFormattedTextField getTextField(JSpinner spinner) {
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            return ((JSpinner.DefaultEditor)editor).getTextField();
        } else {
            System.err.println("Unexpected editor type: "
                               + spinner.getEditor().getClass()
                               + " isn't a descendant of DefaultEditor");
            return null;
        }
    }
      public void addListEntries(List<ListEntry> list) {

        while(leftModel.getRowCount()>0){
            leftModel.removeRow(0);
        }
        while(rightModel.getRowCount()>0){
            rightModel.removeRow(0);
        }

        for(ListEntry entry: list) {
            int rRR = Integer.parseInt(entry.getRightRow()[1].toString());
            if(rRR != 0){
                rightModel.addRow(entry.getRightRow());
            }

        }

        for(ListEntry entry: list) {
            int lRR = Integer.parseInt(entry.getLeftRow()[1].toString());
            if(lRR != 0){
                leftModel.addRow(entry.getLeftRow());
            }
        }
    }

    private File file = null;
    private TableExporter exporter = new TableExporter();
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jXTaskPaneContainer1 = new org.jdesktop.swingx.JXTaskPaneContainer();
        jXTaskPane1 = new org.jdesktop.swingx.JXTaskPane();
        jPanel1 = new javax.swing.JPanel();
        jbSubmit = new javax.swing.JButton();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        jspEH = new javax.swing.JSpinner();
        jspSS = new javax.swing.JSpinner();
        jspSH = new javax.swing.JSpinner();
        jspSM = new javax.swing.JSpinner();
        jspEM = new javax.swing.JSpinner();
        jspES = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtLeftResponse = new javax.swing.JTable();
        jbExportLeftResult = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtRightResponse = new javax.swing.JTable();
        jbExportRightResult = new javax.swing.JButton();

        setOpaque(true);

        jXTaskPaneContainer1.setBackground(new Color(255,255,255,64));
        jXTaskPaneContainer1.setOpaque(false);

        jXTaskPane1.setBackground(new java.awt.Color(255, 255, 255));
        jXTaskPane1.setForeground(java.awt.Color.darkGray);
        jXTaskPane1.setScrollOnExpand(true);
        jXTaskPane1.setTitle(org.openide.util.NbBundle.getMessage(DatabaseTopComponent.class, "DatabaseTopComponent.jXTaskPane1.title")); // NOI18N
        jXTaskPane1.getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 204)));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jbSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sdk/connector/resources/find.png"))); // NOI18N
        jbSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSubmitActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jbSubmit, gridBagConstraints);

        jXDatePicker1.setDate(new Date());
        jXDatePicker1.setFormats(new String[] { "yyyy-MM-dd" });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jXDatePicker1, gridBagConstraints);

        jXDatePicker2.setDate(new Date());
        jXDatePicker2.setFormats(new String[] { "yyyy-MM-dd" });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jXDatePicker2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jspEH, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jspSS, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jspSH, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jspSM, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jspEM, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jspES, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(DatabaseTopComponent.class, "DatabaseTopComponent.jLabel1.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 12));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(DatabaseTopComponent.class, "DatabaseTopComponent.jLabel2.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jSeparator1, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 12));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(DatabaseTopComponent.class, "DatabaseTopComponent.jLabel3.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 12));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(DatabaseTopComponent.class, "DatabaseTopComponent.jLabel4.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 12));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(DatabaseTopComponent.class, "DatabaseTopComponent.jLabel5.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jLabel5, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 12));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(DatabaseTopComponent.class, "DatabaseTopComponent.jLabel6.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jLabel6, gridBagConstraints);
        jPanel1.add(jSeparator2, new java.awt.GridBagConstraints());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 40);
        jPanel1.add(jLabel7, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("sansserif", 1, 12));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(DatabaseTopComponent.class, "DatabaseTopComponent.jLabel8.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(jLabel8, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("sansserif", 1, 12));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(DatabaseTopComponent.class, "DatabaseTopComponent.jLabel9.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(jLabel9, gridBagConstraints);

        jXTaskPane1.getContentPane().add(jPanel1);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.BorderLayout());

        jtLeftResponse.setAutoCreateRowSorter(true);
        jtLeftResponse.setForeground(new Color(0,0,255, 99));
        jtLeftResponse.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Timestamp", "Left RR"
            }
        ));
        jtLeftResponse.setDoubleBuffered(true);
        jtLeftResponse.setOpaque(false);
        jScrollPane1.setViewportView(jtLeftResponse);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.PAGE_START);

        jbExportLeftResult.setFont(new java.awt.Font("sansserif", 0, 10));
        org.openide.awt.Mnemonics.setLocalizedText(jbExportLeftResult, java.util.ResourceBundle.getBundle("com/sdk/connector/db/Bundle").getString("EXPORT RESULT..."));
        jbExportLeftResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExportLeftResultActionPerformed(evt);
            }
        });
        jPanel3.add(jbExportLeftResult, java.awt.BorderLayout.LINE_END);

        jPanel2.add(jPanel3);

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.BorderLayout());

        jtRightResponse.setAutoCreateRowSorter(true);
        jtRightResponse.setForeground(new Color(255,0,0, 99));
        jtRightResponse.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Timestamp", "Right RR"
            }
        ));
        jtRightResponse.setDoubleBuffered(true);
        jtRightResponse.setOpaque(false);
        jScrollPane2.setViewportView(jtRightResponse);

        jPanel4.add(jScrollPane2, java.awt.BorderLayout.PAGE_START);

        jbExportRightResult.setFont(new java.awt.Font("sansserif", 0, 10));
        org.openide.awt.Mnemonics.setLocalizedText(jbExportRightResult, java.util.ResourceBundle.getBundle("com/sdk/connector/db/Bundle").getString("EXPORT RESULT..."));
        jbExportRightResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExportRightResultActionPerformed(evt);
            }
        });
        jPanel4.add(jbExportRightResult, java.awt.BorderLayout.LINE_END);

        jPanel2.add(jPanel4);

        javax.swing.GroupLayout jXTaskPaneContainer1Layout = new javax.swing.GroupLayout(jXTaskPaneContainer1);
        jXTaskPaneContainer1.setLayout(jXTaskPaneContainer1Layout);
        jXTaskPaneContainer1Layout.setHorizontalGroup(
            jXTaskPaneContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXTaskPaneContainer1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jXTaskPaneContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
                    .addComponent(jXTaskPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)))
        );
        jXTaskPaneContainer1Layout.setVerticalGroup(
            jXTaskPaneContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXTaskPaneContainer1Layout.createSequentialGroup()
                .addComponent(jXTaskPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXTaskPaneContainer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXTaskPaneContainer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSubmitActionPerformed
        try {
            String time1 = getTextField(jspSH).getText();
            time1 += ":" + getTextField(jspSM).getText();
            time1 += ":" + getTextField(jspSS).getText();
            String date1 = sdf.format(jXDatePicker1.getDate());
            date1 = date1.replaceAll("00\\:00\\:00", time1);
            String time2 = getTextField(jspEH).getText();
            time2 += ":" + getTextField(jspEM).getText();
            time2 += ":" + getTextField(jspES).getText();
            String date2 = sdf.format(jXDatePicker2.getDate());
            date2 = date2.replaceAll("00\\:00\\:00", time2);
            Date date11 = sdf.parse(date1);
            Timestamp timestamp1 = new Timestamp(date11.getTime());
            Date date22 = sdf.parse(date2);
            Timestamp timestamp2 = new Timestamp(date22.getTime());
            addListEntries(recordingSamples.getDateDiffListEntries(timestamp1, timestamp2));

            jXTaskPane1.setCollapsed(true);
        } catch (ParseException ex) {
            debug(ex);
        }
}//GEN-LAST:event_jbSubmitActionPerformed

    private void jbExportLeftResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExportLeftResultActionPerformed
        jXTaskPaneContainer1.setAlpha((float) 0.3);
        JFileChooser fileSave = new JFileChooser(System.getProperty("user.dir"));
        fileSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileSave.setSelectedFile(new File("*.txt"));
        int ret = fileSave.showDialog(this, java.util.ResourceBundle.getBundle("com/sdk/connector/db/Bundle").getString("SAVE"));
        if (ret == JFileChooser.APPROVE_OPTION) {
            file = fileSave.getSelectedFile();
            try {
                exporter.exportTable(jtLeftResponse.getModel(), file);
            } catch ( Exception ex) {
                debug(ex);
            }
        }
        jXTaskPaneContainer1.setAlpha((float) 1);
}//GEN-LAST:event_jbExportLeftResultActionPerformed

    private void jbExportRightResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExportRightResultActionPerformed
        jXTaskPaneContainer1.setAlpha((float) 0.3);
        JFileChooser fileSave = new JFileChooser(System.getProperty("user.dir"));
        fileSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileSave.setSelectedFile(new File("*.txt"));
        int ret = fileSave.showDialog(this, java.util.ResourceBundle.getBundle("com/sdk/connector/db/Bundle").getString("SAVE"));
        if (ret == JFileChooser.APPROVE_OPTION) {
            file = fileSave.getSelectedFile();
            try {
                exporter.exportTable(jtRightResponse.getModel(), file);
            } catch (  Exception ex) {
                debug(ex);
            }
        }
        jXTaskPaneContainer1.setAlpha((float) 1);
}//GEN-LAST:event_jbExportRightResultActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane1;
    private org.jdesktop.swingx.JXTaskPaneContainer jXTaskPaneContainer1;
    private javax.swing.JButton jbExportLeftResult;
    private javax.swing.JButton jbExportRightResult;
    private javax.swing.JButton jbSubmit;
    private javax.swing.JSpinner jspEH;
    private javax.swing.JSpinner jspEM;
    private javax.swing.JSpinner jspES;
    private javax.swing.JSpinner jspSH;
    private javax.swing.JSpinner jspSM;
    private javax.swing.JSpinner jspSS;
    private javax.swing.JTable jtLeftResponse;
    private javax.swing.JTable jtRightResponse;
    // End of variables declaration//GEN-END:variables
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized DatabaseTopComponent getDefault() {
        if (instance == null) {
            instance = new DatabaseTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the DatabaseTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized DatabaseTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(DatabaseTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof DatabaseTopComponent) {
            return (DatabaseTopComponent) win;
        }
        Logger.getLogger(DatabaseTopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID
                + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentActivated(){
        updateCurrentTime();
    }
    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    Object readProperties(java.util.Properties p) {
        if (instance == null) {
            instance = this;
        }
        instance.readPropertiesImpl(p);
        return instance;
    }

    private void readPropertiesImpl(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }
}
