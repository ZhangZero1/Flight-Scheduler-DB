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
public class SearchCustomerBox extends GroupBox{
    JComboBox<String> date;
    JComboBox<String> flight;
    JButton search;
    
    InputListener handler;
    
    public SearchCustomerBox(InputListener L){
        super("Search Customers on Particular Day and Flight", null);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        handler = L;
        
        date = new JComboBox<>(new String[]{"Select Date"});
        flight = new JComboBox<>(new String[] {"Select Flight"});
        search = new JButton("Search");
        search.setEnabled(false);
        
        JPanel row = new JPanel();
        row.add(new JLabel("Select Date: "));
        row.add(date);
        add(row);
        
        row = new JPanel();
        row.add(new JLabel("Select Flight: "));
        row.add(flight);
        add(row);
        
        row = new JPanel();
        row.add(search);
        add(row);
        
        
       flight.addMouseListener(InputListener.getHandler("flight",handler));
        
       flight.addActionListener(InputListener.getHandler("flightSel",handler));
        
        date.addMouseListener(InputListener.getHandler("date",handler));
        
        date.addActionListener(InputListener.getHandler("dateSel",handler));
        
        search.addActionListener(InputListener.getHandler("search",handler));
    }
    
    public void checkReady(){
        if(date.getSelectedIndex() != 0 && 
           flight.getSelectedIndex() != 0){
            search.setEnabled(true);
        }else{
            search.setEnabled(false);
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
    
    public boolean readyToSearch(){
        return date.getSelectedIndex() != 0 && flight.getSelectedIndex() != 0;
    }
    
    public String getDate(){
        return date.getItemAt(date.getSelectedIndex());
    }
    public String getFlight(){
        return flight.getItemAt(flight.getSelectedIndex());
    }
    
    public void resetSelection(){
        date.setSelectedIndex(0);
        flight.setSelectedIndex(0);
    }
    
}
