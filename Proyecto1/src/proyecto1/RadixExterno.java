package proyecto1;

import java.util.List;
/**
 * @author Alfaro Fernández Azul
 * @author Arreola Robles Itzel
 * @author Núñez Luna Aranza Abril
 */

/**
 * La clase RadixExterno es la que se encarga de la implementación del algoritmo de ordenamiento externo con radix.
 */
public class RadixExterno {

    /**
     * El metodo digito se encarga de obtener un digito del numero dado.
     * @param num Este parametro representa el numero del que se quiere obtener un digito.
     * @param n Este parametro representa un exponente de 10 el cual ayuda a la obtencion de un solo digito
     * @return Este metodo devuelve el digito obtenido.
     */
    int digito(int num, int n){
        int r=num/n;
        return((int)r%10);
    }

    /**
     * El metodo sort realiza las operaciones necesarias para el ordenamiento a partir de radix externo.
     * @param a Este parametro representa la lista que se ordenara en los archivos.
     * @param size Este parametro representa el tamaño de la lista que se ordena.
     * @param digits Este parametro representa cuantos digitos se toman a consideracion en el ordenamiento
     * @return Este metodo regresa los nombres del archivo final, el archivo de iteraciones y la carpeta final.
     */
    String[] sort(List<Alumno> a, int size, int digits){
        Archivos arch=new Archivos();
        Utilities u=new Utilities();
        String names[]=new String[3];
        names[0]="RadixExterno";
        names[1]="RadixFinal.txt";
        names[2]="RadixIteraciones.txt";
        List<Alumno> list,aux;

        int n,i,it=0;
        int cantidad[]=new int[10];
        String files[]=new String[10];
        for(int j=0;j<10;j++)
            files[j]=("RadixAux"+j+".txt");

        arch.escribirAlumnos(names[1],a,'w');
        System.out.println("\nIteraciones Radix Externo:");
        /** El ciclo se repite tantas veces como digitos se tengan. */
        for(int exp=1; exp> 0 && exp<=Math.pow(10,digits-1); exp*=10){
            list=arch.leerAlumnos(names[1]);
            it++;

            /** Se copian los datos al archivo auxiliar correspondiente según el digito obtenido. */
            for(int j=0;j<cantidad.length;j++)
                cantidad[j]=0;

            for(int j=0;j<list.size();j++){
                n=digito(list.get(j).getNoCuenta(),exp);
                if(cantidad[n]==0) {
                    arch.escribirAlumno(files[n], list.get(j),'w');
                    cantidad[n]=1;
                }else arch.escribirAlumno(files[n],list.get(j),'a');
            }

            /** Se copian los datos en los auxiliares al archivo resultante. */
            i=0;
            while(i<size){
                for(int j=0;j<10;j++){
                    if(cantidad[j]!=0){
                        aux=arch.leerAlumnos(files[j]);
                        if(i==0) {
                            arch.escribirAlumnos(names[1], aux,'w');
                        }else arch.escribirAlumnos(names[1], aux,'a');
                        i+=aux.size();
                    }
                }
            }

            /** Impresion de iteraciones. */
            System.out.println(" Se ha acomodado el digito "+it+".");
            System.out.println(" Se ocuparon los archivos auxiliares: ");
            for(int j=0;j<10;j++)
                if(cantidad[j]!=0)
                    System.out.print(" "+files[j]);
            System.out.println();
            if(exp==1)
                arch.escribir(names[2],"\tLista final en Iteracion "+it+":",'w');
            else
                arch.escribir(names[2],"\tLista final en Iteracion "+it+":",'a');
            arch.escribirAlumnos(names[2],arch.leerAlumnos(names[1]), 'a');
        }
        for(int j=0;j<files.length;j++)
            arch.moverCarpeta(names[0], files[j]);
        arch.moverCarpeta(names[0],names[2]);
        arch.moverCarpeta(names[0],names[1]);
        return names;
    }

}
