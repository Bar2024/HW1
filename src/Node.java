public class Node {
    private static int m;
    private static int n;

    private State state;
    private Action action;

    private Node parent;

    public Node(State state) { // This constructor is for the root only
                                // root parent = null
                                // root action = null
        this.state = state;
        this.m = state.getBoard().getM();
        this.n = state.getBoard().getN();
    }

    public Node(State state, Action action, Node parent) {
        this.state = state;
        this.action = action;
        this.parent = parent;
    }


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
    public State getState() {
        return state;
    }
    public Action getAction() {
        return action;
    }
    public Node getParent() {
        return parent;
    }

    // HEURISTICVALUE TO CHANGE
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
                    int rowDiff = Math.abs(desiredCoords[0] - i);
                    int colDiff = Math.abs(desiredCoords[1] - j);
                    heuristicValue += rowDiff + colDiff;
                }
            }
        }

        return heuristicValue;
    }

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
        return null; // Desired coordinates not found for the given value
    }

}


