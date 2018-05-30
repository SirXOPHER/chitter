import java.util.ArrayList;

/**
 * Object representation of a user
 *
 * Created by xopher on 23/05/2018.
 */
public class User {

    private String name;
    private ArrayList<User> subscriptions;

    public User(String name) {
        this.name = name;
        this.subscriptions = new ArrayList<User>();
        this.subscriptions.add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addSubscription(User followeeToAdd) {
        subscriptions.add(followeeToAdd);
    }

    public ArrayList<User> getSubscriptions() {
        return subscriptions;
    }
}
