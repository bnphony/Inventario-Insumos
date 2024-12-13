/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insumos;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import net.sf.jcarrierpigeon.Notification;
import net.sf.jcarrierpigeon.NotificationQueue;
import net.sf.jcarrierpigeon.WindowPosition;

/**
 *
 * @author windows
 */
public class Prueba extends JFrame{
    
    Border border = BorderFactory.createLineBorder(Color.black, 1);
    public Prueba(String datos){      
        System.out.println("Los datos recibidos son: " + datos);
        setBounds(0,0,400,200);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setResizable(false);
        imprimirComponentes(datos);
        
        
    }
    
    private void imprimirComponentes(String datos){
        JPanel panel = new JPanel();
        panel.setBackground(new Color(29,45,80));
        panel.setLayout(null);
        add(panel);
        JLabel label = new JLabel();
        label.setText(datos);
        label.setFont(new Font("Consolas", Font.PLAIN, 18));
        label.setBounds(50,50,300,100);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(border);
        label.setOpaque(true);
        label.setBackground(Color.white);
        panel.add(label);
        Notification nt = new Notification(this, WindowPosition.BOTTOMRIGHT, 0, 0, 2000);
        NotificationQueue lanzar = new NotificationQueue();
        lanzar.add(nt);
    }
    
    
    public static void main(String[] args){
        
    }

    
}

