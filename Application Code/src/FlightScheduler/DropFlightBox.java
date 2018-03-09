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
public class DropFlightBox extends GroupBox{
    JComboBox<String> index;
    JTextField flight;
    JTextField capacity;
    JButton cancel;
    
    InputListener handler;
    
    public DropFlightBox(InputListener L){
        super("Drop Flight",null);
        index = new JComboBox<>(new String[]{"Index"});
        capacity = new JTextField("",5);
        flight = new JTextField("",8);
        cancel = new JButton("Cancel Selected Flight");
        
        handler = L;
        
        capacity.setEditable(false);
        flight.setEditable(false);
        cancel.setEnabled(false);
               
        
        addItem("Booking Index to Drop", index);
        addItem("Flight:       ", flight);
        addItem("Capacity:     ", capacity);
        JPanel row = new JPanel();
        row.add(cancel);
        add(row);
        
        index.addMouseListener(InputListener.getHandler("index",handler));
        
        index.addActionListener(InputListener.getHandler("indexSel",handler));
        
        cancel.addActionListener(InputListener.getHandler("add",handler));
        

    }
    public void resetIndex(){
        index.setSelectedIndex(0);
    }
    public void checkIndex(){
        if(index.getSelectedIndex() > 0){
            cancel.setEnabled(true);
        }else{
            cancel.setEnabled(false);
        }
    }
    
    public void setIndexRange(int start, int end){
        index.removeAllItems();
        index.addItem("Index");
        for(int i = start+1; i< end; i++){
            index.addItem(String.valueOf(i));
        }
        
    }
    
    public boolean isReady(){
        return index.getSelectedIndex() != 0;
    }
    
    private void addItem(String text, Component c){
        JPanel row = new JPanel();
        row.add(new JLabel(text));
        row.add(c);
        add(row);
    }
    
    public void setData(String flight, String capacity){
        this.flight.setText(flight);
        this.capacity.setText(capacity);
        
    }
    public void setData(String flight, int capacity){
        setData(flight, String.valueOf(capacity));
    }
    
    public void clearData(){
        setData("","");
    }
    
    public int getIndex(){
        if(index.getSelectedIndex() <= 0){
            return -1;
        }
        return Integer.parseInt(index.getItemAt(index.getSelectedIndex()));
    }
    
}
