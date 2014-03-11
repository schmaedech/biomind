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

package org.biomind.tools;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JMenuBar;
import javax.swing.JRootPane;

/**
 *
 * @author Chris
 */

class MyRootPaneLayout implements LayoutManager2 {

    private JComponent _toolbar;

    public MyRootPaneLayout(JComponent toolbar) {
        _toolbar = toolbar;
    }

    public Dimension preferredLayoutSize(Container parent) {
        int contentWidth = 0;
        int menuWidth = 0;
        int height = 0;

        Insets insets = parent.getInsets();
        height += insets.top + insets.bottom;

        JRootPane rootPane = (JRootPane) parent;

        Dimension contentSize;
        if (rootPane.getContentPane() != null) {
            contentSize = rootPane.getContentPane().getPreferredSize();
        } else {
            contentSize = rootPane.getSize();
        }
        contentWidth = contentSize.width;
        height += contentSize.height;

        if (rootPane.getJMenuBar() != null) {
            Dimension menuSize = rootPane.getJMenuBar().getPreferredSize();
            height += menuSize.height;
            menuWidth = menuSize.width;
        }

        return new Dimension(Math.max(contentWidth, menuWidth) + insets.left + insets.right, height);
    }

    public Dimension minimumLayoutSize(Container parent) {
        int contentWidth = 0;
        int menuWidth = 0;
        int height = 0;

        Insets insets = parent.getInsets();
        height += insets.top + insets.bottom;

        JRootPane rootPane = (JRootPane) parent;

        Dimension contentSize;
        if (rootPane.getContentPane() != null) {
            contentSize = rootPane.getContentPane().getMinimumSize();
        } else {
            contentSize = rootPane.getSize();
        }
        contentWidth = contentSize.width;
        height += contentSize.height;

        if (rootPane.getJMenuBar() != null) {
            Dimension menuSize = rootPane.getJMenuBar().getMinimumSize();
            height += menuSize.height;
            menuWidth = menuSize.width;
        }

        return new Dimension(Math.max(contentWidth, menuWidth) + insets.left + insets.right, height);
    }

    public Dimension maximumLayoutSize(Container target) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    public void layoutContainer(Container parent) {
        JRootPane rootPane = (JRootPane) parent;
        Rectangle bounds = rootPane.getBounds();
        Insets insets = rootPane.getInsets();
        int y = insets.top;
        int x = insets.left;
        int w = bounds.width - insets.right - insets.left;
        int h = bounds.height - insets.top - insets.bottom;

        if (rootPane.getLayeredPane() != null) {
            rootPane.getLayeredPane().setBounds(x, y, w, h);
        }

        if (rootPane.getGlassPane() != null) {
            rootPane.getGlassPane().setBounds(x, y, w, h);
        }

        if (rootPane.getJMenuBar() != null) {
            JMenuBar menu = rootPane.getJMenuBar();
            Dimension size = menu.getPreferredSize();
            menu.setBounds(x, y, w, size.height);
            y += size.height;
        }


        if (_toolbar != null) {
            Dimension size = _toolbar.getPreferredSize();
            _toolbar.setBounds(x, y, w, size.height);
            y += size.height;
        }

        if (rootPane.getContentPane() != null) {
            int height = h - y;
            if (height < 0) {
                height = 0;
            }
            rootPane.getContentPane().setBounds(x, y, w, height);
        }
    }

    public void addLayoutComponent(String name, Component comp) {
    }

    public void removeLayoutComponent(Component comp) {
    }

    public void addLayoutComponent(Component comp, Object constraints) {
    }

    public float getLayoutAlignmentX(Container target) {
        return 0.0f;
    }

    public float getLayoutAlignmentY(Container target) {
        return 0.0f;
    }

    public void invalidateLayout(Container target) {
    }
    
}
