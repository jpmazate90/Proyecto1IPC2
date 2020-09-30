
package Logica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class HTML {// constantes que serviran para el lenguaje 
   private static final String ESTILO_TABLA="<table style=\"border-collapse: collapse;\">";
    private static final String INICIO_OPERADOR_TR="<tr>";
    private static final String FIN_OPERADOR_TR="</tr>";
    private static final String ESTILO_LINEA_INICIAL="<th style=\"border: 1px solid #000000;\">";
    private static final String FIN_OPERADOR_TH="</th>";// constantes que serviran para el lenguaje 
    private static final String ESTILO_LINEA_ATRIBUTO="<td style=\"border: 1px solid #000000;\">";
    private static final String FIN_OPERADOR_TD="</td>";
    private static final String FIN_OPERADOR_TABLA="</table>";
    private static final String INICIO_OPERADOR_HTML="<html>";
    private static final String FIN_OPERADOR_HTML="</html>";// constantes que serviran para el lenguaje 
    private static final String INICIO_OPERADOR_H="<h1>";
    private static final String FIN_OPERADOR_H="</h1>";
    private static final String INICIO_OPERADOR_P="<p>";
    private static final String FIN_OPERADOR_P="</p>";
    private static final String PLANTILLA_ID="ID";// constantes que serviran para el lenguaje 
    private static final String PLANTILLA_NOMBRE="NOMBRE";
    private static final String PLANTILLA_ACTIVO="ACTIVO";
    private static final String PLANTILLA_ADMINISTRADOR_PROYECTO="ADMINISTRADOR PROYECTO";
    private static final String PLANTILLA_CANTIDAD_CASOS="CANTIDAD CASOS";
    private static final String PLANTILLA_ID_CASO="ID CASO";
    private static final String PLANTILLA_NOMBRE_CASO="NOMBRE CASO";
    private static final String PlANTILLA_ID_ETAPA="ID ETAPA";
    private static final String PlANTILLA_TIEMPO_ETAPA="TIEMPO ETAPA";// constantes que serviran para el lenguaje 
    private static final String PlANTILLA_TOTAL="TOTAL";
    private static final String PlANTILLA_TOTAL_DINERO="TOTAL DINERO";
    private static final String PlANTILLA_DESARROLLADOR_A_CARGO="DESARROLLADOR A CARGO";
    private static final String PLANTILLA_TIEMPO_HORAS ="TIEMPO HORAS";
    private static final String PLANTILLA_DESARROLLADOR="DESARROLLADOR";
    private static final String PLANTILLA_TIPO="TIPO";
    private static final String PLANTILLA_NOMBRE_ETAPA="NOMBRE ETAPA";
    private static final String PLANTILLA_APRODABO="APROBADO";
    private static final String PLANTILLA_FECHA_LIMITE="FECHA LIMITE";// constantes que serviran para el lenguaje 
    private static final String PLANTILLA_USUARIO_QUE_REGISTRO_REPORTE="USUARIO QUE REGISTRO EL REPORTE";
    private static final String PLANTILLA_BUSQUEDA_POR="BUSQUEDA POR";
    private static final String PLANTILLA_ID_PROYECTO="ID PROYECTO";// constantes que serviran para el lenguaje 
    
    private static final String PLANTILLA_PORCENTAJE_AVANCE="PORCENTAJE AVANCE";// constantes que serviran para el lenguaje 
    
    // crea un objeto filechooser para escoger
    public static File usarFileChooser(){
        JFileChooser guardarComo = new JFileChooser();
        guardarComo.setApproveButtonText("Guardar");
        guardarComo.showSaveDialog(null);
        File archivo = new File (guardarComo.getSelectedFile()+".html");
        return archivo;
    }
    // dice si se genero el archivo correctamente
    public static void decirQueSeGeneroElArchivo(){
        JOptionPane.showMessageDialog(null, "Se genero el archivo html correctamente");
    }
    
    public static void generarTitulo(File archivo, String titulo){
        try{// abre la via para escribir en el archivo
            File archivoReportes = archivo;
            FileWriter escritura = new FileWriter(archivoReportes, true);
            BufferedWriter escritor = new BufferedWriter(escritura);
            // crea unicamente el titulo de un archivo
            escritor.write(INICIO_OPERADOR_HTML);
            escritor.write(INICIO_OPERADOR_H);
            escritor.write(titulo);
            escritor.write(FIN_OPERADOR_H);
            escritor.newLine(); 
            
            escritor.write(FIN_OPERADOR_HTML);
                
            escritor.flush();
            escritor.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void generarReporte0(File archivo, DefaultTableModel modelo,String usuario){
        try{// abre el archivo para su uso
                File archivoReportes = archivo;
                FileWriter escritura = new FileWriter(archivoReportes, true);
                BufferedWriter escritor = new BufferedWriter(escritura);
            // añade las fechas iniciales y finales 
                

                escritor.write(ESTILO_TABLA);
                escritor.newLine();
                escritor.write(INICIO_OPERADOR_TR);
                escritor.newLine();
                //agarra todo lo de la tabla en un arreglo
                String[] plantillas ={PLANTILLA_ID, PLANTILLA_NOMBRE, PLANTILLA_ACTIVO,PLANTILLA_ADMINISTRADOR_PROYECTO, PLANTILLA_CANTIDAD_CASOS};
            //ciclo para agilizar la escritura
            for(int j=0;j<plantillas.length;j++){
                escritor.write(ESTILO_LINEA_INICIAL);
                escritor.write(plantillas[j]);
                escritor.write(FIN_OPERADOR_TH);
                escritor.newLine();
            }
            escritor .write(FIN_OPERADOR_TR);
            escritor .newLine();
            
            // ciclo por el cual añade los valores a la tabla
            for(int i=0;i<modelo.getRowCount();i++){
                escritor.write(INICIO_OPERADOR_TR);
                String[] datos = new String[5];
                datos[0] = (String) modelo.getValueAt(i,0);
                datos[1] = (String) modelo.getValueAt(i,1);
                datos[2] = (String) modelo.getValueAt(i,2);
                datos[3] = (String) modelo.getValueAt(i,3);
                datos[4] = (String) modelo.getValueAt(i,4);
                // añade los datos a la tabla
                for(int k=0; k<datos.length;k++){
                      escritor.write(ESTILO_LINEA_ATRIBUTO);
                      escritor.write(datos[k]);
                      escritor.write(FIN_OPERADOR_TD);
                      escritor.newLine();
                  }
                escritor .write(FIN_OPERADOR_TR);
            }
            // termina la tabla
            escritor .write(FIN_OPERADOR_TABLA);
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_USUARIO_QUE_REGISTRO_REPORTE+": "+ usuario);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            
            escritor .write(FIN_OPERADOR_HTML);
            escritor .flush();
            escritor.close();
            
            decirQueSeGeneroElArchivo();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    public static void generarReporte1(File archivo, DefaultTableModel modelo,String usuario,String total){
        try{// abre el archivo para su uso
                File archivoReportes = archivo;
                FileWriter escritura = new FileWriter(archivoReportes, true);
                BufferedWriter escritor = new BufferedWriter(escritura);
            // añade las fechas iniciales y finales 
                

                escritor.write(ESTILO_TABLA);
                escritor.newLine();
                escritor.write(INICIO_OPERADOR_TR);
                escritor.newLine();
                //agarra todo lo de la tabla en un arreglo
                String[] plantillas ={PLANTILLA_ID_CASO, PLANTILLA_NOMBRE_CASO, PlANTILLA_ID_ETAPA,PlANTILLA_TIEMPO_ETAPA, PlANTILLA_TOTAL};
                
            //ciclo para agilizar la escritura
            for(int j=0;j<plantillas.length;j++){
                escritor.write(ESTILO_LINEA_INICIAL);
                escritor.write(plantillas[j]);
                escritor.write(FIN_OPERADOR_TH);
                escritor.newLine();
            }
            escritor .write(FIN_OPERADOR_TR);
            escritor .newLine();
            
            // ciclo por el cual añade los valores a la tabla
            for(int i=0;i<modelo.getRowCount();i++){
                escritor.write(INICIO_OPERADOR_TR);
                String[] datos = new String[5];
                datos[0] = (String) modelo.getValueAt(i,0);
                datos[1] = (String) modelo.getValueAt(i,1);
                datos[2] = (String) modelo.getValueAt(i,2);
                datos[3] = (String) modelo.getValueAt(i,3);
                datos[4] = (String) modelo.getValueAt(i,4);
                // añade los datos a la tabla
                for(int k=0; k<datos.length;k++){
                      escritor.write(ESTILO_LINEA_ATRIBUTO);
                      escritor.write(datos[k]);
                      escritor.write(FIN_OPERADOR_TD);
                      escritor.newLine();
                  }
                escritor .write(FIN_OPERADOR_TR);
            }
            // termina la tabla
            escritor .write(FIN_OPERADOR_TABLA);
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PlANTILLA_TOTAL_DINERO+": "+ total);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_USUARIO_QUE_REGISTRO_REPORTE+": "+ usuario);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            
            escritor .write(FIN_OPERADOR_HTML);
            escritor .flush();
            escritor.close();
            
            decirQueSeGeneroElArchivo();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static void generarReporte2(File archivo, DefaultTableModel modelo, String usuario ,String total){
        try{// abre el archivo para su uso
                String desarrollador;
                File archivoReportes = archivo;
                FileWriter escritura = new FileWriter(archivoReportes, true);
                BufferedWriter escritor = new BufferedWriter(escritura);
                desarrollador = (String)modelo.getValueAt(0, 3);
            // añade las fechas iniciales y finales 
                escritor.write(INICIO_OPERADOR_P);
                escritor.write(PLANTILLA_DESARROLLADOR+": "+ desarrollador);
                escritor.write(FIN_OPERADOR_P);
                escritor.newLine();

                escritor.write(ESTILO_TABLA);
                escritor.newLine();
                escritor.write(INICIO_OPERADOR_TR);
                escritor.newLine();
                
                //agarra todo lo de la tabla en un arreglo
                String[] plantillas ={PLANTILLA_ID_CASO, PLANTILLA_NOMBRE_CASO, PlANTILLA_ID_ETAPA,PlANTILLA_DESARROLLADOR_A_CARGO,PLANTILLA_TIEMPO_HORAS, PlANTILLA_TOTAL};
                
            //ciclo para agilizar la escritura
            for(int j=0;j<plantillas.length;j++){
                escritor.write(ESTILO_LINEA_INICIAL);
                escritor.write(plantillas[j]);
                escritor.write(FIN_OPERADOR_TH);
                escritor.newLine();
            }
            escritor .write(FIN_OPERADOR_TR);
            escritor .newLine();
            
            // ciclo por el cual añade los valores a la tabla
            for(int i=0;i<modelo.getRowCount();i++){
                escritor.write(INICIO_OPERADOR_TR);
                String[] datos = new String[6];
                datos[0] = (String) modelo.getValueAt(i,0);
                datos[1] = (String) modelo.getValueAt(i,1);
                datos[2] = (String) modelo.getValueAt(i,2);
                datos[3] = (String) modelo.getValueAt(i,3);
                datos[4] = (String) modelo.getValueAt(i,4);
                datos[5] = (String) modelo.getValueAt(i,5);
                // añade los datos a la tabla
                for(int k=0; k<datos.length;k++){
                      escritor.write(ESTILO_LINEA_ATRIBUTO);
                      escritor.write(datos[k]);
                      escritor.write(FIN_OPERADOR_TD);
                      escritor.newLine();
                  }
                escritor .write(FIN_OPERADOR_TR);
            }
            // termina la tabla
            escritor .write(FIN_OPERADOR_TABLA);
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PlANTILLA_TOTAL_DINERO+": "+ total);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_USUARIO_QUE_REGISTRO_REPORTE+": "+ usuario);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            
            escritor .write(FIN_OPERADOR_HTML);
            escritor .flush();
            escritor.close();
            
            decirQueSeGeneroElArchivo();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

    public static void generarReporte3(File archivo, DefaultTableModel modelo, String usuario ,String total){
        try{// abre el archivo para su uso
                String tipo;
                File archivoReportes = archivo;
                FileWriter escritura = new FileWriter(archivoReportes, true);
                BufferedWriter escritor = new BufferedWriter(escritura);
                tipo = (String)modelo.getValueAt(0, 3);
            // añade las fechas iniciales y finales 
                escritor.write(INICIO_OPERADOR_P);
                escritor.write(PLANTILLA_DESARROLLADOR+": "+ tipo);
                escritor.write(FIN_OPERADOR_P);
                escritor.newLine();

                escritor.write(ESTILO_TABLA);
                escritor.newLine();
                escritor.write(INICIO_OPERADOR_TR);
                escritor.newLine();
                
                //agarra todo lo de la tabla en un arreglo
                String[] plantillas ={PLANTILLA_ID_CASO, PLANTILLA_NOMBRE_CASO, PlANTILLA_ID_ETAPA,PLANTILLA_TIPO,PLANTILLA_TIEMPO_HORAS, PlANTILLA_TOTAL};
                
            //ciclo para agilizar la escritura
            for(int j=0;j<plantillas.length;j++){
                escritor.write(ESTILO_LINEA_INICIAL);
                escritor.write(plantillas[j]);
                escritor.write(FIN_OPERADOR_TH);
                escritor.newLine();
            }
            escritor .write(FIN_OPERADOR_TR);
            escritor .newLine();
            
            // ciclo por el cual añade los valores a la tabla
            for(int i=0;i<modelo.getRowCount();i++){
                escritor.write(INICIO_OPERADOR_TR);
                String[] datos = new String[6];
                datos[0] = (String) modelo.getValueAt(i,0);
                datos[1] = (String) modelo.getValueAt(i,1);
                datos[2] = (String) modelo.getValueAt(i,2);
                datos[3] = (String) modelo.getValueAt(i,3);
                datos[4] = (String) modelo.getValueAt(i,4);
                datos[5] = (String) modelo.getValueAt(i,5);
                // añade los datos a la tabla
                for(int k=0; k<datos.length;k++){
                      escritor.write(ESTILO_LINEA_ATRIBUTO);
                      escritor.write(datos[k]);
                      escritor.write(FIN_OPERADOR_TD);
                      escritor.newLine();
                  }
                escritor .write(FIN_OPERADOR_TR);
            }
            // termina la tabla
            escritor .write(FIN_OPERADOR_TABLA);
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PlANTILLA_TOTAL_DINERO+": "+ total);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_USUARIO_QUE_REGISTRO_REPORTE+": "+ usuario);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            
            escritor .write(FIN_OPERADOR_HTML);
            escritor .flush();
            escritor.close();
            
            decirQueSeGeneroElArchivo();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }// aqui me quede
    public static void generarReporte5(File archivo, DefaultTableModel modelo, String usuario ,String total){
        try{// abre el archivo para su uso
                String tipo;
                File archivoReportes = archivo;
                FileWriter escritura = new FileWriter(archivoReportes, true);
                BufferedWriter escritor = new BufferedWriter(escritura);
                tipo = (String)modelo.getValueAt(0, 2);
            // añade las fechas iniciales y finales 
                escritor.write(INICIO_OPERADOR_P);
                escritor.write(PLANTILLA_DESARROLLADOR+": "+ tipo);
                escritor.write(FIN_OPERADOR_P);
                escritor.newLine();

                escritor.write(ESTILO_TABLA);
                escritor.newLine();
                escritor.write(INICIO_OPERADOR_TR);
                escritor.newLine();
                
                //agarra todo lo de la tabla en un arreglo
                String[] plantillas ={PlANTILLA_ID_ETAPA,PLANTILLA_NOMBRE_ETAPA, PLANTILLA_DESARROLLADOR,PLANTILLA_ACTIVO,PLANTILLA_TIEMPO_HORAS,PLANTILLA_APRODABO,PLANTILLA_FECHA_LIMITE,PLANTILLA_ID_CASO, PlANTILLA_TOTAL};
                
            //ciclo para agilizar la escritura
            for(int j=0;j<plantillas.length;j++){
                escritor.write(ESTILO_LINEA_INICIAL);
                escritor.write(plantillas[j]);
                escritor.write(FIN_OPERADOR_TH);
                escritor.newLine();
            }
            escritor .write(FIN_OPERADOR_TR);
            escritor .newLine();
            
            // ciclo por el cual añade los valores a la tabla
            for(int i=0;i<modelo.getRowCount();i++){
                escritor.write(INICIO_OPERADOR_TR);
                String[] datos = new String[9];
                datos[0] = (String) modelo.getValueAt(i,0);
                datos[1] = (String) modelo.getValueAt(i,1);
                datos[2] = (String) modelo.getValueAt(i,2);
                datos[3] = (String) modelo.getValueAt(i,3);
                datos[4] = (String) modelo.getValueAt(i,4);
                datos[5] = (String) modelo.getValueAt(i,5);
                datos[6] = (String) modelo.getValueAt(i,6);
                datos[7] = (String) modelo.getValueAt(i,7);
                datos[8] = (String) modelo.getValueAt(i,8);
                // añade los datos a la tabla
                for(int k=0; k<datos.length;k++){
                      escritor.write(ESTILO_LINEA_ATRIBUTO);
                      escritor.write(datos[k]);
                      escritor.write(FIN_OPERADOR_TD);
                      escritor.newLine();
                  }
                escritor .write(FIN_OPERADOR_TR);
            }
            // termina la tabla
            escritor .write(FIN_OPERADOR_TABLA);
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PlANTILLA_TOTAL_DINERO+": "+ total);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_USUARIO_QUE_REGISTRO_REPORTE+": "+ usuario);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            
            escritor .write(FIN_OPERADOR_HTML);
            escritor .flush();
            escritor.close();
            
            decirQueSeGeneroElArchivo();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void generarReporte7y8(File archivo, DefaultTableModel modelo, String usuario ,String total){
        try{// abre el archivo para su uso
                String tipo;
                File archivoReportes = archivo;
                FileWriter escritura = new FileWriter(archivoReportes, true);
                BufferedWriter escritor = new BufferedWriter(escritura);
                tipo = (String)modelo.getValueAt(0,3);
            // añade las fechas iniciales y finales 
                escritor.write(INICIO_OPERADOR_P);
                escritor.write(PLANTILLA_DESARROLLADOR+": "+ tipo);
                escritor.write(FIN_OPERADOR_P);
                escritor.newLine();

                escritor.write(ESTILO_TABLA);
                escritor.newLine();
                escritor.write(INICIO_OPERADOR_TR);
                escritor.newLine();
                
                //agarra todo lo de la tabla en un arreglo
                String[] plantillas ={PLANTILLA_ID_CASO,PLANTILLA_NOMBRE_CASO,PlANTILLA_ID_ETAPA, PlANTILLA_DESARROLLADOR_A_CARGO,PLANTILLA_TIEMPO_HORAS,PLANTILLA_TIEMPO_HORAS, PlANTILLA_TOTAL};
                
            //ciclo para agilizar la escritura
            for(int j=0;j<plantillas.length;j++){
                escritor.write(ESTILO_LINEA_INICIAL);
                escritor.write(plantillas[j]);
                escritor.write(FIN_OPERADOR_TH);
                escritor.newLine();
            }
            escritor .write(FIN_OPERADOR_TR);
            escritor .newLine();
            
            // ciclo por el cual añade los valores a la tabla
            for(int i=0;i<modelo.getRowCount();i++){
                escritor.write(INICIO_OPERADOR_TR);
                String[] datos = new String[6];
                datos[0] = (String) modelo.getValueAt(i,0);
                datos[1] = (String) modelo.getValueAt(i,1);
                datos[2] = (String) modelo.getValueAt(i,2);
                datos[3] = (String) modelo.getValueAt(i,3);
                datos[4] = (String) modelo.getValueAt(i,4);
                datos[5] = (String) modelo.getValueAt(i,5);
          
                // añade los datos a la tabla
                for(int k=0; k<datos.length;k++){
                      escritor.write(ESTILO_LINEA_ATRIBUTO);
                      escritor.write(datos[k]);
                      escritor.write(FIN_OPERADOR_TD);
                      escritor.newLine();
                  }
                escritor .write(FIN_OPERADOR_TR);
            }
            // termina la tabla
            escritor .write(FIN_OPERADOR_TABLA);
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PlANTILLA_TOTAL_DINERO+": "+ total);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_USUARIO_QUE_REGISTRO_REPORTE+": "+ usuario);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            
            escritor .write(FIN_OPERADOR_HTML);
            escritor .flush();
            escritor.close();
            
            decirQueSeGeneroElArchivo();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void generarReporte9EnAdelante(File archivo, DefaultTableModel modelo, String usuario ,int posicionAgarrarValor,String busqueda){
        try{// abre el archivo para su uso
                String tipo;
                File archivoReportes = archivo;
                FileWriter escritura = new FileWriter(archivoReportes, true);
                BufferedWriter escritor = new BufferedWriter(escritura);
                
                tipo = (String)modelo.getValueAt(0,posicionAgarrarValor);
            // añade las fechas iniciales y finales 
                escritor.write(INICIO_OPERADOR_P);
                escritor.write(PLANTILLA_BUSQUEDA_POR+" "+busqueda+": "+ tipo);
                escritor.write(FIN_OPERADOR_P);
                escritor.newLine();

                escritor.write(ESTILO_TABLA);
                escritor.newLine();
                escritor.write(INICIO_OPERADOR_TR);
                escritor.newLine();
                
                //agarra todo lo de la tabla en un arreglo
                String[] plantillas ={PLANTILLA_ID,PLANTILLA_NOMBRE,PLANTILLA_ID_PROYECTO, PLANTILLA_FECHA_LIMITE,PLANTILLA_PORCENTAJE_AVANCE,PLANTILLA_ACTIVO, PLANTILLA_TIPO};
                
            //ciclo para agilizar la escritura
            for(int j=0;j<plantillas.length;j++){
                escritor.write(ESTILO_LINEA_INICIAL);
                escritor.write(plantillas[j]);
                escritor.write(FIN_OPERADOR_TH);
                escritor.newLine();
            }
            escritor .write(FIN_OPERADOR_TR);
            escritor .newLine();
            
            // ciclo por el cual añade los valores a la tabla
            for(int i=0;i<modelo.getRowCount();i++){
                escritor.write(INICIO_OPERADOR_TR);
                String[] datos = new String[7];
                datos[0] = (String) modelo.getValueAt(i,0);
                datos[1] = (String) modelo.getValueAt(i,1);
                datos[2] = (String) modelo.getValueAt(i,2);
                datos[3] = (String) modelo.getValueAt(i,3);
                datos[4] = (String) modelo.getValueAt(i,4);
                datos[5] = (String) modelo.getValueAt(i,5);
                datos[6] = (String) modelo.getValueAt(i,6);
          
                // añade los datos a la tabla
                for(int k=0; k<datos.length;k++){
                      escritor.write(ESTILO_LINEA_ATRIBUTO);
                      escritor.write(datos[k]);
                      escritor.write(FIN_OPERADOR_TD);
                      escritor.newLine();
                  }
                escritor .write(FIN_OPERADOR_TR);
            }
            // termina la tabla
            escritor .write(FIN_OPERADOR_TABLA);

            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_USUARIO_QUE_REGISTRO_REPORTE+": "+ usuario);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            
            escritor .write(FIN_OPERADOR_HTML);
            escritor .flush();
            escritor.close();
            
            decirQueSeGeneroElArchivo();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
     public static void generarReporte12(File archivo, DefaultTableModel modelo, String usuario,String desarrollador,String busqueda){
        try{// abre el archivo para su uso
                String tipo;
                File archivoReportes = archivo;
                FileWriter escritura = new FileWriter(archivoReportes, true);
                BufferedWriter escritor = new BufferedWriter(escritura);
                
            // añade las fechas iniciales y finales 
                escritor.write(INICIO_OPERADOR_P);
                escritor.write(PLANTILLA_BUSQUEDA_POR+" "+busqueda+": "+desarrollador );
                escritor.write(FIN_OPERADOR_P);
                escritor.newLine();

                escritor.write(ESTILO_TABLA);
                escritor.newLine();
                escritor.write(INICIO_OPERADOR_TR);
                escritor.newLine();
                
                //agarra todo lo de la tabla en un arreglo
                String[] plantillas ={PLANTILLA_ID,PLANTILLA_NOMBRE,PLANTILLA_ID_PROYECTO, PLANTILLA_FECHA_LIMITE,PLANTILLA_PORCENTAJE_AVANCE,PLANTILLA_ACTIVO, PLANTILLA_TIPO};
                
            //ciclo para agilizar la escritura
            for(int j=0;j<plantillas.length;j++){
                escritor.write(ESTILO_LINEA_INICIAL);
                escritor.write(plantillas[j]);
                escritor.write(FIN_OPERADOR_TH);
                escritor.newLine();
            }
            escritor .write(FIN_OPERADOR_TR);
            escritor .newLine();
            
            // ciclo por el cual añade los valores a la tabla
            for(int i=0;i<modelo.getRowCount();i++){
                escritor.write(INICIO_OPERADOR_TR);
                String[] datos = new String[7];
                datos[0] = (String) modelo.getValueAt(i,0);
                datos[1] = (String) modelo.getValueAt(i,1);
                datos[2] = (String) modelo.getValueAt(i,2);
                datos[3] = (String) modelo.getValueAt(i,3);
                datos[4] = (String) modelo.getValueAt(i,4);
                datos[5] = (String) modelo.getValueAt(i,5);
                datos[6] = (String) modelo.getValueAt(i,6);
          
                // añade los datos a la tabla
                for(int k=0; k<datos.length;k++){
                      escritor.write(ESTILO_LINEA_ATRIBUTO);
                      escritor.write(datos[k]);
                      escritor.write(FIN_OPERADOR_TD);
                      escritor.newLine();
                  }
                escritor .write(FIN_OPERADOR_TR);
            }
            // termina la tabla
            escritor .write(FIN_OPERADOR_TABLA);

            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_USUARIO_QUE_REGISTRO_REPORTE+": "+ usuario);
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            
            escritor .write(FIN_OPERADOR_HTML);
            escritor .flush();
            escritor.close();
            
            decirQueSeGeneroElArchivo();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
