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
public class AddCustomerBox extends GroupBox{
    
    private JTextField name;
    private JButton add;
    private InputListener handler;
    
    public AddCustomerBox(InputListener L) {
        super("Add Customer", null);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        handler = L;
        
        name = new JTextField("",15);
        add = new JButton("add");
        
        JPanel row = new JPanel();
        row.add(new JLabel("Customer Name to Add"));
        row.add(name);
        add(row);
        
        row = new JPanel();
        row.add(add);
        add(row);
        
        
        add.addActionListener(InputListener.getHandler("add",handler));
        
        
        
        
    }
    
    public void checkText(){
        if(name.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Cannot Insert Blank Name", "Invalid Input",
                                          JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
    
    public String getName(){
        return name.getText();
    }
    
    public void setValue(String value){
        name.setText(value);
    }
    public void resetText(){
        setValue("");
    }
    
}
