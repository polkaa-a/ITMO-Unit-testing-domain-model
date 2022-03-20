package chemistry;

import space.map.Locale;

public interface ElementaryParticle {

    boolean setGroup( Molecule<? extends ElementaryParticle> molecule);
    Molecule<? extends ElementaryParticle> getGroup();

    boolean changeLocale( int x, int y );
    Locale getCurrentLocale();

}
