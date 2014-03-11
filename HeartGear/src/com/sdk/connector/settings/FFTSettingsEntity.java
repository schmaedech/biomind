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
public class FFTSettingsEntity {
    private int freq = 1;
    private int samples = 0;
    private int K = 0;
    private int L = 0;
    private int D = 0;

    private boolean plotPower = true;

    private int smooth = 1;
    private int decimation = 1;
    private String window = "";

    private boolean zeromean = false;
    private boolean detrend = false;

    private boolean bicubic = false;
    private int bicubicWindow = 0;
    private int bicubicPrecision = 0;

    private double HFmax;
    private double HFmin;
    private double LFmax;
    private double LFmin;
    private double VLFmax;
    private double VLFmin;

    /**
     * @return the freq
     */
    public int getFreq() {
        return freq;
    }

    /**
     * @param freq the freq to set
     */
    public void setFreq(int freq) {
        this.freq = freq;
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

    /**
     * @return the K
     */
    public int getK() {
        return K;
    }

    /**
     * @param K the K to set
     */
    public void setK(int K) {
        this.K = K;
    }

    /**
     * @return the L
     */
    public int getL() {
        return L;
    }

    /**
     * @param L the L to set
     */
    public void setL(int L) {
        this.L = L;
    }

    /**
     * @return the D
     */
    public int getD() {
        return D;
    }

    /**
     * @param D the D to set
     */
    public void setD(int D) {
        this.D = D;
    }


    /**
     * @return the smooth
     */
    public int getSmooth() {
        return smooth;
    }

    /**
     * @param smooth the smooth to set
     */
    public void setSmooth(int smooth) {
        this.smooth = smooth;
    }

    /**
     * @return the decimation
     */
    public int getDecimation() {
        return decimation;
    }

    /**
     * @param decimation the decimation to set
     */
    public void setDecimation(int decimation) {
        this.decimation = decimation;
    }

    /**
     * @return the window
     */
    public String getWindow() {
        return window;
    }

    /**
     * @param window the window to set
     */
    public void setWindow(String window) {
        this.window = window;
    }

    /**
     * @return the bicubicWindow
     */
    public int getBicubicWindow() {
        return bicubicWindow;
    }

    /**
     * @param bicubicWindow the bicubicWindow to set
     */
    public void setBicubicWindow(int bicubicWindow) {
        this.bicubicWindow = bicubicWindow;
    }

    /**
     * @return the bicubicPrecision
     */
    public int getBicubicPrecision() {
        return bicubicPrecision;
    }

    /**
     * @param bicubicPrecision the bicubicPrecision to set
     */
    public void setBicubicPrecision(int bicubicPrecision) {
        this.bicubicPrecision = bicubicPrecision;
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
     * @return the bicubic
     */
    public boolean isBicubic() {
        return bicubic;
    }

    /**
     * @param bicubic the bicubic to set
     */
    public void setBicubic(boolean bicubic) {
        this.bicubic = bicubic;
    }


     
}
