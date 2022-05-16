package space.technique;

import chemistry.Molecule;
import space.creatures.World;
import space.creatures.Character;
import space.map.Locale;

import java.util.ArrayList;
import java.util.List;

public class Spaceship implements Molecule<Character> {

    private final List<Character> team = new ArrayList<>();
    private final Locale currentLocale;
    private final World world;

    protected Spaceship(int x, int y, World world) {
        currentLocale = new Locale(x, y);
        this.world = world;
    }

    @Override
    public List<Character> getMembers() {
        return team;
    }

    @Override
    public boolean addMember(Character character) {
        getMembers().add(character);
        return character.setGroup(this);
    }


    @Override
    public Locale getCurrentLocale() {
        return currentLocale;
    }

    @Override
    public void changeLocale(int x, int y) {
        currentLocale.setCoordinateX(x);
        currentLocale.setCoordinateY(y);

        addMemberIfPossible();
        preventPossibleCrash();
    }

    public void addMemberIfPossible() {
        for (Character character : world.getCharacters()) {
            if (!isMember(character) && isNear(character) && character.isAlone()) {
                this.addMember(character);
            }
        }
    }

    public void preventPossibleCrash() {
        for (Character character : world.getCharacters()) {
            if (!character.isAlone() && !isMember(character) && isNear(character)) {
                changeLocale(getCurrentLocale().getCoordinateX() - 15, getCurrentLocale().getCoordinateY() - 15);
            }
        }
    }

    private boolean isNear(Character character) {
        return world.isNear(character, this.getMembers().get(0));
    }

    private boolean isMember(Character character) {
        return character.getGroup() == this;
    }

}
