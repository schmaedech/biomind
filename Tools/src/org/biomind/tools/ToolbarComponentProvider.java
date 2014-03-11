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
 
import com.sdk.connector.ButtonsBar;
import javax.swing.JComponent;
import org.openide.util.Lookup;

/**
 *
 * @author Schmaedech
 */
public abstract class ToolbarComponentProvider {

    public abstract JComponent createToolbar();

    public static ToolbarComponentProvider getDefault() {
        ToolbarComponentProvider provider = Lookup.getDefault().lookup(ToolbarComponentProvider.class);
        if (provider == null) {
            provider = new DefaultToolbarComponentProvider();
        }
        return provider;
    }

    private static class DefaultToolbarComponentProvider extends ToolbarComponentProvider {
        @Override
        public JComponent createToolbar() {
//            JTabbedPane pane = new JTabbedPane();
//            JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
//            panel1.add(new JButton("Button 1"));
//            panel1.add(new JButton("Button 2"));
//            pane.add("Tab 1", panel1);
//            pane.add("Tab 2", new JPanel());
//            pane.setPreferredSize(new Dimension(100, 70));
            return new ButtonsBar();
        }
    }

}
