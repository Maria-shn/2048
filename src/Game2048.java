import java.util.Scanner;

public class Game2048 {

    public static void main(String[] args) {
        terminalGame();
    }

    public static void terminalGame(){
        Board gameBoard = new Board();
        gameBoard.newTile();
        gameBoard.newTile();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Clear the terminal screen (if supported)
            System.out.print("\033[H\033[2J");
            System.out.flush();

            // Display the game board
            gameBoard.displayBoard();

            // Get user input
            System.out.println("Enter direction (W/A/S/D or Q to quit): ");
            String input = scanner.nextLine().toUpperCase();
            
            if (input.equals("Q")) {
                System.out.println("Game Over. Quitting.");
                break;
            }

            if (input.equals("W")) {
                gameBoard.direction = 'U';
            } else if (input.equals("A")) {
                gameBoard.direction = 'L';
            } else if (input.equals("S")) {
                gameBoard.direction = 'D';
            } else if (input.equals("D")) {
                gameBoard.direction = 'R';
            } else {
                System.out.println("Invalid input. Use W/A/S/D or Q to quit.");
                continue;
            }

            // Make a move
            gameBoard.move();

            // Generate a new tile
            gameBoard.newTile();
            
            // Check for game over condition (no more moves)
            if (!gameBoard.canMove()) {
                System.out.println("Game Over. No more moves!");
                break;
            }
        }

        scanner.close();
    }
}

    