public class State {
    static Board board;
    static Tile[][] WANTED_BOARD;
    public Direction direction;

    public State(Board board) {
        this.board = board;
        WANTED_BOARD = board.getWantedBoard();
    }


    /**
     * checks if the current state of the board is the wanted state
     *
     * @return true if the both boards are equals, false otherwise
     */
    // CHANGE THIS FUMCTION TO SEND THE WANTED BOARD TO BOARD CLASS TO CHECK FOR EQUALLS
    public boolean isGoal() {
        Tile[][] currentTiles = board.getGameBoard();
        int rows = board.getM();
        int columns = board.getN();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (currentTiles[i][j] != WANTED_BOARD[i][j]) {
                    return false;
                }
            }
        }
        return true;
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

    public Action[] makeActionArray(Action[] actions) {
        int count = 0;
        for (int i = 0; i < actions.length; i++) {
            if (actions[i] != null) {
                    count += 1;
            }
        }
        Action[] validActions = new Action[count];
        int currentI = 0;
        for (int i = 0; i < actions.length; i++) {
            if (actions[i] != null) {
                    validActions[currentI] = actions[i];
                    currentI += 1;
            }
        }
        return validActions;
    }

    public Action[] actions() {
        int[] idxArray = new int[2];
        Action[] actionArray = new Action[4];
        switch (direction) {
            case UP:
                idxArray[0] = 1;
                idxArray[1] = 0;
                if (board.validMovement(idxArray) != null) {
                    actionArray[0] = new Action(board.validMovement(idxArray), direction = Direction.UP);
                }
            case DOWN:
                idxArray[0] = -1;
                idxArray[1] = 0;
                if (board.validMovement(idxArray) != null) {
                    actionArray[1] = new Action(board.validMovement(idxArray), direction = Direction.DOWN);
                }
            case RIGHT:
                idxArray[0] = 0;
                idxArray[1] = -1;
                if (board.validMovement(idxArray) != null) {
                    actionArray[2] = new Action(board.validMovement(idxArray), direction = Direction.RIGHT);
                }
            case LEFT:
                idxArray[0] = 0;
                idxArray[1] = 1;
                if (board.validMovement(idxArray) != null) {
                    actionArray[3] =new  Action(board.validMovement(idxArray), direction = Direction.LEFT);
                }
        }

        return makeActionArray(actionArray);

    }

    // change the next method according to the way the board is made
    public State result(Action action) {
        Tile tile = action.getTileAction();
        Direction direction = action.getDirection();
        // GET THE INDEX OF THE WANTED TILE
        int[] idxArray = new int[2];
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
            idxArray[1] = -1;
        }
        board.swapTiles(tile, idxArray);
        return this;
    }
}
