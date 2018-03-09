/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerfrederickffz5015;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Whyer
 */
public class Display extends JFrame{
    BookQuery book;
    WaitlistQuery wait;
    CustomerQuery customer;
    DateQuery date;
    FlightQuery flight;
    
    JTabbedPane tabs;
    
    AddFlightTab addFlightTab;
    AddCustomerTab addCustomerTab;
    AddDayTab addDayTab;
    BookCustomerTab bookCustomerTab;
    StatusTab statusTab;
    CancelBookingTab cancelBookingTab;
    SearchWaitlistTab searchWaitlistTab;
    SearchCustomerTab searchCustomerTab;
    DropFlightTab dropFlightTab;
    
    public Display(){
        super("Test");
        setLayout(new FlowLayout());
        
        book = new BookQuery();
        wait = new WaitlistQuery();
        date = new DateQuery();
        customer = new CustomerQuery(book, wait);
        flight = new FlightQuery(book, wait);
        
        addFlightTab = new AddFlightTab(flight);
        addCustomerTab =new AddCustomerTab(customer);
        addDayTab =new AddDayTab(date);
        bookCustomerTab =new BookCustomerTab(customer, flight, date);
        statusTab =new StatusTab(customer);
        cancelBookingTab =new CancelBookingTab(customer);
        searchWaitlistTab =new SearchWaitlistTab(customer, date);
        searchCustomerTab =new SearchCustomerTab(date, flight, customer);
        dropFlightTab = new DropFlightTab(flight);
        
        tabs = new JTabbedPane();
        
        tabs.addTab("Add Flight", null, addFlightTab,"Includes functionality to add new flights");
        tabs.addTab("Add Customer",null, addCustomerTab,"Includes functionality to add new customers");
        tabs.addTab("AddDay", null, addDayTab, "Includes functionality to add new dates ");
        tabs.addTab("Book Customer", null, bookCustomerTab, "From where customers can be booked to flihgts");
        tabs.addTab("Status", null, statusTab, "To get all relevant booking data by customer for all customers");
        tabs.addTab("Cancel Booking", null, cancelBookingTab, "To cancel a customers booking");
        tabs.addTab("Waitlist search", null, searchWaitlistTab, "To search for waitlisted customers by date");
        tabs.addTab("Customer Search", null, searchCustomerTab, "Searches for customers by Day and Flight");
        tabs.addTab("Drop Flight", null, dropFlightTab, "removes flights and re-allocates bookings on the dropped flight");
              
        tabs.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                switch(tabs.getSelectedIndex()){
                    case 0:
                        addFlightTab.update();
                        break;
                    case 1:
                        addCustomerTab.update();
                        break;
                    case 2:
                        addDayTab.reset();
                        break;
                    case 3:
                        bookCustomerTab.reset();
                        break;
                    case 4:
                        statusTab.recheck();
                        break;
                    case 5:
                        cancelBookingTab.reset();
                        break;
                    case 6:
                        searchWaitlistTab.reset();
                        break; 
                    case 7:
                        searchCustomerTab.reset();
                        break;
                    case 8:
                        dropFlightTab.reset();
                        break;
                }
            }
        });
        
        add(tabs);
        
        
        
        
      
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650,650);
        setVisible(true);
    }
    
}
