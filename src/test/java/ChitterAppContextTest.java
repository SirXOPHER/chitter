import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by xopher on 29/05/2018.
 */
public class ChitterAppContextTest {

    private ChitterAppContext ChitterAppContextTestInstance;

    @Before
    public void setUp() throws Exception {
        ChitterAppContextTestInstance = new ChitterAppContext();
    }

    @After
    public void tearDown() throws Exception {
        ChitterAppContextTestInstance = null;
        assertNull(ChitterAppContextTestInstance);
    }

    @Test
    public void addUser_NumberOfUsers_ConfirmAdditionOfOneUser() throws Exception {
        int actualNumberOfUsers = 0;
        ChitterAppContextTestInstance.addUser("Testino");

        // Access class field of type ArrayList<?> using reflection
        Field field = ChitterAppContextTestInstance.getClass().getDeclaredField("userSpace");
        System.out.println("Instance variable: \'" + field.getName() + "\'");
        System.out.println("Object data type:  " + field.getType() + "\n");
        field.setAccessible(true);
        Object fieldValue = field.get(ChitterAppContextTestInstance);
        if(fieldValue instanceof ArrayList) {
            ArrayList<?> arrayListField = (ArrayList<?>) fieldValue;
            actualNumberOfUsers = arrayListField.size();
        }
        field.setAccessible(false);

        assertEquals("should confirm the addition of one user",1, actualNumberOfUsers);
    }

    @Test
    public void addPosting_NumberOfPostings_ConfirmAdditionOfOnePosting() throws Exception {
        int actualNumberOfPostings = 0;
        ChitterAppContextTestInstance.addPosting("Testino", "Very meaningful message");

        // Access class field of type ArrayList<?> using reflection
        Field field = ChitterAppContextTestInstance.getClass().getDeclaredField("msgVault");
        System.out.println("Instance variable: \'" + field.getName() + "\'");
        System.out.println("Object data type:  " + field.getType()+ "\n");
        field.setAccessible(true);
        Object fieldValue = field.get(ChitterAppContextTestInstance);
        if(fieldValue instanceof ArrayList) {
            ArrayList<?> arrayListField = (ArrayList<?>) fieldValue;
            actualNumberOfPostings = arrayListField.size();
        }
        field.setAccessible(false);

        assertEquals("should confirm the addition of one posting", 1, actualNumberOfPostings);
    }

    @Test
    public void read_AllPostingsOfCertainUser_PrintInReverseChronologicalOrder() throws Exception {
        // Test set-up calling alternate testing constructor
        ArrayList<User> testUserList = new ArrayList<User>();
        testUserList.add(new User("Testino"));
        ArrayList<Posting> testMessages = new ArrayList<Posting>();
        testMessages.add(new Posting("Testino", "1st meaningful message"));
        testMessages.add(new Posting("Testino", "2nd meaningful message"));
        ChitterAppContextTestInstance = new ChitterAppContext(testUserList, testMessages);

        String expectedOutput =
            "2nd meaningful message (less than a second ago)" +
            "\n1st meaningful message (less than a second ago)" + "\n";

        // Start capturing console output
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        // Run what is supposed to generate output
        ChitterAppContextTestInstance.read("Testino");
        // Stop capturing console output
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        // Pass on captured content and reset
        String content = buffer.toString();
        buffer.reset();
        System.setOut(System.out);

        String actualOutput = content;
        System.out.println(actualOutput + "\n"); // output captured lines to test console (optional)

        // IMPORTANT NOTICE: Accurate timestamp output is not under test here.
        assertEquals("should print out timeline in reverse-chronological order", expectedOutput, actualOutput);
    }

    @Test
    public void follow_SubscriptionsOfCertainUser_ConfirmSuccessfulSubscription() throws Exception {
        // Test set-up calling alternate testing constructor
        ArrayList<User> testUserList = new ArrayList<User>();
        testUserList.add(new User("Testino"));
        testUserList.add(new User("Superstar"));
        ArrayList<Posting> testMessages = new ArrayList<Posting>();
        ChitterAppContextTestInstance = new ChitterAppContext(testUserList, testMessages);

        String[] expectedOutput = {"Testino","Superstar"};

        ChitterAppContextTestInstance.follow("Testino", "Superstar");

        String[] actualOutput = new String[2];
        int index = 0;
        for (User u : testUserList.get(0).getSubscriptions()) {
            actualOutput[index] = u.getName();
            index++;
        }

        assertArrayEquals("should confirm successful subscription", expectedOutput, actualOutput);
    }

    @Test
    public void printPersonalWall_AllPostingsOfCertainUserIncludingSubscriptions_PrintInReverseChronologicalOrder() throws Exception {
        // Test set-up calling alternate testing constructor
        ArrayList<User> testUserList = new ArrayList<User>();
        testUserList.add(new User("Testino"));
        testUserList.add(new User("Superstar"));
        ArrayList<Posting> testMessages = new ArrayList<Posting>();
        testMessages.add(new Posting("Testino", "1st meaningful message"));
        testMessages.add(new Posting("Superstar", "1st word of wisdom"));
        testMessages.add(new Posting("Testino", "2nd meaningful message"));
        testMessages.add(new Posting("Superstar", "2nd word of wisdom"));
        ChitterAppContextTestInstance = new ChitterAppContext(testUserList, testMessages);
        ChitterAppContextTestInstance.follow("Testino", "Superstar");

        String expectedOutput =
            "Superstar - 2nd word of wisdom (less than a second ago)" +
            "\nTestino - 2nd meaningful message (less than a second ago)" +
            "\nSuperstar - 1st word of wisdom (less than a second ago)" +
            "\nTestino - 1st meaningful message (less than a second ago)" + "\n";

        // Start capturing console output
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        // Run what is supposed to generate output
        ChitterAppContextTestInstance.printPersonalWall("Testino");
        // Stop capturing console output
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        // Pass on captured content and reset
        String content = buffer.toString();
        buffer.reset();
        System.setOut(System.out);

        String actualOutput = content;
        System.out.println(actualOutput + "\n"); // output captured lines to test console (optional)

        // IMPORTANT NOTICE: Accurate timestamp output is not under test here.
        assertEquals("should print out a specified user's wall in reverse-chronological order", expectedOutput, actualOutput);
    }

    @Test
    public void isUserPreExisting_SpecificUserInUserSpace_VerifyUserIsNotPreExisting() throws Exception {
        assertFalse("should confirm user is not pre-existing", ChitterAppContextTestInstance.isUserPreExisting("Testino"));
    }

    @Test
    public void isUserPreExisting_SpecificUserInUserSpace_VerifyUserIsPreExisting() throws Exception {
        ChitterAppContextTestInstance.addUser("Testino");
        assertTrue("should confirm user is pre-existing", ChitterAppContextTestInstance.isUserPreExisting("Testino"));
    }

    // Comment: method 'errorNotice' (simple switch for user guidance) excluded from tests
}