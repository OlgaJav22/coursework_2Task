import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            MyCalendar.addTask(scanner);
                            break;
                        case 2:
                            MyCalendar.editTask(scanner);
                            break;
                        case 3:
                            MyCalendar.deleteTask(scanner);
                            break;
                        case 4:
                            MyCalendar.getTasksByDay(scanner);
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }

    }

    private static void printMenu() {
        System.out.println("\n1. Добавить задачу\n2. Редактировать задачу\n3. Удалить задачу\n4. Получить задачу на указанный день\n0. Выход");
    }

}