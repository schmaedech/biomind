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
package com.sdk.connector.chart;


import com.sdk.connector.analysis.ArraysAndVectors;
import com.sdk.connector.analysis.PhyCoerence;
import com.sdk.connector.settings.FFTSettingsEntity;
import com.sdk.connector.settings.FFTSettingsPanel;
import com.sdk.connector.settings.LombSettingsEntity;
import com.sdk.connector.settings.LombSettingsPanel;
import com.sdk.connector.settings.MemseSettingsEntity;
import com.sdk.connector.settings.MemseSettingsPanel;
import com.sdk.connector.analysis.PhyFFT;
import com.sdk.connector.analysis.PhyLomb;
import com.sdk.connector.analysis.PhyMemse;
import com.sdk.connector.analysis.Statistics;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.annotations.XYAnnotation;
import org.jfree.chart.annotations.XYPointerAnnotation;
import org.jfree.chart.axis.NumberAxis; 
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeries; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;
import softvfc.com.sdk.Protocol;


 
/** 
 * A demo showing chart annotations. 
 */

public class FrequencyDomainRenderer{

    private Protocol protocol = Protocol.getInstance();
    private ChartPanel chartPanel;
    private XYPlot plot;
    private XYSeries serie = new XYSeries("", true, true);
    private PhyFFT FFT;
    private PhyLomb Lomb;
    private PhyMemse Memse;
    NumberFormat formatter = new DecimalFormat("#0.000");
    XYItemRenderer renderer;
    double[] milisseconds = null;
    IntervalMarker vlfTarget = new IntervalMarker(0, 0.04);
    IntervalMarker lfTarget = new IntervalMarker(0.04, 0.15);
    IntervalMarker hfTarget = new IntervalMarker(0.15, 0.4);


    private Vector<XYAnnotation> lapAnnotations = new Vector<XYAnnotation>();
    private XYSeriesCollection dataset = new XYSeriesCollection();


    FFTSettingsEntity ffts;
    LombSettingsEntity lombs;
    MemseSettingsEntity memses;
    PhyCoerence phyCoerence = new PhyCoerence();

    NumberAxis numRangeAxis;
    
    private void debug(Exception ex){
        Logger.getLogger(FrequencyDomainRenderer.class.getName()).log(Level.INFO, null, ex);
    }

    private String side = "";
    private String type = "";
    /**
     * Creates a new demo application.
     *
     * @param title  the frame title.
     */
    public FrequencyDomainRenderer(String title, JPanel panel, String side, String type) {

        this.side = side;
        this.type = type;
        serie.setKey(side);

        dataset.addSeries(serie);
        JFreeChart chart = ChartFactory.createXYAreaChart(
            title,
            java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("frequency.xlabel"),
            java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("frequency.ylabel2"),
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );

    //        TextTitle t1 = new TextTitle( "Espectro de Frequência Estimado (PSD)", new Font("SansSerif", Font.BOLD, 14)  );
    //        //TextTitle t2 = new TextTitle( "valores atualizados a cada potência de 2", new Font("SansSerif", Font.PLAIN, 11)      );
    //        chart.addSubtitle(t1);
        chart.getRenderingHints().put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        chart.getRenderingHints().put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        chart.getRenderingHints().put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        chart.getRenderingHints().put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        chart.getRenderingHints().put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        chart.getRenderingHints().put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
        chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        chart.getRenderingHints().put(RenderingHints.KEY_TEXT_LCD_CONTRAST,  100);
        //chart.addSubtitle(t2);
        plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.BLUE);
        plot.setRangeGridlinePaint(Color.CYAN);
        plot.getRenderer().setSeriesStroke(0, new BasicStroke(2.0f));
        plot.getRenderer().setSeriesStroke(1, new BasicStroke(2.0f));


        //plot.setRangePannable(true);
        plot.setForegroundAlpha(0.65f);


        //XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
       renderer = plot.getRenderer();
       renderer.setSeriesOutlinePaint(0, Color.BLACK);
        if(side.startsWith(java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("side.left"))){
             renderer.setSeriesPaint(0, Color.BLUE);
        }else{
             renderer.setSeriesPaint(0, Color.RED);
        }
    //        renderer.setSeriesLinesVisible(0, true);
    //        renderer.setSeriesShapesVisible(0, false);
    //        renderer.setSeriesLinesVisible(1, true);
    //        renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.BLACK);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setForegroundAlpha(0.5f);


        Color color1 = new Color(0,0,0, 24);
        Color color2 = new Color(255,255,255, 24);

        GradientPaint gp = new GradientPaint(0, 0, color1, 0, 0, color2);
        plot.setBackgroundPaint(gp);

        final ValueAxis domainAxis = plot.getDomainAxis();
        domainAxis.setTickMarkPaint(Color.black);
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.0);
        NumberAxis numDomainAxis = (NumberAxis) plot.getDomainAxis();
        numDomainAxis.setAutoRangeIncludesZero(true);
    //
    //        final NumberAxis rangeAxis = new LogarithmicAxis("Log(y)");
    //
    //        plot.setRangeAxis(rangeAxis);
        final ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setTickMarkPaint(Color.black);


        numRangeAxis = (NumberAxis) plot.getRangeAxis();
        numRangeAxis.setAutoRangeIncludesZero(true);


        vlfTarget.setLabel("VLF");
        vlfTarget.setLabelFont(new Font("SansSerif", Font.ITALIC, 9));
        vlfTarget.setLabelAnchor(RectangleAnchor.CENTER);
        vlfTarget.setLabelTextAnchor(TextAnchor.CENTER);
        vlfTarget.setPaint(new Color(0, 100, 255, 128));
       // plot.addRangeMarker(target, Layer.BACKGROUND);
        plot.addDomainMarker(vlfTarget, Layer.BACKGROUND);


        lfTarget.setLabel("LF");
        lfTarget.setLabelFont(new Font("SansSerif", Font.ITALIC, 9));
        lfTarget.setLabelAnchor(RectangleAnchor.CENTER);
        lfTarget.setLabelTextAnchor(TextAnchor.CENTER);
        lfTarget.setPaint(new Color(255, 255, 0, 128));
       // plot.addRangeMarker(target, Layer.BACKGROUND);
        plot.addDomainMarker(lfTarget, Layer.BACKGROUND);


        hfTarget.setLabel("HF");
        hfTarget.setLabelFont(new Font("SansSerif", Font.ITALIC, 9));
        hfTarget.setLabelAnchor(RectangleAnchor.CENTER);
        hfTarget.setLabelTextAnchor(TextAnchor.CENTER);
        hfTarget.setPaint(new Color(255, 0, 255, 128));
       // plot.addRangeMarker(target, Layer.BACKGROUND);
        plot.addDomainMarker(hfTarget, Layer.BACKGROUND);
        plot.setNoDataMessage(java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("message.wait"));



        chartPanel = new ChartPanel(chart);

        panel.setLayout(new GridLayout(0,1));
        panel.add(chartPanel);
        panel.repaint();
        panel.revalidate();

    }

    private void drawPeakVLF(double[] x, double[] y, double min, double max){
        vlfTarget.setStartValue(min);
        vlfTarget.setEndValue(max);
        double[] xx = new double[x.length];
        double[] yy = new double[y.length];
        System.arraycopy(x, 0, xx, 0, x.length);
        System.arraycopy(y, 0, yy, 0, y.length);

        for(int i = 0; i < xx.length; i++){
            if(!(xx[i] > min && xx[i] < max)){
                yy[i] = 0;
            }
        }
        double maxx = Statistics.max(yy);
        int indexXX = Statistics.indexMax(yy);
        if(indexXX != 0){
            createAnnotation( new Color(0, 100, 255), xx[indexXX] , xx[indexXX], maxx );
            vlfTarget.setLabel(formatter.format(xx[indexXX]));
        }
    }

    private void drawPeakLF(double[] x, double[] y, double min, double max){
        lfTarget.setStartValue(min);
        lfTarget.setEndValue(max);
        double[] xx = new double[x.length];
        double[] yy = new double[y.length];
        System.arraycopy(x, 0, xx, 0, x.length);
        System.arraycopy(y, 0, yy, 0, y.length);
         for(int i = 0; i < xx.length; i++){
            if(!(xx[i] > min && xx[i] < max)){
                yy[i] = 0;
            }
        }
        double maxx = Statistics.max(yy);
        int indexXX = Statistics.indexMax(yy);
        if(indexXX != 0){
            createAnnotation(new Color(255, 255, 0), xx[indexXX] , xx[indexXX], maxx );
            lfTarget.setLabel( formatter.format(xx[indexXX]));
        }
    }

    private void drawPeakHF(double[] x, double[] y, double min, double max){
        hfTarget.setStartValue(min);
        hfTarget.setEndValue(max);
        double[] xx = new double[x.length];
        double[] yy = new double[y.length];
        System.arraycopy(x, 0, xx, 0, x.length);
        System.arraycopy(y, 0, yy, 0, y.length);
          for(int i = 0; i < xx.length; i++){
            if(!(xx[i] > min && xx[i] < max)){
                yy[i] = 0;
            }
        }
        double maxx = Statistics.max(yy);
        int indexXX = Statistics.indexMax(yy);
        if(indexXX != 0){
            createAnnotation(new Color(255, 0, 255),  xx[indexXX] , xx[indexXX], maxx );
            hfTarget.setLabel( formatter.format(xx[indexXX]));
        }
    }

    private void FFT(double[] array){
        double[] sampleArr = null;
        ffts = FFTSettingsPanel.getFftSettingsEntity();
        if(ffts.getSamples() != 0 && array.length < ffts.getSamples()){
            sampleArr = new double[ffts.getSamples()];
            for (int i = 0; i < sampleArr.length; i++) {
                sampleArr[i] = 0;
                if(i < array.length ){
                    sampleArr[i] = array[i];
                }
            }
            array = sampleArr;
        }
        if(ffts.getSamples() != 0 && array.length > ffts.getSamples()){
            sampleArr = new double[ffts.getSamples()];
            for (int i = 0; i < sampleArr.length; i++) {
                 sampleArr[i] = array[i + (array.length  - sampleArr.length)];

            }
            array = sampleArr;
        }
        if(ffts.isBicubic()){
            array = Statistics.resampleWindow(null, array, ffts.getBicubicWindow(), ffts.getBicubicPrecision());
        }

        if(ffts.isPlotPower()){
            numRangeAxis.setLabel(java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("frequency.ylabel2"));
        }else{
            numRangeAxis.setLabel(java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("frequency.ylabel"));
        }


        FFT = new PhyFFT(array, 
                ffts.getFreq(),
                ffts.getSamples(),
                ffts.getK(),
                ffts.getL(),
                ffts.getD(),
                ffts.isPlotPower(),
                ffts.getSmooth(),
                ffts.getDecimation(),
                ffts.getWindow(),
                ffts.isZeromean(),
                ffts.isDetrend());
        for (int i = 0; i < FFT.getFrequency().length; i++) {
            serie.add(FFT.getFrequency()[i], FFT.getPower()[i]);
        }
        drawPeakVLF(FFT.getFrequency(),FFT.getPower(), ffts.getVLFmin(), ffts.getVLFmax());
        drawPeakLF(FFT.getFrequency(),FFT.getPower(), ffts.getLFmin(), ffts.getLFmax());
        drawPeakHF(FFT.getFrequency(),FFT.getPower(), ffts.getHFmin(), ffts.getHFmax());
        phyCoerence.drawCoerence(FFT.getFrequency(), FFT.getPower(), side, "FFT");
    }

    private void Lomb(double[] array){

        double[] sampleArr = null;
        double[] milissecondsArr = null;

        lombs = LombSettingsPanel.getLombSettingsEntity();
        if(lombs.getSamples() != 0 && array.length < lombs.getSamples()){
            sampleArr = new double[lombs.getSamples()];
            milissecondsArr = new double[lombs.getSamples()];
            for (int i = 0; i < sampleArr.length; i++) {
                sampleArr[i] = 0;
                milissecondsArr[i] = (double) new Date().getTime() + 1000*i;
                if(i < array.length ){
                    sampleArr[i] = array[i];
                }
            }
            array = sampleArr;
            milisseconds = milissecondsArr;
        }
        if(lombs.getSamples() != 0 && array.length > lombs.getSamples()){
            sampleArr = new double[lombs.getSamples()];
            milissecondsArr = new double[lombs.getSamples()];
            for (int i = 0; i < sampleArr.length; i++) {
                 sampleArr[i] = array[i + (array.length  - sampleArr.length)];
                 milissecondsArr[i] = milisseconds[i + (array.length  - sampleArr.length)];

            }
            array = sampleArr;
            milisseconds = milissecondsArr;
        }

        


        if(lombs.isPlotPower()){
            numRangeAxis.setLabel(java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("frequency.ylabel2"));
        }else{
            numRangeAxis.setLabel(java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("frequency.ylabel"));
        }
        Lomb = new PhyLomb(milisseconds, array, lombs.isSmooth(), lombs.isPlotPower(), lombs.isZeromean(), lombs.isDetrend());
        for (int i = 0; i < Lomb.getFrequency().length; i++) {
            serie.add(Lomb.getFrequency()[i], Lomb.getPower()[i]);
        }
         
        drawPeakVLF(Lomb.getFrequency(),Lomb.getPower(), lombs.getVLFmin(), lombs.getVLFmax());
        drawPeakLF(Lomb.getFrequency(),Lomb.getPower(), lombs.getLFmin(), lombs.getLFmax());
        drawPeakHF(Lomb.getFrequency(),Lomb.getPower(), lombs.getHFmin(), lombs.getHFmax());
        phyCoerence.drawCoerence(Lomb.getFrequency(), Lomb.getPower(), side, "Lomb");
    }

    private void Memse(double[] array){

        double[] sampleArr = null;
        memses = MemseSettingsPanel.getMemseSettingsEntity();
        if(memses.getSamples() != 0 && array.length < memses.getSamples()){
            sampleArr = new double[memses.getSamples()];
            for (int i = 0; i < sampleArr.length; i++) {
                sampleArr[i] = 0;
                if(i < array.length ){
                    sampleArr[i] = array[i];
                }
            }
            array = sampleArr;
        }
        if(memses.getSamples() != 0 && array.length > memses.getSamples()){
            sampleArr = new double[memses.getSamples()];
            for (int i = 0; i < sampleArr.length; i++) {
                 sampleArr[i] = array[i + (array.length  - sampleArr.length)];

            }
            array = sampleArr;
        }
        if(memses.isBicubic()){
            array = Statistics.resampleWindow(null, array, memses.getBicubicWindow(), memses.getBicubicPrecision());
        }

         
        if(memses.isPlotPower()){
            numRangeAxis.setLabel(java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("frequency.ylabel2"));
        }else{
            numRangeAxis.setLabel(java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("frequency.ylabel"));
        }
         
        Memse = new PhyMemse(array, memses.getFreq(), memses.getArModel(), memses.isPlotPower(), memses.getWindow(),memses.isZeromean(),memses.isDetrend());
        for (int i = 0; i < Memse.getFrequency().length; i++) {
            serie.add(Memse.getFrequency()[i], Memse.getPower()[i]);
        }
        drawPeakVLF(Memse.getFrequency(),Memse.getPower(), memses.getVLFmin(), memses.getVLFmax());
        drawPeakLF(Memse.getFrequency(),Memse.getPower(), memses.getLFmin(), memses.getLFmax());
        drawPeakHF(Memse.getFrequency(),Memse.getPower(), memses.getHFmin(), memses.getHFmax());
       // System.out.println(Memse.getFrequency().length + "   "+ Memse.getPower().length);
        phyCoerence.drawCoerence(Memse.getFrequency(), Memse.getPower(), side, "Memse");
    }

    public void createAnnotation(Color color, double lap, double x, double y){


        final XYPointerAnnotation pointer = new XYPointerAnnotation("", x, y,  345 );
        pointer.setBaseRadius(35.0);
        pointer.setTipRadius(5.0);
        pointer.setFont(new Font("DejaVu Sans", Font.PLAIN, 9));
        pointer.setPaint(Color.blue);
        pointer.setTextAnchor(TextAnchor.HALF_ASCENT_RIGHT);
        lapAnnotations.add(pointer);
        renderer.addAnnotation(lapAnnotations.lastElement());


        chartPanel.repaint();
        chartPanel.revalidate();
    }

    public void clearAnnotations(){

        renderer.removeAnnotations();
        lapAnnotations.removeAllElements();
    }

    public void update() {



            try{
                
                double[] array = null;
                if(side.startsWith(java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("side.left"))){
                    if(protocol.getLeftAccumulator().size() > 10){
                        plot.setNoDataMessage(java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("CALIBRATING[")+ (15 - protocol.getLdata().size())+"]...");
                        if( protocol.getLdata().size() > 15){
                            try{
                                array =  ArraysAndVectors.getDoubleArray(protocol.getLdata());
                                milisseconds = ArraysAndVectors.getDoubleArray(protocol.getLtime());
                            }catch(Exception ex){
                                debug(ex);
                            }
                        }
                    }
                }else{
                    if(protocol.getRightAccumulator().size() > 10){
                        plot.setNoDataMessage(java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("CALIBRATING[")+ (15 - protocol.getRdata().size())+"]...");
                        if( protocol.getRdata().size() > 15){
                            try{
                                array =  ArraysAndVectors.getDoubleArray(protocol.getRdata());
                                milisseconds = ArraysAndVectors.getDoubleArray(protocol.getRtime());
                            }catch(Exception ex){
                                debug(ex);
                            }
                        }
                    }
                }

                if( array != null){
                   serie.clear();
                   clearAnnotations();
                   if(type.startsWith("FFT")){
                        FFT(array);
                   }else if(type.startsWith("Lomb")){
                        Lomb(array);
                   }else{
                        Memse(array);
                   }
                }

            }catch(Exception ex){
                debug(ex);
            }
        }



} 
 
