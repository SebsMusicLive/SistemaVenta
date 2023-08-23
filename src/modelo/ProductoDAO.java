/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
/**
 *
 * @author ASUS
 */
public class ProductoDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean registrarProductos(Producto pro){
        String sql = "INSERT INTO productos (codigo, nombre, proveedor, stock, precio) VALUES (?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getProveedor());
            ps.setInt(4, pro.getStock());
            ps.setDouble(5, pro.getPrecio());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public void consultarProveedor(JComboBox proveedor){
        String sql = "SELECT nombre FROM proveedor";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                proveedor.addItem(rs.getString("nombre"));
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List listarProductos(){
        List<Producto> listarPro = new ArrayList<>();
        
        String sql = "SELECT * FROM productos";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Producto pro = new Producto();
                pro.setId(rs.getInt("id"));
                pro.setCodigo(rs.getString("codigo"));
                pro.setNombre(rs.getString("nombre"));
                pro.setProveedor(rs.getString("proveedor"));
                pro.setStock(rs.getInt("stock"));
                pro.setPrecio(rs.getDouble("precio"));
                listarPro.add(pro); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listarPro;
    }

}
