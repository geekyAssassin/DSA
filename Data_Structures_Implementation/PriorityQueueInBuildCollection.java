import java.util.Comparator;
import java.util.PriorityQueue;

class Task {
    String name;
    int priority;

    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{name='" + name + "', priority=" + priority + '}';
    }
}

class TaskComparator implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        // Higher Priority Tasks have lower priority value
        return Integer.compare(t1.priority, t2.priority);
    }
}

public class PriorityQueueInBuildCollection {

    public static void main(String[] args) {
        Comparator<Task> comparator = new TaskComparator();
        PriorityQueue<Task> pq = new PriorityQueue<>(comparator);

        pq.add(new Task("Task5", 5));
        pq.add(new Task("Task4", 4));
        pq.add(new Task("Task3", 3));
        pq.add(new Task("Task2", 2));

        while (!pq.isEmpty()) {
            Task task = pq.poll();
            System.out.println("Processing" + task);
        }

    }
    
}
