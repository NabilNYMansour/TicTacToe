import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // Making the positions:
        String[] positions = new String[9];
        for (Integer i = 0; i < 9; i++) {
            positions[i] = i.toString(); // Staring with spaces
        }
        // Initialing the game:
        int selection = -1; // To be used to check the value the user placed.
        Scanner input; // The user will input information through this.
        boolean gameGoing = true; // To check if the game should end.
        boolean draw = false; // To be used to check if there is a draw.
        boolean againstCPU = true; // To check if two players are playing.

        System.out.print("Hello, this is a tic tac toe game, press anything to start: ");
        input = new Scanner(System.in);
        input.nextLine();
        System.out.println("Do you want to play with a human? (Y/N) ");
        if (input.nextLine().substring(0, 1).equalsIgnoreCase("y")) {
            againstCPU = false;
        }

        // Setting up the players:
        Player p1 = new Player("x");
        Player p2 = new Player("o");
        if (againstCPU) {
            // Code for initializing the cpu as the other player.
        }
        p1.setTurn(true); // Player 1 will start the game regardless of CPU or Player 2.

        // While the game is running...
        while (gameGoing) {

            // Printing the board:
            printScreen(positions);

            // Player 1 turn:
            if (p1.getTurn()) {
                input = new Scanner(System.in);

                p1.placeValue(input, selection, positions, p2);

                // Checking if Player won:
                if (checkWin(p1.getCharacter(), positions)) {
                    gameGoing = false;
                    p1.ActivateWin();
                }

            } // Player 2 turn:
            else {
                if (!againstCPU) {
                    input = new Scanner(System.in);

                    p2.placeValue(input, selection, positions, p1);

                    // Checking if Player won:
                    if (checkWin(p2.getCharacter(), positions)) {
                        gameGoing = false;
                        p2.ActivateWin();
                    } else {
                        // !!!!!!!!!!!!!!!!!!!!!!!!!!AI CODE HERE!!!!!!!!!!!!!!!!!!!!!!!!!!
                        // For a draw, have it be after u check if the cpu has won such that if cpu and
                        // player have both won, then draw = true.
                    }
                }
            }
            if (checkDraw(p1.getCharacter(), p2.getCharacter(), positions)) {
                draw = true;
                gameGoing = false;
            }
        }
        input.close();
        // Win Screen:
        printScreen(positions);
        if (draw) {
            System.out.println("Draw!");
        } else if (p1.getWin()) {
            System.out.println("Player x Wins!");
        } else {
            System.out.println("Player o Wins!");
        }
        // Write code for play again.
    }

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
                            || ((positions[i] == character && positions[i + 2] == character
                                    && positions[i + 4] == character))) {
                        return true;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }
        }
        return false;
    }

    //
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

