/**
 * Command interpreter identifying command codes from user input
 *
 * Created by xopher on 24/05/2018.
 */
public class CommandRouter {

    private Command cmd = Command.CDM_UNIDENTIFIED;

    public Command interpret(String userInput) {

        if (userInput.contains(" ")) {
            if (userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf(" ") + 3).equals("->")) {
                this.cmd = Command.CMD_POSTING;
            } else if (userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf(" ") + 5).equals("wall")) {
                this.cmd = Command.CMD_WALL;
            } else {
                this.cmd = Command.CMD_FOLLOWING;
            }
        } else {
            if (userInput.toLowerCase().equals("exit")) {
                this.cmd = Command.CMD_EXIT;
            } else {
                this.cmd = Command.CMD_READING;
            }
        }

        return this.cmd;
    }
}
