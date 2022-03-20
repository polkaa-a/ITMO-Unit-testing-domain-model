import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import space.creatures.Character;
import space.creatures.World;
import space.technique.Spaceship;
import space.technique.SpaceshipFactory;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class SpaceShipTest {

    static World world;
    static ArrayList<Character> charactersInWorld;

    static Character firstCharacterForCurrenSpaceShip;
    static Character secondCharacterForCurrentSpaceShip;
    static Character firstCharacterForAnotherSpaceShip;
    static Character secondCharacterForAnotherSpaceShip;
    static Character aloneCharacterFromSpace;
    static Character aloneCharacterToAdd;

    static Spaceship currentSpaceship;
    static Spaceship anotherSpaceship;

    public static void setUpCharacterMocks(){
        firstCharacterForCurrenSpaceShip = Mockito.mock(Character.class);
        secondCharacterForCurrentSpaceShip = Mockito.mock(Character.class);
        firstCharacterForAnotherSpaceShip = Mockito.mock(Character.class);
        secondCharacterForAnotherSpaceShip = Mockito.mock(Character.class);
        aloneCharacterFromSpace = Mockito.mock(Character.class);
        aloneCharacterToAdd = Mockito.mock(Character.class);
    }

    private static void setUpWorldMocks(){
        world = Mockito.mock(World.class);

        charactersInWorld = new ArrayList<>();
        charactersInWorld.add(aloneCharacterFromSpace);
        charactersInWorld.add(aloneCharacterToAdd);
        charactersInWorld.add(firstCharacterForCurrenSpaceShip);
        charactersInWorld.add(secondCharacterForCurrentSpaceShip);
        charactersInWorld.add(firstCharacterForAnotherSpaceShip);
        charactersInWorld.add(secondCharacterForAnotherSpaceShip);

        Mockito.when(world.getCharacters()).thenReturn(charactersInWorld);
    }

    public static void setUpSpaceshipsSpyWithMocks(){
        Mockito.when(firstCharacterForCurrenSpaceShip.setGroup(any())).thenReturn(true);
        Mockito.when(secondCharacterForCurrentSpaceShip.setGroup(any())).thenReturn(true);
        Mockito.when(firstCharacterForAnotherSpaceShip.setGroup(any())).thenReturn(true);
        Mockito.when(secondCharacterForAnotherSpaceShip.setGroup(any())).thenReturn(true);

        currentSpaceship = SpaceshipFactory.createNewSpaceship(0, 0, world, firstCharacterForCurrenSpaceShip, secondCharacterForCurrentSpaceShip);
        anotherSpaceship = SpaceshipFactory.createNewSpaceship(0, 0, world, firstCharacterForAnotherSpaceShip, secondCharacterForAnotherSpaceShip);
    }

    @BeforeAll
    public static void setUpAllMocks(){
        setUpCharacterMocks();
        setUpWorldMocks();
        setUpSpaceshipsSpyWithMocks();
    }

    @Test
    public void addMemberValidCharacter(){
        Mockito.when(aloneCharacterToAdd.setGroup(any())).thenReturn(true);

        assertTrue(currentSpaceship.addMember(aloneCharacterToAdd));
    }

    @Test
    public void addMemberInvalidCharacter(){
        Mockito.when(aloneCharacterToAdd.setGroup(any())).thenReturn(false);

        assertFalse(currentSpaceship.addMember(aloneCharacterToAdd));
    }

    @ParameterizedTest
    @CsvSource({
            "6, 6",
            "19, 56"
    })
    public void changeLocaleNoPossibleCrash(int x, int y){
        currentSpaceship.changeLocale(x, y);
        assertEquals(x, currentSpaceship.getCurrentLocale().getCoordinateX());
        assertEquals(y, currentSpaceship.getCurrentLocale().getCoordinateY());
    }

    @ParameterizedTest
    @CsvSource({
           "5, 5",
           "-3, -3"
    })
    public void changeLocaleWithPossibleCrash(int x, int y){
        charactersInWorld = new ArrayList<>();
        charactersInWorld.add(firstCharacterForAnotherSpaceShip);
        Mockito.when(world.getCharacters()).thenReturn(charactersInWorld);

        Mockito.doReturn(false, true, false).when(world).isNear(any(), any());
        Mockito.when(firstCharacterForAnotherSpaceShip.isAlone()).thenReturn(false);

        currentSpaceship.changeLocale(x, y);

        assertEquals(x - 15, currentSpaceship.getCurrentLocale().getCoordinateX());
        assertEquals(y - 15, currentSpaceship.getCurrentLocale().getCoordinateY());
    }

    @Test
    public void addMemberIfPossibleWhenPossible(){
        int prevSize = anotherSpaceship.getMembers().size();

        charactersInWorld = new ArrayList<>();
        charactersInWorld.add(aloneCharacterFromSpace);
        Mockito.when(world.getCharacters()).thenReturn(charactersInWorld);

        Mockito.when(world.isNear(any(), any())).thenReturn(true);
        Mockito.when(aloneCharacterFromSpace.isAlone()).thenReturn(true);

        anotherSpaceship.addMemberIfPossible();

        int newSize = anotherSpaceship.getMembers().size();

        assertEquals(1, newSize - prevSize);
    }


}
