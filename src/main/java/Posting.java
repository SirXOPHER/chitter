import java.time.LocalDateTime;

/**
 * Object representation of a posting
 *
 * Created by xopher on 24/05/2018.
 */
public class Posting {

    private String authorName;
    private String message;
    private final LocalDateTime timestamp;

    public Posting(String authorName, String message) {
        this.authorName = authorName;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
