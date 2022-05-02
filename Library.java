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
    ArrayList<Playlist> playlists;

    public Library() {
        playlists = new ArrayList<Playlist>();
    }

    // show all playlists
    public void showPlaylists() {
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
                stream.writeObject(p);
            }
            System.out.println(" Done!");

            // close file output stream
            stream.close();

        } catch (IOException e) {
            System.out.println("Error saving library to " + filename);
        }
    }

    // load library from file
    public void load(String filename) {
        try {
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
                        // error: invalid object
                    }
                } catch (ClassNotFoundException e) {
                    System.out.println("Invalid file contents in " + filename);
                } catch (EOFException e) {
                    break;
                }
            }

            System.out.println(" Done!");

            // close file input stream
            stream.close();

        } catch (FileNotFoundException e) {
            System.out.println("Could not find file " + filename);
        } catch (IOException e) {
            System.out.println("Error loading library from " + filename);
        } catch (ClassNotFoundException e) {
            System.out.println("Bad file contents in " + filename);
        }
    }
}
