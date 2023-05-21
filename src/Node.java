/**
 * the node class. it contains the sizes of the board, the parent state , the current state and the action we made
 * to get from parent state to the current
 */
public class Node {
    private static int m;
    private static int n;

    private State state;
    private Action action;

    private Node parent;

    /**
     * the constructor for the first node, without the parent state and the action
     * @param state the current state of the game
     */
    public Node(State state) {
        this.state = state;
        this.m = state.getBoard().getM();
        this.n = state.getBoard().getN();
    }

    /**
     * the second constructor
     * @param state the current state of the board
     * @param action the last action we made
     * @param parent the state before the action
     */
    public Node(State state, Action action, Node parent) {
        this.state = state;
        this.action = action;
        this.parent = parent;
    }


    /**
     * expands the possible nodes of the game, by going on every possible action we get from the state class
     * @return array of possible nodes to expand
     */
    public Node[] expand() {
        Action[] possibleAction =  state.actions();
        int length = possibleAction.length;
        Node[] arr = new Node[length];
        for(int i=0; i<length; i++){
            // Because I am the parent of the future childs
            Action currAction = possibleAction[i];
            State currState = state.result(currAction); // State.result is what will happen if..
            Node newNode = new Node(currState, currAction, this);
            arr[i] = newNode;
        }
        return arr;
    }

    /**
     * gets the state
     * @return the current state of the game
     */
    public State getState() {
        return state;
    }

    /**
     * gets the action
     * @return the action
     */
    public Action getAction() {
        return action;
    }

    /**
     * get the parent state
     * @return the parent state
     */
    public Node getParent() {
        return parent;
    }

    /**
     * calculate the difference of each tile between the current coordination on the board and the wanted coordination
     * on the board
     * @return The sum of the differences.
     */
    public int heuristicValue() {
        int heuristicValue = 0;

        Tile[][] currentTiles = state.getBoard().getGameBoard();
        Tile[][] wantedTiles = state.getBoard().getWantedBoard();

        int rows = currentTiles.length;
        int columns = currentTiles[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Tile currentTile = currentTiles[i][j];
                if (currentTile != null) {
                    int value = currentTile.getValue();
                    int[] desiredCoords = getDesiredCoordinates(value, wantedTiles);
                    int rowDiff = desiredCoords[0] - i;
                    int colDiff = desiredCoords[1] - j;
                    if (rowDiff < 0) {
                        rowDiff = -rowDiff;
                    }
                    if (colDiff < 0) {
                        colDiff = -colDiff;
                    }
                    heuristicValue += rowDiff + colDiff;
                }
            }
        }

        return heuristicValue;
    }

    /**
     * calculate the difference between the coordination of a tile in the current board and the wanted board
     * @param value        the value of the tile to find the coordinates for.
     * @param wantedTiles  the wanted state of the board.
     * @return The wanted coordination of the tile as an array, or null otherwise.
     */
    private int[] getDesiredCoordinates(int value, Tile[][] wantedTiles) {
        int rows = wantedTiles.length;
        int cols = wantedTiles[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (wantedTiles[i][j] != null && wantedTiles[i][j].getValue() == value) {
                    return new int[]{i, j};
                }
            }
        }
        /** cannot find the coordination */
        return null;
    }


}


