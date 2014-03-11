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
public class Windows {

static double wsum = 0;
/* See Oppenheim & Schafer, Digital Signal Processing, p. 241 (1st ed.) */
static final int BARTLETT     = 1;
static double win_bartlett(int j,int n) {
    double a = 2.0d/(n-1d), w;
    if ((w = j*a) > 1.0d)
        w = 2.0d - w;
    wsum += w;
    return (w);
}

/* See Oppenheim & Schafer, Digital Signal Processing, p. 242 (1st ed.) */
static final int BLACKMAN     = 4;
static double win_blackman(int j,int n) {
    double a = (2.0d * Math.PI / (n - 1d)), w;
    w = (0.42d - 0.5d * Math.cos(a * j) + 0.08d * Math.cos(2d * a * j));
    wsum += w;
    return (w);
}

/* See Harris, F.J., "On the use of windows for harmonic analysis with the
   discrete Fourier transform", Proc. IEEE, Jan. 1978 */
static final int BLACKMAN_HARRIS     = 6;
static double win_blackman_harris(int j,int n){
    double a = (2.0d * Math.PI / (n - 1d)), w;
    w = (0.35875d - 0.48829d * Math.cos(a * j) + 0.14128d * Math.cos(2 * a * j) - 0.01168d * Math.cos(3d * a * j));
    wsum += w;
    return (w);
}

/* See Oppenheim & Schafer, Digital Signal Processing, p. 242 (1st ed.) */
static final int HAMMING      = 3;
static double win_hamming(int j,int n){
    double a = (2.0d * Math.PI / (n - 1d)), w;

    w =  (0.54d - 0.46d * Math.cos(a * j));
    wsum += w;
    return (w);
}

/* See Oppenheim & Schafer, Digital Signal Processing, p. 242 (1st ed.)
   The second edition of Numerical Recipes calls this the "Hann" window. */
static final int HANNING      = 2;
static double win_hanning(int j,int n){
    double a =  (2.0 * Math.PI / (n - 1)), w;

    w =  (0.5 - 0.5 * Math.cos(a * j));
    wsum += w;
    return (w);
}

/* See Press, Flannery, Teukolsky, & Vetterling, Numerical Recipes in C,
   p. 442 (1st ed.) */
static final int PARZEN  = 7;
static double win_parzen(int j,int n){
    double a = ((n - 1) / 2.0d), w;

    if ((w = (j-a)/(a+1)) > 0.0d)
        w = 1 - w;
    else
        w = 1 + w;
    wsum += w;
    return (w);
}

/* See any of the above references. */
static final int SQUARE  = 0;
static double win_square(int j,int n){
    wsum += 1.0d;
    return 1.0d;
}

/* See Press, Flannery, Teukolsky, & Vetterling, Numerical Recipes in C,
   p. 442 (1st ed.) or p. 554 (2nd ed.) */
static final int WELCH     = 5;
static double win_welch(int j,int n){
    double a =  ((n - 1) / 2.0d), w;

    w = (j-a)/(a+1d);
    w = 1d - w*w;
    wsum += w;
    return (w);
}

static int windowType = 0;  // defaults to rectangular window

static void setWindowType(String w) {
if (w.equals("Dirichlet") || w.equals("Square"))
    windowType = SQUARE;
if (w.equals("Bartlett"))
    windowType = BARTLETT;
if (w.equals("Hanning"))
    windowType = HANNING;
if (w.equals("Hamming"))
    windowType = HAMMING;
if (w.equals("Blackman"))
    windowType = BLACKMAN;
if (w.equals("Welch"))
    windowType = WELCH;
if (w.equals("Blackman Harris"))
    windowType = BLACKMAN_HARRIS;
if (w.equals("Parzen"))
    windowType = PARZEN;
}


public static double apply(double[] c, int m, String type){
    wsum = 0;

    setWindowType(type);
    for (int i = 0; i < m; i++){
            switch (windowType) {
              case BARTLETT: // Bartlett (triangular) window
                    c[i] *= win_bartlett(i, m);
                break;
              case WELCH: // Bartlett (triangular) window
                    c[i] *= win_welch(i, m);
                break;
              case HANNING: // Hanning window
                    c[i] *= win_hanning(i, m);
                break;
              case HAMMING: // Hamming window
                    c[i] *= win_hamming(i, m);
                break;
              case BLACKMAN: // Blackman window
                    c[i] *= win_blackman(i, m);
                break;
              case BLACKMAN_HARRIS: // Blackman window
                    c[i] *= win_blackman_harris(i, m);
                break;
              case PARZEN: // Blackman window
                    c[i] *= win_parzen(i, m);
                break;
              case SQUARE: // Blackman window
                    c[i] *= win_square(i, m);
                break;
              default: // Rectangular window function

            }
        }

    return wsum;

}


}
