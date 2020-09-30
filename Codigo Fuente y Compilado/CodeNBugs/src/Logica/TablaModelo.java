
package Logica;

import javax.swing.table.DefaultTableModel;


public class TablaModelo extends DefaultTableModel{
    
    @Override
    public boolean isCellEditable (int row, int column){
       // Aquí devolvemos true o false según queramos que una celda
       // identificada por fila,columna (row,column), sea o no editable
       if (column == -5 )
          return true;
       return false;
   }
}
    

