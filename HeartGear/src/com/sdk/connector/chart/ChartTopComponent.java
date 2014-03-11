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

package com.sdk.connector.chart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.logging.Logger;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.openide.util.ImageUtilities;
import org.netbeans.api.settings.ConvertAsProperties;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//com.sdk.connector.chart//Chart//EN",
autostore = false)
public final class ChartTopComponent extends TopComponent {

    private static TimeDomainRenderer lRRTtimeDomainRenderer,rRRTtimeDomainRenderer ,lBCTtimeDomainRenderer,rBCTtimeDomainRenderer;
    private static FrequencyDomainRenderer lFFTFrequencyDomainRenderer,rFFTFrequencyDomainRenderer,lLombFrequencyDomainRenderer,rLombFrequencyDomainRenderer,lMemseFrequencyDomainRenderer,rMemseFrequencyDomainRenderer;
    private static CoherenceDomainRenderer lCoerenceDomainRenderer,rCoerenceDomainRenderer;

    private static ChartTopComponent instance;
    /** path to the icon used by the component and its open action */
    static final String ICON_PATH = "com/sdk/connector/chart/xfce-office.png";
    private static final String PREFERRED_ID = "ChartTopComponent";

    public ChartTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(ChartTopComponent.class, "CTL_ChartTopComponent"));
        setToolTipText(NbBundle.getMessage(ChartTopComponent.class, "HINT_ChartTopComponent"));
        setIcon(ImageUtilities.loadImage(ICON_PATH, true));

        lRRTtimeDomainRenderer = new TimeDomainRenderer("", jpLeftRR, java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("side.left"), java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("RR"));
        rRRTtimeDomainRenderer = new TimeDomainRenderer("", jpRightRR, java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("side.right"), java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("RR"));
        lBCTtimeDomainRenderer = new TimeDomainRenderer("", jpLeftBC, java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("side.left"), java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("BC"));
        rBCTtimeDomainRenderer = new TimeDomainRenderer("", jpRightBC, java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("side.right"), java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("BC"));

        lFFTFrequencyDomainRenderer = new FrequencyDomainRenderer("", jpLeftFFT, java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("side.left"), "FFT");
        rFFTFrequencyDomainRenderer = new FrequencyDomainRenderer("", jpRightFFT, java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("side.right"), "FFT");

        lLombFrequencyDomainRenderer = new FrequencyDomainRenderer("", jpLeftLomb, java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("side.left"), "Lomb");
        rLombFrequencyDomainRenderer = new FrequencyDomainRenderer("", jpRightLomb, java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("side.right"), "Lomb");

        lMemseFrequencyDomainRenderer = new FrequencyDomainRenderer("", jpLeftMemse, java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("side.left"), "Memse");
        rMemseFrequencyDomainRenderer = new FrequencyDomainRenderer("", jpRightMemse, java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("side.right"), "Memse");

        lCoerenceDomainRenderer = new CoherenceDomainRenderer("", jpLeftCoerence, java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("side.left"));
        rCoerenceDomainRenderer = new CoherenceDomainRenderer("", jpRightCoerence, java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("side.right"));

//        jTabbedPane1.setEnabledAt(2, false);
//        jTabbedPane1.setEnabledAt(3, false);
//        jTabbedPane1.setEnabledAt(4, false);
//        jTabbedPane1.setEnabledAt(5, false);
      
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

    public static void update(){
        lRRTtimeDomainRenderer.update();
        rRRTtimeDomainRenderer.update();
        lBCTtimeDomainRenderer.update();
        rBCTtimeDomainRenderer.update(); 
    }

    public static void updateFrequency(){
        lFFTFrequencyDomainRenderer.update();
        rFFTFrequencyDomainRenderer.update();
        lLombFrequencyDomainRenderer.update();
        rLombFrequencyDomainRenderer.update();
        lMemseFrequencyDomainRenderer.update();
        rMemseFrequencyDomainRenderer.update();
        updateCoerence();
    }

    public static void updateCoerence(){
        lCoerenceDomainRenderer.update();
        rCoerenceDomainRenderer.update();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        layerRR = new javax.swing.JPanel();
        jpLeftRR = new javax.swing.JPanel();
        jpRightRR = new javax.swing.JPanel();
        layerBC = new javax.swing.JPanel();
        jpLeftBC = new javax.swing.JPanel();
        jpRightBC = new javax.swing.JPanel();
        layerCoerence = new javax.swing.JPanel();
        jpLeftCoerence = new javax.swing.JPanel();
        jpRightCoerence = new javax.swing.JPanel();
        layerFFT = new javax.swing.JPanel();
        jpLeftFFT = new javax.swing.JPanel();
        jpRightFFT = new javax.swing.JPanel();
        layerMemse = new javax.swing.JPanel();
        jpLeftMemse = new javax.swing.JPanel();
        jpRightMemse = new javax.swing.JPanel();
        layerLomb = new javax.swing.JPanel();
        jpLeftLomb = new javax.swing.JPanel();
        jpRightLomb = new javax.swing.JPanel();

        setOpaque(true);

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        layerRR.setLayout(new java.awt.GridLayout(2, 0));

        jpLeftRR.setBackground(new java.awt.Color(255, 255, 255));
        jpLeftRR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        jpLeftRR.setOpaque(false);

        javax.swing.GroupLayout jpLeftRRLayout = new javax.swing.GroupLayout(jpLeftRR);
        jpLeftRR.setLayout(jpLeftRRLayout);
        jpLeftRRLayout.setHorizontalGroup(
            jpLeftRRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        jpLeftRRLayout.setVerticalGroup(
            jpLeftRRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );

        layerRR.add(jpLeftRR);

        jpRightRR.setBackground(new java.awt.Color(255, 255, 255));
        jpRightRR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        jpRightRR.setOpaque(false);

        javax.swing.GroupLayout jpRightRRLayout = new javax.swing.GroupLayout(jpRightRR);
        jpRightRR.setLayout(jpRightRRLayout);
        jpRightRRLayout.setHorizontalGroup(
            jpRightRRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        jpRightRRLayout.setVerticalGroup(
            jpRightRRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );

        layerRR.add(jpRightRR);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(ChartTopComponent.class, "ChartTopComponent.layerRR.TabConstraints.tabTitle"), layerRR); // NOI18N

        layerBC.setLayout(new java.awt.GridLayout(2, 0));

        jpLeftBC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));

        javax.swing.GroupLayout jpLeftBCLayout = new javax.swing.GroupLayout(jpLeftBC);
        jpLeftBC.setLayout(jpLeftBCLayout);
        jpLeftBCLayout.setHorizontalGroup(
            jpLeftBCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        jpLeftBCLayout.setVerticalGroup(
            jpLeftBCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );

        layerBC.add(jpLeftBC);

        jpRightBC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));

        javax.swing.GroupLayout jpRightBCLayout = new javax.swing.GroupLayout(jpRightBC);
        jpRightBC.setLayout(jpRightBCLayout);
        jpRightBCLayout.setHorizontalGroup(
            jpRightBCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        jpRightBCLayout.setVerticalGroup(
            jpRightBCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );

        layerBC.add(jpRightBC);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(ChartTopComponent.class, "ChartTopComponent.layerBC.TabConstraints.tabTitle"), layerBC); // NOI18N

        layerCoerence.setLayout(new java.awt.GridLayout(2, 0));

        jpLeftCoerence.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));

        javax.swing.GroupLayout jpLeftCoerenceLayout = new javax.swing.GroupLayout(jpLeftCoerence);
        jpLeftCoerence.setLayout(jpLeftCoerenceLayout);
        jpLeftCoerenceLayout.setHorizontalGroup(
            jpLeftCoerenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        jpLeftCoerenceLayout.setVerticalGroup(
            jpLeftCoerenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );

        layerCoerence.add(jpLeftCoerence);

        jpRightCoerence.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));

        javax.swing.GroupLayout jpRightCoerenceLayout = new javax.swing.GroupLayout(jpRightCoerence);
        jpRightCoerence.setLayout(jpRightCoerenceLayout);
        jpRightCoerenceLayout.setHorizontalGroup(
            jpRightCoerenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        jpRightCoerenceLayout.setVerticalGroup(
            jpRightCoerenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );

        layerCoerence.add(jpRightCoerence);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(ChartTopComponent.class, "ChartTopComponent.layerCoerence.TabConstraints.tabTitle"), layerCoerence); // NOI18N

        layerFFT.setLayout(new java.awt.GridLayout(2, 0));

        jpLeftFFT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));

        javax.swing.GroupLayout jpLeftFFTLayout = new javax.swing.GroupLayout(jpLeftFFT);
        jpLeftFFT.setLayout(jpLeftFFTLayout);
        jpLeftFFTLayout.setHorizontalGroup(
            jpLeftFFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        jpLeftFFTLayout.setVerticalGroup(
            jpLeftFFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );

        layerFFT.add(jpLeftFFT);

        jpRightFFT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));

        javax.swing.GroupLayout jpRightFFTLayout = new javax.swing.GroupLayout(jpRightFFT);
        jpRightFFT.setLayout(jpRightFFTLayout);
        jpRightFFTLayout.setHorizontalGroup(
            jpRightFFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        jpRightFFTLayout.setVerticalGroup(
            jpRightFFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );

        layerFFT.add(jpRightFFT);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(ChartTopComponent.class, "ChartTopComponent.layerFFT.TabConstraints.tabTitle"), layerFFT); // NOI18N

        layerMemse.setLayout(new java.awt.GridLayout(2, 0));

        jpLeftMemse.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));

        javax.swing.GroupLayout jpLeftMemseLayout = new javax.swing.GroupLayout(jpLeftMemse);
        jpLeftMemse.setLayout(jpLeftMemseLayout);
        jpLeftMemseLayout.setHorizontalGroup(
            jpLeftMemseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        jpLeftMemseLayout.setVerticalGroup(
            jpLeftMemseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );

        layerMemse.add(jpLeftMemse);

        jpRightMemse.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));

        javax.swing.GroupLayout jpRightMemseLayout = new javax.swing.GroupLayout(jpRightMemse);
        jpRightMemse.setLayout(jpRightMemseLayout);
        jpRightMemseLayout.setHorizontalGroup(
            jpRightMemseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        jpRightMemseLayout.setVerticalGroup(
            jpRightMemseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );

        layerMemse.add(jpRightMemse);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(ChartTopComponent.class, "ChartTopComponent.layerMemse.TabConstraints.tabTitle"), layerMemse); // NOI18N

        layerLomb.setLayout(new java.awt.GridLayout(2, 0));

        jpLeftLomb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));

        javax.swing.GroupLayout jpLeftLombLayout = new javax.swing.GroupLayout(jpLeftLomb);
        jpLeftLomb.setLayout(jpLeftLombLayout);
        jpLeftLombLayout.setHorizontalGroup(
            jpLeftLombLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        jpLeftLombLayout.setVerticalGroup(
            jpLeftLombLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );

        layerLomb.add(jpLeftLomb);

        jpRightLomb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));

        javax.swing.GroupLayout jpRightLombLayout = new javax.swing.GroupLayout(jpRightLomb);
        jpRightLomb.setLayout(jpRightLombLayout);
        jpRightLombLayout.setHorizontalGroup(
            jpRightLombLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        jpRightLombLayout.setVerticalGroup(
            jpRightLombLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );

        layerLomb.add(jpRightLomb);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(ChartTopComponent.class, "ChartTopComponent.layerLomb.TabConstraints.tabTitle"), layerLomb); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpLeftBC;
    private javax.swing.JPanel jpLeftCoerence;
    private javax.swing.JPanel jpLeftFFT;
    private javax.swing.JPanel jpLeftLomb;
    private javax.swing.JPanel jpLeftMemse;
    private javax.swing.JPanel jpLeftRR;
    private javax.swing.JPanel jpRightBC;
    private javax.swing.JPanel jpRightCoerence;
    private javax.swing.JPanel jpRightFFT;
    private javax.swing.JPanel jpRightLomb;
    private javax.swing.JPanel jpRightMemse;
    private javax.swing.JPanel jpRightRR;
    private javax.swing.JPanel layerBC;
    private javax.swing.JPanel layerCoerence;
    private javax.swing.JPanel layerFFT;
    private javax.swing.JPanel layerLomb;
    private javax.swing.JPanel layerMemse;
    private javax.swing.JPanel layerRR;
    // End of variables declaration//GEN-END:variables
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized ChartTopComponent getDefault() {
        if (instance == null) {
            instance = new ChartTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the ChartTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized ChartTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(ChartTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof ChartTopComponent) {
            return (ChartTopComponent) win;
        }
        Logger.getLogger(ChartTopComponent.class.getName()).warning(
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
