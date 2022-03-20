import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import space.map.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocaleTest {

    @ParameterizedTest
    @CsvSource(value = {
            "0, 108",
            "-80, 6"
    })
    public void localeInitialization(int x, int y){
        Locale locale = new Locale(x, y);
        assertEquals(x, locale.getCoordinateX());
        assertEquals(y, locale.getCoordinateY());
    }
}
