package proyecto1;

import java.util.List;
/**
 * @author Alfaro Fernández Azul
 * @author Arreola Robles Itzel
 * @author Núñez Luna Aranza Abril
 */

public class QuickSort {

    void swap(List<Alumno> al,int a, int b){
        Alumno t =al.get(a);
        al.set(a,al.get(b));
        al.set(b,t);
    }

    int partitionNumero(List<Alumno> a, int low, int high){
        int pivot=a.get(high).getNoCuenta();
        int j,i=(low-1);
        for(j=low;j<=high-1;j++){
            if(a.get(j).getNoCuenta()<=pivot){
                i++;
                swap(a,i,j);
            }
        }
        swap(a,i+1,high);
        return(i+1);
    }

    void sortNumero(List<Alumno> a,int low, int high){
        if(low<high){
            int pi=partitionNumero(a,low,high);
            sortNumero(a,low,pi-1);
            sortNumero(a,pi+1,high);
        }
    }

    int partitionNombre(List<Alumno> a, int low, int high){
        String pivot=a.get(high).getNombre();
        int j,i=(low-1);
        for(j=low;j<=high-1;j++){
            if(a.get(j).getNombre().compareToIgnoreCase(pivot)<=0){
                i++;
                swap(a,i,j);
            }
        }
        swap(a,i+1,high);
        return(i+1);
    }

    void sortNombre(List<Alumno> a,int low, int high){
        if(low<high){
            int pi=partitionNombre(a,low,high);
            sortNombre(a,low,pi-1);
            sortNombre(a,pi+1,high);
        }
    }

    int partitionApellidos(List<Alumno> a, int low, int high){
        String pivot=a.get(high).getApellidos();
        int j,i=(low-1);
        for(j=low;j<=high-1;j++){
            if(a.get(j).getApellidos().compareToIgnoreCase(pivot)<=0){
                i++;
                swap(a,i,j);
            }
        }
        swap(a,i+1,high);
        return(i+1);
    }

    void sortApellidos(List<Alumno> a,int low, int high){
        if(low<high){
            int pi=partitionApellidos(a,low,high);
            sortApellidos(a,low,pi-1);
            sortApellidos(a,pi+1,high);
        }
    }
}
