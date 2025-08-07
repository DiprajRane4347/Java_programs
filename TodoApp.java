import java.util.ArrayList;
import java.util.Scanner;

public class TodoApp {
    private static ArrayList<String> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\nTODO LIST APPLICATION");
        System.out.println("=====================");

        while (true) {
            displayMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addTask();
                    break;
                case "2":
                    viewTasks();
                    break;
                case "3":
                    updateTask();
                    break;
                case "4":
                    deleteTask();
                    break;
                case "5":
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Add Task");
        System.out.println("2. View Tasks");
        System.out.println("3. Update Task");
        System.out.println("4. Delete Task");
        System.out.println("5. Exit");
        System.out.print("Enter your choice (1-5): ");
    }

    private static void addTask() {
        System.out.print("\nEnter task description: ");
        String task = scanner.nextLine().trim();
        if (!task.isEmpty()) {
            tasks.add(task);
            System.out.println("Task added successfully!");
        } else {
            System.out.println("Task description cannot be empty.");
        }
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks available.");
            return;
        }

        System.out.println("\nYour Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void updateTask() {
        viewTasks();
        if (tasks.isEmpty()) return;

        try {
            System.out.print("\nEnter task number to update: ");
            int taskNumber = Integer.parseInt(scanner.nextLine());
            if (taskNumber < 1 || taskNumber > tasks.size()) {
                System.out.println("Invalid task number.");
                return;
            }

            System.out.print("Enter new task description: ");
            String newTask = scanner.nextLine().trim();
            if (!newTask.isEmpty()) {
                tasks.set(taskNumber - 1, newTask);
                System.out.println("Task updated successfully!");
            } else {
                System.out.println("Task description cannot be empty.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    private static void deleteTask() {
        viewTasks();
        if (tasks.isEmpty()) return;

        try {
            System.out.print("\nEnter task number to delete: ");
            int taskNumber = Integer.parseInt(scanner.nextLine());
            if (taskNumber < 1 || taskNumber > tasks.size()) {
                System.out.println("Invalid task number.");
                return;
            }

            String removedTask = tasks.remove(taskNumber - 1);
            System.out.println("Task '" + removedTask + "' deleted successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }
}
