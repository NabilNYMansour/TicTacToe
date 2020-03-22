public class ArtificialPlayer extends Player {

    /**
     * Constructor method for ArtificialPlayer
     * 
     * @param c the character of the player.
     */
    public ArtificialPlayer(String c) {
        super(c);
        this.canWin = false;
    }

    // Planing for checkWin:
    private int position1; // First position of the plan.
    private int position2; // Second position of the plan

    private boolean canWin; // A boolean to know if the ai can win by executing the plan.

    /**
     * Creates a plan of action for the AI to execute.
     * 
     * @param positions of the board.
     */
    public void createPlan(final String[] positions) {
        if (!canWin) {
            String[] positionsClone = positions.clone();
            for (int i = 0; i < positions.length; i++) {
                if (Board.isNumber(positionsClone[i])) {
                    positionsClone[i] = this.getCharacter();
                } else {
                    continue;
                }
                for (int j = i + 1; j < positions.length; j++) { // Check all the positions after i.
                    if (Board.isNumber(positionsClone[j])) {
                        positionsClone[j] = this.getCharacter();
                    } else {
                        continue;
                    }
                    if (Board.checkWin(this.getCharacter(), positionsClone)) {
                        this.position1 = i;
                        this.position2 = j;
                        canWin = true;
                        return; // Stop all the looping if it works!
                    } else { // Resetting the board if cpu cannot win
                        positionsClone[j] = "0"; // Any number can work
                    }
                }
                positionsClone[i] = "1"; // Reset if it doesn't work
            }
        }
    }

    /**
     * Places a value randomly on the board.
     * 
     * @param positions of the board.
     */
    public void placeRandom(String[] positions) {
        int random = (int) (Math.random() * (Math.random() * positions.length));
        if (Board.isNumber(positions[random])) {
            positions[random] = this.getCharacter();
        } else {
            placeRandom(positions); // Recursive!
        }
    }

    /**
     * Places a value on the board according to the plan the AI is currently
     * executing.
     * 
     * @param positions of the board.
     */
    public void placeValue(String[] positions) {
        if (positions[position1].equals(this.getCharacter())) { // If the first position is already placed
            if (Board.isNumber(positions[position2])) { // If you can place in position 2
                positions[position2] = this.getCharacter(); // Place char in position 2.
            } else { // If you can't place in position 2, then create a new plan and place value of
                     // position1.
                this.createPlan(positions);
                if (this.canWin) { // If after checking, you can win
                    positions[position1] = this.getCharacter(); // Execute the plan.
                } else { // Otherwise, just place randomly.
                    this.placeRandom(positions);
                }
            }
        } else {
            positions[position1] = this.getCharacter();
        }
    }

    /**
     * Checks if the plan can be executed. To be used in order to check if the ai
     * needs to make a new plan.
     * 
     * @param positions   of the board
     * @param otherPlayer of the game.
     */
    public void checkWin(String[] positions, Player otherPlayer) {
        if (positions[position1].equals(otherPlayer.getCharacter())
                || positions[position2].equals(otherPlayer.getCharacter())) {
            this.canWin = false;
        }
    }

    /**
     * Prints the current positions the ai is thinking of. To be used for testing
     * purposes.
     */
    public void printPos() {
        System.out.println(this.position1 + ", " + this.position2);
    }
}
