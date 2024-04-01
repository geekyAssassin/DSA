package BackTracking;

import java.util.*;
class Combination_Sum3 {
    public List<List<Integer>> result = new ArrayList<>();

    private List<List<Integer>> computeResult(int n, int k){
        helper(result,new ArrayList<>(), n, k, 1);
        return result;
    }

    private void helper(List<List<Integer>> result, List<Integer> tmpList, int n, int k, int start) {
        if(tmpList.size()==k && n==0) {
            result.add(new ArrayList<Integer>(tmpList));
            return;
        }
        for(int i=start;i<=9;i++) {
            tmpList.add(i);
            helper(result, tmpList, n-i, k, i+1);
            tmpList.remove(tmpList.size()-1);
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
        Combination_Sum3 p = new Combination_Sum3();
        while(t-- !=0){
            int k = sc.nextInt();
            int n = sc.nextInt();
            p.computeResult(n,k);
            p.print();
        }

    }
}
