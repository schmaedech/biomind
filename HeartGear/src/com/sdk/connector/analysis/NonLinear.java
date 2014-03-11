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

import java.util.Vector;

/**
 *
 * @author diego
 */
public class NonLinear {

    /* NON-LINEAR methods
    * m, specifies the pattern length
    * r, defines the criterion of similarity
    * called by chart engine
    */
    public static float calculeApEn(int m, float r, Vector<Float> rr){
        float result = 0;
        result = calculePhy(m,r, rr) - calculePhy(m+1,r, rr);
        return result;
    }

    //variavel para o calculo da entropia aproximada //somatorio do Cm
    private static float calculePhy(int m, float r, Vector<Float> rr){
        float result = 0;
        int N = rr.size();
        int jNm = N - m + 1;
        float[][] Uuj = new float[jNm][m];
        for(int k = 0; k < jNm; k++){
            for(int i = 0; i < m; i++){
                Uuj[k][i] = rr.get(i+k);
            }
        }
        for(int i = 0; i < jNm; i++){
            result += Math.log1p(calculeCmj(i,Uuj,r,jNm,true));
        }
        result = result/(float)jNm;
        return result;
    }

    /*
     * called by chart engine
     */
    public static float calculeLnSampEn(int m, float r, Vector<Float> rr){
        float result = 0;
        try{
            result = (float) Math.log1p( ( calculeSampEn(m, r, rr) / calculeSampEn(m+1, r, rr) ) );
        }catch(Exception ex){
            //Exceptions.printStackTrace(ex);
        }
        return result;
    }

    //calculo somatorio do Cm
    private static float calculeSampEn(int m, float r, Vector<Float> rr){
        float result = 0;
        int N = rr.size();
        int jNm = N - m + 1;
        float[][] Uuj = new float[jNm][m];
        for(int k = 0; k < jNm; k++){
            for(int i = 0; i < m; i++){
                Uuj[k][i] = rr.get(i+k);
            }
        }
        for(int i = 0; i < jNm; i++){
            result += calculeCmj(i,Uuj,r,jNm,false);
        }
        result = result/(float)jNm;
        return result;
    }

    public static float calculeD2(int m, int rBin, float[] rr){
        if(rr.length > m){
            Vector<Float> slopeX = new Vector<Float>();
            Vector<Float> slopeY = new Vector<Float>();
            float[] x = new float[rBin];
            float[] y = new float[rBin];
            float r = -1.5f;
            float lnD2 = 0f;
            for(int i = 0; i < rBin; i++){
                 r += 0.015f;
                 x[i] = r;

                 try{
                    lnD2 = calculeLnD2(m, (float)Math.expm1(r+1f), rr);
                 }catch(Exception ex){
                    return 0f;
                 }
                 y[i] = lnD2;
            }

            if(y.length > 0){
                float middle = Statistics.max(y)/3f;
                float middle2 = middle*2f;
                for(int i = 0; i< rBin; i++){
                     if(y[i] > middle && y[i] < middle2){
                        slopeX.add(x[i]);
                        slopeY.add(y[i]);
                     }
                }

                if(slopeX != null && slopeX.size() > 0 && slopeY != null && slopeY.size() > 0 ){
                    return Statistics.resampleLinearRegression(ArraysAndVectors.getFloatArray(slopeX), ArraysAndVectors.getFloatArray(slopeY))[0];
                }else{
                    return 0f;
                }
            }else{
                return 0f;
            }
        }else{
            return 0f;
        }
    }


    /*
     * double lnr = -1.5;
     *   for(int i = 0; i < 50; i++){
     *        lnr += 0.02;
     *        System.out.println( lnr +"\t"+ StdStats.calculeLnD2(10, Math.exp(lnr), rr) );
     *   }
     */
    public static float calculeLnD2(int m, float r, float[] rr){
        float result = 0;

        int jNm = rr.length - m + 1;
        float[][] Uuj = new float[jNm][m];
        for(int k = 0; k < jNm; k++){
            for(int i = 0; i < m; i++){
                Uuj[k][i] = rr[i+k];
            }
        }
        for(int i = 0; i < jNm; i++){
            result += calculeD2Cmj(i,Uuj,r,jNm, false);
        }

        return (float) Math.log1p(result/(float)jNm);
    }

    private static double calculeD2Cmj(int j, float[][] Uj, float r, int jNm, boolean itself){
        float result = 0;
        int nbrofu = 0;
        for( int k = 0; k < jNm; k++ ){
            if(itself){
                result = dCoUjUk( Uj[j], Uj[k] );
            }else{
                if(j!=k){
                    result = dCoUjUk( Uj[j], Uj[k] );
                }
            }
            if( result <= r ){
                nbrofu++;
            }
        }
        if(itself){
            result = (float)nbrofu/jNm;
        }else{
            result = (float)nbrofu/(jNm-1);
        }
        return result;
    }

    private static float calculeCmj(int j, float[][] Uj, float r, int jNm, boolean itself){
        float result = 0;
        int nbrofu = 0;
        for( int k = 0; k < jNm; k++ ){
            if(itself){
                result = dEnUjUk( Uj[j], Uj[k] );
            }else{
                if(j!=k){
                    result = dEnUjUk( Uj[j], Uj[k] );
                }
            }

            if( result <= r ){
                nbrofu++;
            }
        }
        if(itself){
            result = (float)nbrofu/jNm;
        }else{
            result = (float)nbrofu/(jNm-1);
        }

        return result;
    }

    //definido para ApEn e SamplEn
    private static float dEnUjUk(float[] uj, float[] uk){
        float[] result = new float[uj.length];
        int i = 0;
        for (float f : uj) {
            result[i] = Math.abs(uk[i]-f);
            i++;
        }

        return Statistics.max(result);
    }

    //definido para dimension correlation
    private static float dCoUjUk(float[] uj, float[] uk){
        float result = 0f;
        int i = 0;
        for (float f : uj) {
            result += Math.pow(uk[i]-f,2);
            i++;
        }

        return (float) Math.sqrt(result);
    }

}
