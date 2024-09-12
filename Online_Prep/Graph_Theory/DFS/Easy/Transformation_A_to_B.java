package Easy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Transformation_A_to_B {
    static boolean flag = false;
    static long ans = 0;
    static PrintWriter out;

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    public static void main(String[] args) {
        out = new PrintWriter(System.out);
        FastReader sc = new FastReader();

        long a, b;
        a = sc.nextLong();
        b = sc.nextLong();
        int[] result = new int[10000005];
        result[0] = (int) a;
        dfsTraversal(a, b, 1, result);
        if (flag == true) {
            out.println("YES");
            out.println(ans);
            for (int i=0;i<ans;i++) {
                out.print(result[i] + " ");
            }
            out.println();
        } else {
            out.println("NO");
        }
        out.close();
    }

    private static void dfsTraversal(long a, long b, int step, int[] result) {
        if (a == b) {
            flag = true;
            ans = step;
            return;
        }
        if (a > b)
            return;
        result[step] = (int) (a * 10 + 1);
        dfsTraversal(a * 10 + 1, b, step+1, result);

        if (flag == true)
            return;

        result[step] = (int) (a * 2);
        dfsTraversal(a * 2, b, step+1, result);
    }
}
