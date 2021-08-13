package com.team4.HRIS.employee;

import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class EmployeeService {

    static DataSource ds = EmployeeConfiguration.getDataSource();

    // Gets every employee from a specified department
    public static ArrayList<Employee> getAll(int depId) {
        ArrayList<Employee> employees = new ArrayList<>();
        final String sql = "SELECT * FROM Employee JOIN Positions USING(position_id) WHERE department_id = ?";
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);) {

            stmt.setInt(1, depId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Employee e = new Employee();

                e.setId(rs.getInt("employee_id"));
                e.setFirstName(rs.getString("first_name"));
                e.setLastName(rs.getString("last_name"));
                e.setEmail(rs.getString("email"));
                e.setPhone(rs.getString("phone"));
                e.setPassword(rs.getString("password"));
                employees.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public Employee getEmployee(int id) {
        Employee employee = new Employee();
        final String sql = "SELECT * FROM Employee WHERE employee_id = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone(rs.getString("phone"));
                employee.setPassword(rs.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }

    // Inserts a new employee into the database
    public void createEmployee(Employee emp) {
        System.out.println("Test");
        final String[] sql = {"INSERT INTO Employee (first_name, last_name, email, phone, password, position_id, status) " +
                "VALUES(?,?,?,?,?,?, 'Active')",
                "INSERT INTO Address (street, city, state, primary_residence, employee_id) VALUES(?,?,?,?,?)"};
        final String selectId = "SELECT MAX(employee_id) as employee_id FROM Employee";
        int empId = 0;

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql[0]);
             PreparedStatement stmt1 = con.prepareStatement(sql[1]);) {

            // Set the variables for the employee table
            stmt.setString(1, emp.getFirstName());
            stmt.setString(2, emp.getLastName());
            stmt.setString(3, emp.getEmail());
            stmt.setString(4, emp.getPhone());
            stmt.setString(5, sha1Hash(emp.getPassword()));
            stmt.setInt(6, emp.getPosition_id());
            //Execute the employee update
            stmt.executeUpdate();

            // Gather the newly created employee's id
            PreparedStatement selectIdStmt = con.prepareStatement(selectId);
            ResultSet rs = selectIdStmt.executeQuery();
            while (rs.next()) {
                empId = rs.getInt("employee_id");
            }

            // Set the variables for the Address table
            stmt1.setString(1, emp.getAddress().getStreet());
            stmt1.setString(2, emp.getAddress().getCity());
            stmt1.setString(3, emp.getAddress().getState());
            stmt1.setBoolean(4, emp.getAddress().isPrimaryResidence());
            stmt1.setInt(5, empId);
            // Execute the address update
            stmt1.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String sha1Hash(String input) {

        try {

            // getInstance() method is called with algorithm SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 40) {

                hashtext = "0" + hashtext;
            }
            // return the HashText
            return hashtext;
        }
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Deletes an employee from the database
    public void deleteEmployee(int empId) {
        final String sql = "DELETE FROM Employee where employee_id = ?";

        try(Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);){

            stmt.setInt(1, empId);
            stmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    // Updates an employee in the database
    public void updateEmployee(Employee employee) {
        final String sql = "UPDATE Employee SET ? = ? WHERE employee_id = ?";
    }
}

