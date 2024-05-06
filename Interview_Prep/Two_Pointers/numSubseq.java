package Two_Pointers;

import java.util.*;

/* Sort input A first,
For each A[i], find out the maximum A[j]
that A[i] + A[j] <= target.

For each elements in the subarray A[i+1] ~ A[j],
we can pick or not pick,
so there are 2 ^ (j - i) subsequences in total.
So we can update res = (res + 2 ^ (j - i)) % mod.

We don't care the original elements order,
we only want to know the count of sub sequence.
So we can sort the original A, and the result won't change. */
public class numSubseq {
    private static int numseq(int[] arr, int target) {
        Arrays.sort(arr);
        int start=0,end=arr.length -1, res=0,mod = (int)1e9 + 7;
        int[] pow = new int[arr.length];
        pow[0] =1;
        for(int i=1;i<arr.length;i++)
            pow[i] = pow[i-1] * 2 % mod;
        while(start<=end) {
            if(arr[start] + arr[end] > target)
                end--;
            else
                res = (res+ pow[end-start++])%mod;

        }
        return res;
    }

    public static void main(String[] args) {
        int no_of_tests, length;
        Scanner sc = new Scanner(System.in);
        no_of_tests = sc.nextInt();
        numSubseq s = new numSubseq();
        while (no_of_tests != 0) {
            length = sc.nextInt();
            int[] arr = new int[length];
            for (int i = 0; i < length; i++) {
                arr[i] = sc.nextInt();
            }
            int target = sc.nextInt();
            System.out.println(numseq(arr, target));
            no_of_tests--;
        }
    }
}