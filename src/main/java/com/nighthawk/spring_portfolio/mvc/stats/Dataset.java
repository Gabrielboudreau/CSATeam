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

    public Dataset() {

    }

    public Dataset(String name, String data, Double mean, Double median, Double standardDeviation) {
      this.name = name;
      this.data = data;
      this.mean = mean;
      this.median = median;
      this.standardDeviation = standardDeviation;
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
}
