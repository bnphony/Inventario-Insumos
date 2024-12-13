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

/**
 *
 * @author windows
 */
public class RenderPrueba extends DefaultTableCellRenderer {
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        
        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, 
                hasFocus, row, 5);
        
        if(value.getClass().equals(Double.class) && column == 5){
            double valor = Double.parseDouble(table.getValueAt(row, column).toString());
            
            if(valor < Double.parseDouble(table.getValueAt(row, 2).toString()) && column == 5){
                cell.setBackground(new Color(243,28,28));
                cell.setForeground(Color.white);
            }else if(valor <= Double.parseDouble(table.getValueAt(row, 3).toString())
                    && valor >= Double.parseDouble(table.getValueAt(row, 2).toString()) && column == 5){
                cell.setBackground(new Color(255,255,65));
                cell.setForeground(Color.black);             
            }else if(column == 5){
                cell.setBackground(new Color(69,255,23));
                cell.setForeground(Color.black);
            }
            
            return cell;
        }else{
            cell.setBackground(table.getBackground());
            cell.setForeground(table.getForeground());
        }
        
        
        return cell;
    }
    
}
