public class main {
    public static void main(String[] args) {
        int[] a = {10,20,30};
        int[] b = {10,20,30};
        int[] c = {10,20,30,40};
        int[] d = {10,20,30};
        int[] e = {10,20,50};
        int[] f = {10,20,30};
        int[] g = {10,20,30,40,60,100};
        int[] h = {10,20,30,40,60,100,300};

        System.out.println(compare(a, b)); // esperas: 0
        System.out.println(compare(c, d)); // esperas: positivo
        System.out.println(compare(e, f)); // esperas: positivo
        System.out.println(compare(g, h)); // esperas: negativo
    }
    public static int compare(int[] a, int[] b){
        int menor;
        if(a.length > b.length){
            menor=b.length;
        }else {
            menor=a.length;
        }

        int i=0;
        boolean x=true;
        while (true){
            if(i==menor) break;
            if(a[i]!=b[i]){
                x=false;
                break;
            }
            i++;
        }

        if(x && a.length==b.length){
            return 0;
        }else if(x){
            return a.length-b.length;
        }else{
            return a[i] - b[i];
        }
    }
}
