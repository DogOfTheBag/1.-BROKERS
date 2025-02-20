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
        /*Creamos unas cuantas variables necesarias, scanner, y una lista de empresas
        con las que podremos usar metodos como add en un bucle posteriormente, que personalmente veo más sencillo*/
        boolean salir = false;
        Scanner sc = new Scanner(System.in);
        final int NUM_COTIZACIONES = 12;
        ArrayList<Empresa> empresas = new ArrayList<>();
        //usamos el booleano salir para cerrar el bucle de introducción de datos de la empresa, si escribe parar cierra
        while(!salir){
            System.out.println("Introduce el nombre de la empresa(Escribe parar si no quieres introducir mas)");
            String nombre = sc.nextLine();
            if (nombre.equalsIgnoreCase("parar")){
                salir = true;
            } else {
                /*Si no escribe parar, creamos un array de cotizaciones, donde guardaremos los 12 datos del IBEX 35 de la empresa*/
                double cotizacion[] = new double[NUM_COTIZACIONES];
                for (int i = 0; i < NUM_COTIZACIONES ; i++) {
                    /*Con una función que comprueba si el número se encuentra entre los que queremos, deja meter 12 datos double*/
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
                //pongo aquí un nextline para que pille el buffer del anterior scanner y no se salte cosas
                sc.nextLine();
                //con el nombre y las cotizaciones, añadimos una nueva empresa a la lista.
                empresas.add(new Empresa(nombre,cotizacion));
            }
        }
        /*Creamos un nuevo menu, que repetiremos hasta que el usuario nos indique que quiere salir*/
        salir = false;
        Menu m = new Menu(empresas);
        while(!salir){
            m.vis();
            System.out.println("Introduce la eleccion deseada");
            /*En el enunciado nos dicen que las opciones tienen que ser 1, 2 y s
            por lo que usaremos un char y lo compararemos después en la clase menu
            
            (usamos charAt(0) para indicar que cogemos el primer caracter de la String que nos pase
            ya que scanner no deja coger chars, solo Strings.*/
            char eleccion = sc.next().charAt(0);
            salir = m.gestionarMenu(eleccion, salir);
            m.CLS();
        }
        System.out.println("Que tengas un buen dia!");
    }
    //pequeño metodo que comprueba que el dato de la cotización está entre 0 y 10, si no no lo admite.
    public static boolean cotizacionCorrecta(double cotizacion){
        if (cotizacion >= 0 && cotizacion <= 10)
            return true;
        else
            return false;
    }
}
