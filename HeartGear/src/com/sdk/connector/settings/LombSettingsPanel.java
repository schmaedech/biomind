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
public class LombSettingsPanel extends javax.swing.JPanel {

    private static LombSettingsEntity lombSettingsEntity = new LombSettingsEntity();

    /**
     * @return the lombSettingsEntity
     */
    public static LombSettingsEntity getLombSettingsEntity() {
        return lombSettingsEntity;
    }
    /** Creates new form FFTSettings */
    public LombSettingsPanel() {
        initComponents();
        buttonGroup1.add(flagPower);
        buttonGroup1.add(flagSquaredPower);
        setEditable(false);
        updateFields();
      jlVLF.setBackground(new Color(0, 100, 255, 128));
        jlLF.setBackground(new Color(255, 255, 0, 128));
        jlHF.setBackground(new Color(255, 0, 255, 128));
       
    }

    private void updateFields(){


        if(flagPower.isSelected()){
            getLombSettingsEntity().setPlotPower(flagPower.isSelected());
        }else{
            getLombSettingsEntity().setPlotPower(flagPower.isSelected());
        }

        getLombSettingsEntity().setDetrend(flagDetrend.isSelected());
        getLombSettingsEntity().setZeromean(flagZeromean.isSelected());

        if(flagSamplingAll.isSelected()){
            getLombSettingsEntity().setSamples(0);
        }else{
            getLombSettingsEntity().setSamples(Integer.parseInt(jtfSampling.getText()));
        }


        getLombSettingsEntity().setSmooth(jcbSmooth.isSelected());


        getLombSettingsEntity().setLFmin(Double.parseDouble(jtfLFmin.getText()));
        getLombSettingsEntity().setLFmax(Double.parseDouble(jtfLFmax.getText()));
        getLombSettingsEntity().setHFmin(Double.parseDouble(jtfHFmin.getText()));
        getLombSettingsEntity().setHFmax(Double.parseDouble(jtfHFmax.getText()));
        getLombSettingsEntity().setVLFmin(Double.parseDouble(jtfVLFmin.getText()));
        getLombSettingsEntity().setVLFmax(Double.parseDouble(jtfVLFmax.getText()));

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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        flagZeromean = new javax.swing.JCheckBox();
        flagDetrend = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        flagSamplingAll = new javax.swing.JCheckBox();
        jtfSampling = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        flagPower = new javax.swing.JRadioButton();
        flagSquaredPower = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jcbSmooth = new javax.swing.JCheckBox();
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
        setPreferredSize(new java.awt.Dimension(540, 400));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pre-processing", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 10))); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(540, 80));

        jPanel3.setOpaque(false);

        flagZeromean.setBackground(new java.awt.Color(255, 255, 255));
        flagZeromean.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        flagZeromean.setSelected(true);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/sdk/connector/settings/Bundle"); // NOI18N
        flagZeromean.setText(bundle.getString("ZERO-MEAN")); // NOI18N
        jPanel3.add(flagZeromean);

        flagDetrend.setBackground(new java.awt.Color(255, 255, 255));
        flagDetrend.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        flagDetrend.setSelected(true);
        flagDetrend.setText(bundle.getString("LINEAR DETREND")); // NOI18N
        jPanel3.add(flagDetrend);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Params", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 10))); // NOI18N
        jPanel7.setOpaque(false);
        jPanel7.setPreferredSize(new java.awt.Dimension(540, 80));
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        flagSamplingAll.setBackground(new java.awt.Color(255, 255, 255));
        flagSamplingAll.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        flagSamplingAll.setText(bundle.getString("ALL SAMPLES:")); // NOI18N
        flagSamplingAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                flagSamplingAllchangeStateSamplingAll(evt);
            }
        });
        jPanel7.add(flagSamplingAll);

        jtfSampling.setColumns(5);
        jtfSampling.setText("128");
        jPanel7.add(jtfSampling);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Output", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 10))); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(540, 140));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel8.setOpaque(false);
        jPanel8.setLayout(new java.awt.BorderLayout());

        flagPower.setBackground(new java.awt.Color(255, 255, 255));
        flagPower.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        flagPower.setSelected(true);
        flagPower.setText(bundle.getString("POWER")); // NOI18N
        jPanel8.add(flagPower, java.awt.BorderLayout.CENTER);

        flagSquaredPower.setBackground(new java.awt.Color(255, 255, 255));
        flagSquaredPower.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        flagSquaredPower.setText(bundle.getString("SQUARED MAG.")); // NOI18N
        jPanel8.add(flagSquaredPower, java.awt.BorderLayout.PAGE_START);

        jPanel2.add(jPanel8);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setPreferredSize(new java.awt.Dimension(5, 50));
        jPanel2.add(jSeparator1);

        jPanel6.setOpaque(false);
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jcbSmooth.setBackground(new java.awt.Color(255, 255, 255));
        jcbSmooth.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jcbSmooth.setText(bundle.getString("SMOOTH")); // NOI18N
        jPanel6.add(jcbSmooth);

        jPanel2.add(jPanel6);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setPreferredSize(new java.awt.Dimension(5, 50));
        jPanel2.add(jSeparator2);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255)));
        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jlVLF.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jlVLF.setText("VLF(Hz):");
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditActionPerformed
        setEditable(true);
        jbUpdate.setEnabled(true);
        jbEdit.setEnabled(false);
        this.repaint();
        this.revalidate();
        
}//GEN-LAST:event_jbEditActionPerformed

    private void jbUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbUpdateActionPerformed
        updateFields();
        jbUpdate.setEnabled(false);

        jbEdit.setEnabled(true);
        setEditable(false);
}//GEN-LAST:event_jbUpdateActionPerformed

    private void flagSamplingAllchangeStateSamplingAll(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_flagSamplingAllchangeStateSamplingAll
        if(flagSamplingAll.isSelected()){
            jtfSampling.setEnabled(false);
            flagSamplingAll.setSelected(true);
            JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle("com/sdk/connector/settings/Bundle").getString("WARNING SAMPLING ALL"), java.util.ResourceBundle.getBundle("com/sdk/connector/settings/Bundle").getString("WARNING!") , JOptionPane.WARNING_MESSAGE);
        }else{
            flagSamplingAll.setSelected(false);
            jtfSampling.setEnabled(true);
        }
}//GEN-LAST:event_flagSamplingAllchangeStateSamplingAll

    private void setEditable(boolean flag){

        flagDetrend.setEnabled(flag);

        flagPower.setEnabled(flag);

        flagSquaredPower.setEnabled(flag);

        flagZeromean.setEnabled(flag);

        jtfSampling.setEnabled(flag);
        jtfHFmax.setEnabled(flag);
        jtfHFmin.setEnabled(flag);

        jtfLFmax.setEnabled(flag);
        jtfLFmin.setEnabled(flag);

        jcbSmooth.setEnabled(flag);
        jtfVLFmax.setEnabled(flag);
        jtfVLFmin.setEnabled(flag);

        flagZeromean.setEnabled(flag); // NOI18N
        flagDetrend.setEnabled(flag); // NOI18N
        jlVLF.setEnabled(flag);
        jlLF.setEnabled(flag);
        jlHF.setEnabled(flag);

        flagPower.setEnabled(flag); // NOI18N
        flagSquaredPower.setEnabled(flag);
         flagSamplingAll.setEnabled(flag);


    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox flagDetrend;
    private javax.swing.JRadioButton flagPower;
    private static javax.swing.JCheckBox flagSamplingAll;
    private javax.swing.JRadioButton flagSquaredPower;
    private javax.swing.JCheckBox flagZeromean;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton jbEdit;
    private javax.swing.JButton jbUpdate;
    private javax.swing.JCheckBox jcbSmooth;
    private javax.swing.JLabel jlHF;
    private javax.swing.JLabel jlLF;
    private javax.swing.JLabel jlVLF;
    private javax.swing.JTextField jtfHFmax;
    private javax.swing.JTextField jtfHFmin;
    private javax.swing.JTextField jtfLFmax;
    private javax.swing.JTextField jtfLFmin;
    private static javax.swing.JTextField jtfSampling;
    private javax.swing.JTextField jtfVLFmax;
    private javax.swing.JTextField jtfVLFmin;
    // End of variables declaration//GEN-END:variables

}
