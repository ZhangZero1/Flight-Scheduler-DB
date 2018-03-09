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
public class BookQuery extends GeneralQuery{
    private PreparedStatement bookPerson;
    private PreparedStatement cancelBooking;
    private PreparedStatement getOccupancy;
    private PreparedStatement getPeopleByDayFlight;
    private PreparedStatement statusPerson;
    
    private PreparedStatement getAllBookings;
    
    private PreparedStatement getPeopleByFlight; 
    private PreparedStatement getFlightOnDay; 
    private PreparedStatement getDaysOfFlight;
    
    private PreparedStatement fixID;
    
    
    public BookQuery(){
        super();
        try{
            fixID = connection.prepareStatement("UPDATE Booking SET FlightID=(FlightID-1) WHERE FlightID>(?)");
            bookPerson = connection.prepareStatement(
                    String.format("%s VALUES( (%s WHERE FlightName = (?)), (%s where DATESTAMP = (?)), (%s WHERE CUSTOMERNAME = (?)))",
                          "INSERT INTO Booking (FLIGHTID, DATEID, CUSTOMERID)",
                          "SELECT FLIGHTID FROM Flight",
                          "SELECT DATEID FROM Date",
                          "SELECT CUSTOMERID FROM Customer"
                    )
            );
            cancelBooking = connection.prepareStatement(
                    String.format("DELETE FROM BOOKING WHERE FlightID=%s AND DateID=%s AND CustomerID=%s",
                          "(SELECT FLIGHTID FROM Flight WHERE FlightName = ?)",
                          "(SELECT DATEID FROM Date WHERE DateStamp = ?)",
                          "(SELECT CUSTOMERID FROM Customer WHERE CustomerName = ?)"
                    )
            );

            getOccupancy = connection.prepareStatement(
                    String.format("SELECT * from Booking %s %s %s WHERE FLIGHTNAME = ? AND DATESTAMP = ?", 
                            "INNER JOIN CUSTOMER ON Booking.CUSTOMERID = Customer.CUSTOMERID",
                            "INNER JOIN Flight ON Booking.FlightID = Flight.FlightID", 
                            "INNER JOIN Date ON Booking.DATEID = DATE.DATEID"
                    ) 
            );
            
            getPeopleByDayFlight = connection.prepareStatement(
                    String.format("SELECT * from Booking %s %s %s WHERE FLIGHTNAME = ? AND DATESTAMP = ?", 
                            "INNER JOIN CUSTOMER ON Booking.CUSTOMERID = Customer.CUSTOMERID",
                            "INNER JOIN Flight ON Booking.FlightID = Flight.FlightID", 
                            "INNER JOIN Date ON Booking.DATEID = DATE.DATEID"
                    ) 
            );
            
            
            statusPerson = connection.prepareStatement(
                    String.format("SELECT * from Booking %s %s %s WHERE CustomerName=?", 
                                  "INNER JOIN CUSTOMER ON Booking.CUSTOMERID = Customer.CUSTOMERID",
                                  "INNER JOIN Flight ON Booking.FlightID = Flight.FlightID", 
                                  "INNER JOIN Date ON Booking.DATEID = DATE.DATEID")
            );
            
            getAllBookings = connection.prepareStatement(
                    "SELECT * from Booking"
            );
            
            getPeopleByFlight = connection.prepareStatement(
                    String.format("SELECT * from Booking %s %s %s WHERE FlightName=?", 
                                  "INNER JOIN CUSTOMER ON Booking.CUSTOMERID = Customer.CUSTOMERID",
                                  "INNER JOIN Flight ON Booking.FlightID = Flight.FlightID", 
                                  "INNER JOIN Date ON Booking.DATEID = DATE.DATEID")
            );
            getFlightOnDay = connection.prepareStatement(
                    String.format("SELECT * from Booking %s %s %s WHERE DateStamp=?", 
                                  "INNER JOIN CUSTOMER ON Booking.CUSTOMERID = Customer.CUSTOMERID",
                                  "INNER JOIN Flight ON Booking.FlightID = Flight.FlightID", 
                                  "INNER JOIN Date ON Booking.DATEID = DATE.DATEID")
            );
            getDaysOfFlight =  connection.prepareStatement(
                    String.format("SELECT * from Booking %s %s %s WHERE FlightName=?", 
                                  "INNER JOIN CUSTOMER ON Booking.CUSTOMERID = Customer.CUSTOMERID",
                                  "INNER JOIN Flight ON Booking.FlightID = Flight.FlightID", 
                                  "INNER JOIN Date ON Booking.DATEID = DATE.DATEID")
            );
        
        }catch(SQLException e){
            e.printStackTrace();
            System.exit(1);
        }
        
    }
    
    protected void fixID(int id){
        super.fixID(fixID, id);
    }
    
    protected void bookPerson(String flight, String date, String name){
        try{            
            bookPerson.setString(1, flight);
            bookPerson.setString(2, date);
            bookPerson.setString(3, name);
            bookPerson.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            handleExecute();
        }
    }
    protected void cancelBooking(String flight, String date, String name){
        try{
            cancelBooking.setString(1, flight);
            cancelBooking.setString(2, date);
            cancelBooking.setString(3, name);
            cancelBooking.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            handleExecute();
        }
    }
    
    protected int getOccupancy(String flight, String date){
        int size = -1;
        ResultSet resultSet = null;
        try{
            getOccupancy.setString(1, flight);
            getOccupancy.setString(2,date);
            size = getSize(getOccupancy)-1;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return size;
    }
    
    protected ArrayList<Customer> getPeopleByDayFlight(String flight, String date){
        ResultSet resultSet = null;
        ArrayList<Customer> allEntries = new ArrayList<>();
        try{
            getPeopleByDayFlight.setString(1,flight);
            getPeopleByDayFlight.setString(2,date);
            resultSet = getPeopleByDayFlight.executeQuery();
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
    
    protected ArrayList<String> getFlightOnDay(String day){
        ResultSet resultSet = null;
        ArrayList<String> flights = new ArrayList<>();
        try{
            getFlightOnDay.setString(1,day);
            resultSet = getFlightOnDay.executeQuery();
            while(resultSet.next()){
                flights.add(resultSet.getString("FlightName"));     
            }
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }finally{
            handleParse(resultSet);
        }
        return flights;
    }
    
    protected ArrayList<String> getPeopleByFlight(String flight){
        ResultSet resultSet = null;
        ArrayList<String> people = new ArrayList<>();
        try{
            getPeopleByFlight.setString(1,flight);
            resultSet = getPeopleByFlight.executeQuery();
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
    
    protected ArrayList<String> getDaysOfFlight(String flight){
        ResultSet resultSet = null;
        ArrayList<String> days = new ArrayList<>();
        try{
            getDaysOfFlight.setString(1,flight);
            resultSet = getDaysOfFlight.executeQuery();
            while(resultSet.next()){
                days.add(resultSet.getString("DateStamp"));     
            }
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }finally{
            handleParse(resultSet);
        }
        return days;
    }
    
    
    
    
    
}
