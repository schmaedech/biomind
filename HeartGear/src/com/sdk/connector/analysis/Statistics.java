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
public class Statistics {

    public static float mean(Vector<Float> accumulator) {
         return mean(ArraysAndVectors.getFloatArray(accumulator));
    }
    public static float max(float[] a) {
        float max = Float.NEGATIVE_INFINITY;
        for (float f : a) {
            if (f > max) max = f;
        }
        return max;
    }

    public static double max(double[] a) {
        double max = Float.NEGATIVE_INFINITY;
        for (double f : a) {
            if (f > max)
                max = f;
        }
        return max;
    }
    /**
    * Return minimum value in array, +infinity if no such value.
    */
    public static float min(float[] a) {
        float min = Float.POSITIVE_INFINITY;
        for (float f : a) {
            if (f < min) min = f;
        }
        return min;
    }

    /**
    * Return average value in array, NaN if no such value.
    */
    public static float mean(float[] a) {
        return sum(a) / a.length;
    }

    /**
    * Return average value in array, NaN if no such value.
    */
    public static double mean(double[] a) {
        return sum(a) / a.length;
    }
    /**
    * Return sample variance of array, NaN if no such value.
    */
    public static float var(float[] a) {
        float avg = mean(a);
        float sum = 0f;
        for (float f : a) {
            sum += Math.pow((f - avg),2);
        }
        return sum / (a.length - 1);
    }

    /**
    * Return sample standard deviation of array, NaN if no such value.
    */
    public static float stddev(float[] a) {
        return (float) Math.sqrt(var(a));
    }

    /**
    * Return sum of all values in array.
    */
    public static float sum(float[] a) {
        float sum = 0f;
        for (float f : a) {
            sum += f;
        }

        return sum;
    }

    /**
    * Return sum of all values in array.
    */
    public static double sum(double[] a) {
        double sum = 0f;
        for (double f : a) {
            sum += f;
        }

        return sum;
    }
    /*
     * y = ax + b where result[0]=a and result[1] = b
     * and correlation r = result[2]
     */
    public static float[] resampleLinearRegression(float[] x, float[] y){
        float[] result = new float[]{0f,0f,0f};
        int n = x.length;
        float sumX = 0, sumY = 0,sumXY = 0, sumX2 = 0, sumY2 = 0;
        sumX = sum(x);
        sumY = sum(y);
        for(int i = 0; i < n; i++){
            sumXY += x[i]*y[i];
            sumX2 += Math.pow(x[i], 2);
            sumY2 += Math.pow(y[i], 2);
        }



        try{
            result[0] = (n*sumXY-sumX*sumY)/(n*sumX2-(float)Math.pow(sumX, 2));
            //result[1] = (sumY-result[0]*sumX)/n;
            //result[2] = ( n*sumXY-sumX*sumY )/( (float)Math.sqrt( (n*sumX2-(float)Math.pow(sumX, 2))*(n*sumY2-Math.pow(sumY,2)) ) );
        }catch(Exception ex){
            return new float[]{0f,0f,0f};
        }
        return result;
    }

    private static void solveTridiag(float sub[], float diag[], float sup[], float b[], int n){
        /*                  solve linear system with tridiagonal n by n matrix a
                    using Gaussian elimination *without* pivoting
                    where   a(i,i-1) = sub[i]  for 2<=i<=n
                            a(i,i)   = diag[i] for 1<=i<=n
                            a(i,i+1) = sup[i]  for 1<=i<=n-1
                    (the values sub[1], sup[n] are ignored)
                    right hand side vector b[1:n] is overwritten with solution
                    NOTE: 1...n is used in all arrays, 0 is unused */
        int i;
        /*                  factorization and forward substitution */
        for(i=2; i<=n; i++){
            sub[i] = sub[i]/diag[i-1];
            diag[i] = diag[i] - sub[i]*sup[i-1];
            b[i] = b[i] - sub[i]*b[i-1];
        }
        b[n] = b[n]/diag[n];
        for(i=n-1;i>=1;i--){
            b[i] = (b[i] - sup[i]*b[i+1])/diag[i];
        }
    }

    private static void solveTridiag(double sub[], double diag[], double sup[], double b[], int n){
        /*                  solve linear system with tridiagonal n by n matrix a
                    using Gaussian elimination *without* pivoting
                    where   a(i,i-1) = sub[i]  for 2<=i<=n
                            a(i,i)   = diag[i] for 1<=i<=n
                            a(i,i+1) = sup[i]  for 1<=i<=n-1
                    (the values sub[1], sup[n] are ignored)
                    right hand side vector b[1:n] is overwritten with solution
                    NOTE: 1...n is used in all arrays, 0 is unused */
        int i;
        /*                  factorization and forward substitution */
        for(i=2; i<=n; i++){
            sub[i] = sub[i]/diag[i-1];
            diag[i] = diag[i] - sub[i]*sup[i-1];
            b[i] = b[i] - sub[i]*b[i-1];
        }
        b[n] = b[n]/diag[n];
        for(i=n-1;i>=1;i--){
            b[i] = (b[i] - sup[i]*b[i+1])/diag[i];
        }
    }
 
    public static float[][] resampleBicubic(float[] x, float[] d, int precision ) {
            float[][] interpol = new float[2][0];
            int np = x.length;
            float y;
            float t;
            float a[] = new float[np];
            float t1;
            float t2;
            float h[] = new float[np];
            for (int i=1; i<=np-1; i++){
                h[i] = x[i] - x[i-1];
            }

            float sub[] = new float[np-1];
            float diag[] = new float[np-1];
            float sup[] = new float[np-1];

            for (int i=1; i<=np-2; i++){
                diag[i] = (h[i] + h[i+1])/3;
                sup[i] = h[i+1]/6;
                sub[i] = h[i]/6;
                a[i] = (d[i+1]-d[i])/h[i+1]-(d[i]-d[i-1])/h[i];
            }
            solveTridiag(sub,diag,sup,a,np-2);

            interpol = new float[(np-1)*precision][(np-1)*precision];
            int count = 0;
            for (int i=1; i<=np-1; i++) {   // loop over intervals between nodes
                for (int j=1; j<=precision; j++){
                    t1 = (h[i]*j)/precision;
                    t2 = h[i] - t1;
                    y = ((-a[i-1]/6*(t2+h[i])*t1+d[i-1])*t2 + (-a[i]/6*(t1+h[i])*t2+d[i])*t1)/h[i];
                    t=x[i-1]+t1;
                    interpol[0][count] = t;
                    interpol[1][count] = y;
                    count++;
                }
            }

            return interpol;
    }

    public static double[][] resampleBicubic(double[] x, double[] d, int precision ) {
            if(x == null){
                double[] time = new double[d.length];
                for(int u = 0; u < d.length; u++){
                    time[u] = u+1;
                }
                x = time;
            }
            double[][] interpol = new double[2][0];
            int np = x.length;
            double y;
            double t;
            double a[] = new double[np];
            double t1;
            double t2;
            double h[] = new double[np];
            for (int i=1; i<=np-1; i++){
                h[i] = x[i] - x[i-1];
            }

            double sub[] = new double[np-1];
            double diag[] = new double[np-1];
            double sup[] = new double[np-1];

            for (int i=1; i<=(np-2); i++){
                diag[i] = (h[i] + h[i+1])/3;
                sup[i] = h[i+1]/6;
                sub[i] = h[i]/6;
                a[i] = (d[i+1]-d[i])/h[i+1]-(d[i]-d[i-1])/h[i];
            }
            solveTridiag(sub,diag,sup,a,np-2);

            interpol = new double[(np-1)*precision][(np-1)*precision];
            int count = 0;
            for (int i=1; i<=np-1; i++) {   // loop over intervals between nodes
                for (int j=1; j<=precision; j++){
                    t1 = (h[i]*j)/precision;
                    t2 = h[i] - t1;
                    y = ((-a[i-1]/6*(t2+h[i])*t1+d[i-1])*t2 + (-a[i]/6*(t1+h[i])*t2+d[i])*t1)/h[i];
                    t=x[i-1]+t1;
                    interpol[0][count] = t;
                    interpol[1][count] = y;
                    count++;
                }
            }

            return interpol;
    }

    public static double[] resampleWindow(double[] time, double[] data, int window, int precision){

        double[][] dataInterpoled = resampleBicubic(time, data, precision);
        double[] dataResampled = new double[window];
        int inc = Math.round(dataInterpoled[1].length/window);
        int k = 0;
        for(int i = 0; i < window;i++){
            dataResampled[k] = dataInterpoled[1][i*inc];
            k++;
        }
        return dataResampled;
    }

    public static double[] removeConsecutivesEquals(double[] data){

        double val = data[0];
        int valid = 1;
        for(int i = 1; i <data.length;i++){
            if (data[i] == val){
                data[i] = 0;
            } else{
                val = data[i];
                valid++;
            }
        }
        double[] temp = new double[valid];
        int count = 0;
        for(int i = 0; i <data.length;i++){
            if (data[i] != 0){
                temp[count] = data[i];
                count++;
            }else{
               // System.out.println(data[i]);
            }
        }
        return temp;

    }

    /* This function detrends (subtracts a least-squares fitted line from) a
   a sequence of n uniformily spaced ordinates supplied in c. */
    public static void detrend(float[] c, int n) {
        int i;
        float a, b = 0f, tsqsum = 0f, ysum = 0f, t;

        for (i = 0; i < n; i++)
            ysum += c[i];
        for (i = 0; i < n; i++) {
            t = (i - n / 2 + 0.5f);
            tsqsum += t*t;
            b += t*c[i];
        }
        b /= tsqsum;
        a = (ysum / n - b * (n - 1) / 2.0f);
        for (i = 0; i < n; i++)
            c[i] -= a + b*i;
        if (b < -0.04 || b > 0.04){}
            //System.out.print("(warning) possibly significant trend in input series\n");
    }

     /* This function detrends (subtracts a least-squares fitted line from) a
   a sequence of n uniformily spaced ordinates supplied in c. */
    public static void detrend(double[] c, int n) {
        int i;
        double a, b = 0d, tsqsum = 0d, ysum = 0d, t;

        for (i = 0; i < n; i++)
            ysum += c[i];
        for (i = 0; i < n; i++) {
            t = (i - n / 2 + 0.5d);
            tsqsum += t*t;
            b += t*c[i];
        }
        b /= tsqsum;
        a = (ysum / n - b * (n - 1) / 2.0d);
        for (i = 0; i < n; i++)
            c[i] -= a + b*i;
        if (b < -0.04 || b > 0.04){}
            //System.out.print("(warning) possibly significant trend in input series\n");
    }
    /* This function calculates the mean of all sample values and subtracts it
       from each sample value, so that the mean of the adjusted samples is zero. */

    public static void zeromean(float[] y) {
        int l;
        float mean = 0.0f;
        mean = mean(y);
        for (l = 0; l < y.length; l++)
            y[l] -= mean;
    }

    public static void zeromean(double[] y, int n) {
        double mean = 0.0;
        mean = sum(y)/n;
        for (int l = 0; l < n; l++)
            y[l] -= mean;
    }
     /**
      * Return absolut distance in array, -infinity if no such value.
      */
    public static double[] absDistance(double[] a) {
        double[] result = new double[a.length-1];
        for (int i = 0; i < a.length-1; i++) {
             result[i] = Math.abs(a[i]-a[i+1]);
        }
        return result;
    }

     /**
      * Return absolut distance in array, -infinity if no such value.
      */
    public static float[] absDistance(float[] a) {
        float[] result = new float[a.length-1];
        for (int i = 0; i < a.length-1; i++) {
             result[i] = Math.abs(a[i]-a[i+1]);
        }
        return result;
    }

    public static int indexMax(double[] a) {
        double max = Float.NEGATIVE_INFINITY;
        int index = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
                index = i;
            }
        }
        return index;
    }

    /**
     * Return minimum value in array, +infinity if no such value.
     */
    public static int indexMin(float[] a) {
        float min = Float.POSITIVE_INFINITY;
        int index = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < min){
                min = a[i];
                index = i;
            }
        }
        return index;
    }

    
}
