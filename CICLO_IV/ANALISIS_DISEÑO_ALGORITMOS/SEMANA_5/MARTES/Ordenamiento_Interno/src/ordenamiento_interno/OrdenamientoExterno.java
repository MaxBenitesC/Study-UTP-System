package ordenamiento_interno;

import java.util.Arrays;

public class OrdenamientoExterno {
    private int original[];
    private int arr1[];
    private int arr2[];
    private int ind1, ind2;

    public OrdenamientoExterno(int[] a){
        original = a;
        arr1= new int[original.length];
        arr2= new int[original.length];
    }
    public void divideArreglo() {
        int indMax = original.length - 1;
        ind1 = -1;
        ind2 = -1;
        for (int i = 0; i <= indMax; i++) {
            if (i % 2 == 0) {
                ind1++;
                arr1[ind1] = original[i];
            } else {
                ind2++;
                arr2[ind2] = original[i];
            }
        }
    }
    public String muestraArreglo(){
        String cadena;
        cadena= Arrays.toString(original)+"\n"+Arrays.toString(arr1)+"\n"+Arrays.toString(arr2);
        return cadena;
    }

    public void intercalacion(){
        int i=0,j=0,k=-1;
        while(i <= ind1 && j <= ind2 ){
            k++;
            if(arr1[i] < arr2[j]){
                original[k] = arr1[i];i++;
            }
            else{
                original[k] = arr2[j];j++;
            }
        }
    }
}

