import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xopher on 28/05/2018.
 */
public class CommandRouterTest {

        private CommandRouter cmdRouterTestInstance;

    @Before
    public void setUp() throws Exception {
        cmdRouterTestInstance = new CommandRouter();
    }

    @After
    public void tearDown() throws Exception {
        cmdRouterTestInstance = null;
        assertNull(cmdRouterTestInstance);
    }

    @Test
    public void interpret_UserInput_IdentifyPosting() throws Exception {
        String userInput = "A -> B";
        assertEquals("should identify a posting", cmdRouterTestInstance.interpret(userInput), Command.CMD_POSTING);
    }

    @Test
    public void interpret_UserInput_IdentifyWallRequest() throws Exception {
        String userInput = "Testino wall";
        assertEquals("should identify a wall request", cmdRouterTestInstance.interpret(userInput), Command.CMD_WALL);
    }

    @Test
    public void interpret_UserInput_IdentifySubscriptionRequest() throws Exception {
        String userInput = "A follows B";
        assertEquals("should identify a subscription request", cmdRouterTestInstance.interpret(userInput), Command.CMD_FOLLOWING);
    }

    @Test
    public void interpret_UserInput_IdentifyApplicationTermination() throws Exception {
        String userInput = "eXiT";
        assertEquals("should identify the termination keyword", cmdRouterTestInstance.interpret(userInput), Command.CMD_EXIT);
    }

    @Test
    public void interpret_UserInput_IdentifyTimelineRequest() throws Exception {
        String userInput = "Readme";
        assertEquals("should identify a timeline request", cmdRouterTestInstance.interpret(userInput), Command.CMD_READING);
    }
}