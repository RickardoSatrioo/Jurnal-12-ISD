import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Scanner;

class Task {
    public int priority;
    public String taskName;

    public Task(int priority, String taskName) {
        this.priority = priority;
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }
}

class TaskPriorityComparator implements Comparator<Task> {
    // Override method compare() of Comparator to order by priority in descending order
    public int compare(Task t1, Task t2) {
        return Integer.compare(t1.priority, t2.priority);
    }
}

public class TaskManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating a priority queue with an initial capacity of 5 and using TaskPriorityComparator
        PriorityQueue<Task> taskQueue = new PriorityQueue<>(5, new TaskPriorityComparator());

        char continueChoice;

        do {
            System.out.println("To-Do List Menu");
            System.out.println("1. Add Task \n2. View Next Task \n3. Mark Task as Completed \n4. Clear To-Do List");
            int userChoice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (userChoice) {
                case 1:
                    System.out.print("Enter task name: ");
                    String taskName = scanner.nextLine();
                    System.out.print("Enter priority: ");
                    int priority = scanner.nextInt();

                    taskQueue.add(new Task(priority, taskName));
                    System.out.println("Task \"" + taskName + "\" added to the list with priority " + priority);
                    break;
                case 2:
                    if (!taskQueue.isEmpty()) {
                        System.out.println("Next task to complete: " + taskQueue.peek().getTaskName());
                    } else {
                        System.out.println("No tasks in the list.");
                    }
                    break;
                case 3:
                    if (!taskQueue.isEmpty()) {
                        System.out.println("Completed: " + taskQueue.poll().getTaskName());
                        if (!taskQueue.isEmpty()) {
                            System.out.println("Next task to complete: " + taskQueue.peek().getTaskName());
                        } else {
                            System.out.println("No more tasks in the list.");
                        }
                    } else {
                        System.out.println("No tasks to complete.");
                    }
                    break;
                case 4:
                    taskQueue.clear();
                    System.out.println("To-Do List cleared.");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }

            System.out.println("Do you want to continue? (y/n)");
            continueChoice = scanner.next().charAt(0);
        } while (continueChoice == 'Y' || continueChoice == 'y');

        scanner.close();
    }
}
