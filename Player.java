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
    public void setWin(boolean condition) {
        this.win = condition;
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

    /**
     * Check if the player placed a false parameter and keeps their turn running if
     * so.
     * 
     * @param otherPlayer: the other player
     */
    public void checkError(Player otherPlayer) {
        if (this.error) {
            this.PlayerTurn = true;
            otherPlayer.setTurn(false);
        }
    }

}
