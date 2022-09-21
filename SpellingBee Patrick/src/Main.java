/**
 * Plays the NYT Spelling Bee.
 *
 * @author J. Hollingsworth and YOUR_NAME // FIXME
 */

public class Main {

    /*
       Currently, the main only tests Milestone 1. You'll want
       to modify this when tested Milestone 2 and 3.
     */

    public static void main(String[] args) {
        // create a SpellingBee object
        SpellingBee bee = new SpellingBee();

        // get a valid puzzle from the user
        bee.promptForPuzzle();

        // display the beehive
        System.out.println(bee);

        bee.searchForAnswers();

        bee.displayAnswers();
    }

}
