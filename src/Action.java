public class Action {
    private Tile tile;
    private Direction direction;

    /**
     * set the tile and the direction
     *
     * @param tile      the tile we eant to move
     * @param direction the direction we want this tile to move
     */
    public Action(Tile tile, Direction direction) {
        this.tile = tile;
        this.direction = direction;
    }

    /**
     * returns a string made from the tile and the direction
     *
     * @return the tile and the direction we want to move
     */
    public String toString() {
        return ("Move " + tile + " " + direction);
    }

    public Tile getTileAction() {
        return tile;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
