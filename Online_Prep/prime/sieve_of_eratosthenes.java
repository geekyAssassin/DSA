import java.util.*;

class sieve_of_eratosthenes {
    void sieveOfEratosthenes (int n) {
        boolean prime [] = new boolean [n+1];
        for(int i=0;i<=n;i++) {
            prime[i] = true;
        }

        for(int i=2;i<=n;i++) {
            if(prime[i] == true) {
                for(int p = i*i ; p<=n; p+=i)
                prime[p] = false;
            }
        }

        for(int i=2;i<=n;i++){
            if(prime[i] == true) {
                System.out.println(i);
            }
        }
    }

    public static void main (String arg[]){
        int n = 20;
        sieve_of_eratosthenes s = new sieve_of_eratosthenes();
        s.sieveOfEratosthenes(n);
    }
}