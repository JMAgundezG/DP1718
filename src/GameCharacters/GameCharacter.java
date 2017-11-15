package GameCharacters;


import GameCharacters.Movement.Movement;

public abstract class GameCharacter {

    private String name;

    private String id;

    private int position;

    private Movement movement;

    private String type;

    public GameCharacter(String name, String type, String id, int pos, Movement movement){
        this.name = name;
        this.id = id;
        this.position = pos;
        this.movement = movement;
        this.type = type;
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
