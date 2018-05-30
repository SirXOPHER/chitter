import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xopher on 28/05/2018.
 */
public class CommandSplitterTest {

    @Test
    public void extractUserName_ParameterToBePassedOnIsUserName_SourceCommandPosting() throws Exception {
        CommandSplitter commandSplitterTestInstance = new CommandSplitter("Testino -> Meaningful posting");
        assertEquals("should extract username \"Testino\" from a posting", commandSplitterTestInstance.extractUserName(), "Testino");
    }

    // Comment: Username does not need to be extracted from command 'reading', because command = username in this case.

    @Test
    public void extractUserName_ParameterToBePassedOnIsUserName_SourceCommandFollowing() throws Exception {
        CommandSplitter commandSplitterTestInstance = new CommandSplitter("Testino follows Testonia");
        assertEquals("should extract username \"Testino\" from a subscription request", commandSplitterTestInstance.extractUserName(), "Testino");
    }

    @Test
    public void extractUserName_ParameterToBePassedOnIsUserName_SourceCommandWall() throws Exception {
        CommandSplitter commandSplitterTestInstance = new CommandSplitter("Testino wall");
        assertEquals("should extract username \"Testino\" from a wall call", commandSplitterTestInstance.extractUserName(), "Testino");
    }

    @Test
    public void extractFolloweeName_ParameterToBePassedOnIsFolloweeName_SourceCommandFollowing() throws Exception {
        CommandSplitter commandSplitterTestInstance = new CommandSplitter("Testino follows Testonia");
        assertEquals("should extract username \"Testonia\" from a subscription request", commandSplitterTestInstance.extractFolloweeName(), "Testonia");
    }

    @Test
    public void extractMessageText_ParameterToBePassedOnIsMessage_SourceCommandPosting() throws Exception {
        CommandSplitter commandSplitterTestInstance = new CommandSplitter("Testino -> Meaningful posting");
        assertEquals("should extract the message text from a posting", commandSplitterTestInstance.extractMessageText(), "Meaningful posting");
    }

}