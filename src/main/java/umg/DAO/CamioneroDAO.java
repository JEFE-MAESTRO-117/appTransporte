package umg.DAO;

import umg.modelo.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

public class CamioneroDAO {

    public void insertar(Camionero c) throws SQLException {
        String sql = "INSERT INTO camionero(Nombre, Telefono, Direccion, Salario, Poblacion) VALUES (?,?,?,?,?)";
        try (Connection cn = ConexionDB.abrir();
                PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getTelefono());
            ps.setString(3, c.getDireccion());
            ps.setBigDecimal(4, c.getSalario());
            ps.setString(5, c.getPoblacion());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se almaceno ningun registro");
            }
        }
    }

    public void actualizar(Camionero c) throws SQLException {
        String sql = "UPDATE camionero SET Nombre=?, Telefono=?, Direccion=?, Salario=?, Poblacion=? WHERE idCamionero=?";
        try (Connection cn = ConexionDB.abrir();
                PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getTelefono());
            ps.setString(3, c.getDireccion());
            ps.setBigDecimal(4, c.getSalario());
            ps.setString(5, c.getPoblacion());
            ps.setInt(6, c.getIdCamionero());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo actualizar");
            }
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM camionero WHERE idCamionero=?";
        try (Connection cn = ConexionDB.abrir();
                PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo eliminar");
            }
        }
    }

    public List<Camionero> listar() throws SQLException {
        List<Camionero> lista = new ArrayList<>();
        String sql = "SELECT * FROM camionero";
        try (Connection cn = ConexionDB.abrir();
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(armar(rs));
            }
        }
        return lista;
    }

    public Camionero buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM camionero WHERE idCamionero=?";
        try (Connection cn = ConexionDB.abrir();
                PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return armar(rs);
                }
            }
        }
        return null;
    }

    private Camionero armar(ResultSet rs) throws SQLException {
        Camionero c = new Camionero();
        c.setIdCamionero(rs.getInt("idCamionero"));
        c.setNombre(rs.getString("Nombre"));
        c.setTelefono(rs.getString("Telefono"));
        c.setDireccion(rs.getString("Direccion"));
        c.setSalario(rs.getBigDecimal("Salario"));
        c.setPoblacion(rs.getString("Poblacion"));
        return c;
    }
}
