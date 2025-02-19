package data;

import java.util.Scanner;

/**
 *
 * @author alber
 */

/*clase empresa donde almacenaremos el nombre de la empresa, y las 12 cotizaciones
que tendrá en el IBEX 35*/
public class Empresa {
    private String nombre;
    private double cotizaciones[];

    public Empresa(String nombre, double[] cotizaciones) {
        this.nombre = nombre;
        this.cotizaciones = cotizaciones;
    }
    //Metodos basicos para la clase empresa
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double[] getCotizaciones() {
        return cotizaciones;
    }

    public void setCotizaciones(double[] cotizaciones) {
        this.cotizaciones = cotizaciones;
    }

}
