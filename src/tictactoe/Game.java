package tictactoe;

import java.util.Arrays;

/**
 * Represents a game.
 */
public class Game {

    PossibleValues[][] board = new PossibleValues[3][3];
    GameState currentState = GameState.NOT_FINISHED;
    int emptySpaceCount = 9;
    private PossibleValues currentPlayer;

    public Game() {
        initBoard();
    }

    /**
     * Create a gameboard from a character sequence like _XX_XOXOO
     * @param values character array.
     */
    public Game(char[] values) {
        initBoard();
        int movesLeft = 9;
        for (int line = 0; line < 3; line++) {
            for (int column = 0; column < 3; column++) {
                int position = 9 - movesLeft;
                makeMove(line, column, PossibleValues.withCharValue(values[position]));
                movesLeft--;
            }
        }

        updateGameState();
    }

    /**
     * This is the interface from with the game can be played.
     * @param x coordinate on x axis.
     * @param y coordinate on y axis.
     * @return boolean if the move was valid.
     */
    public boolean playAt(int x, int y) {
        if(y > 3 || y < 1 || x > 3 || x < 1) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }
        // Bringing then to 0 base.
        x--;
        y--;

        if (y == 0) {
            y = 2;
        } else if ( y == 2 ) {
            y = 0;
        }

        try {
            makeMove(y, x, currentPlayer);
            currentPlayer = currentPlayer == PossibleValues.X ? PossibleValues.O : PossibleValues.X;
            updateGameState();
        } catch (IllegalArgumentException exception) {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }

        return true;
    }

    private void makeMove(int line, int column, PossibleValues value) {
        // If the value played its blank then we shouldn't consider it.
        if (value == PossibleValues.BLANK) {
            return;
        }

        // If the position already has values, jump off.
        if (board[line][column] != PossibleValues.BLANK) {
            throw new IllegalArgumentException("Position already " + line + " " + column + " has a value (" + board[line][column].toString() + ")");
        }

        board[line][column] = value;
        emptySpaceCount--; // Used to determine if there is spaces to continue playing.
    }

    public void updateGameState() {
        GameStateChecker checker = new GameStateChecker(this);
        currentState = checker.checkGame();
    }

    public boolean canKeepPlaying() {
        return currentState == GameState.NOT_FINISHED;
    }

    /**
     * Prints the current GameBoard;
     */
    public void printBoard() {
        StringBuilder outputBuilder = new StringBuilder();
        // Adding header
        outputBuilder.append("---------\n");
        // Mounting the lines:
        for (int i = 0; i < 3; i++) {
            outputBuilder
                    .append("| ")
                    .append(board[i][0])
                    .append(" ")
                    .append(board[i][1])
                    .append(" ")
                    .append(board[i][2])
                    .append(" |\n");
        }

        // Adding footer
        outputBuilder.append("---------");

        System.out.println(outputBuilder);
    }

    /**
     * This method initializes an empty board;
     */
    private void initBoard() {
        // Reset board values;
        for (PossibleValues[] line : board) {
            Arrays.fill(line, PossibleValues.BLANK);
        }

        // Set spaces left count;
        this.emptySpaceCount = 9;

        // Defines gamestate
        this.currentState = GameState.NOT_FINISHED;

        // Defines player 1:
        this.currentPlayer = PossibleValues.X;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public PossibleValues getValueAt(int line, int column) {
        return board[line][column];
    }

    public boolean hasEmptySpacesLeft() {
        return emptySpaceCount != 0;
    }
}
