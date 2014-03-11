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
public class CoherenceSettingsEntity {

    private boolean plotPowerBased = true;
    private boolean showFFT = true;
    private boolean showMemse = true;
    private boolean showLomb = true;

    private int plotPoints = 10;
    private double totalPowerMax;
    private double totalPowerMin;
    private double integratedWindow;
    private double coerenceRangeMax;
    private double coerenceRangeMin;

    /**
     * @return the plotPowerBased
     */
    public boolean isPlotPowerBased() {
        return plotPowerBased;
    }

    /**
     * @param plotPowerBased the plotPowerBased to set
     */
    public void setPlotPowerBased(boolean plotPowerBased) {
        this.plotPowerBased = plotPowerBased;
    }

    /**
     * @return the showFFT
     */
    public boolean isShowFFT() {
        return showFFT;
    }

    /**
     * @param showFFT the showFFT to set
     */
    public void setShowFFT(boolean showFFT) {
        this.showFFT = showFFT;
    }

    /**
     * @return the showMemse
     */
    public boolean isShowMemse() {
        return showMemse;
    }

    /**
     * @param showMemse the showMemse to set
     */
    public void setShowMemse(boolean showMemse) {
        this.showMemse = showMemse;
    }

    /**
     * @return the showLomb
     */
    public boolean isShowLomb() {
        return showLomb;
    }

    /**
     * @param showLomb the showLomb to set
     */
    public void setShowLomb(boolean showLomb) {
        this.showLomb = showLomb;
    }

    /**
     * @return the totalPowerMax
     */
    public double getTotalPowerMax() {
        return totalPowerMax;
    }

    /**
     * @param totalPowerMax the totalPowerMax to set
     */
    public void setTotalPowerMax(double totalPowerMax) {
        this.totalPowerMax = totalPowerMax;
    }

    /**
     * @return the totalPowerMin
     */
    public double getTotalPowerMin() {
        return totalPowerMin;
    }

    /**
     * @param totalPowerMin the totalPowerMin to set
     */
    public void setTotalPowerMin(double totalPowerMin) {
        this.totalPowerMin = totalPowerMin;
    }

    /**
     * @return the integratedWindow
     */
    public double getIntegratedWindow() {
        return integratedWindow;
    }

    /**
     * @param integratedWindow the integratedWindow to set
     */
    public void setIntegratedWindow(double integratedWindow) {
        this.integratedWindow = integratedWindow;
    }

    /**
     * @return the coerenceRangeMax
     */
    public double getCoerenceRangeMax() {
        return coerenceRangeMax;
    }

    /**
     * @param coerenceRangeMax the coerenceRangeMax to set
     */
    public void setCoerenceRangeMax(double coerenceRangeMax) {
        this.coerenceRangeMax = coerenceRangeMax;
    }

    /**
     * @return the coerenceRangeMin
     */
    public double getCoerenceRangeMin() {
        return coerenceRangeMin;
    }

    /**
     * @param coerenceRangeMin the coerenceRangeMin to set
     */
    public void setCoerenceRangeMin(double coerenceRangeMin) {
        this.coerenceRangeMin = coerenceRangeMin;
    }

    /**
     * @return the plotPoints
     */
    public int getPlotPoints() {
        return plotPoints;
    }

    /**
     * @param plotPoints the plotPoints to set
     */
    public void setPlotPoints(int plotPoints) {
        this.plotPoints = plotPoints;
    }

}
