package com.nighthawk.spring_portfolio.mvc.stats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/stats")
public class StatsApiController {

    // Autowired enables Control to connect HTML and POJO Object to database easily for CRUD operations
    @Autowired
    private DataJpaRepository repository;

    /*
    GET List of Datasets
     */
    @CrossOrigin("*")
    @GetMapping("/all")
    public ResponseEntity<List<Dataset>> getJokes() {
        return new ResponseEntity<>( repository.findAll(), HttpStatus.OK);
    }

    /*
    GET Specific Dataset
     */
    @CrossOrigin("*")
    @GetMapping("/dataset/{name}")
    public ResponseEntity<List<Dataset>> getJokes(@PathVariable String name) {
        return new ResponseEntity<>( repository.findByNameIgnoreCase(name), HttpStatus.OK);
    }

     /* 
      PUT a new dataset
     */
    @CrossOrigin("*")
    @PostMapping("/new")
    public ResponseEntity<Dataset> newDataset(@RequestParam(name="data") List<Double> data, @RequestParam(name="name") String name) {
      StatsCalculator calc = new StatsCalculator(data, name);
      Dataset dataset = new Dataset(name, calc.toString(), calc.getMean(), calc.getMedian(), calc.getSD(), data.size(), calc.getHistogram(), calc.getBoxPlot());
      System.out.println(calc.getDotPlot());
      repository.save(dataset);
      return new ResponseEntity<>(dataset, HttpStatus.OK);
    }


}
