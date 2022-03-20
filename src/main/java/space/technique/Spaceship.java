package space.technique;

import chemistry.Molecule;
import space.creatures.World;
import space.creatures.Character;
import space.map.Locale;

import java.util.ArrayList;

public class Spaceship implements Molecule<Character> {

    final private ArrayList<Character> team = new ArrayList<>();
    final private Locale currentLocale;
    private final World world;

    Spaceship( int x, int y, World world ) {
        currentLocale = new Locale(x, y);
        this.world = world;
    }

    @Override
    public ArrayList<Character> getMembers() {
        return team;
    }

    @Override
    public boolean addMember(Character character) {
        getMembers().add(character);
        return character.setGroup(this);
    }

    @Override
    public void changeLocale(int x, int y) {
        currentLocale.setCoordinateX( x );
        currentLocale.setCoordinateY( y );

        for ( Character character : world.getCharacters() ) {
            if ( character.getGroup() != this && world.isNear(character, this.getMembers().get(0))) {
                if (character.getGroup() == null) {
                    this.addMember(character);
                }else{
                    changeLocale( x - 6, y - 6 );
                }
            }
        }
    }

    @Override
    public Locale getCurrentLocale() {
        return currentLocale;
    }

}
