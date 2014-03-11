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
package com.sdk.connector;

import com.sdk.connector.animation.AnimationTopComponent;
import com.sdk.connector.chart.ChartTopComponent;
import com.sdk.connector.db.DatabaseTopComponent;
import com.sdk.connector.settings.SettingsTopComponent;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import org.openide.windows.Mode;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author diego
 */
public class ButtonsBar extends javax.swing.JPanel implements MouseListener{

    private ArrayList<MenuJLabel> menu = new ArrayList<MenuJLabel>();
   
    private String lblDriver, lblData,lblChart,lblSettings, lblAnimation, lblAbout;

    private boolean free = false;

    /** Creates new form ButtonsBar */
    public ButtonsBar() {
       initComponents();
       

       lblDriver = ResourceBundle.getBundle("com/sdk/connector/Bundle").getString("driver");
       lblData = ResourceBundle.getBundle("com/sdk/connector/Bundle").getString("data");
       lblChart = ResourceBundle.getBundle("com/sdk/connector/Bundle").getString("chart");
       lblSettings = ResourceBundle.getBundle("com/sdk/connector/Bundle").getString("settings");
       lblAnimation = ResourceBundle.getBundle("com/sdk/connector/Bundle").getString("animation");
       lblAbout = ResourceBundle.getBundle("com/sdk/connector/Bundle").getString("about");

       MenuJLabel mjlAquisition = new MenuJLabel(lblDriver);
       mjlAquisition.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sdk/connector/resources/utilities-system-monitor.png")));
       mjlAquisition.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
       mjlAquisition.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM); 
       MenuJLabel mjlChart = new MenuJLabel(lblChart);
       mjlChart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sdk/connector/resources/chart.png")));
       mjlChart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
       mjlChart.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
       MenuJLabel mjlData = new MenuJLabel(lblData);
       mjlData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sdk/connector/resources/database.png")));
       mjlData.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
       mjlData.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
       MenuJLabel mjlSettings = new MenuJLabel(lblSettings);
       mjlSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sdk/connector/resources/gnome-settings.png")));
       mjlSettings.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
       mjlSettings.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
       MenuJLabel mjlAnimation = new MenuJLabel(lblAnimation);
       mjlAnimation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sdk/connector/resources/flash24.png")));
       mjlAnimation.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
       mjlAnimation.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

       MenuJLabel mjlAbout = new MenuJLabel(lblAbout);
       mjlAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sdk/connector/resources/gtk-about.png")));
       mjlAbout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
       mjlAbout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
       menu.add(mjlAquisition);
       menu.add(mjlChart);
       if(free)
            mjlData.setEnabled(false);

       menu.add(mjlData);
       menu.add(mjlSettings);
       if(free)
            mjlSettings.setEnabled(false);

       //menu.add(mjlAnimation);
       menu.add(mjlAbout);

       Iterator<MenuJLabel> itr = menu.iterator();
       while (itr.hasNext()) {
          MenuJLabel source = itr.next();
          source.addMouseListener(this);
          add(source);
       }
       doClicked(mjlAquisition);

       init();
       
    }

    private static void putOnMode(TopComponent c){
        final Mode mode = WindowManager.getDefault().findMode("output");
        if (mode != null) {
            mode.dockInto(c);
            c.open();
            c.requestActive();
        }
    }
    // some initialization method
    public static void init () {
     WindowManager.getDefault().invokeWhenUIReady(new Runnable() {
            @Override
        public void run() {

                if(!CaptureTopComponent.findInstance().isOpened()){
                    CaptureTopComponent.findInstance().open();
                    putOnMode(CaptureTopComponent.findInstance());
                    CaptureTopComponent.findInstance().close();

                }else{
                    putOnMode(CaptureTopComponent.findInstance());
                }
                if(!DatabaseTopComponent.findInstance().isOpened()){
                    DatabaseTopComponent.findInstance().open();
                    DatabaseTopComponent.findInstance().close();

                }
                if(!ChartTopComponent.findInstance().isOpened()){
                    ChartTopComponent.findInstance().open();
                    ChartTopComponent.findInstance().close();

                }
                if(!SettingsTopComponent.findInstance().isOpened()){
                    SettingsTopComponent.findInstance().open();
                    SettingsTopComponent.findInstance().close();

                }
                if(!AnimationTopComponent.findInstance().isOpened()){
                    AnimationTopComponent.findInstance().open();                    
                    putOnMode(AnimationTopComponent.findInstance());
                    AnimationTopComponent.findInstance().close();
                }else{
                    putOnMode(AnimationTopComponent.findInstance());
                }

           new UpdateThread();
           new UpdateFrequency();
        }

     });

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
        Line2D lin = new Line2D.Float(0, h, w, h);
        g2d.setColor(Color.darkGray);
        g2d.setStroke(new BasicStroke(2));
        g2d.draw(lin);

        setOpaque( false );
        super.paintComponent( g );
        setOpaque( true );
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(680, 90));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void mouseClicked(MouseEvent e) {
        MenuJLabel source = (MenuJLabel)e.getSource();
        doClicked(source);
    }

    private void doClicked(MenuJLabel source){


        Iterator<MenuJLabel> itr = menu.iterator();
        while (itr.hasNext()) {
            MenuJLabel list = itr.next();
            list.setFont(new java.awt.Font("SansSerif", 0, 13));
            list.setOpaque(false);
            list.repaint();

        }
        source.setOpaque(true);
        source.setFont(new java.awt.Font("SansSerif", 1, 13));
        source.repaint();
       

        if(lblDriver.equals(source.getText())){
            CaptureTopComponent.findInstance().open();
            CaptureTopComponent.findInstance().requestActive();
            putOnMode(CaptureTopComponent.findInstance());
        }else if(lblData.equals(source.getText()) && !free){
            DatabaseTopComponent.findInstance().open();
            DatabaseTopComponent.findInstance().requestActive();
        }else if(lblChart.equals(source.getText()) ){
            ChartTopComponent.findInstance().open();
            ChartTopComponent.findInstance().requestActive();
        }else if(lblSettings.equals(source.getText()) && !free){
            SettingsTopComponent.findInstance().open();
            SettingsTopComponent.findInstance().requestActive();
        }else if(lblAnimation.equals(source.getText())){
            AnimationTopComponent.findInstance().open();
            AnimationTopComponent.findInstance().requestActive();
        }else if(lblAbout.equals(source.getText())){
             final AboutJDialog dialog = new AboutJDialog(null, true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        dialog.dispose();
                    }
                });
                dialog.setVisible(true);
        }


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        MenuJLabel source = (MenuJLabel)e.getSource();
        source.setOpaque(true);
        source.repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        MenuJLabel source = (MenuJLabel)e.getSource();
        if(!source.getFont().isBold()){
            source.setOpaque(false);
            source.repaint();
        }
        
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
