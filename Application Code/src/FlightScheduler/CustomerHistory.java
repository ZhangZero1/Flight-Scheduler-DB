/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerfrederickffz5015;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ScrollPaneLayout;

/**
 *
 * @author Whyer
 */

/*

*
* @TODO:  STILL HAVE TO MODIFY THE RESULTING CODE TO APPLY SCROLLING
*

*/
public class CustomerHistory extends JPanel{
    
    private JTextField name;
    
    private JTextField[] flights;
    private JTextField[] dates;
    private JTextField[] waitlists;
    private JTextField[] indexer;
    
    private LazyScroll scroll;
    
    public CustomerHistory(Customer customer){
        super();
        scroll = new LazyScroll();
        scroll.setLazyLayout(new BoxLayout(scroll.getView(), BoxLayout.Y_AXIS));
        scroll.setPreferredSize(new Dimension(400, 200));
        
        
        
        if(customer != null){
            name = new JTextField(customer.getName());
            name.setEditable(false);
            scroll.addItem(mkLabel("Customer:",name));

            Booking[] booking = customer.getBooking();
            Booking[] waitlist = customer.getWaitlist();   
            int totalSize = booking.length + waitlist.length;

            flights = new JTextField[totalSize];
            dates = new JTextField[totalSize];
            waitlists = new JTextField[totalSize];
            indexer = new JTextField[totalSize];

            for(int i = 0; i<booking.length; i++ ){
                scroll.addItem(new Row(i,i,booking,false));
            }
            for(int i = booking.length; i<totalSize;i++){
                int bookingCount = i-booking.length;
                scroll.addItem(new Row(i,bookingCount,waitlist, true));
            }
        }else{
            name = new JTextField("");
            name.setEditable(false);
        }
        add(scroll);

    }
    
    public void setCustomer(Customer customer){
        scroll.clear();
        if(customer != null ){
            
            
            name.setText(customer.getName());
            scroll.addItem(mkLabel("Customer:",name));

            Booking[] booking = customer.getBooking();
            Booking[] waitlist = customer.getWaitlist();   
            int totalSize = booking.length + waitlist.length;

            flights = new JTextField[totalSize];
            dates = new JTextField[totalSize];
            waitlists = new JTextField[totalSize];
            indexer = new JTextField[totalSize];

            for(int i = 0; i<booking.length; i++ ){
                scroll.addItem(new Row(i,i,booking,false));
            }
            for(int i = booking.length; i<totalSize;i++){
                int bookingCount = i-booking.length;
                scroll.addItem(new Row(i,bookingCount,waitlist, true));
            }
            
            
            
        }
    }
    
    private JPanel mkLabel(String text, Component c){
        JPanel temp = new JPanel();
        temp.add(new JLabel(text));
        temp.add(c);
        return temp;
    }
    
    
    //////////////////// -- ROW CLASS -- //////////////////////
    
    private class Row extends JPanel{
        private Row(int index, int bookingCount, Booking[] booking, boolean waitlist){
            int nCount = index +1;
            String count = String.valueOf(nCount) ;
            String flight = booking[bookingCount].getFlight().getName();
            String date = booking[bookingCount].getDate().getDate();
            
            indexer[index] = new JTextField(count,3);
            flights[index] = new JTextField(flight);
            dates[index] = new JTextField(date);
            
            if(waitlist){
                waitlists[index] = new JTextField("TRUE");
            }else{
                waitlists[index] = new JTextField("FALSE");
            }
            
            addLabel("Index", indexer[index]);
            addLabel("Flight:",flights[index]);
            addLabel("Date:",dates[index]);
            addLabel("Wait-listed",waitlists[index]);
            
            setUneditable(index);
        }
        
        private void addLabel(String text, Component c){
            add(new JLabel(text));
            add(c);
        }
        
        private void setUneditable(int index){
            indexer[index].setEditable(false);
            flights[index].setEditable(false);
            dates[index].setEditable(false);
            waitlists[index].setEditable(false);
            
        }
    }
}
