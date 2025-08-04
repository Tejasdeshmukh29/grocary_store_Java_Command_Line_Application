import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FileHandler {

    public FileHandler() {
        File folder = new File("tasks");
        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            if (created) {
                System.out.println("Tasks folder created.");
            } else {
                System.out.println("Failed to create tasks folder.");
            }
        }

        if (!folder.canRead() || !folder.canWrite()) {
            System.out.println("Warning: tasks folder not accessible (read/write denied).");
        }
    }

    public void addTaskToFile(TaskModel task) {
        String filename = "tasks/" + task.getTaskName() + ".txt"; // added ()

        try {
            File file = new File(filename);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            writer.write(task.getTaskName());
            writer.newLine();
            writer.write(Boolean.toString(task.isCompleted()));
            writer.newLine();
            writer.write(task.getTaskdate().toString());
            writer.newLine();
            writer.write(task.getTasktime().toString());
            writer.newLine();
            writer.write(task.getTaskDescription());
            writer.newLine();

            writer.close();
            System.out.println("\n Task saved in file successfully! \n");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the task: " + e.getMessage());
        }
    }

    public void marktaskascompleted(String taskname) {
        try {
            if (taskname == null || taskname.isEmpty()) {
                System.out.println("Task name cannot be empty.");
                return;
            } else {
                File file = new File("tasks" + File.separator + taskname + ".txt");
                if (!file.exists()) {
                    System.out.println("file not found !");
                    return;
                }
                ArrayList<String> lines = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                reader.close();

                if (lines.size() >= 2)
                    lines.set(1, "true");
                else {
                    System.out.println("file format is not correct !");
                }

                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                for (String l : lines) {
                    writer.write(l);
                    writer.newLine();
                }
                writer.close();
                System.out.println("Task marked as completed successfully!");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while marking the task as completed: " + e.getMessage());
        }
    }

    public TaskModel readTaskFromFile(String taskName) {
        try {
            String filename = "tasks" + File.separator + taskName + ".txt";
            File file = new File(filename);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String name = reader.readLine();
            boolean isCompleted = Boolean.parseBoolean(reader.readLine());
            LocalDate date = LocalDate.parse(reader.readLine());
            LocalTime time = LocalTime.parse(reader.readLine());

            StringBuilder descriptionBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                descriptionBuilder.append(line).append("\n");
            }

            reader.close();

            TaskModel task1 = new TaskModel(name, date, time, descriptionBuilder.toString().trim());
            task1.setCompleted(isCompleted);
            return task1;

        } catch (IOException e) {
            System.out.println("An error occurred while reading the task: " + e.getMessage());
            return null;
        }

    }

    public void viewtasks() {
        File folder = new File("tasks");

        // Check read access before listing
        if (!folder.exists() || !folder.isDirectory() || !folder.canRead()) {
            System.out.println("Cannot access tasks directory. Check permissions or existence.");
            return;
        }

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files == null || files.length == 0) {
            System.out.println("No tasks found.");
            return;
        }

        for (File file : files) {
            String taskname = file.getName().replace(".txt", "");
            TaskModel task = readTaskFromFile(taskname);
            if (task != null) {
                printtask(task);
            }
        }
    }

    public void editTaskname(String newname, String taskname) {
        try {
            if (taskname == null || taskname.isEmpty()) {
                System.out.println("Task name cannot be empty.");
                return;
            } else {
                File file = new File("tasks" + File.separator + taskname + ".txt");
                if (!file.exists()) {
                    System.out.println("file not found !");
                    return;
                }
                ArrayList<String> lines = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                reader.close();

                if (lines.size() >= 1)
                    lines.set(0, newname);
                else {
                    System.out.println("file format is not correct !");
                }

                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                for (String l : lines) {
                    writer.write(l);
                    writer.newLine();
                }
                writer.close();
                File newFile = new File("tasks" + File.separator + newname + ".txt");
                file.renameTo(newFile);
                System.out.println("Task name changed successfully!");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while marking the task as completed: " + e.getMessage());
        }
    }

    public void editTaskcompletiondate(String taskname, LocalDate changeddate) {
        try {
            if (taskname == null || taskname.isEmpty()) {
                System.out.println("Task name cannot be empty.");
                return;
            } else {
                File file = new File("tasks" + File.separator + taskname + ".txt");
                if (!file.exists()) {
                    System.out.println("file not found !");
                    return;
                }
                ArrayList<String> lines = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                reader.close();

                if (lines.size() >= 3)
                    lines.set(2, changeddate.toString());
                else {
                    System.out.println("file format is not correct !");
                }

                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                for (String l : lines) {
                    writer.write(l);
                    writer.newLine();
                }
                writer.close();
                System.out.println("Task completion date changed successfully!");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while marking the task as completed: " + e.getMessage());
        }
    }

    public void edittaskcompletionTime(String taskname, LocalTime changedtime) {
        try {
            if (taskname == null || taskname.isEmpty()) {
                System.out.println("Task name cannot be empty.");
                return;
            } else {
                File file = new File("tasks" + File.separator + taskname + ".txt");
                if (!file.exists()) {
                    System.out.println("file not found !");
                    return;
                }
                ArrayList<String> lines = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                reader.close();

                if (lines.size() >= 4)
                    lines.set(3, changedtime.toString());
                else {
                    System.out.println("file format is not correct !");
                }

                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                for (String l : lines) {
                    writer.write(l);
                    writer.newLine();
                }
                writer.close();
                System.out.println("Task completion time changed successfully!");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while marking the task as completed: " + e.getMessage());
        }
    }

    public void editdescription(String taskname, String newDescription) {
        try {
            if (taskname == null || taskname.isEmpty()) {
                System.out.println("Task name cannot be empty.");
                return;
            } else {
                File file = new File("tasks" + File.separator + taskname + ".txt");
                if (!file.exists()) {
                    System.out.println("file not found !");
                    return;
                }
                ArrayList<String> lines = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                reader.close();

                if (lines.size() >= 5)
                    lines.set(4, newDescription);
                else {
                    System.out.println("file format is not correct !");
                }

                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                for (String l : lines) {
                    writer.write(l);
                    writer.newLine();
                }
                writer.close();
                System.out.println("Task Description changed successfully!");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while marking the task as completed: " + e.getMessage());
        }
    }

    public void viewcompletedtasks() {
        File folder = new File("tasks");
        File[] files = folder.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.exists()) {
                    String taskname = file.getName().replace(".txt", "");
                    if (taskisCompleted(taskname)) {
                        TaskModel task = readTaskFromFile(taskname);
                        if (task != null) {
                            printtask(task);
                        } else {
                            System.out.println("Error reading task from file: " + file.getName());
                        }
                    }
                }

            }
        }
    }

    public void viewpendingtasks() {
        File folder = new File("tasks");
        File[] files = folder.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.exists()) {
                    String taskname = file.getName().replace(".txt", "");
                    if (!taskisCompleted(taskname)) {
                        TaskModel task = readTaskFromFile(taskname);
                        if (task != null) {
                            printtask(task);
                        } else {
                            System.out.println("Error reading task from file: " + file.getName());
                        }
                    }
                }
            }
        }
    }

    public boolean taskisCompleted(String taskname) {
        try {
            if (taskname == null || taskname.isEmpty()) {
                System.out.println("task name cannot be empty !");
                return false;

            }
            File file = new File("tasks" + File.separator + taskname + ".txt");
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                for (int i = 0; i < 2; i++) {
                    line = reader.readLine();
                    if (i == 1) {
                        return line.equalsIgnoreCase("True");
                    }
                }

            }
        } catch (Exception e) {
            System.out.println("An error occurred while checking if the task is completed: " + e.getMessage());
            return false;
        }
        return false;
    }

    public void printtask(TaskModel task) {
        System.out.println("-------------------------------");
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-MM-YYYY  HH:mm:ss");
        System.out.println("Task Name: " + task.getTaskName());
        System.out.println("Completed: " + task.isCompleted());
        System.out.println("Date: " + task.getTaskdate());
        System.out.println("Time: " + task.getTasktime());
        System.out.println("Description: " + task.getTaskDescription());
        System.out.println("Time of creating Task : " + (task.startingDateTime).format(formater));
        System.out.println("-------------------------------\n");
    }

    public void deleteTask(String Taskname) {
        File file = new File("tasks" + File.separator + Taskname + ".txt");
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("file deleted Successfully");
            } else {
                System.out.println("file not deleted Successfully");
            }
        } else {
            System.out.println("File does not exist.");
        }
    }
}
