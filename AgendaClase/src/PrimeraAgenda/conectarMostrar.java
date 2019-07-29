package PrimeraAgenda;
import java.sql.*;
import java.sql.DriverManager;
 
public class conectarMostrar {
    private static Connection conexion = null;
    private static String db = "agenda"; // Nombre de BD.
    private static int port = 3306;
    private static String host = "localhost";
    private static String user = "root"; // Usuario de BD.
    private static String password = "0110"; // Password de BD.
    // Driver para MySQL en este caso.
    private static String driver = "com.mysql.jdbc.Driver";
    // Ruta del servidor.
    private static  String url = String.format("jdbc:mysql://%s:%d/%s?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow", host, port, db);
 
    public static void main(String[] args) throws SQLException {
 
        System.out.println("INICIO DE EJECUCIÓN.");
        conectar();
        Statement st = conexion();
        
 
        // Se elimina la tabla "personal" en caso de existir.
        String cadena = "DROP TABLE IF EXISTS alumnos;";
        consultaActualiza(st, cadena);
 
        // Se crea la tabla "personal"
        cadena = "CREATE TABLE alumnos (`Identificador` INT(10) NOT NULL , `Nombre` varchar(50) NOT NULL, `Apellidos` varchar(50) NOT NULL, `Modulo` varchar(20) DEFAULT NULL, `Email` varchar(60) DEFAULT NULL, PRIMARY KEY (`Identificador`))";
        consultaActualiza(st, cadena);
 
        // Se crean datos de prueba para utilizarlos en la tabla "personal"
        cadena = "INSERT INTO alumnos (`Identificador`, `Nombre`, `Apellidos`, `Modulo`, `Email`) VALUES ('1', 'Andrea', 'Cañas Garabito', 'DAW', 'andrea@cañas.com'), ('2', 'Martín', 'Castro Pardo', 'DAM', 'martin@castro.com'), ('3', 'Adrian', 'Eiroa Otero', 'DAM', 'adrian@eiroa.com'), ('4', 'Miguel', 'Fernandez Sanchez', 'DAW', 'miguel@fernandez.com'),"
        		+ "('5', 'Manuel', 'Fraga Fernandez', 'DAM', 'manuel@fraga.com'), ('6', 'José', 'Garcia Castiñeira', 'DAW', 'jose@garcia.com'), ('7', 'Noelia', 'Justo Fernandez', 'DAW', 'noelia@justo.com'), ('8', 'Iván', 'Meléndez Barredo', 'DAM', 'ivan@melendez.com'), ('9', 'Hamilton', 'Mercado Cuellar', 'DAW', 'hamilton@mercado.com'),"
        		+ "('10', 'Richard Daniel', 'Oliva Denis', 'DAW', 'richard@oliva.com'), ('11', 'Juan', 'Pardiñas Barbara', 'DAM', 'juan@pardiñas.com'), ('12', 'Julian', 'Pollán Rivadulla', 'DAW', 'julian@pollan.com'), ('13', 'David', 'Portos Pose', 'DAM', 'david@portos.com'), ('14', 'Kevin', 'Quiroga Sánchez', 'DAM', 'kevin@quiroga.com'),"
        		+ "('15', 'Diego', 'Rocha Crespo', 'DAM', 'diego@rocha.com'), ('16', 'Diego', 'Roibás Veiga', 'DAM', 'diego@roibas.com'), ('17', 'Santiago', 'Seijas MArante', 'DAM', 'santiago@seijas.com'), ('18', 'Rut', 'Seoane Gonzalez', 'DAW', 'rut@seoane.com'), ('19', 'Ivan Ramon', 'Seoane Martinez', 'DAM', 'ivan@seoane.com'),"
        		+ "('20', 'Martin', 'Sieiro Gutierrez', 'DAW', 'martin@sieiro.com'), ('21', 'Francisco Manuel', 'Somoza Triñanes', 'DAW', 'francisco@somoza.com'), ('22', 'Vanesa', 'Torres Barca', 'DAM', 'vanesa@torres.com'), ('23', 'Jacobo', 'Vazquez Encina', 'DAW', 'jacobo@vazquez.com');";
        consultaActualiza(st, cadena);
 
        // Se sacan los datos de la tabla personal
        cadena = "SELECT * FROM alumnos;";
        ResultSet rs = consultaQuery(st, cadena);
        if (rs != null) {
            System.out.println("El listado de persona es el siguiente:");
 
            while (rs.next()) {
                System.out.println("  ID: " + rs.getObject("Identificador"));
                System.out.println("  Nombre completo: "
                        + rs.getObject("Nombre") + " "
                        + rs.getObject("Apellidos"));
 
                System.out.println("  Contacto: " + rs.getObject("Modulo")
                        + " " + rs.getObject("Email"));
 
                System.out.println("- ");
            }
            cerrar(rs);
        }
        cerrar(st);
        System.out.println("FIN DE EJECUCIÓN.");
    }
 
    /**
     * Método neecesario para conectarse al Driver y poder usar MySQL.
     */
    public static void conectar() {
        try {
            //Class.forName(driver);
            conexion = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Error: Imposible realizar la conexion a BD.");
            e.printStackTrace();
        }
    }
 
    /**
     * Método para establecer la conexión con la base de datos.
     *
     * @return
     */
    private static Statement conexion() {
        Statement st = null;
        try {
            st = conexion.createStatement();
        } catch (SQLException e) {
            System.out.println("Error: Conexión incorrecta.");
            e.printStackTrace();
        }
        return st;
    }
 
    /**
     * Método para realizar consultas del tipo: SELECT * FROM tabla WHERE..."
     *
     * @param st
     * @param cadena La consulta en concreto
     * @return
     */
    private static ResultSet consultaQuery(Statement st, String cadena) {
        ResultSet rs = null;
        try {
            rs = st.executeQuery(cadena);
        } catch (SQLException e) {
            System.out.println("Error con: " + cadena);
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }
 
    /**
     * Método para realizar consultas de actualización, creación o eliminación.
     *
     * @param st
     * @param cadena La consulta en concreto
     * @return
     */
    private static int consultaActualiza(Statement st, String cadena) {
        int rs = -1;
        try {
            rs = st.executeUpdate(cadena);
        } catch (SQLException e) {
            System.out.println("Error con: " + cadena);
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }
 
    /**
     * Método para cerrar la consula
     *
     * @param rs
     */
    private static void cerrar(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                System.out.print("Error: No es posible cerrar la consulta.");
            }
        }
    }
 
    /**
     * Método para cerrar la conexión.
     *
     * @param st
     */
    private static void cerrar(java.sql.Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (Exception e) {
                System.out.print("Error: No es posible cerrar la conexión.");
            }
        }
    }
}