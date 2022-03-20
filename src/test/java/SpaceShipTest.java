import chemistry.ElementaryParticle;
import chemistry.Molecule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import space.creatures.Character;
import space.creatures.World;
import space.map.Locale;
import space.technique.Spaceship;
import space.technique.SpaceshipFactory;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

public class SpaceShipTest {

    static World world;

    static Character firstCharacterForCurrenSpaceShip;
    static Character secondCharacterForCurrentSpaceShip;
    static Character characterFromSpace;
    static Character characterToAdd;

    static ArrayList<Character> characters;
    static ArrayList<Character> teamOfCurrentSpaceShip;

    static Spaceship currentSpaceshipSpy;
    static Spaceship anotherSpaceshipSpy;

    public static void setUpCharacterMocks(){
        firstCharacterForCurrenSpaceShip = Mockito.mock(Character.class);
        secondCharacterForCurrentSpaceShip = Mockito.mock(Character.class);
        characterFromSpace = Mockito.mock(Character.class);
        characterToAdd = Mockito.mock(Character.class);

        Mockito.when(firstCharacterForCurrenSpaceShip.setGroup(any())).thenReturn(true);
        Mockito.when(secondCharacterForCurrentSpaceShip.setGroup(any())).thenReturn(true);
    }

    private static void setUpWorldMocks(){
        world = Mockito.mock(World.class);

        characters = new ArrayList<>();
        characters.add(characterFromSpace);

        Mockito.when(world.getCharacters()).thenReturn(characters);
    }

    public static void setUpSpaceShipSpyWithMocks(){
        Spaceship currentSpaceShip = SpaceshipFactory.createNewSpaceship(0, 0, world, firstCharacterForCurrenSpaceShip, secondCharacterForCurrentSpaceShip);
        assert currentSpaceShip != null;
        currentSpaceshipSpy = Mockito.spy(currentSpaceShip);

        Spaceship anotherSpaceShip = SpaceshipFactory.createNewSpaceship(0, 0, world, firstCharacterForCurrenSpaceShip, secondCharacterForCurrentSpaceShip);
        assert anotherSpaceShip != null;
        anotherSpaceshipSpy = Mockito.spy(anotherSpaceShip);

        teamOfCurrentSpaceShip = new ArrayList<>();
        teamOfCurrentSpaceShip.add(firstCharacterForCurrenSpaceShip);
        teamOfCurrentSpaceShip.add(secondCharacterForCurrentSpaceShip);

    }

    @BeforeAll
    public static void setUpAllMocks(){
        setUpCharacterMocks();
        setUpWorldMocks();
        setUpSpaceShipSpyWithMocks();
    }

    @Test
    public void addMemberValidCharacter(){
        Mockito.when(characterToAdd.setGroup(any())).thenReturn(true);
        Mockito.when(currentSpaceshipSpy.getMembers()).thenReturn(teamOfCurrentSpaceShip);

        assertTrue(currentSpaceshipSpy.addMember(characterToAdd));
    }

    @Test
    public void addMemberInvalidCharacter(){
        Mockito.when(characterToAdd.setGroup(any())).thenReturn(false);
        Mockito.when(currentSpaceshipSpy.getMembers()).thenReturn(teamOfCurrentSpaceShip);

        assertFalse(currentSpaceshipSpy.addMember(characterToAdd));
    }

    @Test
    public void changeLocaleNearACharacter(){
        Mockito.clearInvocations(currentSpaceshipSpy);

        Mockito.when(characterFromSpace.getGroup()).thenReturn(null);
        //Mockito.when(firstCharacterForCurrenSpaceShip.getGroup()).thenReturn(currentSpaceshipSpy);
        //Mockito.when(firstCharacterForCurrenSpaceShip.getGroup()).thenReturn(currentSpaceshipSpy);

        Mockito.when(world.isNear(any(), any())).thenReturn(true);
        currentSpaceshipSpy.changeLocale(0, 0);

        Mockito.verify(currentSpaceshipSpy).addMember(any());
    }

    @Test
    public void changeLocaleNearAnotherSpaceShip(){
        // нужно вернуть anotherSpaceshipSpy
        // как ????
        //Mockito.when(characterFromSpace.getGroup()).thenReturn();
    }

    @Test
    public void changeLocaleNoNearObjects(){
    }

}
