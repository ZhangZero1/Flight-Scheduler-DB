/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerfrederickffz5015;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Whyer
 */
public class Indexer extends JPanel{
    private int current;
    private int maxSize;
    
    private JTextField currPage;
    private JTextField maxPage;
    private JButton previous;
    private JButton next;
    
    private InputListener handler;
    
    
    public Indexer(Integer maxSize, InputListener L){
        this(0, maxSize, L);
    }
    public Indexer(int current,Integer lastPage, InputListener L){
        
        currPage = new JTextField("", 3);
        maxPage = new JTextField("", 3);
        previous = new JButton("Previous");
        next = new JButton("Next");
        
        

        handler = L;
        //handler.setListener(this);
        
        
        
        add(previous);
        add(currPage);
        add(new JLabel("of"));
        add(maxPage);
        
        add(next);
        
        if(lastPage == null){
            this.current = -1;
            this.maxSize = -1;
            previous.setEnabled(false);
            next.setEnabled(false);
        }
        else{
            maxSize = lastPage;
            this.current = current;
            
            previous.setEnabled(true);
            next.setEnabled(true);
            if(current == lastPage && lastPage != -1){
                next.setEnabled(false);
            }else if(current == 1){
                previous.setEnabled(false);
            }
            
            
            currPage.setText(String.valueOf(this.current));
            maxPage.setText(String.valueOf(this.maxSize));
        }
        
        previous.addActionListener(InputListener.getHandler("previousPage",handler));
        next.addActionListener(InputListener.getHandler("nextPage",handler));
        currPage.addActionListener(InputListener.getHandler("currentPage",handler));
        
        maxPage.addActionListener(InputListener.getHandler("maxPage",handler));
        
        
        
    }
    
    
    
    
    
    

    
    
    private void checkBounds(){
        
        next.setEnabled(true);
        previous.setEnabled(true);
        if(current == maxSize && maxSize != -1){
            next.setEnabled(false);
        }
        if(current == 1 && maxSize != -1){
            previous.setEnabled(false);
        }
        if(maxSize == -1){
            previous.setEnabled(false);
            next.setEnabled(false);
        }
        
    }
    
    public void setPage(int page){
        if(page > 0 && page <= maxSize && maxSize != -1){
            current = page;
            checkBounds();
            currPage.setText(String.valueOf(page));
        }else if(maxSize == -1){
            current = -1;
            checkBounds();
            currPage.setText("");
            JOptionPane.showMessageDialog(null, "Max Page is not Initialized", "Invalid Input",
                                          JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null, "Page must be between 1 and max Page", "Invalid Input",
                                          JOptionPane.INFORMATION_MESSAGE);
            setPage(getPage());
        }
    }
    public int getPage(){
        return current;
    }
    
    public void setMax(int max){
        if(max > 0){
            if(maxSize == -1){
                maxSize = max;
                setPage(1);
            }else if(current > max){
                maxSize = max;
                setPage(1);
            }else{
                maxSize = max;
            }
            
            maxPage.setText(String.valueOf(max));
        }else{
            previous.setEnabled(false);
            next.setEnabled(false);
            
            this.current = -1;
            this.maxSize = -1;
            maxPage.setText("");
            currPage.setText("");
        }
        checkBounds();
    }
    
    public int getMax(){
        return maxSize;
    }
    
}
