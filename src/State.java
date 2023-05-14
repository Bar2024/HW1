public class State {
    static Board board;
    static Board WANTED_BOARD;
    static Direction direction;

    /**
     * The constructor for Board.
     *
     * @param board The current state of the board
     */
    public State(Board board, Direction direction) {
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


    public Board getBoard() {
        return board;
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

    public Action[] actions() {

        Action[] possibleActions;
        switch (direction) {
            case UP:
                // move glider up
                break;
            case DOWN:
                // move glider down
                break;
            case LEFT:
                // move glider left
                break;
            case RIGHT:
                // move glider right
                break;
        }
    }

    // change the next method according to the way the board is made
    public Board result(Action action) {
        Tile tile = action.getTileAction();
        Direction direction = action.getDirection();
        // GET THE INDEX OF THE WANTED TILE
        int[] idxArray = new int[2];
        int i;
        int j;
        if (direction == Direction.UP) {
            idxArray[0] = -1;
            idxArray[1] = 0;
        } else if (direction == Direction.DOWN) {
            idxArray[0] = 1;
            idxArray[1] = 0;
        } else if (direction == Direction.RIGHT) {
            idxArray[0] = 0;
            idxArray[1] = 1;
        } else if (direction == Direction.LEFT) {
            idxArray[0] = 0;
            idxArray[1] = 1;
        }
        Board.switchTiles(tile, idxArray);
        return board;
    }
}
