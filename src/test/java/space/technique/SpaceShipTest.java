package space.technique;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import space.creatures.Character;
import space.creatures.World;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class SpaceShipTest {

    private World world;
    private List<Character> charactersInWorld;

    private Character firstCharacterForCurrenSpaceShip;
    private Character secondCharacterForCurrentSpaceShip;
    private Character firstCharacterForAnotherSpaceShip;
    private Character secondCharacterForAnotherSpaceShip;
    private Character aloneCharacterFromSpace;
    private Character aloneCharacterToAdd;

    private Spaceship currentSpaceship;
    private Spaceship anotherSpaceship;

    private void setUpCharacterMocks(){
        firstCharacterForCurrenSpaceShip = Mockito.mock(Character.class);
        secondCharacterForCurrentSpaceShip = Mockito.mock(Character.class);
        firstCharacterForAnotherSpaceShip = Mockito.mock(Character.class);
        secondCharacterForAnotherSpaceShip = Mockito.mock(Character.class);
        aloneCharacterFromSpace = Mockito.mock(Character.class);
        aloneCharacterToAdd = Mockito.mock(Character.class);
    }

    private void setUpWorldMocks(){
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

    private void setUpSpaceshipsSpyWithMocks(){
        Mockito.when(firstCharacterForCurrenSpaceShip.setGroup(any())).thenReturn(true);
        Mockito.when(secondCharacterForCurrentSpaceShip.setGroup(any())).thenReturn(true);
        Mockito.when(firstCharacterForAnotherSpaceShip.setGroup(any())).thenReturn(true);
        Mockito.when(secondCharacterForAnotherSpaceShip.setGroup(any())).thenReturn(true);

        currentSpaceship = SpaceshipFactory.createNewSpaceship(0, 0, world, firstCharacterForCurrenSpaceShip, secondCharacterForCurrentSpaceShip);
        anotherSpaceship = SpaceshipFactory.createNewSpaceship(0, 0, world, firstCharacterForAnotherSpaceShip, secondCharacterForAnotherSpaceShip);
    }

    @BeforeEach
    public void setUpAllMocks(){
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
