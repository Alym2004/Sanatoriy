import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Добавление класса для процедур
public class Procedure {
    private String name;
    private LocalDateTime time;
    private double price;
    private String description;
    private List<User> attendees; // Список посетителей, записанных на процедуру
    private boolean isActive; // Статус процедуры (активна или отменена)

    public Procedure(String name, LocalDateTime time, String description, double price) {
        this.name = name;
        this.time = time;
        this.price = price;
        this.description = description;
        this.attendees = new ArrayList<>();
        this.isActive = true;
    }

    public void cancelProcedure() {
        this.isActive = false;
        // Отправка уведомлений всем зарегистрированным пользователям
        for (User attendee : attendees) {
            System.out.println("Уведомление: Процедура " + name + " на " + time + " отменена.");
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<User> attendees) {
        this.attendees = attendees;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}