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
package com.sdk.connector.chart;


import com.sdk.connector.analysis.PhyCoerence;
import com.sdk.connector.settings.CoherenceSettingsEntity;
import com.sdk.connector.settings.CoherenceSettingsPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import softvfc.com.sdk.connector.Protocol;

/**
 *
 * @author Diego Schmaedech Martins (schmaedech@gmail.com)
 * @version 15/10/2010
 */
public class CoherenceDomainRenderer {
    private ChartPanel chartPanel;

    private CoherenceSettingsEntity coes;
    private XYPlot plot;
    private TimeSeries serieFFT = new TimeSeries("");
    private TimeSeries serieLomb = new TimeSeries("");
    private TimeSeries serieMemse = new TimeSeries("");
    private SimpleDateFormat sdf ;
    private SimpleDateFormat tickSDF = new SimpleDateFormat("HH:mm");
    private TextTitle rangeAnnotation = new TextTitle( "", new Font("SansSerif", Font.PLAIN, 11) );
    private TimeSeriesCollection dataset = new TimeSeriesCollection();
   
    private JFreeChart chart;
    private boolean serieFFTAdd = true;
    private boolean serieLombAdd = true;
    private boolean serieMemseAdd = true;


    private boolean leftSide = true;
    public CoherenceDomainRenderer(final String title, JPanel panel, String side)  {
        sdf = Protocol.getInstance().getTimestampFormat();



        serieFFT.setKey(side + " FFT Based");
        serieLomb.setKey(side + " Lomb Based");
        serieMemse.setKey(side+ " AR Based");
        
        dataset.addSeries(serieFFT);
        dataset.addSeries(serieLomb);
        dataset.addSeries(serieMemse);

        chart = ChartFactory.createTimeSeriesChart(
            title,
            java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("coherence.xlabel"),
            java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("coherence.ylabel"),
            dataset,
            true,
            true,
            false
        );

        chart.getRenderingHints().put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        chart.getRenderingHints().put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        chart.getRenderingHints().put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        chart.getRenderingHints().put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        chart.getRenderingHints().put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        chart.getRenderingHints().put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
        chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        chart.getRenderingHints().put(RenderingHints.KEY_TEXT_LCD_CONTRAST,  100);
        //chart.setBackgroundPaint(new GradientPaint(0, 0, Color.white, 0, 1000, Color.GREEN));
       // chart.setBackgroundPaint(new Color(220,255,220,0));
        //chart.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/com/sdk/connector/resources/background.png")).getImage());
        chart.addSubtitle(rangeAnnotation);
        plot = (XYPlot) chart.getPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, true);
        renderer.setBaseToolTipGenerator(
            new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                sdf, new DecimalFormat("0.00")
            ));
        renderer.setSeriesOutlinePaint(0, Color.BLACK);
 
        if(side.startsWith(java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("side.left"))){
             renderer.setSeriesPaint(0, Color.BLUE);
             leftSide = true;
        }else{
             renderer.setSeriesPaint(0, Color.RED);
             leftSide = false;
        }
        renderer.setSeriesShape(0, new Ellipse2D.Double(-1.0, -1.0, 3.0, 3.0));


        DateTickUnit dtUnit = new DateTickUnit(DateTickUnitType.MINUTE, 1, tickSDF);
        final DateAxis domainAxis = (DateAxis) plot.getDomainAxis();
        domainAxis.setTickUnit(dtUnit);

//        ValueAxis axis = plot.getDomainAxis();
//        axis = plot.getRangeAxis();
//        ((NumberAxis) axis).setTickUnit(new NumberTickUnit(100));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.BLACK);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.getRenderer().setSeriesStroke(0, new BasicStroke(1.0f));
       // plot.getRenderer().setSeriesStroke(1, new BasicStroke(1.0f));
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage(java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("message.wait"));
        plot.setRangePannable(true);
        plot.setDomainPannable(true);
        Color color1 = new Color(0,0,0, 24);
        Color color2 = new Color(255,255,255, 24);

        GradientPaint gp = new GradientPaint(0, 0, color1, 0, 0, color2);
        plot.setBackgroundPaint(gp);

        chartPanel = new ChartPanel(chart);
         
        panel.setLayout(new GridLayout(0,1));

        panel.add(chartPanel);
        panel.repaint();
        panel.revalidate();


    }

    private void debug(Exception ex){ 
        Logger.getLogger(CoherenceDomainRenderer.class.getName()).log(Level.INFO, null, ex);
    }

    public void update(){
        try{
            coes = CoherenceSettingsPanel.getCoerenceSettingsEntity();
            if(coes.isShowFFT()){
                if(!serieFFTAdd){
                    dataset.addSeries(serieFFT);
                    serieFFTAdd = true;
                }
            }else {
                dataset.removeSeries(serieFFT);
                serieFFT.clear();
                serieFFTAdd = false;
            }

            if(coes.isShowLomb()){
                if(!serieLombAdd){
                    dataset.addSeries(serieLomb);
                    serieLombAdd = true;
                }
            }else{
                dataset.removeSeries(serieLomb);
                 serieLomb.clear();
                serieLombAdd = false;
            }

            if(coes.isShowMemse()){
                if(!serieMemseAdd){
                    dataset.addSeries(serieMemse);
                    serieMemseAdd = true;
                }
            }else{
                dataset.removeSeries(serieMemse);
                serieMemse.clear();
                serieMemseAdd = false;
            }

            if(leftSide){
                double leftCoherence = 0;
                int sum = 0;
                if(PhyCoerence.leftFFTCoerence != 0){
                    serieFFT.add(new Millisecond(new Date()), PhyCoerence.leftFFTCoerence);
                    leftCoherence += PhyCoerence.leftFFTCoerence;
                    sum++;
                    while(serieFFT.getItemCount() > CoherenceSettingsPanel.getCoerenceSettingsEntity().getPlotPoints()){
                        serieFFT.delete(0, 1);
                    }
                        serieFFT.fireSeriesChanged();
                }
                if(PhyCoerence.leftMemseCoerence != 0){
                    serieMemse.add(new Millisecond(new Date()), PhyCoerence.leftMemseCoerence);
                    leftCoherence += PhyCoerence.leftMemseCoerence;
                    sum++;
                    while(serieMemse.getItemCount() > CoherenceSettingsPanel.getCoerenceSettingsEntity().getPlotPoints()){
                        serieMemse.delete(0, 1);
                    }
                        serieMemse.fireSeriesChanged();
                }
                if(PhyCoerence.leftLombCoerence != 0){
                    serieLomb.add(new Millisecond(new Date()),PhyCoerence.leftLombCoerence );
                    leftCoherence += PhyCoerence.leftLombCoerence;
                    sum++;
                    while(serieLomb.getItemCount() > CoherenceSettingsPanel.getCoerenceSettingsEntity().getPlotPoints()){
                        serieLomb.delete(0, 1);
                    }
                        serieLomb.fireSeriesChanged();
                }

                if(sum > 0){
                    Protocol.getInstance().setLeftCoherence( (leftCoherence/sum) * 1);
                }
            }else{
                double rightCoherence = 0;
                int sum = 0;
                if(PhyCoerence.rightFFTCoerence != 0){
                    serieFFT.add(new Millisecond(new Date()), PhyCoerence.rightFFTCoerence);
                    rightCoherence += PhyCoerence.rightFFTCoerence;
                    sum++;
                    while(serieFFT.getItemCount() > CoherenceSettingsPanel.getCoerenceSettingsEntity().getPlotPoints()){
                        serieFFT.delete(0, 1);
                    }
                        serieFFT.fireSeriesChanged();
                }
                if(PhyCoerence.rightMemseCoerence != 0){
                    serieMemse.add(new Millisecond(new Date()), PhyCoerence.rightMemseCoerence);
                    rightCoherence += PhyCoerence.rightMemseCoerence;
                    sum++;
                    while(serieMemse.getItemCount() > CoherenceSettingsPanel.getCoerenceSettingsEntity().getPlotPoints()){
                        serieMemse.delete(0, 1);
                    }
                        serieMemse.fireSeriesChanged();
                }
                if(PhyCoerence.rightLombCoerence != 0){
                    serieLomb.add(new Millisecond(new Date()),PhyCoerence.rightLombCoerence );
                    rightCoherence += PhyCoerence.rightLombCoerence;
                    sum++;
                    while(serieLomb.getItemCount() > CoherenceSettingsPanel.getCoerenceSettingsEntity().getPlotPoints()){
                        serieLomb.delete(0, 1);
                    }
                        serieLomb.fireSeriesChanged();
                }
                if(sum > 0){
                    Protocol.getInstance().setRightCoherence((rightCoherence/sum) * 1);
                }
            }
        }catch(Exception ex){
            debug(ex);
        }
    }
    

}

