
package InterfazGrafica;

import Logica.ValidacionProyecto;
import java.sql.Connection;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class CreacionProyecto extends javax.swing.JDialog {
    
    // valroes privados de la clase
    private ValidacionProyecto proyecto;
    private DefaultListModel modeloLista;
    private Connection conexion;
    
// constructor de la clase
    public CreacionProyecto(java.awt.Frame parent, boolean modal, Connection conexion) {
        super(parent, modal);// inicializa los componentes
        this.conexion=conexion;
        initComponents();
        proyecto = new ValidacionProyecto(conexion);
        modeloLista = new DefaultListModel();// empieza el modelo de la tabla 
        listaAdministradoresProyecto.setModel(modeloLista);
        agregarDatosLista();// agrega los daots
        
    }
    // agrega los datos
    public void agregarDatosLista(){
        proyecto.agregarDatosLista(modeloLista);
    }
    // crea un proyecto
    public void crearProyecto(){
        try {// crea un proyeccto nuevo
            Date fechaActual = new Date();
        int seleccion = listaAdministradoresProyecto.getSelectedIndex();// agaraa el admin que se escogio
        String usuarioEscogido = (String) modeloLista.getElementAt(seleccion);// agarra el usuairo
        boolean sePuedeCrear = proyecto.verificarCampos(textoNombre.getText(),fechaActual,usuarioEscogido);
        if(sePuedeCrear==true){// verifica si no falta ningun campo
            boolean seCreo=proyecto.crearProyecto(textoNombre.getText(), fechaActual, usuarioEscogido);
            if(seCreo==true){// faltaria ver si hay que agregarle un caso
                JOptionPane.showMessageDialog(null,"Se creo correctamente el proyecto");
            }
        }else{// nose puede crear
           JOptionPane.showMessageDialog(null,"No se pudo crear el proyecto");
        }// nose puede crear
        } catch (ArrayIndexOutOfBoundsException e) {// nose puede crear
            JOptionPane.showMessageDialog(null,"No asigno ningun administrador");
        }
        
//        Date fecha = fechaLimite.getDate();
//        long tiempo= fecha.getTime();
//        java.sql.Date fechaSql= new java.sql.Date(tiempo);
//        JOptionPane.showMessageDialog(null, fechaSql);
    }
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaAdministradoresProyecto = new javax.swing.JList<>();
        botonCrearProyecto = new javax.swing.JButton();
        textoNombre = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("CREACION PROYECTO");

        jLabel2.setText("Nombre:");

        jLabel5.setText("Administrador Proyecto:");

        jScrollPane1.setViewportView(listaAdministradoresProyecto);

        botonCrearProyecto.setText("CREAR PROYECTO");
        botonCrearProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearProyectoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(251, 251, 251)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(botonCrearProyecto))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(textoNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                    .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(botonCrearProyecto)
                .addGap(62, 62, 62))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCrearProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearProyectoActionPerformed
        crearProyecto();
    }//GEN-LAST:event_botonCrearProyectoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCrearProyecto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listaAdministradoresProyecto;
    private javax.swing.JTextField textoNombre;
    // End of variables declaration//GEN-END:variables
}
