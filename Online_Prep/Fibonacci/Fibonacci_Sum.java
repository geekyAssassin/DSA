import java.util.Scanner;

class Fibonacci_Sum {
     long mod = 1000000007L;
     long fib(long n) {
        long[][] m = {{1,1},{1,0}};
        if(n==0)
            return 0;
        p(m,n-1);
        return m[0][0];
    }
    void p(long[][]m, long n) {
        if(n==0 || n ==1)
            return;
        long[][] r = {{1,1},{1,0}};
        p(m,n/2);
        mu(m, m);

        if(n%2!=0)
            mu(m, r);
    }
    
    void mu(long[][] m, long [][]r) {
        long w = (m[0][0]*r[0][0] + m[0][1]*r[1][0])%mod;
        long x = (m[0][0]*r[0][1] + m[0][1]*r[1][1])%mod;
        long y = (m[1][0]*r[0][0] + m[1][1]*r[1][0])%mod;
        long z = (m[1][0]*r[1][0] + m[1][1]*r[1][1])%mod;
        m[0][0] = w;m[0][1] = x;m[1][0] = y;m[1][1] = z;
    }
    public static void main(String[] args) {
        int t;
        Scanner sc = new Scanner(System.in);
        Fibonacci_Sum s = new Fibonacci_Sum();
        t=sc.nextInt();
        while(t-- !=0) {
            long n = sc.nextLong();
            long m = sc.nextLong();
            long r= (s.fib(m+2)-s.fib(n+1))%s.mod;
            if(r<0)
                r+=s.mod;
            System.out.println(r);
        }
    }
}