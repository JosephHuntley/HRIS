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

// Author - Joseph Huntley
// Team 4
@Service
public class EmployeeService {

    static DataSource ds = EmployeeConfiguration.getDataSource();

    // Gets every employee from a specified department
    public static ArrayList<Employee> getAll(int depId) {
        // Declaring variables at the top that will be used later in the method as per Java code convention chapter 6.3
        ArrayList<Employee> employees = new ArrayList<>();
        Employee e;
        ResultSet rs;
        // Declare and initialize the sql statement that will be used below
        final String sql = "SELECT * FROM Employee JOIN Positions USING(position_id) WHERE department_id = ?";
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, depId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                // Create a new employee each time the loop completes so that it doesn't overwrite the previous employee
                e = new Employee();

                e.setId(rs.getInt("employee_id"));
                e.setFirstName(rs.getString("first_name"));
                e.setLastName(rs.getString("last_name"));
                e.setEmail(rs.getString("email"));
                e.setPhone(rs.getString("phone"));
                e.setPassword(rs.getString("password"));
                employees.add(e);
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return employees;
    }

    public static String sha1Hash(String input) {
        // TODO: May remove from sever side and preform on the client side to prevent passwords from being sent unsecure
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

    // Gets a single employee based upon their ID
    public Employee getEmployee(int id) {
        // Declaring variables at the top that will be used later in the method as per Java code convention chapter 6.3
        Employee employee = new Employee();
        ResultSet rs;
        // Declare and initialize the sql statement that will be used below
        final String sql = "SELECT * FROM Employee WHERE employee_id = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            rs = stmt.executeQuery();

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
        // Declaring variables at the top that will be used later in the method as per Java code convention chapter 6.3
        // Declare and initialize the sql statement that will be used below
        final String sql = "INSERT INTO Employee (first_name, last_name, email, phone, password, position_id, status) " +
                "VALUES(?,?,?,?,?,?, 'Active')";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            // Set the variables for the employee table
            stmt.setString(1, emp.getFirstName());
            stmt.setString(2, emp.getLastName());
            stmt.setString(3, emp.getEmail());
            stmt.setString(4, emp.getPhone());
            stmt.setString(5, emp.getPassword());
            stmt.setInt(6, emp.getPosition_id());
            //Execute the employee update
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Deletes an employee from the database
    public void deleteEmployee(int empId) {
        // Declaring variables at the top that will be used later in the method as per Java code convention chapter 6.3
        // Declare and initialize the sql statement that will be used below
        final String sql = "DELETE FROM Employee where employee_id = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, empId);
            stmt.executeUpdate();

        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    // Updates an employee in the database
    public void updateEmployee(Employee employee) {
        // Declaring variables at the top that will be used later in the method as per Java code convention chapter 6.3
        String firstName = null;
        String lastName = null;
        String email = null;
        String phone = null;
        String password = null;
        String sql;
        ResultSet rs;
        // Declare and initialize the sql statement that will be used below
        final String selectId = "SELECT * FROM Employee WHERE employee_id = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement select = con.prepareStatement(selectId)) {
            select.setInt(1, employee.getId());
            rs = select.executeQuery();

            // Gathers the data to compare with in the if statements below
            while (rs.next()) {
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                email = rs.getString("email");
                phone = rs.getString("phone");
                password = rs.getString("password");
            }


            // A set of if statements to determine what needs to be updated in the database
            if (!employee.getFirstName().equals(firstName) && employee.getFirstName() != null) {
                sql = "UPDATE Employee SET first_name = ? WHERE employee_id = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(2, employee.getId());
                stmt.setString(1, employee.getFirstName());

                stmt.executeUpdate();
            }
            if (!employee.getLastName().equals(lastName) && employee.getLastName() != null) {
                sql = "UPDATE Employee SET last_name = ? WHERE employee_id = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(2, employee.getId());
                stmt.setString(1, employee.getLastName());
                stmt.executeUpdate();
            }
            if (!employee.getEmail().equals(email) && employee.getEmail() != null) {
                sql = "UPDATE Employee SET email = ? WHERE employee_id = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(2, employee.getId());
                stmt.setString(1, employee.getEmail());
                stmt.executeUpdate();
            }
            if (!employee.getPhone().equals(phone) && employee.getPhone() != null) {
                sql = "UPDATE Employee SET phone = ? WHERE employee_id = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(2, employee.getId());
                stmt.setString(1, employee.getPhone());
                stmt.executeUpdate();
            }
            if (!employee.getPassword().equals(password) && employee.getPassword() != null) {
                sql = "UPDATE Employee SET password = ? WHERE employee_id = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(2, employee.getId());
                stmt.setString(1, employee.getPhone());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

