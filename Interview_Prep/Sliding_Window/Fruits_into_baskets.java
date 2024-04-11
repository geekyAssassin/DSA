package Sliding_Window;

import java.util.*;

public class Fruits_into_baskets {
    public static int totalFruit(int[] fruits) {
        int total=0,start=0,end=0;
        HashMap<Integer,Integer> count = new HashMap<>();
        while(end < fruits.length) {
            count.put(fruits[end], count.getOrDefault(fruits[end], 0)+1);
            int fruit_count = count.size();
            if(fruit_count > 2) {
                int val = count.get(fruits[start]);
                val = val-1;
                if(val==0)
                    count.remove(fruits[start]);
                else 
                    count.put(fruits[start],val);
                
                    start++;
            }
            total = Integer.max(end-start+1, total);
            end++;
        }
        return total;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        while (test!= 0) {
            int n = sc.nextInt();
            int [] arr = new int[n];
            for(int i=0;i<n;i++)
            arr[i] = sc.nextInt();
            System.out.println(totalFruit(arr));
            test--;
        }
    }
}
