import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class To_Do_App {

    static class oprations_todo {

        Scanner sc1 = new Scanner(System.in);
        FileHandler fh = new FileHandler();

        public void addtask() {
            String taskName;
            String taskDescription;
            LocalDate taskdate;
            LocalTime tasktime;

            System.out.println("Enter Task Name:");
            taskName = sc1.nextLine();
            System.out.println("Enter Task Description:");
            taskDescription = sc1.nextLine();
            System.out.println("Enter Task Date (YYYY-MM-DD):");
            taskdate = LocalDate.parse(sc1.nextLine());
            System.out.println("Enter Task Time (HH:MM):");
            tasktime = LocalTime.parse(sc1.nextLine());
            TaskModel task = new TaskModel(taskName, taskdate, tasktime, taskDescription);
            fh.addTaskToFile(task);
        }
     
        public void editTask(String taskname)
        {
            fh.readTaskFromFile(taskname);
            System.out.println(" this is old task enter new task details \n");
            int choice ;
             System.out.println("---- 1 to edit task name \n");
            System.out.println("---- 2 to edit task description \n");
            System.out.println("---- 3 to edit task date \n");    
            System.out.println("---- 4 to edit task time \n");
            choice = sc1.nextInt();
            sc1.nextLine();
            
            switch (choice) {
                 case 1:
                    System.out.println("Enter new task name:");
                    String newTaskName = sc1.nextLine();
                    fh.editTaskname(newTaskName.trim(), taskname);
                    break;

                    case 2:
                    System.out.println("Enter new task description:");
                    String newTaskDescription = sc1.nextLine(); 
                    fh.editdescription(taskname, newTaskDescription);
                    break;

                    case 3:
                    System.out.println("Enter new task date (YYYY-MM-DD):");
                    LocalDate newTaskDate = LocalDate.parse(sc1.nextLine()); 
                    fh.editTaskcompletiondate(taskname, newTaskDate);
                    break;

                    case 4:
                    System.out.println("Enter new task time (HH:MM):");
                    LocalTime newTaskTime = LocalTime.parse(sc1.nextLine()); 
                    fh.edittaskcompletionTime(taskname, newTaskTime);
                    break;


                    default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }

        public void viewtasks() {
            System.out.println(" -----  press 1 Show all tasks");
            System.out.println(" -----  press 2 Show tassk by name");
            System.out.println(" -----  press 3 Show completed tasks");
            System.out.println(" -----  press 4 Show pending tasks");
            int chice = sc1.nextInt();
            switch(chice)
            {
                case 1:
                    fh.viewtasks();
                    break;

                case 2:
                    System.out.println("Enter the name of the task to view:");
                    String taskName = sc1.next();
                    TaskModel task = fh.readTaskFromFile(taskName);
                    if (task != null) {
                        System.out.println("Task Details: " );
                         fh.printtask(task);

                    } else {
                        System.out.println("Task not found.");
                    }
                    break;

                case 3:
                    fh.viewcompletedtasks();
                    break;

                case 4:
                    fh.viewpendingtasks();
                    break;
                
                default:
                    System.out.println("Invalid choice, please try again.");    
                    break;
            }
        }



    }

    public To_Do_App()
    {
        System.out.println("--------------------------[  Welcome to the To-Do List Management System  ]--------------------------");
        System.out.println("------------------You can add, view, and manage your tasks here. Let's get started!------------------");
        System.out.println();
        int chioce;
        Scanner sc = new Scanner(System.in);
        oprations_todo todo = new oprations_todo();

        while (true) {
            System.out.println();
            System.out.println("Please choose an option:");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Edit Task");
            System.out.println("5. Delete Task");
            System.out.println("6. Exit");

            chioce = sc.nextInt();
            sc.nextLine(); // consume leftover newline

            switch (chioce) {
                case 1:
                    todo.addtask();
                    break;
                case 2:
                    todo.viewtasks();
                    break;

                case 3:
                    System.out.println("Enter the name of the task to mark as completed:");
                    String taskName = sc.nextLine();
                    todo.fh.marktaskascompleted(taskName);
                    break;
                case 4:
                    System.out.println("Enter the name of the task to edit:");
                    String taskToEdit = sc.nextLine();
                    todo.editTask(taskToEdit);
                    break;

                case 5:
                     System.out.println("Enter Task name to delete ");
                     String task = sc.nextLine();
                     todo.fh.deleteTask(task);

                case 6:
                    System.out.println("Exiting To-Do List Management System.");
                    return;
                    
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
