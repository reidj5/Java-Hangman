import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Hangman {

    // Classwide variables
    static Boolean gameOver = false;
    static int numOfGuesses = 6;

    public static void main(String[] args) throws IOException {

        // Initialize the dictionary file
        String file = null;
        if (args.length > 0) {
            file = "./" + args[0];
        } else {
            file = "./words.txt";
        }
        // Variables
        List<String> words = createWordList(file);
        List<Character> guessed = new ArrayList<>();
        List<Character> incorrect = new ArrayList<>();
        Scanner key = new Scanner(System.in);

        // Create a random word
        Random random = new Random();
        String word = words.get(random.nextInt(words.size()));

        // Print the word word for the first time, with all blanks
        printWord(word, guessed);

        while (gameOver == false) {
            getPlayerGuess(key, word, guessed);
        }

    }

    // Create word list
    public static List<String> createWordList(String fileDir) throws FileNotFoundException {

        // Create a list of words and return it
        Scanner scanner = new Scanner(new File(fileDir));
        List<String> words = new ArrayList<>();
        while (scanner.hasNext()) {
            words.add(scanner.next());
        }
        scanner.close();
        return words;
    }

    // Method that prints the current guessed game state
    public static void printWord(String word, List<Character> guessed) {

        // Set gameover to be true initially, if any blanks remain, we set gameover back
        // to false
        gameOver = true;
        System.out.println("\n");
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (guessed.contains(c)) {
                System.out.print(c + " ");
            } else {
                System.out.print("_" + " ");
                gameOver = false;
            }
        }
        if (gameOver == true) {
            System.out.println("\nYou win!");
            System.exit(0);
        }

        System.out.println();

    }

    // Method that takes a player guess and applies it to the guessed word
    public static void getPlayerGuess(Scanner scan, String word, List<Character> guessed) {

        // Ask for a guess
        System.out.print("Enter a letter: ");
        String guess = scan.next();
        char c = guess.charAt(0);
        guessed.add(c);

        // Set correct guess to false initally and check if guess is correct
        Boolean guessCorrect = false;
        for (int i = 0; i < word.length(); i++) {
            char d = word.charAt(i);
            if (c == d) {
                System.out.println("Correct!");
                guessCorrect = true;
            }
        }
        if (guessCorrect == false) {
            System.out.println("Incorrect guess");
            numOfGuesses--;
            System.out.println(numOfGuesses + " guesses remaining");

        }

        // Print the word with the guessed letters and blanks
        printWord(word, guessed);

        // Check if the game is over
        if (numOfGuesses == 0) {
            System.out.println("Game over! The word was " + word);
            gameOver = true;
        }
    }

}