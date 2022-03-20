import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import space.creatures.Character;
import space.map.Locale;
import space.technique.Spaceship;

public class WorldTest {

    static Locale localeForCharacterInSpace;
    static Locale localeForCurrentSpaceShip;
    static Locale localeForAnotherSpaceShip;

    static Character characterInSpace;

    static Spaceship currentSpaceShip;
    static Spaceship anotherSpaceShip;

    public static void setUpLocationMocks(){
        localeForCharacterInSpace = Mockito.mock(Locale.class);
        localeForCurrentSpaceShip = Mockito.mock(Locale.class);
        localeForAnotherSpaceShip = Mockito.mock(Locale.class);

        Mockito.when(characterInSpace.getCurrentLocale()).thenReturn(localeForCharacterInSpace);
        Mockito.when(currentSpaceShip.getCurrentLocale()).thenReturn(localeForCurrentSpaceShip);
        Mockito.when(anotherSpaceShip.getCurrentLocale()).thenReturn(localeForCurrentSpaceShip);
    }

    @Test
    public void addToTheWorldAfterInitialization(){

    }
}
