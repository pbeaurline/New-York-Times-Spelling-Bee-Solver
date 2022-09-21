import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Plays the NYT Spelling Bee game.
 *
 * @author J. Hollingsworth and Patrick Beaurline
 */

public class SpellingBee {

    // the puzzle string for an instance of the bee
    private String puzzle = "";
    private ArrayList<String> answers = new ArrayList<>();

    public void promptForPuzzle() {
        Scanner sc = new Scanner(System.in);

        // keep asking until we are given a valid puzzle
        while (!isValidPuzzle(puzzle)) {
            System.out.print("puzzle? ");
            puzzle = sc.nextLine();
        }
    }

    private boolean isValidPuzzle(String possiblePuzzle) {

        // 7 characters?
        if (possiblePuzzle.length() != 7) {
            return false;
        }

        // all lower case letters?
        for (int i = 0; i < possiblePuzzle.length(); i++) {
            if (possiblePuzzle.charAt(i) < 'a'
                    || possiblePuzzle.charAt(i) > 'z') {
                return false;
            }
        }

        // any repeat letters?
        for (int i = 0; i < possiblePuzzle.length(); i++) {
            char x = possiblePuzzle.charAt(i);
            //goes through string looking for duplicate letters, starting
            //at the index after the character started at.  if the character occurs, the method will not return -1 and thus
            //returns false
            if (possiblePuzzle.indexOf(x, i+1) != -1) {
                return false;
            }
        }

        return true;
    }

    public void searchForAnswers() {
        File file = new File("EnglishWords.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Invalid file name");
            System.exit(0);
        }

        while (sc.hasNext()) {
            String word = sc.nextLine();
            if (word.length() >= 4) { //makes sure word is atleast 4 letters long
                if (word.contains(Character.toString(puzzle.charAt(0))) == true) {
                    String charsToRemove = "["+Character.toString(puzzle.charAt(0)) +"|"+ Character.toString(puzzle.charAt(1)) +"|"+
                            Character.toString(puzzle.charAt(2)) +"|"+ Character.toString(puzzle.charAt(3)) +"|"+
                            Character.toString(puzzle.charAt(4)) +"|"+ Character.toString(puzzle.charAt(5)) +"|"+
                            Character.toString(puzzle.charAt(6))+"]";
                    String alpabet = "abcdefghijklmnopqrstuvwxyz";
                    String illegalChars= alpabet.replaceAll(charsToRemove,""); //removes all chars present in puzzle
                    int counter = 0;
                    for (int i = 0; i < word.length(); i++) {
                        if (!illegalChars.contains(Character.toString(word.charAt(i)))) { //tests to see if word does not contain illegal chars
                            counter = counter + 1;
                            if (counter == word.length()) {
                                answers.add(word);
                            }
                        }

                    }
                }
            }
            //System.out.println(word);
        }
    }

    public String toString() {
        // FIXME should return a string representation of the beehive
        return "     " + puzzle.charAt(2) + "     " + System.lineSeparator()
                + " " + puzzle.charAt(3) + "       " + puzzle.charAt(1) + " " + System.lineSeparator()
                + "     " + puzzle.charAt(0) + "     " + System.lineSeparator()
                + " " + puzzle.charAt(4) + "       " + puzzle.charAt(6) + " " + System.lineSeparator()
                +  "     " + puzzle.charAt(5) + "     ";



    }

    public void displayAnswers() {
        System.out.println(answers.size() + " Words:");
        int score = 0;
        int maxScore = 0;
        for (int i = 0; i < answers.size(); i++) {
            String validWord = answers.get(i);
            if (isPangram(validWord) == true) {
                score = score + 7;
                maxScore = maxScore + 7;
            }
            if (validWord.length() == 4) {
                score = score + 1;
                maxScore = maxScore + 1;
            } else {
                score = score + validWord.length();
                maxScore = maxScore + validWord.length();
            }
            if (isPangram(validWord) == true) {
                System.out.println(score + " - " + answers.get(i) + "*");
                score = 0;
            } else {
                System.out.println(score + " - " + answers.get(i));
                score = 0;
            }
        }
        System.out.println("Max score = " + maxScore);
    }

    private boolean isPangram(String word) {
        if (word.length() >= 7) {
            if (word.contains(Character.toString(puzzle.charAt(0)))) {
                if (word.contains(Character.toString(puzzle.charAt(1)))) {
                    if (word.contains(Character.toString(puzzle.charAt(2)))) {
                        if (word.contains(Character.toString(puzzle.charAt(3)))) {
                            if (word.contains(Character.toString(puzzle.charAt(4)))) {
                                if (word.contains(Character.toString(puzzle.charAt(5)))) {
                                    if (word.contains(Character.toString(puzzle.charAt(6)))) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }


}