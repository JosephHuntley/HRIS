package com.team4.HRIS.performance;

import com.team4.HRIS.employee.EmployeeConfiguration;
import com.team4.HRIS.request.Request;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class PerformanceService {

    final DataSource ds = EmployeeConfiguration.getDataSource();

    public void createReport(Performance p) {
        // Declaring variables at the top that will be used later in the method as per Java code convention chapter 6.3
        // Declare and initialize the sql statement that will be used below
        final String sql = "INSERT INTO Performance (rating, remarks, employee_id) VALUE(?, ?, ?)";

        try(Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, p.getRating());
            stmt.setString(2, p.getRemarks());
            stmt.setInt(3, p.getEmpId());
            // Execute the creation of a performance report
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Performance viewReport(int id) {
        // Declaring variables at the top that will be used later in the method as per Java code convention chapter 6.3
        Performance p = new Performance();
        ResultSet rs;
        // Declare and initialize the sql statement that will be used below
        final String sql = "SELECT * FROM Performance WHERE employee_id = ?";

        try(Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            // Gather the variables from the result set
            while (rs.next()){

                p.setEmpId(id);
                p.setRating(rs.getInt("rating"));
                p.setRemarks(rs.getString("remarks"));
            }
            return p;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void updateReport(Performance p) {
        // Declaring variables at the top that will be used later in the method as per Java code convention chapter 6.3
        // Declare and initialize the sql statement that will be used below
        final String sql = "UPDATE Performance SET rating = ?, remarks = ? WHERE employee_id = ?";

        try(Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, p.getRating());
            stmt.setString(2, p.getRemarks());
            stmt.setInt(3, p.getEmpId());
            // Execute the update
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void deleteReport(int id) {
        // Declaring variables at the top that will be used later in the method as per Java code convention chapter 6.3
        // Declare and initialize the sql statement that will be used below
        final String sql = "DELETE FROM Performance WHERE employee_id = ?";

        try(Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
