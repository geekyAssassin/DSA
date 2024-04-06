package BackTracking;

import java.util.*;

public class Word_Search {
    public boolean solution(char[][] board, String word) {
        char[] ch = word.toCharArray();
        for(int x = 0 ;x<board.length;x++) {
            for(int y=0; y < board[x].length;y++) {
                if(exist(board,x,y,ch,0) == true)
                return true;
            }
        }
        return false;
    }

    private boolean exist(char[][] charArray, int x, int y, char[] ch, int i) {
        if(i==ch.length)
            return true;
        if(y < 0 || x < 0 || x == charArray.length || y == charArray[x].length)
            return false;
        if(charArray[x][y] != ch[i])
            return false; 
        
        charArray[x][y]^= 256;
        boolean exist = exist(charArray, x+1, y, ch, i+1) ||
        exist(charArray, x, y+1, ch, i+1) ||
        exist(charArray, x-1, y, ch, i+1) ||
        exist(charArray, x, y-1, ch, i+1);
        charArray[x][y]^=256;
        return exist;
    }

    public static void main(String args[]) {

        int no_of_tests;
        String input;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of tests");
        no_of_tests = sc.nextInt();
        Word_Search s = new Word_Search();
        while (no_of_tests != 0) {
            System.out.println("Enter the number of rows (m):");
            int m = sc.nextInt(); // Reading the number of rows
            System.out.println("Enter the number of columns (n):");
            int n = sc.nextInt(); // Reading the number of columns
            char[][] charArray = new char[m][n];
            sc.nextLine();
            for (int i = 0; i < m; i++) {
                String[] line = sc.nextLine().split(" "); // Reading a line and splitting by space

                // Looping through each column in the current row
                for (int j = 0; j < n; j++) {
                    charArray[i][j] = line[j].charAt(0); // Converting the string to a char and storing it in the array
                }
            }
            System.out.println("Enter the String");
            input = sc.next();
            
            System.out.println("The String after operation is" + s.solution(charArray, input));
            no_of_tests--;
        }
    }
}