/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerfrederickffz5015;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Whyer
 */
public class BookCustomerBox extends GroupBox{
    JComboBox<String> date;
    JComboBox<String> flight;
    JButton book;
    JLabel waitlisted;
    
    InputListener handler;
    
    public BookCustomerBox(InputListener L){
        super("Book Customer for Flight and Day", null);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        handler = L;
        
        date = new JComboBox<>(new String[]{"Select Date"});
        flight = new JComboBox<>(new String[] {"Select Flight"});
        waitlisted = new JLabel("");
        book = new JButton("Book");
        book.setEnabled(false);
        
        JPanel row = new JPanel();
        row.add(new JLabel("Select Date: "));
        row.add(date);
        add(row);
        
        row = new JPanel();
        row.add(new JLabel("Select Flight: "));
        row.add(flight);
        add(row);
        
        row = new JPanel();
        row.add(waitlisted);
        add(row);
        
        row = new JPanel();
        row.add(book);
        add(row);
        
        
        flight.addMouseListener(InputListener.getHandler("flight",handler));
        
        flight.addActionListener(InputListener.getHandler("flightSel",handler));
        
        date.addMouseListener(InputListener.getHandler("date",handler));
        
        date.addActionListener(InputListener.getHandler("dateSel",handler));
        
        book.addActionListener(InputListener.getHandler("add",handler));
    }
    
    public void checkReady(){
        if(date.getSelectedIndex() != 0 && 
           flight.getSelectedIndex() != 0){
            book.setEnabled(true);
        }else{
            book.setEnabled(false);
        }
    }
    
    public void setDates(String[] dates){
        date.removeAllItems();
        date.addItem("Select Date");
        for(String d: dates){
            date.addItem(d);
        }
    }
    
    public void setFlights(String[] flights){
        flight.removeAllItems();
        flight.addItem("Select Flight");
        for(String f: flights){
            flight.addItem(f);
        }
    }
    
    public boolean canBook(){
        return date.getSelectedIndex() != 0 && flight.getSelectedIndex() != 0;
    }
    
    public String getDate(){
        if(date.getSelectedIndex() == 0){
            return null;
        }
        return date.getItemAt(date.getSelectedIndex());
    }
    public String getFlight(){
        if(flight.getSelectedIndex() == 0){
            return null;
        }
        return flight.getItemAt(flight.getSelectedIndex());
    }
    
    public boolean isWaitListed(){
        return waitlisted.getText().equals("Flight is fully occupied. Booking will go to waitlist");
    }
    public void setWaitlisted(boolean value){
        if(value){
            waitlisted.setText("Flight is fully occupied. Booking will go to waitlist");
        }else{
            waitlisted.setText("Flight is has vaccancies avalible to book");
        }
    }
    public void resetWait(){
        waitlisted.setText("");
    }
    
    public void resetSelection(){
        date.setSelectedIndex(0);
        flight.setSelectedIndex(0);
    }
    
}
