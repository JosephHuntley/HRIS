package com.team4.HRIS.payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
// Author - Joseph Huntley
// Team 4
@RestController
@RequestMapping(path = "api/v1/payroll")
public class PayrollController {
    private final PayrollService payrollService;


    @Autowired
    public PayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    // View a paystub
    @GetMapping(path = "/view/{id}")
    public Paystub viewPaystub(@PathVariable int id){
        return payrollService.viewPaystub(id);
    }
    // Create a new entry into the payroll table
    @PostMapping(path = "/new")
    public void createPayroll(@RequestBody Paystub payroll) {
        payrollService.createPay(payroll);
    }
    // Delete an entry from the payroll table
    @DeleteMapping(path = "/delete/{id}")
    public void deletePaystub(@PathVariable int id) {
        payrollService.deletePaystub(id);
    }
    // Updates the payroll
    @PutMapping(path = "/update/{empId}")
    public void updatePayroll(@RequestBody Paystub payroll, @PathVariable int empId){
        payrollService.updatePayroll(payroll, empId);
    }
}
