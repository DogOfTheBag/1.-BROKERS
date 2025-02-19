package data;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author alber
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        boolean salir = false;
        Scanner sc = new Scanner(System.in);
        final int NUM_COTIZACIONES = 12;
        ArrayList<Empresa> empresas = new ArrayList<>();
        while(!salir){
            System.out.println("Introduce el nombre de la empresa(Escribe parar si no quieres introducir mas)");
            String nombre = sc.nextLine();
            if (nombre.equalsIgnoreCase("parar")){
                salir = true;
            } else {
                double cotizacion[] = new double[NUM_COTIZACIONES];
                for (int i = 0; i < NUM_COTIZACIONES ; i++) {
                    System.out.println("Introduce 12 valores decimales (,) de las cotizaciones anuales de la empresa (0-10), Num " + (i+1));
                    boolean entradaValida = false;
                    while(!entradaValida){
                    cotizacion[i] = sc.nextDouble();
                    if(cotizacionCorrecta(cotizacion[i]))
                        entradaValida = true;
                       else {
                        System.out.println("La entrada no es valida");
                        }
                    }
                }
                sc.nextLine();
                empresas.add(new Empresa(nombre,cotizacion));
            }
        }
       
        salir = false;
        Menu m = new Menu(empresas);
        while(!salir){
            m.vis();
            System.out.println("Introduce la eleccion deseada");
            char eleccion = sc.next().charAt(0);
            salir = m.gestionarMenu(eleccion, salir);
        }
    }
    public static boolean cotizacionCorrecta(double cotizacion){
        if (cotizacion >= 0 && cotizacion <= 10)
            return true;
            else
                return false;
    }
}
