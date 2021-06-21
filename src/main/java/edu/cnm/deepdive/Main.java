package edu.cnm.deepdive;

import edu.cnm.deepdive.model.Game;
import edu.cnm.deepdive.model.Guess;
import edu.cnm.deepdive.service.GameRepository;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Implements a simple client for a Codebreaker (similar to MasterMind, Bulls &amp; Cows) game that
 * runs as a web service. This client uses console interaction (standard input &amp; output) to
 * obtain guesses from the user and display the result of each guess.
 *
 * @author Nicholas Bennett and DDC Java + Android Bootcamp cohort 13.
 */
public class Main {

  private static final String POOL = "AEIOUY";
  private static final String BUNDLE_NAME = "strings";
  private static final int CODE_LENGTH = 1;
  private static final String REPLAY_PROMPT_KEY = "replay_prompt";
  private static final char NEGATIVE_RESPONSE = 'n';
  private static final String INITIAL_STATUS_KEY = "initial_status_format";
  private static final String GUESS_PROMPT_KEY = "guess_prompt_format";
  private static final String GUESS_RESULTS_KEY = "guess_results_format";

  /**
   * Entry point for game. Connects to Codebreaker service to start each game, and query the user
   * for guesses and new games, until the user declines to play again.
   *
   * @param args Command-line arguments (currently ignored).
   * @throws IOException If a network or network resource (such as web service) fails in sending the
   *                     request and receiving the response.
   */
  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);
    ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME);
    GameRepository repository = new GameRepository();
    do {
      play(scanner, bundle, repository);
    } while (queryReplay(scanner, bundle));  // End of do-while
  }

  private static void play(Scanner scanner, ResourceBundle bundle, GameRepository repository)
      throws IOException {
    // TODO Play a single game.
    String initialStatusFormat = bundle.getString(INITIAL_STATUS_KEY);
    String guessPromptFormat = bundle.getString(GUESS_PROMPT_KEY);
    String guessResultsFormat = bundle.getString(GUESS_RESULTS_KEY);
    Game game = repository.newGame(POOL, CODE_LENGTH);
    System.out.printf(initialStatusFormat, game.getLength(), game.getPool());
    Guess guess;
    do {
      System.out.printf(guessPromptFormat, game.getLength(), game.getPool());
      String input = scanner.nextLine().trim().toUpperCase();
      // TODO Validate input before sending, ...
      guess = repository.newGuess(game, input);
      // TODO ...or , catch illergal
      System.out.printf(guessResultsFormat,
          guess.getText(), guess.getExactMatches(), guess.getNearMatches());
    } while (!guess.isSolution());
  }

  /**
   * Prompts the user to play again. If a resource bundle exists for the current (or set) locale, a
   * string from this resource bundle will be used for the prompt text.
   *
   * @param scanner Instance of {@link Scanner} used to obtain user input.
   * @param bundle {@link ResourceBundle} holding UI string context.
   * @return {@code false} if the user declines to play again; {@code true} otherwise.
   */
  private static boolean queryReplay(Scanner scanner, ResourceBundle bundle) {
    System.out.println(bundle.getString(REPLAY_PROMPT_KEY));
    String input = scanner.nextLine().trim().toLowerCase();
    return (input.isEmpty() || input.charAt(0) != NEGATIVE_RESPONSE);
  }

}
