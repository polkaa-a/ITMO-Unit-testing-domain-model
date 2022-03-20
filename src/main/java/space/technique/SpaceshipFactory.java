package space.technique;

import space.creatures.Character;
import space.creatures.World;

public class SpaceshipFactory {

    private SpaceshipFactory() {}

    private static boolean isValidNumOfCharacters(Character... characters){
        return characters.length >= 2;
    }

    public static Spaceship createNewSpaceship(int x, int y, World world, Character... characters){
        if (!isValidNumOfCharacters(characters)) return null;

        Spaceship spaceship = new Spaceship( x, y, world );
        for ( Character character : characters) {
            if(!spaceship.addMember(character)) return null;
        }

        return spaceship;
    }



}
