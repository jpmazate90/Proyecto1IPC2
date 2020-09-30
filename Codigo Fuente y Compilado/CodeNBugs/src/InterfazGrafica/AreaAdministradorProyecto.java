
package InterfazGrafica;

import Entidades.Etapa;
import Logica.MostrarInformacion;
import Logica.Sesion;
import Logica.TablaModelo;
import Logica.ValidacionCaso;
import Logica.ValidacionEtapa;
import Logica.ValidacionProyecto;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author jpmazate
 */
public class AreaAdministradorProyecto extends javax.swing.JInternalFrame {
// variables privadas de la clase 
    private TablaModelo modelo;
    private TablaModelo modeloCasos;
    private TablaModelo modeloEtapasTerminadas;
    private TablaModelo modeloFechaCercana;
    private TablaModelo modeloFechaFinalizada;
    private TableRowSorter tablaSorteada;
    private Connection conexion;
    private JFrame frame;
    private ValidacionCaso caso; 
    private ValidacionEtapa etapa;
    private ValidacionProyecto proyecto;
    private String usuario;
    private MostrarInformacion informacion;
    private JDesktopPane escritorio;
    // constructor que facilita la instanciacion de la clase
    public AreaAdministradorProyecto(Connection conexion,JFrame frame,String usuarioSesion,JDesktopPane escritorio) {
        this.usuario=usuarioSesion;
        initComponents();
        textoUsuario.setText(usuarioSesion);
        this.conexion=conexion;// asigna las conexiones  y los validadores
        this.escritorio=escritorio;
        caso = new ValidacionCaso(this.conexion);
        etapa = new ValidacionEtapa(conexion);
        proyecto = new ValidacionProyecto(conexion);
        informacion = new MostrarInformacion(conexion);
        this.frame=frame;// crea los modelos de las tablas
        modelo = new TablaModelo();
        modeloCasos = new TablaModelo();
        modeloEtapasTerminadas = new TablaModelo();
        modeloFechaCercana = new TablaModelo();
        modeloFechaFinalizada = new TablaModelo();
        agregarDatosTabla();// asigna los valores de las tablas
        tablaEtapasAprobar.setModel(modeloEtapasTerminadas);
        tablaProyectos.setModel(modelo);
        tablaCasos.setModel(modeloCasos);// setea los modelos a las tablas solicitadas
        tablaFechaCercana.setModel(modeloFechaCercana);
        tablaFechaFinalizada.setModel(modeloFechaFinalizada);
        agregarProyectos();
        agregarFechasLimites();
    }
    
// agrega los proyectos a las tablas que se utilizaran
    public void agregarProyectos(){
        caso.agregarProyectosTabla(modelo, usuario);
        caso.asignarDatosCasos(modeloCasos, usuario);
        etapa.agregarDatosEtapasFinalizadas(modeloEtapasTerminadas, usuario);
        
    }// borra los datos de las tablas 
    public void borrarDatosTabla(){
        proyecto.borrarDatos(modelo);
        proyecto.borrarDatos(modeloCasos);
        proyecto.borrarDatos(modeloEtapasTerminadas);
    }
    // verifica las etapas 
    public void verificarEtapa(){
        boolean huboFalla=false;
        String idEtapa=null;
        String usuario=null;
        String idCaso=null;
        String idAccion=null;
        try {// recoge valores a las tablas 
         int seleccion = tablaEtapasAprobar.getSelectedRow();// recoge la selecion
         idEtapa = (String)modeloEtapasTerminadas.getValueAt(seleccion, 4);// recoge el id de etapa
         idCaso = (String)modeloEtapasTerminadas.getValueAt(seleccion, 3);// recoge el id de caso
         usuario = (String)modeloEtapasTerminadas.getValueAt(seleccion, 1);// recoge el usuario
         idAccion = (String)modeloEtapasTerminadas.getValueAt(seleccion, 0);// recoge el id de la accion
        } catch (Exception e) {
           huboFalla=true;
        }// si no hubo falla al recoger los datos de las tablas entra
            if(huboFalla==false){
                boolean activo = caso.verificarCaso(idCaso);
                if(activo==true){
                    boolean aprobado=true;// agrega los valores y reliza las acciones pertinentes
                    etapa.agregarTotalTiempo(idEtapa, usuario);
                    etapa.actualizarPorcentajeAvance(idCaso);
                    etapa.actualizarEstadoAccion(idAccion,aprobado);
                    etapa.siguienteEtapa(idCaso, frame);
                    borrarDatosTabla();
                    agregarProyectos();
                }else{// muestra que no se pudo realizar la accion
                    JOptionPane.showMessageDialog(null, "No se puede hacer realizar la accion, el caso esta cancelado");
                }
                
            }else{// muestra que no se pudo realizar la accion
                JOptionPane.showMessageDialog(null, "No se selecciono ninguna etapa");
            }
    }
    // cancela una etapa
    public void cancelarEtapa(){
        boolean huboFalla=false;
        String idEtapa=null;
        String usuario=null;
        String idCaso=null;
        String idProyecto=null;
        String idAccion=null;
        try {// recoge los valores d elas tablas
         int seleccion = tablaEtapasAprobar.getSelectedRow();
         idEtapa = (String)modeloEtapasTerminadas.getValueAt(seleccion, 4); //recoge el id de una etapa
         idCaso = (String)modeloEtapasTerminadas.getValueAt(seleccion, 3);//recoge el id de un caso
         usuario = (String)modeloEtapasTerminadas.getValueAt(seleccion, 1);// recoge el id de un usuario
         idProyecto =(String)modeloEtapasTerminadas.getValueAt(seleccion, 2);// recoge el id de un proyecto
         idAccion = (String)modeloEtapasTerminadas.getValueAt(seleccion, 0);// recoge el ide de la accion
        } catch (Exception e) {
           huboFalla=true;
        }// si no hubo falla significa que pudo recoger los valroes
            if(huboFalla==false){
                boolean activo = caso.verificarCaso(idCaso);
                if(activo==true){// cancela la accion
                    etapa.cancelarAccion(idEtapa, idCaso, this.usuario,idProyecto,Sesion.getCancelacion());
                    etapa.cancelarEtapa(idEtapa);
                    etapa.revisarAccion(idAccion);
                    etapa.actualizarPorcentajeAvance(idCaso);
                    borrarDatosTabla();// borra los datos de la tabla
                    agregarProyectos();// agrega los proyectos
                }else{// muestra que no se pudo realizar la accion
                    JOptionPane.showMessageDialog(null, "No se puede hacer realizar la accion, el caso esta cancelado");
                }
                
            }else{// muestra que no se pudo realizar la accion
                JOptionPane.showMessageDialog(null, "No se selecciono ninguna etapa");
            }
    }
    // este metodo cancela un caso
    public void cancelarCaso(boolean cancelar){
    String id=labelId.getText();
    String idProyecto = labelIdProyecto.getText();
    boolean sePuede=proyecto.validarNombre(id);// valida si se puede cancelear un caso por el id
    if(sePuede==true){// si se cancela agrega la accion al sistema
        caso.agregarAccionCancelacionCaso(idProyecto,id, usuario, Sesion.getCANCELACION_CASO());
        caso.cancelarCaso(id, cancelar);
        borrarDatosTabla();// borra los datos
        agregarProyectos();// actualiza la tabla
    }else{// muestra que no se hizo nada
        JOptionPane.showMessageDialog(null,"No se selecciono ningun caso");
    }
    
    }
    // consulta un proyecto 
    public void consultarProyecto(){
        String idProyecto = null;
        boolean huboFalla=false;
        try {// agarrra el proyeccto de la tabla
         int seleccion = tablaProyectos.getSelectedRow();
         idProyecto = (String)modelo.getValueAt(seleccion, 0);
        }catch(Exception e){
            huboFalla=true;
        }// mira is hubo falla al agarrar el valor
        if(huboFalla==false){
            try{
            proyecto.borrarDatos(modeloCasos);
            informacion.consultaProyectoCasos(modeloCasos, idProyecto);
            }catch(Exception e){// consulta el proyecto
                e.printStackTrace();
            }
        }else{// muestra que no se selecciono noada
            JOptionPane.showMessageDialog(null,"No se selecciono ningun proyecto");
        }
        
    }
    // consulta un caso
    public void consultarCaso(){
        boolean huboFalla=false;
        String idCaso=null;
        String nombreCaso=null;
        try {// verifica si se selecciono algo
         int seleccion = tablaCasos.getSelectedRow();
         nombreCaso = (String)modeloCasos.getValueAt(seleccion, 1);
         idCaso = (String)modeloCasos.getValueAt(seleccion, 0);
        } catch (Exception e) {
           huboFalla=true;
        }// si no huberrion fallas entras
            if(huboFalla==false){
                    CasosAsignados caso = new CasosAsignados(conexion, frame,nombreCaso, idCaso);
                    escritorio.add(caso);
                    caso.setVisible(true);
                
            }else{// muestra que no se seleccionon nada
                JOptionPane.showMessageDialog(null, "No se selecciono ningun caso");
            }
    }
    public void cambiarFechaLimite(){// cambia la fecha limite de un caso
        String idCaso = labelId.getText();
        if(idCaso==null || idCaso.equals("")){// muestra que no se seleccionon nada
            JOptionPane.showMessageDialog(null, "No se selecciono ningun caso");
        }else{
            Date fecha = proyecto.preguntarFecha();
            proyecto.cambiarFecha(fecha, idCaso);
            // cambia la fecha
        }
    }
    
    public void agregarFechasLimites(){
        try{// agrega las fechas limites a los casos tabka
        informacion.agregarFechasLimites(modeloFechaCercana, modeloFechaFinalizada, modeloCasos,3,0,"4");
        }catch(Exception e){
            
        }
    }
    // agrega los valores a las columnas pertinentes
    public void agregarDatosTabla(){
        modelo.addColumn("ID"); modelo.addColumn("Nombre");
        modelo.addColumn("Activo");
        modelo.addColumn("AdministradorProyecto");
        modelo.isCellEditable(0,0);// agrega los valores a las columnas pertinentes
        modeloCasos.addColumn("ID"); modeloCasos.addColumn("Nombre");
        modeloCasos.addColumn("Id Proyecto"); modeloCasos.addColumn("Fecha Limite");
        modeloCasos.addColumn("Porcentaje Avance"); modeloCasos.addColumn("Activo");
        modeloCasos.addColumn("Tipo");// agrega los valores a las columnas pertinentes
        modeloCasos.isCellEditable(0,0);// agrega los valores a las columnas pertinentes
        modeloEtapasTerminadas.addColumn("ID");modeloEtapasTerminadas.addColumn("Nombre Usuario"); modeloEtapasTerminadas.addColumn("ID Proyecto");
        modeloEtapasTerminadas.addColumn("ID Caso"); modeloEtapasTerminadas.addColumn("ID Etapa"); modeloEtapasTerminadas.addColumn("Fase Proyecto");
        modeloEtapasTerminadas.addColumn("Tipo Accion"); modeloEtapasTerminadas.addColumn("Comentario"); modeloEtapasTerminadas.addColumn("Aprobado");
        modeloEtapasTerminadas.addColumn("Revisado");// agrega los valores a las columnas pertinentes
        modeloEtapasTerminadas.isCellEditable(0,0);// agrega los valores a las columnas pertinentes
        modeloFechaCercana.addColumn("ID"); modeloFechaCercana.addColumn("Fecha");
        modeloFechaFinalizada.addColumn("ID"); modeloFechaFinalizada.addColumn("Fecha");// agrega los valores a las columnas pertinentes

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaFechaCercana = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaFechaFinalizada = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaCasos = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablaProyectos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labelId = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEtapasAprobar = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        labelTipo = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        labelFecha = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        labelIdProyecto = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        checkActivo = new javax.swing.JCheckBox();
        jButton8 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        textoFiltro = new javax.swing.JTextField();
        textoUsuario = new javax.swing.JLabel();
        textoProyectos = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        checkProyectoActivo = new javax.swing.JCheckBox();
        labelProyectoAdmin = new javax.swing.JLabel();
        labelProyectoId = new javax.swing.JLabel();
        labelProyectoNombre = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Area Administrador Proyecto");
        setPreferredSize(new java.awt.Dimension(1200, 900));

        jButton1.setText("Asignar Caso");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel21.setText("Casos Cercanos Fecha Limite");

        tablaFechaCercana.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(tablaFechaCercana);

        tablaFechaFinalizada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(tablaFechaFinalizada);

        jLabel22.setText("Casos Fecha Limite Cumplida");

        jLabel24.setText("Usuario En Sesion:");

        jLabel25.setText("Casos");

        jLabel11.setText("Proyectos:");

        tablaCasos.setAutoCreateRowSorter(true);
        tablaCasos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaCasos.setCellSelectionEnabled(true);
        tablaCasos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCasosMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tablaCasos);

        tablaProyectos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaProyectos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProyectosMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tablaProyectos);

        jLabel1.setText("Caso:");

        jLabel2.setText("ID:");

        jButton3.setText("CANCELAR CASO");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        tablaEtapasAprobar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaEtapasAprobar);

        jLabel3.setText("Etapas Por Aprobar:");

        jButton6.setText("ACEPTAR ETAPA");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("CANCELAR ETAPA");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel5.setText("Tipo:");

        jLabel8.setText("Fecha Limite:");

        jLabel10.setText("ID Proyecto:");

        jLabel27.setText("Activo:");

        checkActivo.setEnabled(false);

        jButton8.setText("Cambiar Fecha Limite");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel4.setText("ID:");

        textoFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoFiltroKeyTyped(evt);
            }
        });

        textoProyectos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoProyectosKeyTyped(evt);
            }
        });

        jLabel6.setText("Proyecto:");

        jLabel7.setText("ID:");

        jLabel9.setText("Admin. Proyecto:");

        jLabel12.setText("Activo:");

        checkProyectoActivo.setEnabled(false);

        jButton5.setText("Consultar proyecto");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton9.setText("Regresar Tabla Normalidad");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Consultar Caso");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(57, 57, 57)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(18, 18, 18)
                                        .addComponent(textoProyectos, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel12)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(checkProyectoActivo))
                                                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel25)
                                        .addGap(49, 49, 49)
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel8)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel10)
                                                    .addComponent(jLabel27))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(checkActivo, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(labelTipo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(labelFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(labelIdProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel1)
                                                    .addComponent(jLabel2))
                                                .addGap(59, 59, 59)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(labelNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(labelId, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(450, 450, 450)
                                .addComponent(jLabel24)
                                .addGap(15, 15, 15)
                                .addComponent(textoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(330, 330, 330)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelProyectoId, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelProyectoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelProyectoAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel24)
                    .addComponent(textoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(textoProyectos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelProyectoId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelProyectoNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(labelProyectoAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(checkProyectoActivo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton9)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(jLabel4)
                            .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelId, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(labelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelIdProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27)
                                    .addComponent(checkActivo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addGap(437, 437, 437))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AsignarCaso caso = new AsignarCaso(frame, true, conexion,this.usuario);
        caso.setVisible(true);

        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        verificarEtapa();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        cancelarEtapa();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void textoFiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoFiltroKeyTyped
            
// sortea la tabla
        textoFiltro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tablaSorteada.setRowFilter(RowFilter.regexFilter("(?i)"+textoFiltro.getText(), 0));
            }
        });

        tablaSorteada = new TableRowSorter(modeloCasos);
        tablaCasos.setRowSorter(tablaSorteada);
    }//GEN-LAST:event_textoFiltroKeyTyped

    private void tablaCasosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCasosMouseClicked
        
        //muestra lo que se selecciono de la tabla en las casillas
        int seleccion = tablaCasos.getSelectedRow();
        labelId.setText(String.valueOf(tablaCasos.getValueAt(seleccion, 0)));
        labelNombre.setText(String.valueOf(tablaCasos.getValueAt(seleccion, 1)));
        labelIdProyecto.setText(String.valueOf(tablaCasos.getValueAt(seleccion, 2)));
        labelFecha.setText(String.valueOf(tablaCasos.getValueAt(seleccion, 3)));
        labelTipo.setText(String.valueOf(tablaCasos.getValueAt(seleccion, 6)));
        checkActivo.setSelected(Boolean.parseBoolean((String) tablaCasos.getValueAt(seleccion, 5)));
    }//GEN-LAST:event_tablaCasosMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        cancelarCaso(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void textoProyectosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoProyectosKeyTyped
                  
// sortea la tabla
        textoProyectos.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {// sortea la tabla
                tablaSorteada.setRowFilter(RowFilter.regexFilter("(?i)"+textoProyectos.getText(), 0));
            }
        });
// sortea la tabla
        tablaSorteada = new TableRowSorter(modelo);
        tablaProyectos.setRowSorter(tablaSorteada);
    }//GEN-LAST:event_textoProyectosKeyTyped

    private void tablaProyectosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProyectosMouseClicked
         //muestra lo que se selecciono de la tabla en las casillas
        int seleccion = tablaProyectos.getSelectedRow();
        labelProyectoId.setText(String.valueOf(tablaProyectos.getValueAt(seleccion, 0)));//muestra lo que se selecciono de la tabla en las casillas
        labelProyectoNombre.setText(String.valueOf(tablaProyectos.getValueAt(seleccion, 1)));//muestra lo que se selecciono de la tabla en las casillas
        labelProyectoAdmin.setText(String.valueOf(tablaProyectos.getValueAt(seleccion, 3)));
        checkProyectoActivo.setSelected(Boolean.parseBoolean((String) tablaProyectos.getValueAt(seleccion, 2)));
    }//GEN-LAST:event_tablaProyectosMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        consultarProyecto();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        proyecto.borrarDatos(modeloCasos);
        caso.asignarDatosCasos(modeloCasos, usuario);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        consultarCaso();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        cambiarFechaLimite();
    }//GEN-LAST:event_jButton8ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkActivo;
    private javax.swing.JCheckBox checkProyectoActivo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelIdProyecto;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelProyectoAdmin;
    private javax.swing.JLabel labelProyectoId;
    private javax.swing.JLabel labelProyectoNombre;
    private javax.swing.JLabel labelTipo;
    private javax.swing.JTable tablaCasos;
    private javax.swing.JTable tablaEtapasAprobar;
    private javax.swing.JTable tablaFechaCercana;
    private javax.swing.JTable tablaFechaFinalizada;
    private javax.swing.JTable tablaProyectos;
    private javax.swing.JTextField textoFiltro;
    private javax.swing.JTextField textoProyectos;
    private javax.swing.JLabel textoUsuario;
    // End of variables declaration//GEN-END:variables
}
