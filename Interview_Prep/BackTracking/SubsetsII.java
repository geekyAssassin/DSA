package BackTracking;
import java.util.*;
class SubsetsII {
    public List<List<Integer>> result = new ArrayList<>();

    private List<List<Integer>> computeResult(int arr[]){
        Arrays.sort(arr);
        helper(result, new ArrayList<>(), arr, 0);
        return result;
    }

    private void helper(List<List<Integer>> result, List<Integer> tempList, int[] arr , int n) {
        result.add(new ArrayList<>(tempList));
        HashSet<Integer> s = new HashSet<>();
        for(int i=n;i<arr.length;i++) {
            if(!s.contains(arr[i])) {
                s.add(arr[i]);
                tempList.add(arr[i]);
                helper(result, tempList, arr, i+1);
                tempList.remove(tempList.size() -1);
            }
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
        SubsetsII p = new SubsetsII();
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