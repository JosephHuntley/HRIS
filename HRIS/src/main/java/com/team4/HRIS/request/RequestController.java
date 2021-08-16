package com.team4.HRIS.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "api/v1/request")
public class RequestController {
    private RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService){
        this.requestService = requestService;
    }
    //Create a request by an employee.
    @PostMapping
    public void createRequest(@RequestBody Request request){
        requestService.createRequest(request);
    }
    // View all open requests from an employee
    @GetMapping(path = "/all")
    public ArrayList<Request> viewRequests(){
        return requestService.viewRequests();
    }
    // View all request from a specific employee whether it's open or not
    @GetMapping(path = "/{id}")
    public ArrayList<Request> viewEmpRequests(@PathVariable int id){
        return requestService.viewEmpRequests(id);
    }
    // Update a request by a manager
    @PutMapping
    public void updateRequest(@RequestBody Request request){
        requestService.updateRequest(request);
    }
    // Delete a request based on a request ID
    @DeleteMapping(path = "/{id}")
    public void deleteRequest(@PathVariable int id){
        requestService.deleteRequest(id);
    }
}
