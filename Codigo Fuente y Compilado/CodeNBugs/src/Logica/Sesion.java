
package Logica;

import Entidades.Usuario;
import InterfazGrafica.AreaAdministradorProyecto;
import InterfazGrafica.AreaAdministradorSistema;
import InterfazGrafica.AreaDesarrollador;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class Sesion {
    // variables locales privadas
    private Connection conexion;
    private JFrame frame;
    private static final String DESARROLLADOR="desarrollador";
    private static final String ADMINISTRADOR_SISTEMA="administrador sistema";
    private static final String ADMINISTRADOR_PROYECTO="administrador proyecto";
    private static final String FINALIZACION = "Finalizacion";
    private static final String CANCELACION =  "Cancelacion";
    private static final String CANCELACION_CASO =  "Cancelacion Caso";
    // constantes de strings
    private static final String R_PROYECTO_CANTIDAD_PASOS =  "Reporte de proyectos con cantidad de casos";// constantes de strings
    private static final String R_HORAS_DINERO_PROYECTO =  "Reporte horas y dinero en casos de algun proyecto";
    private static final String R_HORAS_DINERO_DESARROLLADOR =  "Reporte horas y dinero en casos de algun desarrollador";
    private static final String R_HORAS_DINERO_TIPO =  "Reporte horas y dinero en casos de algun tipo en especifico";
    private static final String R_HORAS_DINERO_INTERVALO_TIEMPO =  "Reporte horas y dinero en casos en intervalo tiempo";
    private static final String R_DESARROLLADORES =  "Reporte de desarrolladores";
    private static final String R_PROYECTOS =  "Reporte de Proyectos";// constantes de strings
    private static final String R_DESARROLLADOR_CASOS =  "Reporte de desarrollador que ha participado en mas casos";
    private static final String R_DESARROLLADOR_MAS_PAGO ="Reporte de desarrollador al que mas se le ha pagado";// constantes de strings
    private static final String R_CASOS_FINALIZADOS =  "Reporte proyecto que tiene mas casos reportados y finalizados";
    private static final String R_CASOS_CANCELADOS="Reporte proyecto que tiene mas casos cancelados";// constantes de strings
    private static final String R_CASOS_PROYECTO =  "Reporte de casos de un proyecto";
    private static final String R_CASOS_DESARROLLADOR =  "Reporte de casos de un desarrollador";// constantes de strings
    private static final String R_CASOS_TIPO =  "Reporte de casos de un tipo";// constantes de strings







// constructor de la clase 
    public Sesion(Connection conexion, JFrame frame) {
        this.conexion=conexion;
        this.frame=frame;
    }
    // verifica un usuario 
    public String verificarUsuario(String usuario, String contraseña){
        try {// prepara la orden 
            PreparedStatement declaracion = conexion.prepareStatement("SELECT * FROM USUARIO WHERE Usuario=?");
            declaracion.setString(1, usuario);
            ResultSet resultado = declaracion.executeQuery();
            resultado.next();// ejecuta el resultado
                String usuarioEncontrado;
                String contraseñaEncontrada;
                String areaEncontrada;// maneja el resultado 
                usuarioEncontrado=resultado.getString("Usuario");
                contraseñaEncontrada=resultado.getString("Contraseña");// maneja el resultado 
                areaEncontrada=resultado.getString("Tipo_Usuario");// maneja el resultado 
                if(usuarioEncontrado==null){
                    return null;
                }else{// maneja el resultado 
                    if(contraseñaEncontrada.equals(contraseña)){
                        return areaEncontrada;
                    }else{
                        return null;
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    // agrega a un frame
    public void agregarFrame(String tipoUsuario, String usuario, JDesktopPane escritorio){
        if(tipoUsuario.equals(DESARROLLADOR)){//crea el objeto
            AreaDesarrollador area = new AreaDesarrollador(conexion,frame,usuario,escritorio);
            limpiar(escritorio);
            escritorio.add(area);
            area.show();//crea el objeto
        }else if(tipoUsuario.equals(ADMINISTRADOR_PROYECTO)){
            AreaAdministradorProyecto area = new AreaAdministradorProyecto(conexion,frame,usuario,escritorio);
            limpiar(escritorio);
            escritorio.add(area);//crea el objeto
            area.show();
        }else if(tipoUsuario.equals(ADMINISTRADOR_SISTEMA)){
            AreaAdministradorSistema area = new AreaAdministradorSistema(conexion, frame,usuario,escritorio);
            limpiar(escritorio);//crea el objeto
            escritorio.add(area);
            area.show();
        }
    }
    //limpia
    public void limpiar(JDesktopPane escritorio){
        escritorio.removeAll();
        escritorio.repaint();
    }
// valida un usuario 
    public boolean validarEspaciosUsuario(String[] datos){
        for(int i =0; i<datos.length;i++){
            if(datos[i].equals("") || datos[i]==null){
                return false;
            }
        }
        return true;
    }
    // valida un usuario 
    public boolean validarUsuario(String usuario, String dinero,String tipo){
        try{
            int valorDinero = Integer.parseInt(dinero);
            int valorTipo = Integer.parseInt(tipo);
            if(valorTipo>3){
                return false;
            }else{
                //ResultSet resultado = declaracion.executeQuery("SELECT * FROM USUARIO WHERE Usuario='"+usuario+"'");
                PreparedStatement declaracion = conexion.prepareStatement("SELECT * FROM USUARIO WHERE Usuario=?");
                declaracion.setString(1,usuario);
                ResultSet resultado=declaracion.executeQuery();
                resultado.next();// maneja el resultado 
                String usuarioEncontrado = resultado.getString("Usuario");
                if(usuarioEncontrado==null){
                    return true;// regresa true
                }else{
                    return false;
                }
            }
// excepciones
        }catch(NumberFormatException e){
            e.printStackTrace();
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return true;
        }
        
    }
    // crea un usuario
    public void crearUsuario(String nombre, String apellidos, String usuario, String contraseña, String dpi, String dinero,String tipo){
        try {
            PreparedStatement declaracion;
            SimpleDateFormat formatoSinHora = new SimpleDateFormat("yyyy/MM/dd");
            Date fechaActual = new Date();
            long tiempo= fechaActual.getTime();
            java.sql.Date fechaSql= new java.sql.Date(tiempo);
            String fechaEnString = formatoSinHora.format(fechaActual);
            int valorDinero = Integer.parseInt(dinero);
            int valorTipo = Integer.parseInt(tipo);
            String tipoUsuario="";
            // dependiendo el tipo que se escogio escoge uno u otro
            switch(valorTipo){
                case 1: tipoUsuario=DESARROLLADOR; break;
                case 2: tipoUsuario=ADMINISTRADOR_PROYECTO;break;
                case 3: tipoUsuario=ADMINISTRADOR_SISTEMA;break;
            }
            Usuario usuarioNuevo = new Usuario(
                    usuario, contraseña, nombre, apellidos, dpi, fechaSql, valorDinero, tipoUsuario);
            // prepaara la orden 
            declaracion = conexion.prepareStatement("INSERT INTO USUARIO(Usuario,Contraseña,Nombre,Apellido,Dpi,Fecha_Ingreso,Dinero_Hora,Tipo_Usuario) VALUES (?,?,?,?,?,?,?,?);");
            declaracion.setString(1,usuarioNuevo.getUsuario());// maneja el resultado 
            declaracion.setString(2,usuarioNuevo.getContraseña());
            declaracion.setString(3,usuarioNuevo.getNombre());// maneja el resultado 
            declaracion.setString(4,usuarioNuevo.getApellido());
            declaracion.setString(5,usuarioNuevo.getDpi());// maneja el resultado 
            declaracion.setDate(6,usuarioNuevo.getFechaIngreso());// hay que cambiarle al tipo date como tal
            declaracion.setInt(7, usuarioNuevo.getDineroHora());
            declaracion.setString(8,usuarioNuevo.getTipoUsuario());
            declaracion.executeUpdate();// maneja el resultado 
            
                JOptionPane.showMessageDialog(null,"se creo correctamente el usuario: "+usuarioNuevo.getUsuario());
           
        } catch (HeadlessException | SQLException e) {
            e.printStackTrace();
        }
    }
    // cra el arreglo de reportes a utilizar 
    public static String[] arregloReportes(){
        String[] reportes ={getR_PROYECTO_CANTIDAD_PASOS(), getR_HORAS_DINERO_PROYECTO(),getR_HORAS_DINERO_DESARROLLADOR()
        ,getR_HORAS_DINERO_TIPO(),getR_HORAS_DINERO_INTERVALO_TIEMPO(),getR_DESARROLLADORES(),getR_PROYECTOS(),getR_DESARROLLADOR_CASOS(),getR_DESARROLLADOR_MAS_PAGO(),getR_CASOS_FINALIZADOS()
        ,getR_CASOS_CANCELADOS(),getR_CASOS_PROYECTO(),getR_CASOS_DESARROLLADOR(),getR_CASOS_TIPO()};
        return reportes;
    }
    // getter
    public static String getDesarrollador(){
        return DESARROLLADOR;
    }
     // getter
    public static String getAdministradorProyecto(){
        return ADMINISTRADOR_PROYECTO;
    } // getter
    public static String getAdministradorSistema(){
        return ADMINISTRADOR_SISTEMA;
    }
     // getter
    public static String getFinalizacion(){
        return FINALIZACION;
    } // getter
    public static String getCancelacion(){
        return CANCELACION;
    }
 // getter
    public static String getDESARROLLADOR() {
        return DESARROLLADOR;
    }
 // getter
    public static String getADMINISTRADOR_SISTEMA() {
        return ADMINISTRADOR_SISTEMA;
    }
 // getter
    public static String getADMINISTRADOR_PROYECTO() {
        return ADMINISTRADOR_PROYECTO;
    }
 // getter
    public static String getFINALIZACION() {
        return FINALIZACION;
    }
 // getter
    public static String getCANCELACION() {
        return CANCELACION;
    } // getter

    public static String getR_PROYECTO_CANTIDAD_PASOS() {
        return R_PROYECTO_CANTIDAD_PASOS;
    } // getter

    public static String getR_HORAS_DINERO_PROYECTO() {
        return R_HORAS_DINERO_PROYECTO;
    } // getter

    public static String getR_HORAS_DINERO_DESARROLLADOR() {
        return R_HORAS_DINERO_DESARROLLADOR;
    }
 // getter
    public static String getR_HORAS_DINERO_INTERVALO_TIEMPO() {
        return R_HORAS_DINERO_INTERVALO_TIEMPO;
    }

     // getter
    public static String getR_HORAS_DINERO_TIPO() {
        return R_HORAS_DINERO_TIPO;
    } // getter

    public static String getR_DESARROLLADORES() {
        return R_DESARROLLADORES;
    }
 // getter
    public static String getR_PROYECTOS() {
        return R_PROYECTOS;
    }
 // getter
    public static String getR_DESARROLLADOR_CASOS() {
        return R_DESARROLLADOR_CASOS;
    }
 // getter
    public static String getR_DESARROLLADOR_MAS_PAGO() {
        return R_DESARROLLADOR_MAS_PAGO;
    } // getter

    public static String getR_CASOS_FINALIZADOS() {
        return R_CASOS_FINALIZADOS;
    } // getter

    public static String getR_CASOS_CANCELADOS() {
        return R_CASOS_CANCELADOS;
    } // getter

    public static String getR_CASOS_PROYECTO() {
        return R_CASOS_PROYECTO;
    } // getter

    public static String getR_CASOS_DESARROLLADOR() {
        return R_CASOS_DESARROLLADOR;
    }
 // getter
    public static String getR_CASOS_TIPO() {
        return R_CASOS_TIPO;
    }
 // getter
    public static String getCANCELACION_CASO() {
        return CANCELACION_CASO;
    }
    
    
}
