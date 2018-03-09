/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightschedulerfrederickffz5015;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Whyer
 */
public abstract class InputListener implements ActionListener, MouseListener{
        
        protected String triggerName;
        
        
        public void setButton(String triggerName){
            this.triggerName = triggerName;
        }
        public abstract InputListener thisListener();
        
        public InputListener Clone(){
            InputListener temp = thisListener();
            temp.setButton(triggerName);
            return temp;
        }
        
        @Override
        public abstract void actionPerformed(ActionEvent e);
        
        public static InputListener getHandler(String txt, InputListener handler){
            InputListener temp = handler.Clone(); 
            temp.setButton(txt);
            return temp;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }