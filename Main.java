import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // Making the positions:
        String[] positions = new String[9];
        Board.setBoard(positions);

        // Initializing the game:
        int selection = -1; // To be used to check the value the user placed.
        Scanner input; // The user will input information through this.
        boolean gameGoing = true; // To check if the game should end.
        boolean draw = false; // To be used to check if there is a draw.
        boolean againstCPU = true; // To check if two players are playing.

        boolean cpuStart = true; // Just for the initial value of the cpu.

        // Establishing the game
        System.out.print("Hello, this is a tic tac toe game, press anything to start: ");
        input = new Scanner(System.in);
        input.nextLine();

        System.out.println("Do you want to play with a human? (Y/N) ");
        if (input.nextLine().substring(0, 1).equalsIgnoreCase("y")) {
            againstCPU = false;
        }

        // Setting up the players:
        HumanPlayer p1 = new HumanPlayer("x");
        HumanPlayer p2 = new HumanPlayer("o");
        ArtificialPlayer pc = new ArtificialPlayer("o");

        p1.setTurn(true); // Player 1 will start the game regardless of CPU or Player 2.

        // While the game is running...
        while (gameGoing) {

            // Printing the board:
            Board.printScreen(positions);

            // Player 1 turn:
            if (p1.getTurn()) {
                // input = new Scanner(System.in);

                p1.placeValue(input, selection, positions, p2);

                // Checking if Player won:
                if (Board.checkWin(p1.getCharacter(), positions)) {
                    gameGoing = false;
                    p1.setWin(true);
                }

            } // Player 2 turn:
            else {
                if (!againstCPU) {
                    // input = new Scanner(System.in);

                    p2.placeValue(input, selection, positions, p1);

                    // Checking if Player won:
                    if (Board.checkWin(p2.getCharacter(), positions)) {
                        gameGoing = false;
                        p2.setWin(true);
                    }
                } else {
                    // !!!!!!!!!!!!!!!!!!!!!!!!!!AI CODE HERE!!!!!!!!!!!!!!!!!!!!!!!!!!
                    if (cpuStart) {
                        if (!positions[4].equals(p1.getCharacter())) {
                            positions[4] = pc.getCharacter();
                        } else {
                            pc.placeRandom(positions);
                        }
                        cpuStart = false;
                    } else {
                        pc.cantWin(positions, p1);
                        pc.checkWillWin(positions);
                        pc.placeValue(positions);
                    }
                    p1.setTurn(true);
                    p2.setTurn(false);
                    if (Board.checkWin(p2.getCharacter(), positions)) {
                        gameGoing = false;
                        p2.setWin(true);
                    }
                }
            }
            if (Board.checkDraw(p1.getCharacter(), p2.getCharacter(), positions)) {
                draw = true;
                gameGoing = false;
            }
            if (!gameGoing) {
                // Win Screen:
                Board.printScreen(positions);
                if (draw) {
                    System.out.println("Draw!");
                } else if (p1.getWin()) {
                    System.out.println("Player x Wins!");
                } else {
                    System.out.println("Player o Wins!");
                }
                // Play again?
                input.nextLine();
                System.out.println("Do you want to play again? (Y/N) ");
                if (input.nextLine().substring(0, 1).equalsIgnoreCase("y")) {
                    // Resetting the game:
                    gameGoing = true;
                    Board.setBoard(positions);
                    p1.setTurn(true);
                    p2.setTurn(false);
                    p1.setWin(false);
                    p2.setWin(false);
                    cpuStart = true;

                    // Asking if the player wants to play with a human again:
                    System.out.println("Do you want to play with a human? (Y/N) ");
                    if (input.nextLine().substring(0, 1).equalsIgnoreCase("y")) {
                        againstCPU = false;
                    } else {
                        againstCPU = true;
                    }
                } else {
                    input.close();
                }
            }
        }
    }

}
