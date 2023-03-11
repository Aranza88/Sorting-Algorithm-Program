package proyecto1;

import java.util.LinkedList;
import java.util.List;
/**
 * @author Alfaro Fernández Azul
 * @author Arreola Robles Itzel
 * @author Núñez Luna Aranza Abril
 */

/**
 * La clase Polifase es la que se encarga de la implementación del algoritmo de ordenamiento externo polifase.
 */
public class Polifase {

    /**
     * El metodo poli realiza las operaciones necesarias para el ordenamiento a partir de polifase.
     * @param alumnos Este parametro contiene la lista de alumnos leida del archivo original.
     * @param size Este parametro indica el tamaño de la lista original alumnos.
     * @param mode Este parametro indica el tipo de ordenamiento que realizará el algoritmo.
     * @return Este metodo regresa los nombres del archivo final, el archivo de iteraciones y la carpeta final.
     */
    public String[] Poli(List<Alumno> alumnos,int size,char mode){
        List<Alumno> Superior[];
        List<Alumno> Above[];
        List<Alumno> Intercalacion[];
        List<Alumno> Arriba[];
        List<Alumno> Abajo[];
        /** Puede recibir variable como tipo de ordenamiento. */
        Archivos arch=new Archivos();
        String names[]=new String[4];
        names[0]="PF1.txt";
        names[1]="PF2.txt";
        names[2]="PF0.txt";
        names[3]="PF3.txt";
        String files[]=new String[3];
        String line = "--------------------------------------------------------------------------";
        files[1]=null;
        files[2]="PolifaseIteraciones.txt";
        if(mode=='c')
            files[0]="PolifaseNumero";
        if(mode=='n')
            files[0]="PolifaseNombre";
        if(mode=='a')
            files[0]="PolifaseApellido";

        System.out.println("\nIteraciones "+files[0]+":");

        int divisor=2,numlistas=0;

        Superior = new LinkedList[(size/divisor)+1];

        /** Por editar elementos. */
        for(int i=0;i<size/divisor;++i){
            Superior[i]=new LinkedList();
            numlistas++;
            /** Crea listas en Superior. Habrá la cantidad de listas como se tome n. */
        }
        if((size/divisor)%2!=0){
            Superior[numlistas] = new LinkedList();
            /**
             * Si hay un numero impar, faltaría crearse una lista más.
             * Por eso se crea una lista extra para los elementos sobrantes que no entran en n.
             */
        }
        int elementos[] = new int [numlistas];
        int pos=0;
        for(int m=0;m<=numlistas;m++){
            for(int i=0;i<divisor;++i){
                if(pos==size)
                    break;
                Superior[m].add(alumnos.get(pos));
                elementos[m]++;
                pos++;
                /** Se agrega un alumno de la lista de alumnos a cierta lista dentro de superior. */
            }
        }
        /** Ahora superior tiene k listas desordenadas. */
        for(int i=0;i<=numlistas;++i){
            sorting(Superior[i],mode);
        }
        int listasA=0, listasB=0;
        /** Ahora Superior tiene todas sus listas ordenadas. */
        for(int i=0;i<numlistas;++i){
            if(i==0){
                arch.escribirAlumnos(names[0],Superior[i],'w');
                listasA++;
            }else if(i%2==0){
                arch.escribirAlumnos(names[0],Superior[i],'a');
                listasA++;
            }else if(listasB==0){
                arch.escribirAlumnos(names[1],Superior[i],'w');
                listasB++;
            }else{
                arch.escribirAlumnos(names[1],Superior[i],'a');
                listasB++;
            }
        }
        /** Los archivos f1 y f2 ya tienen varios bloques, con listas ordenadas. */
        arch.escribir(files[2],"",'w');
        //------
        int iteracion=1,temporal,a,c,d,e;
        int multiusos=0;
        Above = new LinkedList [4];
        Arriba = new LinkedList[listasA];
        Abajo = new LinkedList[listasB];
        for(int i=0;i<4;++i)
            Above[i]=new LinkedList();
        for(int i=0;i<listasA;++i)
            Arriba[i]=new LinkedList();
        for(int i=0;i<listasB;++i)
            Abajo[i]=new LinkedList();
        do{
            for(int i=0;i<4;++i)
                Above[i].clear();
            for(int i=0;i<listasA;++i)
                Arriba[i].clear();
            for(int i=0;i<listasB;++i)
                Abajo[i].clear();
            int b=0;
            int pos2=0;
            pos=0;

            /**
             * Leer un bloque de "f1" o "f0" o "f2" o "f3" (deben estar separados por medio de algo).
             * numlistas= sacar el numero de listas existentes en cada iteracion.
             * Lo unico que se debe hacer es dividir el numero de listas entre 2. Ese resultado es el num de listas.
             * Si la division es impar, se le suma una unidad
            */

            if(iteracion%2!=0){
                a=2; c=3;
                d=0; e=1;
            }else{
                a=0; c=1;
                d=2; e=3;
            }
            Above[a]=arch.leerAlumnos(names[d]);
            Above[c]=arch.leerAlumnos(names[e]);
            for(int m=0;b<numlistas&&(b<Above[a].size()||b<Above[c].size());m++){
                if(b%2==0 && b<numlistas){
                    for(int i=0;i<elementos[b];++i){
                        Arriba[m].add(Above[a].get(pos));
                        pos++;
                    }
                }b++;
                if(b%2!=0 && b<numlistas){
                    for(int i=0;i<elementos[b];++i){
                        Abajo[m].add(Above[c].get(pos2));
                        pos2++;
                    }
                }
                b++;
            }
            /**
            * Ahora, arriba tiene las listas de F1 o F0 y Abajo tiene las listas de F2 o F3
            * Determinamos que archivos debemos leer dependiendo de la iteracion.
            * Debe haber un contador que nos diga que iteracion en. si es 0.
            * Es decir, la primera, entonces debe caer en f1 y f2 primer if.
            * Conseguir que el nombre cambie dependiendo de la iteracion
            */
            if(listasA==listasB)
                {temporal=listasA;}
            else
                temporal=listasB;
            Intercalacion = new LinkedList [temporal+1];

            b=0;
            int m=0;
            int elementi[] = new int [numlistas];
            while(m<temporal & b<numlistas){
                if(mode=='c'){
                    Intercalacion[m]=intercalarNumeroCuenta(Arriba[m],Abajo[m]);
                }
                if(mode=='n'| mode== 'a'){
                    Intercalacion[m]=intercalarStrings(Arriba[m],Abajo[m],mode);
                }

                elementi[b]=Intercalacion[m].size();
                multiusos++;
                b++;
                m++;
                /** Intercalar me regresa una lista. */
            }
            elementos=elementi;
//
            if(numlistas%2!=0){
                Intercalacion[m] = Arriba[listasA-1];
                elementos[b]=Intercalacion[m].size();
            }
//
            if(listasA==listasB)
                numlistas=multiusos;
            else
                numlistas=multiusos+1;
//
            System.out.println(" Iteracion "+iteracion+".");
            System.out.println(" En esta iteracion se ocupan los archivos auxiliares "+names[a]+" y "+names[c]+".");
            if(multiusos>0){
                arch.escribir(files[2], line+"\n\nIteración "+iteracion+ "\nEn el archivo "+names[a], 'a');
            }
            for(int i=0;i<numlistas;i=i+2){
                //Pares
                arch.escribirAlumnos(files[2], Intercalacion[i], 'a');
            }
            if(multiusos>0){
                arch.escribir(files[2], "\nEn el archivo "+names[c], 'a');
            }
            for(int i=1;i<numlistas;i=i+2){
                //impares
                arch.escribirAlumnos(files[2], Intercalacion[i], 'a');
            }

            multiusos=0;
            /** Intercalacion ya es una arreglo de listas. Estas listas ya fueron ordenadas e intercaladas. */
            listasA=0;
            listasB=0;
            for(int i=0;i<numlistas;i++){
                if(Intercalacion[i]!=null){
                    if(i==0){
                        arch.escribirAlumnos(names[a],Intercalacion[i],'w');
                        listasA++;
                        files[1]=names[a];
                    }else if(i%2==0){
                        arch.escribirAlumnos(names[a],Intercalacion[i],'a');
                        listasA++;
                        files[1]=names[a];
                    }else if(i==1){
                        arch.escribirAlumnos(names[c],Intercalacion[i],'w');
                        listasB++;
                        files[1]=names[c];
                    }else{
                        arch.escribirAlumnos(names[c],Intercalacion[i],'a');
                        listasB++;
                        files[1]=names[c];
                    }
                }
            }
            iteracion++;
        }while(numlistas>1);
        for(int j=0;j<names.length;j++)
            arch.moverCarpeta(files[0], names[j]);
        arch.moverCarpeta(files[0],files[2]);
        return files;
    }

    /**
     * El metodo sorting() se encarga de llamar al ordenamiento interno indicado segun el modo indicado.
     * @param lista Este parametro representa la lista que será ordenada.
     * @param modo Este parametro representa el dato que se ordenará.
     */
    public void sorting(List<Alumno> lista, char modo){
        QuickSort qs=new QuickSort();
        if(modo=='c')
            qs.sortNumero(lista, 0, lista.size()-1);
        else if(modo=='a')
            qs.sortApellidos(lista, 0, lista.size()-1);
        else if(modo=='n')
            qs.sortNombre(lista, 0, lista.size()-1);
    }

    /**
     * El método intercalarNumeroCuenta() se encarga de intercalar dos listas y ordenarlas segun el numero de cuenta.
     * @param primero Este parametro representa uno de los bloques que se intercala.
     * @param segundo Este parametro representa el segundo bloque que se intercala.
     * @return Este método regresa la lista resultante de la interacalación de ambas listas parametros.
     */
    public List<Alumno> intercalarNumeroCuenta(List<Alumno> primero,List<Alumno> segundo){
        List<Alumno> finaly = new LinkedList();
            int n1 = primero.size();
            int n2 = segundo.size();
            int i=0,j=0;
            while (i < n1 && j < n2) {
                if (primero.get(i).getNoCuenta() <= segundo.get(j).getNoCuenta()) {
                    finaly.add(primero.get(i));
                    i++;
                }
                else {
                    finaly.add(segundo.get(j));
                    j++;
                }
            }
            while (i < n1) {
                finaly.add(primero.get(i));
                i++;
            }
            while (j < n2) {
                finaly.add(segundo.get(j));
                j++;
            }
        return finaly;
    }

    /**
     * El metodo intercalarString() se encarga de intercalar dos listas y ordenarlas por un sato de tipo String.
     * @param primero Este parametro representa uno de los bloques que se intercala.
     * @param segundo Este parametro representa el segundo bloque que se intercala.
     * @param mode Este parametro indica el dato que se ordenara en el la lista intercalacion.
     * @return Este metodo regresa la lista resultante de la interacalación de ambas listas parametros.
     */
    public List<Alumno> intercalarStrings(List<Alumno> primero,List<Alumno> segundo, char mode){
        List<Alumno> finaly = new LinkedList();
        List<Alumno> temporal = new LinkedList();
        int n1 = primero.size();
        int n2 = segundo.size();
        int i=0,j=0;
        while (i < n1 && j < n2) {
            temporal.add(primero.get(i));
            temporal.add(segundo.get(j));
            sorting(temporal,mode);
            if (temporal.get(0)==primero.get(i)) {
                finaly.add(primero.get(i));
                i++;
            }
            else {
                finaly.add(segundo.get(j));
                j++;
            }
            temporal.clear();
        }
        while (i < n1) {
            finaly.add(primero.get(i));
            i++;
        }
        while (j < n2) {
            finaly.add(segundo.get(j));
            j++;
        }
        return finaly;
    }
}