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
lomb transforms a real-valued time series (from the specified input-file, or from the standard input if input-file is specified as "-"; input-file must be in text form) into a power spectrum (on the standard output), using a technique known as the Lomb periodogram.

The input is a text file containing a sampled time series, presented as two columns of numbers (the sample times and the sample values). The intervals between consecutive samples need not be uniform (in fact, this is the most significant advantage of the Lomb periodogram over other methods of power spectral density estimation). lomb writes the Lomb periodogram (the power spectral density estimate derived from the input time series) on the standard output, in two columns (frequency and power). If the units of the sample times in the input file are seconds, the units of the frequencies in the output are Hz.

Options are:

-h
    Print a usage summary.
-P
    Generate a power spectrum (print squared magnitudes).
-s
    Smooth the output.
-z
    Add a constant to each input sample, chosen such that the mean value of the entire series is zero.

 */
/* file: lomb.c		G. Moody	12 February 1992
			Last revised:	  27 July 2010
-------------------------------------------------------------------------------
lomb: Lomb periodogram of real data
Copyright (C) 1992-2010 George B. Moody

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

The default input to this program is a text file containing a sampled time
series, presented as two columns of numbers (the sample times and the sample
values).  The intervals between consecutive samples need not be uniform (in
fact, this is the most significant advantage of the Lomb periodogram over other
methods of power spectral density estimation).  This program writes the Lomb
periodogram (the power spectral density estimate derived from the input time
series) on the standard output.

The original version of this program was based on the algorithm described in
Press, W.H, and Rybicki, G.B., Astrophysical J. 338:277-280 (1989).  It has
been rewritten using the versions of this algorithm presented in Numerical
Recipes in C (Press, Teukolsky, Vetterling, and Flannery;  Cambridge U. Press,
1992).

This version agrees with 'fft' output (amplitude spectrum up to the Nyquist
frequency with total power equal to the variance);  thanks to Joe Mietus.
*/
package com.sdk.connector.analysis;
 
/**
 * This code is a adaptation of physionet c code.
 * @author Diego Schmaedech
 * schmaedech@gmail.com
 * Brazil, 22/03/2011
 */
public final class PhyLomb {
    public static void main(String arg[]){
         double[] rr = {731,732,735,738,750,758,760,757,746,738,744,832,914,847,692,605,630,683,711,712,726,738,759,760,766,773,776,759,730,718,720,706,698,712,728,731,730,726,725,729,731,742,752,753,755,759,760,750,735,725,723,710,692,704,724,731,736,751,760,761,768,776,769,761,760,752,741,738,737,730,725,725,718,711,721,739,746,750,760,768,766,759,753,757,760,732,701,698,701,708,711,712,722,731,737,744,746,737,723,718,723,744,760,747,732,731,725,715,711,707,691,680,699,725,731,748,758,768,765,758,753,758,748,738,745,752,753,740,723,718,725,731,734,738,744,755,760,762,766,768,766,754,746,728,711,713,717,718,719,722,725,738,745,746,754,765,788,763,756,753,756,764,768,767,768,760,756,709,711,715,725,731,732,747,760,764,768,778,761,760,754,743,738,733,720,711,731,732,735,738,750,758,760,757,746,738,744,832,914,847,692,605,630,683,711,712,726,738,759,760,766,773,776,759,730,718,720,706,698,712,728,731,730,726,725,729,731,742,752,753,755,759,760,750,735,725,723,710,692,704,724,731,736,751,760,761,768,776,769,761,760,752,741,738,737,730,725,725,718,711,721,739,746,750,760,768,766,759,753,757,760,732,701,698,701,708,711,712,722,731,737,744,746,737,723,718,723,744,760,747,732,731,725,715,711,707,691,680,699,725,731,748,758,768,765,758,753,758,748,738,745,752,753,740,723,718,725,731,734,738,744,755,760,762,766,768,766,754,746,728,711,713,717,718,719,722,725,738,745,746,754,765,788,763,756,753,756,764,768,767,768,760,756,709,711,715,725,731,732,747,760,764,768,778,761,760,754,743,738,733,720,711,731,732,735,738,750,758,760,757,746,738,744,832,914,847,692,605,630,683,711,712,726,738,759,760,766,773,776,759,730,718,720,706,698,712,728,731,730,726,725,729,731,742,752,753,755,759,760,750,735,725,723,710,692,704,724,731,736,751,760,761,768,776,769,761,760,752,741,738,737,730,725,725,718,711,721,739,746,750,760,768,766,759,753,757,760,732,701,698,701,708,711,712,722,731,737,744,746,737,723,718,723,744,760,747,732,731,725,715,711,707,691,680,699,725,731,748,758,768,765,758,753,758,748,738,745,752,753,740,723,718,725,731,734,738,744,755,760,762,766,768,766,754,746,728,711,713,717,718,719,722,725,738,745,746,754,765,788,763,756,753,756,764,768,767,768,760,756,709,711,715,725,731,732,747,760,764,768,778,761,760,754,743,738,733,720,711,731,732,735,738,750,758,760,757,746,738,744,832,914,847,692,605,630,683,711,712,726,738,759,760,766,773,776,759,730,718,720,706,698,712,728,731,730,726,725,729,731,742,752,753,755,759,760,750,735,725,723,710,692,704,724,731,736,751,760,761,768,776,769,761,760,752,741,738,737,730,725,725,718,711,721,739,746,750,760,768,766,759,753,757,760,732,701,698,701,708,711,712,722,731,737,744,746,737,723,718,723,744,760,747,732,731,725,715,711,707,691,680,699,725,731,748,758,768,765,758,753,758,748,738,745,752,753,740,723,718,725,731,734,738,744,755,760,762,766,768,766,754,746,728,711,713,717,718,719,722,725,738,745,746,754,765,788,763,756,753,756,764,768,767,768,760,756,709,711,715,725,731,732,747,760,764,768,778,761,760,754,743,738,733,720,711};
      // double[] rr = {731,732,735,738,75,758,76,757,746,738,744,832,914,847,692,605,63,683,711,712,726,738,759,76,766,773,776,759,73,718,72,706,698,712,728,731,73,726,725,729,731,742,752,753,755,759,76,75,735,725,723,71,692,704,724,731,736,751,76,761,768,776,769,761,76,752,741,738,737,73,725,725,718,711,721,739,746,75,76,768,766,759,753,757,76,732,701,698,701,708,711,712,722,731,737,744,746,737,723,718,723,744,76,747,732,731,725,715,711,707,691,68,699,725,731,748,758,768,765,758,753,758,748,738,745,752,753,74,723,718,725,731,734,738,744,755,76,762,766,768,766,754,746,728,711,713,717,718,719,722,725,738,745,746,754,765,788,763,756,753,756,764,768,767,768,76,756,709,711,715,725,731,732,747,76,764,768,778,761,76,754,743,738,733,72,711};
        //./lomb -P -s -
        double[] tt = new double[rr.length];
        for(int u = 0;u< rr.length; u++){
            tt[u]  = u+1;
        }

        PhyLomb phyLomb = new PhyLomb(tt,rr,true, true, true, true);
    }

     float TWOPI = (float) (2.0f*Math.PI);
    int MACC = 4;
    double pwr;
    double[] x, y, wk1, wk2;
    int nmax = 512;	/* Initial buffer size (must be a power of 2).
                               Note that input() will increase this value as
                               necessary by repeated doubling, depending on
                               the length of the input series. */
    int i;
    double[] times = null;
    double[] values = null;

    double[] frequency;
    double[] power;

    boolean zeromean;
    boolean detrend;
    boolean plotPower;
    boolean smooth;


    public PhyLomb(double[] times, double[] values, boolean smooth, boolean plotPower, boolean zeromean, boolean detrend){
        double[] tt = new double[values.length];
        if(times == null){        
            for(int u = 0; u < tt.length; u++){
                tt[u]  = u;
            }
            times = tt;
        }else{

            for(int u = 0; u < times.length; u++){
                 
                times[u]  =  times[u]/1000d;
                
            }
        }
        this.times = times;
        this.values = values;
        this.plotPower = plotPower;
        this.smooth = smooth;
        this.zeromean = zeromean;
        this.detrend = detrend;

        int n, maxout;
        double[] nout = new double[1];
        double[] jmax = new double[1];
        double[] prob = new double[1];

        /* Read input. */
        n = input();

        /* Zero-mean the input if requested. */
        if (this.zeromean)
            Statistics.zeromean(y,n);

        if (this.detrend)
            Statistics.detrend(y,n);

    //    double[] xdata = new double[x.length];
    //    double[] ydata = new double[y.length];
    //    double[] wk1data = new double[wk1.length];
    //    double[] wk2data = new double[wk2.length];
    //    xdata[0] = 0;
    //    ydata[0] = 0;
    //    wk1data[0] = 0;
    //    wk2data[0] = 0;
    //    for(int p = 1; p< x.length-1;p++){
    //        xdata[p] = x[p-1];
    //        ydata[p] = y[p-1];
    //    }
    //
    //    for(int p = 1; p< wk1.length-1;p++){
    //        wk1data[p] = wk1[p-1];
    //        wk2data[p] = wk2[p-1];
    //    }

        /* Compute the Lomb periodogram. */


        int nfreqt = (int) (4.0 * 2.0 * n * MACC);
        int nfreq = 64;
        while (nfreq < nfreqt){
            nfreq <<= 1;
        }
        int ndim = nfreq << 1;

        wk1 = new double[ndim+1];
        wk2 = new double[ndim+2];

        fasper(x, y, n, 4.0, 2.0, wk1, wk2, 64*nmax, nout, jmax, prob);

        for(int p = 0; p< x.length-1;p++){
            x[p] = x[p+1];
            y[p] = x[p+1];
        }
        for(int p = 0; p< wk1.length-1;p++){
            wk1[p] = wk1[p+1];
            wk2[p] = wk2[p+1];
        }
    //    x[n] = x[1];		/* unpack the output array */
    //    x[1] = x[n+1] = 0f;
    //    y[n] = y[1];		/* unpack the output array */
    //    y[1] = y[n+1] = 0f;
    //    wk1[64*n] = wk1[1];		/* unpack the output array */
    //    wk1[1] = wk1[64*n+1] = 0f;
    //    wk2[64*n] = wk2[1];		/* unpack the output array */
    //    wk2[1] = wk2[64*n+1] = 0f;
        /* Write the results.  Output only up to Nyquist frequency, so that the
           results are directly comparable to those obtained using conventional
           methods.  The normalization is by half the number of output samples; the
           sum of the outputs is (approximately) the mean square of the inputs.

           Note that the Nyquist frequency is not well-defined for an irregularly
           sampled series.  Here we use half of the mean sampling frequency, but
           the Lomb periodogram can return (less reliable) estimates of frequency
           content for frequencies up to half of the maximum sampling frequency in
           the input.  */

        maxout = (int) (nout[0] / 2);
        frequency = new double[maxout];
        power = new double[maxout];

        if (this.smooth) {        /* smoothed */

            pwr /= 4;
           // System.out.println("nout[0] "+nout[0]);
           
            if (!plotPower) {     /* smoothed amplitudes */
                for (n = 0; n < maxout; n += 4){
                   // System.out.println(wk1[n]+ "\t"+ Math.sqrt((wk2[n]+wk2[n+1]+wk2[n+2]+wk2[n+3])/(nout[0]/(8.0*pwr))));
                    frequency[n] = wk1[n];
                    power[n] = Math.sqrt((wk2[n]+wk2[n+1]+wk2[n+2]+wk2[n+3])/(nout[0]/(8.0*pwr)));
                }
            } else {           /* smoothed powers */
                //for (n = 0; n < maxout; n += 4){
                for (n = 0; n < maxout; n += 4){
                  // System.out.println("wk2[p]: " + wk2[n]);
                  //  System.out.println(wk1[n]+"\t"+ (wk2[n]+wk2[n+1]+wk2[n+2]+wk2[n+3])/(nout[0]/(8.0*pwr)));
                    frequency[n] = wk1[n];
                    power[n] = (wk2[n]+wk2[n+1]+wk2[n+2]+wk2[n+3])/(nout[0]/(8.0*pwr));
                }
            }
        } else {    	        /* oversampled */

            if (!plotPower)      /* amplitudes */
                for (n = 0; n < maxout; n++){
                  //  System.out.println(wk1[n]+ "\t"+ Math.sqrt(wk2[n]/(nout[0]/(2.0*pwr))));
                    frequency[n] = wk1[n];
                    power[n] = Math.sqrt(wk2[n]/(nout[0]/(2.0*pwr)));
                }

            else            /* powers */
                for (n = 0; n < maxout; n++){
                 //   System.out.println(wk1[n]+ "\t"+ wk2[n]/(nout[0]/(2.0*pwr)));
                    frequency[n] = wk1[n];
                    power[n] = wk2[n]/(nout[0]/(2.0*pwr));

                }

        }


    }

    double MOD(double a, double b){
            while (a >= b)
                a -= b;
            
            return a;
    }

    double SIGN(double a, double b){
        return (b) > 0.0 ? Math.abs(a) : -Math.abs(a);
    }



    void fasper(double[] x,double[] y, int n, double ofac, double hifac, double[] wk1,double[] wk2, int nwk, double[] nout,double[] jmax, double[] prob){

        int j, k, ndim=0, nfreq, nfreqt;
        double ck, ckk, cterm, cwt, den, df, effm, expy, fac, fndim, hc2wt,
              hs2wt, hypo, pmax, sterm, swt, xdif, xmax, xmin;

        double[] ave = new double[1];
        double[] var = new double[1];
        nout[0] =  (0.5 * ofac * hifac * n);
       // System.out.println(" nout[0] : "+  nout[0] );
        nfreqt = (int) (ofac * hifac * n * MACC);
        nfreq = 64;
        while (nfreq < nfreqt){
            nfreq <<= 1;
        }
       // System.out.println("nfreq: "+  nfreq );
        ndim = nfreq << 1;
       // System.out.println("ndim: "+ ndim);
        if (ndim > nwk){
            System.out.println("workspaces too small\n");
        }


        pwr = avevar(y, n, ave, var);
        xmax = xmin = x[1];
        for (j = 2; j <= n; j++) {
            if (x[j] < xmin)
                xmin = x[j];
            if (x[j] > xmax)
                xmax = x[j];
        }
        xdif = xmax - xmin;
        //System.out.println("ndim: "+ ndim);
        for (j = 1; j <= ndim; j++){
            wk1[j] = wk2[j] = 0.0;
        }
       // System.out.println("j : "+  j);
        fac = ndim/(xdif*ofac);
        fndim = ndim;
        for (j = 1; j <= n; j++) {
            ck = (x[j] - xmin)*fac;
            MOD(ck, fndim);
            ckk = 2.0*(ck++);
            MOD(ckk, fndim);
            ++ckk;
            spread(y[j] - ave[0], wk1, ndim, ck, MACC);
            spread(1.0, wk2, ndim, ckk, MACC);
            //System.out.println("y[j] - ave[0]: "+ (y[j] - ave[0]));
        }
        FrequencyDomain.realft(wk1, ndim/2, 1);
        FrequencyDomain.realft(wk2, ndim/2, 1);
        df = 1.0/(xdif*ofac);
        pmax = -1.0;
        for (k = 3, j = 1; j <= nout[0]; j++, k += 2) {
            hypo = Math.sqrt(wk2[k]*wk2[k] + wk2[k+1]*wk2[k+1]);
            hc2wt = 0.5*wk2[k]/hypo;
            hs2wt = 0.5*wk2[k+1]/hypo;
            cwt = Math.sqrt(0.5+hc2wt);
            swt = SIGN(Math.sqrt(0.5-hc2wt), hs2wt);
            den = 0.5*n + hc2wt*wk2[k] + hs2wt*wk2[k+1];
            cterm = Math.pow(((cwt*wk1[k] + swt*wk1[k+1])/den), 2);
            sterm = Math.pow((cwt*wk1[k+1] - swt*wk1[k])/(n - den), 2);
            wk1[j] = j*df;
            wk2[j] = (cterm+sterm)/(2.0*var[0]);
            if (wk2[j] > pmax) {
                jmax[0] = j;
                pmax = wk2[j];
            }
        }
        expy = Math.exp(-pmax);
        effm = 2.0*(nout[0])/ofac;
        prob[0] = effm*expy;
        if (prob[0] > 0.01)
            prob[0] = 1.0 - Math.pow(1.0 - expy, effm);
    }

    void spread(double y,double[] yy,int n,double x,int m){
        int ihi, ilo, ix, j, nden;
        int[] nfac = { 0, 1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880 };
        double fac;


        if (m > 10)
            System.out.println("factorial table too small");
        ix = (int)x;
        if (x == (double)ix){
            yy[ix] += y;
            //System.out.println("yy[ix]: "+yy[ix]);
        }else {
            ilo =  (int) Math.min(Math.max((x - 0.5*m + 1.0), 1), n - m + 1);
            //System.out.println("ilo: "+ilo);
            ihi = ilo + m - 1;
            nden = nfac[m];
            fac = x - ilo;
            for (j = ilo + 1; j <= ihi; j++) {
                fac *= (x - j);
            }
            yy[ihi] += y*fac/(nden*(x - ihi));
            for (j = ihi-1; j >= ilo; j--) {
                nden = (nden/(j + 1 - ilo))*(j - ihi);
                yy[j] += y*fac/(nden*(x - j));
            }
            //System.out.println("fac: "+fac);
        }
    }

    double avevar(double[] data, int n, double[] ave, double[] var){
        int j;
        double s, ep;

        for (ave[0] = 0.0, j = 1; j <= n; j++)
            ave[0] += data[j];

        ave[0] /= n;
        var[0] = ep = 0.0;
        for (j = 1; j <= n; j++) {
            s = data[j] - ave[0];
            ep += s;
            var[0] += s*s;
        }
        var[0] = (var[0] - ep*ep/n)/(n-1);
        return var[0];
    }

    int input(){

        while(times.length >= nmax ){
            nmax = nmax << 1;
        }
        //System.out.println("nmax: " + nmax);
        x = new double[nmax];
        y = new double[nmax];

        x[0]=0;
        y[0]=0;
        for(int u = 0; u < nmax;u++){
            if(u < times.length){
                x[u+1]=times[u];
                y[u+1]=values[u];
            }

        }
       // System.out.println("wk1: " + wk1.length);
        return ((times.length));
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
 
 
    /* This function calculates the mean of all sample values and subtracts it
       from each sample value, so that the mean of the adjusted samples is zero. */

//    void zeromean(int n) {
//        int l;
//        double ysum = 0.0;
//
//        for (l = 0; l < n; l++)
//            ysum += y[l];
//        ysum /= n;
//        for (l = 0; l < n; l++)
//            y[l] -= ysum;
//    }
//
//    void realft(double[] data, int n,int isign){
//    //    for (int h = 0; h < n; h++){
//    //	    System.out.println( "h: "+ h + "\t"+ data[h]);
//    //        }
//        int ii, i1, i2, i3, i4, np3;
//        double c1 = 0.5f, c2, h1r, h1i, h2r, h2i;
//        double wr, wi, wpr, wpi, wtemp, theta;
//
//
//        theta = (double) (Math.PI / (double)( n>>1));
//        if (isign == 1) {
//            c2 = -0.5f;
//            four1(data, n>>1, 1);
//        }
//        else {
//            c2 = 0.5f;
//            theta = -theta;
//        }
//        wtemp = (double) Math.sin(0.5*theta);
//        wpr = (double) (-2.0 * wtemp * wtemp);
//        wpi = (double) Math.sin(theta);
//        wr = (double) (1.0 + wpr);
//        wi = wpi;
//        np3 = n+3;
//        for (ii = 2; ii <= (n>>2); ii++) {
//            try{
//                i4 = 1 + (i3 = np3 - (i2 = 1 + ( i1 = ii + ii - 1)));
//                h1r =  c1*(data[i1] + data[i3]);
//                h1i =  c1*(data[i2] - data[i4]);
//                h2r = -c2*(data[i2] + data[i4]);
//                h2i =  c2*(data[i1] - data[i3]);
//                data[i1] =  (double) (h1r + wr * h2r - wi * h2i);
//                data[i2] =  (double) (h1i + wr * h2i + wi * h2r);
//                data[i3] =  (double) (h1r - wr * h2r + wi * h2i);
//                data[i4] = (double) (-h1i + wr * h2i + wi * h2r);
//                wr = (wtemp = wr)*wpr - wi*wpi+wr;
//                wi = wi*wpr + wtemp*wpi + wi;
//            }catch(Exception ex){
//                System.out.println(ex.getMessage());
//            }
//
//        }
//        if (isign == 1) {
//            data[1] = (h1r = data[1]) + data[2];
//            data[2] = h1r - data[2];
//        } else {
//            data[1] = c1*((h1r = data[1]) + data[2]);
//            data[2] = c1*(h1r - data[2]);
//            four1(data, n>>1, -1);
//        }
//    }
//
//    void four1(double[] data, int nn, int isign){
//
//
//        int nf, mmax, mf, j, istep, ii;
//        double wtemp, wr, wpr, wpi, wi, theta;
//        double tempr, tempi;
//
//        nf = nn << 1;
//        j = 1;
//        for (ii = 1; ii < nf; ii += 2) {
//            if (j > ii) {
//                tempr = data[j];
//                data[j] = data[ii];
//                data[ii] = tempr;
//                tempr = data[j+1];
//                data[j+1] = data[ii+1];
//                data[ii+1] = tempr;
//            }
//            mf = nf >> 1;
//            while (mf >= 2 && j > mf) {
//                j -= mf;
//                mf >>= 1;
//            }
//            j += mf;
//        }
//        mmax = 2;
//        while (nf > mmax) {
//            istep = mmax << 1;
//            theta = isign*(TWOPI/mmax);
//            wtemp = (double) Math.sin(0.5*theta);
//            wpr = (double) (-2.0 * wtemp * wtemp);
//            wpi = (double) Math.sin(theta);
//            wr = (double) 1.0;
//            wi = (double) 0.0;
//            for (mf = 1; mf < mmax; mf += 2) {
//                for (ii = mf; ii <= nf; ii += istep) {
//                    j =ii + mmax;
//                        //System.out.println("j + 1" +j + 1);
//                    try{
//                        tempr = (double) (wr * data[j] - wi * data[j + 1]);
//                        tempi = (double) (wr * data[j + 1] + wi * data[j]);
//                        data[j]   = data[ii]   - tempr;
//                        data[j+1] = data[ii+1] - tempi;
//                        data[ii] += tempr;
//                        data[ii+1] += tempi;
//                    }catch(Exception ex){
//                        System.out.println(ex.getMessage());
//                    }
//                }
//                wr = (wtemp = wr)*wpr - wi*wpi + wr;
//                wi = wi*wpr + wtemp*wpi + wi;
//            }
//            mmax = istep;
//        }
//    }


}
