import java.time.LocalDateTime;
import java.util.Objects;

public class Task {

    private String title;
    private TaskType taskType;
    private String taskDescription;
    private LocalDateTime dateTime;
    private static Integer counter = 1;
    private final Integer id;

    public Task(String title, TaskType taskType, String taskDescription, LocalDateTime dateTime) {
        this.title = ValidateUtils.checkString(title);
        this.taskType = taskType;
        this.taskDescription = ValidateUtils.checkString(taskDescription);
        this.dateTime = dateTime;
        this.id = counter++;
    }

    public String getTitle() {
        return title;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(title, task.title) && taskType == task.taskType && Objects.equals(taskDescription, task.taskDescription) && Objects.equals(dateTime, task.dateTime) && Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, taskType, taskDescription, dateTime, id);
    }

    @Override
    public String toString() {
        return '{' + "Заголовок: " + title + ", " +
                "тип реализации - " + taskType +
                ", описание (" + taskDescription + "), " +
                "дата:  " + dateTime +
                ", id=" + id +
                '}';
    }
}

