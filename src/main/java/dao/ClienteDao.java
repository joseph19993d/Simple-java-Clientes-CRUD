/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mycompany.sistemadegestion.models.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JOSEPH
 */
public class ClienteDao {
    public void agregar (Cliente cliente) {
    String baseDeDatos = "java";
    String usuario = "root";
    String pasword = "";
    String host = "localhost";
    String puerto = "3306";
    String driver = "com.mysql.jdbc.Driver";
    String conexionUrl = "jdbc:mysql://" + host + ":" + puerto + "/" + baseDeDatos + "?useSSL=false";
    
    Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(conexionUrl, usuario, pasword);
            String sql= "INSERT INTO `clientes` (`id`, `nombre`, `apellido`, `telefono`, `email`) VALUES (NULL, 'Joseph', 'Rodelo', '3007111546', 'dasd@dasda.com');";
            Statement statament = connection.createStatement();
            statament.execute(sql);
        } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    
    
}
