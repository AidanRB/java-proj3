public class Track {
    // title, creator, length
    private String title;
    private String creator;
    private int length;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Track(String title, String creator, int length) {
        setTitle(title);
        setCreator(creator);
        setLength(length);
    }

    public String toString() {
        return getTitle() + " by " + getCreator() + ": " + getLength();
    }

    // class is equal if title, creator, and length are equal
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Track) {
            Track t = (Track) obj;
            return getTitle().equals(t.getTitle()) && getCreator().equals(t.getCreator())
                    && getLength() == t.getLength();
        }
        return false;
    }

    // compare tracks by track title
    public int compareTo(Track t) {
        return getTitle().compareTo(t.getTitle());
    }
}
