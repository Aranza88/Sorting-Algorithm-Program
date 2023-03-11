package proyecto1;

import java.util.*;
/**
 * @author Alfaro Fernández Azul
 * @author Arreola Robles Itzel
 * @author Núñez Luna Aranza Abril
 */

/**
 * La clase MezclaEquilibrada es la que se encarga de la implementación del algoritmo de ordenamiento externo mezcla equilibrada o natural.
 */
public class MezclaEquilibrada{

    /**
     * El metodo mezcla realiza las operaciones necesarias para el ordenamiento a partir de mezcla equilibrada.
     * @param alumnos Este parametro contiene la lista de alumnos leida del archivo original.
     * @param size Este parametro indica el tamaño de la lista original alumnos.
     * @param mode Este parametro indica el tipo de ordenamiento que realizará el algoritmo.
     * @return Este metodo regresa los nombres del archivo final, el archivo de iteraciones y la carpeta final.
     */
    public String[] mezcla(List<Alumno> alumnos,int size,char mode){
        List<Alumno> Superior[];
        List<Alumno> Above1=new LinkedList();
        List<Alumno> Above2=new LinkedList();
        List<Alumno> Arriba[];
        List<Alumno> Abajo[];
        List<Alumno> Intercalacion[];
        Archivos arch=new Archivos();
        String names[]=new String[3];
        Utilities u =new Utilities();
        names[0]="MezclaEquilibradaFinal.txt";
        names[1]="MEF1.txt";
        names[2]="MEF2.txt";
        String files[]=new String[3];
        String line = "--------------------------------------------------------------------------";
        files[1]="MezclaEquilibradaFinal.txt";
        files[2]="MezclaEquilibradaIteraciones.txt";
        if(mode=='c')
            files[0]="MezclaEquilibradaPorNumerosDeCuenta";
        if(mode=='n')
            files[0]="MezclaEquilibradaPorNombres";
        if(mode=='a')
            files[0]="MezclaEquilibradaPorApellidos";

        System.out.println("\nIteraciones "+files[0]+":");
        Superior = new LinkedList[size+1];
        //
        int m=0;
        for(int i=0; i<size;i++){
            List<Alumno> temporal = new LinkedList();
            Superior[i]=new LinkedList();
            if(i==0){
                Superior[0].add(alumnos.get(0));
                temporal.add(alumnos.get(0));
            }
            else{
                temporal.add(alumnos.get(i-1));
                temporal.add(alumnos.get(i));
                sorting(temporal,mode);
                if(temporal.get(1)==alumnos.get(i)){
                    Superior[m].add(alumnos.get(i));
                }
                else{
                    m++;
                    Superior[m].add(alumnos.get(i));
                }
            }
            temporal.clear();
        }
        int elementos[] = new int [m];
        int listasA=0, listasB=0;
        for(int i=0;i<m;i++){
            elementos[i]=Superior[i].size();
        }
        /** Ahora, elementos tiene la cantidad de elementos que hay en cada lista. */
        for(int i=0;i<m+1;++i){
            if(i==0){
                arch.escribirAlumnos(names[1],Superior[i],'w');
                listasA++;
            }else if(i%2==0){
                arch.escribirAlumnos(names[1],Superior[i],'a');
                listasA++;
            }else if(listasB==0){
                arch.escribirAlumnos(names[2],Superior[i],'w');
                listasB++;
            }else{
                arch.escribirAlumnos(names[2],Superior[i],'a');
                listasB++;
            }
            if(i==0){
                arch.escribirAlumnos(names[0],Superior[i],'w');
            }
            else{
                arch.escribirAlumnos(names[0],Superior[i],'a');
            }

        }
        int numlistas=0;
        if(listasA==listasB)
            numlistas=m;
        else
            numlistas=m+1;
        /**
         * De esta manera, tenemos a la lista Superior que contiene a todas las subilistas
         * Que se van a dividir entre los dos documentos.
         * m es igual al numero de listas que hay.
        */
        /**
         * F0 tiene la lista original, F1 Y F2 la tienen por partes
         * Los archivos 1 y 2 ya tienen a las listas intercaladas.
         * ListasA guarda cuantas listas hay en 1
         * ListasB guarda cuantas listas hay en 2
        */
        Arriba = new LinkedList[listasA];
        Abajo = new LinkedList[listasB];
        for(int i=0;i<listasA;++i)
            Arriba[i]=new LinkedList();
        for(int i=0;i<listasB;++i)
            Abajo[i]=new LinkedList();
        /**
         * Ahora, la lista Arriba guardará todas las listas existentes en F1
         * Y Abajo guardará todas las listas existentes en F2.
        */
        //----------------------------------------------------------------------------------------
        arch.escribir(files[2],"",'w');
        int iteracion=1,temp=0;
        int multiusos=0;
        do{
            Above1.clear();
            Above2.clear();
            for(int i=0;i<size;++i)
                Superior[i].clear();
            for(int i=0;i<listasA;++i)
                Arriba[i].clear();
            for(int i=0;i<listasB;++i)
                Abajo[i].clear();
            int b=0;

            Above1=arch.leerAlumnos(names[1]);
            Above2=arch.leerAlumnos(names[2]);
            /**
             * Above1 tiene la lista en F1
             * Above2 tiene la lista en F2.
             */

            /** Se copio la lista de F1 a Above1. */
            int pos=0,pos2=0;
            //&&(b<Above1.size()||b<Above2.size())
            for(m=0;m<numlistas;m++){
                if(b%2==0 && b<numlistas){
                    for(int i=0;i<elementos[b];++i){
                        Arriba[m].add(Above1.get(pos));
                        pos++;
                    }
                }b++;
                if(b%2!=0 && b<numlistas){
                    for(int i=0;i<elementos[b];++i){
                        Abajo[m].add(Above2.get(pos2));
                        pos2++;
                    }
                }
                b++;
            }

            /**
             * Se copio la lista de F2 a Above1
             *
             * Ahora, Arriba y Abajo contienen las listas de los elementos ordenados
             * de cada documento.
             * Siguiente paso, hacer la intercalación
            */
            if(listasA==listasB)
                {temp=listasA;}
            else
                temp=listasB;
            Intercalacion = new LinkedList [temp+1];

            b=0;
            m=0;
            int elementi[] = new int [numlistas];
            while(m<temp & b<numlistas){
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
                //Intercalar me regresa una lista
            }
            elementos=elementi;


            if(numlistas%2!=0){
                Intercalacion[m] = Arriba[listasA-1];
                elementos[b]=Intercalacion[m].size();
            }
            /**
             * Ahora, Intercalación tiene a las listas intercaladas de cada uno
             * Siguiente paso: mandar estas listas a F0.
            */
            if(listasA==listasB)
                numlistas=multiusos;
            else
                numlistas=multiusos+1;

            System.out.println(" Iteracion "+iteracion+".");
            System.out.println(" Los bloques se guardan en "+names[1]+" y "+names[2]);
            System.out.println(" Las intercalaciones se guardan en "+names[0]+".");
            if(multiusos>0){
                arch.escribir(files[2], line+"\n\nIteración "+iteracion+ "\nEn el archivo "+names[0], 'a');
            }
            for(int i=0;i<numlistas;i++){
                //Pares
                arch.escribirAlumnos(files[2], Intercalacion[i], 'a');
            }

            listasA=0;
            listasB=0;
            for(int i=0;i<numlistas;++i){
            if(i==0){
                arch.escribirAlumnos(names[0],Intercalacion[i],'w');
                }
            else{
                arch.escribirAlumnos(names[0],Intercalacion[i],'a');
                }
            }
            /** Ahora, F0 tiene a las listas ordenadas. */
            multiusos=0;
            for(int i=0;i<numlistas;i++){
                if(Intercalacion[i]!=null){
                    if(i==0){
                        arch.escribirAlumnos(names[1],Intercalacion[i],'w');
                        listasA++;
                    }else if(i%2==0){
                        arch.escribirAlumnos(names[1],Intercalacion[i],'a');
                        listasA++;
                    }else if(i==1){
                        arch.escribirAlumnos(names[2],Intercalacion[i],'w');
                        listasB++;
                    }else{
                        arch.escribirAlumnos(names[2],Intercalacion[i],'a');
                        listasB++;
                    }
                }
            }
            iteracion++;
        }while(numlistas>1);
        files[1]=names[0];
        for(int j=0;j<names.length;j++)
            arch.moverCarpeta(files[0], names[j]);
        arch.moverCarpeta(files[0],files[2]);
        return files;
    }

    /**
     * El metodo sorting() se encarga de llamar al ordenamiento interno indicado segun el modo indicado.
     * @param lista Este parametro representa la lista que sera ordenada.
     * @param modo Este parametro representa el dato que se ordenara.
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
     * El metodo intercalarNumeroCuenta() se encarga de intercalar dos listas y ordenarlas segun el numero de cuenta.
     * @param primero Este parametro representa uno de los bloques que se intercala.
     * @param segundo Este parametro representa el segundo bloque que se intercala.
     * @return Este metodo regresa la lista resultante de la interacalación de ambas listas parametros.
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
