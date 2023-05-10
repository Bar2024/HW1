/**
 * This class is making a new tile, and set him a number.
 */
public class Tile {

    static int value = 0;
    public Tile(int num){
        value = num;
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