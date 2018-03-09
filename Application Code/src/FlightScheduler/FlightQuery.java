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
import javax.swing.JOptionPane;

/**
 *
 * @author Whyer
 */
public class FlightQuery extends GeneralQuery{

    private PreparedStatement getFlightID;
    private PreparedStatement addFlight;
    private PreparedStatement dropFlight;
    private PreparedStatement getAllFlights;
    private PreparedStatement getCapacity;
    private PreparedStatement fixID;
    
    BookQuery book;
    WaitlistQuery wait;

    public FlightQuery(BookQuery book, WaitlistQuery wait){
        super();
        
        this.book = book;
        this.wait = wait;
        
        try{            
            getFlightID = connection.prepareStatement("SELECT * FROM FLIGHT WHERE FlightName=?");
            getAllFlights = connection.prepareStatement("SELECT * FROM FLIGHT");
            addFlight = connection.prepareStatement(
                      "INSERT INTO Flight (FlightID, FlightName, Capacity) VALUES(?,?,?)");
            dropFlight = connection.prepareStatement("DELETE FROM FLIGHT WHERE FlightName=?");
            getCapacity = connection.prepareStatement("SELECT * FROM FLIGHT WHERE FlightName=?");
            fixID = connection.prepareStatement("UPDATE Flight SET FlightID=(FlightID-1) WHERE FlightID>(?)");
        }catch(SQLException e){
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    
    public ArrayList<Flight> getAllFlights(){
        ResultSet resultSet = null;
        ArrayList<Flight> allEntries = new ArrayList<>();
        try{
            resultSet = getAllFlights.executeQuery();
            while(resultSet.next()){
                allEntries.add(
                    new Flight(
                               resultSet.getInt("Capacity"),
                               resultSet.getString("FlightName")
                               )
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
    
    public void addFlight(String name, int capacity){
        try{
            PreparedStatement FlightTable = getAllFlights;
            addFlight.setInt(1, getSize(FlightTable));
            addFlight.setString(2, name);
            addFlight.setInt(3, capacity);
            addFlight.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            handleExecute();
        }
    }
    public void dropFlight(String name){
        ResultSet resultSet = null;
        try{
            getFlightID.setString(1, name);
            resultSet = getFlightID.executeQuery();
            resultSet.next();
            int id = resultSet.getInt("FlightID");
            
            int capacity;
            int occupancy;
            
            for(String d: book.getDaysOfFlight(name)){
                ArrayList<Customer> customer= book.getPeopleByDayFlight(name, d);
                ArrayList<Flight> flight = getAllFlights();
                
                capacity = this.getCapacity(flight.get(0).getName());
                occupancy = book.getOccupancy(flight.get(0).getName(), d);
                while(flight.size() != 0){
                    
                    if(customer.size() == 0){
                        break;
                    }
                    if(!flight.get(0).equals(name)){
                        if(occupancy == capacity){
                            flight.remove(0);
                            if(flight.size() != 0){
                                capacity = this.getCapacity(flight.get(0).getName());
                                occupancy = book.getOccupancy(flight.get(0).getName(), d);
                            }
                        }else{
                            book.bookPerson(flight.get(0).getName(), d, customer.get(0).getName());
                            book.cancelBooking(name, d, customer.get(0).getName());
                            customer.remove(0);
                            occupancy += 1;
                        }
                    }
                    else{
                        flight.remove(0);
                        if(flight.size() != 0){
                            capacity = this.getCapacity(flight.get(0).getName());
                            occupancy = book.getOccupancy(flight.get(0).getName(), d);
                        }
                    }
                }
                for(Customer c: customer){
                    JOptionPane.showMessageDialog(null, c.getName() + " got unbooked", "booking issue",
                                          JOptionPane.INFORMATION_MESSAGE);
                    book.cancelBooking(name, d, c.getName());
                }
                for(String c: wait.getPeopleOnDayFlight(d, name)){
                    JOptionPane.showMessageDialog(null, c + " got de-waitlisted", "booking issue",
                                          JOptionPane.INFORMATION_MESSAGE);
                    wait.removeWaitlist(c, d, name);
                }
            }
            // drop flight last
            dropFlight.setString(1, name);
            dropFlight.executeUpdate();
            // drop flight last
            fixID(fixID, id);
            book.fixID(id);
            wait.fixID(id);
            
            
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            handleParse(resultSet);
        }
    }
    
    protected int getCapacity(String flight){
        ResultSet resultSet = null;
        try{
            getCapacity.setString(1, flight);
            resultSet = getCapacity.executeQuery();
            resultSet.next();
            return resultSet.getInt("Capacity");
            
        }catch(SQLException e){
            e.printStackTrace();
            return -1;
        }finally{
            handleParse(resultSet);
        }
    }
    
    protected int getOccupancy(String flight, String date){
        return book.getOccupancy(flight, date);
    }
    
    
}
