import java.time.LocalDateTime;

public class YearlyTask extends Task implements Repeatable {
    public YearlyTask(String title, TaskType taskType, String taskDescription, LocalDateTime dateTime) {
        super(title, taskType, taskDescription, dateTime);
    }

    @Override
    public boolean checkDate(LocalDateTime requestedDate) {
        return getDateTime().getYear() == requestedDate.getYear();
    }
}

