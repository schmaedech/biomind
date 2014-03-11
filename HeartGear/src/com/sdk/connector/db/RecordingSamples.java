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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import softvfc.com.sdk.connector.Protocol;

public class RecordingSamples implements Runnable {

    private SimpleDateFormat sdf;
    private HearGearDB db;
    private static String lastLeftTimestamp = "0000-00-00 00:00:00.00000";
    private static String lastRightTimestamp = "0000-00-00 00:00:00.00000";
    private static Protocol protocol = Protocol.getInstance();

    private void debug(Exception ex) {
        Logger.getLogger(RecordingSamples.class.getName()).log(Level.INFO, null, ex);
    }

    @Override
    public void run() {
        try {
            db = new HearGearDB();
            db.connect();
            sdf = protocol.getTimestampFormat();
        } catch (Exception ex) {
            debug(ex);
        }

    }

    public void update() {
        
        if (Protocol.getInstance().isRegister()) {

            String leftTimestamp = (String) protocol.getRow()[0];
            String rightTimestamp = (String) protocol.getRow()[2];
          
            int leftSampledValue = Integer.parseInt(protocol.getRow()[1].toString());
            int rightSampledValue = Integer.parseInt(protocol.getRow()[3].toString());
            if ( !leftTimestamp.equals(lastLeftTimestamp) && isValidDate(leftTimestamp) && isValidDate(lastLeftTimestamp)
                    && !rightTimestamp.equals(lastRightTimestamp) && isValidDate(rightTimestamp) && isValidDate(lastRightTimestamp)) {
                try {

                    Date ldate = sdf.parse(leftTimestamp);
                    Timestamp ltTimestamp = new Timestamp(ldate.getTime());
                    Date rdate = sdf.parse(rightTimestamp);
                    Timestamp rtTimestamp = new Timestamp(rdate.getTime());

                    if (leftTimestamp.equals(lastLeftTimestamp)) {
                        leftSampledValue = 0;
                    }
                    if (rightTimestamp.equals(lastRightTimestamp)) {
                        rightSampledValue = 0;
                    }
  
                    db.saveRecord(new Aquisition(ltTimestamp, leftSampledValue, rtTimestamp, rightSampledValue));

                    lastLeftTimestamp = leftTimestamp;
                    lastRightTimestamp = rightTimestamp;
                } catch (Exception ex) {
                    debug(ex);
                }

            } else if ( !leftTimestamp.equals(lastLeftTimestamp) && isValidDate(leftTimestamp)) {

                try {
                    rightSampledValue = 0;
                    rightTimestamp = leftTimestamp;
                    Date ldate = sdf.parse(leftTimestamp);
                    Timestamp ltTimestamp = new Timestamp(ldate.getTime());
                    Date rdate = sdf.parse(lastRightTimestamp);
                    Timestamp rtTimestamp = new Timestamp(rdate.getTime());

                    if (leftTimestamp.equals(lastLeftTimestamp)) {
                        leftSampledValue = 0;
                    }
                    if (rightTimestamp.equals(lastRightTimestamp)) {
                        rightSampledValue = 0;
                    }

                    db.saveRecord(new Aquisition(ltTimestamp, leftSampledValue, rtTimestamp, rightSampledValue));

                    lastLeftTimestamp = leftTimestamp;
                    lastRightTimestamp = rightTimestamp;
                } catch (Exception ex) {
                    debug(ex);
                }
            } else if ( !rightTimestamp.equals(lastRightTimestamp) && isValidDate(rightTimestamp)) {

                try {

                    leftSampledValue = 0;
                    leftTimestamp = rightTimestamp;
                    Date ldate = sdf.parse(leftTimestamp);
                    Timestamp ltTimestamp = new Timestamp(ldate.getTime());
                    Date rdate = sdf.parse(rightTimestamp);
                    Timestamp rtTimestamp = new Timestamp(rdate.getTime());

                    if (leftTimestamp.equals(lastLeftTimestamp)) {
                        leftSampledValue = 0;
                    }
                    if (rightTimestamp.equals(lastRightTimestamp)) {
                        rightSampledValue = 0;
                    }

                    db.saveRecord(new Aquisition(ltTimestamp, leftSampledValue, rtTimestamp, rightSampledValue));

                    lastLeftTimestamp = leftTimestamp;
                    lastRightTimestamp = rightTimestamp;
                } catch (Exception ex) {
                    debug(ex);
                }
            }

        }
    }

    // date validation using SimpleDateFormat
    // it will take a string and make sure it's in the proper 
    // format as defined by you, and it will also make sure that
    // it's a legal date
    public boolean isValidDate(String date) {

        Date testDate = null;

        try {
            testDate = sdf.parse(date);
            
        } catch (Exception ex) {
            //debug(ex); 
            return false;
        }

        if (!sdf.format(testDate).equals(date)) {

            return false;
        }

        return true;

    } // end isValidDate

    public List<ListEntry> getAllEntrys() {
        return db.getListEntries();
    }

    public List<ListEntry> getDateDiffListEntries(Timestamp startDate, Timestamp endDate) {
        return db.getDateDiffListEntries(startDate, endDate);
    }

}
