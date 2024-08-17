import java.util.Scanner;

public class even_odd_query {
    public class element {
        int arrElement;
        int oddCount;
        int evenCount;
        public element(int arrElement) {
            this.arrElement = arrElement;
            this.oddCount = arrElement%2==1 ? 1:0;
            this.evenCount = arrElement%2==0 ? 1:0;
        }
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            int n = sc.nextInt();
            for(int i=0;i<n;i++) {

            }
        }
    }
}
