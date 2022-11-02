package com.nighthawk.spring_portfolio.mvc.stats;

import javax.persistence.*;

@Entity // Annotation to simplify creating an entity, which is a lightweight persistence domain object. Typically, an entity represents a table in a relational database, and each entity instance corresponds to a row in that table.
public class Dataset {
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

    public Dataset() {

    }

    public Dataset(String name, String data, Double mean, Double median, Double standardDeviation, int count, String histogramImg, String boxPlotImg) {
      this.name = name;
      this.data = data;
      this.mean = mean;
      this.median = median;
      this.standardDeviation = standardDeviation;
      this.count = count;
      this.histogramImg = histogramImg;
      this.boxPlotImg = boxPlotImg;
    }

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
}
