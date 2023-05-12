import java.lang.reflect.Array;

public class State {
    static Board board;
    final Board WANTED_BOARD;
    public String Action[];

    /**
     * The constructor for Board.
     *
     * @param board The current state of the board
     */
    public State(Board board) {
        this.board = board;
        WANTED_BOARD = board.getWantedBoard();
    }

    /**
     * checks if the current state of the board is the wanted state
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

    public State result(Action) {


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
}
