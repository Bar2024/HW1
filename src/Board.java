import java.util.Arrays;

public class Board {
    private static int m;
    private static int n;

    static Tile[][] tiles;

    public Board(String string) {
        this.m = getRowsNum(string);
        this.n = getColsNum(string);
        this.tiles = new Tile[m][n];
        initializeGameBoard(string);
    }

    public Board(Board board) {  // Calling in state.result()
        this.tiles  = new Tile[m][n];
        Tile[][] board2 = board.getGameBoard();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                tiles [i][j] = board2[i][j];
            }
        }
    }

    public void swapTiles(Tile tile,int[] helper) {
        int []p1 = findTile(tile);
        int []p2 = new int[2];
        p2[0] = p1[0] + helper[0];
        p2[1] = p1[1] + helper[1];
        Tile temp = tiles [p1[0]][p1[1]];
        tiles [p1[0]][p1[1]] =  tiles[p2[0]][p2[1]];
        tiles [p2[0]][p2[1]] = temp;
    }


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

    public Tile validMovement(int []coordination) {

        int[] nullCoordination = emptyLocation();
        int[] sumOfCoords = new int[2];
        sumOfCoords[0] = coordination[0] + nullCoordination[0];
        sumOfCoords[1] = coordination[1] + nullCoordination[1];

        if (!(sumOfCoords[0] >= 0 && sumOfCoords[0] < m && sumOfCoords[1] >= 0 && sumOfCoords[1] < n))
            return null;

        int value = tiles[sumOfCoords[0]][sumOfCoords[1]].getValue();
        return findTileNumber1(value);
    }

    // MAYBE CHANGE TO RETURN OBJECT? SO NO PROBLEMS WOULD ARISE?
    public Tile findTileNumber1(int num) {
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(tiles[i][j].getValue() == num)
                    return tiles[i][j];
            }
        }
        return null;
    }


    // פונקציה היא מקבלת מערך של קורדניטה
    // שמייצגת כיוון הפוך
    // הפונקציה emptyLocation
    // בדיקה בסכום בודק אם זה נמצא בתוך
    // אם כן אני אחזיר את הטייל במקום הזה
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


    // תבנה פונקציה שמקבלת
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


    public static int getM() {
        return m;
    }

    public static int getN() {
        return n;
    }

    public Tile[][] getGameBoard() { return tiles; }

    /**
     *
     * @param string
     * @return
     */
    public int getRowsNum (String string){
        int flag = 0;
        char []helper = string.toCharArray();
        for (int i=0; i<helper.length; i++){
            if(helper[i] == '|')
                flag = 1;
        }
        if(flag == 0)
            return 1;

        String[] everyRows = string.split("|");
        return everyRows.length;
    }

    /**
     *
     * @param string
     * @return
     */
    public int getColsNum (String string){
        int flag = 0;
        char []helper = string.toCharArray();
        for (int i=0; i<helper.length; i++){
            if(helper[i] == '|')
                flag = 1;
        }
        if(flag == 1) {
            String[] everyRows = string.split("|");
            return (everyRows[0].length() / 2) + 1;
        }
        return helper.length/2 +1;
    }

    /**
     *
     * @param string
     */
    public void initializeGameBoard (String string) {
        int currRowIndex = 0;
        int rows = getM();
        if (rows != 1) {

            String[] everyRows = string.split("|");

            for (String s : everyRows) {
                int currColIndex = 0;
                String[] currRow = s.split(" ");

                for (String t : currRow) {
                    if (!t.equals("_"))
                        this.tiles[currRowIndex][currColIndex] = new Tile(Integer.parseInt(t));
                    currColIndex++;
                }
                currRowIndex++;
            }
        } else {
            int curr = 0;
            String[] currRow = string.split(" ");
            for (String t : currRow) {
                if (!t.equals("_")) {
                    this.tiles[0][curr] = new Tile(Integer.parseInt(t));
                }
                curr++;
            }
        }
    }

    public void swapTile(Tile move, Direction directionToMove){
        int[] pointToMove2 = emptyLocation();
    }

    // If you can make method that sort the current board, so it would be easier for me to check if the state of
    // the board is the same as the final wanted state.

    // could you add a method that finds a tile by its index?

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
