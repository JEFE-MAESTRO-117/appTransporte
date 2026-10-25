
package umg.DAO;
import umg.modelo.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

public class CiudadDAO {
    public void insertar(Ciudad ciudad)throws SQLException{
        String sql="INSERT INTO ciudad(Nombre) VALUES (?)";
        try(Connection cn=ConexionDB.abrir(); 
                PreparedStatement ps=cn.prepareStatement(sql)){
                ps.setString(1, ciudad.getNombre());
                int filas=ps.executeUpdate();
                if(filas==0) {
                       throw new SQLException("No se almaceno nigun registro");
                }
        }        
    }
    public void actualizar(Ciudad ciudad)throws SQLException{
        //crear la instruccion de sql para realizar la operacion de DB
        String sql="UPDATE ciudad SET Nombre=? WHERE idCiudad=?";
        try(Connection cn=ConexionDB.abrir(); 
                PreparedStatement ps=cn.prepareStatement(sql)){
                ps.setString(1, ciudad.getNombre());
                ps.setInt(2, ciudad.getIdCiudad());
                int filas=ps.executeUpdate();
                if(filas==0) {
                       throw new SQLException("No se pudo actualizar");
                }
        }        
    }
    
    public List<Ciudad> listar() throws SQLException {
        List<Ciudad> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM ciudad";
        
        try (Connection cn = ConexionDB.abrir();
                PreparedStatement ps = cn.prepareStatement (sql);
                ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()){
                
                Ciudad ciudad = new Ciudad();
                //asignamos los valores obtenidos de MySQL
                ciudad.setIdCiudad(rs.getInt("idCiudad"));
                ciudad.setNombre(rs.getString("Nombre"));
                
                lista.add(ciudad);
                
                
            }
            
        }      
        return lista;
    }

    public void eliminar(int idCiudad) throws SQLException {
        String sql="DELETE FROM ciudad WHERE idCiudad=?";
        try(Connection cn=ConexionDB.abrir();
                PreparedStatement ps=cn.prepareStatement(sql)){
                ps.setInt(1, idCiudad);
                int filas=ps.executeUpdate();
                if(filas==0) {
                       throw new SQLException("No se pudo eliminar");
                }
        }
    }

    public Ciudad buscarPorId(int id) throws SQLException {
        String sql="SELECT * FROM ciudad WHERE idCiudad=?";
        try(Connection cn=ConexionDB.abrir();
                PreparedStatement ps=cn.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs=ps.executeQuery()){
                if(rs.next()){
                    return new Ciudad(rs.getInt("idCiudad"), rs.getString("Nombre"));
                }
            }
        }
        return null;
    }
}
