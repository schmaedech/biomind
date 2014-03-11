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

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class AquisitionFrame
        extends javax.swing.JFrame 
        implements ActionListener, ListSelectionListener {
    
    /** Creates new form AquisitionFrame */
    public AquisitionFrame() {
        initComponents();
        loadFrameIcon();
        windowAdapter = new WindowCloser();
        this.addWindowListener(windowAdapter);
        db = new HearGearDB();
        db.connect();
        addressActionPanel.addActionListener(this);
        addressPanel.setEditable(false);
        List<ListEntry> entries = db.getListEntries();
        addressListPanel.addListEntries(entries);
        addressPanel.addListEntries(entries);
        addressListPanel.addListSelectionListener(this);
    }

    private void loadFrameIcon() {
        ImageIcon imgIcon = null;
        imgIcon = new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sdk/connector/resources/frame.gif")).getImage());
        Image img = imgIcon.getImage();
        this.setIconImage(img); 
    }    
    

    private void initComponents() {
        addressActionPanel = new  AquisitionActionPanel();
        addressPanel = new  AquisitionPanel();
        addressListPanel = new  AquisitionListPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aquisition Database");
        getContentPane().add(addressActionPanel, java.awt.BorderLayout.SOUTH);

        getContentPane().add(addressPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(addressListPanel, java.awt.BorderLayout.WEST);

        pack();
    }


    
    private void cancelAddress() {
        addressPanel.clear();
        addressPanel.setEditable(false);
        ListEntry entry = addressListPanel.getSelectedListEntry();
        if (entry != null) {
            int id = entry.getId();
            Aquisition aquisition = db.getAquisition(id);
            addressPanel.setAquisition(aquisition);
        }
        
        
    }
    
    private void newAddress(){
        addressPanel.clear();
        addressPanel.setEditable(true);
        
    }
    
    private void deleteAddress() {
        int id = addressPanel.getId();
        if (id != -1) {
            db.deleteRecord(id);
            int selectedIndex = addressListPanel.deleteSelectedEntry();
            if (selectedIndex >= 0) {
                selectedIndex = addressListPanel.setSelectedIndex(selectedIndex);
                ListEntry entry = addressListPanel.getSelectedListEntry();
                if (entry != null) {
                    id = entry.getId();
                    Aquisition aquisition = db.getAquisition(id);
                    addressPanel.setAquisition(aquisition);
                } else {
                    addressPanel.clear();
                }
            }
        } else {
            addressPanel.clear();
        }
        addressPanel.setEditable(false);
        
        
        
    }
    
    private void editAddress() {
        int selected = addressListPanel.getSelectedIndex();
        if (selected >=0) {
            addressPanel.setEditable(true);
        }
        
    }
    
    private void saveAddress() throws ParseException {
        if (addressPanel.isEditable()) {
            Aquisition aquisition = addressPanel.getAquisition();
            int id = aquisition.getId();
            int idRegister = aquisition.getIdRegister();
            Timestamp leftTimestamp = aquisition.getLeftTimestamp();
            int leftRR = aquisition.getLeftRR();
            Timestamp rightTimestamp = aquisition.getRightTimestamp();
            int rightRR = aquisition.getRightRR();

            if (id == -1) {
                id = db.saveRecord(aquisition);
                aquisition.setId(id);

                ListEntry entry = new ListEntry(idRegister, leftTimestamp, leftRR,rightTimestamp,rightRR, id);
                addressListPanel.addListEntry(entry);

            } else {
                db.editRecord(aquisition);
                ListEntry entry = addressListPanel.getSelectedListEntry();
                entry.setIdRegister(idRegister);
                entry.setLeftTimestamp(leftTimestamp);
                entry.setLeftRR(leftRR);
                entry.setRightTimestamp(rightTimestamp);
                entry.setRightRR(rightRR);
                addressListPanel.repaint();
            }
            addressPanel.setEditable(false);
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        System.out.println("ActionEvent: " + actionCommand);
        if (actionCommand.equalsIgnoreCase("CANCEL_AQUISITION")) {
            cancelAddress();
        } else if (actionCommand.equalsIgnoreCase("NEW_AQUISITION")) {
            newAddress();
        } else if (actionCommand.equalsIgnoreCase("DELETE_AQUISITION")) {
            deleteAddress();
        } else if (actionCommand.equalsIgnoreCase("EDIT_AQUISITION")) {
            editAddress();
        } else if (actionCommand.equalsIgnoreCase("SAVE_AQUISITION")) {
            try {
                saveAddress();
            } catch (ParseException ex) {
                Logger.getLogger(AquisitionFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        AquisitionFrame app = new AquisitionFrame();
        app.setVisible(true);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        JList entryList = (JList) e.getSource();
        selectedEntry = entryList.getSelectedIndex();
        ListEntry entry = (ListEntry)entryList.getSelectedValue();
        if (entry != null) {
            int id = entry.getId();
            Aquisition aquisition = db.getAquisition(id);
            addressPanel.setAquisition(aquisition);
        } else {
            addressPanel.clear();
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private  AquisitionActionPanel addressActionPanel;
    private  AquisitionListPanel addressListPanel;
    private  AquisitionPanel addressPanel;
    // End of variables declaration//GEN-END:variables
    
    private int selectedEntry = -1;
    private HearGearDB db;
    private WindowAdapter windowAdapter;
    
    class WindowCloser extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            db.disconnect();
        }
    
    }
}
