import java.time.Clock;
import java.util.ArrayList;

/**
 * Application context holding current state of the program and providing commands
 *
 * Created by xopher on 24/05/2018.
 */
public class ChitterAppContext {

    private ArrayList<User> userSpace;
    private ArrayList<Posting> msgVault;
    private final Clock productionClock = Clock.systemDefaultZone();

    public ChitterAppContext() {
        this.userSpace =  new ArrayList<User>();
        this.msgVault = new ArrayList<Posting>();
    }

    //package-private constructor for the sake of testing
    ChitterAppContext(ArrayList<User> userSpace, ArrayList<Posting> msgVault) {
        this.userSpace = userSpace;
        this.msgVault = msgVault;
    }

    public void addUser(String userName) {
        if (!isUserPreExisting(userName)) {
            userSpace.add(new User(userName));
        }
    }

    public void addPosting(String authorName, String messageContent) {
        msgVault.add(new Posting(authorName, messageContent));
    }

    public void read(String userName) {
        if (!isUserPreExisting(userName)) {
            System.out.println(errorNotice(userName, 1));
        } else {
            for (int index = msgVault.size() - 1; index >= 0; index--) {
                if(msgVault.get(index).getAuthorName().equals(userName)) {
                    System.out.println(msgVault.get(index).getMessage() +
                " (" + TimestampConverter.convertTimestamp(msgVault.get(index).getTimestamp(), productionClock) + ")");
                }
            }
        }
    }

    public void follow(String userName, String followeeName) {
        if (!isUserPreExisting(userName)) {
            System.out.println(errorNotice(userName, 1));
        } else if (!isUserPreExisting(followeeName)) {
            System.out.println(errorNotice(userName, 2));
        } else {
            User subscriber = null;
            User followee = null;
            for (User u : userSpace) {
                if (u.getName().equals(userName)) {
                    subscriber = u;
                }
                if (u.getName().equals(followeeName)) {
                    followee = u;
                }
            }
            subscriber.addSubscription(followee);
        }
    }

    public void printPersonalWall(String userName) {
        if (!isUserPreExisting(userName)) {
            System.out.println(errorNotice(userName, 1));
        } else {
            User wallOwner = null;
            for (User u : userSpace) {
                if (u.getName().equals(userName)) {
                    wallOwner = u;
                }
            }
            for (int index = msgVault.size() - 1; index >= 0; index--) {
                for (User s : wallOwner.getSubscriptions()) {
                    if(msgVault.get(index).getAuthorName().equals(s.getName())) {
                        System.out.println(s.getName() + " - " + msgVault.get(index).getMessage() +
                    " (" + TimestampConverter.convertTimestamp(msgVault.get(index).getTimestamp(), productionClock) + ")");
                    }
                }
            }
        }
    }

    public boolean isUserPreExisting(String userName) {
        boolean existing = false;
        if (!userSpace.isEmpty()) {
            for (User u : userSpace) {
                if (u.getName().equals(userName)) {
                    existing = true;
                    break;
                }
            }
        }
        return existing;
    }

    public String errorNotice (String userName, int errorCase) {
        String message;
        switch (errorCase) {
            // case 1: invalid user
            case 1:  message = "User \"" + userName + "\" doesn't exist yet." +
                    " Users are created as they post their first message.";
                break;
            // case 2: invalid followee
            case 2:  message = "User \"" + userName + "\" can't be followed as she/he doesn't exist yet." +
                    " Users are created as they post their first message.";
                break;
            default: message = "Undefined error";
                break;
        }
        return message;
    }
}
