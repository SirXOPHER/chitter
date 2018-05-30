import java.util.Scanner;

/**
 * Entry point of the program and focal point of control flow
 *
 * Created by xopher on 23/05/2018.
 */
public class ChitterApp {

    private static Scanner console;

    public static void main (String[] args) {

        System.out.println(intro());
        console = new Scanner(System.in);
        ChitterAppContext continuum = new ChitterAppContext();

        boolean active = true;

        do {
            // System.out.print(">"); // display command prompt symbol (optional)
            String currentInput = console.nextLine();
            CommandRouter router = new CommandRouter();
            Command currentCMD = router.interpret(currentInput);
            CommandSplitter splitter = new CommandSplitter(currentInput);
            switch (currentCMD) {
                case CMD_POSTING:   continuum.addUser(splitter.extractUserName());
                                    continuum.addPosting(splitter.extractUserName(), splitter.extractMessageText());
                    break;
                case CMD_READING:   continuum.read(currentInput);
                    break;
                case CMD_FOLLOWING: continuum.follow(splitter.extractUserName(), splitter.extractFolloweeName());
                    break;
                case CMD_WALL:      continuum.printPersonalWall(splitter.extractUserName());
                    break;
                case CMD_EXIT:      active = false;
                                    console.close();
                                    System.out.print("\n" + "Thank you for using CHITTER today. See you soon!" + "\n");
                    break;
                default:
                    break;
            }
        } while (active);
    }

    private static String intro () {
        // Printing ASCII-esque banner for purely nostalgic reasons
        String banner =
            " ######  ##     ## #### ######## ######## ######## ########"  + "\n" +
            "##    ## ##     ##  ##     ##       ##    ##       ##     ##" + "\n" +
            "##       ##     ##  ##     ##       ##    ##       ##     ##" + "\n" +
            "##       #########  ##     ##       ##    ######   ########"  + "\n" +
            "##       ##     ##  ##     ##       ##    ##       ##   ##"   + "\n" +
            "##    ## ##     ##  ##     ##       ##    ##       ##    ##"  + "\n" +
            " ######  ##     ## ####    ##       ##    ######## ##     ##" + "\n";
        return banner;
    }
}
