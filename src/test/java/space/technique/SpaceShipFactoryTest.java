package space.technique;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import space.creatures.Character;
import space.creatures.World;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;

public class SpaceShipFactoryTest {
    private World world;
    private Character firstCharacter;
    private Character secondCharacter;

    @BeforeEach
    public void setUpMocks(){
        world = Mockito.mock(World.class);
        firstCharacter = Mockito.mock(Character.class);
        secondCharacter = Mockito.mock(Character.class);

        Mockito.when(firstCharacter.setGroup(any())).thenReturn(true);
        Mockito.when(secondCharacter.setGroup(any())).thenReturn(true);
    }

    @Test
    public void spaceShipInitializationValidInput(){
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
