import java.util.Arrays;

/**
 * the board class, has the matrix of tiles
 */
public class Board {
    private static int m;
    private static int n;

    private Tile[][] tiles;

    /**
     * the first constructor of Board class
     * @param string the string we get and make a board out of it
     */
    public Board(String string) {
        this.m = getRowsNum(string);
        this.n = getColumnsNum(string);
        this.tiles = new Tile[m][n];
        initializeGameBoard(string);
    }

    /**
     * the second constructor, duplicate the previous board
     * @param board the board we want to duplicate to a new one
     */
    public Board(Board board) {
        this.tiles  = new Tile[m][n];
        Tile[][] board2 = board.getGameBoard();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                tiles[i][j] = board2[i][j];
            }
        }
    }

    /**
     * move a tile on a board
     * @param tile the tile we want to move
     * @param helper the direction of the move
     */
    public void swapTiles(Tile tile, int[] helper) {
        int []p1 = findTile(tile);
        int []p2 = new int[2];
        p2[0] = p1[0] + helper[0];
        p2[1] = p1[1] + helper[1];
        Tile temp = tiles [p1[0]][p1[1]];
        tiles [p1[0]][p1[1]] =  tiles[p2[0]][p2[1]];
        tiles [p2[0]][p2[1]] = temp;
    }

    /**
     * makes a desired board according to m and n
     * @return the wanted board
     */
    public Tile[][] getWantedBoard() {
        Tile[][] wantedTiles = new Tile[m][n];
        int count = 1;
        for(int i=0 ; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(count == n*m)
                    break;

                wantedTiles[i][j] = new Tile(count);
                count++;
            }
        }
        return wantedTiles;
    }

    /**
     * by given coordination checks if there is tile near the null tile (tile we can move)
     * @param coordination the direction of the movement according to the place of the null tile
     * @return true if there's a tile we can move returns true, false otherwise
     */
    public Tile validMovement(int []coordination) {

        int[] nullCoordination = emptyLocation();
        int[] sumOfCoords = new int[2];
        sumOfCoords[0] = coordination[0] + nullCoordination[0];
        sumOfCoords[1] = coordination[1] + nullCoordination[1];

        if (!(sumOfCoords[0] >= 0 && sumOfCoords[0] < m && sumOfCoords[1] >= 0 && sumOfCoords[1] < n))
            return null;

        Tile wantedTile = tiles[sumOfCoords[0]][sumOfCoords[1]];
        if (wantedTile != null) {
            return findTileNumber(wantedTile.getValue());
        }
        return null;
    }


    /**
     * finds the tile's by the the given value of the tile
     * @param num the value of the wanted tile
     * @return the tile itself
     */
    public Tile findTileNumber(int num) {
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(tiles[i][j] != null && tiles[i][j].getValue() == num)
                    return tiles[i][j];
            }
        }
        return null;
    }


    /**
     * finds the null tile
     * @return the array of null's tile coordination
     */
    public int[] emptyLocation() {
        int[] helper = new int[2];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(tiles[i][j] == null){
                    helper[0] = i;
                    helper[1] = j;
                }
            }
        }
        return helper;
    }


    /**
     * by given tile returns it coordination
     * @param tile the tile we want its coordination
     * @return array of the coordination
     */
    public int[] findTile(Tile tile) {
        int[] helper = new int[2];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(tiles[i][j] == tile){
                    helper[0] = i;
                    helper[1] = j;
                }
            }
        }
        return helper;
    }


    /**
     * get the numbers of rows
     * @return m- number of rows of the board
     */
    public static int getM() {
        return m;
    }

    /**
     * get the numbers of columns
     * @return n- number of columns of the board
     */
    public static int getN() {
        return n;
    }

    /**
     * return the board
     * @return the current state of the board
     */
    public Tile[][] getGameBoard() {
        return tiles; }


    /**
     * gets the number or rows by splitting the string of the board
     * @param string the string of the board
     * @return number of rows
     */
    public int getRowsNum (String string) {
        if (!string.contains("|"))
            return 1;

        String[] everyRows = string.split("\\|");
        return everyRows.length;
    }


    /**
     * gets the number or columns by splitting the string of the board
     * @param string the string of the board
     * @return the number of columns
     */
    public int getColumnsNum (String string){
        if (!string.contains("|")) {
            String[] theOnlyRow = string.split(" ");
            return theOnlyRow.length;
        }

        String[] everyRows = string.split("\\|");
        String[] firstRow = everyRows[0].split(" ");
        return firstRow.length;
    }

    /**
     * makes the matrix of the game board
     * @param string the string of the board
     */
    public void initializeGameBoard (String string) {
        // Case There is only 1 row
        if (!string.contains("|")) {
            int curr = 0;
            String[] theOnlyRow = string.split(" ");
            for (String s : theOnlyRow) {
                if (!s.equals("_")) {
                    this.tiles[0][curr] = new Tile(Integer.parseInt(s));
                }
                curr++;
            }
        }

        /** Case There is few rows */
        else {
            int currRowIndex = 0;
            String[] everyRows = string.split("\\|");
            for (String s : everyRows) {
                int currColIndex = 0;
                String[] currRow = s.split(" ");

                for (String t : currRow) {
                    if (!t.equals("_")) {
                        this.tiles[currRowIndex][currColIndex] = new Tile(Integer.parseInt(t));
                    }
                    currColIndex++;
                }
                currRowIndex++;
            }
        }
    }


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
