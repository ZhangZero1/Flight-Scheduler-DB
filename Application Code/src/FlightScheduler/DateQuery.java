/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerfrederickffz5015;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Whyer
 */
public class DateQuery extends GeneralQuery{
    private static final String URL = "jdbc:derby://localhost:1527/FlightSchedulerDBFrederickffz5015";
    private static final String USERNAME = "java";
    private static final String PASSWORD = "java";
    
    private Connection connection;
    
    private PreparedStatement addDate;
    private PreparedStatement getAllDates;
    
    public DateQuery(){
        super();
        try{            
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            getAllDates= connection.prepareStatement("SELECT * FROM Date");
            addDate = connection.prepareStatement(
                    "INSERT INTO Date (DateID, DateStamp) VALUES(?,?)"
            );
        }catch(SQLException e){
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    public ArrayList<Date> getAllDates(){
        ResultSet resultSet = null;
        ArrayList<Date> allEntries = new ArrayList<>();
        try{
            resultSet = getAllDates.executeQuery();
            while(resultSet.next()){
                allEntries.add(
                    new Date(resultSet.getString("DateStamp"))
                );      
            }
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }finally{
            handleParse(resultSet);
        }
        return allEntries;
    }
    
    public void addDate(String date){
        try{
            PreparedStatement DateTable = getAllDates;
            addDate.setInt(1, getSize(DateTable));
            addDate.setString(2, date);
            addDate.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            handleExecute();
        }
    }
}
