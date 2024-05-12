import java.time.temporal.ChronoUnit;
import java.util.*;
import java.time.LocalDate;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Date data = new Date();
        LocalDate today = LocalDate.now();
        LocalDate nowDate = LocalDate.now();

        DataBase dataBase = new DataBase();
        List<User> userList = new ArrayList<>();
//        List<Client> clientList = new ArrayList<>();
        User manager = new User();
        manager.setName("Alym");
        manager.setPassword("0");
        manager.setRole(Role.MANAGER);

        User director = new User();
        director.setName("Aidar");
        director.setPassword("0");
        director.setRole(Role.DIRECTOR);

        User client1 = new User();
        client1.setName("Beka");
        client1.setPassword("0");
        client1.setSurname("Bekaa");
        client1.setServicePrice(200);
        client1.setStartDate(LocalDate.now());
        client1.setEndDate(LocalDate.now().plusDays(1));
        client1.setRole(Role.CLIENT);

//        User client2 = new User();
//        client2.setName("Kurmanbek");
//        client2.setPassword("0");
//        client2.setRole(Role.CLIENT);
//
//        User client3 = new User();
//        client3.setName("Baha");
//        client3.setPassword("0");
//        client3.setRole(Role.CLIENT);
//
//        User client4 = new User();
//        client4.setName("Nurs");
//        client4.setPassword("0");
//        client4.setRole(Role.CLIENT);


        User personal = new User();
        personal.setName("Azim");
        personal.setPassword("0");
        personal.setRole(Role.PERSONAL);

        userList.add(manager);
        userList.add(director);
        userList.add(client1);
        userList.add(personal);
        dataBase.setUserList(userList);

        String request;
        while (true) {
            System.out.print("Name: ");
            String userName = scanner.nextLine();
            System.out.print("Password: ");
            String userPassword = scanner.nextLine();
            User user = dataBase.findByName(userName);
            if (user == null) {
                System.out.println("User not found");
            } else if (user.getPassword().equals(userPassword)) {
                while (true) {
                    if (user.getRole().equals(Role.MANAGER)) {
                        System.out.println("Приветствую дорогой, Менеджер!\nПожалуйста наберите номер меню для работы с программой, если закончили, то наберите 8");
                        System.out.println("1. Показать список посетителей\n2. Показать количество посетителей\n3. Поиск посетителя:\n4. Показать посетителя с максимальным количеством посещений\n5. Показать посетителя с минимальным количеством посещений\n6. Выход");
                        int getInput = scanner.nextInt();
                        if (getInput == 8){
                            System.out.println("Программа завершена, мы будем рады вашему возвращению!");
                            break;
                        } else if (getInput == 1){
                            do {
                                System.out.println("\t\t\tpatients\n\t\t\t\t\t\t exit");
                                List<User> clients = dataBase.getAllClients();
                                int orderOfClient = 1;
                                for (User user1 : clients) {
                                    System.out.println(orderOfClient + ". Name: " + user1.getName() + "\tSurname: " + user1.getSurname() + "\n");
                                    orderOfClient++;
                                }
                                request = scanner.nextLine();
                            } while (!request.equalsIgnoreCase("exit"));
                        } else if (getInput == 2) {
                            do {
                                System.out.println("\t\t\t\t\texit");
                                List<User> clients = dataBase.getAllClients();
                                System.out.println("Amount of patients: " + clients.size());
                                request = scanner.nextLine();
                            } while (!request.equalsIgnoreCase("exit"));
                        } else if (getInput == 3) {
                            do {
                                System.out.print("\t\t\t\t\t\texit\n\nEnter client name: ");
                                request = scanner.nextLine();
                                List<User> clients = dataBase.getAllClients();
                                int clientIndex = 0;
                                for(User user1 : clients) {
                                    if(user1.getName().equalsIgnoreCase(request)) {
                                        System.out.println("\t\t\t\t\texit\n\nName: " + user1.getName()
                                                + "\nSurname: " + user1.getSurname()
                                                + "\nStart Date: " + user1.getStartDate()
                                                + "\nEnd Date: " + user1.getEndDate()
                                                + "\nService Price: " + user1.getServicePrice());
                                        do {
                                            request = scanner.nextLine();
                                            if("END DATE".startsWith(request.toUpperCase())) {
                                                System.out.println("End Date of procedure: " + user1.getEndDate());
                                                System.out.println("Enter new end date: ");
                                                System.out.print("Year: ");
                                                int year = scanner.nextInt();
                                                System.out.print("Month: ");
                                                int month = scanner.nextInt();
                                                System.out.print("Day: ");
                                                int day = scanner.nextInt();
                                                scanner.nextLine();
                                                LocalDate newEndDate = LocalDate.of(year, month, day);
                                                while (true) {
                                                    System.out.println("New End Date of procedure: " + newEndDate);
                                                    System.out.println("\tSubmit\t\t\t\t\tCancel");
                                                    request = scanner.nextLine();
                                                    if("CANCEL".startsWith(request.toUpperCase())) {
                                                        break;
                                                    } else if ("SUBMIT".startsWith(request.toUpperCase())) {
                                                        user1.setEndDate(newEndDate);
                                                        clients.set(clientIndex, user1);
                                                        break;
                                                    }
                                                }
                                            } else if ("START DATE".startsWith(request.toUpperCase())) {
                                                System.out.println("Start Date of procedure: " + user1.getStartDate());
                                                System.out.println("Enter new start date: ");
                                                System.out.print("Year: ");
                                                int year = scanner.nextInt();
                                                System.out.print("Month: ");
                                                int month = scanner.nextInt();
                                                System.out.print("Day: ");
                                                int day = scanner.nextInt();
                                                scanner.nextLine();
                                                LocalDate newStartDate = LocalDate.of(year, month, day);
                                                while (true) {
                                                    System.out.println("New Start Date of procedure: " + newStartDate);
                                                    System.out.println("\tSubmit\t\t\t\t\tCancel");
                                                    request = scanner.nextLine();
                                                    if("CANCEL".startsWith(request.toUpperCase())) {
                                                        break;
                                                    } else if ("SUBMIT".startsWith(request.toUpperCase())) {
                                                        user1.setStartDate(newStartDate);
                                                        clients.set(clientIndex, user1);
                                                        break;
                                                    }
                                                }
                                            } else if ("SERVICE PRICE".startsWith(request.toUpperCase())) {
                                                System.out.println("Service Price: " + user1.getServicePrice());
                                                System.out.print("Enter new Service Price: ");
                                                double newServicePrice = scanner.nextDouble();
                                                scanner.nextLine();
                                                while (true) {
                                                    System.out.println("New Service Price: " + newServicePrice);
                                                    System.out.println("\tSubmit\t\t\t\t\tCancel");
                                                    request = scanner.nextLine();
                                                    if("CANCEL".startsWith(request.toUpperCase())) {
                                                        break;
                                                    } else if ("SUBMIT".startsWith(request.toUpperCase())) {
                                                        user1.setServicePrice(newServicePrice);
                                                        clients.set(clientIndex, user1);
                                                        break;
                                                    }
                                                }
                                            }
                                        } while (!request.equalsIgnoreCase("exit"));
                                    }
                                    if("EXIT".startsWith(request.toUpperCase())) {
                                        break;
                                    }
                                    clientIndex++;
                                }
                            } while (!request.equalsIgnoreCase("exit"));
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
                    } else if (user.getRole().equals(Role.CLIENT)) {
                        System.out.println("\n" + "Приветствую дорогой, Посетитель!\n" + "Пожалуйста наберите номер меню для работы с программой, если закончили, то наберите 7:\n");
                        System.out.println("1.Показать историю посещений процедур\n2.Показать последнюю дату посещения процедур\n3.Показать историю оплаты процедурам\n4.Показать расписание к процедурам\n5.Показать мою информацию\n6.Показать мой день проживания в Санатории\n7.Выход ");
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
                    } else if (user.getRole().equals(Role.PERSONAL)) {
                        System.out.println("\n" + "Приветствую уважаемая, Персонал!\n" + "Пожалуйста наберите номер меню для работы с программой, если закончили, то наберите 6:\n");
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
                    } else if (user.getRole().equals(Role.DIRECTOR)){
                        System.out.println("Welcome director");
                    }
                    else {
                        System.out.println("Something went wrong. Server error!");
                    }
                }
            } else {
                System.out.println("Извините, но мы не нашли такой тип аккаунта или у вас неправильно введены логин и/или пароль пожалуйста повторите\n");
            }
        }
    }
    public static void findeClientsByName(String userName,Scanner scanner,List<User> userList){
        userName = scanner.nextLine();
        if (userList.contains(userName)){
            System.out.println(userName);

        }
    }
    public static void clients(User client1,User client2,User client3, User client4,User client){
        System.out.println(("1 " + client.getName() +
                "\n2 "+ client1.getName()+
                "\n3 " + client2.getName()+
                "\n4 "+ client3.getName()+
                "\n5 " + client4.getName()));
    }
}
//+ " "+ ChronoUnit.DAYS.between(clientNumu,nowDate) + " Days in Sanatoriy " + ChronoUnit.DAYS.between(nowDate, daysLeftNumu) + " Days left"