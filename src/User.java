import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String password;
    private Role role;
    private List<Procedure> procedures;
    private List<String> paymentHistory;
    private String healthInfo;  // Информация о здоровье и рекомендации
    private int visitsCount;  // Количество посещений
    private int proceduresHandled; // Количество обработанных процедур


    public User(String name, String password, Role role) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.procedures = new ArrayList<>();
        this.paymentHistory = new ArrayList<>();
        this.healthInfo = "";
        this.proceduresHandled = 0;
    }

    public List<Procedure> getProcedures() {
        return procedures;
    }

    public void addProcedure(Procedure procedure) {
        procedures.add(procedure);
    }

    public List<String> getPaymentHistory() {
        return paymentHistory;
    }

    public void addPayment(String paymentDetails) {
        paymentHistory.add(paymentDetails);
    }

    public String getHealthInfo() {
        return healthInfo;
    }

    public void setHealthInfo(String healthInfo) {
        this.healthInfo = healthInfo;
    }


    public int getVisitsCount() {
        return visitsCount;
    }

    public void setVisitsCount(int visitsCount) {
        this.visitsCount = visitsCount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    private List<Procedure> scheduledProcedures;

    public List<Procedure> getScheduledProcedures() {
        return scheduledProcedures;
    }

    public void removeProcedure(String procedureName) {
        scheduledProcedures.removeIf(procedure -> procedure.getName().equalsIgnoreCase(procedureName));
    }

    public int getProceduresHandled() {
        return proceduresHandled;
    }

    public void setProceduresHandled(int proceduresHandled) {
        this.proceduresHandled = proceduresHandled;
    }

    public void setScheduledProcedures(List<Procedure> scheduledProcedures) {
        this.scheduledProcedures = scheduledProcedures;
    }
}