package game;

public class Map {

    private Square[][] map;

    private int columns;
    private int rows;

    private DoorMan doorMan;

    private int dailyPlanet;

    private static Map singletonInstance = null;

    private Map(int rows, int columns, int dailyPlanet, int depth){

        this.map = new Square[rows][columns];
        this.rows = rows;
        this.columns = columns;
        this.dailyPlanet = dailyPlanet;

        int k = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                map[i][j] = new Square(k);
                k++;
            }
        }

        this.doorMan = new DoorMan(depth);

        spendWeaponsD1();
    }

    public static Map getSingleton(){
        return singletonInstance;
    }

    public static Map getSingletonInstance(int rows, int columns, int dailyPlanet, int depth){
        if(singletonInstance == null){
            singletonInstance = new Map(rows, columns, dailyPlanet, depth);
        }
        return singletonInstance;
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
     * @return a [rows][columns] game.Square matrix
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

    public DoorMan getDoorMan() {
        return doorMan;
    }

    public int getDailyPlanet() {
        return dailyPlanet;
    }

    /*
            TODO
             */
    public void spendWeaponsD1(){
        int k = 0;
        int numWeaponsSquares = 60;
        Weapon[] weaponsSquares = {new Weapon("Mjolnir",29), new Weapon("Anillo",1),
                new Weapon("Garra",27), new Weapon("Weapondura",3), new Weapon("Red",25), 
                new Weapon("Escudo",5), new Weapon("Lucille",23), new Weapon("Lawgiver",7), 
                new Weapon("GuanteInfinito",21), new Weapon("LazoVerdad",9),
                new Weapon("CadenaFuego",19), new Weapon("Capa",11),
                new Weapon("Flecha",17), new Weapon("Tridente",13),
                new Weapon("Antorcha",15), new Weapon("Baston",28),
                new Weapon("Latigo",2), new  Weapon("MazaOro",26),
                new Weapon("CampoMagnetico",4), new Weapon("Tentaculo",24),
                new Weapon("CampoEnergia",6), new Weapon("Cetro",22),
                new Weapon("RayoEnergia",8), new Weapon("Laser",20), new Weapon("Bola",10), 
                new Weapon("Espada",18),  new Weapon("Sable",12),  new Weapon("Acido",16),
                new Weapon("Gema",14), new Weapon("Nullifier",23), new Weapon("Mjolnir",1), 
                new Weapon("Anillo",29), new Weapon("Garra",3), new Weapon("Weapondura",27),
                new Weapon("Red",5),  new Weapon("Escudo",25), new Weapon("Lucille",7), 
                new Weapon("Lawgiver",23), new Weapon("GuanteInfinito",9),
                new Weapon("LazoVerdad",21), new Weapon("CadenaFuego",11),
                new Weapon("Capa",19), new Weapon("Flecha",13), new Weapon("Tridente",17), 
                new Weapon("Antorcha",28), new Weapon("Baston",15), new Weapon("Latigo",26), 
                new Weapon("MazaOro",2), new Weapon("CampoMagnetico",24),
                new Weapon("Tentaculo",4), new Weapon("CampoEnergia",22),
                new Weapon("Cetro",6), new Weapon("RayoEnergia",20), new Weapon("Laser",8), 
                new Weapon("Bola",18), new Weapon("Espada",10), new Weapon("Sable",16), 
                new Weapon("Acido",12), new Weapon("Gema",1), new Weapon("Nullifier",3)};

        int[] squares = {1, 2, 8, 14, 15, 21, 27, 35, 28, 29, 33, 34};
        for (int i = 0; i < squares.length && k < numWeaponsSquares; i++) {
            Square s = findSquare(squares[i]);
            for (int j = 0; j < 5; j++) {
                s.saveWeapon(weaponsSquares[k]);
                k++;
            }
        }
    }

    /**
     * Method to print the map info on terminal
     */
    public void show(){
        System.out.printf("(Mapa:" + Integer.toString(dailyPlanet)+")");
        doorMan.show();
    }
    public Square findSquare(int nSquare) {
        Square r = null;
        try{
            r = map[nSquare / columns][nSquare % columns];
        }catch (NullPointerException e){
            System.out.println("The room that you are searching doesn't exists");
        }
        return r;
    }

}
