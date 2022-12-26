import java.time.LocalDateTime;

public class OncelyTask extends Task implements Repeatable {
    public OncelyTask(String title, TaskType taskType, String taskDescription, LocalDateTime dateTime) throws RuntimeException {
        super(title, taskType, taskDescription, dateTime);
    }

    @Override
    public boolean checkDate(LocalDateTime requestedDate) {
        return getDateTime().toLocalDate().equals(requestedDate.toLocalDate());
    }
}
