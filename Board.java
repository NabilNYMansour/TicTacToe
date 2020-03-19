import java.io.IOException;

public class Board {
    /**
     * Clears the terminal and prints the board in it.
     * 
     * @param positions of values in the board.
     * @throws IOException
     */
    public static void printScreen(String[] positions) throws IOException {
        System.out.println("\n".repeat(50));
        System.out.println("---------");
        for (int i = 0; i < 9; i++) {
            if ((i % 3 == 0) && (i != 0)) {
                System.out.print("\n---------\n|" + positions[i] + "|");
            } else {
                System.out.print("|" + positions[i] + "|");
            }
        }
        System.out.println("\n---------");
    }

    /**
     * A method that returns true if the Player has won the game and false otherwise
     * given the positions{String[]} of the board.
     * 
     * @param positions of the Strings in the board.
     * @return boolean
     */
    public static boolean checkWin(String character, String[] positions) {
        for (int i = 0; i < positions.length; i++) {
            try {
                // Check Vlines:
                if (i % 3 == 0) {
                    if (positions[i] == character && positions[i + 1] == character && positions[i + 2] == character) {
                        return true;
                    }
                }
                // Check Hlines:
                if (i < 3) {
                    if (positions[i] == character && positions[i + 3] == character && positions[i + 6] == character) {
                        return true;
                    }
                }
                // Check Xlines:
                if (i % 2 == 0) {
                    if ((positions[i] == character && positions[i + 4] == character && positions[i + 8] == character)
                            || ((positions[2] == character && positions[4] == character
                                    && positions[6] == character))) {
                        return true;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * Checks if there are no more moves a player can make to win. Will return true
     * if there are none, false otherwise.
     * 
     * @param characterPlayer1
     * @param characterPlayer2
     * @param positions        of the board
     * @return boolean
     */
    public static boolean checkDraw(String characterPlayer1, String characterPlayer2, final String[] positions) {
        // Clone the positions:
        String[] P1positions = positions.clone();
        String[] P2positions = positions.clone();
        // Replace all the values by the characters:
        for (int i = 0; i < positions.length; i++) {
            if (isNumber(positions[i])) {
                P1positions[i] = characterPlayer1;
                P2positions[i] = characterPlayer2;
            }
        }
        // If neither can win, return true, false otherwise.
        if (!(checkWin(characterPlayer1, P1positions) || checkWin(characterPlayer2, P2positions))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the String s is a number. True if it is, false otherwise.
     * 
     * @param s String
     * @return boolean
     */
    public static boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}