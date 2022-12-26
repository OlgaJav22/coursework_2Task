import java.time.LocalDateTime;

public interface Repeatable {
    boolean checkDate(LocalDateTime localDateTime);

    void setTitle(String title);

    LocalDateTime getDateTime();
}

