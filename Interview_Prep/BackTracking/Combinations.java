package BackTracking;

import java.util.*;

public class Combinations {
    public List<List<Integer>> result = new ArrayList<>();

    private List<List<Integer>> computeResult(int n, int k) {
        helper(result,new ArrayList<>(),n,k,1);
        return result;
    }

    private void helper(List<List<Integer>> result, List<Integer> tmplist, int n,int k,int start) {
        if(k==0) {
            result.add(new ArrayList<>(tmplist));
            return;
        }
        for(int i=start;i<=n;i++) {
            tmplist.add(i);
            k--;
            helper(result,tmplist,n,k,i+1);
            tmplist.remove(tmplist.size()-1);
            k++;
        }
    }

    private void print() {
        for (List<Integer> row : result) {
            for (Integer element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        Combinations p = new Combinations();
        while (t-- != 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            p.computeResult(n,k);
            p.print();
        }

    }
}
