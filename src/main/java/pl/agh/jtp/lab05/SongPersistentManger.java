package pl.agh.jtp.lab05;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class SongPersistentManger {

    /**
     * <pre>
     * Method assumed that file has pattern:
     * ...
     * title;artist;duration\n
     * ...
     * Throw RuntimeException when file doesn't exist, or it isn't possible to open it.
     * </pre>
     * @param path
     * @return
     */
    public static Collection<Song> getSongsCollectionFromFile(File path) {
        Collection<Song> result = new ArrayList<Song>();

        try {
            BufferedReader input = new BufferedReader(new FileReader(path));
            try {
                String songLine;
                while((songLine = input.readLine()) != null){
                    String[] songParameters = songLine.split(";");

                    String
                            title = songParameters[0],
                            artist = songParameters[1];
                    int duration = Integer.parseInt(songParameters[2]);

                    Song song = new Song(title, artist, duration);
                    result.add(song);
                }
            } finally {
                input.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("Problem with opening file");
        }

        return result;
    }

    /**
     * Method write a collection of Songs into file using Serialization.
     * Throw RuntimeException when something bad happened.
     * @param songs Collection of Songs
     * @param path String name of file which will be filled with Song Objects
     */
    public static void saveSongCollectionIntoFile(Collection<Song> songs, String path) {

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
            try {
                out.writeObject(songs);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot serialize song collection");
        }
    }

    /**
     * Reads a file and return a collection of Songs.
     * @param fileName File that contains serialized Songs.
     * @return Collection of Songs
     */
    public static Collection<Song> readSongCollectionFromFile(String fileName) {
        Collection<Song> songs;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            try {
                try {
                    songs = (Collection<Song>) in.readObject();
                } catch (ClassNotFoundException e) {
                    throw new ClassCastException("Your object in file is not a Collection of Songs!");
                }

            } finally {
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException("Can't retrieve song collection form file!");
        }

        return songs;
    }
}
