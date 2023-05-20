/**
 * this class represent the current state od the game, it stores inside the current board
 */
public class State {
    private Board board;


    /**
     * constructor of state
     * @param board the current board of the game
     */
    public State(Board board) {
        this.board = board;
    }


    /**
     * makes two matrix of tile[][], one for the current board and the second for the wanted board.
     * checks between the two if the placement is the same
     *
     * @return true if the both boards are equals, false otherwise
     */
    // CHANGE THIS FUMCTION TO SEND THE WANTED BOARD TO BOARD CLASS TO CHECK FOR EQUALLS
    public boolean isGoal() {
        Tile[][] currentTiles = board.getGameBoard();
        Tile[][] wantedBoard = board.getWantedBoard();
        int rows = Board.getM();
        int columns = Board.getN();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (wantedBoard[i][j] == null && currentTiles[i][j] == null) {

                }else if ((currentTiles[i][j] != null && wantedBoard[i][j] == null) ||
                        (wantedBoard[i][j] != null && currentTiles[i][j] == null )) {
                    return false;
                } else if (!currentTiles[i][j].equals(wantedBoard[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * get the current state of the board
     * @return current state of the board
     */
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

    /**
     * makes an array of actions. checks for each possible action if the movement is valid.
     * after that makes a new array of possible actions without null values.
     * @return an array of possible movement in board
     */
    public Action[] actions() {
        int[] idxArray = new int[2];
        Action[] actionArray = new Action[4];
            //case UP:
        idxArray[0] = 1;
        idxArray[1] = 0;
        if (board.validMovement(idxArray) != null) {
            actionArray[0] = new Action(board.validMovement(idxArray), Direction.UP);
                }
            //case DOWN:
        idxArray[0] = -1;
        idxArray[1] = 0;
        if (board.validMovement(idxArray) != null) {
            actionArray[1] = new Action(board.validMovement(idxArray), Direction.DOWN);
                }
            //case RIGHT:
        idxArray[0] = 0;
        idxArray[1] = -1;
        if (board.validMovement(idxArray) != null) {
            actionArray[2] = new Action(board.validMovement(idxArray), Direction.RIGHT);
                }
            //case LEFT:
        idxArray[0] = 0;
        idxArray[1] = 1;
        if (board.validMovement(idxArray) != null) {
            actionArray[3] =new  Action(board.validMovement(idxArray), Direction.LEFT);
                }
        int count = 0;
        for (int i = 0; i < actionArray.length; i++) {
            if (actionArray[i] != null) {
                count += 1;
            }
        }
        Action[] validActions = new Action[count];
        int currentI = 0;
        for (int i = 0; i < actionArray.length; i++) {
            if (actionArray[i] != null) {
                validActions[currentI] = actionArray[i];
                currentI += 1;
            }
        }
        return validActions;
        }


    /**
     * get the action we want to make and modify the board accordingly.
     * modify the direction of the action to indexes and send it with the tile to board class to make the changes.
     * @param action the action we want to perform on the board
     * @return a new state with the modified board
     */
    public State result(Action action) {
        Tile tile = action.getTileAction();
        Direction direction = action.getDirection();

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
        /** create a new copy of the board and make the changes */
        Board newBoard = new Board(board);
        newBoard.swapTiles(tile, idxArray);

        action.setTile(tile);
        action.setDirection(direction);

        /** return a new state with the changes */
        return new State(newBoard);
    }
}
