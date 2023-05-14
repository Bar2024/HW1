import java.util.Arrays;

public class Board {
    private static int m;
    private static int n;

    static Tile[][] gameBoard;

    public Board(String string){
        this.m = getRowsNum(string);
        this.n = getColsNum(string);
        this.gameBoard = new Tile[m][n];
        initializeGameBoard(string);
    }

    public Board(Board board){  // Calling in state.result()
        this.gameBoard = new Tile[m][n];
        Tile[][] board2 = board.getGameBoard();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                gameBoard[i][j] = board2[i][j];
            }
        }
    }

    getTile(int num){
        int[] helper = new arr[2];
    }

    f(Tile tile,int[] helper){
        int []p1 = getTile(tile);
        int []p2 = new arr[2];
        p2[0] = p1[0] + helper[0];
        p2[1] = p1[1] + helper[1];
        Tile temp = gameBoard[p1[0]][p1[1]];
        gameBoard[p1[0]][p1[1]] =  gameBoard[p2[0]][p2[1]];
        gameBoard[p2[0]][p2[1]] = temp;
    }


    public int[] emptyLocation(){
        int[] helper = new int[2];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(gameBoard[i][j] == null){
                    helper[0] = i;
                    helper[1] = j;
                }
            }
        }
        return helper;
    }

    public int[] findTile(Tile tile){
        int[] helper = new int[2];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(gameBoard[i][j] == tile){
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

    public Tile[][] getGameBoard() {
        return gameBoard;
    }

    /**
     *
     * @param string
     * @return
     */
    public int getRowsNum(String string){
        int rowCounter = 1;
        for (char c : string.toCharArray()) {
            if(c == '|')
                rowCounter++;
        }
        return rowCounter;
    }

    /**
     *
     * @param string
     * @return
     */
    public int getColsNum(String string){
        int colCounter = 1;
        for (char c : string.toCharArray()) {
            if(c == '|')
                break;
            else if(c == ' ')
                colCounter++;
        }
        return colCounter;
    }

    /**
     *
     * @param string
     */
    public void initializeGameBoard(String string){
        int currRowIndex = 0;
        String[] everyRows = string.split("|");

        for (String s : everyRows){
            int currColIndex = 0;
            String[] currRow = s.split(" ");

            for(String t : currRow){
                if(!t.equals("_"))
                    this.gameBoard[currRowIndex][currColIndex] = new Tile(Integer.parseInt(t));
                currColIndex++;
            }
            currRowIndex++;
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
