package com.team4.HRIS.Address;

import com.team4.HRIS.employee.EmployeeConfiguration;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
// Author - Joseph Huntley
// Team 4
@Service
public class AddressService {

    static DataSource ds = EmployeeConfiguration.getDataSource();

    public void createAddress(Address address){
        final String sql = "INSERT INTO Address (street, city, state, primary_residence, employee_id) VALUES(?,?,?,?,?)";

    }
}
