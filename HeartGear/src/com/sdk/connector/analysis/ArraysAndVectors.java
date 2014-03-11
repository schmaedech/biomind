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

import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author diego
 */
public class ArraysAndVectors {

    public static float[] getFloatArray(Vector<Float> vector){
        float[] values = new float[vector.size()];
        for(int i = 0; i < vector.size(); i++){
            values[i] = vector.get(i);
        }
        return values;
    }

    public static double[] getDoubleArray(Vector<Double> vector){
        Vector<Double> interactor = new Vector<Double>(vector);
        double[] values = new double[interactor.size()];
        Iterator<Double> iter = interactor.iterator();
        int i = 0;
        while (iter.hasNext()) {
          values[i++] = iter.next();

        }

        return values;
    }

    public static float[] getFloatArrayFromString(Vector<String> vector){
        float[] values = new float[vector.size()];
        for(int i = 0; i < vector.size(); i++){
            values[i] = Float.parseFloat(vector.get(i));
        }
        return values;
    }

    public static float[] getNormFloatArray(Vector<Float> vector){
        float[] values = new float[vector.size()];
        for(int i = 0; i < vector.size(); i++){
            values[i] = vector.get(i)/1000f;
        }
        return values;
    }

}
