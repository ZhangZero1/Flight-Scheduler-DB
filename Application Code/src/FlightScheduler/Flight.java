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
public class Flight {
    private int capactiy;
    private String name;
    
    public Flight(int capacity, String name){
        this.capactiy = capacity;
        this.name = name;
    }
    

    public void setCapacity(int capacity){
        this.capactiy = capacity;
    }
    public int getCapacity(){
        return capactiy;
    }
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    
}
