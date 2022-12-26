import java.time.LocalDateTime;

public class WeeklyTask extends Task implements Repeatable {
    public WeeklyTask(String title, TaskType taskType, String taskDescription, LocalDateTime dateTime) {
        super(title, taskType, taskDescription, dateTime);
    }

    @Override
    public boolean checkDate(LocalDateTime requestedDate) {
        return getDateTime().getDayOfWeek().equals(requestedDate.getDayOfWeek());
    }
}

