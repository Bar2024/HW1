/**
 * this class represents the action of the game, inside there are the tile and
 * the direction we want the tile to be moved
 */
public class Action {
    private Tile tile;
    private Direction direction;

    /**
     *  constructor of the class
     * @param tile      the tile we want to move
     * @param direction the direction we want this tile to be moved
     */
    public Action(Tile tile, Direction direction) {
        this.tile = tile;
        this.direction = direction;
    }

    /**
     * makes a string of the tile and the action
     * @return the tile and the direction of the action
     */
    public String toString() {
        return ("Move " + tile.getValue() + " " + direction);
    }

    /**
     *
     * @return the direction of the tile of the action
     */
    public Tile getTileAction() {
        return tile;
    }

    /**
     *
     * @return the direction of the action
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * set the tile of the action
     * @param tile the tile we want to be moved
     */
    public void setTile(Tile tile) {
        this.tile = tile;
    }

    /**
     * set the direction of the action
     * @param direction the direction of the movement
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
