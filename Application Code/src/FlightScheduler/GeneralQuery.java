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

/**
 *
 * @author Whyer
 */
public class GeneralQuery {
    
    private static final String URL = "jdbc:derby://localhost:1527/FlightSchedulerDBFrederickffz5015";
    private static final String USERNAME = "java";
    private static final String PASSWORD = "java";
    
    protected Connection connection;
    
    public GeneralQuery(){
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
        }catch(SQLException e){
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    protected int getSize(PreparedStatement query){
        int size = -1;
        ResultSet resultSet = null;
        try{
            resultSet = query.executeQuery();
            
            size = 0;
            while(resultSet.next()){
                size +=1;
            }
            size += 1;
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            handleParse(resultSet);
        }
        return size;
    }
    
    // reformat all ids   --check--
    // remove excess ids --still left--  // try making new query classes for booking and waitlist
    protected void fixID(PreparedStatement fixID, int id){
        int size = 0;
        try{
            //"UPDATE %s SET %s=(%s-1) WHERE %s>(?)"
            fixID.setInt(1, id);
            fixID.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            handleExecute();
        }
    }
    
    protected void handleParse(ResultSet resultSet){
        try{
            resultSet.close();
        }catch(SQLException e){
            e.printStackTrace();
            try{
                connection.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    
    protected void handleExecute(){
        try{
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
