package umg.DAO;

import umg.modelo.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

public class PaqueteDAO {

    private static final String SELECT_BASE
            = "SELECT p.idPaquete, p.Descripcion, p.Destinatario, p.DireccionEnvio, "
            + "p.idCamionero, cm.Nombre AS NomCamionero, "
            + "p.idCiudad, ci.Nombre AS NomCiudad "
            + "FROM paquete p "
            + "INNER JOIN camionero cm ON p.idCamionero = cm.idCamionero "
            + "INNER JOIN ciudad ci ON p.idCiudad = ci.idCiudad ";

    public void insertar(Paquete p) throws SQLException {
        String sql = "INSERT INTO paquete(Descripcion, Destinatario, DireccionEnvio, idCamionero, idCiudad) VALUES (?,?,?,?,?)";
        try (Connection cn = ConexionDB.abrir();
                PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, p.getDescripcion());
            ps.setString(2, p.getDestinatario());
            ps.setString(3, p.getDireccionEnvio());
            ps.setInt(4, p.getCamionero().getIdCamionero());
            ps.setInt(5, p.getCiudad().getIdCiudad());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se almaceno ningun registro");
            }
        }
    }

    public void actualizar(Paquete p) throws SQLException {
        String sql = "UPDATE paquete SET Descripcion=?, Destinatario=?, DireccionEnvio=?, idCamionero=?, idCiudad=? WHERE idPaquete=?";
        try (Connection cn = ConexionDB.abrir();
                PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, p.getDescripcion());
            ps.setString(2, p.getDestinatario());
            ps.setString(3, p.getDireccionEnvio());
            ps.setInt(4, p.getCamionero().getIdCamionero());
            ps.setInt(5, p.getCiudad().getIdCiudad());
            ps.setInt(6, p.getIdPaquete());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo actualizar");
            }
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM paquete WHERE idPaquete=?";
        try (Connection cn = ConexionDB.abrir();
                PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo eliminar");
            }
        }
    }

    public List<Paquete> listar() throws SQLException {
        List<Paquete> lista = new ArrayList<>();
        try (Connection cn = ConexionDB.abrir();
                PreparedStatement ps = cn.prepareStatement(SELECT_BASE + "ORDER BY p.idPaquete");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(armar(rs));
            }
        }
        return lista;
    }

    public Paquete buscarPorId(int id) throws SQLException {
        try (Connection cn = ConexionDB.abrir();
                PreparedStatement ps = cn.prepareStatement(SELECT_BASE + "WHERE p.idPaquete=?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return armar(rs);
                }
            }
        }
        return null;
    }

    private Paquete armar(ResultSet rs) throws SQLException {
        Camionero cm = new Camionero();
        cm.setIdCamionero(rs.getInt("idCamionero"));
        cm.setNombre(rs.getString("NomCamionero"));

        Ciudad ci = new Ciudad(rs.getInt("idCiudad"), rs.getString("NomCiudad"));

        Paquete p = new Paquete();
        p.setIdPaquete(rs.getInt("idPaquete"));
        p.setDescripcion(rs.getString("Descripcion"));
        p.setDestinatario(rs.getString("Destinatario"));
        p.setDireccionEnvio(rs.getString("DireccionEnvio"));
        p.setCamionero(cm);
        p.setCiudad(ci);
        return p;
    }
}
