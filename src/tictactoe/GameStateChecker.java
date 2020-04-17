package tictactoe;

public class GameStateChecker {

    protected Game game;

    public GameStateChecker(Game game) {
        this.game = game;
    }

    public GameState checkGame() {
        GameState linesGameState = checkLines();
        GameState columnsGameState = checkColumns();
        GameState diagonalsGameState = checkDiagonals();
        GameState valuesPlayed = checkImpossibleByNumberOfValues();

        GameState currentState;

        if (hasGameStateValue(linesGameState, columnsGameState, diagonalsGameState, valuesPlayed, GameState.IMPOSSIBLE)) {
            currentState = GameState.IMPOSSIBLE;
        } else if (hasGameStateValue(linesGameState, columnsGameState, diagonalsGameState, valuesPlayed, GameState.X_WINS)) {
            currentState = GameState.X_WINS;
        } else if (hasGameStateValue(linesGameState, columnsGameState, diagonalsGameState, valuesPlayed, GameState.O_WINS)) {
            currentState = GameState.O_WINS;
        } else if (!game.hasEmptySpacesLeft()) {
            currentState = GameState.DRAW;
        } else {
            currentState = GameState.NOT_FINISHED;
        }

        return currentState;
    }

    /**
     * Check if a player has way more pieces at play than another.
     * @return {@link GameState}
     */
    private GameState checkImpossibleByNumberOfValues() {
        int xCount = 0;
        int oCount = 0;

        for (int line = 0; line < 3; line++) {
            for (int colunm = 0; colunm < 3; colunm++) {
                if (game.getValueAt(line, colunm) == PossibleValues.X) {
                    xCount++;
                } else if (game.getValueAt(line, colunm) == PossibleValues.O) {
                    oCount++;
                }
            }
        }

        if (xCount - oCount > 1 || oCount - xCount > 1) {
            return GameState.IMPOSSIBLE;
        }
        return game.getCurrentState();
    }

    /**
     * Check both diagonal lines for a winner
     * @return {@link GameState}
     */
    private GameState checkDiagonals() {

        boolean XWins = false;
        boolean OWins = false;

        // Top-left to bottom-right;
        if (game.getValueAt(0, 0) == game.getValueAt(1, 1) && game.getValueAt(1, 1) == game.getValueAt(2, 2) && game.getValueAt(1, 1) != PossibleValues.BLANK) {
            if (game.getValueAt(0, 0) == PossibleValues.X) {
                XWins = true;
            } else {
                OWins = true;
            }
        }

        // bottom-right to top-left;
        if (game.getValueAt(0, 2) == game.getValueAt(1, 1) && game.getValueAt(1, 1) == game.getValueAt(2, 0) && game.getValueAt(1, 1) != PossibleValues.BLANK) {
            if (game.getValueAt(0, 0) == PossibleValues.X) {
                XWins = true;
            } else {
                OWins = true;
            }
        }

        return resolveXorOWins(XWins, OWins);
    }

    /**
     * Check every single column for a winner
     * @return {@link GameState}
     */
    private GameState checkColumns() {

        boolean XWins = false;
        boolean OWins = false;

        for (int column = 0; column < 3; column++) {
            if (game.getValueAt(0, column) == PossibleValues.X &&
                    game.getValueAt(1, column) == PossibleValues.X &&
                    game.getValueAt(2, column) == PossibleValues.X) {
                XWins = true;
            } else if (game.getValueAt(0, column)== PossibleValues.O &&
                    game.getValueAt(1, column) == PossibleValues.O &&
                    game.getValueAt(2, column) == PossibleValues.O) {
                OWins = true;
            }
        }

        return resolveXorOWins(XWins, OWins);
    }

    /**
     * Check every single line for a winner
     * @return {@link GameState}
     */
    private GameState checkLines() {

        boolean XWins = false;
        boolean OWins = false;

        for (int line = 0; line < 3; line++) {
            if (game.getValueAt(line, 0) == PossibleValues.X &&
                    game.getValueAt(line, 1) == PossibleValues.X &&
                    game.getValueAt(line, 2) == PossibleValues.X) {
                XWins = true;
            } else if (game.getValueAt(line, 0) == PossibleValues.O &&
                    game.getValueAt(line, 1) == PossibleValues.O &&
                    game.getValueAt(line, 2) == PossibleValues.O) {
                OWins = true;
            }
        }

        return resolveXorOWins(XWins, OWins);
    }

    /**
     * Used to check if x and o has win the game, returns the state based on that.
     * @param XWins if x has win
     * @param OWins if o has win
     * @return the game state.
     */
    private GameState resolveXorOWins(boolean XWins, boolean OWins) {
        if (XWins && OWins) {
            return GameState.IMPOSSIBLE;
        } else if (XWins) {
            return GameState.X_WINS;
        } else if (OWins) {
            return GameState.O_WINS;
        } else {
            return GameState.NOT_FINISHED;
        }
    }

    /**
     * Checks if one of the checks has a expected value.
     * @param linesCheck state resolved from line check.
     * @param colunmsCheck state resolved from columns check.
     * @param diagonalCheck state resolved from diagonal check.
     * @param valuesCheck state resolved from values.
     * @param expectedValue expected state.
     * @return if one of them have.
     */
    private boolean hasGameStateValue(GameState linesCheck, GameState colunmsCheck, GameState diagonalCheck, GameState valuesCheck, GameState expectedValue) {
        return linesCheck == expectedValue ||
                colunmsCheck == expectedValue ||
                diagonalCheck == expectedValue ||
                valuesCheck == expectedValue;
    }

}
