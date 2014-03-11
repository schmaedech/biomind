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
public class TimeDomain {

    public static float calculeMEAN(Vector<Float> rr){
        return Statistics.mean(ArraysAndVectors.getFloatArray(rr));
    }

    public static float calculeSD(Vector<Float> rr){
        return Statistics.stddev(ArraysAndVectors.getFloatArray(rr));
    }

    public static float calculeSD1(Vector<Float> rr){
        return (float) (Math.sqrt(0.5) * calculeSDSD(rr));
    }

    public static float calculeSD2(Vector<Float> rr){
        return (float) Math.sqrt( 2*Math.pow(calculeSD(rr), 2) - 0.5 * Math.pow(calculeSDSD(rr), 2));
    }

    public static float calculeSDSD(Vector<Float> rr){
        float[] adjacent = new float[rr.size()-1];
        for(int t=0; t < rr.size()-1; t++){
            adjacent[t] = rr.get(t) - rr.get(t+1);
        }
        return Statistics.stddev(adjacent);
    }

    public static float calculeRMSSD(Vector<Float> rr){
        float[] adjacent = new float[rr.size()-1];
        float[] adjacentPow2 = new float[adjacent.length];

        for(int t=0; t < rr.size()-1; t++){
            adjacent[t] = rr.get(t) - rr.get(t+1);
            adjacentPow2[t] = (float) Math.pow(adjacent[t],2);
        }
        float adj = 0;
        for(int t = 0; t < adjacentPow2.length; t++){
            adj += adjacentPow2[t];
        }
        return (float) Math.sqrt(adj/adjacentPow2.length);
    }

}
