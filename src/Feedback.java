import java.time.LocalDateTime;

public class Feedback {
    private User author;
    private String content;
    private LocalDateTime created;

    public Feedback(User author, String content) {
        this.author = author;
        this.content = content;
        this.created = LocalDateTime.now(); // Фиксация времени создания отзыва
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
