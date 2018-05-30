/**
 * Command splitter extracting data values from full commands
 *
 * Created by xopher on 24/05/2018.
 */
public class CommandSplitter {

    private String fullCommand;

    public CommandSplitter(String fullCommandText) {
        this.fullCommand = fullCommandText;
    }

    public String extractUserName() {
        String name = fullCommand.substring(0, fullCommand.indexOf(" "));
        return name;
    }

    public String extractFolloweeName() {
        String name = fullCommand.substring(fullCommand.lastIndexOf("follows") + 8);
        return name;
    }

    public String extractMessageText() {
        String msg = fullCommand.substring(fullCommand.lastIndexOf("->") + 3);
        return msg;
    }
}
