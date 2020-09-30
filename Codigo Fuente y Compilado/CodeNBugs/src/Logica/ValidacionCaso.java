
package Logica;

import Entidades.Accion;
import Entidades.Caso;
import Entidades.TipoCaso;
import Entidades.Proyecto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ValidacionCaso {
    // unica variable de la clase
    private Connection conexion;
//constructor de la clase
    public ValidacionCaso(Connection conexion) {
        this.conexion = conexion;
    }
    // agrega los proyectos
    public void agregarProyectos(DefaultListModel modelo, String usuario){
            
        try {// prepara la orden
            PreparedStatement declaracion;
            declaracion = conexion.prepareStatement("SELECT * FROM PROYECTO WHERE Administrador_Proyecto=?");
            declaracion.setString(1,usuario);
            ResultSet resultado = declaracion.executeQuery();
    // ejectua la orden         
            while (resultado.next()) {
                modelo.addElement(resultado.getString("Nombre"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
           
    }
    // verifica si lo que le pasaron no es nullo o vacio 
    public boolean verificarInformacionCaso(String nombre, String etapas){
        if( nombre== null || nombre.equals("")){
            return false;
        }else{// verifica si lo que le pasaron no es nullo o vacio
                if((etapas==null) || etapas.equals("")){
                    return false;
                }else{
                        try {// verifica si lo que le pasaron no es nullo o vacio
                            int entero = Integer.parseInt(etapas);
                        } catch (NumberFormatException e) {
                            return false;
                        }  // verifica si lo que le pasaron no es nullo o vacio
                }
            return true;   
        }     
    }
 
    // crea un tipo de caso 
    public void crearTipoCaso(String nombre, int etapas){
        PreparedStatement declaracion;
        try {
            TipoCaso caso = new TipoCaso(nombre, etapas);// inserta un tipo de caso nuevo
            declaracion = conexion.prepareStatement("INSERT INTO TIPO_CASO(Nombre,Etapas) VALUES (?,?);");
            declaracion.setString(1, caso.getNombre());
            declaracion.setInt(2, caso.getEtapas());// le asigna valores
            declaracion.executeUpdate();
            JOptionPane.showMessageDialog(null,"Se creo el tipo de caso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"El nombre ya existe prueba con otro");
        }
    }
    // asigna valores a la tabla casos 
    public void asignarDatosTablaCasos(DefaultTableModel modelo){
        String datos[];
        try {// crea un objeto orden 
            PreparedStatement declaracion;
            declaracion = conexion.prepareStatement("SELECT * FROM TIPO_CASO");
            ResultSet resultado = declaracion.executeQuery();
            TipoCaso caso;
            datos = new String[2];// maneja el resultado
            while (resultado.next()) {// ejecuta
                datos[0]= resultado.getString("Nombre");
                datos[1]= Integer.toString(resultado.getInt("Etapas"));
                caso = new TipoCaso(datos[0], Integer.parseInt(datos[1]));
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // agrega proyectos a la tabla 
    public void agregarProyectosTabla(DefaultTableModel modelo, String usuario){
            
        try {// prepara la orden
            PreparedStatement declaracion;
            declaracion = conexion.prepareStatement("SELECT * FROM PROYECTO WHERE Administrador_Proyecto=?");
            declaracion.setString(1,usuario);
            ResultSet resultado = declaracion.executeQuery();
            // maneja el resultado 
            while (resultado.next()) {
                String datos[] = new String [5];
                datos[0]= Integer.toString(resultado.getInt("Id"));// maneja el resultado 
                datos[1]= resultado.getString("Nombre");
                datos[2]= Boolean.toString(resultado.getBoolean("Activo"));
                datos[3]= resultado.getString("Administrador_Proyecto");// maneja el resultado 
                Proyecto proyecto = new Proyecto(datos[0], resultado.getDate("Fecha_Limite"), resultado.getBoolean("Activo"),datos[4]);
                modelo.addRow(datos);//agrega 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // verifica si los datos no son nulos
    public boolean veriricarDatosCreacionCaso(String nombre, Date fechaLimite,String id, String tipo){
        if( nombre== null || nombre.equals("")){
            return false;
        }else{ // verifica si los datos no son nulos
                if((fechaLimite==null)){
                    return false;
                }else{ // verifica si los datos no son nulos
                    if(id==null || nombre.equals("")){
                        return false;
                    }else{
                        if(tipo==null || tipo.equals("")){
                            return false;
                        }else{ // verifica si los datos no son nulos
                            return true;
                        }
                        
                    }
                          
                }
        }
    }
    
        // crea un caso 
    public void crearCasos(String nombre, Date fechaLimite , String id, String tipo){
        PreparedStatement declaracion;
        int idProyecto = Integer.parseInt(id);
        long tiempo= fechaLimite.getTime();
        java.sql.Date fechaSql= new java.sql.Date(tiempo);// convierte a fecha en mysql
        Caso caso = new Caso(nombre,fechaSql,idProyecto,0,true,tipo);
        try {// prepara la orden 
            declaracion = conexion.prepareStatement("INSERT INTO CASO(Nombre,Id_Proyecto,Fecha_Limite,Porcentaje_Avance,Activo,Tipo) VALUES (?,?,?,?,?,?);");
            declaracion.setString(1, caso.getNombre());
            declaracion.setInt(2, caso.getIdProyecto());// crea un objeto caso
            declaracion.setDate(3, caso.getFechaLimite());
            declaracion.setInt(4, caso.getPorcentajeAvance());
            declaracion.setBoolean(5, caso.isActivo());// crea un objeto caso
            declaracion.setString(6, caso.getTipo());
            declaracion.executeUpdate();//ejectua
            JOptionPane.showMessageDialog(null,"Se creo el caso correctamente");
        } catch (SQLException ex) {
            Logger.getLogger(ValidacionCaso.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    // asigna valroes a los desarrolladores 
    public void asignarDatosListaDesarrolladores(DefaultListModel modelo){
        PreparedStatement declaracion;
        try {//prepara la orden 
            declaracion = conexion.prepareStatement("SELECT * FROM USUARIO WHERE Tipo_Usuario=?");
            declaracion.setString(1, Sesion.getDesarrollador());
            ResultSet resultado = declaracion.executeQuery();
            while (resultado.next()) {// maneja el resultado 
                modelo.addElement(resultado.getString("Usuario"));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // asigna datos a los casos 
    public void asignarDatosCasos(DefaultTableModel modelo, String usuario){
        PreparedStatement declaracion;
        try {// prepara la orden 
            declaracion = conexion.prepareStatement(
                    "SELECT CASO.Id,CASO.Nombre,CASO.Id_Proyecto,CASO.Fecha_Limite,CASO.Porcentaje_Avance,CASO.Activo,CASO.Tipo  FROM CASO INNER JOIN PROYECTO ON CASO.Id_Proyecto=PROYECTO.Id AND PROYECTO.Administrador_Proyecto=?;");
            declaracion.setString(1,usuario);
            ResultSet resultado = declaracion.executeQuery();
            while(resultado.next()){// maneja el resultado
                String datos[] = new String[7];
                datos[0] = Integer.toString(resultado.getInt("Id"));
                datos[1] = resultado.getString("Nombre");// maneja el resultado
                datos[2] = Integer.toString(resultado.getInt("Id_Proyecto"));
                datos[3] = resultado.getDate("Fecha_Limite").toString();
                datos[4] = Integer.toString(resultado.getInt("Porcentaje_Avance"));
                datos[5] = Boolean.toString(resultado.getBoolean("Activo"));// maneja el resultado
                datos[6] = resultado.getString("Tipo");
                modelo.addRow(datos);// maneja el resultado
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }// maneja el id del caso 
    public int idCaso(){
        PreparedStatement declaracion;
        int numeroId=0;
        try {// prepara la orden 
            declaracion = conexion.prepareStatement(
                    "SELECT Id  FROM CASO ORDER BY Id DESC;");
          
            ResultSet resultado = declaracion.executeQuery();
            resultado.next();// agarra el id 
            numeroId = resultado.getInt("Id");
            return numeroId;
        } catch (SQLException e) {
            return 0;
        }
    }
    // cancela un caso 
    public void cancelarCaso(String idCaso, boolean cancelar){
        int id= Integer.parseInt(idCaso);
        try {
            conexion.setAutoCommit(false);
            PreparedStatement declaracion;// prepara una orden 
            declaracion = conexion.prepareStatement("UPDATE CASO SET Activo=? WHERE Id=?");
            declaracion.setBoolean(1, cancelar);
            declaracion.setInt(2, id);// maneja 
            declaracion.executeUpdate();
            conexion.commit();
            JOptionPane.showMessageDialog(null,"Se actualizaron los datos del caso");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                conexion.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    
    }// habre un input dialog
    public String mandarComentario(){
        String comentario = null;
        do{// pregunta que es lo que quiere mandar 
             comentario = JOptionPane.showInputDialog(null,"Escribe el comentario que le quieres mandar");
            if(comentario==null || comentario.equals("")){// sihubo un error lo meustra otra vez
                JOptionPane.showMessageDialog(null,"NO introduciste un comentario valido, por favor introduce uno");
            }
        }while(comentario==null || comentario.equals(""));
        // regresa el comentario
        return comentario;
    }// agrega accions de cancelacion 
    public void agregarAccionCancelacionCaso(String idProyecto,String idCaso,String usuario,String tipoAccion1){
        String faseProyecto="Caso";
        String tipoAccion=tipoAccion1;
        String comentario = mandarComentario();
        boolean aprobado=false;
        boolean revisado=true;
        PreparedStatement declaracion;
        Accion accion = new Accion(// crea el objeto accion 
                usuario,Integer.parseInt(idProyecto),Integer.parseInt(idCaso),faseProyecto,tipoAccion, comentario, aprobado);
        try {// prepara la orden 
            declaracion = conexion.prepareStatement("INSERT INTO ACCION(Nombre_Usuario,Id_Proyecto,Id_Caso,Fase_Proyecto,Tipo_Accion,Comentario,Aprobado,Revisada) VALUES(?,?,?,?,?,?,?,?);");
            declaracion.setString(1, accion.getNombreUsuario());
            declaracion.setInt(2, accion.getIdProyecto());// agrega valroes
            declaracion.setInt(3, accion.getIdCaso());// agrega valroes
            declaracion.setString(4, accion.getFaseProyecto());
            declaracion.setString(5, accion.getTipoAccion());
            declaracion.setString(6, accion.getComentario());// agrega valroes
            declaracion.setBoolean(7, aprobado);
            declaracion.setBoolean(8,revisado);// agrega valroes
            declaracion.executeUpdate();// agrega valroes
            JOptionPane.showMessageDialog(null,"Se asigno la accion");
        }catch(SQLException e){
            
        }
    }
    //verifica el caso 
    public boolean verificarCaso(String idCaso){
        int id = Integer.parseInt(idCaso);
        try {
            conexion.setAutoCommit(false);
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT * FROM CASO WHERE Id=?");
            declaracion.setInt(1, id);
            ResultSet resultado = declaracion.executeQuery();
            resultado.next();// ejecuta el resulado 
            boolean activo= resultado.getBoolean("Activo");
            conexion.commit();
            return activo;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }finally{// hace el commit 
            try {
                conexion.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    // asigna valores a una accion de un desarrollador 
    public void asignarValoresAccionDesarrollador(DefaultTableModel modelo,String usuario){
        
        
        PreparedStatement declaracion;
        try {
            declaracion = conexion.prepareStatement(
"SELECT ACCION.Id,ACCION.Nombre_Usuario,ACCION.Id_Proyecto,ACCION.Id_Caso,ACCION.Id_Etapa,ACCION.Fase_Proyecto,ACCION.Tipo_Accion,ACCION.Comentario,ACCION.Aprobado ,ACCION.Revisada FROM ACCION INNER JOIN PROYECTO INNER JOIN ETAPA ON ACCION.Id_Proyecto=PROYECTO.Id AND ETAPA.Desarrollador_A_Cargo=? WHERE ETAPA.Id=ACCION.Id_Etapa AND ACCION.Aprobado=0 AND ACCION.Revisada=1 AND ETAPA.Aprobado IS NULL AND PROYECTO.Activo=1 AND ACCION.Tipo_Accion=?;"); 
            declaracion.setString(1, usuario);
            declaracion.setString(2,Sesion.getCancelacion());
            ResultSet resultado = declaracion.executeQuery();
            Accion accion;// prepara laoden     
            while(resultado.next()){// ejectua el resultado 
                accion = new Accion(resultado.getString("Nombre_Usuario"),resultado.getInt("Id_Proyecto"),resultado.getInt("Id_Caso"),resultado.getInt("Id_Etapa"),resultado.getString("Fase_Proyecto"),resultado.getString("Tipo_Accion"), resultado.getString("Comentario"),resultado.getBoolean("Aprobado"));
                String datos[] = new String[10];// maneja el resultado 
                datos[0]=Integer.toString(resultado.getInt("Id"));
                datos[1]=resultado.getString("Nombre_Usuario");// maneja el resultado
                datos[2]=Integer.toString(resultado.getInt("Id_Proyecto"));
                datos[3]=Integer.toString(resultado.getInt("Id_Caso"));
                datos[4]=Integer.toString(resultado.getInt("Id_Etapa"));
                datos[5]=resultado.getString("Fase_Proyecto");// maneja el resultado
                datos[6]=resultado.getString("Tipo_Accion");
                datos[7]= resultado.getString("Comentario");
                datos[8]=Boolean.toString(resultado.getBoolean("Aprobado"));
                datos[9]=Boolean.toString(resultado.getBoolean("Revisada"));// maneja el resultado
                modelo.addRow(datos);// agrega 
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }// agrega casos asignado s
    public void agregarCasosAsignados(DefaultTableModel modelo, String usuario){
            
        try {// prepara el resultado 
            PreparedStatement declaracion;
            declaracion = conexion.prepareStatement("SELECT DISTINCT CASO.Id, CASO.Nombre FROM CASO INNER JOIN ETAPA ON CASO.Id=ETAPA.Id_Caso AND ETAPA.Desarrollador_A_Cargo=?");
            declaracion.setString(1,usuario);// maneja el select 
            ResultSet resultado = declaracion.executeQuery();
            
            while (resultado.next()) {//maneja el resultado 
                String datos[] = new String [5];
                datos[0]= Integer.toString(resultado.getInt("Id"));
                datos[1]= resultado.getString("Nombre");
                modelo.addRow(datos);// agrega 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}

