/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerfrederickffz5015;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Whyer
 */
public class CancelBookingBox extends GroupBox{
    JComboBox<String> index;
    JTextField date;
    JTextField flight;
    JTextField waitlist;
    JButton cancel;
    
    InputListener handler;
    
    public CancelBookingBox(InputListener L){
        super("Cancel Booking",null);
        index = new JComboBox<>(new String[]{"Index"});
        index.setSelectedIndex(0);
        date = new JTextField("",10);
        waitlist = new JTextField("",5);
        flight = new JTextField("",8);
        cancel = new JButton("Cancel Selected Booking");
        
        handler = L;
        
        date.setEditable(false);
        waitlist.setEditable(false);
        flight.setEditable(false);
        cancel.setEnabled(false);
               
        
        addItem("Booking Index to Drop", index);
        addItem("Flight:   ", flight);
        addItem("Date:     ", date);
        addItem("Waitlist: ", waitlist);
        JPanel row = new JPanel();
        row.add(cancel);
        add(row);
        
        index.addMouseListener(InputListener.getHandler("index",handler));
        
        index.addActionListener(InputListener.getHandler("indexSel",handler));
        
        cancel.addActionListener(InputListener.getHandler("add",handler));
        

    }
    
    private void addItem(String text, Component c){
        JPanel row = new JPanel();
        row.add(new JLabel(text));
        row.add(c);
        add(row);
    }
    
    public void setData(String flight, String date, String waitlist){
        this.flight.setText(flight);
        this.date.setText(date);
        this.waitlist.setText(waitlist);
    }
    public void clearData(){
        setData("","","");
    }
    
    public void checkIndex(){
        if(index.getSelectedIndex() > 0){
            cancel.setEnabled(true);
        }else{
            cancel.setEnabled(false);
        }
    }
    
    public int getIndex(){
        if(index.getSelectedIndex() <= 0){
            return -1;
        }
        return Integer.parseInt(index.getItemAt(index.getSelectedIndex()));
    }
    public void resetIndex(){
        index.setSelectedIndex(0);
    }
    
    public boolean isReady(){
        return index.getSelectedIndex() != 0;
    }
    
    // exclusive to the end index
    public void setIndexRange(int start, int end){
        index.removeAllItems();
        index.addItem("Index");
        for(int i = start+1; i< end; i++){
            index.addItem(String.valueOf(i));
        }
        
    }
    
}
