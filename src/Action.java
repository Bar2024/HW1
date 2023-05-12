public class Action {
    static int tile;
    static EnumDirection direction;

    public Action(int tile, EnumDirection direction) {
        this.tile = tile;
        this.direction = direction;
    }

    public String toString() {
        return ("Move " + tile + " " + direction);
    }
}
