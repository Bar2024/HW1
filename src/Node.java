import javax.swing.*;

public class Node {
    private static int m;
    private static int n;

    private State state;
    private Action action;

    private Node parent;

    public Node(State state){ // This constructor is for the root only
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


    public Node[] expand(){
        Action[] possibleAction =  state.actions();
        int length = possibleAction.length;
        Node[] arr = new Node[length];
        for(int i=0; i<length; i++){
            Node currParent = this; // Because I am the parent of the future childs
            Action currAction = possibleAction[i];
            State currState = state.result(currAction); // State.result is what will happen if..
            Node newNode = new Node(currState, currAction, currParent);
            arr[i] = newNode;
        }
        return arr;
    }



}
