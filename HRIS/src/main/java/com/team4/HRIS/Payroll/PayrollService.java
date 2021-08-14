package com.team4.HRIS.Payroll;

import com.team4.HRIS.employee.EmployeeConfiguration;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// Author - Joseph Huntley
// Team 4
@Service
public class PayrollService {

    static DataSource ds = EmployeeConfiguration.getDataSource();

    public void createPay(Paystub payroll){
        final String sql = "INSERT INTO Payroll (ishourly, isfulltime, pay_rate, employee_id) VALUES(?, ?, ?, ?)";
        final String selectId = "SELECT MAX(employee_id) as employee_id FROM Employee";
        int empId = 0;

        try(Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            PreparedStatement selectIdStmt = con.prepareStatement(selectId);
            ResultSet rs = selectIdStmt.executeQuery()) {

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

    public void deletePaystub(int paystubId) {
        final String sql = "DELETE FROM Payroll WHERE payroll_id = ?";

        try(Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, paystubId);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public Paystub viewPaystub(int id) {
        final String sql ="SELECT * FROM Paystub WHERE `Paystub ID` = ?";
        Paystub paystub = new Paystub();

        try(Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                paystub.setId(id);
                paystub.setName(rs.getString("Name"));
                paystub.setHours(rs.getInt("Clocked Hours"));
                paystub.setHourly(rs.getBoolean("Is Hourly?"));
                paystub.setPayrate(rs.getInt("Payrate"));
                // This doesn't require a querry from the db because it is a calculation preformed by Java
                paystub.setNetpay();
                paystub.sethPlan(rs.getInt("Health Plan Deduction"));
                paystub.setvPlan(rs.getInt("Vision Plan Deduction"));
                paystub.setdPlan(rs.getInt("Dental Plan Deduction"));
            }
            return paystub;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void updatePayroll(Paystub payroll, int empId) {
        final String sql = "SELECT * FROM Payroll WHERE 'ID' = ?";
        String update;
        boolean hourly = false, fullTime = false;
        double payRate = 0;

        try(Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, empId);
            ResultSet rs = stmt.executeQuery();
            // Gathers the values from the db to check what needs to be updated
            while(rs.next()){
                payRate = rs.getDouble("pay_rate");

            }
            // If statements to see if it needs to be updated
            if(payroll.getPayrate() > 0){
                update = "UPDATE Payroll SET pay_rate = ?, ishourly = ?, isfulltime = ? WHERE employee_id = ?";
                PreparedStatement ps = con.prepareStatement(update);
                ps.setDouble(1, payroll.getPayrate());
                ps.setBoolean(2, payroll.isHourly());
                ps.setBoolean(3, payroll.isFullTime());
                ps.setInt(4,empId);
                ps.executeUpdate();
            }else {
                update = "UPDATE Payroll SET ishourly = ?, isfulltime = ? WHERE employee_id = ?";
                PreparedStatement ps = con.prepareStatement(update);
                ps.setBoolean(1, payroll.isHourly());
                ps.setBoolean(2, payroll.isFullTime());
                ps.setInt(3,empId);
                ps.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
