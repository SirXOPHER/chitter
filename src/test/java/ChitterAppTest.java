import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Scenario simulations (system tests)
 *
 * Created by xopher on 28/05/2018.
 */
public class ChitterAppTest {

    @Test
    public void main_TimelineRequest_ConsoleOutput() throws Exception {

        String input =
            "A -> A's first posting" +
            "\nA -> A's second posting" +
            "\nA" +
            "\nEXIT";

        String expectedOutput =
            ScenarioSimulatorUtility.bannertext() +
            "\nA's second posting (less than a second ago)" +
            "\nA's first posting (less than a second ago)" +
            "\n" +
            "\nThank you for using CHITTER today. See you soon!" + "\n";

        String actualOutput = ScenarioSimulatorUtility.simulateScenario(input);

        // IMPORTANT NOTICE: Accurate timestamp output is not under test here.
        assertEquals("should simulate a timeline request (after posting)", expectedOutput, actualOutput);
    }

    @Test
    public void main_WallRequest_ConsoleOutput() throws Exception {

        String input =
            "A -> A's first posting" +
            "\nA -> A's second posting" +
            "\nA wall" +
            "\nEXIT";

        String expectedOutput =
            ScenarioSimulatorUtility.bannertext() +
            "\nA - A's second posting (less than a second ago)" +
            "\nA - A's first posting (less than a second ago)" +
            "\n" +
            "\nThank you for using CHITTER today. See you soon!" + "\n";

        String actualOutput = ScenarioSimulatorUtility.simulateScenario(input);

        // IMPORTANT NOTICE: Accurate timestamp output is not under test here.
        assertEquals("should simulate a wall request (after posting)", expectedOutput, actualOutput);
    }

    // Comment: Commands 'posting' and 'following' do not result in a console output

    @Test
    public void main_SimpleScenario_ConsoleOutput() throws Exception {

        String input =
            "A -> A's first posting" +      // CMD_POSTING
            "\nA -> A's second posting" +   // CMD_POSTING
            "\nB -> B's first posting" +    // CMD_POSTING
            "\nB -> B's second posting" +   // CMD_POSTING
            "\nA" +                         // CMD_READING
            "\nB" +                         // CMD_READING
            "\nB -> B's third posting" +    // CMD_POSTING
            "\nA -> A's third posting" +    // CMD_POSTING
            "\nA wall" +                    // CMD_WALL
            "\nA follows B" +               // CMD_FOLLOWING
            "\nA wall" +                    // CMD_WALL
            "\nEXIT";                       // CMD_EXIT

        String expectedOutput =
            ScenarioSimulatorUtility.bannertext() +
            "\nA's second posting (less than a second ago)" +
            "\nA's first posting (less than a second ago)" +
            "\nB's second posting (less than a second ago)" +
            "\nB's first posting (less than a second ago)" +
            "\nA - A's third posting (less than a second ago)" +
            "\nA - A's second posting (less than a second ago)" +
            "\nA - A's first posting (less than a second ago)" +
            "\nA - A's third posting (less than a second ago)" +
            "\nB - B's third posting (less than a second ago)" +
            "\nB - B's second posting (less than a second ago)" +
            "\nB - B's first posting (less than a second ago)" +
            "\nA - A's second posting (less than a second ago)" +
            "\nA - A's first posting (less than a second ago)" +
            "\n" +
            "\nThank you for using CHITTER today. See you soon!" + "\n";

        String actualOutput = ScenarioSimulatorUtility.simulateScenario(input);

        // IMPORTANT NOTICE: Accurate timestamp output is not under test here.
        assertEquals("should simulate a simplified scenario with 2 users using 4+1 commands", expectedOutput, actualOutput);
    }

    @Test
    public void main_PredefinedScenario_ConsoleOutput() throws Exception {

        String input =
            "Alice -> I love the weather today" +
            "\nBob -> Damn! We lost!" +
            "\nBob -> Good game though." +
            "\nAlice" +
            "\nBob" +
            "\nCharlie -> I'm in New York today! Anyone want to have a coffee?" +
            "\nCharlie follows Alice" +
            "\nCharlie wall" +
            "\nCharlie follows Bob" +
            "\nCharlie wall" +
            "\nEXIT";

        String expectedOutput =
            ScenarioSimulatorUtility.bannertext() +
            "\nI love the weather today (less than a second ago)" +
            "\nGood game though. (less than a second ago)" +
            "\nDamn! We lost! (less than a second ago)" +
            "\nCharlie - I'm in New York today! Anyone want to have a coffee? (less than a second ago)" +
            "\nAlice - I love the weather today (less than a second ago)" +
            "\nCharlie - I'm in New York today! Anyone want to have a coffee? (less than a second ago)" +
            "\nBob - Good game though. (less than a second ago)" +
            "\nBob - Damn! We lost! (less than a second ago)" +
            "\nAlice - I love the weather today (less than a second ago)" +
            "\n" +
            "\nThank you for using CHITTER today. See you soon!" + "\n";

        String actualOutput = ScenarioSimulatorUtility.simulateScenario(input);

        // IMPORTANT NOTICE: Accurate timestamp output is not under test here.
        assertEquals("should simulate entire scenario of the exercise", expectedOutput, actualOutput);
    }
}