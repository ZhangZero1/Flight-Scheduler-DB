/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerfrederickffz5015;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Whyer
 */
public class SearchWaitlistTab extends JPanel{
    CustomerQuery Cquery;
    DateQuery Dquery;
    
    CustomerBox customerBox;
    SearchWaitlistBox searchWaitlistBox;
    public SearchWaitlistTab(CustomerQuery Cquery, DateQuery Dquery){
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.Cquery = Cquery;
        this.Dquery = Dquery;
        customerBox = new CustomerBox(null); 
        searchWaitlistBox = new SearchWaitlistBox(new SearchListener());
        
        add(searchWaitlistBox);
        add(customerBox);
    }
    
    public void reset(){
        searchWaitlistBox.resetSelection();
        customerBox.setCustomers(null);
    }
    
    private class SearchListener extends InputListener{ 
        
        private void search(){
            if(searchWaitlistBox.readyToSearch() && searchWaitlistBox.getSelected() != null){
                if (Cquery.getWaitlist(searchWaitlistBox.getSelected()).size() == 0){
                    customerBox.setCustomers(null);
                    return;
                }
                customerBox.setCustomers(Cquery.getWaitlist(searchWaitlistBox.getSelected()).toArray(new Customer[0]));
            }
            searchWaitlistBox.resetSelection();
        }
        
        private void date(){
            ArrayList<String> allDates = new ArrayList<>();
            for(Date d: Dquery.getAllDates()){
                allDates.add(d.getDate());       
            }
            searchWaitlistBox.setDates(allDates.toArray(new String[0]));
        }
        private void dateSel(){
            searchWaitlistBox.checkReady();
        }
    
    
    
        public InputListener thisListener(){
            return new SearchListener();
        }
        

        @Override
        public void actionPerformed(ActionEvent e) {
            if(triggerName.equals("dateSel")){
                dateSel();
            }
            else if(triggerName.equals("search")){
                search();
            }
        }
        
        @Override
        public void mouseClicked(MouseEvent e){
            if(triggerName.equals("date")){
                date();
            }
        }
    }
}

