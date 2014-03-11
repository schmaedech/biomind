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

/*
 memse transforms a real-valued time series (from the specified input-file, or from the standard input if input-file is specified as ‘‘-’’; input-file must be in text form) into a power spectrum (on the standard output). memse is designed to be used in much the same way as fft(1) ; it accepts the same input, produces output in the same format, and accepts many of the same options used with fft.

Unlike fft, which bases its spectral estimates on the discrete Fourier transform, memse uses the maximum entropy (all poles) method, also known as autoregressive (AR) spectral estimation. This method models the spectrum by a series expansion in which the free parameters are all in the denominators of its terms; hence each term may represent a pole (corresponding to infinite power spectral density within an infinitely narrow frequency band). By contrast, Fourier analysis models the spectrum by a series expansion in which the free parameters are all in the numerators; hence each term in a Fourier series may represent a zero. All-poles models are particularly useful for analysis of spectra which have discrete peaks (in the terminology of optical spectra, ‘‘lines’’).

In order to use memse, you should have some idea of the order of the model you wish to use (i.e., the number of poles). Although this may be any number up to the number of input points, the number of poles generally should not exceed the square root of the number of input points, and usually should be considerably less than that number. Large numbers of poles lead to lengthy computations (much slower than the FFT) in which accumulated roundoff error becomes a serious problem. This problem may also occur if the length of the input series becomes excessive. The recommended way to use memse is to begin by using fft, in order to estimate the model order. Typically this should be a small multiple of the number of peaks which you believe are present. Beware! memse will produce smooth spectral estimates for whatever model order you choose -- and they may be totally bogus if you choose incorrectly. Varying the model order can help to weed out some spurious features, but use extreme care when interpreting memse output given noisy input.

Options are:

-b low high [ low high ... ]
    Print power in the specified bands. Each low and high pair specifies the low and high frequency boundaries of the band of interest, in Hz. Multiple bands may be specified following a single -b option; only the last -b option has any effect. Also see -s below.
-f frequency
    Show the center frequency for each bin in the first column. The frequency argument specifies the input sampling frequency; the center frequencies are given in the same units.
-h
    Print a usage summary.
-n n
    Produce exactly n power estimates, evenly spaced in frequency from 0 up to half the input sampling frequency inclusive. The default depends on the length of the input series; it is designed to match fft’s defaults, to make it easy to compare outputs. You may wish to use values of n which are higher than the default in order to improve your estimates of the locations of sharp features in the spectrum; since this is not possible using fft, this feature is one of the main advantages of memse.
-o n
    Use an nth order model (i.e., up to n poles). Default: the square root of the number of input samples.
-P
    Generate a power spectrum (print squared magnitudes).
-s
    Print power in a standard set of frequency bands of interest for HRV analysis.
-w window-type
    Apply the specified window to the input data. window-type may be one of: ‘Bartlett’, ‘Blackman’, ‘Blackman-Harris’, ‘Hamming’, ‘Hanning’, ‘Parzen’, ‘Square’, and ‘Welch’. The ‘Square’ window type is equivalent to using no window at all; this is also variously known as a rectangular or Dirichlet window.
-z
    Add a constant to each input sample, chosen such that the mean value of the entire series is zero.
-Z
    Set the mean value of the inputs to zero as for -z, and detrend the series (set its mean first derivative to zero). This is equivalent to subtracting a best-fit (by least squares) line from the input data. 
 */

package com.sdk.connector.analysis;

/**
 *
 * @author diego
 */
public final class PhyMemse {
public static void main(String arg[]){
     double[] rr = {731,732,735,738,750,758,760,757,746,738,744,832,914,847,692,605,630,683,711,712,726,738,759,760,766,773,776,759,730,718,720,706,698,712,728,731,730,726,725,729,731,742,752,753,755,759,760,750,735,725,723,710,692,704,724,731,736,751,760,761,768,776,769,761,760,752,741,738,737,730,725,725,718,711,721,739,746,750,760,768,766,759,753,757,760,732,701,698,701,708,711,712,722,731,737,744,746,737,723,718,723,744,760,747,732,731,725,715,711,707,691,680,699,725,731,748,758,768,765,758,753,758,748,738,745,752,753,740,723,718,725,731,734,738,744,755,760,762,766,768,766,754,746,728,711,713,717,718,719,722,725,738,745,746,754,765,788,763,756,753,756,764,768,767,768,760,756,709,711,715,725,731,732,747,760,764,768,778,761,760,754,743,738,733,720,711,731,732,735,738,750,758,760,757,746,738,744,832,914,847,692,605,630,683,711,712,726,738,759,760,766,773,776,759,730,718,720,706,698,712,728,731,730,726,725,729,731,742,752,753,755,759,760,750,735,725,723,710,692,704,724,731,736,751,760,761,768,776,769,761,760,752,741,738,737,730,725,725,718,711,721,739,746,750,760,768,766,759,753,757,760,732,701,698,701,708,711,712,722,731,737,744,746,737,723,718,723,744,760,747,732,731,725,715,711,707,691,680,699,725,731,748,758,768,765,758,753,758,748,738,745,752,753,740,723,718,725,731,734,738,744,755,760,762,766,768,766,754,746,728,711,713,717,718,719,722,725,738,745,746,754,765,788,763,756,753,756,764,768,767,768,760,756,709,711,715,725,731,732,747,760,764,768,778,761,760,754,743,738,733,720,711,731,732,735,738,750,758,760,757,746,738,744,832,914,847,692,605,630,683,711,712,726,738,759,760,766,773,776,759,730,718,720,706,698,712,728,731,730,726,725,729,731,742,752,753,755,759,760,750,735,725,723,710,692,704,724,731,736,751,760,761,768,776,769,761,760,752,741,738,737,730,725,725,718,711,721,739,746,750,760,768,766,759,753,757,760,732,701,698,701,708,711,712,722,731,737,744,746,737,723,718,723,744,760,747,732,731,725,715,711,707,691,680,699,725,731,748,758,768,765,758,753,758,748,738,745,752,753,740,723,718,725,731,734,738,744,755,760,762,766,768,766,754,746,728,711,713,717,718,719,722,725,738,745,746,754,765,788,763,756,753,756,764,768,767,768,760,756,709,711,715,725,731,732,747,760,764,768,778,761,760,754,743,738,733,720,711,731,732,735,738,750,758,760,757,746,738,744,832,914,847,692,605,630,683,711,712,726,738,759,760,766,773,776,759,730,718,720,706,698,712,728,731,730,726,725,729,731,742,752,753,755,759,760,750,735,725,723,710,692,704,724,731,736,751,760,761,768,776,769,761,760,752,741,738,737,730,725,725,718,711,721,739,746,750,760,768,766,759,753,757,760,732,701,698,701,708,711,712,722,731,737,744,746,737,723,718,723,744,760,747,732,731,725,715,711,707,691,680,699,725,731,748,758,768,765,758,753,758,748,738,745,752,753,740,723,718,725,731,734,738,744,755,760,762,766,768,766,754,746,728,711,713,717,718,719,722,725,738,745,746,754,765,788,763,756,753,756,764,768,767,768,760,756,709,711,715,725,731,732,747,760,764,768,778,761,760,754,743,738,733,720,711};
     //./memse -f 1 -P -w Welch -Z -


        PhyMemse fFT = new PhyMemse(rr, 1, 0 ,true,"Welch",true,true);
}


/* file: memse.c	G. Moody	6 February 1992
			Last revised:	 13 March 2006

-------------------------------------------------------------------------------
memse: Estimate power spectrum using maximum entropy (all poles) method
Copyright (C) 1992-2006 George B. Moody

This program is free software; you can redistribute it and/or modify it under
the terms of the GNU General Public License as published by the Free Software
Foundation; either version 2 of the License, or (at your option) any later
version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY
WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with
this program; if not, write to the Free Software Foundation, Inc., 59 Temple
Place - Suite 330, Boston, MA 02111-1307, USA.

You may contact the author by e-mail (george@mit.edu) or postal mail
(MIT Room E25-505A, Cambridge, MA 02139 USA).  For updates to this software,
please visit PhysioNet (http://www.physionet.org/).
_______________________________________________________________________________

This program has been written to behave as much like 'fft' as possible.  The
input and output formats and many of the options are the same.  See the man
page (memse.1) for details.

This version agrees with 'fft' output (amplitude spectrum with total power
equal to the variance);  thanks to Joe Mietus.

The function 'integ' (to compute total power over a band), and the code using
'integ' to summarize power in several bands of interest for HRV analysis, was
contributed by Peter P. Domitrovich.
*/


double wsum;
double[] data = null, wk1 = null, wk2 = null;
int nmax = 512;	/* Initial buffer size (must be a power of 2).
			   Note that input() will increase this value as
			   necessary by repeated doubling, depending on
			   the length of the input series. */
double[] pm = new double[1];
double[] cof = null;
double[] wkm = null;
 
int len = 0;
int nout = 0;
int poles = 0;
 
 
double freq = 0.0;
double[] input = null;


void printf(String str){
   // System.out.println(str);
}

void band_power(double f0, double f1){
    double f;
    int first_band = 1;

    if (f0 < 0.0)
        f0 = 0.0;
    if (f1 < 0.0)
        f1 = 0.0;
    if (f0 > f1) {
        f = f0;
        f0 = f1;
        f1 = f;
    }
    if (f0 == f1)
        return;
    if (first_band==1) {
	printf("\nModel order ="+poles+"\n");
	printf("     Band (Hz)\t\t  Power\n");
	first_band = 0;
    }
    printf(f0+ "-"+f1+"\t"+2 * integ(cof, poles, pm[0], f0, f1, 0.0000001) +"\n");
}

/* Function 'integ' was contributed by Peter P. Domitrovich, who translated it
   from a FORTRAN version by an unknown author from a book written in Chinese.
   This code is designed to integrate functions with sharp peaks. */
double integ(double[] aa, int m, double ee, double a, double b, double epsilon){
  double[][] f = new double[3][31];
  double[][] fm = new double[3][31];
  double[][] e = new double[3][31];
  double[] krtn = new double[31];

  double sum = 0.0, t = 1.0, absa = 1.0, est = 1.0, f1 = 0.0, fa = 0.0, fb = 0.0, fp = 0.0, x = a, da = b - a, dx = 0.0, sx = 0.0, fm1 = 0.0, e1 = 0.0, s = 0.0;
  int l = 0, k = 0;

  fa = evlmem(a, aa, m, ee);
  fb = evlmem(b, aa, m, ee);
  fp = 4.0 * evlmem(0.5 * (a + b), aa, m, ee);
  while (true) {
      k = 1;
      l++;
      t *= 1.7;
      dx = da / 3.0;
      sx = dx / 6.0;
      fm1 = 4.0 * evlmem(x + 0.5 * dx, aa, m, ee);
      f1 = evlmem(x + dx, aa, m, ee);
      fm[1][l] = fp;
      f[1][l] = evlmem(x + 2.0 * dx, aa, m, ee);
      fm[2][l] = 4.0 * evlmem (x + 2.5 * dx, aa, m, ee);
      f[2][l] = fb;
      e1 = sx * (fa + fm1 + f1);
      e[1][l] = sx * (f1 + fp + f[1][l]);
      e[2][l] = sx * (f[1][l] + fm[2][l] + fb);
      s = e1 + e[1][l] + e[2][l];
      absa = absa - Math.abs(est) + Math.abs(e1) + Math.abs(e[1][l]) + Math.abs(e[2][l]);
      if (Math.abs(est - 1.0) < 1.0e-06) {
	  est = e1;
	  fp = fm1;
	  fb = f1;
	  da = dx;
	  krtn[l] = k;
	  continue;
      }
      if (t * Math.abs (est - s) <= epsilon * absa) {
	  sum += s;
	  do {
	      l--;
	      t /= 1.7;
	      k =   (int) krtn[l];
	      dx *= 3.0;
	      if (k == 3 && l - 1 <= 0)
		  return sum;
	  } while (k == 3 && l - 1 > 0);
	  est = e[k][l];
	  fp = fm[k][l];
	  fa = fb;
	  fb = f[k][l];
	  k++;
	  x += da;
	  da = dx;
	  krtn[l] = k;
	  continue;
	}
      if (l < 30) {
	  est = e1;
	  fp = fm1;
	  fb = f1;
	  da = dx;
	  krtn[l] = k;
	  continue;
      }
      sum += 5.0;
      do {
	  l--;
	  t /= 1.7;
	  k =   (int) krtn[l];
	  dx *= 3.0;
	  if (k == 3 && l - 1 <= 0)
	      return sum;
      } while (k == 3 && l - 1 > 0);
      est = e[k][l];
      fp = fm[k][l];
      fa = fb;
      fb = f[k][l];
      k++;
      x += da;
      da = dx;
      krtn[l] = k;
  }
}
private double[] frequency;
private double[] power;
boolean zeromean;
boolean detrend;
public PhyMemse( double[] input, int freq, int poles, boolean plotPower, String win, boolean zeromean, boolean detrend){
    this.input = input;

    this.poles = poles;
    this.zeromean = zeromean;
    this.detrend = detrend;
  
    int i = 0;
    double df = 0.0,f = 0.0,p = 0.0;


    /* Read the input series. */
    len = input( );

    /* Check the model order. */
    if (this.poles > len)
        this.poles =  len;
    if ((double)this.poles*this.poles > len)
	printf("the model order (number of poles) may be too high");

    /* Set the model order to a reasonable value if it is unspecified. */
    if (this.poles == 0) {
	this.poles = (int)(Math.sqrt((double)len) + 0.5);
	printf("using a model order of "+this.poles);
    }

    /* Allocate arrays for coefficients. */
    cof = new double[this.poles];
    wkm = new double[this.poles];


    /* Zero-mean, detrend, and/or window the input series as required. */
    if (this.zeromean) {
	Statistics.zeromean(data, len); 
    }
    if (this.detrend){
        Statistics.detrend(data, len);
    }
    if (!win.equals(""))
	 wsum = Windows.apply(data, len, win);


    /* Calculate coefficients for MEM spectral estimation. */

    memcof(data, len, this.poles, pm, cof);

    /* If the number of output points was not specified, choose the largest
      power of 2 less than len, plus 1 (so that the number of output points
      matches that produced by an FFT). */
    if (nout == 0) {
	while (nmax >= len)
	    nmax /= 2;
	nout = nmax + 1;
    }

    frequency = new double[nout];
    power = new double[nout];
    /* Print outputs. */
    if (nout > 1)
       for (i = 0, df = 0.5/((double)(nout-1)); i < nout; i++) {
	   f = i*df*freq;
	   p = evlmem(i*df, cof, this.poles, pm[0])/((double)(nout-1));

	   
               //System.out.print(f+"\t" );
           frequency[i] = f;
           
	   if (plotPower){
	       //System.out.println(p);
                power[i] = p;
           }else{
                power[i] = Math.sqrt(p);
	       //System.out.println(Math.sqrt(p));
           }
       }
    else {
         printf("no output produced");
    }

    if (false) {
	band_power(0.0, 0.5);
	band_power(0.0, 0.0033);
	band_power(0.0033, 0.04);
	band_power(0.04, 0.15);
	band_power(0.15, 0.4);
	band_power(0.4, 0.5);
    }



}

/* Calculate coefficients for MEM spectral estimation.  See Numerical Recipes,
   pp. 447-451. */
void memcof(double[] data, int n, int m, double[] pm, double[] cof){
    int i = 0, j = 0, k = 0;
    double denom = 0.0, num = 0.0, p = 0.0;

    for (j = 0; j < n; j++){
	p += data[j]*data[j];
    }
    pm[0] = p/n;

    wk1[0] = data[0];
    wk2[n-2] = data[n-1];
    for (j = 1; j < n-1; j++) {
	wk1[j] = data[j];
	wk2[j-1] = data[j];
    }
    for (k = 0; k < m; k++) {
	for (j = 0, num = denom = 0.0; j < n-k-1; j++) {
	    num += wk1[j]*wk2[j];
	    denom += wk1[j]*wk1[j] + wk2[j]*wk2[j];
	}
	cof[k] = 2.0*num/denom;
	pm[0] *= (1.0 - cof[k]*cof[k]);
      //  System.out.println("pm: "+ pm[0]);
	if (k>0){

	    for (i = 0; i < k; i++){
		cof[i] = wkm[i] - cof[k]*wkm[k-i-1];
            }
        }
	if (k != m-1) {
          //  System.out.println("k != m-1: "+ k);
	    for (i = 0; i <= k; i++)
		wkm[i] = cof[i];
	    for (j = 0; j < n-k-2; j++) {
		wk1[j] -= wkm[k]*wk2[j];
		wk2[j] = wk2[j+1] - wkm[k]*wk1[j+1];
	    }
	}
    }

}

/* Evaluate power spectral estimate at f (0 <= f < = 0.5, where 1 is the
   sampling frequency), given MEM coefficients in cof[0 ... m-1] and pm
   (see Numerical Recipes, pp. 451-452). */
double evlmem(double f, double[] cof, int m, double pm){
    int i = 0;
    double sumr = 1.0, sumi = 0.0;
    double wr = 1.0, wi = 0.0, wpr = 0.0, wpi = 0.0, wt = 0.0, theta = 0.0;

    theta = 2.0*Math.PI*f;
    wpr = Math.cos(theta);
    wpi = Math.sin(theta);
    for (i = 0; i < m; i++) {
	wt = wr;
	sumr -= cof[i]*(wr = wr*wpr - wi*wpi);
	sumi -= cof[i]*(wi = wi*wpr + wt*wpi);
    }
    return (pm/(sumr*sumr+sumi*sumi));
}


/* Read input data, allocating and filling x[] and y[].  The return value is
   the number of points read.

   This function allows the input buffers to grow as large as necessary, up to
   the available memory (assuming that a int int is large enough to address
   any memory location). */

int input( ){

    data = new double[input.length];
    wk1 = new double[64 * input.length];
    wk2 = new double[64 * input.length];
    System.arraycopy(input, 0, data, 0, input.length);

    return (input.length);
}
    /**
     * @return the frequency
     */
    public double[] getFrequency() {
        return frequency;
    }

    /**
     * @return the power
     */
    public double[] getPower() {
        return power;
    }

}
