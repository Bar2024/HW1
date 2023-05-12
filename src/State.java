public class State {
    static Board board;
    final Board WANTED_BOARD;
    static EnumDirection direction;
    public String possibleAction[];

    /**
     * The constructor for Board.
     *
     * @param board The current state of the board
     */
    public State(Board board, EnumDirection direction) {
        this.board = board;
        WANTED_BOARD = board.getWantedBoard();
        this.direction = direction;
    }

    /**
     * checks if the current state of the board is the wanted state
     *
     * @return true if the both boards are equals, false otherwise
     */
    public boolean isGoal() {
        if (board.equals(WANTED_BOARD)) {
            return true;
        }
        return false;
    }

    public String[] actions() {


    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof State)) {
            return false;
        }
        State otherState = (State) other;
        return board.equals(otherState.board);
    }

    @Override
    public int hashCode() {
        return board.hashCode();
    }

    // change the next method according to the way the board is madee
    public Board result(Action action) {
        Tile tile = action.getTileAction();
        EnumDirection direction = action.getDirection();
        // GET THE INDEX OF THE WANTED TILE
        int n;
        int m;
        if (direction == EnumDirection.UP) {
            board[n - 1][m] = board[n][m];
            board[n][m] = "_";
        } else if (direction == EnumDirection.DOWN) {
            board[n + 1][m] = board[n][m];
            board[n][m] = "_";
        } else if (direction == EnumDirection.RIGHT) {
            board[n][m + 1] = board[n][m];
            board[n][m] = "_";
        } else if (direction == EnumDirection.LEFT) {
            board[n][m - 1] = board[n][m];
            board[n][m] = "_";
        }
        return board;
    }
}
