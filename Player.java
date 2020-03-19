import java.util.InputMismatchException;
import java.util.Scanner;

class Player {
    private boolean error; // In the case of an error.
    private boolean alreadyPLaced; // If the player has already placed a value.
    private boolean PlayerTurn; // To identify the player's turn.
    private boolean win; // To be used to show win or loss screen.
    private String character; // The character to be used.

    /**
     * Constructor method for Player class.
     * 
     * @param c The character that the Player will be placing.
     */
    public Player(String c) {
        this.error = false;
        this.alreadyPLaced = false;
        this.PlayerTurn = false;
        this.win = false;
        this.character = c.substring(0, 1); // In the case of multiple Strings input error.
    }

    /**
     * 
     * @return Player character
     */
    public String getCharacter() {
        return this.character;
    }

    /**
     * 
     * @return true if its the player's turn, false otherwise.
     */
    public boolean getTurn() {
        return this.PlayerTurn;
    }

    /**
     * 
     * @return true if player has won, false otherwise.
     */
    public boolean getWin() {
        return this.win;
    }

    public boolean getAlreadyPlaced() {
        return this.alreadyPLaced;
    }

    /**
     * Sets the turn of the player. True if its the player's turn, false otherwise.
     * 
     * @param condition boolean
     */
    public void setTurn(boolean condition) {
        this.PlayerTurn = condition;
    }

    /**
     * Sets the error condition of the Player. To be used if an error is raised or
     * to remove error token.
     * 
     * @param condition boolean
     */
    public void setError(boolean condition) {
        this.error = condition;
    }

    /**
     * Sets the alreadyPlayed on or off. To be used when the player places an
     * illegal value or to remove the alreadyPlayed token.
     * 
     * @param condition boolean
     */
    public void setAlreadyPlaced(boolean condition) {
        this.alreadyPLaced = condition;
    }

    /**
     * Sets the win token to true. To be used when a player has won.
     */
    public void ActivateWin() {
        this.win = true;
    }

    /**
     * Prints error screens depending on what type of error is raised.
     */
    public void printErrors() {
        if (this.error) { // If an error has been placed, its still the player's turn._
            System.out.println("You placed the wrong value, place an int less than 9.");
            this.error = false;
        }
        if (this.alreadyPLaced) {
            System.out.println("Cannot place value.");
            this.alreadyPLaced = false;
        }
    }

    /**
     * Checks if the value placed by the player is invalid or illegal and switches
     * the players tokens accordingly.
     * 
     * @param positions    of the board.
     * @param index        of the position.
     * @param otherPlayer: The other player.
     */
    public void checkPlaced(String[] positions, int index, Player otherPlayer) {
        if (!Board.isNumber(positions[index])) {
            this.alreadyPLaced = true;
            this.PlayerTurn = true;
            otherPlayer.setTurn(false);
        } else {
            this.PlayerTurn = false;
            otherPlayer.setTurn(true);
        }
    }

}

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

            // Checking if the player has already placed this value:
            this.checkPlaced(positions, selection, otherPlayer);

            // Placing the value in the board:
            if (!this.getAlreadyPlaced()) {
                positions[selection] = this.getCharacter();
            }
            // In the case of an error:
        } catch (ArrayIndexOutOfBoundsException e) {
            this.setError(true);
        } catch (InputMismatchException e) {
            this.setError(true);
        }
    }
}