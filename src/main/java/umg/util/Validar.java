package umg.util;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Validaciones reutilizables para los formularios.
 * Si un dato es invalido lanzan IllegalArgumentException con el mensaje para el usuario.
 */
public class Validar {

    //campo obligatorio con longitud maxima
    public static String texto(String valor, String campo, int max) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo " + campo + " es obligatorio");
        }
        valor = valor.trim();
        if (valor.length() > max) {
            throw new IllegalArgumentException("El campo " + campo + " no puede tener mas de " + max + " caracteres");
        }
        return valor;
    }

    //solo letras, espacios y algunos signos de nombres
    public static String soloLetras(String valor, String campo, int max) {
        valor = texto(valor, campo, max);
        if (!valor.matches("[\\p{L} .'-]+")) {
            throw new IllegalArgumentException("El campo " + campo + " solo puede contener letras");
        }
        return valor;
    }

    //exactamente n digitos telefono = 8)
    public static String digitos(String valor, String campo, int cantidad) {
        valor = texto(valor, campo, 50);
        if (!valor.matches("\\d{" + cantidad + "}")) {
            throw new IllegalArgumentException("El campo " + campo + " debe tener exactamente " + cantidad + " digitos");
        }
        return valor;
    }

    //placa: letras, numeros y guion
    public static String placa(String valor) {
        valor = texto(valor, "No. de placa", 10).toUpperCase().replace(" ", "");
        if (!valor.matches("[A-Z0-9-]{5,10}")) {
            throw new IllegalArgumentException("La placa no es valida (ejemplo: P123ABC)");
        }
        return valor;
    }

    //numero decimal mayor que cero
    public static BigDecimal decimalPositivo(String valor, String campo) {
        valor = texto(valor, campo, 15);
        try {
            BigDecimal numero = new BigDecimal(valor);
            if (numero.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("El campo " + campo + " debe ser mayor que cero");
            }
            return numero;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El campo " + campo + " debe ser un numero valido");
        }
    }


    //obligatorio seleccionar un elemento de un combo
    public static void seleccion(Object elemento, String campo) {
        if (elemento == null) {
            throw new IllegalArgumentException("Debe seleccionar " + campo);
        }
    }

    //traduce los errores de MySQL a mensajes entendibles
    public static String mensajeSQL(SQLException e) {
        switch (e.getErrorCode()) {
            case 1062:
                return "Ya existe un registro con ese dato (valor duplicado)";
            case 1451:
                return "No se puede eliminar: el registro esta relacionado con otros datos";
            case 1452:
                return "El dato relacionado seleccionado no existe";
            default:
                return "Error de base de datos: " + e.getMessage();
        }
    }
}
