package lab05Tests;

import org.junit.Test;
import pl.agh.jtp.lab05.Song;

import static org.junit.Assert.fail;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class SongTest {

    @Test(expected = NullPointerException.class)
    public void shouldCantBePossibleToCreateASongWithNullTitleArgument() {
         //given
        Song song;

        //when
        song = new Song("artist", null, 999);

        //then
        fail("NullPointerException should been thrown above");
    }

    @Test(expected = NullPointerException.class)
    public void shouldCantBePossibleToCreateASongWithNullArtistArgument() {
        //given
        Song song;

        //when
        song = new Song(null, "title", 999);

        //then
        fail("NullPointerException should been thrown above");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldCantBePossibleToCreateASongWithNegativeDuration() {
        //given
        Song song;

        //when
        song = new Song("artist", "title", -10);

        //then
        fail("IllegalArgumentException should been thrown above");
    }
}
