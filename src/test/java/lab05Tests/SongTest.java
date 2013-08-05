package lab05Tests;

import org.junit.Test;
import pl.agh.jtp.lab05.Song;

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
        //empty, exception should been thrown above
    }

    @Test(expected = NullPointerException.class)
    public void shouldCantBePossibleToCreateASongWithNullArtistArgument() {
        //given
        Song song;

        //when
        song = new Song(null, "title", 999);

        //then
        //empty, exception should been thrown above
    }

}
