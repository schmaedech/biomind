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
package com.sdk.connector.db;

 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
 


/**
 *
 * @author Diego Schmaedech Martins (schmaedech@gmail.com)
 * @version 09/10/2010
 */
public class TableExporter {

    public TableExporter() {
    
    }

    public void exportTable(DefaultTableModel model, File file) throws UnsupportedEncodingException, FileNotFoundException {


        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF8"));

        try {
            for (int i = 0; i < model.getColumnCount(); i++) {
                out.write(model.getColumnName(i) + "\t");
            }
            out.write(System.getProperty("line.separator"));
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) { 
                    out.write(model.getValueAt(i, j) + "\t");
                }
                out.write(System.getProperty("line.separator"));
            }
            out.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void exportTable(TableModel model, File file) throws UnsupportedEncodingException, FileNotFoundException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF8"));
        try {
            for (int i = 0; i < model.getColumnCount(); i++) {
                out.write(model.getColumnName(i) + "\t");
            }
            out.write(System.getProperty("line.separator"));
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    if(model.getValueAt(i, j) != null){
                        out.write(model.getValueAt(i, j) + "\t");
                    }else{
                        out.write("" + "\t");
                    }
                }
                out.write(System.getProperty("line.separator"));
            }
            out.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    } 
}
