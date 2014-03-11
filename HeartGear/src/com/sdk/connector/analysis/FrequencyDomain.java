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


/**
 *
 * @author diego
 */
public class FrequencyDomain {

    static double TWOPI = (2.0f*Math.PI);

    public static float calculeVLF(float[] xs, float[] ys){
        float result = 0;
        int i = 0;
        for (float f : xs) {
            if(f <= 0.04f){
                result += ys[i];
            }
            i++;
        }

        return result;
     }

    public static float calculeLF(float[] xs, float[] ys){
        float result = 0;
        int i = 0;
        for (float f : xs) {
            if(f > 0.04f && f <= 0.15f){
                result += ys[i];
            }
            i++;
        }
        return result;
     }

    public static float calculeHF(float[] xs, float[] ys){
        float result = 0;
        int i = 0;
        for (float f : xs) {
            if(f > 0.15f && f <= 0.4f){
                result += ys[i];
            }
            i++;
        }
        return result;
     }

    public static float calculeLFHF(float[] xs, float[] ys){
        return calculeLF(xs,ys)/calculeHF(xs,ys);
     }

    public static void realft(double[] data, int n, int isign){
    //    for (int h = 0; h < n; h++){
    //	    System.out.println( "h: "+ h + "\t"+ data[h]);
    //        }
        int i, i1, i2, i3, i4, n2p3;
        double c1 = 0.5d, c2, h1r, h1i, h2r, h2i;
        double wr, wi, wpr, wpi, wtemp, theta;


        theta = (Math.PI / (double) n);
        if (isign == 1) {
            c2 = -0.5d;
            four1(data, n, 1);
        }
        else {
            c2 = 0.5d;
            theta = -theta;
        }
        wtemp =  Math.sin(0.5*theta);
        wpr =  (-2.0d * wtemp * wtemp);
        wpi =  Math.sin(theta);
        wr =  (1.0d + wpr);
        wi = wpi;
        n2p3 = 2*n+3;
        for (i = 2; i <= n/2; i++) {
            try{
                i4 = 1 + (i3 = n2p3 - (i2 = 1 + ( i1 = i + i - 1)));
                h1r =  c1*(data[i1] + data[i3]);
                h1i =  c1*(data[i2] - data[i4]);
                h2r = -c2*(data[i2] + data[i4]);
                h2i =  c2*(data[i1] - data[i3]);
                data[i1] = (h1r + wr * h2r - wi * h2i);
                data[i2] = (h1i + wr * h2i + wi * h2r);
                data[i3] = (h1r - wr * h2r + wi * h2i);
                data[i4] = (-h1i + wr * h2i + wi * h2r);
                wr = (wtemp = wr)*wpr - wi*wpi+wr;
                wi = wi*wpr + wtemp*wpi + wi;
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }

        }
        if (isign == 1) {
            data[1] = (h1r = data[1]) + data[2];
            data[2] = h1r - data[2];
        } else {
            data[1] = c1*((h1r = data[1]) + data[2]);
            data[2] = c1*(h1r - data[2]);
            four1(data, n, -1);
        }
    }

    public static void four1(double[] data, int nn, int isign){


        int nf, mmax, mf, j, istep, i;
        double wtemp, wr, wpr, wpi, wi, theta;
        double tempr, tempi;

        nf = nn << 1;
        j = 1;
        for (i = 1; i < nf; i += 2) {
            if (j > i) {
                tempr = data[j];
                data[j] = data[i];
                data[i] = tempr;
                tempr = data[j+1];
                data[j+1] = data[i+1];
                data[i+1] = tempr;
            }
            mf = nf >> 1;
            while (mf >= 2 && j > mf) {
                j -= mf;
                mf >>= 1;
            }
            j += mf;
        }
        mmax = 2;
        while (nf > mmax) {
            istep = 2*mmax;
            theta = TWOPI/(isign*mmax);
            wtemp = Math.sin(0.5d*theta);
            wpr = (-2.0d * wtemp * wtemp);
            wpi = Math.sin(theta);
            wr = 1.0d;
            wi = 0.0d;
            for (mf = 1; mf < mmax; mf += 2) {
                for (i = mf; i <= nf; i += istep) {
                    j =i + mmax;

                   try{
                        tempr = (wr * data[j] - wi * data[j + 1]);
                        tempi = (wr * data[j + 1] + wi * data[j]);
                        data[j]   = data[i]   - tempr;
                        data[j+1] = data[i+1] - tempi;
                        data[i] += tempr;
                        data[i+1] += tempi;
                    }catch(Exception ex){
                        System.out.println(ex.getMessage());
                    }
                }
                wr = (wtemp = wr)*wpr - wi*wpi + wr;
                wi = wi*wpr + wtemp*wpi + wi;
            }
            mmax = istep;
        }
    }

    }
