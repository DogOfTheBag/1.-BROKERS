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
    public void CLS(){
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
        if(!empresas.isEmpty()){
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
            Thread.sleep(1500);
            salir = true;
            return salir;
        }
    }
    /*está funcion se encarga de coger la empresa y asegurarse de que existe*/
    private void empresaGrafico(Scanner sc) throws InterruptedException {
        System.out.println("Introduce la empresa sobre la que quieras ver el grafico");
        System.out.println("");
        for (Empresa empresa : empresas) {
            System.out.println("Empresa: " + empresa.getNombre());
        }
        String nombreEmpresa = sc.nextLine();
        boolean encontrada = false;
        //hacemos un for each para escribir todas las empresas
        for (Empresa empresa : empresas) {
            /*Si la empresa introducida es igual a una de las introducidas antes, y si existe
            hace el grafico*/
            if(nombreEmpresa.equalsIgnoreCase(empresa.getNombre())){
                generarGrafico(empresa,sc);
                Thread.sleep(3000);
                encontrada = true;
            }
        }
        if (!encontrada)
            System.out.println("No se ha encontrado la empresa...");

    }
   /*Método que se encarga de generar los graficos. Te pide primero la cantidad de meses
    de los que quieres ver datos, y después lo representa. Explicaré el del año entero,
    los demás son iguales, cambiando solo la cantidad de meses que se muestra*/
    private void generarGrafico(Empresa empresa, Scanner sc) {
        System.out.println("Introduce que datos quieres ver: (3) meses, (6) meses, (12) meses");
        int formato = sc.nextInt();
        switch(formato){
            case 12:
                /*hacemos una matriz donde se haga el gráfico, las dimensiones en vertical
                se mantienen siempre las mismas, y en horizontal los meses seleccionados + 1 para el nombre de la empresa*/
                String[][] grafico = new String[12][13];
                //Ponemos espacios en blanco en todos los lados para que no salga nada null, por los huecos que no se rellenen después
                for (int j = 0; j < 12; j++) {
                    for (int i = 0; i < 13; i++) {
                        grafico[j][i] = " ";
                    }
                }
                /*Este for funciona de la siguiente forma:
                el limite dentro del for es 12 para que no se salga del limite marcado en el array, y que aparte no pase el valor (las cotizaciones de cada mes
                después, se pondrán astericos verticalmente con la j, donde va de abajo a arriba. 
                La dimension del array será 10 - j para señalizar que empieza desde la 10 (abajo) y va restando j para ir subiendo hasta el numero que sea*/
                for (int i = 0; i < 12; i++) {
                    double valores[] = empresa.getCotizaciones();
                    for (int j = 0; j < valores[i] && j < 12; j++) {
                        grafico[10 - j][i] = "*";
                    }
                } 
                //abajo pone los meses del año y el nombre de la empresa seleccionada
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
                // lo pintamos finalmente
                for (int j = 0; j < 12; j++) {
                    for (int i = 0; i < 13; i++) {
                        System.out.print(grafico[j][i] + " ");
                    }
                    System.out.println();
                }
                break;
            case 6:
                //igual, pero en vez de 12 meses 6, por lo que la segunda dimension será 6 meses más el nombre de la empresa
                String[][] grafico6 = new String[12][7]; 

                for (int j = 0; j < 12; j++) {
                    for (int i = 0; i < 7; i++) {
                        grafico6[j][i] = " ";
                    }
                }

                double valores[] = empresa.getCotizaciones();
                for (int i = 0; i < 6; i++) { 
                    for (int j = 0; j < valores[i] && j < 12; j++) {
                        grafico6[10 - j][i] = "*";
                    }
                }

                grafico6[11][0] = "E";
                grafico6[11][1] = "F";
                grafico6[11][2] = "M";
                grafico6[11][3] = "A";
                grafico6[11][4] = "M";
                grafico6[11][5] = "J";
                grafico6[11][6] = "Empresa: " + empresa.getNombre();

                for (int j = 0; j < 12; j++) {
                    for (int i = 0; i < 7; i++) {
                        System.out.print(grafico6[j][i] + " ");
                    }
                    System.out.println();
                }
                break;
            
            case 3:
                //segunda dimension 4, 3 meses mas el nombre de la empresa
                String[][] grafico3 = new String[12][4]; 
                for (int j = 0; j < 12; j++) {
                    for (int i = 0; i < 4; i++) {
                        grafico3[j][i] = " ";
                    }
                }
                double valores3[] = empresa.getCotizaciones();
                for (int i = 0; i < 3; i++) { 
                    for (int j = 0; j < valores3[i] && j < 12; j++) {
                        grafico3[10 - j][i] = "*";
                    }
                }
                grafico3[11][0] = "E";
                grafico3[11][1] = "F";
                grafico3[11][2] = "M";
                grafico3[11][3] = "Empresa: " + empresa.getNombre();

                for (int j = 0; j < 12; j++) {
                    for (int i = 0; i < 4; i++) {
                        System.out.print(grafico3[j][i] + " ");
                    }
                    System.out.println();
                }
            break;
        }
    }
        //metodo usado para poner 2 gráficos de 2 empresas al mismo tiempo
        //es como el metodo graficoEmpresa pero para 2
        private void compararEmpresas(Scanner sc) throws InterruptedException{
        System.out.println("Introduce las dos empresas que quieres comparar:");
        for (Empresa empresa : empresas) {
            System.out.println("Empresa: " + empresa.getNombre());
        }
        //cogemos las 2 empresas que quiere usar el usuario
        System.out.print("Primera empresa: ");
        String nombreEmpresa1 = sc.nextLine();
        System.out.print("Segunda empresa: ");
        String nombreEmpresa2 = sc.nextLine();
        //si pones una empresa que no está, directamente te corta el programa con una excepción
        Empresa empresa1 = null;
        Empresa empresa2 = null;
        //busca entre las que está y si la encuentra la asigna, si no no.
        for (Empresa empresa : empresas) {
            if (nombreEmpresa1.equalsIgnoreCase(empresa.getNombre())) {
                empresa1 = empresa;
            }
            if (nombreEmpresa2.equalsIgnoreCase(empresa.getNombre())) {
                empresa2 = empresa;
            }
        }
        //después usamos el metodo generarGráfico en ambos casos y ya
        System.out.println("Grafico de la primera empresa:");
        generarGrafico(empresa1,sc);
        Thread.sleep(3000);
        System.out.println("Grafico de la segunda empresa:");
        generarGrafico(empresa2,sc);
        Thread.sleep(3000);
    } 
}
