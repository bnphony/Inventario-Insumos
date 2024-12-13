/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author windows
 */
public class RenderNotificacion extends JTable {

   
    public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int ColumnIndex) {
        Component componente = super.prepareRenderer(renderer, rowIndex, ColumnIndex);
        
        if(getValueAt(rowIndex, ColumnIndex).getClass().equals(Double.class)){
            double valor = Double.parseDouble(this.getValueAt(rowIndex, ColumnIndex).toString());
            
            if(valor < Double.parseDouble(getValueAt(rowIndex, 2).toString()) && ColumnIndex == 5){
                componente.setBackground(new Color(199,0,49));
                componente.setForeground(Color.white);
            }else if(valor <= Double.parseDouble(getValueAt(rowIndex, 3).toString())
                    && valor >= Double.parseDouble(getValueAt(rowIndex, 2).toString()) && ColumnIndex == 5){
                componente.setBackground(new Color(255,255,65));
                componente.setForeground(Color.black);             
            }else if(ColumnIndex == 5){
                componente.setBackground(new Color(69,255,23));
                componente.setForeground(Color.black);
            }
        }else{            
            componente.setBackground(Color.white);
            componente.setForeground(Color.black);
        }
        
        return componente;
    }
}
