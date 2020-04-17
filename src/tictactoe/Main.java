package tictactoe;

import java.util.Scanner;

public class Main {
    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize game.
        Game game = new Game();
        // Game first print.
        game.printBoard();

        // Game loop.
        while(game.canKeepPlaying()) {
            // Asks for input
            System.out.print("Enter the coordinates: ");
            String line = scanner.nextLine();

            // Validate format.
            if(!line.matches("\\d \\d")) {
                System.out.println("You should enter numbers!");
                continue;
            }

            // Parse input into params.
            String[] numbersStr = line.trim().split(" ");
            int x = Integer.valueOf(numbersStr[0]);
            int y = Integer.valueOf(numbersStr[1]);

            // Attempt play!
            boolean madePlay = game.playAt(x, y);
            if(madePlay) {
                game.printBoard();
            }
        }

        System.out.println(game.getCurrentState());
    }
}
