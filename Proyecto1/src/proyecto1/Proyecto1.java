package proyecto1;

//import java.util.LinkedList;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
/**
 * @author Alfaro Fernández Azul
 * @author Arreola Robles Itzel
 * @author Núñez Luna Aranza Abril
 */

/**
 * La clase Proyecto1 es la clase principal del proyecto actual.
 */
public class Proyecto1 {

    /**
     * El metodo principal crea un menu para el usuario de forma que el usuario pueda interactuar con las clases creadas.
     * @param args Parametro estipulado para metodo main.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        Archivos arch=new Archivos();
        String[] resultado;
        List<Alumno> list;
        String op="si";
        String r;

        System.out.println("\t\t\t--- Algoritmos de Ordenamiento Externo ---");
        System.out.println("\nEquipo 8:\n> Alfaro Fern\u00e1ndez, Azul\n> Arreola Robles, Itzel\n> N\u00fa\u00f1ez Luna, Aranza Abril");
        System.out.println("\n\t\t\t\t\tBienvenido!\n\nAntes de empezar, asegurese de que su archivo esté en el formato correcto.");
        System.out.println("\tFormato: Nombre, Apellidos, No.Cuenta");
        do{
            System.out.print("\nInserte el nombre se su archivo sin extenci\u00f3n.\n--> ");
            r=sc.nextLine()+".txt";
            list= arch.leerAlumnos(r);
            r="no";
            if(list.isEmpty()){
                System.out.print("El archivo insertado no existe o se encuentra vac\u00edo. Desea buscar otro archivo? ");
                r=sc.nextLine();
                if(!r.toLowerCase().equals("si")||!r.equals("sí")||!r.equals("Sí")||!r.equals("SÍ")) op="no";
            }
        }while(r.toLowerCase().equals("si")||r.equals("sí")||r.equals("Sí")||r.equals("SÍ"));
        while((op.toLowerCase().equals("si")||op.equals("sí")||op.equals("Sí")||op.equals("SÍ"))&&!op.toLowerCase().equals("exit")&&!op.toLowerCase().equals("salir")){
            System.out.println("\nQu\u00e9 algoritmo desea ejecutar?");
            System.out.print("> Polifase\n> Mezcla equilibrada\n> Radix Externo (Solo para n\u00fameros de cuenta)\n> Exit para salir\n--> ");
            op=sc.nextLine();
            if(op.toLowerCase().equals("polifase")){
                Polifase poli=new Polifase();
                do{
                    System.out.println("\nDe acuerdo a que caracteristica quiere ordenar?");
                    System.out.print("> Nombre\n> Apellidos\n> Numero de cuenta\n> Exit para salir\n--> ");
                    r=sc.nextLine();
                    if(r.toLowerCase().equals("nombre")){
                        resultado=poli.Poli(list, list.size(),'n');
                        System.out.print("\nEl resultado del ordenamiento polifase por nombre esta en el archivo ");
                        System.out.println(resultado[1]+" en la carpeta "+resultado[0]+" del proyecto actual.");
                        System.out.println("Las iteraciones est\u00e1n especificadas en el archivo "+resultado[2]);
                        r="exit";
                    }else if(r.toLowerCase().equals("apellido")||r.toLowerCase().equals("apellidos")){
                        resultado=poli.Poli(list, list.size(),'a');
                        System.out.print("\nEl resultado del ordenamiento polifase por apellidos esta en el archivo ");
                        System.out.println(resultado[1]+" en la carpeta "+resultado[0]+" del proyecto actual.");
                        System.out.println("Las iteraciones est\u00e1n especificadas en el archivo "+resultado[2]);
                        r="exit";
                    }else if(r.toLowerCase().equals("numero")||r.toLowerCase().equals("numero de cuenta")){
                        resultado=poli.Poli(list, list.size(),'c');
                        System.out.print("\nEl resultado del ordenamiento polifase por numeros esta en el archivo ");
                        System.out.println(resultado[1]+" en la carpeta "+resultado[0]+" del proyecto actual.");
                        System.out.println("Las iteraciones est\u00e1n especificadas en el archivo "+resultado[2]);
                        r="exit";
                    }else if(!r.toLowerCase().equals("exit")&&!r.toLowerCase().equals("salir"))
                        System.out.println("Opci\u00f3n no v\u00e1lida");
                }while(!r.toLowerCase().equals("exit")&&!r.toLowerCase().equals("salir"));
            }else if(op.toLowerCase().equals("mezcla")||op.toLowerCase().equals("mezcla equilibrada")){
                MezclaEquilibrada me=new MezclaEquilibrada();
                do{
                    System.out.println("\nDe acuerdo a que caracteristica quiere ordenar?");
                    System.out.print("> Nombre\n> Apellidos\n> Numero de cuenta\n> Exit para salir\n--> ");
                    r=sc.nextLine();
                    if(r.toLowerCase().equals("nombre")){
                        resultado=me.mezcla(list, list.size(),'n');
                        System.out.print("\nEl resultado del ordenamiento mezcla por nombre esta en el archivo ");
                        System.out.println(resultado[1]+" en la carpeta "+resultado[0]+" del proyecto actual.");
                        System.out.println("Las iteraciones est\u00e1n especificadas en el archivo "+resultado[2]);
                        r="exit";
                    }else if(r.toLowerCase().equals("apellido")||r.toLowerCase().equals("apellidos")){
                        resultado=me.mezcla(list, list.size(),'a');
                        System.out.print("\nEl resultado del ordenamiento mezcla por apellidos esta en el archivo ");
                        System.out.println(resultado[1]+" en la carpeta "+resultado[0]+" del proyecto actual.");
                        System.out.println("Las iteraciones est\u00e1n especificadas en el archivo "+resultado[2]);
                        r="exit";
                    }else if(r.toLowerCase().equals("numero")||r.toLowerCase().equals("numero de cuenta")){
                        resultado=me.mezcla(list, list.size(),'c');
                        System.out.print("\nEl resultado del ordenamiento mezcla por numeros esta en el archivo ");
                        System.out.println(resultado[1]+" en la carpeta "+resultado[0]+" del proyecto actual.");
                        System.out.println("Las iteraciones est\u00e1n especificadas en el archivo "+resultado[2]);
                        r="exit";
                    }else if(!r.toLowerCase().equals("exit")&&!r.toLowerCase().equals("salir"))
                        System.out.println("Opci\u00f3n no v\u00e1lida");
                }while(!r.toLowerCase().equals("exit")&&!r.toLowerCase().equals("salir"));
            }else if(op.toLowerCase().equals("radix")||op.toLowerCase().equals("radix externo")){
                RadixExterno re=new RadixExterno();
                resultado=re.sort(list, list.size(), 6);
                System.out.print("\nEl resultado del ordenamiento radix esta en el archivo ");
                System.out.println(resultado[1]+" en la carpeta "+resultado[0]+" del proyecto actual.");
                System.out.println("Las iteraciones est\u00e1n especificadas en el archivo "+resultado[2]);
            }else if(!op.toLowerCase().equals("exit")&&!op.toLowerCase().equals("salir"))
                System.out.println("Error: Algoritmo no valido.");
            if(!op.toLowerCase().equals("exit")&&!op.toLowerCase().equals("salir")){
                System.out.print("Desea ejecutar otro algoritmo? ");
                op=sc.nextLine();
            }
        }
    }

}
