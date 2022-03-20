
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import space.creatures.Character;
import space.creatures.World;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;

public class CharacterTest {

    @ParameterizedTest
    @CsvSource(value = {
            "1, 16",
            "-8, 3"
    })
    public void characterInitialization(int x, int y){
        //World world = Mockito.mock(World.class);
        //Mockito.when(world.createNewCharacter(any(), any())).thenCallRealMethod();

        World worldSpy = Mockito.spy(World.class);
        Mockito.when(worldSpy.createNewCharacter(any(), any())).thenCallRealMethod();
        Character character = worldSpy.createNewCharacter(0, 0);
        assertEquals(x, character.getCurrentLocale().getCoordinateX());
        assertEquals(y, character.getCurrentLocale().getCoordinateY());
        assertNull(character.getGroup());
    }

}
