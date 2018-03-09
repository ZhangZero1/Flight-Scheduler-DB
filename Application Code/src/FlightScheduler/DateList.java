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
public class DateList extends JPanel{
    private JTextField[] count;
    private JTextField[] date;
    
    public DateList(int start, Date... dates){
        super();
        count = new JTextField[5];
        date = new JTextField[5];
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        for (int i =0; i<5; i++){
            count[i] = new JTextField(3);
            date[i] = new JTextField(10);
            JPanel row = new JPanel();
            row.add(new JLabel("Index:"));
            row.add(new JLabel("Index:"));
            row.add(count[i]);
            row.add(new JLabel("Name:  "));
            row.add(date[i]);
            
            count[i].setEditable(false);
            date[i].setEditable(false);
            
            add(row);
        }
        if(dates != null){
            for(int i = 0; i<5 && i<dates.length;i++){
                count[i].setText(String.valueOf(start + i));
                date[i].setText(dates[i].getDate());
            }
        }
    }
    
    public void setList(int start, Date... dates){
        if(dates != null){
            for(int i = 0; i<5 && i<dates.length;i++){
                if(dates[i] == null){
                    count[i].setText("");
                    date[i].setText("");
                }else{
                    count[i].setText(String.valueOf(start + i));
                    date[i].setText(dates[i].getDate());
                }
                
            }
        }
    }
    public String[] getDate(){
        ArrayList<String> allDates = new ArrayList<>();
        for(JTextField d: date){
            if(!d.getText().equals("")){
                allDates.add(d.getText());
            }
        }
        return allDates.toArray(new String[0]);
    }
}
