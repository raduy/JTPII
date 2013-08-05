package pl.agh.jtp.lab05;

import com.google.common.base.Preconditions;

import java.io.Serializable;

/**
 * Class simply represent a song.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Song implements Serializable {
    private final String title;
    private final String artist;
    private final int duration;

    public Song(String artist, String title, int duration) {
        Preconditions.checkNotNull(artist, "Song must have an artist!");
        Preconditions.checkNotNull(title, "Song must have a title!");
        this.artist = artist;
        this.title = title;
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(!(o instanceof Song)) {
            return false;
        }

        Song song = (Song) o;

        if(duration != song.duration) {
            return false;
        }
        if(!artist.equals(song.artist)) {
            return false;
        }
        if(!title.equals(song.title)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + artist.hashCode();
        result = 31 * result + duration;
        return result;
    }

    @Override
    public String toString() {
        return artist + " - " + title + " duration=" + duration;
    }
}
