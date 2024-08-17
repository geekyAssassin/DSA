
class Priority_Queue_Item {
    int item;
    int priority;
}

public class PriorityQueueUsingArrays {

    Priority_Queue_Item pq[] = new Priority_Queue_Item[1000];
    int size = -1;

    void enqueue(int item, int priority) {
        size++;
        pq[size] = new Priority_Queue_Item();
        pq[size].item = item;
        pq[size].priority = priority;
    }

    int peek() {
        int highest_priority = Integer.MIN_VALUE;
        int ind = -1;

        for (int i = 0; i <= size; i++) {
            if (highest_priority == pq[i].priority &&
                    ind > -1 &&
                    pq[ind].item < pq[i].item) {
                highest_priority = pq[i].priority;
                ind = i;
            } else if (highest_priority < pq[i].priority) {
                highest_priority = pq[i].priority;
                ind = i;
            }
        }
        return ind;
    }

    void dequeue() {
        int ind = peek();

        for (int i = ind; i < size; i++) {
            pq[i] = pq[i + 1];
        }

        size--;
    }

    void printQueue() {
        for (int i = 0; i <= size; i++)
            System.out.println(pq[i].item + " " + pq[i].priority);
    }

    public static void main(String[] args) {
        PriorityQueueUsingArrays priorityQueueUsingArrays = new PriorityQueueUsingArrays();

        priorityQueueUsingArrays.enqueue(10, 1);
        priorityQueueUsingArrays.enqueue(13, 2);
        priorityQueueUsingArrays.enqueue(14, 2);
        priorityQueueUsingArrays.enqueue(9, 3);

        priorityQueueUsingArrays.printQueue();

        priorityQueueUsingArrays.dequeue();
        priorityQueueUsingArrays.dequeue();

        priorityQueueUsingArrays.printQueue();
    }
}