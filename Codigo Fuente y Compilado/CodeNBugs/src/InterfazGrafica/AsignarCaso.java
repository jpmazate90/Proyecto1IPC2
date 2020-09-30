
package InterfazGrafica;

import Logica.ValidacionCaso;
import Logica.ValidacionEtapa;
import Logica.ValidacionProyecto;
import java.awt.Frame;
import java.sql.Connection;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AsignarCaso extends javax.swing.JDialog {
    // valores de la calse
    private ValidacionCaso casos; 
    private ValidacionEtapa etapas;
    private ValidacionProyecto proyectos;
    private DefaultTableModel modeloProyectos;// valores de la calse
    private DefaultTableModel modeloCasos;
    private Connection conexion;
    private String usuario;
    private Frame frame;// valores de la calse

    
    // constructor de la clase
    public AsignarCaso(java.awt.Frame parent, boolean modal, Connection conexion,String usuario) {
        super(parent, modal);
        this.frame = parent;
        initComponents();
        this.conexion=conexion;
        this.usuario=usuario;// crea los objetos
        casos = new ValidacionCaso(conexion);// crea los objetos
        etapas = new ValidacionEtapa(conexion);// crea los objetos
        proyectos = new ValidacionProyecto(conexion);// crea los objetos
        modeloCasos = new DefaultTableModel();// crea los objetos
        modeloProyectos= new DefaultTableModel();// crea los objetos
        // crea la columnas del modelo
        modeloCasos.addColumn("Nombre"); modeloCasos.addColumn("Cantidad Etapas");
        modeloProyectos.addColumn("ID"); modeloProyectos.addColumn("Nombre");
        modeloProyectos.addColumn("Fecha Limite"); modeloProyectos.addColumn("Activo");
        modeloProyectos.addColumn("AdministradorProyecto");
        tablaCasos.setModel(modeloCasos);
        tablaProyectos.setModel(modeloProyectos);
        agregarDatos();
    }
    // agrega los valores a las tablas
    public void agregarDatos(){
        casos.asignarDatosTablaCasos(modeloCasos);
        casos.agregarProyectosTabla(modeloProyectos, this.usuario);
    }
// crea la etapa 
    public void crearEtapa(){
        String idStringCaso=null, idStringProyecto=null;
        int idCaso=0, idProyecto=0;
        boolean sePuede=false;
        boolean huboFalla=false;
        try{// agarra los valroes de la tabla
         int seleccionProyectos = tablaProyectos.getSelectedRow();
         int seleccionCasos = tablaCasos.getSelectedRow();
         idStringProyecto = (String)modeloProyectos.getValueAt(seleccionProyectos, 0);
         idStringCaso =(String)modeloCasos.getValueAt(seleccionCasos, 0);
        }catch(ArrayIndexOutOfBoundsException e){
            huboFalla=true;
            // si no hubo falla entra
        }if(huboFalla==false){
            idCaso = Integer.parseInt(idStringCaso);
            idProyecto = Integer.parseInt(idStringProyecto);
            
            // y valida los datos
        sePuede=etapas.validarDatosFecha(fechaLimite.getDate());
        if(sePuede==true){
//            casos.crearAsignacionCasos(idProyecto, idCaso, fechaLimite.getDate());
        }else{
            JOptionPane.showMessageDialog(null, "No se puede crear la etapa faltan datos");
        }
        }else{
            JOptionPane.showMessageDialog(null, "No se puede crear la etapa datos inconsistentes");
        }
    }
    // asigna los casos
    public void asignarCaso(){
        boolean huboFalla=false;
        String nombreCasoString=null;
        String idProyecto=null;
        int cantidadEtapas=0;
        AsignacionEtapa asignacion = null;
        try {// agarra los valors que va a utilizar
        int seleccionCaso = tablaCasos.getSelectedRow();
        nombreCasoString = (String)modeloCasos.getValueAt(seleccionCaso, 0); 
        int seleccionProyecto = tablaProyectos.getSelectedRow();
        idProyecto = (String)modeloProyectos.getValueAt(seleccionProyecto, 0);
        cantidadEtapas = Integer.parseInt((String)modeloCasos.getValueAt(seleccionCaso, 1));
        } catch (ArrayIndexOutOfBoundsException e) {
            huboFalla=true;
        }// si no hbueron fallas entra
        if(huboFalla==false){
            boolean activo=proyectos.verificarProyectoCaso(idProyecto);
            if(activo==true){// verifica si el proyecto esta activo
                boolean sePuede=casos.veriricarDatosCreacionCaso(textoCaso.getText(), fechaLimite.getDate(), idProyecto, nombreCasoString);
                if(sePuede==true){// verifica si el caso esta activo
                    casos.crearCasos(textoCaso.getText(), fechaLimite.getDate(), idProyecto, nombreCasoString);
                    int idCaso = casos.idCaso();
                    boolean primeraEtapa=true;// si es asi pide crear las etapas
                    for(int i=0; i<cantidadEtapas;i++){
                        CrearEtapa etapa = new CrearEtapa(frame, true, conexion, nombreCasoString,Integer.toString(idCaso));
                        etapa.setVisible(true);// crea las etapas segun el tipo que se utilizo
                        if(primeraEtapa==true){
                            int idEtapa=etapas.idEtapa();
                            String nombreEtapa = etapas.nombreEtapa();
                            asignacion = new AsignacionEtapa(frame, true, conexion,Integer.toString(idCaso),Integer.toString(idEtapa));
                            primeraEtapa=false;
                        }
                    }
                    try{// trata de ponerlo true
                        asignacion.setVisible(true);
                    }catch(NullPointerException e){
                    
                    }
                
                }else{//no se puede
                    JOptionPane.showMessageDialog(null,"No se puede crear el caso");
                }
            }else{//no se puede
                JOptionPane.showMessageDialog(null,"No se puede crear el caso porque el proyecto esta inactivo");
            }
        }else{//no se puede
            JOptionPane.showMessageDialog(null,"No se puede crear el caso faltan datos");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProyectos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCasos = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        fechaLimite = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        textoCaso = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("ASIGNACION DE CASO");

        jLabel2.setText("Proyecto:");

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
        jScrollPane1.setViewportView(tablaProyectos);

        jLabel3.setText("Tipo Caso:");

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
        jScrollPane2.setViewportView(tablaCasos);

        jLabel4.setText("Fecha Limite:");

        jButton1.setText("Asignar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Nombre Caso:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(314, 314, 314)
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(textoCaso, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(27, 27, 27)
                                        .addComponent(fechaLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(textoCaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fechaLimite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(32, 32, 32)
                        .addComponent(jButton1)))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        asignarCaso();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser fechaLimite;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaCasos;
    private javax.swing.JTable tablaProyectos;
    private javax.swing.JTextField textoCaso;
    // End of variables declaration//GEN-END:variables
}
