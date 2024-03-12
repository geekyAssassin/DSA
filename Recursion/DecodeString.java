import java.util.Scanner;
class Solution {
    int i=0;
    public String decodeString(String s){
        StringBuilder result = new StringBuilder();
        int count =0;
        String temp = "";

        while(i < s.length()){
            char c = s.charAt(i);
            i++;

            if(Character.isDigit(c)) {
                count = count*10 + (c-'0');
            }
            else if (c == '[') {
                temp = decodeString(s);
                for(int j =0;j<count;j++)
                result.append(temp);
                count =0;
            }
            else if(Character.isAlphabetic(c)) {
                result.append(c);
            }
            else if(c==']') {
                break;
            }
        }
        return result.toString();
    }
    public static void main(String[] args) {
        int no_of_tests;
        Scanner sc = new Scanner(System.in);
        no_of_tests = sc.nextInt();
        Solution s = new Solution();
        while(no_of_tests!=0){
            String input;
            input = sc.nextLine();
            System.out.println(s.decodeString(input));
            no_of_tests--;
        }
    }
}