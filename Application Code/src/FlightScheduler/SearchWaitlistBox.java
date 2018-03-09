/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerfrederickffz5015;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
public class SearchWaitlistBox extends GroupBox{
    JComboBox<String> date;
    JButton search;
    
    InputListener handler;
    
    public SearchWaitlistBox(InputListener L){
        super("Search Wait-List by Day", null);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        handler = L;
        
        date = new JComboBox<>(new String[]{"Select Date"});
        search = new JButton("Search");
        search.setEnabled(false);
        
        JPanel row = new JPanel();
        row.add(new JLabel("Select Date: "));
        row.add(date);
        add(row);
        
        row = new JPanel();
        row.add(search);
        add(row);
        
        
       
        
        date.addMouseListener(InputListener.getHandler("date",handler));
        
        date.addActionListener(InputListener.getHandler("dateSel",handler));
        
        search.addActionListener(InputListener.getHandler("search",handler));
    }
    
    public void setDates(String[] dates){
        date.removeAllItems();
        date.addItem("Select Date");
        for(String d: dates){
            date.addItem(d);
        }
    }
    
    public boolean readyToSearch(){
        return date.getSelectedIndex() != 0;
    }
    
    public String getSelected(){
        if(date.getSelectedIndex() <= 0){
            return null;
        }
        return date.getItemAt(date.getSelectedIndex());
    }
    
    public void resetSelection(){
        date.setSelectedIndex(0);
    }
    public void checkReady(){
        if(date.getSelectedIndex() == 0){
            search.setEnabled(false);
        }else{
            search.setEnabled(true);
        }
    }
    
}
