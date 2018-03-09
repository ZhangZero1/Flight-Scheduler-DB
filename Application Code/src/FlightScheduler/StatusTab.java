/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerfrederickffz5015;

import javax.swing.JPanel;

/**
 *
 * @author Whyer
 */
public class StatusTab extends JPanel {
    CustomerHistoryBox customerDetailsBox;
    CustomerQuery query;
    
    public StatusTab(CustomerQuery query){
        super();
        
        this.query = query;
        customerDetailsBox = new CustomerHistoryBox("Status Customers", query.statusPerson().toArray(new Customer[0]));
        add(customerDetailsBox);
    }
    
    public void recheck(){
        customerDetailsBox.setCustomers(query.statusPerson().toArray(new Customer[0]));
    }
    
}
