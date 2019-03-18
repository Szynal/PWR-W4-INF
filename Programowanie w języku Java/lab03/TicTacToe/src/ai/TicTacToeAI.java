package ai;

import java.util.Random;
import app.TicTacToeApp.Difficulty;

/**
 * @author Pawel Szynal
 */
public class TicTacToeAI {

	@NotNull(message = "Name cannot be null")
	private int board[][];

	public static final int EMPTY = 0;
	public static final int PLAYER_ONE = 1;
	public static final int PLAYER_TWO = 2;

	/**
	 * @param sizeOfTicTacToe
	 */
	public TicTacToeAI(Integer sizeOfTicTacToe) {
		board = new int[sizeOfTicTacToe][sizeOfTicTacToe];
	}

	public int getBoardValue(int i, int j, int sizeOfTicTacToe) {
		if (i < 0 || i >= sizeOfTicTacToe)
			return EMPTY;
		if (j < 0 || j >= sizeOfTicTacToe)
			return EMPTY;
		return board[i][j];
	}

	public void setBoardValue(int i, int j, int token, int sizeOfTicTacToe) {
		if (i < 0 || i >= sizeOfTicTacToe)
			return;
		if (j < 0 || j >= sizeOfTicTacToe)
			return;
		board[i][j] = token;
	}

	public int[] calculateMoveForCurrentPlayer(int player, int sizeOfTicTacToe, Difficulty difficulty) {

		if (difficulty == Difficulty.EASY) {
			/*
			 * DifficultyMode adnotation = null; try { adnotation =
			 * this.getClass().getMethod("easyMode").getAnnotation(DifficultyMode.class);
			 * 
			 * } catch (NoSuchMethodException | SecurityException e) { e.printStackTrace();
			 * }
			 */
			easyMode(sizeOfTicTacToe);
		}

		if (difficulty == Difficulty.NORMAL) {
			return chooseRandomMove(sizeOfTicTacToe);
		}
		if (difficulty == Difficulty.HARD) {

			if (chooseLuckyPositionInCenterOfBoard(sizeOfTicTacToe) != null) {
				return chooseLuckyPositionInCenterOfBoard(sizeOfTicTacToe);
			}

			int winMove[] = nextWinningMove(player, sizeOfTicTacToe);
			if (winMove != null)
				return winMove;

			/* choose the move that prevent enemy to win */
			for (int i = 0; i < sizeOfTicTacToe; i++)
				for (int j = 0; j < 3; j++)
					if (getBoardValue(i, j, sizeOfTicTacToe) == EMPTY) {
						board[i][j] = player;
						boolean ok = nextWinningMove(swapPlayers(player), sizeOfTicTacToe) == null;
						board[i][j] = EMPTY;
						if (ok)
							return new int[] { i, j };
					}

			return chooseAvailableMove(sizeOfTicTacToe);
		}

		return chooseAvailableMove(sizeOfTicTacToe);
	}

	@DifficultyMode(easyMode = { 3 }, value = Difficulty.EASY)
	private int[] easyMode(int sizeOfTicTacToe) {
		return chooseAvailableMove(sizeOfTicTacToe);
	}

	private int[] chooseAvailableMove(int sizeOfTicTacToe) {
		for (int i = 0; i < sizeOfTicTacToe; i++)
			for (int j = 0; j < sizeOfTicTacToe; j++)
				if (getBoardValue(i, j, sizeOfTicTacToe) == EMPTY)
					return new int[] { i, j };
		return null;
	}

	private int[] chooseRandomMove(int sizeOfTicTacToe) {

		int i = getRandomNumberInRange(0, sizeOfTicTacToe - 1);
		int j = getRandomNumberInRange(0, sizeOfTicTacToe - 1);
		if (getBoardValue(i, j, sizeOfTicTacToe) == EMPTY) {
			return new int[] { i, j };
		}

		return chooseAvailableMove(sizeOfTicTacToe);

	}

	private int[] chooseLuckyPositionInCenterOfBoard(int sizeOfTicTacToe) {
		int centerOfBoard = (sizeOfTicTacToe - 1) / 2;
		if (getBoardValue(centerOfBoard, centerOfBoard, sizeOfTicTacToe) == EMPTY)
			return new int[] { centerOfBoard, centerOfBoard };
		return null;
	}

	private static int getRandomNumberInRange(int min, int max) {

		Random r = new Random();
		return r.ints(min, (max + 1)).findFirst().getAsInt();

	}

	public int[] nextWinningMove(int player, int sizeOfTicTacToe) {

		for (int i = 0; i < sizeOfTicTacToe; i++)
			for (int j = 0; j < sizeOfTicTacToe; j++)
				if (getBoardValue(i, j, sizeOfTicTacToe) == EMPTY) {
					board[i][j] = player;
					boolean win = determineCurrentMove(player, sizeOfTicTacToe);
					board[i][j] = EMPTY;
					if (win)
						return new int[] { i, j };
				}

		return null;
	}

	public int swapPlayers(int token) {
		return token == PLAYER_ONE ? PLAYER_TWO : PLAYER_ONE;
	}

	public boolean determineCurrentMove(int player, int sizeOfTicTacToe) {
		final int DI[] = { -1, 0, 1, 1 };
		final int DJ[] = { 1, 1, 1, 0 };
		for (int i = 0; i < sizeOfTicTacToe; i++)
			for (int j = 0; j < sizeOfTicTacToe; j++) {

				if (getBoardValue(i, j, sizeOfTicTacToe) != player)
					continue;

				for (int k = 0; k < 4; k++) {
					int ctr = 0;
					while (getBoardValue(i + DI[k] * ctr, j + DJ[k] * ctr, sizeOfTicTacToe) == player) {
						ctr++;
					}

					if (ctr == sizeOfTicTacToe)
						return true;
				}
			}
		return false;
	}
}
