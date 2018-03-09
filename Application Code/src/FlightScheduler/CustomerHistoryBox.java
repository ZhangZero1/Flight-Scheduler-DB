/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerfrederickffz5015;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Whyer
 */
public class CustomerHistoryBox extends GroupBox{
    private Indexer pageFlipper;
    private CustomerHistory customerList;
    
    Customer[] Customers;
    public CustomerHistoryBox(String name,Customer[] Customers){
        super(name, null);
        pageFlipper = new Indexer(-1, null, new PageListener());
        
        
        if(Customers != null && Customers.length != 0){
            customerList = new CustomerHistory(Customers[0]);
            this.Customers = Customers;
            pageFlipper.setMax(Customers.length);
            fillTable(pageFlipper.getPage());
        }else{
            customerList = new CustomerHistory(null);
            this.Customers = new Customer[0];
        }
        
        // fillTable(pageFlipper.getPage());
        addComponent(pageFlipper);
        addComponent(customerList);
    }
    
    public CustomerHistoryBox(){
        this("Customer History", null);
    }
    
    private int countTOIndex(int count){
        return count-1;
    }
    private int indexTOCount(int index){
        return index+1;
    }
    
    public void fillTable(int page){
        if(Customers != null && Customers.length != 0){
            customerList.setCustomer(Customers[countTOIndex(page)]);
        }
    }
    
    
    
    public void setCustomers(Customer[] Customers){
        this.Customers = Customers;
        if(Customers != null && Customers.length != 0){
            pageFlipper.setMax(Customers.length);
            fillTable(pageFlipper.getPage());
            customerList.setCustomer(Customers[pageFlipper.getPage()-1]);
        }else{
            customerList.setCustomer(null);
        }
    }
    public Customer[] getCustomers(){
        return this.Customers;
    }
    public Customer getCustomer(){
        if(pageFlipper.getPage()-1 < 0){
            return null;
        }
        return this.Customers[pageFlipper.getPage()-1];
    }
    
    public int range(){
        if(getCustomer() == null){
            return 0;
        }
        return getCustomer().getBooking().length+getCustomer().getWaitlist().length+1;
    }
    
    
    
    //////////////////// -- LISTENER CLASS -- //////////////////////
    
    private class PageListener extends InputListener{ 
        private void forwardPress(){
            pageFlipper.setPage(pageFlipper.getPage()+1);
        }
        private void backwardPress(){
            pageFlipper.setPage(pageFlipper.getPage()-1);
        }
        private void currType(ActionEvent e){
            int page;
            try{
                page = Integer.parseInt(e.getActionCommand());
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "page must be a number", "Invalid Input",
                                          JOptionPane.INFORMATION_MESSAGE);
                pageFlipper.setPage(pageFlipper.getPage());
                return;
            }
                
            pageFlipper.setPage(page);
        }
        public InputListener thisListener(){
            return new PageListener();
        }
        

        @Override
        public void actionPerformed(ActionEvent e) {
            if(triggerName.equals("currentPage")){
                currType(e);
            }else if (triggerName.equals("previousPage")){
                backwardPress();
            }else if (triggerName.equals("nextPage")){
                forwardPress();
            }
            
            if (triggerName.equals("maxPage")){
                pageFlipper.setMax(pageFlipper.getMax()); // make any changes to no edit field reverted
            }else{
                if(pageFlipper.getPage() != -1){
                    fillTable(pageFlipper.getPage());
                }// fix no show issue
            }
        }
    }
    
}
