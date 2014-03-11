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

package com.sdk.connector.db;

 
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author  John O'Conner
 */
public class AquisitionListPanel extends javax.swing.JPanel {
    
    /** Creates new form AquisitionListPanel */
    public AquisitionListPanel() {
        renderer = new ListEntryRenderer();
        model = new DefaultListModel();
        initComponents();
        
        
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        addressList = new javax.swing.JList();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        addressList.setModel(model);
        addressList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        addressList.setCellRenderer(renderer);
        scrollPane.setViewportView(addressList);

        add(scrollPane);
    }// </editor-fold>//GEN-END:initComponents
    
    public void addListEntry(ListEntry entry) {
        model.addElement(entry);
        
    }
    
    public void addListEntries(List<ListEntry> list) {
        for(ListEntry entry: list) { 
            addListEntry(entry);
        }
    }
    
    public int getSelectedIndex() {
        return addressList.getSelectedIndex();
    }
    
    public int setSelectedIndex(int index) {
        assert(index >= -1);
        DefaultListModel model = (DefaultListModel)addressList.getModel();
        int size = model.getSize();
        if (index < size) {
            addressList.setSelectedIndex(index);
        } else {
            addressList.setSelectedIndex(size-1);
            index = size -1;
        }
        return index;
    }
    
    public ListEntry getSelectedListEntry() {
        ListEntry entry = (ListEntry)addressList.getSelectedValue();
        return entry;
    }
    
    public int deleteSelectedEntry() {
        int selectedIndex = addressList.getSelectedIndex();
        if (selectedIndex >= 0) {
            DefaultListModel model = (DefaultListModel)addressList.getModel();
            model.remove(selectedIndex);
        }
        return selectedIndex;
    }
    public void addListSelectionListener(ListSelectionListener listener) {
        addressList.addListSelectionListener(listener);
    }
    
    public void removeListSelectionListener(ListSelectionListener listener) {
        addressList.removeListSelectionListener(listener);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList addressList;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
    
    private ListEntryRenderer renderer;
    private DefaultListModel model;
}