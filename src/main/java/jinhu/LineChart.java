package jinhu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale.Category;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

import Petrinet_with_GUI.Petrinet_with_GUI.Resource;
import jinhu.*;

/**
 * 
 * 折线图
 *       <p>
 *       创建图表步骤：<br/>
 *       1：创建数据集合<br/>
 *       2：创建Chart：<br/>
 *       3:设置抗锯齿，防止字体显示不清楚<br/>
 *       4:对柱子进行渲染，<br/>
 *       5:对其他部分进行渲染<br/>
 *       6:使用chartPanel接收<br/>
 * 
 *       </p>
 */
public class LineChart {
    public LineChart() {
    }

    public DefaultCategoryDataset createDataset(int arrival_time,int step,ArrayList<GraphResult> rlist) {
//        // 标注类别
//        String[] categories = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        Vector<Serie> series = new Vector<Serie>();
//        // 柱子名称：柱子所有的值集合
//        series.add(new Serie("Tokyo", new Double[] { 49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4 }));
//        series.add(new Serie("New York", new Double[] { 83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3 }));
//        series.add(new Serie("London", new Double[] { 48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2 }));
//        series.add(new Serie("Berlin", new Double[] { 42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1 }));
//        // 1：创建数据集合
        String[] categories = new String[arrival_time/step];
        int looptime = arrival_time/step;
        for(int i = 0;i < looptime;i++) {
        	categories[i] = String.valueOf(step*(i+1));
        	System.out.println("Arrival_time"+arrival_time);
        }
        
        
        for(String categorie: categories) {
        	System.out.println("categorie:"+categorie);
        }
    	 
    	 
    	Map<String, Vector<Object>> seriesmap = new HashMap<String, Vector<Object>>();
    	for (GraphResult r:rlist) {
    		System.out.println(""+r.toString());
			if (!seriesmap.containsKey(r.getParamaterName())) {
				seriesmap.put(r.getParamaterName(), new Vector<Object>());
				seriesmap.get(r.getParamaterName()).add(r.getDelay());
			}else {
				seriesmap.get(r.getParamaterName()).add(r.getDelay());
			}
		}
    	
    	for(String key:seriesmap.keySet()) {
    		System.out.println("Key"+key+"  Value:"+seriesmap.get(key));
    		series.add(new Serie(key,seriesmap.get(key)));
    	}
        DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
        return dataset;
    }
    
    
    

    public ChartPanel createChart(int arrival_time,int step,ArrayList<GraphResult> rlist,Map graph) {
        // 2：创建Chart[创建不同图形]
        JFreeChart chart = ChartFactory.createLineChart((String)graph.get("Title"), "", "Waiting Time("+graph.get("unit")+")", createDataset(arrival_time,step,rlist), PlotOrientation.VERTICAL, true, true, true);
        // 3:设置抗锯齿，防止字体显示不清楚
        ChartUtils.setAntiAlias(chart);// 抗锯齿
        // 4:对柱子进行渲染[[采用不同渲染]]
        ChartUtils.setLineRender(chart.getCategoryPlot(), false,true);//
        // 5:对其他部分进行渲染
        ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
        ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
        // 设置标注无边框
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
        // 6:使用chartPanel接收
        ChartPanel chartPanel = new ChartPanel(chart);
        
        LegendTitle legendTitle = chart.getLegend();
//        chart.addSubtitle(0, new TextTitle("dasdasdasd"));
//        legendTitle.setPosition(RectangleEdge.RIGHT);
        
       
       CategoryPlot plot =  (CategoryPlot)chart.getPlot();
       CategoryAxis domainAixs =  plot.getDomainAxis();
       domainAixs.setLabel("Number of "+ graph.get("paramater"));
       domainAixs.setTickLabelsVisible(true);
       
        
        
        return chartPanel;
    }
    
    public static void main(String[] args) {
       

    }

}
