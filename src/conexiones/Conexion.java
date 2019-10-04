
package conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    

public class Conexion {
    
    public Connection conexion;
    
    
    public Conexion(String nombre){
    
        try {
            //cargar driver
            Class.forName("com.mysql.jdbc.Driver");
            //Establecemos la conexion con la BD
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/"+nombre,"root","");

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error al crear la conexion SQL");
        }
    }
        
    
    
    
    
    
    
    
}
