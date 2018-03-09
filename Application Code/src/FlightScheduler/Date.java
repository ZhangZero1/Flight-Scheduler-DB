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
public class Date {
    private int month;
    private int year;
    private int day;
    
    public static final String[] Months = {"January", "February", "March", "April", "May", "June",
                                           "July", "August", "September", "October", "November", 
                                           "December"};
    
    public Date(int year,int month,  int day){
        this.month = month;
        this.year = year;
        this.day = day;
    }
    public Date(String date){
        this( 
                Integer.parseInt(date.substring(0,4)),
                Integer.parseInt(date.substring(5, 7)),
                Integer.parseInt(date.substring(8))
        );
    }
    
    public static String getMonth(int index){
        return Months[index];
    }

    public void setDate(int month, int year, int day){
        this.month = month;
        this.year = year;
        this.day = day;
    }
    public String getDate(){
        return String.format("%04d-%02d-%02d", year, month, day);
    }
    
    public void setDay(int day){
        this.day = day;
    }
    public int getDay(){
        return day;
    }
    
    public void setMonth(int month){
        this.month = month;
    }
    public int getMonth(){
        return month;
    }
    public String getMonthAsText(){
        return getMonth(month);
    }
    
    public void setYear(int year){
        this.year = year;
    }
    public int getYear(){
        return year;
    }
    
}
