
package InterfazGrafica;

import Entidades.Proyecto;
import Logica.MostrarInformacion;
import Logica.Sesion;
import Logica.TablaModelo;
import Logica.ValidacionCaso;
import Logica.ValidacionEtapa;
import Logica.ValidacionProyecto;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class AreaDesarrollador extends javax.swing.JInternalFrame {
// crea los atributos de la clase
    private Connection conexion;
    private String usuario;
    private ValidacionEtapa etapa;
    private ValidacionProyecto proyecto;
    private ValidacionCaso caso;
    private MostrarInformacion informacion;
    private TablaModelo modelo;// crea los atributos de la clase
    private TablaModelo modeloAcciones;
    private TablaModelo modeloCasosAsignados;
    private TablaModelo modeloFechasCercanas;
    private TablaModelo modeloFechasFinalizadas; // crea los atributos de la clase
    private TableRowSorter tablaSorteada;
    private JDesktopPane escritorio;
    private JFrame frame;
    // constructor que inicializa de una mejor forma la clase
    public AreaDesarrollador(Connection conexion,JFrame frame,String usuario , JDesktopPane escritorio) {
        initComponents();
        this.escritorio=escritorio;
        this.frame=frame;
        etapa = new ValidacionEtapa(conexion);// crea el objeto a usar
        proyecto = new ValidacionProyecto(conexion);// crea el objeto a usar
        caso= new ValidacionCaso(conexion);// crea el objeto a usar
        informacion = new MostrarInformacion(conexion);
        modelo = new TablaModelo();// crea los modelos de las tablas 
        modeloAcciones = new TablaModelo();
        modeloCasosAsignados = new TablaModelo();
        modeloFechasCercanas = new TablaModelo();
        modeloFechasFinalizadas = new TablaModelo();
        agregarColumnas();// agrega valores a las columnas
        tablaEtapas.setModel(modelo);// set las tablas
        tablaAcciones.setModel(modeloAcciones);// set las tablas
        tablaCasosAsignados.setModel(modeloCasosAsignados);// set las tablas
        tablaFechaCercana.setModel(modeloFechasCercanas);// set las tablas
        tablaFechaCumplida.setModel(modeloFechasFinalizadas);// set las tablas
        this.usuario=usuario;
        this.conexion=conexion;
        textoUsuario.setText(usuario);
        agregarEtapas();
        agregarFechas();
    }
    
    
    // agrega valores a las etapas
    public void agregarEtapas(){
        etapa.asignarEtapasDesarrolladores(modelo, usuario);
        caso.asignarValoresAccionDesarrollador(modeloAcciones, usuario);
        caso.agregarCasosAsignados(modeloCasosAsignados, usuario);
    }
    // agrega las fechas limites
    public void agregarFechas(){
        informacion.agregarFechasLimites(modeloFechasCercanas, modeloFechasFinalizadas, modelo,4 , 0,null);
    }
    // crea un caso
    public void crearCaso(){
        String caso = labelCaso.getText();
        String id = labelId.getText();// verifica si tiene datos
        if(caso==null || caso.equals("") || id==null || id.equals("")){
            JOptionPane.showMessageDialog(null,"No seleccionaste ningun caso");
        }else{// crea la instancia del objeto
          CasosAsignados casos = new CasosAsignados(conexion, frame, caso,id);
          this.escritorio.add(casos);
          casos.show();
        
        }
    }
// termina el trabajo del desarrollador
    public void terminarTrabajo(){
        String idEtapa=null;
        String idCaso = null;
        String desarrollador = null;
        boolean huboFalla=false;
    try{// agarra los valores de la tabla
         int seleccion = tablaEtapas.getSelectedRow();
         idEtapa = (String)modelo.getValueAt(seleccion, 0);
         idCaso = (String)modelo.getValueAt(seleccion,5);
         desarrollador = (String)modelo.getValueAt(seleccion,2);
        }catch(ArrayIndexOutOfBoundsException e){
            huboFalla=true;
            // si no hubieron fallas entra 
        }if(huboFalla==false){
            boolean activo=proyecto.verificarProyectoEtapa(idEtapa);
            // verifica si un caso
            if(activo==true){// si es asi verifica el proyecto
                boolean casoActivo = caso.verificarCaso(idCaso);
                if(casoActivo==true){// si el caso esta activo entra
                    int idProyecto = etapa.idProyectoDesdeEtapa(idEtapa);
                    etapa.finalizacionCaso(idEtapa, idCaso, desarrollador, Integer.toString(idProyecto),Sesion.getFinalizacion());
                    etapa.registrarHoras(idEtapa);// finaliza la accion
                }else{// muestra que no se puede 
                    JOptionPane.showMessageDialog(null,"El caso esta inactivo no se pueden realizar operaciones sobre él");
                }
            }else{// muestra que no se puede 
                JOptionPane.showMessageDialog(null,"El proyecto esta inactivo no se pueden realizar operaciones sobre él");
            }
        }else{// muestra que no se puede 
            JOptionPane.showMessageDialog(null,"No se selecciono que trabajo terminar");
        }
    }
    // borra los datos de un modleo
    public void borrarDatos(){
        for(int i=0;i<modelo.getRowCount();i++){
            modelo.removeRow(i);
            if(modelo.getRowCount()>0){
                borrarDatos();
            }
        
        }
    }
    //agrega valores a la columna
    public void agregarColumnas(){
        modelo.addColumn("Id"); modelo.addColumn("Nombre"); modelo.addColumn("Desarrollador");
        modelo.addColumn("Activo"); modelo.addColumn("Fecha Limite"); modelo.addColumn("Id Caso");
        modelo.isCellEditable(0,0);//agrega valores a la columna
        modeloAcciones.addColumn("ID");modeloAcciones.addColumn("Nombre Usuario"); modeloAcciones.addColumn("ID Proyecto");
        modeloAcciones.addColumn("ID Caso"); modeloAcciones.addColumn("ID Etapa"); modeloAcciones.addColumn("Fase Proyecto");
        modeloAcciones.addColumn("Tipo Accion"); modeloAcciones.addColumn("Comentario"); modeloAcciones.addColumn("Aprobado");
        modeloAcciones.addColumn("Revisado");//agrega valores a la columna
        modeloAcciones.isCellEditable(0,0);
        modeloCasosAsignados.addColumn("ID"); modeloCasosAsignados.addColumn("Nombre");
        modeloCasosAsignados.isCellEditable(0, 0);//agrega valores a la columna
        modeloFechasCercanas.addColumn("ID"); modeloFechasCercanas.addColumn("Fecha Limite");//agrega valores a la columna
        modeloFechasFinalizadas.addColumn("ID"); modeloFechasFinalizadas.addColumn("Fecha Limite");
    }//agrega valores a la columna
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaFechaCercana = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaFechaCumplida = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaCasosAsignados = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelCaso = new javax.swing.JLabel();
        labelId = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        textoFiltro = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        botonTerminarTrabajo = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        textoUsuario = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaEtapas = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaAcciones = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Desarrollador");

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
        jScrollPane1.setViewportView(tablaFechaCercana);

        jLabel1.setText("Casos Cercanos Fecha Limite");

        tablaFechaCumplida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tablaFechaCumplida);

        jLabel2.setText("Casos Fecha Limite Cumplida");

        tablaCasosAsignados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaCasosAsignados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCasosAsignadosMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tablaCasosAsignados);

        jLabel3.setText("CASO:");

        jLabel4.setText("ID:");

        jButton1.setText("Mostrar Informacion");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel8.setText("ID:");

        textoFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoFiltroKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoFiltro))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(labelCaso, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelId, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1))
                .addContainerGap(302, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(labelCaso, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(labelId, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        botonTerminarTrabajo.setText("Terminar Trabajo");
        botonTerminarTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonTerminarTrabajoActionPerformed(evt);
            }
        });

        jLabel6.setText("Cancelaciones De Etapas:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonTerminarTrabajo)
                    .addComponent(jLabel6))
                .addGap(0, 940, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(botonTerminarTrabajo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        jLabel5.setText("Usuario En Sesion:");

        tablaEtapas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tablaEtapas);

        tablaAcciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(tablaAcciones);

        jLabel7.setText("CASOS ASIGNADOS:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 842, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 839, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel7)
                                .addGap(814, 814, 814)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(textoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonTerminarTrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonTerminarTrabajoActionPerformed
        terminarTrabajo();
        borrarDatos();
        agregarEtapas();
    }//GEN-LAST:event_botonTerminarTrabajoActionPerformed

    private void textoFiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoFiltroKeyTyped
                          
// sortea la tabla
        textoFiltro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tablaSorteada.setRowFilter(RowFilter.regexFilter("(?i)"+textoFiltro.getText(), 0));
            }
        });// sortea la tabla
// sortea la tabla
        tablaSorteada = new TableRowSorter(modeloCasosAsignados);
        tablaCasosAsignados.setRowSorter(tablaSorteada);
    }//GEN-LAST:event_textoFiltroKeyTyped

    private void tablaCasosAsignadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCasosAsignadosMouseClicked
        //muestra lo que se selecciono de la tabla en las casillas
        int seleccion = tablaCasosAsignados.getSelectedRow();
        labelId.setText(String.valueOf(tablaCasosAsignados.getValueAt(seleccion, 0)));
        labelCaso.setText(String.valueOf(tablaCasosAsignados.getValueAt(seleccion, 1)));
    }//GEN-LAST:event_tablaCasosAsignadosMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        crearCaso();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonTerminarTrabajo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel labelCaso;
    private javax.swing.JLabel labelId;
    private javax.swing.JTable tablaAcciones;
    private javax.swing.JTable tablaCasosAsignados;
    private javax.swing.JTable tablaEtapas;
    private javax.swing.JTable tablaFechaCercana;
    private javax.swing.JTable tablaFechaCumplida;
    private javax.swing.JTextField textoFiltro;
    private javax.swing.JLabel textoUsuario;
    // End of variables declaration//GEN-END:variables
}
