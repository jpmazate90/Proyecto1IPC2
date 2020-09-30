
package InterfazGrafica;

import Logica.ValidacionCaso;
import Logica.ValidacionEtapa;
import Logica.ValidacionProyecto;
import java.sql.Connection;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class AsignacionEtapa extends javax.swing.JDialog {

    // valore privaados de la clase
    private Connection conexion;
    private DefaultListModel modelo;
    private ValidacionEtapa etapa;
    private ValidacionProyecto proyecto;
    private ValidacionCaso caso;
    // constructor
    public AsignacionEtapa(java.awt.Frame parent, boolean modal,Connection conexion,String id,  String idEtapa) {
        super(parent, modal);
        initComponents();// inicializa los valores
        this.etapa = new ValidacionEtapa(conexion);
        proyecto = new ValidacionProyecto(conexion);
        this.caso = new ValidacionCaso(conexion);
        labelId.setText(id);// inicializa los valores
        labelIdEtapa.setText(idEtapa);
        this.conexion=conexion;// inicializa los valores
        modelo = new DefaultListModel();
        listaDesarrolladores.setModel(modelo);
        asignarDesarrolladores();// inicializa los valores
    }
    // asigna desarrollador
    public void asignarDesarrolladores(){
        caso.asignarDatosListaDesarrolladores(modelo);
    }// asigna etapa
    public void asignarEtapa(){
        Date fecha = fechaLimite.getDate();
        boolean noHayError=false;
        String desarrollador=null;
        try{// asigna etapa
            int seleccion = listaDesarrolladores.getSelectedIndex();
            desarrollador = (String)modelo.getElementAt(seleccion);
        }catch(ArrayIndexOutOfBoundsException e){
            noHayError=true;
        }
        if(noHayError==false){
        boolean sePuede = etapa.validarFechaDesarrollador(fecha, desarrollador);
        if(sePuede==true){// asigna etapa
            etapa.asignarDatosEtapa(labelId.getText(), labelIdEtapa.getText(), fechaLimite.getDate(), desarrollador);
            this.setVisible(false);
        }else{// no se puede
            JOptionPane.showMessageDialog(null,"Faltan datos no se puede crear");
        }
        }else{// no se puede
            JOptionPane.showMessageDialog(null,"Faltan datos no se puede crear");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fechaLimite = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaDesarrolladores = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        labelId = new javax.swing.JLabel();
        labelIdEtapa = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("ASIGNACION DATOS ETAPA");

        jLabel2.setText("Desarrollador A Cargo:");

        jLabel3.setText("Fecha Limite:");

        jLabel4.setText("Caso:");

        jLabel5.setText("Etapa:");

        jScrollPane1.setViewportView(listaDesarrolladores);

        jButton1.setText("Asignar Etapa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fechaLimite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(37, 37, 37)
                                .addComponent(labelId, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(labelIdEtapa, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(labelId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(labelIdEtapa, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fechaLimite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(128, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        asignarEtapa();
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
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelIdEtapa;
    private javax.swing.JList<String> listaDesarrolladores;
    // End of variables declaration//GEN-END:variables
}
