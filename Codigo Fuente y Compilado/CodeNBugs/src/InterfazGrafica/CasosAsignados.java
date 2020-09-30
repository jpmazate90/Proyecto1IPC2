
package InterfazGrafica;

import Logica.MostrarInformacion;
import Logica.TablaModelo;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;


public class CasosAsignados extends javax.swing.JInternalFrame {
    // valores de la clase atributos
    private String caso;
    private String id;
    private MostrarInformacion informacion;
    private Connection conexion;
    private Frame frame;// valores de la clase atributos
    private TablaModelo modelo;
    private TableRowSorter tablaSorteada;// valores de la clase atributos
// constructor de la clase
    public CasosAsignados(Connection conexion,JFrame frame,String caso, String id) {
        this.conexion=conexion;
        this.frame=frame;
        initComponents();
        this.caso=caso;// inicializa los valroes
        this.id=id;
        informacion = new MostrarInformacion(conexion);
        asignarValores();
        asignarDatos();
    }

    public void asignarValores(){
        // agrega los valores a las tablas
        labelCaso.setText(caso);
        labelId.setText(id);
        modelo = new TablaModelo();// agrega los valores a las tablas
        modelo.addColumn("ID"); modelo.addColumn("Nombre"); modelo.addColumn("Desarrollador a Cargo");
        modelo.addColumn("Activo"); modelo.addColumn("Tiempo en Horas"); modelo.addColumn("Aprobado");
        modelo.addColumn("Fecha Limite"); modelo.addColumn("ID Caso"); modelo.addColumn("Total");
        modelo.isCellEditable(0,0);// agrega los valores a las tablas
        tablaCasos.setModel(modelo);// agrega los valores a las tablas
    }
    // asigna los datos
    public void asignarDatos(){
        informacion.mostrarInformacionCasos(modelo, this.id);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labelId = new javax.swing.JLabel();
        labelCaso = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCasos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        textoFiltro = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("CONSULTA CASOS ASIGNADOS ");

        jLabel1.setText("Caso:");

        jLabel2.setText("ID:");

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

        jLabel3.setText("ETAPA ID:");

        textoFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoFiltroKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoFiltro))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelId, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                            .addComponent(labelCaso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(104, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelId, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelCaso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(212, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textoFiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoFiltroKeyTyped
        
        // sortea la tabla
        textoFiltro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tablaSorteada.setRowFilter(RowFilter.regexFilter("(?i)"+textoFiltro.getText(), 0));
            }// sortea la tabla
        });
// sortea la tabla
        tablaSorteada = new TableRowSorter(modelo);
        tablaCasos.setRowSorter(tablaSorteada);
    }//GEN-LAST:event_textoFiltroKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCaso;
    private javax.swing.JLabel labelId;
    private javax.swing.JTable tablaCasos;
    private javax.swing.JTextField textoFiltro;
    // End of variables declaration//GEN-END:variables
}
