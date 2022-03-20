import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import space.creatures.Character;
import space.creatures.World;
import space.map.Locale;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorldTest {

    static Locale localeForFirstCharacter;
    static Locale localeForSecondCharacter;

    static Character firstCharacter;
    static Character secondCharacter;

    @BeforeAll
    public static void setUpMocks(){
        firstCharacter = Mockito.mock(Character.class);
        secondCharacter = Mockito.mock(Character.class);

        localeForFirstCharacter = Mockito.mock(Locale.class);
        localeForSecondCharacter = Mockito.mock(Locale.class);

        Mockito.when(firstCharacter.getCurrentLocale()).thenReturn(localeForFirstCharacter);
        Mockito.when(secondCharacter.getCurrentLocale()).thenReturn(localeForSecondCharacter);
    }

    @ParameterizedTest
    @CsvSource({
            "2, 7, 9, 4",
            "-3, 0, 5, 6"
    })
    public void isNearWhenTrue(int x1, int x2, int y1, int y2){
        World world = new World();

        setUpCoordinates(x1, x2, y1, y2);

        assertTrue(world.isNear(firstCharacter, secondCharacter));
    }

    @ParameterizedTest
    @CsvSource({
            "1, 9, 8, 0",
            "100, 3, 4, 10"
    })
    public void isNearWhenFalse(int x1, int x2, int y1, int y2){
        World world = new World();

        setUpCoordinates(x1, x2, y1, y2);

        assertFalse(world.isNear(firstCharacter, secondCharacter));
    }

    private void setUpCoordinates(int x1, int x2, int y1, int y2){
        Mockito.when(localeForFirstCharacter.getCoordinateX()).thenReturn(x1);
        Mockito.when(localeForFirstCharacter.getCoordinateY()).thenReturn(y1);
        Mockito.when(localeForSecondCharacter.getCoordinateX()).thenReturn(x2);
        Mockito.when(localeForSecondCharacter.getCoordinateY()).thenReturn(y2);
    }
}
