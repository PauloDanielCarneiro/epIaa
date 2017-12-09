/*public class quickSort{
    public static void main(String[] args){
        int[] a = {1, 5, 2, 3};
        quickSort(a, 0, 2);
    }
    public static void quickSort(int[] a, int ini, int fim){
        if(ini < fim){
            int q = particiona(a, ini, fim);
            quickSort(a, ini, q);
            quickSort(a, q+1, fim);
        }
        
    }
	private static int particiona(int[] a, int ini, int fim) {
        int x = a[ini];
        int i = ini - 1;
        int j = fim + 1;
        while(true){
            do{
                j--;
            }while(a[j] > x);
            do{
                i++;
            }while(a[i] < x);
            if (i < j) troca(a, i, j);
            else return j;
        }
        
	}
	private static void troca(int[] a, int i, int j) {
	}
}*/