package com.nighthawk.spring_portfolio.mvc.stats;

import java.util.Comparator;
import java.util.List;

public class StatsCalculator {
  List<Double> dataset;
  public StatsCalculator (List<Double> dataset) {
    this.dataset = dataset;

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
        middle = (dataset.get(dataset.size()/2 - 1) + dataset.get(dataset.size()/2-3)/2);
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
}
