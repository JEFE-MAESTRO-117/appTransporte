package umg.DAO;

import umg.modelo.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

public class CamioneroCamionDAO {

    private static final String SELECT_BASE
            = "SELECT cc.idCamioneroCamion, "
            + "cc.idCamionero, cm.Nombre, "
            + "cc.idCamion, c.NoPlaca, c.Modelo "
            + "FROM camioneroCamion cc "
            + "INNER JOIN camionero cm ON cc.idCamionero = cm.idCamionero "
            + "INNER JOIN camion c ON cc.idCamion = c.idCamion ";

    public void insertar(CamioneroCamion cc) throws SQLException {
        String sql = "INSERT INTO camioneroCamion(idCamionero, idCamion) VALUES (?,?)";
        try (Connection cn = ConexionDB.abrir();
                PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, cc.getCamionero().getIdCamionero());
            ps.setInt(2, cc.getCamion().getIdCamion());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se almaceno ningun registro");
            }
        }
    }

    public void actualizar(CamioneroCamion cc) throws SQLException {
        String sql = "UPDATE camioneroCamion SET idCamionero=?, idCamion=? WHERE idCamioneroCamion=?";
        try (Connection cn = ConexionDB.abrir();
                PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, cc.getCamionero().getIdCamionero());
            ps.setInt(2, cc.getCamion().getIdCamion());
            ps.setInt(3, cc.getIdCamioneroCamion());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo actualizar");
            }
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM camioneroCamion WHERE idCamioneroCamion=?";
        try (Connection cn = ConexionDB.abrir();
                PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo eliminar");
            }
        }
    }

    public List<CamioneroCamion> listar() throws SQLException {
        List<CamioneroCamion> lista = new ArrayList<>();
        try (Connection cn = ConexionDB.abrir();
                PreparedStatement ps = cn.prepareStatement(SELECT_BASE + "ORDER BY cc.idCamioneroCamion");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(armar(rs));
            }
        }
        return lista;
    }

    public CamioneroCamion buscarPorId(int id) throws SQLException {
        try (Connection cn = ConexionDB.abrir();
                PreparedStatement ps = cn.prepareStatement(SELECT_BASE + "WHERE cc.idCamioneroCamion=?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return armar(rs);
                }
            }
        }
        return null;
    }

    private CamioneroCamion armar(ResultSet rs) throws SQLException {
        Camionero cm = new Camionero();
        cm.setIdCamionero(rs.getInt("idCamionero"));
        cm.setNombre(rs.getString("Nombre"));

        Camion c = new Camion();
        c.setIdCamion(rs.getInt("idCamion"));
        c.setNoPlaca(rs.getString("NoPlaca"));
        c.setModelo(rs.getString("Modelo"));

        CamioneroCamion cc = new CamioneroCamion();
        cc.setIdCamioneroCamion(rs.getInt("idCamioneroCamion"));
        cc.setCamionero(cm);
        cc.setCamion(c);
        return cc;
    }
}
