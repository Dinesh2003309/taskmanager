import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private List<Task> tasks;
    private int nextId;

    public TaskManager() {
        tasks = new ArrayList<>();
        nextId = 1;
    }

    public void addTask(String title, String description, String priority, String status) {
        Task newTask = new Task(nextId++, title, description, priority, status);
        tasks.add(newTask);
        System.out.println("Task added successfully!");
    }

    public void editTask(int id, String title, String description, String priority, String status) {
        Task task = getTaskById(id);
        if (task != null) {
            task.setTitle(title);
            task.setDescription(description);
            task.setPriority(priority);
            task.setStatus(status);
            System.out.println("Task updated successfully!");
        } else {
            System.out.println("Task not found!");
        }
    }

    public void deleteTask(int id) {
        Task task = getTaskById(id);
        if (task != null) {
            tasks.remove(task);
            System.out.println("Task deleted successfully!");
        } else {
            System.out.println("Task not found!");
        }
    }

    public Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    public void viewAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    public void filterTasksByPriority(String priority) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.toString().contains(priority)) {
                System.out.println(task);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks with the given priority found.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        boolean exit = false;

        while (!exit) {
            System.out.println("\nTask Manager Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Edit Task");
            System.out.println("3. Delete Task");
            System.out.println("4. View All Tasks");
            System.out.println("5. Filter Tasks by Priority");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter priority (High/Medium/Low): ");
                    String priority = scanner.nextLine();
                    System.out.print("Enter status (Pending/In Progress/Completed): ");
                    String status = scanner.nextLine();
                    taskManager.addTask(title, description, priority, status);
                    break;
                case 2:
                    System.out.print("Enter task ID to edit: ");
                    int editId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter new title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter new description: ");
                    description = scanner.nextLine();
                    System.out.print("Enter new priority (High/Medium/Low): ");
                    priority = scanner.nextLine();
                    System.out.print("Enter new status (Pending/In Progress/Completed): ");
                    status = scanner.nextLine();
                    taskManager.editTask(editId, title, description, priority, status);
                    break;
                case 3:
                    System.out.print("Enter task ID to delete: ");
                    int deleteId = scanner.nextInt();
                    taskManager.deleteTask(deleteId);
                    break;
                case 4:
                    taskManager.viewAllTasks();
                    break;
                case 5:
                    System.out.print("Enter priority to filter by (High/Medium/Low): ");
                    String filterPriority = scanner.nextLine();
                    taskManager.filterTasksByPriority(filterPriority);
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }
}
