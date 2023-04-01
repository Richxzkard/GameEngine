package labs.lab5;

/**
 * Interface for two-player board games.
 */
public interface Game {
	boolean isValidMove(String move);

	void executeMove(String move);

	boolean gameOver();

	String displayBoard();

	int determineWinner();
}
