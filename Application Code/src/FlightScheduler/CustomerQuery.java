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
public class CustomerQuery extends GeneralQuery{
    
    private PreparedStatement getAllPeople;
    private PreparedStatement addPerson;
    
    private BookQuery book;
    private WaitlistQuery wait;
    
    public CustomerQuery(BookQuery book, WaitlistQuery wait){
        super();
        
        this.book = book;
        this.wait = wait;
        
        try{
            
            
            getAllPeople = connection.prepareStatement("SELECT * FROM CUSTOMER");
            addPerson = connection.prepareStatement(
                    "INSERT INTO Customer (CustomerID, CustomerName) VALUES(?,?)"
            );
            
        }catch(SQLException e){
            e.printStackTrace();
            System.exit(1);
        }
        
    }
    
    protected ArrayList<Customer> getWaitlist(String date){
        return wait.getWaitlist(date);
    }
    
    public ArrayList<Customer> getAllPeople(){
        ResultSet resultSet = null;
        ArrayList<Customer> allEntries = new ArrayList<>();
        try{
            resultSet = getAllPeople.executeQuery();
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
    
    public void addPerson(String name){
        try{
            PreparedStatement CustomerTable = getAllPeople;
            addPerson.setInt(1, getSize(CustomerTable));
            addPerson.setString(2, name);
            addPerson.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            handleExecute();
        }
    }
    
    protected void bookPerson(String flight, String date, String name){
        book.bookPerson(flight, date, name);
    }
    
    protected void cancelBooking(String flight, String date, String name){
        book.cancelBooking(flight, date, name);
    }
    
    protected void bookWait(String flight, String date, String name){
        wait.addWaitlist(name, date, flight);
    }
    protected void cancelWait(String flight, String date, String name){
        wait.removeWaitlist(name, date, flight);
    }
    
    protected ArrayList<Customer> getPeopleByDayFlight(String flight, String date){
        return book.getPeopleByDayFlight(flight, date);
    }
    
    protected ArrayList<Customer> statusPerson(){
        ArrayList<Customer> people = getAllPeople();
        ArrayList<Customer> bookedPeople = new ArrayList<Customer>();
        for(Customer c: people){
            String name = c.getName();
            bookedPeople.add(
                    new Customer(name, 
                book.statusPerson(name).toArray(new Booking[0]),
                wait.statusPerson(name).toArray(new Booking[0]))
            );
            
        }
        return bookedPeople;
        
    }
}
