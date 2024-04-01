package BackTracking;
import java.util.*;
class Permutations {
    public List<List<Integer>> result = new ArrayList<>();

    private List<List<Integer>> computeResult(int[] arr){
        helper(result, arr, 0);
        return result;
    }

    private void helper(List<List<Integer>> result, int[] arr, int n) {
        if(n == arr.length) {
            List<Integer> templist = new ArrayList<>();
            for(int i=0;i<n;i++) {
                templist.add(arr[i]);
            }
            result.add(templist);
            return;
        }

        for(int i=n;i<arr.length;i++) {
            swap(arr,i,n);
            helper(result, arr, n+1);
            swap(arr,i,n);
        }
    }

    private void swap(int[] arr, int i, int n) {
        int t = arr[i];
        arr[i] = arr[n];
        arr[n] = t;
    }

    private void print() {
        for(List<Integer> row : result) {
            for (Integer element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        Permutations p = new Permutations();
        while(t-- !=0){
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i=0;i<n;i++){
                arr[i] = sc.nextInt();
            }
            p.computeResult(arr);
            p.print();
        }

    }
}