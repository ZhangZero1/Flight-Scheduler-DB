/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerfrederickffz5015;

import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Whyer
 */
public class AddFlightTab extends JPanel{
    FlightQuery query;
    FlightBox flightBox;
    AddFlightBox addFlightBox;
    public AddFlightTab(FlightQuery query){
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.query = query;
        flightBox = new FlightBox(query.getAllFlights().toArray(new Flight[0])); 
        addFlightBox = new AddFlightBox(new AddListener());
        
        add(flightBox);
        add(addFlightBox);
    }
    
    public void update(){
        addFlightBox.resetText();
        flightBox.setFlights(query.getAllFlights().toArray(new Flight[0]));
    }
    
    private class AddListener extends InputListener{ 
        
        private void add(){
            addFlightBox.checkText();
            if(addFlightBox.getName().equals("") || addFlightBox.getCapacity() == -1){
                
            }else{
                query.addFlight(addFlightBox.getName(), addFlightBox.getCapacity());
                flightBox.setFlights(query.getAllFlights().toArray(new Flight[0]));
            }
            addFlightBox.setName("");
            addFlightBox.setCapacity("");
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
