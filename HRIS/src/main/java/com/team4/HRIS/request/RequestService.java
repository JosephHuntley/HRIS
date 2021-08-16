package com.team4.HRIS.request;

import com.team4.HRIS.employee.EmployeeConfiguration;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class RequestService {

    static DataSource ds = EmployeeConfiguration.getDataSource();

    public void createRequest(Request request){
        // Declaring variables at the top that will be used later in the method as per Java code convention chapter 6.3
        int type;
        // Declare and initialize the sql statement that will be used below
        final String sql = "INSERT INTO requests (`request_details`, `date_of_request`, `is_request_open`, " +
                "`requesting_employee`, `request_type_id`) VALUES(?, ?, 1, ?, ?)";

        try(Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, request.getReqDetails());
            stmt.setString(2, request.getStartDate());
            stmt.setInt(3, request.getEmpId());

            // Switch the request type to ID
            switch (request.getType().toLowerCase()){
                case "vacation":
                    type = 1;
                    break;
                case "sick leave":
                    type = 2;
                    break;
                case "emergency":
                    type = 3;
                    break;
                case "retirement":
                    type = 4;
                    break;
                case "benefits":
                    type = 5;
                    break;
                case "assistance":
                    type = 6;
                    break;
                case "salary":
                    type = 7;
                    break;
                case "other":
                    type = 8;
                    break;
                default:
                    throw new IllegalStateException();
            }
            stmt.setInt(4,type);
            // Execute the creation
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Request> viewRequests() {
        // Declaring variables at the top that will be used later in the method as per Java code convention chapter 6.3
        ArrayList<Request> requests = new ArrayList<>();
        Request r;
        ResultSet rs;
        //Declare and initialize the sql statement that will be used below
        String sql = "SELECT requesting_employee, request_details, date_of_request, CONCAT(first_name, ' ', last_name) " +
                "AS name, request_type FROM Requests JOIN RequestType USING(request_type_id) INNER JOIN Employee ON " +
                "requests.requesting_employee = Employee.employee_id WHERE is_request_open = 1";

        try(Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            rs = stmt.executeQuery();
            // Gather the open employee requests
            while (rs.next()){
                r = new Request();
                r.setEmpId(rs.getInt("requesting_employee"));
                r.setName(rs.getString("name"));
                r.setStartDate(rs.getString("date_of_request"));
                r.setReqDetails(rs.getString("request_details"));
                r.setType(rs.getString("request_type"));
                requests.add(r);
            }
            return requests;
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Request> viewEmpRequests(int id) {
        // Declaring variables at the top that will be used later in the method as per Java code convention chapter 6.3
        ArrayList<Request> requests = new ArrayList<>();
        Request r;
        ResultSet rs;
        // Declare and initialize the SQL statement that will be used below
        final String sql = "SELECT request_details, date_of_request, CONCAT(first_name, ' ', last_name)" +
                " AS name, request_type FROM Requests JOIN RequestType USING(request_type_id) INNER JOIN Employee ON" +
                " requests.requesting_employee = Employee.employee_id WHERE requesting_employee = ?";

        try(Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            // Gather the open employee requests
            while (rs.next()){
                r = new Request();
                r.setEmpId(id);
                r.setName(rs.getString("name"));
                r.setStartDate(rs.getString("date_of_request"));
                r.setReqDetails(rs.getString("request_details"));
                r.setType(rs.getString("request_type"));
                requests.add(r);
            }
            return requests;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
