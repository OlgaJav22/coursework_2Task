import java.time.LocalDateTime;

public class MonthlyTask extends Task implements Repeatable {
    public MonthlyTask(String title, TaskType taskType, String taskDescription, LocalDateTime dateTime) {
        super(title, taskType, taskDescription, dateTime);
    }

    @Override
    public boolean checkDate(LocalDateTime requestedDate) {
        return getDateTime().getMonth().equals(requestedDate.getMonth());
    }
}

