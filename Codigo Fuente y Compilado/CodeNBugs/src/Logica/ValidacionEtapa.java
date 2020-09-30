
package Logica;

import Entidades.Accion;
import Entidades.Etapa;
import InterfazGrafica.AsignacionEtapa;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ValidacionEtapa {
    // variable privada 
    private Connection conexion;
// constructor de la clase
    public ValidacionEtapa(Connection conexion) {
        this.conexion = conexion;
    }
    // maneja los datos 
    public boolean validarDatosEtapaInicial(int id, String nombre){
        if(id==-1 || id==0){
            return false;
        }else{// maneja los datos 
            if(nombre==null || nombre.equals("")){
                return false;
            }else{// maneja los datos 
                return true;
            }
        }// maneja los datos 
    }
    // maneja los datos 
    public boolean validarNombre(String nombre){
        if(nombre==null || nombre.equals("")){
            return false;
        }else{// maneja los datos 
            return true;
        }
    }
    // maneja los datos 
    public boolean validarDatosFecha(Date fecha){
        if(fecha==null){
            return false;
        }else{// maneja los datos 
            return true;
        }
    }
    // maneja los datos 
    public boolean validarFechaDesarrollador(Date fecha, String desarrollador){
        if(desarrollador==null || desarrollador.equals("")){
            return false;
        }else{// maneja los datos 
            if(fecha==null){
                return false;
            }else{
                return true;
            }
        }
    }
    // crea una etapa 
    public void crearEtapa(String nombreEtapa, int idCaso){
        PreparedStatement declaracion;
        try {// crea el objeto 
            Etapa etapaCrear = new Etapa(nombreEtapa,idCaso);// prepara la orden 
            declaracion = conexion.prepareStatement("INSERT INTO ETAPA(Nombre,Id_Caso) VALUES (?,?);");
            declaracion.setString(1,etapaCrear.getNombre());
            declaracion.setInt(2, etapaCrear.getIdCaso());
            declaracion.executeUpdate();// ejecuta 
            JOptionPane.showMessageDialog(null, "Se creo la etapa correctamente");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // asigna datos a la etapa 
    public void asignarDatosEtapa(String idCaso, String idEtapa, Date fechaLimite, String desarrollador){
        int idCasoEntero = Integer.parseInt(idCaso);
        int idEtapaEntero = Integer.parseInt(idEtapa);
        long tiempo= fechaLimite.getTime();// crea una fecha de mysql 
        java.sql.Date fechaSql= new java.sql.Date(tiempo);
        PreparedStatement declaracion;
        try {// prepara la orden 
            declaracion = conexion.prepareStatement("UPDATE ETAPA SET Desarrollador_A_Cargo=?,Fecha_Limite=?,Activo=? WHERE Id=?");
            declaracion.setString(1, desarrollador);// pepara datos 
            declaracion.setDate(2,fechaSql);// pepara datos 
            declaracion.setBoolean(3, true);// pepara datos 
            declaracion.setInt(4, idEtapaEntero);// pepara datos 
            declaracion.executeUpdate();// pepara datos 
            JOptionPane.showMessageDialog(null,"Se asignaron los datos correctamente");// pepara datos 
            
        } catch (HeadlessException | SQLException e) {
            e.printStackTrace();
        }
        
    }
    // consigue el id de una etapa 
    public int idEtapa(){
        PreparedStatement declaracion;
        int numeroId;
        try {// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT Id FROM ETAPA ORDER BY Id DESC;");
            ResultSet resultado = declaracion.executeQuery();
            resultado.next();// ejecuta el resultado
            numeroId = resultado.getInt("Id");
            return numeroId;// retorna 
        } catch (SQLException e) {
            return 0;
        }
    }
    // metodo de nombre etapa 
    public String nombreEtapa(){
        PreparedStatement declaracion;
        String nombre=null;
        try {// prepara la orden 
            declaracion = conexion.prepareStatement(
                    "SELECT Nombre FROM ETAPA ORDER BY Id DESC;");
            ResultSet resultado = declaracion.executeQuery();
            resultado.next();// maneja el resultado 
            nombre = resultado.getString("Nombre");
            return nombre;// retorna 
        } catch (SQLException e) {
            return null;
        }
    }
  // asigna desarrolladores a las etapas 
    public void asignarEtapasDesarrolladores(DefaultTableModel modelo,String desarrollador){
        PreparedStatement declaracion;
        try {// prepara la orden 
            declaracion = conexion.prepareStatement(
                    "SELECT * FROM ETAPA WHERE Desarrollador_A_Cargo=? AND (Aprobado IS NULL);");
            declaracion.setString(1, desarrollador);
            ResultSet resultado = declaracion.executeQuery();
            while(resultado.next()){// maneja el resultado 
                String datos[] = new String[9];// maneja el resultado 
                datos[0] = Integer.toString(resultado.getInt("Id"));
                datos[1] = resultado.getString("Nombre");// maneja el resultado 
                datos[2] = resultado.getString("Desarrollador_A_Cargo");
                datos[3] = Boolean.toString(resultado.getBoolean("Activo"));
                datos[4] = resultado.getDate("Fecha_Limite").toString();
                datos[5] = Integer.toString(resultado.getInt("Id_Caso"));
                modelo.addRow(datos);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }  
    }// finalizacio caso 
    public void finalizacionCaso(String idEtapa, String idCaso, String desarrollador,String idProyecto,String tipoAccion1){
        String faseProyecto="Etapa";
        String tipoAccion=tipoAccion1;
        String comentario = mandarComentario();
        boolean aprobado=true;
        PreparedStatement declaracion;
        Accion accion = new Accion(
                desarrollador,Integer.parseInt(idProyecto),Integer.parseInt(idCaso), Integer.parseInt(idEtapa),faseProyecto,tipoAccion, comentario, aprobado);
        try {// prepara la orden 
            declaracion = conexion.prepareStatement("INSERT INTO ACCION(Nombre_Usuario,Id_Proyecto,Id_Caso,Id_Etapa,Fase_Proyecto,Tipo_Accion,Comentario) VALUES(?,?,?,?,?,?,?);");
            declaracion.setString(1, accion.getNombreUsuario());
            declaracion.setInt(2, accion.getIdProyecto());// añade valores
            declaracion.setInt(3, accion.getIdCaso());
            declaracion.setInt(4, accion.getIdEtapa());// añade valores
            declaracion.setString(5, accion.getFaseProyecto());
            declaracion.setString(6, accion.getTipoAccion());// añade valores
            declaracion.setString(7, accion.getComentario());
            declaracion.executeUpdate();// añade valores
            JOptionPane.showMessageDialog(null,"Se asigno la accion");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    // registra las horas 
    public void registrarHoras(String idEtapa){
        PreparedStatement declaracion;
        int id = Integer.parseInt(idEtapa);
        
        try {//dddd
            String horas = JOptionPane.showInputDialog("Introduce el tiempo que te Tardaste");
            int tiempoHoras=Integer.parseInt(horas);// prepara la orden 
            declaracion = conexion.prepareStatement("UPDATE ETAPA SET Tiempo_En_Horas=?,Aprobado=0 WHERE Id=?");
            declaracion.setInt(1, tiempoHoras);
            declaracion.setInt(2,id);// añade valores
            declaracion.executeUpdate();
            JOptionPane.showMessageDialog(null,"Se actualizo correctamente las horas");
        }catch(HeadlessException | SQLException e){
            e.printStackTrace();
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"No Introduciste un numero");
            registrarHoras(idEtapa);
        }
            
    }
        
    // id del proyecto 
    public int idProyectoDesdeEtapa(String idEtapa){
        
        int id = Integer.parseInt(idEtapa);
        PreparedStatement declaracion;
        try {// prepara la oren
            declaracion = conexion.prepareStatement(
"SELECT CASO.Id_Proyecto FROM CASO INNER JOIN ETAPA ON CASO.Id=ETAPA.Id_Caso AND ETAPA.Id=?;"); 
            declaracion.setInt(1, id);// añade valores
            ResultSet resultado = declaracion.executeQuery();
            resultado.next();
            int idProyecto = resultado.getInt("Id_Proyecto");
            return idProyecto;
        }catch(SQLException e){
            return 0;
        }
    }
    // agrega valores a los datos de las etapas 
    public void agregarDatosEtapasFinalizadas(DefaultTableModel modelo, String usuario){
        
        PreparedStatement declaracion;
        try {// prepara la orden 
            declaracion = conexion.prepareStatement(
"SELECT ACCION.Id,ACCION.Nombre_Usuario,ACCION.Id_Proyecto,ACCION.Id_Caso,ACCION.Id_Etapa,ACCION.Fase_Proyecto,ACCION.Tipo_Accion,ACCION.Comentario,ACCION.Aprobado ,ACCION.Revisada FROM ACCION INNER JOIN PROYECTO INNER JOIN ETAPA ON ACCION.Id_Proyecto=PROYECTO.Id AND PROYECTO.Administrador_Proyecto=? WHERE ETAPA.Id=ACCION.Id_Etapa AND ACCION.Aprobado IS NULL AND ACCION.Revisada IS NULL AND ETAPA.Aprobado=0 AND PROYECTO.Activo=1 AND ACCION.Tipo_Accion=?;"); 
            declaracion.setString(1, usuario);
            declaracion.setString(2,Sesion.getFinalizacion());// añade valores
            ResultSet resultado = declaracion.executeQuery();
            Accion accion;
            while(resultado.next()){// maneja el resultado 
                accion = new Accion(resultado.getString("Nombre_Usuario"),resultado.getInt("Id_Proyecto"),resultado.getInt("Id_Caso"),resultado.getInt("Id_Etapa"),resultado.getString("Fase_Proyecto"),resultado.getString("Tipo_Accion"), resultado.getString("Comentario"),resultado.getBoolean("Aprobado"));
                String datos[] = new String[10];// maneja el resultado 
                datos[0]=Integer.toString(resultado.getInt("Id"));// maneja el resultado 
                datos[1]=resultado.getString("Nombre_Usuario");// maneja el resultado 
                datos[2]=Integer.toString(resultado.getInt("Id_Proyecto"));
                datos[3]=Integer.toString(resultado.getInt("Id_Caso"));
                datos[4]=Integer.toString(resultado.getInt("Id_Etapa"));// maneja el resultado 
                datos[5]=resultado.getString("Fase_Proyecto");
                datos[6]=resultado.getString("Tipo_Accion");// maneja el resultado 
                datos[7]= resultado.getString("Comentario");
                datos[8]=Boolean.toString(resultado.getBoolean("Aprobado"));
                datos[9]=Boolean.toString(resultado.getBoolean("Revisada"));// maneja el resultado 
                modelo.addRow(datos);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    // agrega el total de tiempo
    public void agregarTotalTiempo(String idEtapa,String desarrollador){
        int id= Integer.parseInt(idEtapa);
         PreparedStatement declaracion;
        try{// prepara la orden 
            declaracion = conexion.prepareStatement("SELECT USUARIO.Dinero_Hora,ETAPA.Tiempo_En_Horas FROM ETAPA INNER JOIN USUARIO ON USUARIO.Usuario=? AND ETAPA.Id=?");
            declaracion.setString(1,desarrollador);
            declaracion.setInt(2, id);// maneja el resultado 
            ResultSet resultado = declaracion.executeQuery();
            resultado.next();// maneja el resultado 
            int dinero = resultado.getInt("Dinero_Hora");
            int horas = resultado.getInt("Tiempo_En_Horas");// maneja el resultado 
            agregarTotal(horas, dinero, idEtapa);
        }catch(Exception e){
            
        }
        
    }// agrega el total a los valores 
    public void agregarTotal(int horas, int dinero, String idEtapa){
        int total = horas * dinero;
        PreparedStatement declaracion;
        try {// prepara el statemtn
            declaracion=conexion.prepareStatement("UPDATE ETAPA SET Total=?,Aprobado=1 WHERE Id=?");
            declaracion.setInt(1, total);
            declaracion.setString(2, idEtapa);// maneja el resultado 
            declaracion.executeUpdate();
            JOptionPane.showMessageDialog(null,"Se agrego el total al caso");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //actualiza el estado de la accion 
    public void actualizarEstadoAccion(String idAccion, boolean aprobado){
        PreparedStatement declaracion;
        int id = Integer.parseInt(idAccion);
        try {// prepara la accion 
            declaracion=conexion.prepareStatement("UPDATE ACCION SET Aprobado=? WHERE Id=?");
            declaracion.setBoolean(1, aprobado);
            declaracion.setInt(2, id);// maneja el resultado 
            declaracion.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // siguiente etapa 
    public void siguienteEtapa(String idCasoString,Frame frame){
        int id=Integer.parseInt(idCasoString);
        PreparedStatement declaracion;
        boolean ultimaEtapa=true;
        try {// prepara la orden 
            declaracion=conexion.prepareStatement("SELECT * FROM ETAPA WHERE Id_Caso=? ORDER BY Id ASC;");
            declaracion.setInt(1, id);
            ResultSet resultado = declaracion.executeQuery();
            while(resultado.next()){// ejecuta elresultado 
                String desarrollador = resultado.getString("Desarrollador_A_Cargo");
                int idEtapa = resultado.getInt("Id");
                String idEtapaString=Integer.toString(idEtapa);
                if(desarrollador==null){
                    ultimaEtapa=false;// crea un objeto de asignar etapa 
                    asignarEtapaNueva(frame, idCasoString, idEtapaString);
                    break;
                }
            }// si es true manda que se pudo 
            if(ultimaEtapa==true){
                JOptionPane.showMessageDialog(null, "El caso ha sido finalizado");
            }
            
        } catch (Exception e) {
        }
        
    }
    // asigna la etapa y crea el objeto 
    public void asignarEtapaNueva(Frame frame, String idCaso,String idEtapa){
        AsignacionEtapa etapa = new AsignacionEtapa(frame, true, conexion, idCaso, idEtapa);
        etapa.setVisible(true);
    }
    // actualiza el porcentaje de avanc e
    public void actualizarPorcentajeAvance(String idCasoString){
        PreparedStatement declaracion;
        int id = Integer.parseInt(idCasoString);
        int contador=0;
        int contadorFinalizados=0;
        try {// prepara la orden 
            declaracion=conexion.prepareStatement("SELECT * FROM ETAPA WHERE Id_Caso=? ORDER BY Id ASC;");
            declaracion.setInt(1, id);
            ResultSet resultado = declaracion.executeQuery();
            while(resultado.next()){// maneja el resultado 
                boolean activo = resultado.getBoolean("Activo");
                if(activo==true){// aumenta el contador de finalizados
                    contadorFinalizados++;
                }// aumenta el contador normal 
                contador++;
            }
            // mria cuanto es el porcentaje 
            int porcentaje=(contadorFinalizados*100)/contador;
            introducirPorcentaje(porcentaje, idCasoString);
            
        } catch (Exception e) {
            
        }
    }
    // introduce porcentaje
    public void introducirPorcentaje(int porcentaje,String idCaso){
        PreparedStatement declaracion;
        int id = Integer.parseInt(idCaso);
        try {// actualiza el porcentaje 
            declaracion=conexion.prepareStatement("UPDATE CASO SET Porcentaje_Avance=? WHERE Id=?");
            declaracion.setInt(1, porcentaje);
            declaracion.setInt(2, id);
            declaracion.executeUpdate();
        } catch (Exception e) {
            
        }
    }
    // cancela la accion 
    public void cancelarAccion(String idEtapa, String idCaso, String usuario,String idProyecto,String tipoAccion1){
        String faseProyecto="Etapa";
        String tipoAccion=tipoAccion1;
        String comentario = mandarComentario();
        boolean aprobado=false;
        boolean revisado=true;
        PreparedStatement declaracion;
        Accion accion = new Accion(// crea el objeto 
                usuario,Integer.parseInt(idProyecto),Integer.parseInt(idCaso), Integer.parseInt(idEtapa),faseProyecto,tipoAccion, comentario, aprobado);
        try {//añade valores
            declaracion = conexion.prepareStatement("INSERT INTO ACCION(Nombre_Usuario,Id_Proyecto,Id_Caso,Id_Etapa,Fase_Proyecto,Tipo_Accion,Comentario,Aprobado,Revisada) VALUES(?,?,?,?,?,?,?,?,?);");
            declaracion.setString(1, accion.getNombreUsuario());
            declaracion.setInt(2, accion.getIdProyecto());//añade valores
            declaracion.setInt(3, accion.getIdCaso());
            declaracion.setInt(4, accion.getIdEtapa());//añade valores
            declaracion.setString(5, accion.getFaseProyecto());//añade valores
            declaracion.setString(6, accion.getTipoAccion());
            declaracion.setString(7, accion.getComentario());//añade valores
            declaracion.setBoolean(8, aprobado);//añade valores
            declaracion.setBoolean(9,revisado);
            declaracion.executeUpdate();
            JOptionPane.showMessageDialog(null,"Se asigno la accion");//añade valores
        }catch(SQLException e){
            
        }
    }
    // cancela una etapa 
    public void cancelarEtapa(String idEtapa){
        PreparedStatement declaracion;
        int id = Integer.parseInt(idEtapa);
        try {// prepara la orde
            declaracion=conexion.prepareStatement("UPDATE ETAPA SET Aprobado = NULL WHERE Id=?;");
            declaracion.setInt(1, id);//añade valores
            declaracion.executeUpdate();
        } catch (Exception e) {
            
        }
    }// revisa una accion 
    public void revisarAccion(String idAccion){
        PreparedStatement declaracion;
        boolean revisado=true;
        int id = Integer.parseInt(idAccion);
        try {// ejecuta la accion 
            declaracion=conexion.prepareStatement("UPDATE ACCION SET Revisada =? WHERE Id=?;");
            declaracion.setBoolean(1, revisado);
            declaracion.setInt(2, id);
            declaracion.executeUpdate();
        } catch (Exception e) {
            
        }
    }
    // manda un comentario 
    public String mandarComentario(){
        String comentario = null;
        do{
             comentario = JOptionPane.showInputDialog(null,"Escribe el comentario que le quieres mandar");
            if(comentario==null || comentario.equals("")){
                JOptionPane.showMessageDialog(null,"NO introduciste un comentario valido, por favor introduce uno");
            }
        }while(comentario==null || comentario.equals(""));
        
        return comentario;
    }
}
