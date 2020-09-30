
package InterfazGrafica;

import Logica.Sesion;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class InicioSesion extends javax.swing.JInternalFrame {
// valores de la cclase
    private Connection conexion;
    private Sesion sesion;
    private MenuPrincipal menu;
    private JFrame frame;
    // inicio de sesion
    public InicioSesion(Connection conexion, MenuPrincipal menu,JFrame frame) {
        initComponents();
        this.conexion=conexion;
        this.frame=frame;
        sesion = new Sesion(this.conexion,frame);
        this.menu= menu;
    }
    // valida si el usuario existe 
    public void validarUsuario(){
        String tipoUsuario=sesion.verificarUsuario(textoUsuario.getText(), textoContraseña.getText());
        if(tipoUsuario==null){
            JOptionPane.showMessageDialog(null,"Los datos no estan correctos");
        }else{// si es asi entonces agrega al frame
            menu.agregarFrame(tipoUsuario,textoUsuario.getText());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textoContraseña = new javax.swing.JPasswordField();
        botonInicioSesion = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        textoUsuario = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        jLabel1.setText("Usuario:");

        jLabel2.setText("Constraseña:");

        botonInicioSesion.setText("Inicio Sesion");
        botonInicioSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonInicioSesionActionPerformed(evt);
            }
        });

        jLabel4.setText("Ingrese su usuario y contraseña");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonInicioSesion)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textoContraseña)
                            .addComponent(textoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel4)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textoContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(botonInicioSesion)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonInicioSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonInicioSesionActionPerformed
        validarUsuario();
    }//GEN-LAST:event_botonInicioSesionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonInicioSesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField textoContraseña;
    private javax.swing.JTextField textoUsuario;
    // End of variables declaration//GEN-END:variables
}
