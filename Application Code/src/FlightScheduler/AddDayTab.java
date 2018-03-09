/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerfrederickffz5015;

import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Whyer
 */
public class AddDayTab extends JPanel{
    DateQuery query;
    
    DateBox dateBox;
    AddDayBox addDayBox;
    
    public AddDayTab(DateQuery query){
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.query = query;
        dateBox = new DateBox(query.getAllDates().toArray(new Date[0]));
        addDayBox = new AddDayBox(new AddListener());
        
        add(dateBox);
        add(addDayBox);
    }
    
    public void reset(){
        dateBox.setDates(query.getAllDates().toArray(new Date[0]));
        addDayBox.reset();
    }
    
    private class AddListener extends InputListener{ 
        
        private void add(){
            if(addDayBox.check()){
                query.addDate(addDayBox.getCurrentDate().getDate());
                dateBox.setDates(query.getAllDates().toArray(new Date[0]));
            }
            
            addDayBox.reset();
        }
    
    
    
        public InputListener thisListener(){
            return new AddListener();
        }
        

        @Override
        public void actionPerformed(ActionEvent e) {
            if(triggerName.equals("add")){
                add();
            }
        }
    }
    
}
