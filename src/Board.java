import java.util.Arrays;

public class Board {

    // If you can make method that sort the current board, so it would be easier for me to check if the state of
    // the board is the same as the final wanted state.

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }
        Board board = (Board) other;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }
}
