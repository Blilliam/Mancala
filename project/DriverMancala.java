
import java.util.Scanner;

public class DriverMancala {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		Mancala m = new Mancala();

		System.out.println("=== MANCALA GAME ===");
		System.out.println("1. Play manually");
		System.out.println("2. Run full test");
		System.out.print("Choose option: ");
		int choice = input.nextInt();

		if (choice == 2) {
			runTests();
			return;
		}

		while (true) {

			System.out.println(m);
			System.out.print("Choose a bucket (0-11) or -1 to quit: ");

			int move = input.nextInt();

			if (move == -1) {
				System.out.println("Game ended.");
				break;
			}

			boolean ok = m.makeMove(move);

			if (!ok) {
				System.out.println("Invalid move. Try again.");
				continue;
			}

			// Check if game is over
			if (m.isGameOver()) {

				// sweep remaining stones first
				m.sweepRemainingStones();

				System.out.println(m);
				System.out.println("=== GAME OVER ===");

				int p1 = m.getBox1();
				int p2 = m.getBox2();

				System.out.println("Player 1 Score: " + p1);
				System.out.println("Player 2 Score: " + p2);
				System.out.println("Winner: " + m.getWinner());

				break;
			}
		}

		input.close();
	}

//testing
	public static void runTests() {

		System.out.println("\n=== Test Cases ===");
		
		testScoringDistribution();
		testIllegalMoves();
		testFreeTurn();
		testCaptureRule();
		testGameOverSweep();

		System.out.println("\n=== TESTING COMPLETE ===");
	}

//Illegal Moves
	public static void testIllegalMoves() {
		System.out.println("\n---------- Illegal Moves ----------");
		Mancala m = new Mancala();

		System.out.println("Move on opponent side:");
		System.out.println("Move 8: " + m.makeMove(8));

		System.out.println("\nMove empty bucket:");
		m.getBuckets()[3] = 0;
		System.out.println("Move 3: " + m.makeMove(3));
	}

// Extra turn
	public static void testFreeTurn() {
		System.out.println("\n---------- Free Turn Rule ----------");
		Mancala m = new Mancala();

		// Set bucket 5 so the last stone lands in box1
		m.getBuckets()[5] = 1;
		m.setP1(true);

		System.out.println("Before move:");
		System.out.println(m);

		m.makeMove(5);

		System.out.println("After move:");
		System.out.println(m);

		System.out.println("Still P1 turn? " + m.getIsP1());
	}

// opposite capture
	public static void testCaptureRule() {
		System.out.println("\n---------- Capture Rule ----------");

		Mancala m = new Mancala();

		// set up capture:
		// last stone lands in empty bucket 2
		m.getBuckets()[2] = 0;
		m.getBuckets()[9] = 3; // opposite bucket
		m.getBuckets()[1] = 1; // move bucket

		m.setP1(true);

		System.out.println("Before:");
		System.out.println(m);

		m.makeMove(1);

		System.out.println("After:");
		System.out.println(m);
		System.out.println("Box1 should have captured 4 stones: " + m.getBox1());
	}

//sweep
	public static void testGameOverSweep() {
		System.out.println("\n---------- End Game Sweep ----------");

		Mancala m = new Mancala();

		// P1 empty
		for (int i = 0; i < 6; i++)
			m.getBuckets()[i] = 0;

		// P2 has remaining stones
		m.getBuckets()[7] = 5;

		// Make any P2 move
		System.out.println(m);
		
		m.setP1(false);
		m.makeMove(7);
		
		System.out.println(m);
		// Sweep manually, in case move didn't end game immediately
		if (m.isGameOver()) {
			m.sweepRemainingStones();
		}

		System.out.println(m);
		System.out.println("Box2 should have +24 (total 25) stones: " + m.getBox2());
	}

//move correctness
	public static void testScoringDistribution() {
		System.out.println("\n---------- Distribution ----------");

		Mancala m = new Mancala();
		m.getBuckets()[5] = 18;

		System.out.println("Before:");
		System.out.println(m);

		m.makeMove(5);

		System.out.println("After:");
		System.out.println(m);
	}

}
