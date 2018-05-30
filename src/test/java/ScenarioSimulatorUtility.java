import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * Auxiliary class for simulating any user input scenario
 *
 * Created by xopher on 29/05/2018.
 */
public final class ScenarioSimulatorUtility {

    private ScenarioSimulatorUtility() {
        // just an auxiliary class
    }

    public static String simulateScenario(String input) {

        String[] args = null;
        final InputStream originalIn = System.in;
        final PrintStream originalOut = System.out;

        // input : String - available from method argument

        final ByteArrayInputStream predefined = new ByteArrayInputStream(input.getBytes());
        final ByteArrayOutputStream logbook = new ByteArrayOutputStream();
        PrintStream captured = new PrintStream(logbook);
        System.setIn(predefined);
        System.setOut(captured);

        ChitterApp.main(args);

        System.setIn(originalIn);
        System.setOut(originalOut);

        captured.flush();
        String actualOutput = logbook.toString();
        System.out.println(actualOutput + "\n"); // output captured lines to test console (optional)
        captured.close();

        return actualOutput;
    }

    public static String bannertext () {
        // Providing bannertext for expected test output (to avoid duplication)
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
