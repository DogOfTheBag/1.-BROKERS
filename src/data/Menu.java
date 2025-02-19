package data;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author alber
 */
public class Menu {
   private ArrayList<Empresa> empresas;

    public Menu(ArrayList <Empresa> empresas) {
        this.empresas = empresas;
        
    }
   /*necesitaremos la lista de las empresas que hemos creado en el main, lo
    demás lo podemos crear aquí*/
 
    /*visualizar*/
    public void vis(){
        System.out.println("1. Ver grafico de 1 valor");
        System.out.println("2. Comparar 2 graficos de valores");
        System.out.println("s. Salir del programa");
    }
    
    /*pequeño metodo que "limpia" la pantalla de la consola*/
    private void CLS(){
        for (int i = 0; i < 50; i++) {
            System.out.println(" ");
            
        }
    }
    /*switch con las elecciones del usuario, uso thread.sleep para parar los mensajes
    y que se puedan leer bien*/
    public boolean gestionarMenu(char eleccion, boolean salir) throws InterruptedException{
        Scanner sc = new Scanner(System.in);
        //convierto la eleccion a minuscula para que si el user pone S lo detecte como s y salga
        eleccion = Character.toLowerCase(eleccion);
        if(empresas.size() > 1){
            switch(eleccion){
                //el caso 1 hace el gráfico de una empresa
                case '1':
                    empresaGrafico(sc);
                    return salir;
                 //el caso 2 hace graficos de 2 empresas (o no si solo tienes una registrada)   
                case '2':
                    /*Debido a que estoy usando listas en vez de arrays, puedo usar la función
                    .size para comprobar el tamaño de la lista, en vez de .length*/
                    if(empresas.size() > 1)
                        compararEmpresas(sc);
                    else{
                        System.out.println("Solo tienes una empresa registrada...");
                        Thread.sleep(1500);
                    }
                    return salir;
                //sale del programa
                case 's':
                    System.out.println("Que tengas un buen dia!");
                    salir = true;
                    return salir;
                //caso predeterminado en caso de que no se seleccione ninguna 
                default:
                    System.out.println("La introduccion introducida no es valida");
                    Thread.sleep(1000);
                    CLS();
                    return salir;
            }            
        }else{
            //en caso de no meter empresa te saldrá esto si o si
            System.out.println("No ha introducido ninguna empresa, saliendo del programa");
            salir = true;
            return salir;
        }
    }
    /*está funcion se encargará de hacer un gráfico con los valores insertados por el user*/
    private void empresaGrafico(Scanner sc) throws InterruptedException {
     System.out.println("Introduce la empresa sobre la que quieras ver el grafico");
                System.out.println("");
                for (Empresa empresa : empresas) {
                    System.out.println("Empresa: " + empresa.getNombre());
                }
                String nombreEmpresa = sc.nextLine();
                boolean encontrada = false;
                
                for (Empresa empresa : empresas) {
                    if(nombreEmpresa.equalsIgnoreCase(empresa.getNombre())){
                        generarGrafico(empresa);
                         Thread.sleep(3000);
                        //TENGO QUE HACER LA FUNCION HACER GRAFICO
                        encontrada = true;
                    }
                }
                if (!encontrada)
                    System.out.println("No se ha encontrado la empresa...");
                
    }
    private void compararEmpresas(Scanner sc) throws InterruptedException{
        System.out.println("Introduce las dos empresas que quieres comparar:");
        for (Empresa empresa : empresas) {
            System.out.println("Empresa: " + empresa.getNombre());
        }

        System.out.print("Primera empresa: ");
        String nombreEmpresa1 = sc.nextLine();
        System.out.print("Segunda empresa: ");
        String nombreEmpresa2 = sc.nextLine();

        Empresa empresa1 = null;
        Empresa empresa2 = null;
        
        for (Empresa empresa : empresas) {
            if (nombreEmpresa1.equalsIgnoreCase(empresa.getNombre())) {
                empresa1 = empresa;
            }
            if (nombreEmpresa2.equalsIgnoreCase(empresa.getNombre())) {
                empresa2 = empresa;
            }
        }
        System.out.println("Grafico de la primera empresa:");
        generarGrafico(empresa1);
        Thread.sleep(3000);
        System.out.println("Grafico de la segunda empresa:");
        generarGrafico(empresa2);
        Thread.sleep(3000);
    }
    //TENGO QUE METERLE QUE HAGA GRAFICOS DE 3 Y 6 MESES
    private void generarGrafico(Empresa empresa) {
        String[][] grafico = new String[12][13];
        
        for (int j = 0; j < 12; j++) {
            for (int i = 0; i < 13; i++) {
                grafico[j][i] = " ";
            }
        }
        for (int i = 0; i < 12; i++) {
            double valores[] = empresa.getCotizaciones();
            for (int j = 0; j < valores[i] && j < 12; j++) {
                grafico[10 - j][i] = "*";
            }
        }    
            grafico[11][0] = "E";
            grafico[11][1] = "F";
            grafico[11][2] = "M";
            grafico[11][3] = "A";
            grafico[11][4] = "M";
            grafico[11][5] = "J";
            grafico[11][6] = "J";
            grafico[11][7] = "A";
            grafico[11][8] = "S";
            grafico[11][9] = "O";
            grafico[11][10] = "N";
            grafico[11][11] = "D";
            grafico[11][12] = "Empresa: " + empresa.getNombre();
        
        for (int j = 0; j < 12; j++) {
            for (int i = 0; i < 13; i++) {
                System.out.print(grafico[j][i] + " ");
            }
            System.out.println();
        }
    }
}
