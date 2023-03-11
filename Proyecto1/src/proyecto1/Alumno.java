package proyecto1;

/**
 * @author Alfaro Fernández Azul
 * @author Arreola Robles Itzel
 * @author Núñez Luna Aranza Abril
 */

/**
 * La clase Alumno se encarga de modelar los datos de un alumno.
 * Esta clase se utiliza en todo el programa, ayudando al manejo de datos
 */
public class Alumno {
    private String nombre;
    private String apellidos;
    private int noCuenta;

    /** Metodos setters de los atributos de Alumno. */
    public void setNombre(String nombre){ this.nombre=nombre; }
    public void setApellidos(String apellidos){ this.apellidos=apellidos; }
    public void setNoCuenta(int noCuenta){ this.noCuenta=noCuenta; }

    /** Metodos getters de los atributos de Alumno. */
    public String getNombre(){ return this.nombre; }
    public String getApellidos(){ return this.apellidos; }
    public int getNoCuenta(){ return this.noCuenta; }

    /**
     * El metodo imprimirAlumno se encarga de la impresion de datos del alumno dado.
     * @param s Este parametro representa un texto para el formato que se desea dar a la impresion.
     */
    public void imprimirAlumno(String s){
        System.out.println(s+"Nombre: "+getNombre());
        System.out.println(s+"Apellidos: "+getApellidos());
        System.out.println(s+"Numero de cuenta: "+getNoCuenta());
    }
}
