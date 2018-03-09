/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerfrederickffz5015;

/**
 *
 * @author Whyer
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Whyer
 */
public class DateBox extends GroupBox {
    
    private Indexer pageFlipper;
    private DateList dateList;
    
    private Date[] Dates;
    
    public DateBox(Date[] Dates){
        super("Existing Dates", null);
        pageFlipper = new Indexer(-1, null, new PageListener());
        dateList = new DateList(0, null);
        
        if(Dates != null && Dates.length != 0){
            int size = Dates.length;
            int pages = (size/5) + (size%5==0?0:1);
            this.Dates = new Date[Dates.length];
            pageFlipper.setMax(pages);
            for (int i =0; i<Dates.length;i++){
                this.Dates[i] = Dates[i];
            }
            fillTable(pageFlipper.getPage());
        }
        
        
        
        addComponent(pageFlipper);
        addComponent(dateList);
    }
    
    
    
    public DateBox(){
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
        int len = indexTOCount(Dates.length)-start; 
        len = boundMaximumBound(len);
        
        Date[] dateLst = new Date[5];
        
        for(int i =0; i<len;i++){
            dateLst[i] = Dates[start-1+i];
        }
        
        dateList.setList(start, dateLst);
    }
    
    public void setDates(Date[] Dates){
        this.Dates = Dates;
        if(Dates != null && Dates.length != 0){
            int size = Dates.length;
            int pages = (size/5) + (size%5==0?0:1);
            this.Dates = new Date[Dates.length];
            pageFlipper.setMax(pages);
            for (int i =0; i<Dates.length;i++){
                this.Dates[i] = Dates[i];
            }
            fillTable(pageFlipper.getPage());
        }
    }
    public Date[] getDates(){
        return this.Dates;
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
