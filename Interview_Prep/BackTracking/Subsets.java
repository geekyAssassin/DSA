package BackTracking;
import java.util.*;
class Subsets {
    public List<List<Integer>> result = new ArrayList<>();

    private List<List<Integer>> computeResult(int arr[]){
        helper(result, new ArrayList<>(), arr, 0);
        return result;
    }

    private void helper(List<List<Integer>> result, List<Integer> templist, int[] arr, int n) {
        result.add(new ArrayList<>(templist));
        for(int i=n;i<arr.length;i++) {
            templist.add(arr[i]);
            helper(result, templist, arr, i+1);
            templist.remove(templist.size() -1);
        }
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
        Subsets p = new Subsets();
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