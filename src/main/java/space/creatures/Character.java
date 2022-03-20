package space.creatures;

import chemistry.*;
import space.map.Locale;
import space.technique.Spaceship;
import space.technique.SpaceshipFactory;

public class Character implements ElementaryParticle {

    private Locale currentLocale;
    private Spaceship spaceship;
    World world;

    Character( int x, int y ){
        currentLocale = new Locale( x, y );
        spaceship = null;
    }

    public Locale getCurrentLocale(){ return this.currentLocale; }
    private void setCurrentLocale(int x, int y ){
        currentLocale.setCoordinateX( x );
        currentLocale.setCoordinateY( y );
    }

    @Override
    public boolean changeLocale(int x, int y) {
        if (spaceship == null) {
            setCurrentLocale(currentLocale.getCoordinateX() + x, currentLocale.getCoordinateY() + y);
            for ( Character character : world.getCharacters()) {
                if (character != this && world.isNear(this, character) && (character.getGroup() == null)){
                            SpaceshipFactory.createNewSpaceship(this.currentLocale.getCoordinateX(), this.currentLocale.getCoordinateY(), world, this, character);
                    }
                }
            }
            return true;
        }

    @Override
    public boolean setGroup(Molecule<? extends ElementaryParticle> spaceship) {
        for ( ElementaryParticle character : spaceship.getMembers() ) {
            if ( character == this ){
                this.spaceship = (Spaceship) spaceship;
                currentLocale = spaceship.getCurrentLocale();
                return true;
            }
        }
        return false;
    }

    @Override
    public Molecule<? extends ElementaryParticle> getGroup() {
        return spaceship;
    }

}
