/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerfrederickffz5015;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Whyer
 */


public class AddDayBox extends GroupBox{
    private JTextField Day;
    private JComboBox Month;
    private JTextField Year;
    
    private JButton add;
    
    private InputListener handler;
    
    public AddDayBox(InputListener L){
        super("Add Day", null);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        handler = L;
        
        Day = new JTextField("",2);
        Month = new JComboBox(new String[]{ "Select Month",
            "January", "Febuary", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"
        });
        Year = new JTextField("",4);
        add = new JButton("add");
        
        addItem("Day to Add (Cannot exceed 31)",Day);
        addItem("Month to Add", Month);
        addItem("Year to Add (must be 4 digits or less)", Year);
        
        JPanel row = new JPanel();
        row.add(add);
        add(row);
        
        add.addActionListener(InputListener.getHandler("add",handler));
        
    }
    
    private void addItem(String text, Component c){
        JPanel row = new JPanel();
        row.add(new JLabel(text));
        row.add(c);
        add(row);
    }
    
    public boolean check(){
        boolean checkDay;
        boolean checkYear;
        try{
            checkDay = Integer.parseInt(Day.getText()) <0 || Integer.parseInt(Day.getText()) > 31;
        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, "Day must be a number", "Invalid Input",
                                          JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        try{
            checkYear = Integer.parseInt(Year.getText()) <0 || Integer.parseInt(Year.getText()) > 9999;
        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, "Year must be a number", "Invalid Input",
                                          JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        
        if(Month.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Must Select Month", "Invalid Input",
                                          JOptionPane.INFORMATION_MESSAGE);
            return false;
        }else if(checkDay){
                    JOptionPane.showMessageDialog(null, "Day must be between 0 to 31", "Invalid Input",
                                          JOptionPane.INFORMATION_MESSAGE);
                    return false;
        }else if(checkYear){
                    JOptionPane.showMessageDialog(null, "Year must be 4 digits", "Invalid Input",
                                          JOptionPane.INFORMATION_MESSAGE);
                    return false;
        }
        return true;
    }
    
    public Date getCurrentDate(){
        int year = Integer.parseInt(Year.getText());
        int month = Month.getSelectedIndex();
        int day = Integer.parseInt(Day.getText());
        return new Date(year, month, day);
    }
    
    public void reset(){
        Year.setText("");
        Month.setSelectedIndex(0);
        Day.setText(""); 
    }
    
}
