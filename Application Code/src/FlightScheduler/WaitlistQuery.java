/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerfrederickffz5015;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Whyer
 */
public class WaitlistQuery extends GeneralQuery{
    
    private PreparedStatement addWaitlist;
    private PreparedStatement removeWaitlist;
    private PreparedStatement getWaitlist;
    
    private PreparedStatement statusPerson;
    
    private PreparedStatement getPeopleOnDayFlight;
    
    private PreparedStatement fixID;
    
    public WaitlistQuery(){
        super();
        
        try{     
            fixID = connection.prepareStatement("UPDATE Waitlist SET FlightID=(FlightID-1) WHERE FlightID>(?)");
            
            addWaitlist = connection.prepareStatement(
                    String.format("INSERT INTO WAITLIST (CustomerID, DateID, FlightID) VALUES (%s,%s,%s)",
                          "(SELECT CustomerID FROM Customer WHERE CustomerName=?)",
                          "(SELECT DATEID FROM Date WHERE Datestamp=?)",
                          "(SELECT FLIGHTID FROM Flight WHERE FlightName=?)"
                    )
            );
            removeWaitlist = connection.prepareStatement(
                    String.format("DELETE FROM Waitlist WHERE FlightID=%s AND DateID=%s AND CustomerID=%s",
                          "(SELECT FLIGHTID FROM Flight WHERE FlightName = ?)",
                          "(SELECT DATEID FROM Date WHERE DateStamp = ?)",
                          "(SELECT CUSTOMERID FROM Customer WHERE CustomerName = ?)"
                    )
            );
            
            getWaitlist = connection.prepareStatement(
                    String.format("SELECT * FROM Waitlist %s %s %s where DateStamp =?",
                                   "INNER JOIN DATE on Date.DATEID = Waitlist.DateID",
                                   "INNER JOIN Customer on Customer.CUSTOMERID = Waitlist.CUSTOMERID",
                                   "INNER JOIN Flight on Flight.FLIGHTID = Waitlist.FLIGHTID"
                    )
            );
            
            getPeopleOnDayFlight = connection.prepareStatement(
                    String.format("SELECT * FROM Waitlist %s %s %s where Datestamp =? AND FlightName =?",
                                   "INNER JOIN DATE on Date.DATEID = Waitlist.DateID",
                                   "INNER JOIN Customer on Customer.CUSTOMERID = Waitlist.CUSTOMERID",
                                   "INNER JOIN Flight on Flight.FLIGHTID = Waitlist.FLIGHTID"
                    )
            );
            
            statusPerson = connection.prepareStatement(
                    String.format("SELECT * from Waitlist %s %s %s WHERE CustomerName=?", 
                                  "INNER JOIN CUSTOMER ON Waitlist.CUSTOMERID = Customer.CUSTOMERID",
                                  "INNER JOIN Flight ON Waitlist.FlightID = Flight.FlightID", 
                                  "INNER JOIN Date ON Waitlist.DATEID = DATE.DATEID")
            );
            
        }catch(SQLException e){
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    protected void fixID(int id){
        super.fixID(fixID, id);
    }
    
    protected void addWaitlist(String customer, String date, String flight){
        try{           
            addWaitlist.setString(1, customer);
            addWaitlist.setString(2, date);
            addWaitlist.setString(3, flight);
            addWaitlist.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            handleExecute();
        }
    }
    
    protected void removeWaitlist(String customer, String date, String flight){
        try{           
            
            removeWaitlist.setString(1, flight);
            removeWaitlist.setString(2, date);
            removeWaitlist.setString(3, customer);
            
            removeWaitlist.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            handleExecute();
        }
        
        
        
    }
    protected ArrayList<Customer> getWaitlist(String date){
        ResultSet resultSet = null;
        ArrayList<Customer> allEntries = new ArrayList<>();
        try{
            getWaitlist.setString(1, date);
            resultSet = getWaitlist.executeQuery();
            while(resultSet.next()){
                allEntries.add(
                    new Customer(
                                 resultSet.getString("CustomerName"),
                                 null,null)
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
    
    protected ArrayList<String> getPeopleOnDayFlight(String date,String flight){
        ResultSet resultSet = null;
        ArrayList<String> people = new ArrayList<>();
        try{
            getPeopleOnDayFlight.setString(1,date);
            getPeopleOnDayFlight.setString(2,flight);
            resultSet = getPeopleOnDayFlight.executeQuery();
            while(resultSet.next()){
                people.add(resultSet.getString("CustomerName"));     
            }
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }finally{
            handleParse(resultSet);
        }
        return people;
    }
    
    protected ArrayList<Booking> statusPerson(String name){
        ResultSet resultSet = null;
        ArrayList<Booking> customer = new ArrayList<>();
        try{
            statusPerson.setString(1,name);
            resultSet = statusPerson.executeQuery();
            while(resultSet.next()){
                customer.add(new Booking(
                    new Flight(resultSet.getInt("Capacity"), resultSet.getString("FlightName")),
                    new Date( resultSet.getString("DateStamp"))  
                )
                );      
            }
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }finally{
            handleParse(resultSet);
        }
        return customer;
    }
}
