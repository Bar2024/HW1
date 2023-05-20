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


        for(int i=1; i< n*m; i++) {

            int []helper1 = new int[2];
            int []helper2 = new int[2];

            for(int j=0; j<m; j++) {
                for(int k=0; k<n; k++) {

                    if(currentTiles[j][k] != null && currentTiles[j][k].getValue() == i){
                        helper1[0] = j;
                        helper1[1] = k;
                    }
                    if(wantedTiles[j][k] != null && wantedTiles[j][k].getValue() == i) {
                        helper2[0] = j;
                        helper2[1] = k;
                    }
                }
                heuristicValue += (helper1[0] - helper2[0] > 0) ? helper1[0] - helper2[0] : helper2[0] - helper1[0];
                heuristicValue += (helper1[1] - helper2[1] > 0) ? helper1[1] - helper2[1] : helper2[1] - helper1[1];
            }
        }
        return heuristicValue;
    }


    // ...
}


