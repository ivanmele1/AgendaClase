package AgendaMVC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class AgendaDAO {
	
	public ArrayList<Identificadores> buscarResultadosConMatriz() {
		 
		  Conexion conn = new Conexion();
		  
		  ArrayList<Identificadores> miLista = new ArrayList<Identificadores>();
		  Identificadores estado;
		  try {
		   Statement st = conn.getConnection().createStatement();
		   ResultSet rs = st.executeQuery
				   
				   ("SELECT Identificador, Nombre, Apellidos, Modulo, Email FROM alumnos;");
		 
		   while (rs.next()) {   
			     
		    estado = new Identificadores();
		    
		    estado.setIdentificador(Integer.parseInt(rs.getString("Identificador")));
		    
		    estado.setNombre(rs.getString("Nombre"));
		    
		    estado.setApellidos(rs.getString("Apellidos"));
		    
		    estado.setModulo(rs.getString("Modulo"));
		    
		    estado.setEmail(rs.getString("Email"));
		    
		    miLista.add(estado);		    
		    
		   }
		   rs.close();
		   st.close();
		   conn.desconectar();
		 
		  } catch (SQLException e) {
		   System.out.println(e.getMessage());
		   JOptionPane.showMessageDialog(null, "Error al consultar", "Error",
		     JOptionPane.ERROR_MESSAGE);
		 
		  }
		  return miLista;
		 }
	
	

	
}
