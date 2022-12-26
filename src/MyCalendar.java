import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MyCalendar {
    private static final Map<Integer, Repeatable> actualTasks = new HashMap<>();

    public static void addTask(Scanner scanner) {
        try {
            scanner.nextLine();
            System.out.println("Введите название задачи: ");
            String title = ValidateUtils.checkString(scanner.nextLine());
            System.out.println("Введите описание задачи: ");
            String taskDescription = ValidateUtils.checkString(scanner.nextLine());
            System.out.println("Введите тип задачи: 0 - рабочая 1 - личная");
            TaskType taskType = TaskType.values()[scanner.nextInt()];
            System.out.println("Введите повторяемость задачи:\n1.Однократная\n2.Ежедневная\n3.Еженедельная\n4.Ежемесячная\n5.Ежегодная \t");
            int occurance = scanner.nextInt();
            System.out.println("Введите дату dd.MM.yyyy HH:mm");
            scanner.nextLine();
            createEvent(scanner, title, taskDescription, taskType, occurance);
            System.out.println("Для выхода нажмите Enter\n");
            scanner.nextLine();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createEvent(Scanner scanner, String title, String taskDescription, TaskType taskType, int occurance) {
        try {
            LocalDateTime eventDate = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            Repeatable task = null;
            try {
                task = createTask(occurance, title, taskDescription, taskType, eventDate);
                System.out.println("Создана задача " + task);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        } catch (DateTimeException e) {
            System.out.println("Неверный фоормат dd.MM.yyyy HH:mm, введите заново");
            createEvent(scanner, title, taskDescription, taskType, occurance);
        }
    }

    public static void editTask(Scanner scanner) {
        try {
            System.out.println("Редактирование задачи: введите Id");
            printActualTasks();
            int id = scanner.nextInt();
            if (!actualTasks.containsKey(id)) {
                throw new RuntimeException("Задача не найдена");
            }
            System.out.println("Редактирование 0-заголовок 1- описание");
            int menuCase = scanner.nextInt();
            switch (menuCase) {
                case 0:
                    scanner.nextLine();
                    System.out.println("Введите название задачи: ");
                    String title = scanner.nextLine();
                    Repeatable task = actualTasks.get(id);
                    task.setTitle(title);
                    break;
                case 1:
                    scanner.nextLine();
                    System.out.println("Введите описание задачи: ");
                    String taskDescription = scanner.nextLine();
                    Repeatable task1 = actualTasks.get(id);
                    task1.setTitle(taskDescription);
                    break;
            }

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteTask(Scanner scanner) {
        System.out.println("Текущие задачи\n");
        printActualTasks();
        System.out.println("Для удаления введите id задачи\n");
        int id = scanner.nextInt();
        actualTasks.remove(id);
    }

    public static void getTasksByDay(Scanner scanner) {
        System.out.println("Введите дату в формате dd.MM.yyyy");
        try {
            String date = scanner.next();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate requestedDate = LocalDate.parse(date, dateTimeFormatter);
            List<Repeatable> foundEvents = findTasksByDate(requestedDate);
            System.out.println("События на " + requestedDate + ":");
            for (Repeatable task : foundEvents) {
                System.out.println(task);
            }
        } catch (DateTimeException e) {
            System.out.println("Проверьте формат даты dd.MM.yyyy и введите еще раз");
        }
        scanner.nextLine();
        System.out.println("Для выхода нажмите Enter\n");
    }

    private static void printActualTasks() {
        for (Repeatable task : actualTasks.values()) {
            System.out.println(task);
        }
    }

    private static Repeatable createTask(int occurance, String title, String taskDescription, TaskType taskType, LocalDateTime localDateTime) throws RuntimeException {
        return switch (occurance) {
            case 0 -> {
                OncelyTask oncelyTask = new OncelyTask(title, taskType, taskDescription, localDateTime);
                actualTasks.put(oncelyTask.getId(), oncelyTask);
                yield oncelyTask;
            }
            case 1 -> {
                DayliTask task = new DayliTask(title, taskType, taskDescription, localDateTime);
                actualTasks.put(task.getId(), task);
                yield task;
            }
            case 2 -> {
                WeeklyTask task = new WeeklyTask(title, taskType, taskDescription, localDateTime);
                actualTasks.put(task.getId(), task);
                yield task;
            }
            case 3 -> {
                MonthlyTask task = new MonthlyTask(title, taskType, taskDescription, localDateTime);
                actualTasks.put(task.getId(), task);
                yield task;
            }
            case 4 -> {
                YearlyTask task = new YearlyTask(title, taskType, taskDescription, localDateTime);
                actualTasks.put(task.getId(), task);
                yield task;
            }
            default -> null;
        };
    }

    private static List<Repeatable> findTasksByDate(LocalDate date) {
        List<Repeatable> tasks = new ArrayList<>();
        for (Repeatable task : actualTasks.values()) {
            if (task.checkDate(date.atStartOfDay())) {
                tasks.add(task);
            }
        }
        return tasks;
    }

}

