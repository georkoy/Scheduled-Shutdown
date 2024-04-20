/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author cst11036
 */
public class Mainframe extends JFrame{
 private JTextField timefield;
 private JButton okbuton;
 private JTextField minutesfield;
    public Mainframe() {
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(450,180));
        setTitle("Scheduled Computer Shutdown  ");
        
       setSize(new Dimension(450, 180));  
       setMaximumSize(new Dimension(450, 180));
       setMinimumSize(new Dimension(450, 180));
   
       JPanel mainpanel1=new JPanel(new FlowLayout()) ;
       JPanel mainpanel=new JPanel(new GridLayout(2, WIDTH,0,15));
        
       
        JLabel timelabel=new JLabel("Hour: ");
         timefield=new JTextField(10);    
         JLabel minuuteslabel=new JLabel("Minutes: ");
         minutesfield=new JTextField(10); 


    mainpanel.add(timelabel);
    mainpanel.add(timefield);
    mainpanel.add(minuuteslabel);
    mainpanel.add(minutesfield);
    mainpanel1.add(mainpanel);
    
    JPanel buttonspanel=new JPanel(   );
     okbuton=new JButton("OK");
    JButton cancelbutton= new JButton("Cancel");
    buttonspanel.add(okbuton);
    buttonspanel.add(cancelbutton);
    
    add(buttonspanel,BorderLayout.SOUTH); 
    add(mainpanel1,BorderLayout.CENTER);
 
    okbuton.setEnabled(false);
    
       
    
    timefield.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                       
                  if(timefield.getText().isEmpty() || minutesfield.getText().isEmpty()){
                    okbuton.setEnabled(false);
                }
                  else {
                    okbuton.setEnabled(true);
                    
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                  if(timefield.getText().isEmpty() || minutesfield.getText().isEmpty()){
                    okbuton.setEnabled(false);
                }
                  else {
                    okbuton.setEnabled(true);
                   
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
              
            }
        });
        
     minutesfield.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                  if(minutesfield.getText().isEmpty() || timefield.getText().isEmpty()){
                    okbuton.setEnabled(false);
                   
                }
                  else {
                    okbuton.setEnabled(true);
                   
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                  if(minutesfield.getText().isEmpty() || timefield.getText().isEmpty()){
                    okbuton.setEnabled(false); 
                }
                  else {
                    okbuton.setEnabled(true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
              
            }
        });
    okbuton.addActionListener(new ActionListener() {
  private boolean oktm=false,okmn=false;
            @Override
            public void actionPerformed(ActionEvent e) {          
                if(!timefield.getText().isEmpty() && !minutesfield.getText().isEmpty()){
                        try{
                          Integer.parseInt(minutesfield.getText());   
                          okmn=true;
                        }
                    catch(NumberFormatException ea){
                        //not integer
                        okmn=false;
                        JOptionPane.showMessageDialog(rootPane, "Minute must be an integer","ERROR",JOptionPane.ERROR_MESSAGE);
                    }
                        
                         try{
                          Integer.parseInt(timefield.getText()); 
                          oktm=true;
                          
                        }
                    catch(NumberFormatException eas){
                        //not integer
                        oktm=false;
                        JOptionPane.showMessageDialog(rootPane, "Hour must be an integer","ERROR",JOptionPane.ERROR_MESSAGE);
                    }
        if(okmn && oktm){
                    JOptionPane.showMessageDialog(rootPane, "Computer will shutdown in "+timefield.getText()+ " hours and "+minutesfield.getText()+" minutes","Inform Meessege",JOptionPane.INFORMATION_MESSAGE);
     int  summinnutes=Integer.parseInt(timefield.getText())*3600+Integer.parseInt(minutesfield.getText())*60;
                        try {
                            Runtime.getRuntime().exec("shutdown -s -t "+summinnutes);
                        } catch (IOException ex) {
                            Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                        }
       System.exit(0);
        }     
                }
            }
            
        });

    cancelbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              System.exit(0);
            }
        });
    
    }
}
