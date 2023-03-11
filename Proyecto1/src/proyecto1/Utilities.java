package proyecto1;

import java.util.Scanner;
import java.util.List;
/**
 * @author Alfaro Fernández Azul
 * @author Arreola Robles Itzel
 * @author Núñez Luna Aranza Abril
 */

/**
 * La clase Uilities contiene metodos utiles en la ejecucion del programa.
 */
public class Utilities {

    /**
     * El metodo pausarPantalla se encarga de realizar una pasusa en la consola hasta que el usuario presione enter.
     */
    public static void pausarPantalla(){
        Scanner sc=new Scanner(System.in);
        String r="a";
        while(!r.equals("")){
            System.out.print("\nPresione enter para continuar ");
            r=sc.nextLine();
        }
    }

    /**
     * El metodo limpiarPantalla se encarga de limpiar el texto en la consola del usuario.
     */
    public static void limpiarPantalla(){
        try{
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        } catch(Exception e){}
    }

    /**
     * El metodo imprimirLista se encarga de imprimir una lista de alumnos.
     * @param list Este parametro representa la lista que se imprimira.
     */
    public static void imprimirLista(List<Alumno> list){
        int cont=0;
        for(Alumno i:list){
            cont++;
            System.out.println("Alumno "+cont+":");
            i.imprimirAlumno("  ");
        }
    }
}
