/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerfrederickffz5015;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Whyer
 */
public class CustomerBox extends GroupBox {
    
    private Indexer pageFlipper;
    private CustomerList customerList;
    
    private Customer[] Customers;
    
    public CustomerBox(Customer[] Customers){
        super("Existing Customer", null);
        pageFlipper = new Indexer(-1, null, new PageListener());
        customerList = new CustomerList(0, null);
        
        if(Customers != null && Customers.length != 0){
            int size = Customers.length;
            int pages = (size/5) + (size%5==0?0:1);
            this.Customers = new Customer[Customers.length];
            pageFlipper.setMax(pages);
            for (int i =0; i<Customers.length;i++){
                this.Customers[i] = Customers[i];
            }
            fillTable(pageFlipper.getPage());
        }
        
        
        
        addComponent(pageFlipper);
        addComponent(customerList);
    }
    
    
    
    public CustomerBox(){
        this(null);
    }
    
    private int countTOIndex(int count){
        return count-1;
    }
    private int indexTOCount(int index){
        return index+1;
    }
    private int boundMaximumBound(int value){
        return value>5?5:value;
    }
    
    public void fillTable(int page){
        int passedEntries =countTOIndex(page)*5; 
        int start = indexTOCount(passedEntries);
        int len = indexTOCount(Customers.length)-start; 
        len = boundMaximumBound(len);
        
        Customer[] custLst = new Customer[5];
        
        for(int i =0; i<len;i++){
            custLst[i] = Customers[start-1+i];
        }
        
        customerList.setList(start, custLst);
    }
    
    public void setCustomers(Customer[] Customers){
        this.Customers = Customers;
        if(Customers != null && Customers.length != 0){
            int size = Customers.length;
            int pages = (size/5) + (size%5==0?0:1);
            this.Customers = new Customer[Customers.length];
            pageFlipper.setMax(pages);
            for (int i =0; i<Customers.length;i++){
                this.Customers[i] = Customers[i];
            }
            fillTable(pageFlipper.getPage());
        }else{
            customerList.setList(1, null);
            pageFlipper.setMax(-1);
            
        }
    }
    public Customer[] getCustomers(){
        return this.Customers;
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
                }
            }
        }
    }
    
    
    
    
    
}
