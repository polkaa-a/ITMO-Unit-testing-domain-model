package chemistry;

import space.map.Locale;

import java.util.ArrayList;

public interface Molecule<T extends ElementaryParticle> {

    ArrayList<T> getMembers();
    boolean addMember( T atom );

    void changeLocale(int x, int y);
    Locale getCurrentLocale();


}
