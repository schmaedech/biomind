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

package com.sdk.connector.analysis;

import com.sdk.connector.settings.CoherenceSettingsEntity;
import com.sdk.connector.settings.CoherenceSettingsPanel;
import com.sdk.connector.settings.FFTSettingsPanel;
import com.sdk.connector.settings.LombSettingsPanel;
import com.sdk.connector.settings.MemseSettingsPanel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class PhyCoerence {

    public static double leftFFTCoerence = 0;
    public static double rightFFTCoerence = 0;
    public static double leftMemseCoerence = 0;
    public static double rightMemseCoerence = 0;
    public static double leftLombCoerence = 0;
    public static double rightLombCoerence = 0;

    private double VLFmin = 0;
    private double VLFmax = 0;
    private double LFmin = 0;
    private double LFmax = 0;
    private double HFmin = 0;
    private double HFmax = 0;

    CoherenceSettingsEntity coes;

    public PhyCoerence(){

    }
    private void debug(Exception ex){
        Logger.getLogger(PhyCoerence.class.getName()).log(Level.INFO, null, ex);
    }

    public void drawCoerence(double[] frequency, double[] power, String side, String type){

        coes = CoherenceSettingsPanel.getCoerenceSettingsEntity();

        if(coes.isPlotPowerBased()){

            if(side.startsWith(java.util.ResourceBundle.getBundle("com/sdk/connector/Bundle").getString("side.left"))){

                if(type.equals("FFT")){
                    try{
                        leftFFTCoerence = drawPowerCoerence(frequency,power) ;
                    }catch(Exception ex){
                        debug(ex);
                    }
                    
                }else if(type.equals("Memse")){
                    try{
                        leftMemseCoerence = drawPowerCoerence(frequency,power) ;
                        //System.out.println("ret = " + leftMemseCoerence);
                    }catch(Exception ex){
                        debug(ex);
                    }
                    
                }else if(type.equals("Lomb")){
                    try{
                        leftLombCoerence = drawPowerCoerence(frequency,power) ;
                    }catch(Exception ex){
                        debug(ex);
                    }
                    
                }
            }else{
                if(type.equals("FFT")){
                    try{
                        rightFFTCoerence = drawPowerCoerence(frequency,power) ;
                    }catch(Exception ex){
                        debug(ex);
                    }
                   
                }else if(type.equals("Memse")){
                    try{
                        rightMemseCoerence = drawPowerCoerence(frequency,power) ;
                    }catch(Exception ex){
                        debug(ex);
                    }
                    
                }else if(type.equals("Lomb")){
                    try{
                        rightLombCoerence = drawPowerCoerence(frequency,power);
                    }catch(Exception ex){
                        debug(ex);
                    }
                    
                }
            }
        }else{
            if(side.startsWith(java.util.ResourceBundle.getBundle("com/sdk/connector/Bundle").getString("side.left"))){

                if(type.equals("FFT")){
                    VLFmin = FFTSettingsPanel.getFftSettingsEntity().getVLFmin();
                    VLFmax = FFTSettingsPanel.getFftSettingsEntity().getVLFmax();
                    LFmin = FFTSettingsPanel.getFftSettingsEntity().getLFmin();
                    LFmax = FFTSettingsPanel.getFftSettingsEntity().getLFmax();
                    HFmin = FFTSettingsPanel.getFftSettingsEntity().getHFmin();
                    HFmax = FFTSettingsPanel.getFftSettingsEntity().getHFmax();
                    try{
                        leftFFTCoerence = drawPeakCoerence(frequency,power) ;
                    }catch(Exception ex){
                        debug(ex);
                    }
                }else if(type.equals("Memse")){
                    VLFmin = MemseSettingsPanel.getMemseSettingsEntity().getVLFmin();
                    VLFmax = MemseSettingsPanel.getMemseSettingsEntity().getVLFmax();
                    LFmin = MemseSettingsPanel.getMemseSettingsEntity().getLFmin();
                    LFmax = MemseSettingsPanel.getMemseSettingsEntity().getLFmax();
                    HFmin = MemseSettingsPanel.getMemseSettingsEntity().getHFmin();
                    HFmax = MemseSettingsPanel.getMemseSettingsEntity().getHFmax();
                    try{
                        leftMemseCoerence = drawPeakCoerence(frequency,power) ;
                    }catch(Exception ex){
                        debug(ex);
                    }
                    
                }else if(type.equals("Lomb")){
                    VLFmin = LombSettingsPanel.getLombSettingsEntity().getVLFmin();
                    VLFmax = LombSettingsPanel.getLombSettingsEntity().getVLFmax();
                    LFmin = LombSettingsPanel.getLombSettingsEntity().getLFmin();
                    LFmax = LombSettingsPanel.getLombSettingsEntity().getLFmax();
                    HFmin = LombSettingsPanel.getLombSettingsEntity().getHFmin();
                    HFmax = LombSettingsPanel.getLombSettingsEntity().getHFmax();
                    try{
                        leftLombCoerence = drawPeakCoerence(frequency,power) ;
                    }catch(Exception ex){
                        debug(ex);
                    }
                    
                }
            }else{
                if(type.equals("FFT")){
                    VLFmin = FFTSettingsPanel.getFftSettingsEntity().getVLFmin();
                    VLFmax = FFTSettingsPanel.getFftSettingsEntity().getVLFmax();
                    LFmin = FFTSettingsPanel.getFftSettingsEntity().getLFmin();
                    LFmax = FFTSettingsPanel.getFftSettingsEntity().getLFmax();
                    HFmin = FFTSettingsPanel.getFftSettingsEntity().getHFmin();
                    HFmax = FFTSettingsPanel.getFftSettingsEntity().getHFmax();
                    try{
                        rightFFTCoerence = drawPeakCoerence(frequency,power) ;
                    }catch(Exception ex){
                        debug(ex);
                    }
                    
                }else if(type.equals("Memse")){
                    VLFmin = MemseSettingsPanel.getMemseSettingsEntity().getVLFmin();
                    VLFmax = MemseSettingsPanel.getMemseSettingsEntity().getVLFmax();
                    LFmin = MemseSettingsPanel.getMemseSettingsEntity().getLFmin();
                    LFmax = MemseSettingsPanel.getMemseSettingsEntity().getLFmax();
                    HFmin = MemseSettingsPanel.getMemseSettingsEntity().getHFmin();
                    HFmax = MemseSettingsPanel.getMemseSettingsEntity().getHFmax();
                    try{
                        rightMemseCoerence = drawPeakCoerence(frequency,power) ;
                    }catch(Exception ex){
                        debug(ex);
                    }
                    
                }else if(type.equals("Lomb")){
                    VLFmin = LombSettingsPanel.getLombSettingsEntity().getVLFmin();
                    VLFmax = LombSettingsPanel.getLombSettingsEntity().getVLFmax();
                    LFmin = LombSettingsPanel.getLombSettingsEntity().getLFmin();
                    LFmax = LombSettingsPanel.getLombSettingsEntity().getLFmax();
                    HFmin = LombSettingsPanel.getLombSettingsEntity().getHFmin();
                    HFmax = LombSettingsPanel.getLombSettingsEntity().getHFmax();
                    try{
                        rightLombCoerence = drawPeakCoerence(frequency,power) ;
                    }catch(Exception ex){
                        debug(ex);
                    }
                    
                }
            }
        }

        
    }

    private double drawPeakCoerence(double[] frequency, double[] power){

        double vlf = getPeakVLF(frequency,power,VLFmin, VLFmax );
        double lf = getPeakLF(frequency,power,LFmin, LFmax );
        double hf = getPeakHF(frequency,power,HFmin, HFmax );

        if((vlf+hf) != 0 && lf !=0)
            return lf/(vlf+hf);
        else
            return 0;
    }

    private double getPeakVLF(double[] x, double[] y, double min, double max){

        double[] xx = new double[x.length];
        double[] yy = new double[y.length];
        System.arraycopy(x, 0, xx, 0, x.length);
        System.arraycopy(y, 0, yy, 0, y.length);

        for(int i = 0; i < xx.length; i++){
            if(!(xx[i] > min && xx[i] < max)){
                yy[i] = 0;
            }
        }
        return Statistics.max(yy);

    }

    private double getPeakLF(double[] x, double[] y, double min, double max){

        double[] xx = new double[x.length];
        double[] yy = new double[y.length];
        System.arraycopy(x, 0, xx, 0, x.length);
        System.arraycopy(y, 0, yy, 0, y.length);
         for(int i = 0; i < xx.length; i++){
            if(!(xx[i] > min && xx[i] < max)){
                yy[i] = 0;
            }
        }
        return Statistics.max(yy);

    }

    private double getPeakHF(double[] x, double[] y, double min, double max){

        double[] xx = new double[x.length];
        double[] yy = new double[y.length];
        System.arraycopy(x, 0, xx, 0, x.length);
        System.arraycopy(y, 0, yy, 0, y.length);
          for(int i = 0; i < xx.length; i++){
            if(!(xx[i] > min && xx[i] < max)){
                yy[i] = 0;
            }
        }
        return Statistics.max(yy);

    }

    private double drawPowerCoerence(double[] frequency, double[] power){

         
        double totalPowerMin = coes.getTotalPowerMin();
        double totalPowerMax = coes.getTotalPowerMax();
        double integratedWindow = coes.getIntegratedWindow();
        double coerenceRangeMin = coes.getCoerenceRangeMin();
        double coerenceRangeMax = coes.getCoerenceRangeMax();

        double[] xx = new double[frequency.length];
        double[] yy = new double[power.length];

        System.arraycopy(frequency, 0, xx, 0, frequency.length);
        System.arraycopy(power, 0, yy, 0, power.length);
        
        for(int i = 0; i < xx.length; i++){
            if(xx[i] <= totalPowerMin || xx[i] >= totalPowerMax){
                yy[i] = 0;//zero out off range total power
            }
            //System.out.println(i + "\t " + yy[i]  );
        }
        double peakPower = 0;
        double totalPower = 0;
        int indexXX = Statistics.indexMax(yy);
       // System.out.println("index = " + indexXX);
        System.arraycopy(power, 0, yy, 0, power.length);
        for(int i = 0; i < xx.length; i++){
            if(xx[i] > coerenceRangeMin && xx[i] < coerenceRangeMax){
                totalPower += yy[i];//zero out off range total power
            }
        }
         //System.out.println("totalPower = " + totalPower);

        for(int i = 0; i < xx.length; i++){
            if(xx[i] > (xx[indexXX]-integratedWindow) && xx[i] < (xx[indexXX]+integratedWindow)){
                peakPower += yy[i];
            }
        }

         // System.out.println("(totalPower-peakPower)  = " + (totalPower-peakPower) );
        if((totalPower-peakPower) != 0){
          //  System.out.println("m = " + peakPower/(totalPower-peakPower));
            return peakPower/(totalPower-peakPower);            
        }else{
            //System.out.println(0);
            return 0;
        }
    }
}
