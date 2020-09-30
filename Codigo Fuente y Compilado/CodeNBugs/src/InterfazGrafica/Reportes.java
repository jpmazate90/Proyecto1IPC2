
package InterfazGrafica;

import Logica.HTML;
import Logica.MostrarInformacion;
import Logica.Sesion;
import Logica.TablaModelo;
import Logica.ValidacionProyecto;
import java.io.File;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Reportes extends javax.swing.JInternalFrame {
    // crea la tablas tablas el objeto
    private TablaModelo modeloProyecto;
    private TablaModelo modeloProyecto2;
    private TablaModelo modeloCasos;
    private TablaModelo modeloEtapas;
    private TablaModelo modeloAcciones;
    private TablaModelo modeloUsuarios;
    private TablaModelo modeloDineroProyecto;
    private TablaModelo modeloDineroDesarrollador;
    private TablaModelo modeloDineroTipo;// crea el modelo de tabla
    private String [] reportes;
    private MostrarInformacion informacion;
    private ValidacionProyecto proyecto;
    private Connection conexion;
    private String usuario;// crea los objetos a utilizar
    private String usuarioEscogido;
// constructor de la clase 
    public Reportes(Connection conexion,String usuario) {
        initComponents();
        this.usuario=usuario;
        modeloProyecto=new TablaModelo();// crea el modelo
        modeloProyecto2=new TablaModelo();// crea el modelo
        modeloDineroProyecto= new TablaModelo();// crea el modelo
        modeloCasos=new TablaModelo();// crea el modelo
        modeloEtapas=new TablaModelo();// crea el modelo
        modeloAcciones=new TablaModelo();// crea el modelo
        modeloUsuarios=new TablaModelo();// crea el modelo
        modeloDineroDesarrollador = new TablaModelo();// crea el modelo
        modeloDineroTipo = new TablaModelo();// crea el modelo
        this.conexion=conexion;// crea el objeto 
        informacion = new MostrarInformacion(conexion);
        proyecto = new ValidacionProyecto(conexion);
        agregarModelos();
        asignarReportes();
        ocultarFiltros();
    }
    // verifica el reporte que se utilizara
    public void irReporte(){// agarra el valor del combo box
        String reporte =(String)comboReportes.getSelectedItem();
        if(reporte.equals(reportes[0])){// entra al reporte 0
            reporteProyectoActivo();
            mostrarBotonTabla();
            
        }if(reporte.equals(reportes[1])){// entra al reporte 1
            reporteDineroHoraProyecto();
            mostrarBotonTabla();
            
        }if(reporte.equals(reportes[2])){// entra al reporte 2
            reporteDineroHoraDesarrollador();
            mostrarBotonTabla();
            
        }if(reporte.equals(reportes[3])){// entra al reporte 3
            reporteDineroHoraTipo();
            mostrarBotonTabla();
            
        }if(reporte.equals(reportes[4])){// entra al reporte 4
            
        }if(reporte.equals(reportes[5])){// entra al reporte 5
            reporteDesarrollador();
            mostrarBotonTabla();
        }if(reporte.equals(reportes[6])){// entra al reporte 6
            reporteProyectoActivo();
            mostrarBotonTabla();
        }if(reporte.equals(reportes[7])){// entra al reporte 7
            reporteDesarrolladorMasCasos();
            mostrarBotonTabla();
        }if(reporte.equals(reportes[8])){// entra al reporte 8
            reporteDesarrolladorMasDinero();
            mostrarBotonTabla();
        }if(reporte.equals(reportes[9])){// entra al reporte 9
            reporteProyectoMasCasos();
            mostrarBotonTabla();
        }if(reporte.equals(reportes[10])){// entra al reporte 10
            reporteProyectoMasCasosCancelados();
            mostrarBotonTabla();
        }if(reporte.equals(reportes[11])){// entra al reporte 11
            reporteCasosProyecto();
            mostrarBotonTabla();
        }if(reporte.equals(reportes[12])){// entra al reporte 12
            reporteDesarrolladorEnCasos();
            usuarioEscogido=textoUsuario.getText();
            mostrarBotonTabla();
        }if(reporte.equals(reportes[13])){// entra al reporte 13
            reporteTipoEnCasos();
            mostrarBotonTabla();
        }
    }
    // muestra el boton de exportacion html
    public void mostrarBotonTabla(){
        tabla1.setVisible(true);
        botonExportar.setVisible(true);
    }// dependiendo del reporte escoge uno u otro
    public void exportarHTML(){
        File archivo = HTML.usarFileChooser();
        if(archivo.getName().equals("null.html")==false){
            String reporte =(String)comboReportes.getSelectedItem();
        if(reporte.equals(reportes[0])){// entra al reporte 0
            HTML.generarTitulo(archivo, "REPORTE PROYECTO ACTIVO");
            HTML.generarReporte0(archivo, (DefaultTableModel)tabla1.getModel(),usuario);
        }if(reporte.equals(reportes[1])){// entra al reporte 1
            HTML.generarTitulo(archivo, "REPORTE DINERO Y HORAS DE RESOLUCION CASOS POR UN PROYECTO");
            HTML.generarReporte1(archivo,(DefaultTableModel)tabla1.getModel() , usuario, labelTotal.getText());
        }if(reporte.equals(reportes[2])){// entra al reporte 2
            HTML.generarTitulo(archivo, "REPORTE DINERO Y HORAS DE RESOLUCION CASOS POR UN DESARROLLADOR");
            HTML.generarReporte2(archivo,(DefaultTableModel)tabla1.getModel(),usuario, labelTotal.getText() );
        }if(reporte.equals(reportes[3])){// entra al reporte 3
            HTML.generarTitulo(archivo, "REPORTE DINERO Y HORAS DE RESOLUCION CASOS POR UN TIPO");
            HTML.generarReporte3(archivo, (DefaultTableModel)tabla1.getModel(), usuario, labelTotal.getText());
        }if(reporte.equals(reportes[4])){// entra al reporte 4
            
        }if(reporte.equals(reportes[5])){// entra al reporte 5
            HTML.generarTitulo(archivo, "REPORTE DESARROLLADOR");
            HTML.generarReporte5(archivo, (DefaultTableModel)tabla1.getModel(), usuario,labelTotal.getText());
        }if(reporte.equals(reportes[6])){// entra al reporte 6
            HTML.generarTitulo(archivo, "REPORTE DE UN PROYECTO");
            HTML.generarReporte0(archivo, (DefaultTableModel)tabla1.getModel(),usuario);
        }if(reporte.equals(reportes[7])){// entra al reporte 7
            HTML.generarTitulo(archivo, "REPORTE DESARROLLADOR QUE HA PARTICIPADO EN MAS CASOS");
            HTML.generarReporte7y8(archivo,(DefaultTableModel)tabla1.getModel(), usuario,labelTotal.getText());
        }if(reporte.equals(reportes[8])){// entra al reporte 8
            HTML.generarTitulo(archivo, "REPORTE QUE SE HA INVERTIDO MAS DINERO");
            HTML.generarReporte7y8(archivo,(DefaultTableModel)tabla1.getModel(), usuario,labelTotal.getText());
            
        }if(reporte.equals(reportes[9])){// entra al reporte 9
            HTML.generarTitulo(archivo, "REPORTE PROYECTO QUE TIENE MAS CASOS FINALIZADOS");
            HTML.generarReporte9EnAdelante(archivo,(DefaultTableModel)tabla1.getModel(),usuario,2, "MAS CASOS FINALIZADOS");
        }if(reporte.equals(reportes[10])){// entra al reporte 10
            HTML.generarTitulo(archivo, "REPORTE PROYECTO QUE TIENE MAS CASOS CANCELADOS");
            HTML.generarReporte9EnAdelante(archivo,(DefaultTableModel)tabla1.getModel(),usuario,2, "MAS CASOS CANCELADOS");
        }if(reporte.equals(reportes[11])){// entra al reporte 11
            HTML.generarTitulo(archivo, "REPORTE DE CASOS DE UN PROYECTO");
            HTML.generarReporte9EnAdelante(archivo,(DefaultTableModel)tabla1.getModel(),usuario,2, "CASOS DE UN PROYECTO");
        }if(reporte.equals(reportes[12])){// entra al reporte 12
            HTML.generarTitulo(archivo, "REPORTE DE CASOS DE UN DESARROLLADOR");
            HTML.generarReporte12(archivo,(DefaultTableModel)tabla1.getModel(),usuario,usuarioEscogido, "CASOS DE UN DESARROLLADOR");
        }if(reporte.equals(reportes[13])){// entra al reporte 13
            HTML.generarTitulo(archivo, "REPORTE DE CASOS DE UN TIPO");
            HTML.generarReporte9EnAdelante(archivo,(DefaultTableModel)tabla1.getModel(),usuario,6, "CASOS DE UN TIPO");
        }
        }else{// si hubo un error dice que lo hubo
            JOptionPane.showMessageDialog(null, "No se creo el archivo");
        }
    }
    // reporte del proyecto activo
    public void reporteProyectoActivo(){
        tabla1.setModel(modeloProyecto2);//asigna el modelo
        proyecto.borrarDatos(modeloProyecto2);// borra datos
        informacion.reporteProyectoActivo(modeloProyecto2, checkActivo.isSelected());
    }
    
    public void reporteDineroHoraProyecto(){
        tabla1.setModel(modeloDineroProyecto);//asigna el modelo
        proyecto.borrarDatos(modeloDineroProyecto);// borra datos
        informacion.reporteDineroHorasProyecto(modeloDineroProyecto, textoProyecto.getText());
        asignarTotal(4);
    }
    public void reporteDineroHoraDesarrollador(){
        tabla1.setModel(modeloDineroDesarrollador);//asigna el modelo
        proyecto.borrarDatos(modeloDineroDesarrollador);// borra datos
        informacion.reporteDineroHorasDesarrollador(modeloDineroDesarrollador, textoUsuario.getText());
        asignarTotal(5);
    }
    
    public void reporteDineroHoraTipo(){
        tabla1.setModel(modeloDineroTipo);//asigna el modelo
        proyecto.borrarDatos(modeloDineroTipo);// borra datos
        informacion.reporteDineroHorasTipo(modeloDineroTipo,textoTipoCaso.getText());
        asignarTotal(5);
    }
    public void reporteDesarrollador(){
        tabla1.setModel(modeloEtapas);//asigna el modelo
        proyecto.borrarDatos(modeloEtapas);// borra datos
        informacion.reporteDesarrollador(modeloEtapas, textoUsuario.getText());
        asignarTotal(8);
    }
    public void reporteDesarrolladorMasCasos(){
        tabla1.setModel(modeloDineroDesarrollador);//asigna el modelo
        proyecto.borrarDatos(modeloDineroDesarrollador);// borra datos
        informacion.reporteDesarrolladorMasCasos(modeloDineroDesarrollador);
        asignarTotal(5);
    }
    
    public void reporteDesarrolladorMasDinero(){
        tabla1.setModel(modeloDineroDesarrollador);//asigna el modelo
        proyecto.borrarDatos(modeloDineroDesarrollador);// borra datos
        informacion.reporteDesarrolladorMasDinero(modeloDineroDesarrollador);
        asignarTotal(5);
    }
    
    public void reporteProyectoMasCasos(){
        tabla1.setModel(modeloCasos);//asigna el modelo
        proyecto.borrarDatos(modeloCasos);// borra datos
        informacion.reporteProyectoMasCasosFinalizados(modeloCasos);
    }
    public void reporteProyectoMasCasosCancelados(){
        tabla1.setModel(modeloCasos);//asigna el modelo
        proyecto.borrarDatos(modeloCasos);// borra datos
        informacion.reporteProyectoMasCasosCancelados(modeloCasos);
    }
    public void reporteCasosProyecto(){
        tabla1.setModel(modeloCasos);//asigna el modelo
        proyecto.borrarDatos(modeloCasos);// borra datos
        informacion.reporteProyectoEnCasos(modeloCasos,textoProyecto.getText());
    }
    
    public void reporteDesarrolladorEnCasos(){
        tabla1.setModel(modeloCasos);//asigna el modelo
        proyecto.borrarDatos(modeloCasos);// borra datos
        informacion.reporteDesarrolladorEnCasos(modeloCasos, textoUsuario.getText());
    }
    
    public void reporteTipoEnCasos(){
        tabla1.setModel(modeloCasos);//asigna el modelo
        proyecto.borrarDatos(modeloCasos);// borra datos
        informacion.reporteTipoEnCasos(modeloCasos, textoTipoCaso.getText());
    }
    public void mostrarFiltros(){// muestra algunos filtros dependiendo de la comveniencia
        String reporte =(String)comboReportes.getSelectedItem();
        if(reporte.equals(reportes[0])){
            labelId.setVisible(false); checkActivo.setVisible(true);
            textoId.setVisible(false);
            labelProyecto.setVisible(false); textoProyecto.setVisible(false);
            labelFechaInicio.setVisible(false); labelFechaFin.setVisible(false);
            fechaMaxima.setVisible(false); fechaMinima.setVisible(false);
            labelUsuario.setVisible(false); textoUsuario.setVisible(false);
            labelTipoCaso.setVisible(false); textoTipoCaso.setVisible(false);
            labelTotal.setVisible(false); labelTotal1.setVisible(false);
            botonExportar.setVisible(false);
            tabla1.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia
        }
        if(reporte.equals(reportes[1])){
            labelId.setVisible(false); checkActivo.setVisible(false);
            textoId.setVisible(false);
            labelProyecto.setVisible(true); textoProyecto.setVisible(true);
            labelFechaInicio.setVisible(false); labelFechaFin.setVisible(false);
            fechaMaxima.setVisible(false); fechaMinima.setVisible(false);
            labelUsuario.setVisible(false); textoUsuario.setVisible(false);
            labelTipoCaso.setVisible(false); textoTipoCaso.setVisible(false);
            labelTotal.setVisible(false); labelTotal1.setVisible(false);
            botonExportar.setVisible(false);
            tabla1.setVisible(false);
        }if(reporte.equals(reportes[2])){// muestra algunos filtros dependiendo de la comveniencia
            labelId.setVisible(false); checkActivo.setVisible(false);
            textoId.setVisible(false);
            labelProyecto.setVisible(false); textoProyecto.setVisible(false);
            labelFechaInicio.setVisible(false); labelFechaFin.setVisible(false);
            fechaMaxima.setVisible(false); fechaMinima.setVisible(false);
            labelUsuario.setVisible(true); textoUsuario.setVisible(true);
            labelTipoCaso.setVisible(false); textoTipoCaso.setVisible(false);
            labelTotal.setVisible(false); labelTotal1.setVisible(false);
            botonExportar.setVisible(false);
            tabla1.setVisible(false);// muestra algunos filtros dependiendo de la comveniencia
        }if (reporte.equals(reportes[3])) {
            labelId.setVisible(false); checkActivo.setVisible(false);
            textoId.setVisible(false);
            labelProyecto.setVisible(false); textoProyecto.setVisible(false);
            labelFechaInicio.setVisible(false); labelFechaFin.setVisible(false);
            fechaMaxima.setVisible(false); fechaMinima.setVisible(false);
            labelUsuario.setVisible(false); textoUsuario.setVisible(false);
            labelTipoCaso.setVisible(true); textoTipoCaso.setVisible(true);
            labelTotal.setVisible(false); labelTotal1.setVisible(false);
            botonExportar.setVisible(false);
            tabla1.setVisible(false);
        }if(reporte.equals(reportes[4])){// muestra algunos filtros dependiendo de la comveniencia
            labelId.setVisible(false); checkActivo.setVisible(false);
            textoId.setVisible(false);
            labelProyecto.setVisible(false); textoProyecto.setVisible(false);
            labelFechaInicio.setVisible(true); labelFechaFin.setVisible(true);
            fechaMaxima.setVisible(true); fechaMinima.setVisible(true);
            labelUsuario.setVisible(false); textoUsuario.setVisible(false);
            labelTipoCaso.setVisible(false); textoTipoCaso.setVisible(false);
            labelTotal.setVisible(false); labelTotal1.setVisible(false);
            botonExportar.setVisible(false);
            tabla1.setVisible(false);
        }if(reporte.equals(reportes[5])){// muestra algunos filtros dependiendo de la comveniencia
            labelId.setVisible(false); checkActivo.setVisible(false);
            textoId.setVisible(false);
            labelProyecto.setVisible(false); textoProyecto.setVisible(false);
            labelFechaInicio.setVisible(false); labelFechaFin.setVisible(false);
            fechaMaxima.setVisible(false); fechaMinima.setVisible(false);
            labelUsuario.setVisible(true); textoUsuario.setVisible(true);
            labelTipoCaso.setVisible(false); textoTipoCaso.setVisible(false);
            labelTotal.setVisible(false); labelTotal1.setVisible(false);
            botonExportar.setVisible(false);
            tabla1.setVisible(false);
        }if(reporte.equals(reportes[6])){// muestra algunos filtros dependiendo de la comveniencia
            labelId.setVisible(false); checkActivo.setVisible(true);
            textoId.setVisible(false);
            labelProyecto.setVisible(false); textoProyecto.setVisible(false);
            labelFechaInicio.setVisible(false); labelFechaFin.setVisible(false);
            fechaMaxima.setVisible(false); fechaMinima.setVisible(false);
            labelUsuario.setVisible(false); textoUsuario.setVisible(false);
            labelTipoCaso.setVisible(false); textoTipoCaso.setVisible(false);
            labelTotal.setVisible(false); labelTotal1.setVisible(false);
            botonExportar.setVisible(false);
            tabla1.setVisible(false);
        }if(reporte.equals(reportes[7])){// muestra algunos filtros dependiendo de la comveniencia
            labelId.setVisible(false); checkActivo.setVisible(false);
            textoId.setVisible(false);
            labelProyecto.setVisible(false); textoProyecto.setVisible(false);
            labelFechaInicio.setVisible(false); labelFechaFin.setVisible(false);
            fechaMaxima.setVisible(false); fechaMinima.setVisible(false);
            labelUsuario.setVisible(false); textoUsuario.setVisible(false);
            labelTipoCaso.setVisible(false); textoTipoCaso.setVisible(false);
            labelTotal.setVisible(false); labelTotal1.setVisible(false);
            botonExportar.setVisible(false);
            tabla1.setVisible(false);
        }if(reporte.equals(reportes[8])){// muestra algunos filtros dependiendo de la comveniencia
            labelId.setVisible(false); checkActivo.setVisible(false);
            textoId.setVisible(false);
            labelProyecto.setVisible(false); textoProyecto.setVisible(false);
            labelFechaInicio.setVisible(false); labelFechaFin.setVisible(false);
            fechaMaxima.setVisible(false); fechaMinima.setVisible(false);
            labelUsuario.setVisible(false); textoUsuario.setVisible(false);
            labelTipoCaso.setVisible(false); textoTipoCaso.setVisible(false);
            labelTotal.setVisible(false); labelTotal1.setVisible(false);
            botonExportar.setVisible(false);
            tabla1.setVisible(false);
        }if(reporte.equals(reportes[9])){// muestra algunos filtros dependiendo de la comveniencia
            labelId.setVisible(false); checkActivo.setVisible(false);
            textoId.setVisible(false);
            labelProyecto.setVisible(false); textoProyecto.setVisible(false);
            labelFechaInicio.setVisible(false); labelFechaFin.setVisible(false);
            fechaMaxima.setVisible(false); fechaMinima.setVisible(false);
            labelUsuario.setVisible(false); textoUsuario.setVisible(false);
            labelTipoCaso.setVisible(false); textoTipoCaso.setVisible(false);
            labelTotal.setVisible(false); labelTotal1.setVisible(false);
            botonExportar.setVisible(false);
            tabla1.setVisible(false);
        }if(reporte.equals(reportes[10])){// muestra algunos filtros dependiendo de la comveniencia
            labelId.setVisible(false); checkActivo.setVisible(false);
            textoId.setVisible(false);
            labelProyecto.setVisible(false); textoProyecto.setVisible(false);
            labelFechaInicio.setVisible(false); labelFechaFin.setVisible(false);
            fechaMaxima.setVisible(false); fechaMinima.setVisible(false);
            labelUsuario.setVisible(false); textoUsuario.setVisible(false);
            labelTipoCaso.setVisible(false); textoTipoCaso.setVisible(false);
            labelTotal.setVisible(false); labelTotal1.setVisible(false);
            botonExportar.setVisible(false);
            tabla1.setVisible(false);
        }if(reporte.equals(reportes[11])){// muestra algunos filtros dependiendo de la comveniencia
            labelId.setVisible(false); checkActivo.setVisible(false);
            textoId.setVisible(false);
            labelProyecto.setVisible(true); textoProyecto.setVisible(true);
            labelFechaInicio.setVisible(false); labelFechaFin.setVisible(false);
            fechaMaxima.setVisible(false); fechaMinima.setVisible(false);
            labelUsuario.setVisible(false); textoUsuario.setVisible(false);
            labelTipoCaso.setVisible(false); textoTipoCaso.setVisible(false);
            labelTotal.setVisible(false); labelTotal1.setVisible(false);
            botonExportar.setVisible(false);
            tabla1.setVisible(false);
        }if(reporte.equals(reportes[12])){// muestra algunos filtros dependiendo de la comveniencia
            labelId.setVisible(false); checkActivo.setVisible(false);
            textoId.setVisible(false);
            labelProyecto.setVisible(false); textoProyecto.setVisible(false);
            labelFechaInicio.setVisible(false); labelFechaFin.setVisible(false);
            fechaMaxima.setVisible(false); fechaMinima.setVisible(false);
            labelUsuario.setVisible(true); textoUsuario.setVisible(true);
            labelTipoCaso.setVisible(false); textoTipoCaso.setVisible(false);
            labelTotal.setVisible(false); labelTotal1.setVisible(false);
            botonExportar.setVisible(false);
            tabla1.setVisible(false);
        }
        if(reporte.equals(reportes[13])){// muestra algunos filtros dependiendo de la comveniencia
            labelId.setVisible(false); checkActivo.setVisible(false);
            textoId.setVisible(false);
            labelProyecto.setVisible(false); textoProyecto.setVisible(false);
            labelFechaInicio.setVisible(false); labelFechaFin.setVisible(false);
            fechaMaxima.setVisible(false); fechaMinima.setVisible(false);
            labelUsuario.setVisible(false); textoUsuario.setVisible(false);
            labelTipoCaso.setVisible(true); textoTipoCaso.setVisible(true);
            labelTotal.setVisible(false); labelTotal1.setVisible(false);
            botonExportar.setVisible(false);
            tabla1.setVisible(false);
        }
    }
    // oculta los filtros que se utilizaron
    public void ocultarFiltros(){
            labelId.setVisible(false); checkActivo.setVisible(true);
            textoId.setVisible(false);
            labelProyecto.setVisible(false); textoProyecto.setVisible(false);
            labelFechaInicio.setVisible(false); labelFechaFin.setVisible(false);
            fechaMaxima.setVisible(false); fechaMinima.setVisible(false);
            labelUsuario.setVisible(false); textoUsuario.setVisible(false);
            labelTipoCaso.setVisible(false); textoTipoCaso.setVisible(false);
            labelTotal.setVisible(false); labelTotal1.setVisible(false);
            botonExportar.setVisible(false);
            tabla1.setVisible(false);
            
    }
    
    // asigna el arreglo de reportes
    public void asignarReportes(){
        this.reportes=Sesion.arregloReportes();
    }
    
    // asigna el total de los valores
    public void asignarTotal(int posicion){
    labelTotal.setVisible(true); labelTotal1.setVisible(true);
    int cantidad =tabla1.getRowCount();
    int total=0;// muestra el ciclo
    for (int i =0;i<cantidad;i++){
        total+=Integer.parseInt((String)tabla1.getValueAt(i,posicion));
    }
    labelTotal.setText(Integer.toString(total));
        
    }
    // agrega columna al modelo
    public void agregarModelos(){
        modeloProyecto.addColumn("ID"); modeloProyecto.addColumn("Nombre");modeloProyecto.addColumn("Activo"); modeloProyecto.addColumn("Administrador Proyecto");
        modeloProyecto.isCellEditable(0,0);// agrega columna al modelo
        modeloCasos.addColumn("ID"); modeloCasos.addColumn("Nombre");
        modeloCasos.addColumn("Id Proyecto"); modeloCasos.addColumn("Fecha Limite");
        modeloCasos.addColumn("Porcentaje Avance"); modeloCasos.addColumn("Activo");
        modeloCasos.addColumn("Tipo");// agrega columna al modelo
        modeloCasos.isCellEditable(0,0);// agrega columna al modelo
        modeloAcciones.addColumn("ID");modeloAcciones.addColumn("Nombre Usuario"); modeloAcciones.addColumn("ID Proyecto");
        modeloAcciones.addColumn("ID Caso"); modeloAcciones.addColumn("ID Etapa"); modeloAcciones.addColumn("Fase Proyecto");
        modeloAcciones.addColumn("Tipo Accion"); modeloAcciones.addColumn("Comentario"); modeloAcciones.addColumn("Aprobado");
        modeloAcciones.addColumn("Revisado");// agrega columna al modelo
        modeloAcciones.isCellEditable(0,0);
        modeloEtapas.addColumn("Id Etapa"); modeloEtapas.addColumn("Nombre Etapa"); modeloEtapas.addColumn("Desarrollador");
        modeloEtapas.addColumn("Activo");modeloEtapas.addColumn("Tiempo Horas"); modeloEtapas.addColumn("Aprobado"); modeloEtapas.addColumn("Fecha Limite"); modeloEtapas.addColumn("Id Caso");
        modeloEtapas.addColumn("Total");// agrega columna al modelo
        modeloEtapas.isCellEditable(0,0);
        modeloUsuarios.addColumn("Usuario"); modeloUsuarios.addColumn("Contraseña");modeloUsuarios.addColumn("Nombre");
        modeloUsuarios.addColumn("Apellido"); modeloUsuarios.addColumn("Dpi"); modeloUsuarios.addColumn("Fecha Ingreso");
        modeloUsuarios.addColumn("Dinero por hora"); modeloUsuarios.addColumn("Tipo Usuario");
        modeloProyecto2.addColumn("ID"); modeloProyecto2.addColumn("Nombre");modeloProyecto2.addColumn("Activo"); modeloProyecto2.addColumn("Administrador Proyecto");
        modeloProyecto2.addColumn("Cantidad Casos");// agrega columna al modelo
        modeloProyecto2.isCellEditable(0,0);// agrega columna al modelo
        modeloDineroProyecto.addColumn("ID Caso"); modeloDineroProyecto.addColumn("Nombre Caso");modeloDineroProyecto.addColumn("ID Etapa");
        modeloDineroProyecto.addColumn("Tiempo etapa"); modeloDineroProyecto.addColumn("Total");
        modeloDineroDesarrollador.addColumn("Caso ID"); modeloDineroDesarrollador.addColumn("Nombre Caso"); modeloDineroDesarrollador.addColumn("ID Etapa");
        modeloDineroDesarrollador.addColumn("Desarrollador A Cargo"); modeloDineroDesarrollador.addColumn("Tiempo En Horas"); modeloDineroDesarrollador.addColumn("Total");
        modeloDineroTipo.addColumn("ID Caso"); modeloDineroTipo.addColumn("Nombre Caso");modeloDineroTipo.addColumn("ID Etapa"); modeloDineroTipo.addColumn("Tipo De Caso");
        modeloDineroTipo.addColumn("Tiempo En Horas"); modeloDineroTipo.addColumn("Total");// agrega columna al modelo
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelReporte = new javax.swing.JLabel();
        comboReportes = new javax.swing.JComboBox<>();
        labelFechaInicio = new javax.swing.JLabel();
        checkActivo = new javax.swing.JCheckBox();
        labelId = new javax.swing.JLabel();
        textoId = new javax.swing.JTextField();
        labelFechaFin = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        textoUsuario = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        fechaMinima = new com.toedter.calendar.JDateChooser();
        fechaMaxima = new com.toedter.calendar.JDateChooser();
        labelTipoCaso = new javax.swing.JLabel();
        textoTipoCaso = new javax.swing.JTextField();
        labelProyecto = new javax.swing.JLabel();
        textoProyecto = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        labelTotal1 = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        botonExportar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("REPORTES");

        labelReporte.setText("Reporte:");

        comboReportes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Reporte de proyectos con cantidad de casos", "Reporte horas y dinero en casos de algun proyecto", "Reporte horas y dinero en casos de algun desarrollador", "Reporte horas y dinero en casos de algun tipo en especifico", "Reporte de desarrolladores", "Reporte de Proyectos", "Reporte de desarrollador que ha participado en mas casos", "Reporte de desarrollador al que mas se le ha pagado", "Reporte proyecto que tiene mas casos reportados y finalizados", "Reporte proyecto que tiene mas casos cancelados", "Reporte de casos de un proyecto", "Reporte de casos de un desarrollador", "Reporte de casos de un tipo" }));
        comboReportes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboReportesItemStateChanged(evt);
            }
        });
        comboReportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboReportesMouseClicked(evt);
            }
        });

        labelFechaInicio.setText("Fecha Inicio:");

        checkActivo.setText("Activo:");

        labelId.setText("ID Caso:");

        labelFechaFin.setText("Fecha Fin:");

        labelUsuario.setText("Nombre Usuario:");

        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla1);

        labelTipoCaso.setText("Tipo Caso:");

        labelProyecto.setText("ID Proyecto:");

        jButton1.setText("REPORTE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        labelTotal1.setText("Total");

        botonExportar.setText("EXPORTAR HTML");
        botonExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonExportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelReporte)
                                    .addComponent(labelFechaInicio)
                                    .addComponent(labelId)
                                    .addComponent(labelProyecto))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(fechaMinima, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textoId, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(29, 29, 29)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(checkActivo)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(labelFechaFin)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(fechaMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(180, 301, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textoProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(comboReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelUsuario)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelTipoCaso)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textoTipoCaso, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botonExportar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9))
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelReporte)
                    .addComponent(comboReportes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelFechaInicio)
                        .addComponent(labelFechaFin))
                    .addComponent(fechaMinima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelId)
                    .addComponent(textoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkActivo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelProyecto)
                    .addComponent(textoProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUsuario)
                    .addComponent(textoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTipoCaso)
                    .addComponent(textoTipoCaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelTotal1)
                        .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(botonExportar))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboReportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboReportesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_comboReportesMouseClicked

    private void comboReportesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboReportesItemStateChanged
        mostrarFiltros();
    }//GEN-LAST:event_comboReportesItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        irReporte();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void botonExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonExportarActionPerformed
        exportarHTML();
    }//GEN-LAST:event_botonExportarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonExportar;
    private javax.swing.JCheckBox checkActivo;
    private javax.swing.JComboBox<String> comboReportes;
    private com.toedter.calendar.JDateChooser fechaMaxima;
    private com.toedter.calendar.JDateChooser fechaMinima;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelFechaFin;
    private javax.swing.JLabel labelFechaInicio;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelProyecto;
    private javax.swing.JLabel labelReporte;
    private javax.swing.JLabel labelTipoCaso;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JLabel labelTotal1;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JTable tabla1;
    private javax.swing.JTextField textoId;
    private javax.swing.JTextField textoProyecto;
    private javax.swing.JTextField textoTipoCaso;
    private javax.swing.JTextField textoUsuario;
    // End of variables declaration//GEN-END:variables
}
