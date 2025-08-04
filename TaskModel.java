
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TaskModel{
    
      
        private String taskName;
        private String taskDescription;
        private LocalDate taskdate;
        private LocalTime tasktime;
        private boolean isCompleted;
        LocalDateTime startingDateTime ;
        

        public TaskModel( String taskName, LocalDate taskdate, LocalTime tasktime, String taskDescription)
        { 
            this.taskName = taskName;
            this.taskDescription = taskDescription;
            this.isCompleted = false; 
            this.taskdate = taskdate;
            this.tasktime = tasktime;
            startingDateTime = LocalDateTime.now(); // Set the starting date and time
        }
        

        public String getTaskName() {
            return taskName;
        }

        public String getTaskDescription() {
            return taskDescription;
        }
        public LocalDate getTaskdate() {
            return taskdate;
        }

        public LocalTime getTasktime() {
            return tasktime;
        }

        public boolean isCompleted() {
            return isCompleted;
        }

        public void setCompleted(boolean completed) {
            isCompleted = completed;
        }

        public LocalDateTime getStartingDateTime() {
            return startingDateTime;
        }
        
        public void setCompleted() {
            this.isCompleted = true;
        }

        public void settaskName(String taskName) {
            this.taskName = taskName;
        }

        public void settaskdate(LocalDate taskdate) {
            this.taskdate = taskdate;
        }

        public void settasktime(LocalTime tasktime) {
            this.tasktime = tasktime;
        }


    
}
