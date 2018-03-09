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
public class FlightBox extends GroupBox{
    
    private Indexer pageFlipper;
    private FlightList flightList;
    
    private Flight[] Flights;
    
    public FlightBox(Flight[] Flights){
        super("Existing Flights", null);
        pageFlipper = new Indexer(-1, null, new PageListener());
        flightList = new FlightList(0, null);
        
        if(Flights != null && Flights.length != 0){
            int size = Flights.length;
            int pages = (size/5) + (size%5==0?0:1);
            this.Flights = new Flight[Flights.length];
            pageFlipper.setMax(pages);
            for (int i =0; i<Flights.length;i++){
                this.Flights[i] = Flights[i];
            }
            fillTable(pageFlipper.getPage());
        }
        
        
        
        addComponent(pageFlipper);
        addComponent(flightList);
    }
    
    
    public FlightBox(){
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
        int len = indexTOCount(Flights.length)-start; 
        len = boundMaximumBound(len);
        
        Flight[] flightLst = new Flight[5];
        
        for(int i =0; i<len;i++){
            flightLst[i] = Flights[start-1+i];
        }
        
        flightList.setList(start, flightLst);
    }
    
    public int[] range(){
        if(Flights != null){
            if(pageFlipper.getMax() == pageFlipper.getPage() && Flights.length != 5){
                int len = 1+Flights.length%5==0?(Flights.length==0?0:5):(Flights.length%5);
                return new int[]{(pageFlipper.getPage()-1)*5, (pageFlipper.getPage()-1)*5+len};
            }else if(pageFlipper.getMax() == -1){
                return null;
            }else{
                return new int[]{(pageFlipper.getPage()-1)*5,(pageFlipper.getPage()-1)*5+5};
            }
        }
        return null;
        
    }
    
    public void setFlights(Flight[] Flights){
        this.Flights = Flights;
        if(Flights != null && Flights.length != 0){
            int size = Flights.length;
            int pages = (size/5) + (size%5==0?0:1);
            this.Flights = new Flight[Flights.length];
            pageFlipper.setMax(pages);
            for (int i =0; i<Flights.length;i++){
                this.Flights[i] = Flights[i];
            }
            fillTable(pageFlipper.getPage());
        }else{
            flightList.setList(1, null);
        }
    }
    public Flight[] getFlights(){
        return this.Flights;
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
                JOptionPane.showMessageDialog(null, "Day must be a number", "Invalid Input",
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

