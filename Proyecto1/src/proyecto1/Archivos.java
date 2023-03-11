package proyecto1;

import java.util.LinkedList;
import java.nio.file.*; //Para carpetas
import java.util.List;
import java.io.*; //Para archivos y excepciones
/**
 * @author Alfaro Fernández Azul
 * @author Arreola Robles Itzel
 * @author Núñez Luna Aranza Abril
 */
/**
 * La clase Archivos es la que se encarga de la lectura y escritura de archivos.
 * Esta clase se ocupa para todo el manejo de archivos en el programa.
 */
public class Archivos {

    /**
     * El metodo escribir se encarga de imprimir un texto dado en el archivo indicado.
     * @param nombre Este parametro representa el nombre del archivo en el que se escribira.
     * @param texto Este parametro representa el texto que se quiere imprimir.
     * @param mode Este parametro define si el texto se sobreescribe o se añade al archivo.
     */
    public void escribir(String nombre,String texto, char mode){
        File f=new File(nombre);
        if(mode=='a'){
            try{
                FileWriter w=new FileWriter(f,true);
                BufferedWriter bw=new BufferedWriter(w);
                PrintWriter wr=new PrintWriter(bw);
                wr.append(texto+"\n");
                wr.close();
                bw.close();
                w.close();
            }catch(IOException e){ System.err.println(e); };
        }else if(mode=='w'){
            try{
                FileWriter w=new FileWriter(f);
                BufferedWriter bw=new BufferedWriter(w);
                PrintWriter wr=new PrintWriter(bw);
                wr.write(texto+"\n");
                wr.close();
                bw.close();
                w.close();
            }catch(IOException e){ System.err.println(e); };
        }else
            System.out.println("Error: Modo no disponible");
    }

    /**
     * El metodo escribirAlumno se encarga de imprimir un alumno dado en el archivo indicado.
     * @param nombre Este parametro representa el nombre del archivo en el que se escribira.
     * @param a Este parametro representa a un alumno, el cual se imprimira en el archivo.
     * @param mode Este parametro define si el texto se sobreescribe o se añade al archivo.
     */
    public void escribirAlumno(String nombre,Alumno a,char mode){
        File f=new File(nombre);
        if(mode=='a'){
            try{
                FileWriter w=new FileWriter(f,true);
                BufferedWriter bw=new BufferedWriter(w);
                PrintWriter wr=new PrintWriter(bw);
                wr.append(a.getNombre()+", "+a.getApellidos()+", "+a.getNoCuenta()+"\n");
                wr.close();
                bw.close();
                w.close();
            }catch(IOException e){ System.err.println(e); };
        }else if(mode=='w'){
            try{
                FileWriter w=new FileWriter(f);
                BufferedWriter bw=new BufferedWriter(w);
                PrintWriter wr=new PrintWriter(bw);
                wr.write(a.getNombre()+", "+a.getApellidos()+", "+a.getNoCuenta()+"\n");
                wr.close();
                bw.close();
                w.close();
            }catch(IOException e){ System.err.println(e); };
        }else
            System.out.println("Error: Modo no disponible");
    }

    /**
     * El metodo escribirAlumnos se encarga de imprimir una lista de alumnos en el archivo indicado.
     * @param nombre Este parametro representa el nombre del archivo en el que se escribira.
     * @param a Este parametro representa una lista de alumnos, la cual se imprimira en el archivo.
     * @param mode Este parametro define si el texto se sobreescribe o se añade al archivo.
     */
    public void escribirAlumnos(String nombre,List<Alumno> a,char mode){
        File f=new File(nombre);
        if(mode=='a'){
            try{
                FileWriter w=new FileWriter(f,true);
                BufferedWriter bw=new BufferedWriter(w);
                PrintWriter wr=new PrintWriter(bw);
                for(Alumno i:a)
                    wr.append(i.getNombre()+", "+i.getApellidos()+", "+i.getNoCuenta()+"\n");
                wr.close();
                bw.close();
                w.close();
            }catch(IOException e){ System.err.println(e); };
        }else if(mode=='w'){
            try{
                FileWriter w=new FileWriter(f);
                BufferedWriter bw=new BufferedWriter(w);
                PrintWriter wr=new PrintWriter(bw);
                wr.write("");
                for(Alumno i:a)
                    wr.append(i.getNombre()+", "+i.getApellidos()+", "+i.getNoCuenta()+"\n");
                wr.close();
                bw.close();
                w.close();
            }catch(IOException e){ System.err.println(e); };
        }else
            System.out.println("Error: Modo no disponible");
    }

    /**
     * El metodo leerAlumnos se encarga de la lectura, separacion y acomodamiento del texto en un archivo dado.
     * @param nombre Este parametro representa el nombre del archivo que se quiere leer.
     * @return Este metodo regresa una lista de alumnos con los datos acomodados.
     */
    public List<Alumno> leerAlumnos(String nombre){
        List<Alumno> al=new LinkedList<>();
        File f=new File(nombre);
        try{
            FileReader r  = new FileReader(f);
            BufferedReader br=new BufferedReader(r);
            String linea=br.readLine();
            //int contador=0; //Opcional. Para saber cuantas lineas lee
            while(linea != null){
                String[] values = linea.split(", ");
                Alumno a=new Alumno();
                a.setNombre(values[0]);
                a.setApellidos(values[1]);
                a.setNoCuenta(Integer.parseInt(values[2].trim()));
                al.add(a);
                //contador++; //contador de lineas
                linea = br.readLine();
            }
            br.close();
            r.close();
        }catch(IOException e){ System.err.println(e); }
        return al;
    }

    /**
     * El metodo moverCarpeta se encarga de mover un archivo dado a la carpeta indicada, creandola si no existe.
     * @param dir Este parametro representa el nombre de la carpeta a donde se quiere mover el archivo.
     * @param name Este parametro representa el nombre del archivo que se desea mover.
     */
    public void moverCarpeta(String dir,String name){
        String actual = System.getProperty("user.dir");
        File carpeta = new File(actual+"/"+dir);
        if(!carpeta.exists()){
            carpeta.mkdir();
            carpeta.setReadable(true);
            carpeta.setWritable(true);
            carpeta.setExecutable(true);
        }
        Path from = FileSystems.getDefault().getPath(actual+"\\"+name);
        Path to = FileSystems.getDefault().getPath(actual+"\\"+dir+"\\"+name);
        try {
            Files.move(from, to, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e) { System.err.println(e); }
    }
}
