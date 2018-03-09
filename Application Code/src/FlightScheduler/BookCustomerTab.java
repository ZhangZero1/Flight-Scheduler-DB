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
public class BookCustomerTab extends JPanel{
    CustomerQuery Cquery;
    FlightQuery Fquery;
    DateQuery Dquery;
    
    CustomerHistoryBox customerDetailsBox;
    BookCustomerBox bookCustomerBox;
    
    public BookCustomerTab(CustomerQuery Cquery, FlightQuery Fquery, DateQuery Dquery){
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        this.Cquery = Cquery;
        this.Fquery = Fquery;
        this.Dquery = Dquery;
        
        customerDetailsBox = new CustomerHistoryBox("Customer Details", 
                                 Cquery.statusPerson().toArray(new Customer[0])
        
        );
        bookCustomerBox = new BookCustomerBox(new AddListener());
        
        add(customerDetailsBox);
        add(bookCustomerBox);
    }
    
    public void reset(){
        customerDetailsBox.setCustomers(Cquery.statusPerson().toArray(new Customer[0]));
        bookCustomerBox.resetSelection();
        bookCustomerBox.resetWait();
    }
    
    private class AddListener extends InputListener{ 
        
        private void add(){
            if(bookCustomerBox.canBook()){
                if(bookCustomerBox.isWaitListed()){
                    Cquery.bookWait(
                        bookCustomerBox.getFlight(),
                        bookCustomerBox.getDate(),
                        customerDetailsBox.getCustomer().getName()
                    );
                }else{
                    Cquery.bookPerson(
                            bookCustomerBox.getFlight(),
                            bookCustomerBox.getDate(),
                            customerDetailsBox.getCustomer().getName());
                }
            }
            
            customerDetailsBox.setCustomers(Cquery.statusPerson().toArray(new Customer[0]));
            bookCustomerBox.resetWait();
            bookCustomerBox.resetSelection();
        }
        
        private void date(){
            ArrayList<String> allDates = new ArrayList<>();
            for(Date x:Dquery.getAllDates()){
                allDates.add(x.getDate());
            }
            bookCustomerBox.setDates(allDates.toArray(new String[0]));
            
        }
        
        private void dateSel(){
            if(bookCustomerBox.getDate() != null && bookCustomerBox.getFlight() != null){
                String flight = bookCustomerBox.getFlight();
                String date = bookCustomerBox.getDate();
                if(Fquery.getOccupancy(flight, date) == Fquery.getCapacity(flight)){
                    bookCustomerBox.setWaitlisted(true);
                }else{
                    bookCustomerBox.setWaitlisted(false);
                }
            }
            bookCustomerBox.checkReady();
        }
        private void flight(){
            ArrayList<String> allFlights = new ArrayList<>();
            for(Flight x:Fquery.getAllFlights()){
                allFlights.add(x.getName());
            }
            bookCustomerBox.setFlights(allFlights.toArray(new String[0]));
        }
        
        
        private void flightSel(){
            if(bookCustomerBox.getDate() != null && bookCustomerBox.getFlight() != null){
                String flight = bookCustomerBox.getFlight();
                String date = bookCustomerBox.getDate();
                if(Fquery.getOccupancy(flight,date) == Fquery.getCapacity(flight)){
                    bookCustomerBox.setWaitlisted(true);
                }else{
                    bookCustomerBox.setWaitlisted(false);
                }
            }
            bookCustomerBox.checkReady();
        }
    
    
    
        public InputListener thisListener(){
            return new AddListener();
        }
        

        @Override
        public void actionPerformed(ActionEvent e) {
            if(triggerName.equals("add")){
                add();
            }else if (triggerName.equals("dateSel")){
                dateSel();
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
