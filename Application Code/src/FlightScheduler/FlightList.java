/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerfrederickffz5015;

import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Whyer
 */
public class FlightList extends JPanel {
    private JTextField[] count;
    private JTextField[] name;
    private JTextField[] capacity;
    
    public FlightList(int start, Flight... flights){
        super();
        
        count = new JTextField[5];
        name = new JTextField[5];
        capacity = new JTextField[5];
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        for (int i =0; i<5; i++){
            count[i] = new JTextField(3);
            name[i] = new JTextField(12);
            capacity[i] = new JTextField(3);
            JPanel row = new JPanel();
            row.add(count[i]);
            row.add(new JLabel("Name:  "));
            row.add(name[i]);
            row.add(new JLabel("Capacity: "));
            row.add(capacity[i]);
            
            count[i].setEditable(false);
            name[i].setEditable(false);
            capacity[i].setEditable(false);
            
            add(row);
        }
        if(flights != null){
            for(int i = 0; i<5 && i<flights.length;i++){
                count[i].setText(String.valueOf(start + i));
                name[i].setText(flights[i].getName());
                capacity[i].setText(String.valueOf(flights[i].getCapacity()));
            }
        }
    }
    
    
    public void setList(int start, Flight... flights){
        if(flights != null){
            for(int i = 0; i<5 && i<flights.length;i++){
                if(flights[i] == null){
                    count[i].setText("");
                    name[i].setText("");
                    capacity[i].setText("");
                }else{
                    count[i].setText(String.valueOf(start + i));
                    name[i].setText(flights[i].getName());
                    capacity[i].setText(String.valueOf(flights[i].getCapacity()));
                }
            }
        }else{
            for(int i=0; i<5; i++){
                count[i].setText("");
                name[i].setText("");
                capacity[i].setText("");
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
    
    public Integer[] getCapacity(){
        ArrayList<Integer> allCapacities = new ArrayList<>();
        for(JTextField c: capacity){
            if(!c.getText().equals("")){
                allCapacities.add(Integer.parseInt(c.getText()));
            }
        }
        return allCapacities.toArray(new Integer[0]);
    }
    
}
