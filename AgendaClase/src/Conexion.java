import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
			
	
	 		String db = "agenda"; // Nombre de BD.
	 		int port = 3306;
	 		String host = "localhost";
			String url  = String.format("jdbc:mysql://%s:%d/%s?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow", host, port, db);
			String user = "root";
			String password = "0110";
			Connection conn;	
	
	public Conexion() {
		
			try {
				// Carga el controlador
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println(" Controlador JDBC cargado");
	
				// Crea conexión
				conn = DriverManager.getConnection(url, user, password);
				if (conn != null) {
					System.out.println(" Conexión con la base de datos creada");
				}
			}
	
			catch (SQLException e) {
				System.out.println(e);
			} catch (ClassNotFoundException e) {
				System.out.println(e);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	
		public Connection getConnection(){
	       return conn;
	    }
	  
	    public void desconectar(){
	       conn = null;
	    }

}
