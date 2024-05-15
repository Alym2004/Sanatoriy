import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final DataBase dataBase = new DataBase();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        User user = new User("Director", "0", Role.DIRECTOR);
        User manager = new User("Manager", "0", Role.MANAGER);
        User personal = new User("Personal", "0", Role.PERSONAL);
        User client = new User("Client", "0", Role.CLIENT);

        Procedure procedure = new Procedure("Procedure", LocalDateTime.now().plusDays(2), "First procedure", 1000);
        Procedure procedure1 = new Procedure("Procedure1", LocalDateTime.now().plusDays(3), "Second procedure", 2000);
        Procedure procedure2 = new Procedure("Procedure2", LocalDateTime.now().plusDays(4), "Third procedure", 3000);

        dataBase.addProcedure(procedure);
        dataBase.addProcedure(procedure1);
        dataBase.addProcedure(procedure2);

        dataBase.addClient(client);
        dataBase.addUser(manager);
        dataBase.addUser(personal);
        dataBase.addUser(user);

        while (true) {
            System.out.println("Введите тип аккаунта (1-Директор  2-Менеджер  3-Персонал  4-Клиент  5-Выход):");
            int accountType = Integer.parseInt(scanner.nextLine());
            switch (accountType) {
                case 1:
                    authenticate("Директор");
                    break;
                case 2:
                    authenticate("Менеджер");
                    break;
                case 3:
                    authenticate("Персонал");
                    break;
                case 4:
                    authenticate("Клиент");
                    break;
                case 5:
                    System.out.println("Программа завершена.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный ввод. Попробуйте снова.");
            }
        }
    }

    private static void authenticate(String role) {
        System.out.print("Введите имя пользователя: ");
        String name = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        User currentUser = dataBase.authenticateUser(name, password);
        if (currentUser != null) {
            switch (role) {
                case "Директор":
                    if(!currentUser.getRole().equals(Role.DIRECTOR)) {
                        System.out.println("Ваш роль не соответсвует!");
                        break;
                    }
                    directorMenu();
                    break;
                case "Менеджер":
                    if(!currentUser.getRole().equals(Role.MANAGER)) {
                        System.out.println("Ваш роль не соответсвует!");
                        break;
                    }
                    managerMenu();
                    break;
                case "Персонал":
                    if(!currentUser.getRole().equals(Role.PERSONAL)) {
                        System.out.println("Ваш роль не соответсвует!");
                        break;
                    }
                    staffMenu();
                    break;
                case "Клиент":
                    if(!currentUser.getRole().equals(Role.CLIENT)) {
                        System.out.println("Ваш роль не соответсвует!");
                        break;
                    }
                    clientMenu(currentUser);
                    break;
            }
        } else {
            System.out.println("Неправильные учетные данные.");
        }
    }

    private static void directorMenu() {
        boolean running = true;
        while (running) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить пользователя\n2. Удалить пользователя\n3. Просмотреть статистику\n4. Управление финансами\n5. Выход");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    removeUser();
                    break;
                case 3:
                    viewStatistics();
                    break;
                case 4:
                    financialControl();
                    break;
                case 5:
                    running = false;
                    break;
            }
        }
    }

    private static void addUser() {
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        System.out.print("Введите роль (1 - Менеджер, 2 - Персонал, 3 - Клиент, 4 - Директор): ");
        int roleId = Integer.parseInt(scanner.nextLine());
        Role role = Role.values()[roleId - 1];
        User newUser = new User(name, password, role);
        dataBase.addUser(newUser);
        System.out.println("Пользователь добавлен.");
    }

    private static void removeUser() {
        System.out.print("Введите имя пользователя для удаления: ");
        String userName = scanner.nextLine();
        dataBase.removeUser(userName);
        System.out.println("Пользователь удален.");
    }

    private static void viewStatistics() {
        System.out.println("Статистика пользователей:");
        dataBase.getUserStats().forEach((role, count) -> System.out.println(role + ": " + count));
    }

    private static void financialControl() {
        boolean running = true;
        while (running) {
            System.out.println("1. Добавить финансовую запись");
            System.out.println("2. Просмотреть все финансовые записи");
            System.out.println("3. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Введите сумму: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Введите описание: ");
                    String description = scanner.nextLine();
                    System.out.print("Введите дату (гггг-мм-дд): ");
                    String dateString = scanner.nextLine();
                    LocalDate date = LocalDate.parse(dateString);
                    dataBase.addFinancialRecord(new FinancialRecord(date, amount, description));
                    System.out.println("Финансовая запись добавлена.");
                    break;
                case 2:
                    System.out.println("Все финансовые записи:");
                    for (FinancialRecord record : dataBase.getAllFinancialRecords()) {
                        System.out.println(record.getDate() + ": " + record.getAmount() + " сом - " + record.getDescription());
                    }
                    break;
                case 3:
                    running = false;
                    break;
            }
        }
    }

    private static void managerMenu() {
        boolean running = true;
        while (running) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить посетителя\n2. Удалить посетителя\n3. Показать список всех посетителей\n4. Показать отчет по активности посетителей\n5. Анализ эффективности персонала\n6. Управление финансами\n7. Отзывы \n8. Процедура\n9. Выход");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addClient();
                    break;
                case 2:
                    removeClient();
                    break;
                case 3:
                    showAllClients();
                    break;
                case 4:
                    showClientActivityReport();
                    break;
                case 5:
                    Map<Role, Integer> staffEfficiency = dataBase.getStaffEfficiency();
                    staffEfficiency.forEach((role, count) ->
                            System.out.println("Роль: " + role + ", Обработано процедур: " + count));
                    break;
                case 6:
                    financialControl();
                    break;
                case 7:
                    System.out.println("Все отзывы:");
                    for (Feedback feedback : dataBase.getAllFeedbacks()) {
                        System.out.println(feedback.getCreated() + " " + feedback.getAuthor().getName() + ": " + feedback.getContent());
                    }
                    break;
                case 8:
                    procedureFunctions();
                    break;
                case 9:
                    running = false;
                    break;
            }
        }
    }

    private static void addClient() {
        System.out.print("Введите имя посетителя: ");
        String name = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        User newUser = new User(name, password, Role.CLIENT);
        dataBase.addUser(newUser);
        System.out.println("Посетитель добавлен.");
    }

    private static void removeClient() {
        System.out.print("Введите имя посетителя для удаления: ");
        String clientName = scanner.nextLine();
        dataBase.removeUser(clientName);
        System.out.println("Посетитель удален.");
    }

    private static void showAllClients() {
        System.out.println("Список всех посетителей:");
        dataBase.getAllClients().forEach(client -> System.out.println("Имя: " + client.getName()));
    }

    private static void showClientActivityReport() {
        System.out.println("Отчет по активности посетителей:");
        dataBase.getClientActivityReport().forEach((userName, visits) ->
                System.out.println("Имя: " + userName + ", Посещения: " + visits));
    }

    private static void procedureFunctions() {
        boolean running = true;
        while (running) {
            System.out.println("1. Просмотр статистики по процедурам");
            System.out.println("2. Редактировать процедуру");
            System.out.println("3. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Map<String, Integer> procedureStats = dataBase.getProcedureUsageStats();
                    procedureStats.forEach((procName, usage) -> System.out.println("Процедура: " + procName + ", Количество посетителей: " + usage));
                    break;
                case 2:
                    System.out.print("Введите название процедуры для редактирования: ");
                    String editName = scanner.nextLine();
                    Procedure proc = dataBase.findProcedureByName(editName);
                    if (proc != null) {
                        System.out.print("Введите новое описание для процедуры: ");
                        String newDescription = scanner.nextLine();
                        proc.setDescription(newDescription);
                        System.out.println("Информация обновлена.");
                    } else {
                        System.out.println("Процедура не найдена.");
                    }
                    break;
                case 3:
                    running = false;
                    break;
            }
        }
    }



    private static void staffMenu() {
        boolean running = true;
        while (running) {
            System.out.println("Выберите действие:");
            System.out.println("1. Просмотреть список процедур\n2. Добавить процедуру\n3. Удалить процедуру\n4. Найти посетителя\n5. Выход");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    listProcedures();
                    break;
                case 2:
                    addProcedure();
                    break;
                case 3:
                    removeProcedure();
                    break;
                case 4:
                    findClient();
                    break;
                case 5:
                    running = false;
                    break;
            }
        }
    }

    private static void listProcedures() {
        System.out.println("Список всех процедур:");
        dataBase.getAllProcedures().forEach(proc -> System.out.println(proc.getName() + ": " + proc.getDescription() + " at " + proc.getTime()));
    }

    private static void addProcedure() {
        System.out.print("Введите название процедуры: ");
        String name = scanner.nextLine();
        System.out.print("Введите описание процедуры: ");
        String description = scanner.nextLine();
        System.out.print("Введите дату и время процедуры (гггг-мм-ддТчч:мм): ");
        LocalDateTime time = LocalDateTime.parse(scanner.nextLine());
        System.out.print("Введите сумму цанаториюЖ ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        Procedure newProcedure = new Procedure(name, time, description, price);
        dataBase.addProcedure(newProcedure);
        System.out.println("Процедура добавлена.");
    }

    private static void removeProcedure() {
        System.out.print("Введите название процедуры для удаления: ");
        String procedureName = scanner.nextLine();
        if (dataBase.removeProcedure(procedureName)) {
            System.out.println("Процедура удалена.");
        } else {
            System.out.println("Процедура не найдена.");
        }
    }

    private static void findClient() {
        System.out.print("Введите имя посетителя для поиска: ");
        String clientName = scanner.nextLine();
        User client = dataBase.findClientByName(clientName);
        if (client != null) {
            System.out.println("Найден посетитель: " + client.getName());
        } else {
            System.out.println("Посетитель не найден.");
        }
    }



    private static void clientMenu(User currentUser) {
        boolean running = true;
        while (running) {
            System.out.println("1. Просмотр расписания процедур\n2. Просмотр истории процедур\n3. Просмотр информации о здоровье\n4. Купить процедуру\n5. Отзыв\n6. Выход");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    viewSchedule(currentUser);
                    break;
                case 2:
                    viewHistory(currentUser);
                    break;
                case 3:
                    viewHealthInfo(currentUser);
                    break;
                case 4:
                    buyProcedure(currentUser);
                    break;
                case 5:
                    System.out.print("Введите ваш отзыв: ");
                    String content = scanner.nextLine();
                    dataBase.addFeedback(new Feedback(currentUser, content));
                    System.out.println("Ваш отзыв успешно добавлен!");
                    break;
                case 6:
                    running = false;
                    break;
            }
        }
    }

    private static void buyProcedure(User currentUser) {
        System.out.println("Все доступные процедуры: ");
        dataBase.getActiveProcedures().forEach(procedure -> System.out.println("Имя: " + procedure.getName() + "\tВремя: " + procedure.getTime() + "\tЦена: " + procedure.getPrice()));
        System.out.print("Введите название процедур: ");
        String procName = scanner.nextLine();
        Procedure procedure = dataBase.findProcedureByName(procName);
        if(procedure != null) {
            List<User> users = new ArrayList<>();
            List<Procedure> attachProcedure = new ArrayList<>();
            attachProcedure.add(procedure);
            currentUser.setScheduledProcedures(attachProcedure);
            users.add(currentUser);
            procedure.setAttendees(users);
            System.out.println("Процедура успешно куплено");
        } else {
            System.out.println("Процедура не найдена");
        }
    }

    private static void viewSchedule(User currentUser) {
        System.out.println("Расписание процедур:");
        currentUser.getScheduledProcedures().forEach(proc -> System.out.println(proc.getName() + " в " + proc.getTime() + " - " + proc.getDescription()));
    }

    private static void viewHistory(User currentUser) {
        System.out.println("История процедур:");
        currentUser.getPaymentHistory().forEach(System.out::println);
    }

    private static void viewHealthInfo(User currentUser) {
        System.out.println("Информация о здоровье: " + currentUser.getHealthInfo());
    }
}
