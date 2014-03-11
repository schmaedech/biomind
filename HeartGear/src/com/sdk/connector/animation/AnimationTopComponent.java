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

package com.sdk.connector.animation;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.openide.util.ImageUtilities;
import org.netbeans.api.settings.ConvertAsProperties;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//com.sdk.connector.animation//Animation//EN",
autostore = false)
public final class AnimationTopComponent extends TopComponent {

    private static AnimationTopComponent instance;
    /** path to the icon used by the component and its open action */
    static final String ICON_PATH = "com/sdk/connector/animation/flash.png";
    private static final String PREFERRED_ID = "AnimationTopComponent";

    public AnimationTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(AnimationTopComponent.class, "CTL_AnimationTopComponent"));
        setToolTipText(NbBundle.getMessage(AnimationTopComponent.class, "HINT_AnimationTopComponent"));
        setIcon(ImageUtilities.loadImage(ICON_PATH, true));

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
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jbFind = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jbFlower = new javax.swing.JButton();

        setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(AnimationTopComponent.class, "AnimationTopComponent.jLabel1.text")); // NOI18N
        jPanel1.add(jLabel1);

        jTextField1.setColumns(50);
        jTextField1.setText(org.openide.util.NbBundle.getMessage(AnimationTopComponent.class, "AnimationTopComponent.jTextField1.text")); // NOI18N
        jPanel1.add(jTextField1);

        jbFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sdk/connector/resources/find.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jbFind, org.openide.util.NbBundle.getMessage(AnimationTopComponent.class, "AnimationTopComponent.jbFind.text")); // NOI18N
        jbFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbFindActionPerformed(evt);
            }
        });
        jPanel1.add(jbFind);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jbFlower.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sdk/connector/resources/flower_icon.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jbFlower, org.openide.util.NbBundle.getMessage(AnimationTopComponent.class, "AnimationTopComponent.jbFlower.text")); // NOI18N
        jbFlower.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbFlowerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbFlower)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbFlower)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbFindActionPerformed
        try {
            if( !java.awt.Desktop.isDesktopSupported() ) {

                System.err.println( "Desktop is not supported (fatal)" );

            }
            URI uri = null;
            try {
                uri = new URI(jTextField1.getText());
                desktop.browse(uri);
            }
            catch(IOException ioe) {
            }
        } catch (Exception ex){
            System.out.print(ex.getMessage());
        }
    }//GEN-LAST:event_jbFindActionPerformed

    private void jbFlowerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbFlowerActionPerformed
        try{
            if(!java.awt.Desktop.isDesktopSupported()) {
                System.err.println( "Desktop is not supported (fatal)" );
            }
            URI uri = null;
            try {

                URL url = getClass().getResource("/com/sdk/connector/resources/background.png");
                String path = url.toString();
                uri = URI.create(path);


                JOptionPane.showMessageDialog(null, uri);
                 
                desktop.browse(uri);
            }
            catch(IOException ioe) {
                System.out.println(ioe.getMessage());
            }
             
        } catch (Exception ex){
            System.out.print(ex.getMessage());
            return;//nothing much to do
        }
    }//GEN-LAST:event_jbFlowerActionPerformed

     
    private Desktop desktop = java.awt.Desktop.getDesktop(); 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton jbFind;
    private javax.swing.JButton jbFlower;
    // End of variables declaration//GEN-END:variables
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized AnimationTopComponent getDefault() {
        if (instance == null) {
            instance = new AnimationTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the AnimationTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized AnimationTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(AnimationTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof AnimationTopComponent) {
            return (AnimationTopComponent) win;
        }
        Logger.getLogger(AnimationTopComponent.class.getName()).warning(
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