import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import space.creatures.Character;
import space.creatures.World;
import space.technique.SpaceshipFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;

public class SpaceShipFactoryTest {
    static World world;
    static Character firstCharacter;
    static Character secondCharacter;

    @BeforeAll
    public static void setUpMocks(){
        world = Mockito.mock(World.class);
        firstCharacter = Mockito.mock(Character.class);
        secondCharacter = Mockito.mock(Character.class);
    }

    @Test
    public void spaceShipInitializationValidInput(){
        Mockito.when(firstCharacter.setGroup(any())).thenReturn(true);
        Mockito.when(secondCharacter.setGroup(any())).thenReturn(true);

        assertNotNull(SpaceshipFactory.createNewSpaceship(0, 0, world, firstCharacter, secondCharacter));
    }

    @Test
    public void spaceShipInitializationInvalidNumOfCharacters(){
        assertNull(SpaceshipFactory.createNewSpaceship(0, 0, world, firstCharacter));
    }

    @Test
    public void spaceShipInitializationInvalidMember(){

        Mockito.when(firstCharacter.setGroup(any())).thenReturn(false);

        assertNull(SpaceshipFactory.createNewSpaceship(0, 0, world, firstCharacter, secondCharacter));

    }
}
