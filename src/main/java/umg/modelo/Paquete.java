package umg.modelo;

public class Paquete {
    private int idPaquete;
    private String descripcion;
    private String destinatario;
    private String direccionEnvio;
    private Camionero camionero;
    private Ciudad ciudad;

    //metodo constructor
    public Paquete() {
    }

    public Paquete(int idPaquete, String descripcion, String destinatario, String direccionEnvio, Camionero camionero, Ciudad ciudad) {
        this.idPaquete = idPaquete;
        this.descripcion = descripcion;
        this.destinatario = destinatario;
        this.direccionEnvio = direccionEnvio;
        this.camionero = camionero;
        this.ciudad = ciudad;
    }

    //encapsulado

    public int getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public Camionero getCamionero() {
        return camionero;
    }

    public void setCamionero(Camionero camionero) {
        this.camionero = camionero;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Paquete{" + "idPaquete=" + idPaquete + ", descripcion=" + descripcion + ", destinatario=" + destinatario + '}';
    }
}
