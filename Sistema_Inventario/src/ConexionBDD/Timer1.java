/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionBDD;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author windows
 */
public class Timer1 {
    private Timer t;
    private int h, m, s, cs;
    
    public Timer1(){
        t = new Timer(10, acciones);
    }
    
    
    private ActionListener acciones = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            cs++;
            
            if(cs == 100){
                cs=0;
                ++s;
            }
            if(s == 60){
                s = 0;
                ++m;
            }
            if(m == 60){
                m = 0;
                ++h;
            }            
        }    
    
    };
    
    
    public void iniciar1(){
        t.start();        
    }
    
    public void pausar(String x){
        t.stop();
        System.out.println(x + " = " + (h<=9?"0":"") + h + ": "
                + "" + (m<=9?"0":"") + m + ": "
                + "" + (s<=9?"0":"") + s + ": "
                + "" + (cs<=9?"0":"") + cs);
        h = 0;
        m = 0; 
        s = 0; 
        cs = 0;
    }
}
