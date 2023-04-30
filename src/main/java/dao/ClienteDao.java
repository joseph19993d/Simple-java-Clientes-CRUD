 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mycompany.sistemadegestion.models.Cliente;
import com.mysql.cj.util.StringUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JOSEPH
 */
public class ClienteDao {

    public Connection conectar() {
    String baseDeDatos = "java";
    String usuario = "root";
    String pasword = "";
    String host = "localhost";
    String puerto = "3306";
    String driver = "com.mysql.jdbc.Driver";
    String conexionUrl = "jdbc:mysql://" + host + ":" + puerto + "/" + baseDeDatos + "?useSSL=false";
    
    Connection connection = null;
    List<Cliente> listado = new ArrayList<>();

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(conexionUrl, usuario, pasword);
           
        } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return connection;
    }

    
    public void agregar (Cliente cliente) {
        try {
            Connection connection = conectar();
            String sql= "INSERT INTO `clientes` (`id`, `nombre`, `apellido`, `telefono`, `email`) VALUES "
                    + "(NULL, '"+cliente.getNombre()+"', '"+cliente.getApellido()+"', '"+cliente.getTelefono()+"', '"+cliente.getEmail()+"');";
            Statement statament = connection.createStatement();
            statament.execute(sql);
        } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    

    public List<Cliente> listar() {
    List<Cliente> listado = new ArrayList<>();

        try {
            Connection connection = conectar();
            String sql= "SELECT * FROM `clientes` ";
            Statement statament = connection.createStatement();
            ResultSet resultado = statament.executeQuery(sql);
            
            while (resultado.next()){
                Cliente cliente = new Cliente();
                cliente.setNombre(resultado.getString("nombre"));
                cliente.setApellido(resultado.getString("apellido"));
                cliente.setTelefono(resultado.getString("telefono"));
                cliente.setEmail(resultado.getString("email"));
                cliente.setId(resultado.getString("id"));
                
                listado.add(cliente);
                
            }
        } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listado;
    }
    
    public void eliminar(String id) {

        try {
            Connection connection = conectar();
            String sql= "DELETE FROM clientes WHERE `clientes`.`id` = "+id;
            Statement statament = connection.createStatement();
            statament.execute(sql);
        } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void actualizar (Cliente cliente) {
        try {
            Connection connection = conectar();
            String sql= "UPDATE `clientes` SET `nombre` = '"+cliente.getNombre()+"', `apellido` = '"+cliente.getApellido()+"', `telefono` = '"+cliente.getTelefono()+"', `email` = '"+cliente.getEmail()+"' WHERE `clientes`.`id` = '"+cliente.getId()+"';";
            Statement statament = connection.createStatement();
            statament.execute(sql);
        } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void guardar(Cliente c) {
        if(StringUtils.isEmptyOrWhitespaceOnly(c.getId())){
            agregar(c);
            
        }else{
            System.out.println(""+c.getId());
            actualizar(c);
            
        }
    }
    
    
    
    
}
