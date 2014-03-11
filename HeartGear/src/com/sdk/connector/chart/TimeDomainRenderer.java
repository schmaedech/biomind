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


import com.sdk.connector.settings.RRBCSettingsPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date; 
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYAnnotation;
import org.jfree.chart.annotations.XYDrawableAnnotation;
import org.jfree.chart.annotations.XYPointerAnnotation;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.PlotEntity;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.TextAnchor;
import softvfc.com.sdk.connector.Protocol;

/**
 *
 * @author Diego Schmaedech Martins (schmaedech@gmail.com)
 * @version 15/10/2010
 */
public class TimeDomainRenderer implements ChartMouseListener {
    private ChartPanel chartPanel;
    private Protocol protocol = Protocol.getInstance();
    private XYPlot plot;
    private TimeSeries serie = new TimeSeries("");
    private SimpleDateFormat sdf ;
    private SimpleDateFormat tickSDF = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    private TextTitle rangeAnnotation = new TextTitle( "", new Font("SansSerif", Font.PLAIN, 11) );
    private TimeSeriesCollection dataset = new TimeSeriesCollection();
   
    private JFreeChart chart;
    private String side = "";
    private String type = "";

    public TimeDomainRenderer(final String title, JPanel panel, String side, String type)  {
        sdf = protocol.getTimestampFormat();
        this.side = side + type;
        this.type = type;
        serie.setKey(side);
        dataset.addSeries(serie);
        String legendX ="";
        if(type.startsWith(java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("RR"))){
             legendX = java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("timeRR.ylabel");
        }else{
             legendX = java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("timeBC.ylabel");
        }
        chart = ChartFactory.createTimeSeriesChart(
            title,
            java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("time.xlabel"),
            legendX,
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
        }else{
             renderer.setSeriesPaint(0, Color.RED);
        }
       
        renderer.setSeriesShape(0, new Ellipse2D.Double(-1.0, -1.0, 3.0, 3.0));
        if(type.startsWith(java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("BC"))){
            renderer.setSeriesShape(0, new Rectangle2D.Double(-1.0, -1.0, 3.0, 3.0));
        }

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
        plot.setNoDataMessage(java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("CALIBRATING..."));
        plot.setRangePannable(true);
        plot.setDomainPannable(true);
        Color color1 = new Color(0,0,0, 24);
        Color color2 = new Color(255,255,255, 24);

        GradientPaint gp = new GradientPaint(0, 0, color1, 0, 0, color2);
        plot.setBackgroundPaint(gp);

        chartPanel = new ChartPanel(chart);
        chartPanel.addChartMouseListener(this);
        panel.setLayout(new GridLayout(0,1));

        panel.add(chartPanel);
        panel.repaint();
        panel.revalidate();


    }

    private void debug(Exception ex){ 
        Logger.getLogger(TimeDomainRenderer.class.getName()).log(Level.INFO, null, ex);
    }

    private String lastLeftTimestamp = "0000-00-00 00:00:00.00000";
    private String lastRightTimestamp = "0000-00-00 00:00:00.00000";
    private RegularTimePeriod lastLeftRegularTimePeriod = new Millisecond(new Date());
    private RegularTimePeriod lastRightRegularTimePeriod = new Millisecond(new Date());
    public void update(){

        if(protocol.isRegister()){
            try{
                String leftTimestamp = (String)protocol.getRow()[0];
                String rightTimestamp = (String)protocol.getRow()[2];
                int leftSampledValue = Integer.parseInt( protocol.getRow()[1].toString());
                int rightSampledValue = Integer.parseInt( protocol.getRow()[3].toString());
//
//
//                String strDate = sdf.format(new Date());
//                Date chartDate = sdf.parse(strDate);

                int value;
                if(this.side.startsWith(java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("side.left"))){
                    if(protocol.getLeftAccumulator().size() > 10){
                        if(!leftTimestamp.equals(lastLeftTimestamp) && !leftTimestamp.startsWith("0000") && !leftTimestamp.equals("")){

                            value = leftSampledValue;
                            if(value > 0){
                                if(type.startsWith(java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("BC"))){
                                      value = Math.round(60000/value);
                                }
                                
                                serie.add(new Millisecond(new Date()), value);

                                while(serie.getItemCount() > RRBCSettingsPanel.getRRBCSettingsEntity().getPlotPoints()){
                                    serie.delete(0, 1);
                                }
                                serie.fireSeriesChanged();


                            }
                            lastLeftTimestamp = leftTimestamp;
                        }

                    }else{
                        plot.setNoDataMessage(java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("CALIBRATING[")+ (10 - protocol.getLeftAccumulator().size())+"]...");
                    }
                }else{
                    if(protocol.getRightAccumulator().size() > 10){
                        if(!rightTimestamp.equals(lastRightTimestamp) && !rightTimestamp.startsWith("0000") && !rightTimestamp.equals("")){

                            value = rightSampledValue;
                            if(value > 0){
                                if(type.startsWith(java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("BC"))){
                                      value = Math.round(60000/value);
                                }
                                serie.add(new Millisecond(new Date()), value);
                                while(serie.getItemCount() > RRBCSettingsPanel.getRRBCSettingsEntity().getPlotPoints()){
                                    serie.delete(0, 1);
                                }
                                serie.fireSeriesChanged();
                            }
                            lastRightTimestamp = rightTimestamp;
                        }
                    }else{
                        plot.setNoDataMessage(java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("CALIBRATING[")+ (10 - protocol.getRightAccumulator().size())+"]...");
                    }
                     
                }

            }catch(Exception ex){
                debug(ex);
               
            }
        }
            

    }

    private static Vector<XYAnnotation> lapAnnotations = new Vector<XYAnnotation>();

    @Override
    public void chartMouseClicked(ChartMouseEvent cme) {
        ChartEntity chartentity = cme.getEntity();
        //JOptionPane.showMessageDialog(null,chartentity.getClass(), "",JOptionPane.CANCEL_OPTION);

        if (cme.getEntity() instanceof PlotEntity) {
            if (chartentity != null  ){
                Point2D p = chartPanel.translateScreenToJava2D(cme.getTrigger().getPoint());
                final Rectangle2D plotArea = chartPanel.getChartRenderingInfo().getPlotInfo().getDataArea();
                double chartX = plot.getDomainAxis().java2DToValue(p.getX(), plotArea, plot.getDomainAxisEdge());
                double chartY = plot.getRangeAxis().java2DToValue(p.getY(), plotArea, plot.getRangeAxisEdge());

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis((long) chartX);
                createAnnotation(formatter.format(calendar.getTime()), chartX, chartY);
            }else{

            }
        }
    }

    public void createAnnotation(String lap, double x, double y){

        final CircleDrawer cd = new CircleDrawer(Color.GREEN, new BasicStroke(1.0f), null);
        final XYAnnotation lapLabel = new XYDrawableAnnotation(x, y, 15, 15, cd);
        lapAnnotations.add(lapLabel);
        plot.addAnnotation(lapAnnotations.lastElement(), false);
        final XYPointerAnnotation pointer = new XYPointerAnnotation(java.util.ResourceBundle.getBundle("com/sdk/connector/chart/Bundle").getString("label.lap")+lap, x, y,  345 );
        pointer.setBaseRadius(35.0);
        pointer.setTipRadius(10.0);
        pointer.setFont(new Font("DejaVu Sans", Font.BOLD, 11));
        pointer.setPaint(Color.black);
        pointer.setTextAnchor(TextAnchor.HALF_ASCENT_RIGHT);
        lapAnnotations.add(pointer);
        plot.addAnnotation(lapAnnotations.lastElement(), false);
    }


    @Override
    public void chartMouseMoved(ChartMouseEvent cme) {

    }


}

