package chemistry;

import space.map.Locale;
import java.util.List;

public interface Molecule<T extends ElementaryParticle> {

    List<T> getMembers();
    boolean addMember( T atom );

    void changeLocale(int x, int y);
    Locale getCurrentLocale();


}
