import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataBase dataBase = new DataBase();
        List<User> userList = new ArrayList<>();
        User manager = new User();
        manager.setName("Alym");
        manager.setPassword("0");
        manager.setRole(Role.MANAGER);

        User trash = new User();
        trash.setName("Trash");
        trash.setPassword("10");

        User client = new User();
        client.setName("Numu");
        client.setPassword("1");
        client.setRole(Role.CLIENT);

        User personal = new User();
        personal.setName("Azim");
        personal.setPassword("0");
        personal.setRole(Role.PERSONAL);


        userList.add(manager);
        userList.add(client);
        userList.add(personal);
        dataBase.setUserList(userList);

        userList.add(trash);

        while (true) {
            System.out.print("Name: ");
            String userName = scanner.nextLine();
            System.out.print("Password: ");
            String userPassword = scanner.nextLine();
            int countAttempt = 0;
            User user1 = dataBase.findByName(userName);
            if (user1 == null) {
                System.out.println("User not found");
            } else if (user1.getPassword().equals(userPassword)) {
                if (user1.getRole().equals(Role.MANAGER)) {
                    System.out.println("User is manager");
                    System.out.println("Приветствую дорогой, Менеджер!\nПожалуйста наберите номер меню для работы с программой, если закончили, то наберите 8");
                    System.out.println("1. Показать список посетителей\n2. Показать количество посетителей\n3. Поиск посетителя:\n4. Изменить цену для процедур\n5. Изменить время - название процедур\n6. Показать посетителя с максимальным количеством посещений\n7. Показать посетителя с минимальным количеством посещений\n8. Выход");
                    int getInput = scanner.nextInt();
                    if (getInput == 8){
                        System.out.println("Программа завершена, мы будем рады вашему возвращению!");
                            break;
                        } else if (getInput == 1){
                            System.out.println("patients");
                        } else if (getInput == 2) {
                            System.out.println("amount of patients");
                        } else if (getInput == 3) {
                            System.out.println("search");
                        } else if (getInput == 4) {
                            System.out.println("Изменить цену для процедур");
                        } else if (getInput == 5) {
                            System.out.println("Изменить время - название процедур");
                        } else if (getInput == 6) {
                            System.out.println(" Показать посетителя с максимальным количеством посещений");
                        } else if (getInput == 7) {
                            System.out.println("Показать посетителя с минимальным количеством посещений");
                        } else {
                            break;
                        }
                } else if (user1.getRole().equals(Role.CLIENT)) {
                    System.out.println("User is client");
                    System.out.println("\n" + "Приветствую дорогой, Посетитель!\n" +
                            "Пожалуйста наберите номер меню для работы с программой, если закончили, то наберите 7:\n");
                    System.out.println("1. Показать историю посещений процедур\n2.Показать последнюю дату посещения процедур\n3.Показать историю оплаты процедурам\n4.Показать расписание к процедурам\n5.Показать мою информацию\n6.Показать мой день проживания в Санатории\n7.Выход ");
                    int getInput = scanner.nextInt();
                    if (getInput == 7){
                        System.out.println("Программа завершена, мы будем рады вашему возвращению!");
                        break;
                    } else if (getInput == 1) {

                    } else if (getInput == 2) {

                    } else if (getInput == 3) {

                    } else if (getInput == 4) {

                    } else if (getInput == 5) {

                    } else if (getInput == 6) {

                    }

                } else if (user1.getRole().equals(Role.PERSONAL)) {
                    System.out.println("User is personal");
                    System.out.println("\n" + "Приветствую уважаемая, Персонал!\n" +
                            "Пожалуйста наберите номер меню для работы с программой, если закончили, то наберите 6:\n");
                    System.out.println("1. Показать список процедур\n2. Найти посетителя\n3. Показать все процедуры\n4. Показать расписание к процедурам\n5. Купить процедуру\n6. Найти процедуры:\n7. Выход");
                    int getInput = scanner.nextInt();
                    if (getInput == 7) {
                        System.out.println("Программа завершена, мы будем рады вашему возвращению!");
                        break;
                    } else if (getInput == 1) {
                        System.out.println("amount of blabla");
                    } else if (getInput == 2) {
                        System.out.println("search");
                    } else if (getInput == 3) {
                        System.out.println("all prosedury");
                    } else if (getInput == 4) {
                        System.out.println("расписание к процедурам");
                    } else if (getInput == 5) {
                        System.out.println("buy proseduru");
                    } else if (getInput == 6) {
                        System.out.println("search proseduru");
                    }
                } else {
                    System.out.println("Something went wrong");
                }
                countAttempt++;
            } else {
                System.out.println("Password is not correct");
                countAttempt++;
            }
            if (countAttempt == 3) {
                break;
            }
        }
    }
}