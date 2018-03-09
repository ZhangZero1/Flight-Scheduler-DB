/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerfrederickffz5015;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Whyer
 */
public class AddFlightBox extends GroupBox{
    JTextField name;
    JTextField capacity;
    JButton add;
    
    private InputListener handler;
    
    public AddFlightBox(InputListener L){
        super("Add Flight", null);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        handler = L;
        
        name = new JTextField("",15);
        add = new JButton("add");
        capacity = new JTextField("",15);
        
        JPanel row = new JPanel();
        row.add(new JLabel("Flight Name to Add"));
        row.add(name);
        add(row);
        row = new JPanel();
        row.add(new JLabel("Flight Capcity"));
        row.add(capacity);
        add(row);
        
        row = new JPanel();
        row.add(add);
        add(row);
        
        add.addActionListener(InputListener.getHandler("add",handler));
        
    }

    public void checkText(){
        if(name.getText().equals("") || capacity.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Cannot Insert Blank Name or No Capcity", "Invalid Input",
                                          JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
    
    public String getName(){
        return name.getText();
    }
    public void setName(String value){
        name.setText(value);
    }
    public int getCapacity(){
        if(capacity.getText().equals("")){
            return -1;
        }
        try{
            Integer.parseInt(capacity.getText());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Capacity must be an integer", "Invalid Input",
                                          JOptionPane.INFORMATION_MESSAGE);
            capacity.setText("");
            return -1;
        }
        return Integer.parseInt(capacity.getText());
    }
    public void setCapacity(String value){
        capacity.setText(value);
    }
    
    public void resetText(){
        setName("");
        setCapacity("");
    }
    
}
    
    
    
    


