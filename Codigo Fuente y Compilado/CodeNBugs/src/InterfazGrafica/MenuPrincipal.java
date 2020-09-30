
package InterfazGrafica;

import Logica.Sesion;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class MenuPrincipal extends javax.swing.JFrame {
    // valores de la clase
    private Connection conexion;
    private Sesion sesion;
// constructor de la clase
    public MenuPrincipal(Connection conexion) throws SQLException {
        initComponents();
        sesion = new Sesion(conexion,this);
        this.conexion=conexion;// muestra que se conecto
        JOptionPane.showMessageDialog(null, "conectado:" + conexion.getCatalog());
    }
    // agrega al frame el usuario
    public void agregarFrame(String tipoUsuario, String usuario){
        sesion.agregarFrame(tipoUsuario, usuario, this.escritorio);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fileMenu.setMnemonic('f');
        fileMenu.setText("Iniciar Sesion");

        jMenuItem1.setText("Inicio Sesion");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem1);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Cerrar Sesion");

        jMenuItem2.setText("Cerrar Sesion");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        editMenu.add(jMenuItem2);

        menuBar.add(editMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //crea el objeto
        InicioSesion inicio = new InicioSesion(conexion,this,this);
        this.escritorio.add(inicio);
        inicio.show();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        sesion.limpiar(escritorio);
    }//GEN-LAST:event_jMenuItem2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu editMenu;
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables

}
