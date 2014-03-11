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
 * @author Schmaedech
 */
public class BicubicInterpolation {
      

        public BicubicInterpolation(){

        }

        private void solveTridiag(float sub[], float diag[], float sup[], float b[], int n){
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

        public float[][] resampleBicubic(float[] x, float[] d, int precision ) {
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


}
class ControlPoint extends Object {
        public float x;
        public float y;

        public ControlPoint(float a, float b) {
            x = a;
            y = b;
        }

        public boolean within(float a, float b, int size) {
            if (a >= x - size && b >= y - size && a <= x + size && b <= y + size)
                return true;
            else
                return false;
        }
}
