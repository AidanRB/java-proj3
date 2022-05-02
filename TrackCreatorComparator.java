import java.util.Comparator;

public class TrackCreatorComparator implements Comparator<Track> {
    // compare tracks by creator name
    public int compare(Track t1, Track t2) {
        return t1.getCreator().compareTo(t2.getCreator());
    }
}
