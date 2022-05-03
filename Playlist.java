import java.util.ArrayList;
import java.io.Serializable;

public class Playlist implements Serializable {
    // use arraylist to store tracks
    private ArrayList<Track> tracks;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Playlist(String title) {
        // initialize arraylist
        tracks = new ArrayList<Track>();
        setTitle(title);
    }

    // add a track to the playlist
    public void add(Track t) {
        tracks.add(t);
    }

    // remove a track from the playlist
    public void remove(Track t) {
        tracks.remove(t);
    }

    // remove a track by index
    public void remove(int index) {
        tracks.remove(index);
    }

    // get playlist length
    public int length() {
        return tracks.size();
    }

    // sort the playlist by track title
    public void sortByTitle() {
        tracks.sort(null);
    }

    // sort the playlist by track creator
    public void sortByCreator() {
        tracks.sort(new TrackCreatorComparator());
    }

    // sort the playlist by track length
    public void sortByLength() {
        tracks.sort(new TrackLengthComparator());
    }

    // show the playlist with track numbers
    public void show() {
        System.out.println();
        for (int i = 0; i < tracks.size(); i++) {
            System.out.println("" + String.format("%02d", i + 1) + ". " + tracks.get(i));
        }
    }

    // play the playlist
    public void play() {
        System.out.println();
        for (Track t : tracks) {
            System.out.println("Now playing: " + t);
        }
    }

    // get total length
    public int getTotalLength() {
        int total = 0;
        for (Track t : tracks) {
            total += t.getLength();
        }
        return total;
    }

    @Override
    public String toString() {
        return getTitle() + ": " + getTotalLength();
    }

    // return playlist with all unlicensed songs removed
    public Playlist getLicensedPlaylist(String... unlicensedCreators) {
        Playlist p = new Playlist(getTitle());

        // iterate through all tracks
        track: for (Track t : tracks) {
            // check each creator for a match
            for (String creator : unlicensedCreators) {
                if (t.getCreator().equals(creator)) {
                    // break to track loop if we find an unlicensed song
                    break track;
                }
            }

            // add the song if it was not unlicensed
            p.add(t);
        }

        return p;
    }
}
