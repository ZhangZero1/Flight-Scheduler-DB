/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerfrederickffz5015;

/**
 *
 * @author Whyer
 */
public class Booking {
    private Flight flight;
    private Date date;
    public Booking(Flight flight, Date date){
        this.flight = flight;
        this.date = date;
    }
    
    public void setDate(Date date){
        this.date = date;
    }
    public Date getDate(){
        return date;
    }
    
    public void setFlight(Flight flight){
        this.flight = flight;
    }
    public Flight getFlight(){
        return flight;
    }
    
    public void setBooking(Flight flight, Date date){
        setFlight(flight);
        setDate(date);
    }
    

}
