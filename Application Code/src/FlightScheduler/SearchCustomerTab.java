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
public class SearchCustomerTab extends JPanel{
    DateQuery Dquery;
    FlightQuery Fquery;
    CustomerQuery Cquery;
    
    SearchCustomerBox searchCustomerBox;
    CustomerBox customerBox;    
    
    public SearchCustomerTab(DateQuery Dquery, FlightQuery Fquery, CustomerQuery Cquery){
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.Fquery = Fquery;
        this.Dquery = Dquery;
        this.Cquery = Cquery;
        
        searchCustomerBox = new SearchCustomerBox(new SearchListener());
        customerBox = new CustomerBox(null);
        
        add(searchCustomerBox);
        add(customerBox);

    }
    
    public void reset(){
        searchCustomerBox.resetSelection();
        customerBox.setCustomers(null);
    }
    
    private class SearchListener extends InputListener{ 
        
        private void search(){
            if(searchCustomerBox.readyToSearch()){
                
                customerBox.setCustomers(Cquery.getPeopleByDayFlight(
                                            searchCustomerBox.getFlight(),
                                            searchCustomerBox.getDate())
                                         .toArray(new Customer[0]));
            }
            searchCustomerBox.resetSelection();
        }
        
        private void date(){
            ArrayList<String> allDates = new ArrayList<>();
            for(Date d: Dquery.getAllDates()){
                allDates.add(d.getDate());      
            }
            searchCustomerBox.setDates(allDates.toArray(new String[0]));
        }
        private void dateSel(){
            searchCustomerBox.checkReady();
        }
        
        private void flight(){
            ArrayList<String> allFlights = new ArrayList<>();
            for(Flight f: Fquery.getAllFlights()){
                allFlights.add(f.getName());    
            }
            searchCustomerBox.setFlights(allFlights.toArray(new String[0]));
        }
        private void flightSel(){
            searchCustomerBox.checkReady();
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
            }else if (triggerName.equals("flightSel")){
                flightSel();
            }
        }
        
        @Override
        public void mouseClicked(MouseEvent e){
            if(triggerName.equals("date")){
                date();
            }else if (triggerName.equals("flight")){
                flight();
            }
        }
    }
    
}
