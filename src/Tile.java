/**
 * This class represents a sole tile on board, inside it has a value of the tile
 */
public class Tile {

    private int value;

    /**
     * constructor of tile class
     * @param num the value of the tile
     */
    public Tile(int num) {
        value = num;
    }

    /**
     * get the value of the tile
     * @return value of the tile
     */
    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Tile)) {
            return false;
        }
        Tile tile = (Tile) other;
        return value == tile.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}