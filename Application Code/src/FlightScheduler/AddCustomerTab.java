/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerfrederickffz5015;

import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Whyer
 */
public class AddCustomerTab extends JPanel{
    CustomerQuery query;
    CustomerBox customerBox;
    AddCustomerBox addCustomerBox;
    public AddCustomerTab(CustomerQuery query){
        super();
        this.query = query;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        customerBox = new CustomerBox(query.getAllPeople().toArray(new Customer[0]));
        addCustomerBox = new AddCustomerBox(new AddListener());
        
        add(customerBox);
        add(addCustomerBox);
        
    }
    
    public void update(){
        addCustomerBox.resetText();
        customerBox.setCustomers(query.getAllPeople().toArray(new Customer[0]));
    }
    
    
    
    private class AddListener extends InputListener{ 
        
        private void add(){
            addCustomerBox.checkText();
            if(addCustomerBox.getName().equals("")){
                // do nothing
            }else{
                query.addPerson(addCustomerBox.getName());
                customerBox.setCustomers(query.getAllPeople().toArray(new Customer[0]));
            }
            addCustomerBox.setName("");
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
