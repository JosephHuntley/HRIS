package com.team4.HRIS.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

// Author - Joseph Huntley
// Team 4
@RestController
@RequestMapping(path = "api/v1/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }

    // Create a new schedule and assign it to a specific employee
    @PostMapping
    public void createSchedule(@RequestBody Schedule schedule){
        scheduleService.createSchedule(schedule);
    }
    // View a schedule based on the employee ID
    @GetMapping(path = "/{id}")
    public Schedule viewSchedule(@PathVariable int id){
        return scheduleService.viewSchedule(id);
    }
    // Update a schedule according to an employee ID
    @PutMapping(path = "/{id}")
    public void updateSchedule(@PathVariable int id, @RequestBody ArrayList<String> list){
        scheduleService.updateSchedule(id, list);
    }
    // Delete a schedule from the DB according to the employee's ID
    @DeleteMapping(path = "/{id}")
    public void deleteSchedule(@PathVariable int id){
        scheduleService.deleteSchedule(id);
    }
}
