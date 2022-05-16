package space.creatures;

import java.util.ArrayList;
import java.util.List;

public class World {

    private final List<Character> characters = new ArrayList<>();

    public Character createNewCharacter(int x, int y){
        Character character = new Character(x, y);
        characters.add( character );
        character.world = this;
        return character;
    }

    public List<Character> getCharacters(){
        return characters;
    }

    public boolean isNear( Character a, Character b ){
        int difX = Math.abs(a.getCurrentLocale().getCoordinateX() - b.getCurrentLocale().getCoordinateX());
        int difY = Math.abs(a.getCurrentLocale().getCoordinateY() - b.getCurrentLocale().getCoordinateY());
        return difY <= 5 && difX <= 5;
    }

}
