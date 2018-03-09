/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerfrederickffz5015;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Whyer
 */
public class DropFlightTab extends JPanel{
    
    FlightQuery query;
    
    FlightBox flightBox;
    DropFlightBox dropFlightBox;
    
    public DropFlightTab(FlightQuery query){
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        this.query = query;
        flightBox = new FlightBox(query.getAllFlights().toArray(new Flight[0]));
        dropFlightBox = new DropFlightBox(new AddListener());
        
        add(flightBox);
        add(dropFlightBox);
    }
    
    public void reset(){
        flightBox.setFlights(query.getAllFlights().toArray(new Flight[0]));
        dropFlightBox.resetIndex();
        dropFlightBox.clearData();
    }
    
    private class AddListener extends InputListener{ 
        
        private void add(){
            
            
            int index = dropFlightBox.getIndex();
        
            if(  index >= 0){
                Flight f = flightBox.getFlights()[index-1];
                query.dropFlight(f.getName());
                flightBox.setFlights(query.getAllFlights().toArray(new Flight[0]));
            }
            dropFlightBox.resetIndex();
            
        }
        
        private void index(){
            
            int[] ends = flightBox.range();
            if(ends != null){
                ends[1] += 1;
            }else{
                ends = new int[]{0,0};
            }
            
            dropFlightBox.setIndexRange(ends[0], ends[1]);
        }
        
        private void indexSel(){
             int index = dropFlightBox.getIndex();
        
            if(  index >= 0){
               
                Flight f = flightBox.getFlights()[index-1];
                dropFlightBox.setData(f.getName(), f.getCapacity());
            }else{
                dropFlightBox.setData("", "");
            }
            dropFlightBox.checkIndex();
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
