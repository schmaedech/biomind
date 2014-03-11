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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import javax.swing.JLabel;

public class MenuJLabel extends JLabel{

    private Color color1 = new Color(230,255,255);
    private Color color2 = color1.brighter();

    MenuJLabel(String string) {
        setText(string);   
        setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        setHorizontalAlignment(javax.swing.SwingConstants.CENTER);         
        setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        setMaximumSize(new java.awt.Dimension(120, 90));
        setMinimumSize(new java.awt.Dimension(0, 90));
         
    }


    @Override
    protected void paintComponent(Graphics g) {
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
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setStroke(new BasicStroke(2));
        g2d.draw(lin);
        setOpaque( false );
        super.paintComponent( g );
        setOpaque( true );
    }


}