package com.nighthawk.spring_portfolio.mvc.stats;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class StatsCalculator {
  List<Double> dataset;
  String name;

  public StatsCalculator (List<Double> dataset, String name) {
    this.dataset = dataset;
    this.name = name;

    // needed for median & other purposes
    Comparator<Double> comparator = new Comparator<Double>() {

        @Override
        public int compare(Double numbers, Double numbers1) {
            if (numbers > numbers1 )
            {
                return 1;
            }
            else if (numbers < numbers1)
            {
                return -1;
            }
            else 
            {
                return 0;
            }

        }
    };
    dataset.sort(comparator);
  }

  public double getMean() {
    double total = 0;

    for (Double i: dataset) {
        total = total + i;
    }

    return total/dataset.size();
  }

  public double getMedian() {
    double middle = 0;

    if(dataset.size() %2 == 0) {
        middle = (dataset.get(dataset.size()/2 - 1) + dataset.get(dataset.size()/2)) / 2;
    }
    else {
        middle = dataset.get(dataset.size() / 2);
    }
    
    return middle;
  }

  public double getSD() {
    double total2 = 0;

    for (Double k: dataset) {
        total2 = Math.pow((getMean() - k), 2);
    }

    return Math.sqrt((total2)/(dataset.size()-1));
  }
  
  public String toString() {
    String output = "";
    boolean first = true;
    for (Double number : dataset) {
      output += (first ? "" : ", ") + number;
      first = false;
    }
    return output;
  }

  private String writeToFile(JFreeChart chart, int width, int height) {
    String uniqueID = UUID.randomUUID().toString();

    try {
      File outputFile = new File("volumes/graphs/" + uniqueID + ".png");
      ChartUtilities.saveChartAsPNG(outputFile, chart, width, height);
    } catch (IOException e) {
      System.out.println("Error saving the chart");
    }

    return "https://teamoops.nighthawkcoding.ml/graphs/" + uniqueID + ".png";
  }

  public String getHistogram() {
    HistogramDataset histo = new HistogramDataset();
    histo.addSeries(name, dataset.stream().mapToDouble(Double::doubleValue).toArray(), 8);
    JFreeChart histogram = ChartFactory.createHistogram(name, "Values", "Frequency", histo, PlotOrientation.VERTICAL, false, false, false);

    return writeToFile(histogram, 500, 500);
  }

  public String getBoxPlot() {
    final DefaultBoxAndWhiskerCategoryDataset box = new DefaultBoxAndWhiskerCategoryDataset();
    box.add(dataset, name, "");

    BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();   
    renderer.setMeanVisible(false);

    NumberAxis numberAxis = new NumberAxis("");
    CategoryPlot categoryplot = new CategoryPlot(box, new CategoryAxis(""), numberAxis, renderer);
    categoryplot.setOrientation(PlotOrientation.HORIZONTAL);
    
    JFreeChart boxplot = new JFreeChart(categoryplot);

    return writeToFile(boxplot, 500, 200);
  }

  public String getDotPlot(){
    XYSeriesCollection newDataSet = new XYSeriesCollection();

    XYSeries series2 = new XYSeries(name);  

    //add keys and values

    Map <Double, Integer> dataInputs = new HashMap<>();
    for(int i=0; i<dataset.size();i++){
        if(dataInputs.containsKey(dataset.get(i))){
            dataInputs.put(dataset.get(i), dataInputs.get(dataInputs.get(i)+1));
        } else {
            dataInputs.put(dataset.get(i),1);
        }


      series2.add(dataset.get(i), dataInputs.get(dataset.get(i)));
    }


    newDataSet.addSeries(series2);


    JFreeChart chart = ChartFactory.createScatterPlot(  
        "",   
        "", "", newDataSet, PlotOrientation.VERTICAL, false, false, false);  

    
    return writeToFile(chart, 500, 200);

  }
}

