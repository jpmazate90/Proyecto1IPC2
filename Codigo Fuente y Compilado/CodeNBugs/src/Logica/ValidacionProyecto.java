
package Logica;

import Entidades.Proyecto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ValidacionProyecto {
// variable privad a
    private Connection conexion;
    // constructor
    public ValidacionProyecto(Connection conexion) {
        this.conexion=conexion;
    }
    // valida el nombre
    
    public boolean validarNombre(String nombre){
        if(nombre==null || nombre.equals("")){
            return false;
        }else{
            return true;
        }
    }
    // verifica si los campos estan bien 
    public boolean verificarCampos(String nombre, Date fecha,String administradorProyecto){
        if(nombre==null||nombre.equals("")){
            JOptionPane.showMessageDialog(null,"No se puede crear el nombre esta en blanco");
            return false;
        }else{// verifica si los campos estan bien 
            if(fecha==null){
                JOptionPane.showMessageDialog(null,"La fecha no es correcta");
                return false;// verifica si los campos estan bien 
            }else{// verifica si los campos estan bien 
                if(administradorProyecto==null || administradorProyecto.equals("")){
                    JOptionPane.showMessageDialog(null,"No se puede crear no se escogio administrador");
                    return false;
                }else{// verifica si los campos estan bien 
                    return true;
                }
            }
        }
    }
    // crea un proyecto 
    public boolean crearProyecto(String nombre, Date fecha, String administradorProyecto){
        PreparedStatement declaracion;
        long tiempo= fecha.getTime();
        java.sql.Date fechaSql= new java.sql.Date(tiempo);
        JOptionPane.showMessageDialog(null, fechaSql);
        Proyecto proyectoCreado = new Proyecto(// crea el objeto proyeccto 
                nombre, fechaSql, true, administradorProyecto);
        try {// inserta valores del proyecto 
            declaracion = conexion.prepareStatement("INSERT INTO PROYECTO(Nombre,Fecha_Limite,Activo,Administrador_Proyecto) VALUES (?,?,?,?);");
            declaracion.setString(1, proyectoCreado.getNombre());// inserta valores del proyecto 
            declaracion.setDate(2, proyectoCreado.getFechaLimite());// inserta valores del proyecto 
            declaracion.setBoolean(3, proyectoCreado.isActivo());// inserta valores del proyecto 
            declaracion.setString(4, proyectoCreado.getAdministradorProyecto());// inserta valores del proyecto 
            declaracion.executeUpdate();// ejecuta 
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    // agrega datos a lista 
    public void agregarDatosLista(DefaultListModel modelo){
        
        try {
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT * FROM USUARIO WHERE Tipo_Usuario=?");
            declaracion.setString(1,Sesion.getAdministradorProyecto());
            ResultSet resultado = declaracion.executeQuery();
            
            while (resultado.next()) {// maneja el resultado 
                modelo.addElement(resultado.getString("Usuario"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
           
    }// agrega datos al proeycto 
    public void agregarDatosTablaProyectos(DefaultTableModel modelo){
        try {
            conexion.setAutoCommit(false);
            PreparedStatement declaracion;// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT * FROM PROYECTO");
            ResultSet resultado = declaracion.executeQuery();
            Proyecto proyecto;
            while (resultado.next()) {// ejecuta el reslutado 
                proyecto = new Proyecto(resultado.getInt("Id"), resultado.getString("Nombre"), resultado.getBoolean("Activo"), resultado.getString("Administrador_Proyecto"));
                String datos[] = new String[4];// maneja datos 
                datos[0]=Integer.toString(proyecto.getId());
                datos[1]=proyecto.getNombre();// maneja datos 
                datos[2]=Boolean.toString(proyecto.isActivo());
                datos[3]=proyecto.getAdministradorProyecto();// maneja datos 
                modelo.addRow(datos);
            }
            conexion.commit();// pone el commit true 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {// autocommit
                conexion.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ValidacionProyecto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    // borral os datos de un modelo que le pasen 
    public void borrarDatos(DefaultTableModel modelo){
        for(int i=0;i<modelo.getRowCount();i++){
            modelo.removeRow(i);
            if(modelo.getRowCount()>0){
                borrarDatos(modelo);
            }
        }
    }
    // edita un proyecto activo o lo desactiva
    public void editarActivoProyecto(String idProyecto, boolean activar){
        int id= Integer.parseInt(idProyecto);
        try {// commit
            conexion.setAutoCommit(false);
            PreparedStatement declaracion;// ejecuta una orden de update
            declaracion = conexion.prepareStatement("UPDATE PROYECTO SET Activo=? WHERE Id=?");
            declaracion.setBoolean(1, activar);
            declaracion.setInt(2, id);// añade vaores
            declaracion.executeUpdate();
            conexion.commit();
            JOptionPane.showMessageDialog(null,"Se actualizaron los datos del proyecto");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                conexion.setAutoCommit(true);//autocommit
            } catch (SQLException ex) {
                Logger.getLogger(ValidacionProyecto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    // verifica el proyecto etap a
    public boolean verificarProyectoEtapa(String idEtapa){
        int id=Integer.parseInt(idEtapa);
        try {
            conexion.setAutoCommit(false);
            PreparedStatement declaracion;// seleccina la orden
            declaracion = conexion.prepareStatement("SELECT PROYECTO.Activo FROM PROYECTO INNER JOIN CASO INNER JOIN ETAPA ON PROYECTO.Id=CASO.Id_Proyecto AND ETAPA.Id_Caso=CASO.Id AND ETAPA.Id=?");
            declaracion.setInt(1,id);
            ResultSet resultado = declaracion.executeQuery();// la ejecuta
            resultado.next();
            boolean activo= resultado.getBoolean("Activo");
            conexion.commit();// commit true
            return activo;//devuelve
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }finally{
            try {//autocommit
                conexion.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }// verifica un caso de proyecto
    public boolean verificarProyectoCaso(String idProyecto){
        int id = Integer.parseInt(idProyecto);
        try {
            conexion.setAutoCommit(false);
            PreparedStatement declaracion;// prepara la orden
            declaracion = conexion.prepareStatement("SELECT * FROM PROYECTO WHERE Id=?");
            declaracion.setInt(1, id);
            ResultSet resultado = declaracion.executeQuery();
            resultado.next();// ejecuta el resutado
            boolean activo= resultado.getBoolean("Activo");
            conexion.commit();//commit
            return activo;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }finally{
            try {//autocommit
                conexion.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    // pregunta la fecha
    public Date preguntarFecha(){
        String fecha=null;
        boolean sePuede=true;
        Date fecha1 = null;
        do{
            sePuede=true;// le pide al usuario la fecha en un formato especfico
            fecha = JOptionPane.showInputDialog(null, "Escribe la fecha nueva (Formato: dd/MM/yyyy)");
            if(fecha==null || fecha.equals("")){
                JOptionPane.showMessageDialog(null,"No introduciste nada por favor introduce la fecha");
                sePuede=false;
            }else{// si no esta bien vuelve 
                try{
                String numeros[]=fecha.split("/");
                fecha1 = new Date(Integer.parseInt(numeros[2])-1900,Integer.parseInt(numeros[1])-1,Integer.parseInt(numeros[0]));
                }catch(PatternSyntaxException e){//maneja el error
                    JOptionPane.showMessageDialog(null,"No es una fecha valida");
                    sePuede=false;//maneja el error
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"No introduciste numeros");
                    sePuede=false;
                }catch(Exception ef){//maneja el error
                    JOptionPane.showMessageDialog(null,"No son datos validos");
                    sePuede=false;
                }
            }//maneja el error
        }while(fecha==null || fecha.equals("") || sePuede==false);
        return fecha1;
    }
    //cambia la fecha 
    public void cambiarFecha(Date fecha, String idCaso){
        int id= Integer.parseInt(idCaso);
        long tiempo= fecha.getTime();
        java.sql.Date fechaSql= new java.sql.Date(tiempo);
        try {// commit false
            conexion.setAutoCommit(false);
            PreparedStatement declaracion;//prepara la orden
            declaracion = conexion.prepareStatement("UPDATE CASO SET Fecha_Limite=? WHERE Id=?");
            declaracion.setDate(1, fechaSql);
            declaracion.setInt(2, id);
            declaracion.executeUpdate();
            conexion.commit();// ejecuta y commit
            JOptionPane.showMessageDialog(null,"Se actualizo la fecha limite del caso");
        } catch (SQLException ex) {//actualiza
            ex.printStackTrace();
        }finally{
            try {//autocommit
                conexion.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ValidacionProyecto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}


