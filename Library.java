import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Library {
    private ArrayList<Playlist> playlists;
    private transient String[] unlicensedCreators = { "Alec Benjamin", "Twenty One Pilots", "Half Alive" };

    // show all playlists
    public void showPlaylists() {
        System.out.println();
        for (Playlist p : playlists) {
            System.out.println(p);
        }
    }

    // add playlist if no name collision exists
    public void addPlaylist(String title) throws IllegalArgumentException {
        for (Playlist p : playlists) {
            if (p.getTitle().equals(title)) {
                // throw exception if playlist name collision
                throw new IllegalArgumentException("Playlist name already exists");
            }
        }
        playlists.add(new Playlist(title));
    }

    // get playlist by title
    public Playlist openPlaylist(String title) throws IllegalArgumentException {
        for (Playlist p : playlists) {
            if (p.getTitle().equals(title)) {
                // return playlist if found
                return p;
            }
        }
        // throw exception if playlist does not exist
        throw new IllegalArgumentException("Playlist does not exist");
    }

    // remove playlist by title
    public void removePlaylist(String title) throws IllegalArgumentException {
        for (Playlist p : playlists) {
            if (p.getTitle().equals(title)) {
                // remove playlist if found
                playlists.remove(p);
                return;
            }
        }
        // throw exception if playlist does not exist
        throw new IllegalArgumentException("Playlist does not exist");
    }

    // print unlicensed creators
    public void printUnlicensedCreators() {
        for (String s : unlicensedCreators) {
            System.out.println(s);
        }
    }

    // save library to file
    public void save(String filename) {
        try {
            // create file output stream
            ObjectOutputStream stream = new ObjectOutputStream(
                    new BufferedOutputStream(new FileOutputStream(filename)));

            System.out.print("Saving library to " + filename);

            // write each playlist to file
            for (Playlist p : playlists) {
                System.out.print(".");
                stream.writeObject(p.getLicensedPlaylist(unlicensedCreators));
            }
            System.out.println(" Done!");

            // close file output stream
            stream.close();

        } catch (IOException e) {
            System.err.println("\nError saving library to " + filename);
        }
    }

    // load library from file
    public void load(String filename) throws FileNotFoundException, IOException {
        // create file input stream
        ObjectInputStream stream = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(filename)));

        System.out.print("Loading library from " + filename);

        // read each playlist from file
        while (true) {
            System.out.print(".");

            // try to add playlist to library
            try {
                Object temp = stream.readObject();
                if (temp != null && temp instanceof Playlist) {
                    // add playlist to library if object is a playlist
                    playlists.add((Playlist) temp);
                } else {
                    System.err.println("Error: invalid playlist");
                }
            } catch (EOFException e) {
                break;
            } catch (ClassNotFoundException e) {
                System.err.println("Error: invalid playlist");
            }
        }

        System.out.println(" Done!");

        // close file input stream
        stream.close();
    }

    public Library() {
        playlists = new ArrayList<Playlist>();
    }

    public Library(String filename) {
        this();

        try {
            load(filename);
            return;
        } catch (FileNotFoundException e) {
            System.err.println("Could not find file " + filename);
        } catch (IOException e) {
            System.err.println("Error loading library from " + filename);
        }
        System.out.println("Creating new library.");
    }
}
