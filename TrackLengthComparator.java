import java.util.Comparator;

public class TrackLengthComparator implements Comparator<Track> {
    // compare tracks by length
    public int compare(Track t1, Track t2) {
        return t1.getLength() - t2.getLength();
    }
}
