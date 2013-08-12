package lab05Tests;

import org.junit.Before;
import org.junit.Test;
import pl.agh.jtp.lab05.Song;
import pl.agh.jtp.lab05.SongPersistentManger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class SongPersistentMangerTest {

    //Can be a mock ? (When persist mock it become a null)
    private Song song1;
    private Song song2;
    private Song song3;

    @Before
    public void setUp() {
        song1 = new Song("The Ones", "One", 999);
        song2 = new Song("The Twos", "Two", 999);
        song3 = new Song("The Threes", "Three", 999);
    }

    @Test
    public void shouldWrightParseTheSongsFromFile() {
        //given
        File songsFile = new File("songsTest.txt");

        //when
        Collection<Song> songs = null;
        try {
            songs = SongPersistentManger.getSongsCollectionFromFile(songsFile);
        } catch (IOException e) {
            fail(e.getMessage());
        }

        //then
        assertEquals("[The Dragon Lies Bleeding - HammerFall duration=263000, " +
                "The Metal Age - HammerFall duration=269000, " +
                "Hammerfall - HammerFall duration=287000, " +
                "I Believe - HammerFall duration=294000, " +
                "Child Of The Damned - HammerFall duration=223000]", songs.toString());
    }

    @Test
    public void shouldPersistCollectionWithoutExceptions() {
        //given
        Collection<Song> songs = new ArrayList<Song>(Arrays.asList(song1, song2, song3));

        //when
        try {
            SongPersistentManger.saveSongCollectionIntoFile(songs, "songs.out");
        } catch (IOException e) {
            fail(e.getMessage());
        }

        //then
        //empty
    }

    @Test
    public void shouldRetrieveCollectionWithoutExceptions() {
        //given
        String file = "songs.out";

        //when
        Collection<Song> songs = null;
        try {
            songs = SongPersistentManger.readSongCollectionFromFile(file);
        } catch (IOException e) {
            fail(e.getMessage());
        }

        //then
        assertNotNull(songs);
        assertEquals(3, songs.size());
        assertEquals("[The Ones - One duration=999, " +
                "The Twos - Two duration=999, " +
                "The Threes - Three duration=999]", songs.toString());
    }

    @Test
    public void shouldAcceptAllAllowedSeparatingChars() {
        //given
        File file = new File("songsTestWithSemicolonAndDots");

        //when
        Collection<Song> songs = null;
        try {
            songs = SongPersistentManger.getSongsCollectionFromFile(file);
        } catch (IOException e) {
            fail(e.getMessage());
        }

        //then
        assertEquals("[The Dragon Lies Bleeding - HammerFall duration=263000, " +
                "The Metal Age - HammerFall duration=269000, " +
                "Hammerfall - HammerFall duration=287000, " +
                "I Believe - HammerFall duration=294000, " +
                "Child Of The Damned - HammerFall duration=223000]", songs.toString());
    }
}
