package space.technique;

import space.creatures.Character;
import space.creatures.World;

public class SpaceshipFactory {

    private SpaceshipFactory() {}

    public static Spaceship createNewSpaceship(int x, int y, World world, Character... characters){
        if (characters.length < 2) return null;
        Spaceship spaceship = new Spaceship( x, y, world );
        for ( Character character : characters) {
            if(!spaceship.addMember(character)) return null;
        }
        return spaceship;
    }

}
