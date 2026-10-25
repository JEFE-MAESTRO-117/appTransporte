package umg.DAO;

import umg.modelo.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

public class CamionDAO {

    public void insertar(Camion c) throws SQLException {
        String sql = "INSERT INTO camion(NoPlaca, Modelo, Tipo, Potencia) VALUES (?,?,?,?)";
        try (Connection cn = ConexionDB.abrir();
                PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, c.getNoPlaca());
            ps.setString(2, c.getModelo());
            ps.setString(3, c.getTipo());
            ps.setBigDecimal(4, c.getPotencia());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se almaceno ningun registro");
            }
        }
    }

    public void actualizar(Camion c) throws SQLException {
        String sql = "UPDATE camion SET NoPlaca=?, Modelo=?, Tipo=?, Potencia=? WHERE idCamion=?";
        try (Connection cn = ConexionDB.abrir();
                PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, c.getNoPlaca());
            ps.setString(2, c.getModelo());
            ps.setString(3, c.getTipo());
            ps.setBigDecimal(4, c.getPotencia());
            ps.setInt(5, c.getIdCamion());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo actualizar");
            }
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM camion WHERE idCamion=?";
        try (Connection cn = ConexionDB.abrir();
                PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo eliminar");
            }
        }
    }

    public List<Camion> listar() throws SQLException {
        List<Camion> lista = new ArrayList<>();
        String sql = "SELECT * FROM camion";
        try (Connection cn = ConexionDB.abrir();
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(armar(rs));
            }
        }
        return lista;
    }

    public Camion buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM camion WHERE idCamion=?";
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

    private Camion armar(ResultSet rs) throws SQLException {
        Camion c = new Camion();
        c.setIdCamion(rs.getInt("idCamion"));
        c.setNoPlaca(rs.getString("NoPlaca"));
        c.setModelo(rs.getString("Modelo"));
        c.setTipo(rs.getString("Tipo"));
        c.setPotencia(rs.getBigDecimal("Potencia"));
        return c;
    }
}
