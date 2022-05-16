package space.creatures;

import chemistry.*;
import space.map.Locale;
import space.technique.Spaceship;
import space.technique.SpaceshipFactory;

public class Character implements ElementaryParticle {

    private Locale currentLocale;
    private Spaceship spaceship;
    protected World world;

    protected Character( int x, int y ){
        currentLocale = new Locale( x, y );
        spaceship = null;
    }

    public Locale getCurrentLocale(){ return this.currentLocale; }
    public World getWorld(){ return world; }
    public boolean isAlone(){
        return spaceship == null;
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

    @Override
    public boolean changeLocale(int x, int y) {
        if (getGroup() == null) {
            currentLocale.setCoordinateX( x );
            currentLocale.setCoordinateY( y );
            createSpaceshipIfPossible();
            return true;
        }
        return false;
    }

    public void createSpaceshipIfPossible(){
        for ( Character character : getWorld().getCharacters()) {
            if (isNear(character) && isAlone()){
                SpaceshipFactory.createNewSpaceship(getCurrentLocale().getCoordinateX(), getCurrentLocale().getCoordinateY(), getWorld(), this, character);
            }
        }
    }

    public boolean isNear(Character character){
        boolean isNear = getWorld().isNear(this, character);
        return character != this && isNear;
    }

}
