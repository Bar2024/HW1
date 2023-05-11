public class Action {
    static int tile;
    static String direction;

    public Action(int tile, String direction) {
        this.tile = tile;
        this.direction = direction;
    }


    public String toString {
        return ("Move" <tile> <direction>);
    }
}
