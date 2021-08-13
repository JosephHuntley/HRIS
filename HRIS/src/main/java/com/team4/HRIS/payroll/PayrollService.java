package com.team4.HRIS.payroll;

import com.team4.HRIS.employee.EmployeeConfiguration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PayrollService {

    static DataSource ds = EmployeeConfiguration.getDataSource();

    public static void createPay(Payroll payroll){
        final String sql = "INSERT INTO Payroll (ishourly, isfulltime, pay_rate, employee_id) VALUES(?, ?, ?, ?)";
        final String selectId = "SELECT MAX(employee_id) as employee_id FROM Employee";
        int empId = 0;

        try(Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            PreparedStatement selectIdStmt = con.prepareStatement(selectId);
            ResultSet rs = selectIdStmt.executeQuery();) {

            while (rs.next()){
                empId = rs.getInt("employee_id");
            }

            // Set the variables for the Payroll table
            stmt.setBoolean(1, payroll.isHourly());
            stmt.setBoolean(2, payroll.isFullTime());
            stmt.setDouble(3, payroll.getPayrate());
            stmt.setInt(4, empId);
            // Execute the payroll update
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
