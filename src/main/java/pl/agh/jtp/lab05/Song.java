package pl.agh.jtp.lab05;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Class simply represent a song.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Song implements Serializable {
    public static final long serialVersionUID = - 6723543235432538901L;

    private final String title;
    private final String artist;
    private final int duration;

    public Song(String artist, String title, int duration) {
        this.artist = checkNotNull(artist, "Song must have an artist!");
        this.title = checkNotNull(title, "Song must have a title!");
        checkArgument(duration > 0, "Song duration must be positive! %s", duration);
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
