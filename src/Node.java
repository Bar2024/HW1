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
            //TEST TEST TEST
            System.out.println(newNode.getAction().toString());
            System.out.println(newNode.getState().isGoal());
        }
        System.out.println();
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

        // Calculate the heuristic value based on the parent state, current state, and action
        // You can access the parent state, current state, and action using the instance variables

        // Example: Calculate the heuristic value based on the number of tiles that are different between the current state and the goal state
        Tile[][] currentTiles = state.getBoard().getGameBoard();
        Tile[][] wantedTiles = state.getBoard().getWantedBoard();

        int diffCount = 0;
        int rows = Board.getM();
        int columns = Board.getN();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!currentTiles[i][j].equals(wantedTiles[i][j])) {
                    diffCount++;
                }
            }
        }

        heuristicValue = diffCount;

        return heuristicValue;
    }

    // ...
}


