import java.util.*;

class Sort_a_Stack {
    public void solution(Stack<Integer> stack) {
        if(stack.size() ==1)
        {
            return;
        }
        int temp = stack.pop();
        solution(stack);
        insert(stack,temp);
    }

    private void insert(Stack<Integer> stack, int temp) {
        if(stack.empty() || temp >=stack.peek())
        {
            stack.push(temp);
            return;
        }

        int prev_value = stack.pop();
        insert(stack, temp);
        stack.push(prev_value);
    }

    public static void main(String args[]) {

        int no_of_tests, no_of_elements;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of tests");
        no_of_tests = sc.nextInt();
        Sort_a_Stack s = new Sort_a_Stack();
        while (no_of_tests != 0) {
            System.out.println("Enter the no of elements of the stack");
            no_of_elements = sc.nextInt();
            System.out.println("Enter the elements of the stack");
            Stack<Integer> stack = new Stack<Integer>();
            for (int i = 0; i < no_of_elements; i++) {
                stack.push(sc.nextInt());
            }
            s.solution(stack);
            System.out.println("Enter the elements of the stack after computation");
            for (int i = 0; i < no_of_elements; i++) {
                System.out.println(stack.pop());
            }
            no_of_tests--;
        }
    }
}