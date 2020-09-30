
package Logica;

import Entidades.Accion;
import Entidades.ControlVeces;
import Entidades.Etapa;
import Entidades.Proyecto;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jpmazate
 */
public class MostrarInformacion {
    //variable local privada
    private Connection conexion;
// constructor de la clase
    public MostrarInformacion(Connection conexion) {
        this.conexion = conexion;
    }
    // muestra la informacion de casos
    public void mostrarInformacionCasos(DefaultTableModel modelo, String idCaso){
        int id=Integer.parseInt(idCaso);
        try {
            PreparedStatement declaracion;// prepara la orden
            declaracion = conexion.prepareStatement("SELECT * FROM ETAPA WHERE Id_Caso=?;");
            declaracion.setInt(1,id);
            ResultSet resultado = declaracion.executeQuery();
            
            while (resultado.next()) {// ejecutta el resultado
                String datos[] = new String [9];
                try{// agarra el valor
                datos[0]= Integer.toString(resultado.getInt("Id"));
                }catch(NullPointerException e){
                    datos[0]="NULL";
                }// agarra el valor
                try{
                datos[1]= resultado.getString("Nombre");
                }catch(NullPointerException e){
                    datos[1]="NULL";
                }
                try{// agarra el valor
                datos[2]= resultado.getString("Desarrollador_A_Cargo");
                }catch(NullPointerException e){
                    datos[2]="NULL";
                }// agarra el valor
                try{
                datos[3]= Boolean.toString(resultado.getBoolean("Activo"));
                }catch(NullPointerException e){
                    datos[3]="NULL";
                }// agarra el valor
                try{
                datos[4]= Integer.toString(resultado.getInt("Tiempo_En_Horas"));
                }catch(NullPointerException e){
                    datos[4]="NULL";
                }// agarra el valor
                try{
                datos[5]= Boolean.toString(resultado.getBoolean("Aprobado"));
                }catch(NullPointerException e){
                    datos[5]="NULL";// agarra el valor
                }
                try{
                datos[6]= resultado.getDate("Fecha_Limite").toString();
                }catch(NullPointerException e){
                    datos[6]="NULL";// agarra el valor
                }
                try{
                datos[7]= Integer.toString(resultado.getInt("Id_Caso"));
                }catch(NullPointerException e){
                    datos[7]="NULL";// agarra el valor
                }
                try{
                datos[8]= Integer.toString(resultado.getInt("Total"));
                }catch(NullPointerException e){
                    datos[8]="NULL";// agarra el valor
                }
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // consluta casos del proyeccto
    public void consultaProyectoCasos(DefaultTableModel modelo, String idProyecto){
        int id=Integer.parseInt(idProyecto);
        PreparedStatement declaracion;
        try {// prepara la orden
            declaracion = conexion.prepareStatement(
                    "SELECT CASO.Id,CASO.Nombre,CASO.Id_Proyecto,CASO.Fecha_Limite,CASO.Porcentaje_Avance,CASO.Activo,CASO.Tipo  FROM CASO INNER JOIN PROYECTO ON CASO.Id_Proyecto=PROYECTO.Id AND CASO.Id_Proyecto=?;");
            declaracion.setInt(1,id);
            ResultSet resultado = declaracion.executeQuery();
            while(resultado.next()){// ejecuta el resultado
                String datos[] = new String[7];
                datos[0] = Integer.toString(resultado.getInt("Id"));
                datos[1] = resultado.getString("Nombre");
                datos[2] = Integer.toString(resultado.getInt("Id_Proyecto"));
                datos[3] = resultado.getDate("Fecha_Limite").toString();
                datos[4] = Integer.toString(resultado.getInt("Porcentaje_Avance"));
                datos[5] = Boolean.toString(resultado.getBoolean("Activo"));
                datos[6] = resultado.getString("Tipo");
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // agrega datos a las tablas de fechas limites y cercanas 
    public void agregarFechasLimites(DefaultTableModel modeloCercano, DefaultTableModel modeloFinalizado, DefaultTableModel modeloBase, int posicionFecha, int posicionId,String posicionPorcentaje){
        int posicionPorcentaje1;
        try{// compara un valor 
           posicionPorcentaje1=Integer.parseInt(posicionPorcentaje);
        }catch(Exception e){
            posicionPorcentaje1=-1;
        }// agarra el tamaño de la tabla
        int tamaño = modeloBase.getRowCount();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, +3);// crea una instancia de calendar
        Date dateMas3 = c.getTime();// crea un objeto date
        Date dateActual = new Date();
        Date fechaTabla;// que servira para comparar
        for(int i=0;i<tamaño;i++){// siglo del tamaño de casos registrads
            String fechaString = (String)modeloBase.getValueAt(i, posicionFecha);
            String id= (String)modeloBase.getValueAt(i, posicionId);
            String porcentaje=null;// agarra el valro y si es null significa que no importa ese parametr
            int valorPorcentaje;
            try {// agarra el valor del porcentaje
                porcentaje =(String)modeloBase.getValueAt(i, posicionPorcentaje1);
                valorPorcentaje=Integer.parseInt(porcentaje);
            } catch (Exception e) {
                porcentaje=null;
                valorPorcentaje=-1;
            }
            try {// si el porcentaje es nulo significa que no se usara
                if(posicionPorcentaje==null){
                
                fechaTabla = formato.parse(fechaString);
                int comparacion = dateMas3.compareTo(fechaTabla);
                // empieza a comparar fechas
                if(comparacion>=0){// si es amyor que sera entra e introduce en la tabla de modelo finalizado
                    int comparacion2= dateActual.compareTo(fechaTabla);
                    String datos[] = new String[2];
                    datos[0]= id;// añade el valor
                    datos[1]=formato.format(fechaTabla);
                    if(comparacion2>=0){
                        modeloFinalizado.addRow(datos);
                    }else{// si no es mayor a 0 entonces agrega al valor de modleo cercano
                        modeloCercano.addRow(datos);
                    }
                }
                }else{// si si importa el parametro de porcentaje
                    if(valorPorcentaje>=0 && valorPorcentaje<100){
                     // entonces compara de distinta forma   
                    fechaTabla = formato.parse(fechaString);
                    int comparacion = dateMas3.compareTo(fechaTabla);
                // la comparacion es mayor que cero entra
                    if(comparacion>=0){
                        int comparacion2= dateActual.compareTo(fechaTabla);
                        String datos[] = new String[2];
                        datos[0]= id;// si cumple entonces agrega a modelo finalizad
                        datos[1]=formato.format(fechaTabla);
                        if(comparacion2>=0){
                            modeloFinalizado.addRow(datos);
                        }else{// si no a la tabla de fechas cercanas
                            modeloCercano.addRow(datos);
                        }
                    }
                    }
                }
                
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    // muestra las acciones realizadas
    public void mostrarAcciones(DefaultTableModel modelo,String idProyecto){
        int id=Integer.parseInt(idProyecto);
        PreparedStatement declaracion;
        try {// prepara la orden
            declaracion = conexion.prepareStatement(
"SELECT DISTINCT ACCION.Id,ACCION.Nombre_Usuario,ACCION.Id_Proyecto,ACCION.Id_Caso,ACCION.Id_Etapa,ACCION.Fase_Proyecto,ACCION.Tipo_Accion,ACCION.Comentario,ACCION.Aprobado ,ACCION.Revisada FROM ACCION INNER JOIN PROYECTO INNER JOIN ETAPA INNER JOIN CASO ON ACCION.Id_Proyecto=PROYECTO.Id WHERE ACCION.Id_Caso=CASO.Id AND  PROYECTO.Id=?;"); 
            declaracion.setInt(1, id);
            ResultSet resultado = declaracion.executeQuery();
            Accion accion;// crea la accion
            while(resultado.next()){// manej el resultado
                accion = new Accion(resultado.getString("Nombre_Usuario"),resultado.getInt("Id_Proyecto"),resultado.getInt("Id_Caso"),resultado.getInt("Id_Etapa"),resultado.getString("Fase_Proyecto"),resultado.getString("Tipo_Accion"), resultado.getString("Comentario"),resultado.getBoolean("Aprobado"));
                String datos[] = new String[10];
                datos[0]=Integer.toString(resultado.getInt("Id"));
                datos[1]=resultado.getString("Nombre_Usuario");// manej el resultado
                datos[2]=Integer.toString(resultado.getInt("Id_Proyecto"));
                datos[3]=Integer.toString(resultado.getInt("Id_Caso"));
                datos[4]=Integer.toString(resultado.getInt("Id_Etapa"));// manej el resultado
                datos[5]=resultado.getString("Fase_Proyecto");
                datos[6]=resultado.getString("Tipo_Accion");
                datos[7]= resultado.getString("Comentario");// manej el resultado
                datos[8]=Boolean.toString(resultado.getBoolean("Aprobado"));
                datos[9]=Boolean.toString(resultado.getBoolean("Revisada"));// manej el resultado
                modelo.addRow(datos);// agrega
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    // devuelve un arreglo de los usuarios 
    public ArrayList<String> usuariosEnArreglo(String tipoUsuario){
        ArrayList<String> usuarios = new ArrayList<>();
        try {// devuelve un arreglo de los usuarios
            PreparedStatement declaracion;
            declaracion = conexion.prepareStatement("SELECT * FROM USUARIO WHERE Tipo_Usuario=?");
            declaracion.setString(1,tipoUsuario);
            ResultSet resultado = declaracion.executeQuery();
            // devuelve un arreglo de los usuarios
            while (resultado.next()) {
                usuarios.add(resultado.getString("Usuario"));
            }
            return usuarios;// devuelve un arreglo de los usuarios
        } catch (SQLException ex) {
            ex.printStackTrace();
            return usuarios;
        }
    }
    // transforma el arreglo en arreglo de objetos
    public Object[] transformarArrayListEnObject(ArrayList<String> arreglo){
        Object objetos[] = new Object[arreglo.size()];
        for(int i=0;i<arreglo.size();i++){
            objetos[i]=arreglo.get(i);
        }
        return objetos;
    }
    // cambia al administrador 
    public void cambiarAdministrador(String administrador, String idProyecto){
        int idProyectoEntero = Integer.parseInt(idProyecto);
        PreparedStatement declaracion;
        try {// prepara la orden para actualizar
            declaracion = conexion.prepareStatement("UPDATE PROYECTO SET Administrador_Proyecto=? WHERE Id=?");
            declaracion.setString(1, administrador);
            declaracion.setInt(2, idProyectoEntero);
            declaracion.executeUpdate();// ejecuta y muestra 
            JOptionPane.showMessageDialog(null,"Se cambio al administrador de proyecto correctamente");
            
        } catch (HeadlessException | SQLException e) {
            e.printStackTrace();
        }
    }
    // reporte de proyecto activo 
    public void reporteProyectoActivo(DefaultTableModel modelo,boolean activo){
        PreparedStatement declaracion;
        try {
            declaracion = conexion.prepareStatement(// prepara la orden
                    "SELECT * FROM PROYECTO WHERE Activo=?");
            declaracion.setBoolean(1, activo);
            ResultSet resultado = declaracion.executeQuery();
            while(resultado.next()){
                String datos[] = new String[7];// maneja el resultado
                datos[0] = Integer.toString(resultado.getInt("Id"));
                datos[1] = resultado.getString("Nombre");
                datos[2] = Boolean.toString(resultado.getBoolean("Activo"));
                datos[3] = resultado.getString("Administrador_Proyecto");
                datos[4] = Integer.toString(cantidadCasos(datos[0]));
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // cantidad de casos
    public int cantidadCasos(String idProyecto){
         PreparedStatement declaracion;
         int cantidadCasos=0;
         int id=Integer.parseInt(idProyecto);
        try {
            declaracion = conexion.prepareStatement(// prepara la orden
                    "SELECT COUNT(*) FROM CASO WHERE Id_Proyecto=?");
            declaracion.setInt(1, id);
            ResultSet resultado = declaracion.executeQuery();
            resultado.next();
            cantidadCasos = resultado.getInt(1);
            return cantidadCasos;//regresa la cnatidad de casos que tiene un proyecto en especifico
        }catch(Exception e ){
            e.printStackTrace();
            return cantidadCasos;
        }
    }
    
    public void reporteDineroHorasProyecto(DefaultTableModel modelo,String idProyecto){
        try{
        int id=Integer.parseInt(idProyecto);
        PreparedStatement declaracion;
        try {// prepara la orden
            declaracion = conexion.prepareStatement(
"SELECT CASO.Id, CASO.Nombre,ETAPA.Id,ETAPA.Total, ETAPA.Tiempo_En_Horas FROM ETAPA INNER JOIN PROYECTO INNER JOIN CASO ON PROYECTO.Id=CASO.Id_Proyecto AND CASO.Id=ETAPA.Id_Caso AND ETAPA.Aprobado=1 AND PROYECTO.Id=?"); 
            declaracion.setInt(1, id);
            ResultSet resultado = declaracion.executeQuery();
            Accion accion;
            while(resultado.next()){// maneja la orden
                String datos[] = new String[5];
                datos[0]=Integer.toString(resultado.getInt(1));
                datos[1]=resultado.getString(2);
                datos[2]=Integer.toString(resultado.getInt(3));
                datos[3]=Integer.toString(resultado.getInt(5));
                datos[4]=Integer.toString(resultado.getInt(4));
                modelo.addRow(datos);// agrega
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"No introduciste algo valido");
        }
    }
    // reporte de dinero de un desarrollador
    public void reporteDineroHorasDesarrollador(DefaultTableModel modelo,String usuario){
        PreparedStatement declaracion;
        try {// prepara la orden
            declaracion = conexion.prepareStatement(
"SELECT CASO.Id, CASO.Nombre,ETAPA.Id, ETAPA.Desarrollador_A_Cargo, ETAPA.Tiempo_En_Horas,ETAPA.Total FROM ETAPA INNER JOIN PROYECTO INNER JOIN CASO ON PROYECTO.Id=CASO.Id_Proyecto AND CASO.Id=ETAPA.Id_Caso AND ETAPA.Aprobado=1 AND ETAPA.Desarrollador_A_Cargo=?");
            
            declaracion.setString(1,usuario);
            ResultSet resultado = declaracion.executeQuery();
            while(resultado.next()){// maneja el resultado
                String datos[] = new String[6];
                datos[0]=Integer.toString(resultado.getInt(1));
                datos[1]=resultado.getString(2);
                datos[2]=Integer.toString(resultado.getInt(3));
                datos[3]=resultado.getString(4);
                datos[4]=Integer.toString(resultado.getInt(5));
                datos[5]=Integer.toString(resultado.getInt(6));
                modelo.addRow(datos);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    // reporte de dinero de un tipo
    public void reporteDineroHorasTipo(DefaultTableModel modelo, String tipo){
        PreparedStatement declaracion;
        try {// prepara la orden
            declaracion = conexion.prepareStatement(
"SELECT CASO.Id, CASO.Nombre,ETAPA.Id, CASO.Tipo, ETAPA.Tiempo_En_Horas,ETAPA.Total FROM ETAPA INNER JOIN PROYECTO INNER JOIN CASO ON PROYECTO.Id=CASO.Id_Proyecto AND CASO.Id=ETAPA.Id_Caso AND ETAPA.Aprobado=1 AND ETAPA.Total IS NOT NULL AND ETAPA.Tiempo_En_Horas IS NOT NULL AND CASO.Tipo=?");
            declaracion.setString(1,tipo);// ejecuta
            ResultSet resultado = declaracion.executeQuery();
            while(resultado.next()){// maneja el resultado
                String datos[] = new String[6];
                datos[0]=Integer.toString(resultado.getInt(1));
                datos[1]=resultado.getString(2);// maneja el resultado
                datos[2]=Integer.toString(resultado.getInt(3));
                datos[3]=resultado.getString(4);
                datos[4]=Integer.toString(resultado.getInt(5));
                datos[5]=Integer.toString(resultado.getInt(6));
                modelo.addRow(datos);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    // reporte de desarrollador 
    public void reporteDesarrollador(DefaultTableModel modelo, String usuario){
        PreparedStatement declaracion;
        try {// prepara la orden
            declaracion = conexion.prepareStatement(
"SELECT ETAPA.Id,ETAPA.Nombre, ETAPA.Desarrollador_A_Cargo, ETAPA.Activo,ETAPA.Tiempo_En_Horas,ETAPA.Aprobado,ETAPA.Fecha_Limite,ETAPA.Id_Caso,ETAPA.Total FROM ETAPA INNER JOIN PROYECTO INNER JOIN CASO ON PROYECTO.Id=CASO.Id_Proyecto AND CASO.Id=ETAPA.Id_Caso AND ETAPA.Aprobado=1 AND ETAPA.Desarrollador_A_Cargo=?");
            declaracion.setString(1,usuario);
            ResultSet resultado = declaracion.executeQuery();
            while(resultado.next()){// maneja el resultado
                String datos[] = new String[9];
                datos[0]=Integer.toString(resultado.getInt(1));
                datos[1]=resultado.getString(2);
                datos[2]=resultado.getString(3);// maneja el resultado
                datos[3]=Boolean.toString(resultado.getBoolean(4));
                datos[4]=Integer.toString(resultado.getInt(5));
                datos[5]=Boolean.toString(resultado.getBoolean(6));
                datos[6]=resultado.getDate(7).toString();// maneja el resultado
                datos[7]=Integer.toString(resultado.getInt(8));
                datos[8]=Integer.toString(resultado.getInt(9));
                modelo.addRow(datos);// maneja el resultado
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    // rpoerte de desarrollador mas casos
    public void reporteDesarrolladorMasCasos(DefaultTableModel modelo){
        ArrayList<String> desarrolladores = usuariosEnArreglo(Sesion.getDESARROLLADOR());
        ArrayList<ControlVeces> control = new ArrayList<>();
        ControlVeces controlador;
        PreparedStatement declaracion;
        try {// maneja el resultado
            declaracion = conexion.prepareStatement(
"SELECT DISTINCT CASO.Id, ETAPA.Desarrollador_A_Cargo FROM ETAPA INNER JOIN PROYECTO INNER JOIN CASO ON PROYECTO.Id=CASO.Id_Proyecto AND CASO.Id=ETAPA.Id_Caso AND ETAPA.Desarrollador_A_Cargo IS NOT NULL;");
            ResultSet resultado = declaracion.executeQuery();
            while(resultado.next()){// maneja el resultad o
                    String nombre = resultado.getString(2);
                    int casilla = numeroObjeto(control,nombre);
                    if(casilla>=0){// maneja el resultado
                        control.get(casilla).setVeces(control.get(casilla).getVeces()+1);
                    }else{// maneja el resultado
                        controlador = new ControlVeces(nombre);
                        control.add(controlador);
                    }
                
            }
            // ordena el arreglo 
        ordenamiento(control);
        int numero = control.size()-1;
            // el de hasta arriba significa que es el que mas tiene 
        String nombre = control.get(numero).getNombre();
        reporteDineroHorasDesarrollador(modelo, nombre);
        }catch(SQLException e){
            e.printStackTrace();
        }
               
    }
    // reporte de desarrollador mas dinero
    public void reporteDesarrolladorMasDinero(DefaultTableModel modelo){
        ArrayList<String> desarrolladores = usuariosEnArreglo(Sesion.getDESARROLLADOR());
        ArrayList<ControlVeces> control = new ArrayList<>();
        ControlVeces controlador;
        PreparedStatement declaracion;
        try {
            declaracion = conexion.prepareStatement(// maneja el resultado
"SELECT CASO.Id, ETAPA.Desarrollador_A_Cargo, ETAPA.Total FROM ETAPA INNER JOIN PROYECTO INNER JOIN CASO ON PROYECTO.Id=CASO.Id_Proyecto AND CASO.Id=ETAPA.Id_Caso AND ETAPA.Desarrollador_A_Cargo IS NOT NULL AND ETAPA.Total IS NOT NULL;");
            ResultSet resultado = declaracion.executeQuery();
            while(resultado.next()){// maneja el resultado 
                    String nombre = resultado.getString(2);
                    int casilla = numeroObjeto(control,nombre);
                    int dinero = resultado.getInt(3);
                    if(casilla>=0){// maneja el resultado
                        control.get(casilla).setVeces(control.get(casilla).getVeces()+dinero);
                    }else{// maneja el resultado
                        controlador = new ControlVeces(nombre,dinero);
                        control.add(controlador);
                    }
                
            }
            // ordena 
        ordenamiento(control);
        int numero = control.size()-1;// el de hasta arriba es el que mas elementos tiene 
        String nombre = control.get(numero).getNombre();
        int dinero = control.get(numero).getVeces();
        reporteDineroHorasDesarrollador(modelo, nombre);
        }catch(SQLException e){
            e.printStackTrace();
        }
               
    }
    
    // numero de objetos que tienen 
    public int numeroObjeto(ArrayList<ControlVeces> controlador, String nombre){
        int casilla = -1;
        for(int i=0; i<controlador.size();i++){
            if(controlador.get(i).getNombre().equals(nombre)){
                return i;
            }
        }
        return casilla;
    }
    // quickssort que sirve para orden ar
    public static ArrayList<ControlVeces> ordenamiento(ArrayList<ControlVeces> arregloNumerosDesordenados){
        ControlVeces variableAuxiliar;
        boolean cambios=false;
        // hace un ordenamiento de los muebles mas vendidos y menos 
        while(true){
            cambios=false;
            // si unno es menor que el otro hace cambio 
            for(int i=1;i<arregloNumerosDesordenados.size();i++){
                if(arregloNumerosDesordenados.get(i).getVeces()<arregloNumerosDesordenados.get(i-1).getVeces()){
                    variableAuxiliar=arregloNumerosDesordenados.get(i);
                    arregloNumerosDesordenados.set(i, arregloNumerosDesordenados.get(i-1));
                    arregloNumerosDesordenados.set(i-1, variableAuxiliar);
                    cambios=true;
                }
            }
            if(cambios==false){
                break;
            }
        } 
        return arregloNumerosDesordenados;
    }
    // reporte de casos finalizados
    public void reporteProyectoMasCasosFinalizados(DefaultTableModel modelo){
        ArrayList<ControlVeces> control = new ArrayList<>();
        ControlVeces controlador;
        PreparedStatement declaracion;
        try {// maneja el resultado
            declaracion = conexion.prepareStatement(
"SELECT DISTINCT CASO.Id,CASO.Porcentaje_Avance,CASO.Id_Proyecto FROM ETAPA INNER JOIN PROYECTO INNER JOIN CASO ON PROYECTO.Id=CASO.Id_Proyecto AND CASO.Id=ETAPA.Id_Caso AND ETAPA.Aprobado=1 AND CASO.Porcentaje_Avance=100;");
            ResultSet resultado = declaracion.executeQuery();
            while(resultado.next()){// maneja el resultado 
                    String nombre = Integer.toString(resultado.getInt(3));
                    int casilla = numeroObjeto(control,nombre);
                    if(casilla>=0){// maneja el resultado
                        control.get(casilla).setVeces(control.get(casilla).getVeces()+1);
                    }else{//// maneja el resultado
                        controlador = new ControlVeces(nombre);
                        control.add(controlador);
                    }
                
            }
            // ordena
        ordenamiento(control);
        int numero = control.size()-1;
        String nombre = control.get(numero).getNombre();
        int veces = control.get(numero).getVeces();// el de hasta arriba es el qeu funciona
        mostrarCasoProyectoCasos(modelo, nombre,111);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    // muestra proyecto mas casos 
    public void mostrarCasoProyectoCasos(DefaultTableModel modelo,String idProyecto,int numero){
        try{
        int id=Integer.parseInt(idProyecto);
        PreparedStatement declaracion;
        try {
            if(numero>=100){// dependiendo si es el que mas casos tiene o el que menos escoge uno u otro
            declaracion = conexion.prepareStatement(// maneja el resultado
"SELECT * FROM CASO WHERE Id_Proyecto=? AND Porcentaje_Avance=100"); 
            declaracion.setInt(1, id);
            }else{// maneja el resultado
                declaracion = conexion.prepareStatement(
"SELECT * FROM CASO WHERE Id_Proyecto=? AND Porcentaje_Avance<100 AND Activo=0"); 
            declaracion.setInt(1, id);
            }
            ResultSet resultado = declaracion.executeQuery();
            while(resultado.next()){// aneja el resultado
                String datos[] = new String[7];
                datos[0]=Integer.toString(resultado.getInt(1));// aneja el resultado
                datos[1]=resultado.getString(2);
                datos[2]=Integer.toString(resultado.getInt(3));
                datos[3]=resultado.getDate(4).toString();// aneja el resultado
                datos[4]=Integer.toString(resultado.getInt(5));
                datos[5]=Boolean.toString(resultado.getBoolean(6));// aneja el resultado
                datos[6]=resultado.getString(7);
                modelo.addRow(datos);// agrega
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"No introduciste algo valido");
        }
    }// reporte de mas cancelados 
    public void reporteProyectoMasCasosCancelados(DefaultTableModel modelo){
        ArrayList<ControlVeces> control = new ArrayList<>();
        ControlVeces controlador;
        PreparedStatement declaracion;
        try {// aneja el resultado
            declaracion = conexion.prepareStatement(
"SELECT Id_Proyecto FROM CASO WHERE Activo=0 AND Porcentaje_Avance!=100;");
            ResultSet resultado = declaracion.executeQuery();
            while(resultado.next()){// maneja el resultado 
                    String nombre = Integer.toString(resultado.getInt(1));
                    int casilla = numeroObjeto(control,nombre);
                    if(casilla>=0){// maneja el resultado 
                        control.get(casilla).setVeces(control.get(casilla).getVeces()+1);
                    }else{// maneja el resultado 
                        controlador = new ControlVeces(nombre);
                        control.add(controlador);
                    }
            }
            // orden a
        ordenamiento(control);
        int numero = control.size()-1;
        String nombre = control.get(numero).getNombre();// el de hasta arriba es el que mas tiene 
        mostrarCasoProyectoCasos(modelo, nombre,90);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    // reporte de proyecto mas casos
    public void reporteProyectoEnCasos(DefaultTableModel modelo, String idProyecto){
        try{
        int id=Integer.parseInt(idProyecto);
        PreparedStatement declaracion;
        try {// prepara la accion 
            declaracion = conexion.prepareStatement(
"SELECT * FROM CASO WHERE Id_Proyecto=?");
            declaracion.setInt(1,id);
            ResultSet resultado = declaracion.executeQuery();
            while(resultado.next()){// maneja el resultado 
                String datos[] = new String[7];
                datos[0]=Integer.toString(resultado.getInt(1));
                datos[1]=resultado.getString(2);
                datos[2]=Integer.toString(resultado.getInt(3));
                datos[3]=resultado.getDate(4).toString();// maneja el resultado
                datos[4]=Integer.toString(resultado.getInt(5));
                datos[5]=Boolean.toString(resultado.getBoolean(6));
                datos[6]=resultado.getString(7);// maneja el resultado
                modelo.addRow(datos);// agrega 
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"No introduciste algo valido");
        }
    }
    // reporte desarrollador mas casos 
    public void reporteDesarrolladorEnCasos(DefaultTableModel modelo, String usuario){
        PreparedStatement declaracion;
        try {// maneja el resultado
            declaracion = conexion.prepareStatement(
"SELECT DISTINCT CASO.Id, CASO.Nombre,CASO.Id_Proyecto,CASO.Fecha_Limite,CASO.Porcentaje_Avance,CASO.Activo,CASO.Tipo FROM ETAPA INNER JOIN PROYECTO INNER JOIN CASO ON PROYECTO.Id=CASO.Id_Proyecto AND CASO.Id=ETAPA.Id_Caso AND ETAPA.Desarrollador_A_Cargo=?");
            declaracion.setString(1,usuario);
            ResultSet resultado = declaracion.executeQuery();
            while(resultado.next()){// maneja el resultado
                String datos[] = new String[7];
                datos[0]=Integer.toString(resultado.getInt(1));
                datos[1]=resultado.getString(2);// maneja el resultado
                datos[2]=Integer.toString(resultado.getInt(3));
                datos[3]=resultado.getDate(4).toString();// maneja el resultado
                datos[4]=Integer.toString(resultado.getInt(5));
                datos[5]=Boolean.toString(resultado.getBoolean(6));
                datos[6]=resultado.getString(7);// maneja el resultado
                modelo.addRow(datos);// agrega
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void reporteTipoEnCasos(DefaultTableModel modelo, String tipo){
        PreparedStatement declaracion;
        try {// prepara la orden 
            declaracion = conexion.prepareStatement(
"SELECT * FROM CASO WHERE Tipo=?");
            declaracion.setString(1,tipo);
            ResultSet resultado = declaracion.executeQuery();
            while(resultado.next()){// maneja el resultado
                String datos[] = new String[7];
                datos[0]=Integer.toString(resultado.getInt(1));
                datos[1]=resultado.getString(2);
                datos[2]=Integer.toString(resultado.getInt(3));// maneja el resultado
                datos[3]=resultado.getDate(4).toString();
                datos[4]=Integer.toString(resultado.getInt(5));// maneja el resultado
                datos[5]=Boolean.toString(resultado.getBoolean(6));
                datos[6]=resultado.getString(7);// maneja el resultado
                modelo.addRow(datos);// maneja el resultado
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
