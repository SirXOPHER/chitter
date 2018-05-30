import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xopher on 23/05/2018.
 */
public class UserTest {

    private User userTestInstance;

    @Before
    public void setUp() throws Exception {
        userTestInstance = new User("Testino");
    }

    @After
    public void tearDown() throws Exception {
        userTestInstance = null;
        assertNull(userTestInstance);
    }

    @Test
    public void automatedTestUsingOpenPojo_GettersAndSetters_ProperFieldAccess() {
        // All Getters and Setters should access proper field
        // https://github.com/OpenPojo/openpojo/wiki
        PojoTestUtility.validateGettersAndSetters(User.class);
    }

    /* Further tests of Getters and Setters below now seem redundant with OpenPojo in place...
    ...but there is no such thing as testing too much, right? */

    @Test
    public void getName_Name_ReturnName() throws Exception {
        assertEquals("should return username \"Testino\"", userTestInstance.getName(),"Testino");
    }

    @Test
    public void setName_Name_SetName() throws Exception {
        userTestInstance.setName("Settino");
        assertEquals("should set username as \"Settino\"", userTestInstance.getName(), "Settino");
    }

    @Test
    public void addSubscription_Subscriptions_AddNewSubscription() throws Exception {
        User dummyUser = new User("Newbie");
        userTestInstance.addSubscription(dummyUser);
        assertTrue("should add a user to subscriptions", userTestInstance.getSubscriptions().contains(dummyUser));
    }

    @Test
    public void getSubscriptions_Subscriptions_CheckSelfReference() throws Exception {
        assertEquals("first entry in subscriptions should be a self-reference", userTestInstance.getSubscriptions().get(0), userTestInstance);
    }

    @Test
    public void getSubscriptions_Subscriptions_CheckSecondSubscription() throws Exception {
        User dummyUser = new User("Newbie");
        userTestInstance.addSubscription(dummyUser);
        assertEquals("should find second entry in subscriptions", userTestInstance.getSubscriptions().get(1), dummyUser);
    }
}