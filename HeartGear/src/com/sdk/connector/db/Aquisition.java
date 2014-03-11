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

public class Aquisition {

    public Aquisition() {
    }

    public Aquisition(Timestamp leftTimestamp, int leftRR, Timestamp rightTimestamp, int rightRR) {
        this(-1,leftTimestamp, leftRR, rightTimestamp, rightRR, -1);
    }

    public Aquisition(int idRegister, Timestamp leftTimestamp, int leftRR, Timestamp rightTimestamp, int rightRR, int id) {
        this.idRegister = idRegister;
        this.leftTimestamp = leftTimestamp;
        this.leftRR = leftRR;
        this.rightTimestamp = rightTimestamp;
        this.rightRR = rightRR;
        this.id = id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private Timestamp leftTimestamp;
    private int leftRR;
    private Timestamp rightTimestamp;
    private int rightRR;
    private int idRegister;
    private int id;

    /**
     * @return the leftTimestamp
     */
    public Timestamp getLeftTimestamp() {
        return leftTimestamp;
    }

    /**
     * @param leftTimestamp the leftTimestamp to set
     */
    public void setLeftTimestamp(Timestamp leftTimestamp) {
        this.leftTimestamp = leftTimestamp;
    }

    /**
     * @return the leftRR
     */
    public int getLeftRR() {
        return leftRR;
    }

    /**
     * @param leftRR the leftRR to set
     */
    public void setLeftRR(int leftRR) {
        this.leftRR = leftRR;
    }

    /**
     * @return the rightTimestamp
     */
    public Timestamp getRightTimestamp() {
        return rightTimestamp;
    }

    /**
     * @param rightTimestamp the rightTimestamp to set
     */
    public void setRightTimestamp(Timestamp rightTimestamp) {
        this.rightTimestamp = rightTimestamp;
    }

    /**
     * @return the rightRR
     */
    public int getRightRR() {
        return rightRR;
    }

    /**
     * @param rightRR the rightRR to set
     */
    public void setRightRR(int rightRR) {
        this.rightRR = rightRR;
    }

    /**
     * @return the idRegister
     */
    public int getIdRegister() {
        return idRegister;
    }

    /**
     * @param idRegister the idRegister to set
     */
    public void setIdRegister(int idRegister) {
        this.idRegister = idRegister;
    }

}
