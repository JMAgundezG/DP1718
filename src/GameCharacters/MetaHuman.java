package GameCharacters;


public abstract class MetaHuman {

    private String name;

    private String id;

    private int position;


    public MetaHuman(String name, String id, int pos){
        this.name = name;
        this.id = id;
        this.position = pos;
    }

    public abstract void useWeapon();

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return name + ": " +  position+ ": ";
    }
}
