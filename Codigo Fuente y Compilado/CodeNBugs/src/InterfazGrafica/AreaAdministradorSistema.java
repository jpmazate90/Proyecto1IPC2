
package InterfazGrafica;

import Logica.MostrarInformacion;
import Logica.Sesion;
import Logica.TablaModelo;
import Logica.ValidacionCaso;
import Logica.ValidacionProyecto;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class AreaAdministradorSistema extends javax.swing.JInternalFrame {
    private TablaModelo modelo2;
    private TableRowSorter tablaSorteada;
    private DefaultTableModel modelo;
    private Connection conexion;
    private JFrame frame;
    private ValidacionProyecto proyectos;
    private ValidacionCaso casos;
    private DefaultTableModel modeloCasos;
    private ValidacionProyecto proyecto;
    private MostrarInformacion informacion;
    private JDesktopPane escritorio;
    private String usuario;
    
    public AreaAdministradorSistema(Connection conexion,JFrame frame,String usuarioSesion, JDesktopPane escritorio) {
        initComponents();
        // son todos los vlaores que hay que iniciarlizar
        this.conexion=conexion;
        this.frame=frame;// frame a inicializar
        proyecto = new ValidacionProyecto(conexion);
        informacion = new MostrarInformacion(conexion);
        this.escritorio=escritorio;
        modelo = new DefaultTableModel();//modelos que se iniciaron
        modeloCasos = new TablaModelo();
        modelo2 = new TablaModelo();
        agregarColumnas();
        tablaProyectos.setModel(modelo2);// asignacion de modelos
        tablaCasos.setModel(modeloCasos);
        this.usuario=usuarioSesion;
        textoUsuario.setText(usuarioSesion);
        proyectos = new ValidacionProyecto(conexion);
        casos = new ValidacionCaso(conexion);// crea los objetos al momento de iniciarse el frame
        agregarProyectos();
        ocultarCreacionCaso();
    }
    // agrega los valores a la tabla proyectos
    public void agregarProyectos(){
        proyectos.agregarDatosTablaProyectos(modelo2);
    }// borra los valores de la tabla proyectos
    public void borrarDatosTabla(){
        proyectos.borrarDatos(modelo2);
    }
    // crea un caso
    public void crearCaso(){
        if(casos.verificarInformacionCaso(textoNombreCaso.getText(),textoEtapas.getText())==true){
            casos.crearTipoCaso(textoNombreCaso.getText(),Integer.parseInt(textoEtapas.getText()));
        }else{// si no se puede lo muestra
            JOptionPane.showMessageDialog(null,"No estan correctos los datos");
        }
    }
    // edita un proyeccto
    public void editarProyecto(boolean activo){
        String id =textoId.getText();
        boolean sePuede=proyectos.validarNombre(id);
        if(sePuede==false){// le edita si esta activo o no
            JOptionPane.showMessageDialog(null,"No se seleciono ningun proyecto");
        }else{// realiza la accion
            proyectos.editarActivoProyecto(id,activo);
        }
           
        
    }
    // unicamente oculta la creacion de un caso
    public void ocultarCreacionCaso(){
        labelNombre.setVisible(false);
        textoNombreCaso.setVisible(false);
        botonCrearCaso.setVisible(false);
        labelTitulCaso.setVisible(false);
        labelEtapas.setVisible(false);
        textoEtapas.setVisible(false);
    }
    // unicamente muestra la creacion de un caso
    public void mostrarCreacionCaso(){
        labelNombre.setVisible(true);
        textoNombreCaso.setVisible(true);
        botonCrearCaso.setVisible(true);
        labelTitulCaso.setVisible(true);
        labelEtapas.setVisible(true);
        textoEtapas.setVisible(true);
    }
    // unicamente consluta un proyecto
    public void consultarProyecto(){
        String idProyecto = null;
        boolean huboFalla=false;
        try {
         int seleccion = tablaProyectos.getSelectedRow();
         idProyecto = (String)modelo2.getValueAt(seleccion, 0);
        }catch(Exception e){
            huboFalla=true;
        }// si no hubieron fallas entra 
        if(huboFalla==false){
            try{
            proyecto.borrarDatos(modeloCasos);// borra los datos del aso
            informacion.consultaProyectoCasos(modeloCasos, idProyecto);// realiza la consulta del proyecto
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{// muestra que no se selecciono ningun proyecto
            JOptionPane.showMessageDialog(null,"No se selecciono ningun proyecto");
        }
    }
    // consulta un caso 
    public void consultarCaso(){
        boolean huboFalla=false;
        String idCaso=null;
        String nombreCaso=null;
        try {// agarra los valores de lo que se selecciono de la tabla
         int seleccion = tablaCasos.getSelectedRow();
         nombreCaso = (String)modeloCasos.getValueAt(seleccion, 1);
         idCaso = (String)modeloCasos.getValueAt(seleccion, 0);
        } catch (Exception e) {
           huboFalla=true;
        }// si no hubieron fallas entra 
            if(huboFalla==false){// y crea el objeto que muestra los casos asignados
                    CasosAsignados caso = new CasosAsignados(conexion, frame,nombreCaso, idCaso);
                    escritorio.add(caso);
                    caso.setVisible(true);
                
            }else{// muestra que no se selecciono ningun caso
                JOptionPane.showMessageDialog(null, "No se selecciono ningun caso");
            }
    }
    // mira las acciones realizadas 
    public void verAccionesRealizadas(){
        String idProyecto=textoId.getText();
        if(idProyecto==null || idProyecto.equals("")){// muestra que no se selecciono ningun proyecto
            JOptionPane.showMessageDialog(null, "No se selecciono ningun proyecto");
        }else{// crea el objeto y lo muestra
            AccionesRealizadas acciones = new AccionesRealizadas(frame, false, conexion, idProyecto);
            acciones.setVisible(true);
        }
    }
    // cambia de administrador el proyecto
    public void cambiarAdministradorProyecto(){
        String idProyecto= textoId.getText();// crea un objeto con los arreglos que pueden ser administradores
        ArrayList<String> usuarios =informacion.usuariosEnArreglo(Sesion.getAdministradorProyecto());
        Object[] objetos= informacion.transformarArrayListEnObject(usuarios);
        Icon icono = frameIcon;// crea el optionpane con el combobox que tiene a los que pueden ser administradores de proyecto
        String administradorProyecto = (String) JOptionPane.showInputDialog(null, "Seleccione un administrador", "Cambiar Administrador Proyecto", JOptionPane.DEFAULT_OPTION, icono, objetos, objetos[0]);
        informacion.cambiarAdministrador(administradorProyecto, idProyecto);// finalmente cambia
    }
    public void agregarColumnas(){
        // solo agrega valores al modelo
        modelo2.addColumn("ID"); modelo2.addColumn("Nombre");modelo2.addColumn("Activo"); modelo2.addColumn("Administrador Proyecto");
        modelo2.isCellEditable(0,0);// solo agrega valores al modelo
        modeloCasos.addColumn("ID"); modeloCasos.addColumn("Nombre");
        modeloCasos.addColumn("Id Proyecto"); modeloCasos.addColumn("Fecha Limite");// solo agrega valores al modelo
        modeloCasos.addColumn("Porcentaje Avance"); modeloCasos.addColumn("Activo");
        modeloCasos.addColumn("Tipo");
        modeloCasos.isCellEditable(0,0);// solo agrega valores al modelo
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textoProyecto = new javax.swing.JLabel();
        textoAdmin = new javax.swing.JLabel();
        checkActivo = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProyectos = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        textoFiltro = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        textoId = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCasos = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        textoFiltroID = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        labelTitulCaso = new javax.swing.JLabel();
        textoNombreCaso = new javax.swing.JTextField();
        textoEtapas = new javax.swing.JTextField();
        labelEtapas = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        botonCrearCaso = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        textoUsuario = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Area Administrador Sistema");

        jButton1.setText("Crear Usuario");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Proyecto:");

        jLabel2.setText("Administrador Proyecto:");

        jLabel4.setText("Activo:");

        checkActivo.setEnabled(false);
        checkActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkActivoActionPerformed(evt);
            }
        });

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
        jScrollPane2.setViewportView(tablaProyectos);

        jLabel11.setText("ID:");

        textoFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoFiltroKeyTyped(evt);
            }
        });

        jLabel12.setText("ID:");

        jButton6.setText("Desactivar Proyecto");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton8.setText("Activar Proyecto");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Consultar Proyecto");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

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
        jScrollPane1.setViewportView(tablaCasos);

        jLabel5.setText("ID:");

        textoFiltroID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoFiltroIDKeyTyped(evt);
            }
        });

        jButton2.setText("Consultar Informacion Caso");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Crear TipoCaso");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        labelTitulCaso.setText("CREACION TIPO DE CASO");

        labelEtapas.setText("Cantidad Etapas: ");

        labelNombre.setText("Nombre:");

        botonCrearCaso.setText("Crearlo");
        botonCrearCaso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearCasoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonCrearCaso)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelEtapas)
                                    .addComponent(labelNombre))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textoNombreCaso, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textoEtapas, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(labelTitulCaso)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textoFiltroID))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3)))
                        .addGap(12, 12, 12))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoFiltroID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(labelTitulCaso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombre)
                    .addComponent(textoNombreCaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEtapas)
                    .addComponent(textoEtapas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(botonCrearCaso)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        jButton7.setText("Ver Acciones Realizadas");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton10.setText("Cambiar Administrador Proyecto");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addGap(18, 18, 18)
                            .addComponent(textoFiltro))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(151, 151, 151)
                            .addComponent(jButton6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton8))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(22, 22, 22)
                                    .addComponent(checkActivo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 239, Short.MAX_VALUE)
                                    .addComponent(jButton9))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel1)
                                                .addComponent(jLabel2))
                                            .addGap(22, 22, 22)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(textoAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                                .addComponent(textoProyecto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(textoId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addComponent(jLabel12))
                                    .addGap(0, 158, Short.MAX_VALUE)))
                            .addGap(87, 87, 87)))
                    .addComponent(jButton10))
                .addGap(39, 39, 39)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(textoId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(textoProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2))
                            .addComponent(textoAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(checkActivo)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton9)
                                .addGap(4, 4, 4)
                                .addComponent(jButton7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6)
                            .addComponent(jButton8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel10.setText("USUARIO EN SESION:");

        jButton5.setText("Crear Proyecto");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel3.setText("Casos:");

        jButton11.setText("Reportes");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(513, 513, 513)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(textoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(65, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addGap(441, 441, 441)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(textoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton5))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jButton11))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // crea el objeto
        CreacionUsuario creacion = new CreacionUsuario(frame, true, conexion,frame);
        creacion.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        CreacionProyecto proyecto = new CreacionProyecto(frame,true,conexion);
        proyecto.setVisible(true);// crea el objeto
        borrarDatosTabla();
        agregarProyectos();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        mostrarCreacionCaso();// crea el objeto
    }//GEN-LAST:event_jButton3ActionPerformed

    private void botonCrearCasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearCasoActionPerformed
        crearCaso();// crea el objeto
    }//GEN-LAST:event_botonCrearCasoActionPerformed

    private void tablaProyectosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProyectosMouseClicked

        //muestra lo que se selecciono de la tabla en las casillas
        int seleccion = tablaProyectos.getSelectedRow();
        textoId.setText(String.valueOf(tablaProyectos.getValueAt(seleccion, 0)));
        textoProyecto.setText(String.valueOf(tablaProyectos.getValueAt(seleccion, 1)));
        textoAdmin.setText(String.valueOf(tablaProyectos.getValueAt(seleccion, 3)));
        checkActivo.setSelected(Boolean.parseBoolean((String) tablaProyectos.getValueAt(seleccion, 2)));
        
    }//GEN-LAST:event_tablaProyectosMouseClicked

    private void textoFiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoFiltroKeyTyped
        
// sortea la tabla
        textoFiltro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {// sortea la tabla
                tablaSorteada.setRowFilter(RowFilter.regexFilter("(?i)"+textoFiltro.getText(), 0));
            }
        });// sortea la tabla

        tablaSorteada = new TableRowSorter(modelo2);
        tablaProyectos.setRowSorter(tablaSorteada);// sortea la tabla
    }//GEN-LAST:event_textoFiltroKeyTyped

    private void checkActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkActivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkActivoActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // actualiza valores
        editarProyecto(false);
        borrarDatosTabla();
        agregarProyectos();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        editarProyecto(true); // actualiza valores
        borrarDatosTabla();
        agregarProyectos();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        consultarProyecto(); // actualiza valores
    }//GEN-LAST:event_jButton9ActionPerformed

    private void textoFiltroIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoFiltroIDKeyTyped
             
// sortea la tabla
        textoFiltroID.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tablaSorteada.setRowFilter(RowFilter.regexFilter("(?i)"+textoFiltroID.getText(), 0));
            }// sortea la tabla
        });
// sortea la tabla
        tablaSorteada = new TableRowSorter(modeloCasos);
        tablaCasos.setRowSorter(tablaSorteada);
    }//GEN-LAST:event_textoFiltroIDKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        consultarCaso();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        verAccionesRealizadas(); // actualiza valores
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        cambiarAdministradorProyecto();
        borrarDatosTabla(); // actualiza valores
        agregarProyectos();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        Reportes reporte = new Reportes(this.conexion,this.usuario);
        escritorio.add(reporte);
        reporte.show(); // actualiza valores
    }//GEN-LAST:event_jButton11ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCrearCaso;
    private javax.swing.JCheckBox checkActivo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelEtapas;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelTitulCaso;
    private javax.swing.JTable tablaCasos;
    private javax.swing.JTable tablaProyectos;
    private javax.swing.JLabel textoAdmin;
    private javax.swing.JTextField textoEtapas;
    private javax.swing.JTextField textoFiltro;
    private javax.swing.JTextField textoFiltroID;
    private javax.swing.JLabel textoId;
    private javax.swing.JTextField textoNombreCaso;
    private javax.swing.JLabel textoProyecto;
    private javax.swing.JLabel textoUsuario;
    // End of variables declaration//GEN-END:variables
}
