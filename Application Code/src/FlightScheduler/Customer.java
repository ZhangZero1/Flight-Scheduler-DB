/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerfrederickffz5015;

import java.util.ArrayList;

/**
 *
 * @author Whyer
 */
public class Customer {
    private String name;
    
    private ArrayList<Booking> booking; 
    private ArrayList<Booking> waitlist;
    
    public Customer(String name, Booking[] booking, Booking[] waitlist){
        this.name = name;
        
        this.booking = new ArrayList<>(0);
        this.waitlist = new ArrayList<>(0);
        if(booking!= null){
            for(Booking book: booking){
                this.booking.add(book);
            }
        }
        if(waitlist != null){
            for(Booking book: waitlist){
                this.waitlist.add(book);
            }
        }
    }
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    
    public void addBooking(Booking book){
        booking.add(book);
    }
    public void removeBooking(int index){
        booking.remove(index);
    }
    public Booking[] getBooking(){
        Booking[] booked = new Booking[booking.size()];
        for (int i = 0; i<booked.length;i++){
            booked[i] = booking.get(i);
        }
        return booked;
    }
    public void setBooking(Booking[] booking){
        if(booking != null){
            for(Booking book: booking){
                this.booking.add(book);
            }
        }else{
            this.booking.clear();
        }
    }
    
    public void addWaitlist(Booking waiting){
        waitlist.add(waiting);
    }
    public void removeWaitlist(int index){
        waitlist.remove(index);
    }
    public Booking[] getWaitlist(){
        Booking[] waited = new Booking[waitlist.size()];
        for (int i = 0; i<waited.length;i++){
            waited[i] = waitlist.get(i);
        }
        return waited;
    }
    public void setWaitlist(Booking[] waiting){
        if(waiting != null){
            for(Booking wait: waiting){
                this.waitlist.add(wait);
            }
        }else{
            this.waitlist.clear();
        }
    }
    
    public boolean isWaitlist(int index){
        if(index >= booking.size()){
            return true;
        }else{
            return false;
        }
    }
    
    public Booking getEntry(int index){    
        if(index >= booking.size()){
            return waitlist.get(index-booking.size());
        }else{
            return booking.get(index);
        }
    }
    
    
}
