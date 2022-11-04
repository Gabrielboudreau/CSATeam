package com.nighthawk.spring_portfolio.mvc.stats;

import javax.persistence.*;

@Entity
public class Dataset {
    // Fields listed here will be stored in the database
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String name;

    @Column()
    private String data;

    @Column()
    private Double mean;

    @Column()
    private Double median;

    @Column()
    private Double standardDeviation;

    @Column()
    private int count;

    @Column()
    private String histogramImg;

    @Column()
    private String boxPlotImg;

    @Column()
    private String dotPlotImg;

    @Column()
    private String stemPlot;
    
    // No arguments constructor
    public Dataset() {

    }

    // All arguments constructor
    public Dataset(String name, String data, Double mean, Double median, Double standardDeviation, int count, String histogramImg, String boxPlotImg, String dotPlotImg, String stemPlot) {
      this.name = name;
      this.data = data;
      this.mean = mean;
      this.median = median;
      this.standardDeviation = standardDeviation;
      this.count = count;
      this.histogramImg = histogramImg;
      this.boxPlotImg = boxPlotImg;
      this.dotPlotImg = dotPlotImg;
      this.stemPlot = stemPlot;
    }

    // Getters and Setters
    public Long getId() {
      return this.id;
    }

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public void setData(String data) {
      this.data = data;
    }

    public String getData() {
      return this.data;
    }

    public Double getMean() {
      return this.mean;
    }

    public void setMean(Double mean) {
      this.mean = mean;
    }

    public Double getMedian() {
      return this.median;
    }

    public void setMedian(Double median) {
      this.median = median;
    }

    public Double getStandardDeviation() {
      return this.standardDeviation;
    }

    public void setStandardDeviation(Double standardDeviation) {
      this.standardDeviation = standardDeviation;
    }

    public int getCount () {
      return this.count;
    }

    public void setCount (int count) {
      this.count = count; 
    }
    
    public String getHistogramImg () {
      return this.histogramImg;
    }

    public void setHistogramImg (String histogramImg) {
      this.histogramImg = histogramImg;
    }

    public String getBoxPlotImg () {
      return this.boxPlotImg;
    }

    public void setBoxPlotImg (String boxPlotImg) {
      this.boxPlotImg = boxPlotImg;
    }

    public String getDotPlotImg (){
      return this.dotPlotImg;
    }

    public void setDotPlotImg (String dotPlotImg){
      this.dotPlotImg = dotPlotImg;
    }

    public void setStemPlot(String stemPlot) {
      this.stemPlot = stemPlot;
    }

    public String getStemPlot() {
      return this.stemPlot;
    }
}
