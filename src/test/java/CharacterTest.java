import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import space.creatures.Character;
import space.creatures.World;
import space.technique.Spaceship;

import java.util.ArrayList;
import space.map.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doCallRealMethod;

public class CharacterTest {

    static World worldSpy;

    static Spaceship spaceship;
    static ArrayList<Character> spaceshipMembers;
    static Character spaceshipCharacter;
    static Locale spaceshipLocale;


    @BeforeAll
    public static void setUpWorldSpy(){
        worldSpy = Mockito.spy(World.class);
        doCallRealMethod().when(worldSpy).createNewCharacter(anyInt(), anyInt());
    }

    private static void setUpSpaceshipPartsWithDefaultBehavior(){
        spaceshipMembers = new ArrayList<>();
        spaceship = Mockito.mock(Spaceship.class);
        spaceshipLocale = Mockito.mock(Locale.class);

        spaceshipCharacter = worldSpy.createNewCharacter(0, 0);

        Mockito.when(spaceship.getCurrentLocale()).thenReturn(spaceshipLocale);
        Mockito.when(spaceship.getMembers()).thenReturn(spaceshipMembers);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1, 16",
            "-8, 3"
    })
    public void characterInitialization(int x, int y){
        Character character = worldSpy.createNewCharacter(x, y);
        assertEquals(x, character.getCurrentLocale().getCoordinateX());
        assertEquals(y, character.getCurrentLocale().getCoordinateY());
        assertNull(character.getGroup());
    }

    @Test
    public void isAloneNullSpaceship(){
        Character character = worldSpy.createNewCharacter(0, 0);
        assertTrue(character.isAlone());
    }

    @Test
    public void setGroupNotNullSpaceship(){
        setUpSpaceshipPartsWithDefaultBehavior();
        spaceshipMembers.add(spaceshipCharacter);

        assertTrue(spaceshipCharacter.setGroup(spaceship));
        assertFalse(spaceshipCharacter.isAlone());
        assertEquals(spaceship, spaceshipCharacter.getGroup());
    }

    @Test
    public void setGroupSpaceshipWithNoMembers(){
        setUpSpaceshipPartsWithDefaultBehavior();

        assertFalse(spaceshipCharacter.setGroup(spaceship));
        assertTrue(spaceshipCharacter.isAlone());
        assertNotEquals(spaceship, spaceshipCharacter.getGroup());
    }

    @Test
    public void isNearInvalidCharacter(){
        Character character = worldSpy.createNewCharacter(0, 0);
        Mockito.doReturn(true).when(worldSpy).isNear(any(), any());
        assertFalse(character.isNear(character));
    }

    @Test
    public void isNearValidCharacter(){
        Character currentCharacter = worldSpy.createNewCharacter(0, 0);
        Character anotherCharacter = worldSpy.createNewCharacter(5, 5);
        Mockito.doReturn(true).when(worldSpy).isNear(any(), any());

        assertTrue(currentCharacter.isNear(anotherCharacter));
    }

    @Test
    public void changeLocaleGroupNotNull(){
        setUpSpaceshipPartsWithDefaultBehavior();
        spaceshipMembers.add(spaceshipCharacter);
        spaceshipCharacter.setGroup(spaceship);

        assertFalse(spaceshipCharacter.changeLocale(0, 0));
    }

    @Test
    public void changeLocaleGroupIsNull(){
        setUpSpaceshipPartsWithDefaultBehavior();

        assertTrue(spaceshipCharacter.changeLocale(0, 0));
    }

}
