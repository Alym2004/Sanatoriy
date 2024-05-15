import java.time.LocalDateTime;

public class Session {
    private User user;
    private LocalDateTime lastAccess;

    public Session(User user) {
        this.user = user;
        this.lastAccess = LocalDateTime.now(); // Записываем время создания сессии
    }



    public LocalDateTime getLastAccess() {
        return lastAccess;
    }

    public void updateLastAccess() {
        lastAccess = LocalDateTime.now(); // Обновляем время последнего доступа
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
} 