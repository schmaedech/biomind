/*
 * @copyright Copyright (c) 2010 Laboratório de Educação Cerebral. (http://www.educacaocerebral.com.br)
 *
 * This file is part of SoftVFC.
 *
 * SoftVFC is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SoftVFC is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SoftVFC.  If not, see <http://www.gnu.org/licenses/>.
 *
 * *********************
 *
 * Este arquivo é parte do programa SoftVFC.
 *
 * SoftVFC é um software livre; você pode redistribui-lo e/ou
 * modifica-lo dentro dos termos da Licença Pública Geral GNU como
 * publicada pela Fundação do Software Livre (FSF); na versão 3 da
 * Licença.
 *
 * Este programa é distribuido na esperança que possa ser util,
 * mas SEM NENHUMA GARANTIA; sem uma garantia implicita de ADEQUAÇÂO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a
 * Licença Pública Geral GNU para maiores detalhes.
 *
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU
 * junto com este programa, se não, acesse no website oficial:
 * http://www.gnu.org/licenses/gpl.html
 *
 */
/**
 *
 * @author Diego Schmaedech Martins (schmaedech@gmail.com)
 * @version 29/07/2010
 */


package com.sdk.connector.settings;

import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class FFTSettingsPanel extends javax.swing.JPanel {

    private static FFTSettingsEntity fftSettingsEntity = new FFTSettingsEntity();

    /**
     * @return the fftSettingsEntity
     */
    public static FFTSettingsEntity getFftSettingsEntity() {
        return fftSettingsEntity;
    }
    /** Creates new form FFTSettings */
    public FFTSettingsPanel() {
        initComponents();
         
        bgOutputPlot.add(flagPower);
        bgOutputPlot.add(flagSquaredPower);

        setEditable(false); 
        updateFields();
        jlVLF.setBackground(new Color(0, 100, 255, 128));
        jlLF.setBackground(new Color(255, 255, 0, 128));
        jlHF.setBackground(new Color(255, 0, 255, 128));
    }

    private void updateFields(){


        if(flagPower.isSelected()){
            getFftSettingsEntity().setPlotPower(flagPower.isSelected());
        }else{
            getFftSettingsEntity().setPlotPower(flagPower.isSelected());
        }


        getFftSettingsEntity().setBicubic(flagBicubic.isSelected());
        getFftSettingsEntity().setBicubicWindow(Integer.parseInt(jtfBicubicWindow.getText()));
        getFftSettingsEntity().setBicubicPrecision(Integer.parseInt(jtfPrecision.getText()));


        getFftSettingsEntity().setDetrend(flagDetrend.isSelected());
        getFftSettingsEntity().setZeromean(flagZeromean.isSelected());

        if(flagWindow.isSelected()){
             
            getFftSettingsEntity().setWindow(jcbWindow.getSelectedItem().toString());
        }else{
            getFftSettingsEntity().setWindow("");
        }
        if(flagSamplingAll.isSelected()){
            getFftSettingsEntity().setSamples(0);
        }else{
            getFftSettingsEntity().setSamples(Integer.parseInt(jtfSampling.getText()));
        }
        if(flagOverlap.isSelected()){

            getFftSettingsEntity().setK(Integer.parseInt(jtfK.getText()));
            getFftSettingsEntity().setD(Integer.parseInt(jtfD.getText()));
            getFftSettingsEntity().setL(Integer.parseInt(jtfL.getText()));
        }else{
            getFftSettingsEntity().setK(0);

        }
        getFftSettingsEntity().setFreq(Integer.parseInt(jtfFrequency.getText()));
        getFftSettingsEntity().setSmooth(Integer.parseInt(jtfSmooth.getText()));
        getFftSettingsEntity().setDecimation(Integer.parseInt(jtfDecimation.getText()));

        getFftSettingsEntity().setLFmin(Double.parseDouble(jtfLFmin.getText()));
        getFftSettingsEntity().setLFmax(Double.parseDouble(jtfLFmax.getText()));
        getFftSettingsEntity().setHFmin(Double.parseDouble(jtfHFmin.getText()));
        getFftSettingsEntity().setHFmax(Double.parseDouble(jtfHFmax.getText()));
        getFftSettingsEntity().setVLFmin(Double.parseDouble(jtfVLFmin.getText()));
        getFftSettingsEntity().setVLFmax(Double.parseDouble(jtfVLFmax.getText()));
        
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        bgOutputPlot = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        flagBicubic = new javax.swing.JCheckBox();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jtfBicubicWindow = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfPrecision = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        flagOverlap = new javax.swing.JCheckBox();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jtfK = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtfD = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtfL = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        flagWindow = new javax.swing.JCheckBox();
        jcbWindow = new javax.swing.JComboBox();
        jSeparator6 = new javax.swing.JSeparator();
        flagZeromean = new javax.swing.JCheckBox();
        flagDetrend = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        flagSamplingAll = new javax.swing.JCheckBox();
        jtfSampling = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jtfFrequency = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jtfSmooth = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jtfDecimation = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        flagPower = new javax.swing.JRadioButton();
        flagSquaredPower = new javax.swing.JRadioButton();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jlVLF = new javax.swing.JLabel();
        jlLF = new javax.swing.JLabel();
        jlHF = new javax.swing.JLabel();
        jtfVLFmin = new javax.swing.JTextField();
        jtfLFmin = new javax.swing.JTextField();
        jtfHFmin = new javax.swing.JTextField();
        jtfVLFmax = new javax.swing.JTextField();
        jtfLFmax = new javax.swing.JTextField();
        jtfHFmax = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jbEdit = new javax.swing.JButton();
        jbUpdate = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(570, 400));
        setPreferredSize(new java.awt.Dimension(570, 400));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pre-processing", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 10))); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(540, 140));
        jPanel1.setLayout(new java.awt.GridLayout(3, 0));

        jPanel6.setOpaque(false);
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        flagBicubic.setBackground(new java.awt.Color(255, 255, 255));
        flagBicubic.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/sdk/connector/settings/Bundle"); // NOI18N
        flagBicubic.setText(bundle.getString("BICUBIC INTERPOLATION")); // NOI18N
        flagBicubic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeStateBicubic(evt);
            }
        });
        jPanel6.add(flagBicubic);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator3.setMinimumSize(new java.awt.Dimension(10, 2));
        jSeparator3.setPreferredSize(new java.awt.Dimension(10, 1));
        jPanel6.add(jSeparator3);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel1.setText(bundle.getString("WINDOW:")); // NOI18N
        jPanel6.add(jLabel1);

        jtfBicubicWindow.setColumns(5);
        jtfBicubicWindow.setText("128");
        jtfBicubicWindow.setEnabled(false);
        jPanel6.add(jtfBicubicWindow);

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel2.setText(bundle.getString("PRECISION:")); // NOI18N
        jPanel6.add(jLabel2);

        jtfPrecision.setColumns(5);
        jtfPrecision.setText("4");
        jtfPrecision.setEnabled(false);
        jPanel6.add(jtfPrecision);

        jPanel1.add(jPanel6);

        jPanel7.setOpaque(false);
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        flagOverlap.setBackground(new java.awt.Color(255, 255, 255));
        flagOverlap.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        flagOverlap.setText(bundle.getString("WINDOW OVELAP")); // NOI18N
        flagOverlap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeStateWindowOverlap(evt);
            }
        });
        jPanel7.add(flagOverlap);

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator4.setMinimumSize(new java.awt.Dimension(3, 2));
        jSeparator4.setPreferredSize(new java.awt.Dimension(10, 2));
        jPanel7.add(jSeparator4);

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel4.setText("K:");
        jPanel7.add(jLabel4);

        jtfK.setColumns(5);
        jtfK.setText("0");
        jtfK.setEnabled(false);
        jPanel7.add(jtfK);

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel5.setText("D:");
        jPanel7.add(jLabel5);

        jtfD.setColumns(5);
        jtfD.setText("32");
        jtfD.setEnabled(false);
        jPanel7.add(jtfD);

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel6.setText("L:");
        jPanel7.add(jLabel6);

        jtfL.setColumns(5);
        jtfL.setText("64");
        jtfL.setEnabled(false);
        jPanel7.add(jtfL);

        jPanel1.add(jPanel7);

        jPanel8.setOpaque(false);
        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        flagWindow.setBackground(new java.awt.Color(255, 255, 255));
        flagWindow.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        flagWindow.setSelected(true);
        flagWindow.setText(bundle.getString("WINDOW")); // NOI18N
        flagWindow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeStateWindow(evt);
            }
        });
        jPanel8.add(flagWindow);

        jcbWindow.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Welch", "Square", "Dirichlet", "Bartlett", "Hanning", "Hamming", "Blackman", "Blackman Harris", "Parzen" }));
        jPanel8.add(jcbWindow);

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator6.setPreferredSize(new java.awt.Dimension(5, 20));
        jPanel8.add(jSeparator6);

        flagZeromean.setBackground(new java.awt.Color(255, 255, 255));
        flagZeromean.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        flagZeromean.setSelected(true);
        flagZeromean.setText(bundle.getString("ZERO-MEAN")); // NOI18N
        jPanel8.add(flagZeromean);

        flagDetrend.setBackground(new java.awt.Color(255, 255, 255));
        flagDetrend.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        flagDetrend.setSelected(true);
        flagDetrend.setText(bundle.getString("LINEAR DETREND")); // NOI18N
        jPanel8.add(flagDetrend);

        jPanel1.add(jPanel8);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Params", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 10))); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(540, 80));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        flagSamplingAll.setBackground(new java.awt.Color(255, 255, 255));
        flagSamplingAll.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        flagSamplingAll.setText(bundle.getString("ALL SAMPLES:")); // NOI18N
        flagSamplingAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeStateSamplingAll(evt);
            }
        });
        jPanel3.add(flagSamplingAll);

        jtfSampling.setColumns(5);
        jtfSampling.setText("128");
        jPanel3.add(jtfSampling);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setPreferredSize(new java.awt.Dimension(5, 20));
        jPanel3.add(jSeparator1);

        jLabel10.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel10.setText(bundle.getString("FREQUENCY(HZ):")); // NOI18N
        jPanel3.add(jLabel10);

        jtfFrequency.setColumns(3);
        jtfFrequency.setText("1");
        jPanel3.add(jtfFrequency);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Output", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 10))); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(540, 140));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel10.setOpaque(false);
        jPanel10.setPreferredSize(new java.awt.Dimension(190, 90));
        jPanel10.setLayout(new java.awt.GridBagLayout());

        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel7.setText(bundle.getString("SMOOTH:")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 25;
        jPanel10.add(jLabel7, gridBagConstraints);

        jtfSmooth.setColumns(5);
        jtfSmooth.setText("1");
        jtfSmooth.setMinimumSize(new java.awt.Dimension(50, 27));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 17;
        jPanel10.add(jtfSmooth, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel8.setText(bundle.getString("DECIMATION:")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel10.add(jLabel8, gridBagConstraints);

        jtfDecimation.setColumns(5);
        jtfDecimation.setText("1");
        jtfDecimation.setMinimumSize(new java.awt.Dimension(50, 27));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 17;
        jPanel10.add(jtfDecimation, gridBagConstraints);

        jPanel2.add(jPanel10);

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator5.setPreferredSize(new java.awt.Dimension(5, 50));
        jPanel2.add(jSeparator5);

        jPanel5.setOpaque(false);
        jPanel5.setLayout(new java.awt.BorderLayout());

        flagPower.setBackground(new java.awt.Color(255, 255, 255));
        flagPower.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        flagPower.setSelected(true);
        flagPower.setText(bundle.getString("POWER")); // NOI18N
        jPanel5.add(flagPower, java.awt.BorderLayout.CENTER);

        flagSquaredPower.setBackground(new java.awt.Color(255, 255, 255));
        flagSquaredPower.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        flagSquaredPower.setText(bundle.getString("SQUARED MAG.")); // NOI18N
        jPanel5.add(flagSquaredPower, java.awt.BorderLayout.PAGE_START);

        jPanel2.add(jPanel5);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setPreferredSize(new java.awt.Dimension(5, 50));
        jPanel2.add(jSeparator2);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255)));
        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jlVLF.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jlVLF.setText("VLF (Hz):");
        jlVLF.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jlVLF, gridBagConstraints);

        jlLF.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jlLF.setText("LF (Hz):");
        jlLF.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jlLF, gridBagConstraints);

        jlHF.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jlHF.setText("HF (Hz):");
        jlHF.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jlHF, gridBagConstraints);

        jtfVLFmin.setColumns(5);
        jtfVLFmin.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jtfVLFmin, gridBagConstraints);

        jtfLFmin.setColumns(5);
        jtfLFmin.setText("0.04");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jtfLFmin, gridBagConstraints);

        jtfHFmin.setColumns(5);
        jtfHFmin.setText("0.15");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jtfHFmin, gridBagConstraints);

        jtfVLFmax.setColumns(5);
        jtfVLFmax.setText("0.04");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jtfVLFmax, gridBagConstraints);

        jtfLFmax.setColumns(5);
        jtfLFmax.setText("0.15");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jtfLFmax, gridBagConstraints);

        jtfHFmax.setColumns(5);
        jtfHFmax.setText("0.4");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jtfHFmax, gridBagConstraints);

        jLabel14.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel4.add(jLabel14, gridBagConstraints);

        jLabel15.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel4.add(jLabel15, gridBagConstraints);

        jLabel16.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel4.add(jLabel16, gridBagConstraints);

        jPanel2.add(jPanel4);

        jPanel11.setOpaque(false);
        jPanel11.setPreferredSize(new java.awt.Dimension(540, 37));
        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jbEdit.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jbEdit.setText(bundle.getString("EDIT")); // NOI18N
        jbEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditActionPerformed(evt);
            }
        });
        jPanel11.add(jbEdit);

        jbUpdate.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jbUpdate.setText(bundle.getString("UPDATE")); // NOI18N
        jbUpdate.setEnabled(false);
        jbUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbUpdateActionPerformed(evt);
            }
        });
        jPanel11.add(jbUpdate);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void changeStateSamplingAll(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeStateSamplingAll
        if(flagSamplingAll.isSelected()){
            jtfSampling.setEnabled(false);
            flagSamplingAll.setSelected(true);
            JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle("com/sdk/connector/settings/Bundle").getString("WARNING SAMPLING ALL"), java.util.ResourceBundle.getBundle("com/sdk/connector/settings/Bundle").getString("WARNING!") , JOptionPane.WARNING_MESSAGE);
        }else{
            flagSamplingAll.setSelected(false);
            jtfSampling.setEnabled(true);
        }
    }//GEN-LAST:event_changeStateSamplingAll

    private void changeStateBicubic(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeStateBicubic
        if(flagBicubic.isSelected()){
            jtfBicubicWindow.setEnabled(true);
            jtfPrecision.setEnabled(true);
        }else{
            jtfBicubicWindow.setEnabled(false);
            jtfPrecision.setEnabled(false);
        }
    }//GEN-LAST:event_changeStateBicubic

    private void changeStateWindowOverlap(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeStateWindowOverlap
        if(flagOverlap.isSelected()){
            jtfK.setEnabled(true);
            jtfD.setEnabled(true);
            jtfL.setEnabled(true);
        }else{
            jtfK.setEnabled(false);
            jtfD.setEnabled(false);
            jtfL.setEnabled(false);
        }
    }//GEN-LAST:event_changeStateWindowOverlap

    private void changeStateWindow(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeStateWindow
        if(flagWindow.isSelected()){
             jcbWindow.setEnabled(true);
        }else{
            jcbWindow.setEnabled(false);
        }
    }//GEN-LAST:event_changeStateWindow

    private void jbUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbUpdateActionPerformed
         updateFields();
         jbUpdate.setEnabled(false);

         jbEdit.setEnabled(true);
         setEditable(false);
    }//GEN-LAST:event_jbUpdateActionPerformed

    private void jbEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditActionPerformed
        setEditable(true);
        jbUpdate.setEnabled(true);
        jbEdit.setEnabled(false);
        this.repaint();
        this.revalidate();
    }//GEN-LAST:event_jbEditActionPerformed

    private void setEditable(boolean flag){
        flagBicubic.setEnabled(flag);
        flagDetrend.setEnabled(flag);
        flagOverlap.setEnabled(flag);
        flagPower.setEnabled(flag);
        flagSamplingAll.setEnabled(flag);
        flagSquaredPower.setEnabled(flag);
        flagWindow.setEnabled(flag);
        flagZeromean.setEnabled(flag);
        jcbWindow.setEnabled(flag);
        jtfBicubicWindow.setEnabled(flag);
        jtfPrecision.setEnabled(flag);
        jtfDecimation.setEnabled(flag);
        jtfFrequency.setEnabled(flag);
        jtfHFmax.setEnabled(flag);
        jtfHFmin.setEnabled(flag);
        jtfK.setEnabled(flag);
        jtfLFmax.setEnabled(flag);
        jtfLFmin.setEnabled(flag);
        jtfL.setEnabled(flag);
        jtfD.setEnabled(flag);
        jtfSampling.setEnabled(flag);
        jtfSmooth.setEnabled(flag);
        jtfVLFmax.setEnabled(flag);
        jtfVLFmin.setEnabled(flag);
flagBicubic.setEnabled(flag); // NOI18N
jLabel1.setEnabled(flag);

jLabel2.setEnabled(flag);
flagOverlap.setEnabled(flag); // NOI18N
jLabel4.setEnabled(flag);
jLabel5.setEnabled(flag);
jLabel6.setEnabled(flag);
flagWindow.setEnabled(flag); // NOI18N
flagZeromean.setEnabled(flag); // NOI18N
flagDetrend.setEnabled(flag); // NOI18N
jLabel10.setEnabled(flag);
jlVLF.setEnabled(flag);
jlLF.setEnabled(flag);
jlHF.setEnabled(flag);
flagPower.setEnabled(flag); // NOI18N
flagSquaredPower.setEnabled(flag);
jLabel7.setEnabled(flag);
jLabel8.setEnabled(flag);
 
flagSamplingAll.setEnabled(flag); // NOI18N
jLabel10.setEnabled(flag);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgOutputPlot;
    private static javax.swing.JCheckBox flagBicubic;
    private static javax.swing.JCheckBox flagDetrend;
    private static javax.swing.JCheckBox flagOverlap;
    private static javax.swing.JRadioButton flagPower;
    private static javax.swing.JCheckBox flagSamplingAll;
    private static javax.swing.JRadioButton flagSquaredPower;
    private static javax.swing.JCheckBox flagWindow;
    private static javax.swing.JCheckBox flagZeromean;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JButton jbEdit;
    private javax.swing.JButton jbUpdate;
    private static javax.swing.JComboBox jcbWindow;
    private javax.swing.JLabel jlHF;
    private javax.swing.JLabel jlLF;
    private javax.swing.JLabel jlVLF;
    private static javax.swing.JTextField jtfBicubicWindow;
    private static javax.swing.JTextField jtfD;
    private static javax.swing.JTextField jtfDecimation;
    private static javax.swing.JTextField jtfFrequency;
    private static javax.swing.JTextField jtfHFmax;
    private static javax.swing.JTextField jtfHFmin;
    private static javax.swing.JTextField jtfK;
    private static javax.swing.JTextField jtfL;
    private static javax.swing.JTextField jtfLFmax;
    private static javax.swing.JTextField jtfLFmin;
    private static javax.swing.JTextField jtfPrecision;
    private static javax.swing.JTextField jtfSampling;
    private static javax.swing.JTextField jtfSmooth;
    private static javax.swing.JTextField jtfVLFmax;
    private static javax.swing.JTextField jtfVLFmin;
    // End of variables declaration//GEN-END:variables

}