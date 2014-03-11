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

package com.sdk.connector.settings;

/**
 *
 * @author diego
 */
public class LombSettingsEntity {


    private int samples = 0;
    private boolean plotPower = true;
    private boolean smooth = true;
    private boolean zeromean = false;
    private boolean detrend = false;
    private double HFmax;
    private double HFmin;
    private double LFmax;
    private double LFmin;
    private double VLFmax;
    private double VLFmin;

    /**
     * @return the smooth
     */
    public boolean isSmooth() {
        return smooth;
    }

    /**
     * @param smooth the smooth to set
     */
    public void setSmooth(boolean smooth) {
        this.smooth = smooth;
    }

    /**
     * @return the zeromean
     */
    public boolean isZeromean() {
        return zeromean;
    }

    /**
     * @param zeromean the zeromean to set
     */
    public void setZeromean(boolean zeromean) {
        this.zeromean = zeromean;
    }

    /**
     * @return the detrend
     */
    public boolean isDetrend() {
        return detrend;
    }

    /**
     * @param detrend the detrend to set
     */
    public void setDetrend(boolean detrend) {
        this.detrend = detrend;
    }

    /**
     * @return the plotPower
     */
    public boolean isPlotPower() {
        return plotPower;
    }

    /**
     * @param plotPower the plotPower to set
     */
    public void setPlotPower(boolean plotPower) {
        this.plotPower = plotPower;
    }

    /**
     * @return the HFmax
     */
    public double getHFmax() {
        return HFmax;
    }

    /**
     * @param HFmax the HFmax to set
     */
    public void setHFmax(double HFmax) {
        this.HFmax = HFmax;
    }

    /**
     * @return the HFmin
     */
    public double getHFmin() {
        return HFmin;
    }

    /**
     * @param HFmin the HFmin to set
     */
    public void setHFmin(double HFmin) {
        this.HFmin = HFmin;
    }

    /**
     * @return the LFmax
     */
    public double getLFmax() {
        return LFmax;
    }

    /**
     * @param LFmax the LFmax to set
     */
    public void setLFmax(double LFmax) {
        this.LFmax = LFmax;
    }

    /**
     * @return the LFmin
     */
    public double getLFmin() {
        return LFmin;
    }

    /**
     * @param LFmin the LFmin to set
     */
    public void setLFmin(double LFmin) {
        this.LFmin = LFmin;
    }

    /**
     * @return the VLFmax
     */
    public double getVLFmax() {
        return VLFmax;
    }

    /**
     * @param VLFmax the VLFmax to set
     */
    public void setVLFmax(double VLFmax) {
        this.VLFmax = VLFmax;
    }

    /**
     * @return the VLFmin
     */
    public double getVLFmin() {
        return VLFmin;
    }

    /**
     * @param VLFmin the VLFmin to set
     */
    public void setVLFmin(double VLFmin) {
        this.VLFmin = VLFmin;
    }

    /**
     * @return the samples
     */
    public int getSamples() {
        return samples;
    }

    /**
     * @param samples the samples to set
     */
    public void setSamples(int samples) {
        this.samples = samples;
    }
 
}
