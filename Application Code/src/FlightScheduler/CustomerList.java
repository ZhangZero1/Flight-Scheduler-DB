/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerfrederickffz5015;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Whyer
 */
public class CustomerList extends JPanel{
    private JTextField[] count;
    private JTextField[] name;
    public CustomerList(int start, Customer... customers){
        super();
        count = new JTextField[5];
        name = new JTextField[5];
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        for (int i =0; i<5; i++){
            count[i] = new JTextField(3);
            name[i] = new JTextField(12);
            JPanel row = new JPanel();
            row.add(new JLabel("Index:"));
            row.add(count[i]);
            row.add(new JLabel("Name:  "));
            row.add(name[i]);
            
            add(row);
            
            count[i].setEditable(false);
            name[i].setEditable(false);
            
            
        }
        if(customers != null){
            for(int i = 0; i<5 && i<customers.length;i++){
                count[i].setText(String.valueOf(start + i));
                name[i].setText(customers[i].getName());
            }
        }
    }
    
 
    
    public void setList(int start, Customer... customers){
        if(customers != null){
            for(int i = 0; i<5 && i<customers.length;i++){
                if(customers[i] == null){
                    count[i].setText("");
                    name[i].setText("");
                }else{
                    count[i].setText(String.valueOf(start + i));
                    name[i].setText(customers[i].getName());
                }
            }
        }else{
            for(int i =0; i<5; i++){
                count[i].setText("");
                name[i].setText("");
            }
        }
    }
    public String[] getNames(){
        ArrayList<String> allNames = new ArrayList<>();
        for(JTextField n: name){
            if(!n.getText().equals("")){
                allNames.add(n.getText());
            }
        }
        return allNames.toArray(new String[0]);
    }
    
    
    
    
    
    
    

}
