package Lists;

import java.util.Scanner;

public class Add_two_Numbers {
    public static ListNode createListNode(int num) {
        ListNode dummy = new ListNode(-1);

        // Extract digits from the number and create ListNode for each digit
        while (num > 0) {
            int digit = num % 10;
            num /= 10;
            ListNode newNode = new ListNode(digit);
            newNode.next = dummy.next;
            dummy.next = newNode;
        }

        return dummy.next;
    }

    // Function to display the digits stored in ListNode
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.value);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the No. Of Tests");
        int tests = sc.nextInt();
        while (tests-- != 0) {
            System.out.println("Enter the Number");
            int val = sc.nextInt();
            ListNode head = createListNode(val);
            printList(head);
        }
    }
}

class ListNode {
    int value;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.value = val;
    }

    ListNode(int val, ListNode next) {
        this.value = val;
        this.next = next;
    }
}
