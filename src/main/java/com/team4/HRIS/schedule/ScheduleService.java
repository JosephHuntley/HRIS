package com.team4.HRIS.schedule;

import com.team4.HRIS.employee.EmployeeConfiguration;
import org.springframework.stereotype.Service;
// Author - Joseph Huntley
// Team 4
import javax.sql.DataSource;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

@Service
public class ScheduleService {

    static DataSource ds = EmployeeConfiguration.getDataSource();

    public void createSchedule(Schedule s) {
        // Declaring variables at the top that will be used later in the method as per Java code convention chapter 6.3
        // Declare and initialize the sql statement that will be used below
        final String sql = "INSERT INTO WorkSchedule (`mon_start`, `mon_end`, `tue_start`, `tue_end`, `wed_start`, " +
                "`wed_end`, `thur_start`, `thur_end`, `fri_start`, `fri_end`, `sat_start`, `sat_end`, `sun_start`, " +
                "`sun_end`, `employee_id`) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, s.getMonStart());
            stmt.setString(2, s.getMonEnd());
            stmt.setString(3, s.getTueStart());
            stmt.setString(4, s.getTueEnd());
            stmt.setString(5, s.getWedStart());
            stmt.setString(6, s.getWedEnd());
            stmt.setString(7, s.getThurStart());
            stmt.setString(8, s.getThurEnd());
            stmt.setString(9, s.getFriStart());
            stmt.setString(10, s.getFriEnd());
            stmt.setString(11, s.getSatStart());
            stmt.setString(12, s.getSatEnd());
            stmt.setString(13, s.getSunStart());
            stmt.setString(14, s.getSunEnd());
            stmt.setInt(15, s.getId());
            // Execute the sql statement
            stmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Schedule viewSchedule(int id) {
        // Declaring variables at the top that will be used later in the method as per Java code convention chapter 6.3
        Schedule s = new Schedule();
        ResultSet rs;
        // Declare and initialize the sql statement that will be used below
        final String sql = "SELECT * FROM workSchedule WHERE employee_id = ?";

        try(Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){

            // Set the employee ID and execute the query
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            // Gathers the needed data from the query
            while (rs.next()){

                s.setMonStart(rs.getString("mon_start"));
                s.setMonEnd(rs.getString("mon_end"));
                s.setTueStart(rs.getString("tue_start"));
                s.setTueEnd(rs.getString("tue_end"));
                s.setWedStart(rs.getString("wed_start"));
                s.setWedEnd(rs.getString("wed_end"));
                s.setThurStart(rs.getString("thur_start"));
                s.setThurEnd(rs.getString("thur_end"));
                s.setFriStart(rs.getString("fri_start"));
                s.setFriEnd(rs.getString("fri_end"));
                s.setSatStart(rs.getString("sat_start"));
                s.setSatEnd(rs.getString("sat_end"));
                s.setSunStart(rs.getString("sun_start"));
                s.setSunEnd(rs.getString("sun_end"));
            }
            return s;
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateSchedule(int id, ArrayList<String> list) {
        // Declaring variables at the top that will be used later in the method as per Java code convention chapter 6.3
        String sql;
        PreparedStatement stmt;
        String day = list.get(0);
        String start = list.get(1);
        String end = list.get(2);


        try(Connection con = ds.getConnection()){

            switch (day.toLowerCase(Locale.ROOT)){
                case "sun":
                    sql = "UPDATE workSchedule SET sun_start = ?, sun_end = ? WHERE employee_id = ?";
                    break;
                case "mon":
                    sql = "UPDATE workSchedule SET mon_start = ?, mon_end = ? WHERE employee_id = ?";
                    break;
                case "tue":
                    sql = "UPDATE workSchedule SET tue_start = ?, tue_end = ? WHERE employee_id = ?";
                    break;
                case "wed":
                    sql = "UPDATE workSchedule SET wed_start = ?, wed_end = ? WHERE employee_id = ?";
                    break;
                case "thur":
                    sql = "UPDATE workSchedule SET thur_start = ?, thur_end = ? WHERE employee_id = ?";
                    break;
                case "fri":
                    sql = "UPDATE workSchedule SET fri_start = ?, fri_end = ? WHERE employee_id = ?";
                    break;
                case "sat":
                    sql = "UPDATE workSchedule SET sat_start = ?, sat_end = ? WHERE employee_id = ?";
                    break;
                default:
                    throw new IllegalStateException("Not a valid day");
            }
            stmt = con.prepareStatement(sql);
            stmt.setString(1, start);
            stmt.setString(2, end);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteSchedule(int id) {
        // Declaring variables at the top that will be used later in the method as per Java code convention chapter 6.3
        // Declare and initialize the sql statement that will be used below
        String sql = "DELETE FROM workSchedule WHERE employee_id = ?";
        try(Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
