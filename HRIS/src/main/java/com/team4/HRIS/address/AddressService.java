package com.team4.HRIS.address;

import com.team4.HRIS.employee.EmployeeConfiguration;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Author - Joseph Huntley
// Team 4
@Service
public class AddressService {

    static DataSource ds = EmployeeConfiguration.getDataSource();

    // Creates a new address and adds it to the db
    public void createAddress(Address address) {
        // Declare and initialize the sql statements that will be used below
        final String sql = "INSERT INTO Address (street, city, state, primary_residence, employee_id) VALUES(?,?,?,?,?)";
        final String selectId = "SELECT MAX(employee_id) as employee_id FROM Employee";
        // Declaring variables at the top that will be used later in the method as per Java code convention chapter 6.3
        int empId = 0;
        // Set up a try/catch block in case of an error
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             PreparedStatement selectStmt = con.prepareStatement(selectId)) {
            ResultSet rs = selectStmt.executeQuery();
            // Gathers the last employee ID from the db
            while (rs.next()) {
                empId = rs.getInt("employee_id");
            }
            // Verify that the employee id was changed from 0
            if (empId != 0) {
                // Insert variables into the prepared statement
                stmt.setString(1, address.getStreet());
                stmt.setString(2, address.getCity());
                stmt.setString(3, address.getState());
                stmt.setBoolean(4, address.isPrimaryResidence());
                stmt.setInt(5, empId);
                // Execute the prepared statement
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Selects every address belonging to a specific employee
    public ArrayList<Address> viewAddress(int id) {
        // Declaring variables at the top that will be used later in the method as per Java code convention chapter 6.3
        ArrayList<Address> addresses = new ArrayList<>();
        Address address = new Address();
        // Declare and initialize the SQL statements that will be used below
        final String sql = "SELECT * FROM Address WHERE employee_id = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            // Gather the address information and add it to the array
            while (rs.next()) {
                address = new Address();
                address.setStreet(rs.getString("street"));
                address.setCity(rs.getString("city"));
                address.setState(rs.getString("state"));
                address.setPrimaryResidence(rs.getBoolean("primary_residence"));
                addresses.add(address);
            }
            return addresses;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Updates a specific address entry for an employee
    public void updateAddress(Address address, int addressId) {
        // Declaring variables at the top that will be used later in the method as per Java code convention chapter 6.3
        String street = null;
        String city = null;
        String state = null;
        String sql;
        PreparedStatement stmt;
        ResultSet rs;
        // Declare and initialize the SQL statement that will be used below
        final String select = "SELECT street, city, state FROM Address WHERE address_id = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement selectStmt = con.prepareStatement(select)) {
            selectStmt.setInt(1, addressId);
            rs = selectStmt.executeQuery();
            // Gather the entry in the db to compare and see what needs to be updated
            while (rs.next()) {
                street = rs.getString("street");
                city = rs.getString("city");
                state = rs.getString("state");
            }
            // If statements to check what needs to be updated
            if (address.getStreet() != street && address.getStreet() != null) {
                sql = "UPDATE Address SET street = ? where address_id = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, address.getStreet());
                stmt.setInt(2, addressId);
                stmt.executeUpdate();
            }
            if (address.getCity() != city && address.getCity() != null) {
                sql = "UPDATE Address SET city = ? where address_id = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, address.getCity());
                stmt.setInt(2, addressId);
                stmt.executeUpdate();
            }
            if (address.getState() != state && address.getState() != null) {
                sql = "UPDATE Address SET state = ? where address_id = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, address.getState());
                stmt.setInt(2, addressId);
                stmt.executeUpdate();
            }
            /* Always updates the boolean value since it is imposible to check if it was changed from the default
             * false or not */
            sql = "UPDATE Address SET primary_residence = ? where address_id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setBoolean(1, address.isPrimaryResidence());
            stmt.setInt(2, addressId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Deletes a specific address entry for an employee
    public void deleteAddress(int id) {
        // Declaring variables at the top that will be used later in the method as per Java code convention chapter 6.3
        // Declare and initialize the SQL statement that will be used below
        final String sql = "DELETE FROM Address WHERE address_id = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
