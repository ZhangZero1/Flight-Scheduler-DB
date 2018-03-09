/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerfrederickffz5015;

import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Whyer
 */
public class LazyScroll extends JScrollPane{
    JPanel view;
    public LazyScroll(){
        super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        view = new JPanel();
        setViewportView(view);
    }
    
    
    public void addItem(Component c){
        view.add(c);
        setViewportView(view);
    }
    public Component getItem(int index){
        return view.getComponent(index);
    }
    public void removeItem(int index){
        view.remove(index);
        setViewportView(view);
    }
    public void setLazyLayout(LayoutManager lm){
        view.setLayout(lm);
    }
    public void clear(){
        view.removeAll();
        setViewportView(view);
    }
    
    public JPanel getView(){
        return view;
    }
}
