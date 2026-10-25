package umg.modelo;

public class CamioneroCamion {
    private int idCamioneroCamion;
    private Camionero camionero;
    private Camion camion;

    //metodo constructor
    public CamioneroCamion() {
    }

    public CamioneroCamion(int idCamioneroCamion, Camionero camionero, Camion camion) {
        this.idCamioneroCamion = idCamioneroCamion;
        this.camionero = camionero;
        this.camion = camion;
    }

    //encapsulado

    public int getIdCamioneroCamion() {
        return idCamioneroCamion;
    }

    public void setIdCamioneroCamion(int idCamioneroCamion) {
        this.idCamioneroCamion = idCamioneroCamion;
    }

    public Camionero getCamionero() {
        return camionero;
    }

    public void setCamionero(Camionero camionero) {
        this.camionero = camionero;
    }

    public Camion getCamion() {
        return camion;
    }

    public void setCamion(Camion camion) {
        this.camion = camion;
    }

    @Override
    public String toString() {
        return "CamioneroCamion{" + "idCamioneroCamion=" + idCamioneroCamion + '}';
    }
}
