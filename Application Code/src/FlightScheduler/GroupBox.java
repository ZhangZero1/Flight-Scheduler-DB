/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerfrederickffz5015;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Whyer
 */
public class GroupBox extends JPanel{
    
    public GroupBox(String name, Component... component){
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        if(component != null){
            for(Component c: component){
                add(c);
            }
        }
        setBorder(BorderFactory.createTitledBorder(name));
    }
    
    public void addComponent(Component c){
        add(c);
    }
    public void removeComponent(int index){
        remove(index);
    }

}
