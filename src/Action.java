public class Action {
    static Tile tile;
    static EnumDirection direction;

    /**
     * set the tile and the direction
     * @param tile the tile we eant to move
     * @param direction the direction we want this tile to move
     */
    public Action(Tile tile, EnumDirection direction) {
        this.tile = tile;
        this.direction = direction;
    }

    /**
     * returns a string made from the tile and the direction
     * @return the tile and the direction we want to move
     */
    public String toString() {
        return ("Move " + tile + " " + direction);
    }

    public Tile getTileAction() {
        return tile;
    }

    public EnumDirection getDirection() {
        return direction;
    }
}
