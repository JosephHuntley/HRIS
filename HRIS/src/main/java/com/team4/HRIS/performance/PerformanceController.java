package com.team4.HRIS.performance;

import com.team4.HRIS.request.Request;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/performance")
public class PerformanceController {

    private final PerformanceService performanceService;

    public PerformanceController(PerformanceService performanceService){
        this.performanceService = performanceService;
    }

    // Create a new performance report
    @PostMapping
    public void createReport(@RequestBody Performance performance){
        performanceService.createReport(performance);
    }
    // View an employee's performance report
    @GetMapping(path = "/{id}")
    public Performance viewReport(@PathVariable int id){
        return performanceService.viewReport(id);
    }
    // Update an employee's performance report
    @PutMapping
    public void updateReport(@RequestBody Performance performance){
        performanceService.updateReport(performance);
    }
    // Delete an employee's performace report based upon their ID
    @DeleteMapping(path = "/{id}")
    public void deleteReport(@PathVariable int id){
        performanceService.deleteReport(id);
    }
}
