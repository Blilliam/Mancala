import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		Mancala game = new Mancala();
		Scanner input = new Scanner(System.in);
		System.out.println(game);
		System.out.println(game.getIsP1());
		System.out.println(game.piecesLeft());
		
		//starting the actual game
		int userInput = input.nextInt();
		
		
		while (userInput != 100) {
			System.out.println("-------------------------------"
					+ "\n\n" + "Did the move work: " + game.makeMove(userInput));
			System.out.println(game);
			System.out.println("\nIs it Player 1's turn: " + game.getIsP1());
			System.out.println("Pieces Left: " + game.piecesLeft());
			System.out.println("Make your move: ");
			userInput = input.nextInt();
			System.out.println(game.makeMove(userInput));			
		}
		game.resetBoard();
		System.out.println(game);
		

	}

}