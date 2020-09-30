
package InterfazGrafica;

import Logica.Sesion;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CreacionUsuario extends javax.swing.JDialog {
    // valores de la calse
    private Sesion sesion;
    private Connection conexion;
    private JFrame frame;
// cnstructor de la clase
    public CreacionUsuario(java.awt.Frame parent, boolean modal, Connection conexion,JFrame frame) {
        super(parent, modal);
        initComponents();
        this.conexion=conexion;
        this.frame=frame;
        sesion = new Sesion(conexion,frame);
    }
// verifica si el usuario existe 
    public void verificarUsuario(){
        String datos[]={textoNombre.getText(),textoApellidos.getText(),textoUsuario.getText(), textoContraseña.getText(),textoDpi.getText(),textoDinero.getText(),(String)comboBoxTipo.getSelectedItem()};
        boolean estanLlenos=sesion.validarEspaciosUsuario(datos);
        if(estanLlenos==true){// si existe entonces agarra los valors
            boolean sePuedeCrear=sesion.validarUsuario(datos[2],datos[5],datos[6]);
            if(sePuedeCrear==true){// si todo cumple entra y crea el usuario
                sesion.crearUsuario(datos[0],datos[1],datos[2],datos[3],datos[4],datos[5],datos[6]);
            }else{// si hubo un problema lo muestra
                JOptionPane.showMessageDialog(null,"No se puede crear el usuario");
                
            }
            
        }else{// si hubo un problema lo muestra
            JOptionPane.showMessageDialog(null, "Hay casillas vacias, por favor llenalos correctamente");
        }
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        botonCrearUsuario = new javax.swing.JButton();
        textoNombre = new javax.swing.JTextField();
        textoApellidos = new javax.swing.JTextField();
        textoUsuario = new javax.swing.JTextField();
        textoContraseña = new javax.swing.JTextField();
        textoDpi = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        comboBoxTipo = new javax.swing.JComboBox<>();
        textoDinero = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("CREAR USUARIO");

        jLabel2.setText("NOMBRE:");

        jLabel3.setText("APELLIDOS:");

        jLabel4.setText("USUARIO:");

        jLabel5.setText("CONTRASEÑA:");

        jLabel6.setText("DPI:");

        jLabel7.setText("DINERO POR HORA:");

        jLabel8.setText("TIPO USUARIO:");

        jLabel9.setText("1=DESARROLLADOR, 2=ADMINISTRADOR DE PROYECTO, 3=ADMINISTRADOR SISTEMA");

        botonCrearUsuario.setText("CREAR USUARIO");
        botonCrearUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearUsuarioActionPerformed(evt);
            }
        });

        textoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoUsuarioActionPerformed(evt);
            }
        });

        jLabel10.setText("(No se puede Repetir)");

        comboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(85, 85, 85)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(comboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textoContraseña))
                        .addGap(444, 444, 444))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textoUsuario)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textoApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textoDpi, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addGap(282, 282, 282))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(52, 52, 52)
                                .addComponent(textoDinero, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9)
                            .addComponent(botonCrearUsuario)
                            .addComponent(jLabel4))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textoApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textoContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(textoDpi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(textoDinero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(comboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(botonCrearUsuario)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoUsuarioActionPerformed

    }//GEN-LAST:event_textoUsuarioActionPerformed

    private void botonCrearUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearUsuarioActionPerformed
        verificarUsuario();
    }//GEN-LAST:event_botonCrearUsuarioActionPerformed

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCrearUsuario;
    private javax.swing.JComboBox<String> comboBoxTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField textoApellidos;
    private javax.swing.JTextField textoContraseña;
    private javax.swing.JTextField textoDinero;
    private javax.swing.JTextField textoDpi;
    private javax.swing.JTextField textoNombre;
    private javax.swing.JTextField textoUsuario;
    // End of variables declaration//GEN-END:variables
}
