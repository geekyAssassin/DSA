package Arrays;

import java.util.Scanner;

public class Second_Max {

    public static void calculateSecondMax(int[] arr) {
        int max_ele_index = -1;
        int second_Max_index = -1;

        for(int i=0;i<arr.length;i++) {
            if(max_ele_index == -1 || arr[i] > arr[max_ele_index]) {
                second_Max_index = max_ele_index;
                max_ele_index = i;
            } else if(second_Max_index == -1 || arr[i] > arr[second_Max_index]) {
                second_Max_index = i;
            }
        }
        System.out.println("Max Element and Index is " + arr[max_ele_index] + " " + max_ele_index);
        System.out.println("Second Max Element and Index is "+ arr[second_Max_index] + " " + second_Max_index);

    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the length of the array");  
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++)
            arr[i] = sc.nextInt();
        calculateSecondMax(arr);     
    }
    
}
