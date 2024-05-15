import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataBase {
    private List<User> userList;
    private List<Procedure> procedureList;
    private List<Session> sessions;
    private List<Feedback> feedbackList;
    private List<FinancialRecord> financialRecords;


    public DataBase() {
        this.userList = new ArrayList<>();
        this.procedureList = new ArrayList<>();
        this.sessions = new ArrayList<>();
        this.feedbackList = new ArrayList<>();
        this.financialRecords = new ArrayList<>();
    }

    public void addFinancialRecord(FinancialRecord record) {
        financialRecords.add(record);
    }

    public List<FinancialRecord> getAllFinancialRecords() {
        return new ArrayList<>(financialRecords);
    }

    public void scheduleProcedure(Procedure procedure) {
        procedureList.add(procedure);
    }

    public boolean unscheduleProcedure(String name) {
        for (Procedure proc : procedureList) {
            if (proc.getName().equalsIgnoreCase(name) && proc.isActive()) {
                proc.cancelProcedure();
                return true;
            }
        }
        return false;
    }

    public List<Procedure> getActiveProcedures() {
        return procedureList.stream().filter(Procedure::isActive).collect(Collectors.toList());
    }

    public void addFeedback(Feedback feedback) {
        feedbackList.add(feedback);
    }

    public List<Feedback> getAllFeedbacks() {
        return new ArrayList<>(feedbackList);
    }

    public void addProcedure(Procedure procedure) {
        procedureList.add(procedure);
    }

    public boolean removeProcedure(String procedureName) {
        return procedureList.removeIf(p -> p.getName().equalsIgnoreCase(procedureName));
    }

    public List<Procedure> getAllProcedures() {
        return new ArrayList<>(procedureList);
    }

    public Procedure findProcedureByName(String name) {
        return procedureList.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public void addUser(User user) {
        userList.add(user);
        sessions.add(new Session(user)); // Создаем сессию для каждого нового пользователя
    }

    public void removeUser(String userName) {
        userList.removeIf(user -> user.getName().equalsIgnoreCase(userName));
        sessions.removeIf(session -> session.getUser().getName().equalsIgnoreCase(userName));
    }

    public List<User> getUserList() {
        return userList;
    }

    public Map<Role, Long> getUserStats() {
        return userList.stream().collect(Collectors.groupingBy(User::getRole, Collectors.counting()));
    }

    // Поиск посетителя по имени
    public User findClientByName(String name) {
        return userList.stream()
                .filter(user -> user.getRole() == Role.CLIENT && user.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    // Метод для получения статистики по количеству пользователей каждой роли
    public Map<Role, Integer> getUserStatistics() {
        Map<Role, Integer> stats = new HashMap<>();
        for (Role role : Role.values()) {
            stats.put(role, (int) userList.stream().filter(user -> user.getRole() == role).count());
        }
        return stats;
    }


    public void addClient(User client) {
        if (client.getRole() == Role.CLIENT) {
            userList.add(client);
        }
    }

    public void removeClient(String clientName) {
        userList.removeIf(user -> user.getName().equalsIgnoreCase(clientName) && user.getRole() == Role.CLIENT);
    }

    public List<User> getAllClients() {
        return userList.stream().filter(user -> user.getRole() == Role.CLIENT).collect(Collectors.toList());
    }

    public Map<String, Integer> getClientActivityReport() {
        Map<String, Integer> report = new HashMap<>();
        for (User client : userList) {
            if (client.getRole() == Role.CLIENT) {
                report.put(client.getName(), client.getVisitsCount());
            }
        }
        return report;
    }

    public User authenticateUser(String name, String password) {
        for (User user : userList) {
            if (user.getName().equalsIgnoreCase(name) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public Map<String, Integer> getProcedureUsageStats() {
        Map<String, Integer> stats = new HashMap<>();
        for (Procedure p : procedureList) {
            stats.put(p.getName(), p.getAttendees().size());
        }
        return stats;
    }

    public Map<Role, Integer> getStaffEfficiency() {
        Map<Role, Integer> efficiency = new HashMap<>();
        for (User user : userList) {
            efficiency.put(user.getRole(), user.getProceduresHandled()); // Допустим, есть такой метод
        }
        return efficiency;
    }
}