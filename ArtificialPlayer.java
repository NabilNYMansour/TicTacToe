public class ArtificialPlayer extends Player {

    public ArtificialPlayer(String c) {
        super(c);
        this.canWin = false;
    }

    // Planing for checkWin:
    private int position1;
    private int position2;

    private boolean canWin;

    // 1. see if cpu can win:
    public void checkWillWin(final String[] positions) {
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

    public void placeRandom(String[] positions) {
        int random = (int) (Math.random() * (Math.random() * positions.length));
        if (Board.isNumber(positions[random])) {
            positions[random] = this.getCharacter();
        } else {
            placeRandom(positions); // Recursive!
        }
    }

    public void placeValue(String[] positions) {
        if (positions[position1].equals(this.getCharacter())) { // If the first position is already placed
            if (Board.isNumber(positions[position2])) {
                positions[position2] = this.getCharacter();
            } else {
                placeRandom(positions);
            }
        } else {
            positions[position1] = this.getCharacter();
        }
    }

    public void cantWin(String[] positions, Player otherPlayer) {
        if (positions[position1].equals(otherPlayer.getCharacter())
                || positions[position2].equals(otherPlayer.getCharacter())) {
            this.canWin = false;
        }
    }

    public void printPos() { // Used for testing purposes.
        System.out.println(this.position1 + ", " + this.position2);
    }
}
