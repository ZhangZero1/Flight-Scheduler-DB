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
public class CancelBookingTab extends JPanel{
    CustomerQuery Cquery;
    
    CustomerHistoryBox customerDetailsBox;
    CancelBookingBox cancelBookingBox;
    
    public CancelBookingTab(CustomerQuery Cquery){
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.Cquery = Cquery;
        
        customerDetailsBox = new CustomerHistoryBox("Customer Details", 
                                 Cquery.statusPerson().toArray(new Customer[0])
        
        );
        cancelBookingBox = new CancelBookingBox(new AddListener());
        
        add(customerDetailsBox);
        add(cancelBookingBox);
    }
    
    public void reset(){
        cancelBookingBox.clearData();
        cancelBookingBox.resetIndex();
        customerDetailsBox.setCustomers(Cquery.statusPerson().toArray(new Customer[0]));
    }
    // when clicked look at range in indexer
    
    private class AddListener extends InputListener{ 
        
        private void add(){
            
            if( cancelBookingBox.isReady()){
                int index = cancelBookingBox.getIndex()-1;
                Customer c = customerDetailsBox.getCustomer();
                if(c.isWaitlist(index)){
                    Cquery.cancelWait(c.getEntry(index).getFlight().getName(), 
                            c.getEntry(index).getDate().getDate()
                            , c.getName());
                }else{
                    Cquery.cancelBooking(c.getEntry(index).getFlight().getName(), 
                            c.getEntry(index).getDate().getDate()
                            , c.getName());
                }  
                customerDetailsBox.setCustomers(Cquery.statusPerson().toArray(new Customer[0]));
            }
            cancelBookingBox.resetIndex();
            cancelBookingBox.checkIndex();
        }
        
        private void index(){
            int end = customerDetailsBox.range();
            cancelBookingBox.setIndexRange(0, end);
        }
        
        private void indexSel(){
            int index = cancelBookingBox.getIndex()-1;
            if( index >= 0){
                Customer c = customerDetailsBox.getCustomer();
                cancelBookingBox.setData(c.getEntry(index).getFlight().getName(), 
                                         c.getEntry(index).getDate().getDate(),
                                         c.isWaitlist(index)?"True":"False");
                
                
            }else{
                cancelBookingBox.setData("", "","");
            }
            cancelBookingBox.checkIndex();
        }
        
        public InputListener thisListener(){
            return new AddListener();
        }
        

        @Override
        public void actionPerformed(ActionEvent e) {
            if(triggerName.equals("indexSel")){
                indexSel();
            }else if(triggerName.equals("add")){
                add();
            } 
        }
        @Override
        public void mouseClicked(MouseEvent e){
            if(triggerName.equals("index")){
                index();
            }
        }
    }
}

    


