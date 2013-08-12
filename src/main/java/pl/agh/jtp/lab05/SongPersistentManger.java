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
     * Method assumed that file has CSV pattern (allowed separating chars are: [,;.])
     * </pre>
     * @param path Path to file
     * @return  Collection of Songs created based on file.
     * @throws IOException
     */
    public static Collection<Song> getSongsCollectionFromFile(File path) throws IOException {
        Collection<Song> result = new ArrayList<>();

        try(BufferedReader input = new BufferedReader(new FileReader(path))) {
            String songLine;
            while((songLine = input.readLine()) != null){
                String[] songParameters = songLine.split("[;,.]");

                String
                    title = songParameters[0],
                    artist = songParameters[1];
                int duration = Integer.parseInt(songParameters[2].trim());

                Song song = new Song(title, artist, duration);
                result.add(song);
            }

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Can't find file of given path");
        } catch (IOException e) {
            throw new IOException("Can't read from file");
        }

        return result;
    }

    /**
     * Method write a collection of Songs into file using Serialization.
     * @param songs Collection of Songs
     * @param path String name of file which will be filled with Song Objects
     * @throws IOException
     */
    public static void saveSongCollectionIntoFile(Collection<Song> songs, String path) throws IOException {

        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
            out.writeObject(songs);
        }  catch (FileNotFoundException e) {
            throw new FileNotFoundException("Can't find the file!");
        } catch (IOException e) {
            throw new IOException("Can't write into file");
        }
    }

    /**
     * Reads a file and return a collection of Songs.
     * @param fileName File that contains serialized Songs.
     * @return Collection of Songs
     * @throws IOException
     */
    public static Collection<Song> readSongCollectionFromFile(String fileName) throws IOException {
        Collection<Song> songs;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            try {
                songs = (Collection<Song>) in.readObject();
            } catch (ClassNotFoundException e) {
                throw new ClassCastException("Your object in file is not a Collection of Songs!");
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Can't find the file");
        } catch (IOException e) {
            throw new IOException("Can't read from file");
        }

        return songs;
    }
}