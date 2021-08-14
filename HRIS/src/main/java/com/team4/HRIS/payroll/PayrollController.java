package com.team4.HRIS.payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/payroll")
public class PayrollController {
    private final PayrollService payrollService;


    @Autowired
    public PayrollController(PayrollService payrollService){
        this.payrollService = payrollService;
    }

    @PostMapping(path = "/new")
    public void createPayroll(@RequestBody Payroll payroll){
        payrollService.createPay(payroll);
    }
}
