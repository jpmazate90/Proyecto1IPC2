
package codenbugs;

import InterfazGrafica.AreaAdministradorSistema;
import InterfazGrafica.MenuPrincipal;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CodeNBugs {

    public static void main(String[] args) {

        Connection connection = null;
		try {
			String user = "root";
			String password = "Suchi123";
			// La url incluye el esquema a usar, en este caso 'mysql'
			String urlConnection ="jdbc:mysql://localhost:3306/CODE_N_BUGS";
			//abrimos una coneccion a la DB usando una url, el usuario y password (SUSTITUIR PASSWORD)
			connection = DriverManager.getConnection(urlConnection,user,password);
			
                        
//Mostramos el nombre del esquema de base de datos, en este caso es mysql
                        MenuPrincipal menu = new MenuPrincipal(connection);
                        menu.setExtendedState(Frame.MAXIMIZED_BOTH);
                        menu.setVisible(true);
                }catch(SQLException e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,"No se pudo conectar a la base de datos");
                }
        
        
    }
    
}
