public class Map {

    private Square[][] map;

    private int columns;
    private int rows;

    public Map(int rows, int columns){

        this.map = new Square[rows][columns];
        this.rows = rows;
        this.columns = columns;

    }

    /**
     * Getter of columns value
     * @return columns
     */
    public int getColumns() {
        return columns;
    }


    /**
     * Getter of rows value
     * @return rows
     */
    public int getRows() {
        return rows;
    }


    /**
     * Getter of square matrix
     * @return a [rows][columns] Square matrix
     */
    public Square[][] getMap() {
        return map;
    }

    /**
     * Setter of the columns value
     * @param columns number of columns
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }


    /**
     * Setter of the rows value
     * @param rows number of rows
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

}
