import java.util.InputMismatchException;
import java.util.Scanner;

class HumanPlayer extends Player {

    /**
     * Constructor method for a human player.
     * 
     * @param c the character the player will be placing.
     */
    public HumanPlayer(String c) {
        super(c);
        // TODO Auto-generated constructor stub
    }

    /**
     * Places the value inserted by the player into the board. It handles any errors
     * that may arise
     * 
     * @param input        of the user.
     * @param selection    of the user.
     * @param positions    of the board.
     * @param otherPlayer: The other player
     */
    public void placeValue(Scanner input, int selection, String[] positions, Player otherPlayer) {
        try {
            // Showing who's turn:
            System.out.println("Player " + this.getCharacter() + ", Your Turn:");

            // If error or already placed:
            this.printErrors();

            // Getting the value from the user:
            System.out.print("Place position ");
            selection = input.nextInt();

            // Checking if the player has already placed this value or has placed a false
            // value:
            this.checkPlaced(positions, selection, otherPlayer);
            this.checkError(otherPlayer);

            // Placing the value in the board:
            if (!this.getAlreadyPlaced()) {
                positions[selection] = this.getCharacter();
            }
            // In the case of an error:
        } catch (ArrayIndexOutOfBoundsException e) {
            this.setError(true);
            input.nextLine();
        } catch (InputMismatchException e) {
            this.setError(true);
            input.nextLine();
        }
    }
}