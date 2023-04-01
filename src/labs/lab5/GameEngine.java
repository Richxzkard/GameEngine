package labs.lab5;

import java.util.Scanner;

/**
 * General player for two-player game.
 */
public class GameEngine {
	private Game game;
	private int playerTurn;

	/**
	 * Create a game player for the provided game.
	 * 
	 * @param game game model
	 */
	public GameEngine(Game game) {
		this.game = game;
		this.playerTurn = 1;
	}

	/**
	 * Play the game.
	 */
	public void play() {
		Scanner scanner = new Scanner(System.in);

		System.out.println(game.displayBoard());

		while (!game.gameOver()) {
			String move = getMove(scanner);
			game.executeMove(move);
			switchPlayer();
			System.out.println(game.displayBoard());
		}

		printWinner();
	}

	private String getMove(Scanner scanner) {
		boolean valid;
		String move;
		do {
			System.out.print("\nEnter move Player " + playerTurn + ": ");
			move = scanner.next();
			valid = game.isValidMove(move);
			if (!valid) {
				System.out.println("Invalid move!");
			}
		} while (!valid);

		return move;
	}

	private void switchPlayer() {
		if (playerTurn == 1) {
			playerTurn = 2;
		} else {
			playerTurn = 1;
		}
	}

	private void printWinner() {
		int winner = game.determineWinner();
		if (winner > 0) {
			System.out.println("\nPlayer " + winner + " wins!");
		} else {
			System.out.println("\nNobody wins.");
		}
	}
}
