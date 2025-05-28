package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.exception.TrackException;
import java.util.Objects;


public class Track implements Playable {
    private String title;
    private int length;

    public Track(String title, int length) throws TrackException {
        if (title == null || title.trim().isEmpty()) {
            throw new TrackException("ERROR: Track title cannot be empty");
        }
        if (length < 0) {
            throw new TrackException("ERROR: Track length cannot be negative");
        }
        this.title = title;
        this.length = length;
    }

    public String getTitle() { return title; }
    public int getLength() { return length; }

    @Override
    public void play() throws PlayerException {
        if (this.length > 0) {
            System.out.println("Playing track: " + this.getTitle());
            System.out.println("Track length: " + this.getLength() + " seconds");
        } else {
            throw new PlayerException("ERROR: Track length is non-positive!");
        }
    }

    // Override equals (Mục 15)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Track)) return false;
        Track track = (Track) o;
        return getLength() == track.getLength() &&
                Objects.equals(getTitle().toLowerCase(), track.getTitle().toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle().toLowerCase(), getLength());
    }


    @Override
    public String toString() {
        return "Track: " + title + " - Length: " + length + "s";
    }
}
