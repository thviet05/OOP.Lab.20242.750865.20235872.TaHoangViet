package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.InvalidMediaFieldException;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.exception.TrackException;
import java.util.ArrayList;
import java.util.List;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<>();

    // Constructor
    public CompactDisc() {
        super();
    }

    public CompactDisc(String title, String category, float cost, String director) throws InvalidMediaFieldException {
        super(title, category, cost, director);
    }

    public CompactDisc(String title, String category, String artist, String director, float cost) throws InvalidMediaFieldException {
        super(title, category, director, 0, cost);
        if (artist == null || artist.trim().isEmpty()) {
            throw new InvalidMediaFieldException("ERROR: Artist name cannot be empty");
        }
        this.artist = artist;
    }

    public CompactDisc(String title, String category, String director, int length, float cost) throws InvalidMediaFieldException {
        super(title, category, director, length, cost);
    }

    public CompactDisc(int id, String title, String category, String artist, String director, float cost) throws InvalidMediaFieldException {
        super(id, title, category, director, 0, cost);
        if (artist == null || artist.trim().isEmpty()) {
            throw new InvalidMediaFieldException("ERROR: Artist name cannot be empty");
        }
        this.artist = artist;
    }

    public CompactDisc(int id, String title, String category, String director, int length, float cost,
                       ArrayList<Track> tracks) throws InvalidMediaFieldException {
        super(id, title, category, director, 0, cost);
        if (artist == null || artist.trim().isEmpty()) {
            throw new InvalidMediaFieldException("ERROR: Artist name cannot be empty");
        }
        this.artist = artist;
        this.tracks = tracks;
    }

    public String getArtist() { return artist; }
    public ArrayList<Track> getTracks() { return tracks; }

    public void addTrack(Track track) throws TrackException {
        if (track == null) {
            throw new TrackException("ERROR: Cannot add null track");
        }

        if (tracks.contains(track)) {
            throw new TrackException("ERROR: Track '" + track.getTitle() + "' already exists on this CD");
        } else {
            tracks.add(track);
            System.out.println("Track '" + track.getTitle() + "' added to CD '" + getTitle() + "'.");
        }
    }

    public void removeTrack(Track track) throws TrackException {
        if (track == null) {
            throw new TrackException("ERROR: Cannot remove null track");
        }

        if (tracks.remove(track)) {
            System.out.println("Track '" + track.getTitle() + "' removed from CD '" + getTitle() + "'.");
        } else {
            throw new TrackException("ERROR: Track '" + track.getTitle() + "' not found on this CD");
        }
    }

    @Override
    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }

    @Override
    public void play() throws PlayerException {
        if (getLength() <= 0) {
            throw new PlayerException("ERROR: CD length is non-positive!");
        }
        System.out.println("--- Playing CD: " + getTitle() + " by " + artist + " ---");
        System.out.println("CD Director: " + getDirector());
        System.out.println("Total CD Length: " + getLength() + " seconds");
        System.out.println("Tracks:");
        if (tracks.isEmpty()) {
            System.out.println("No tracks on this CD.");
        } else {
            java.util.Iterator iter = tracks.iterator();
            Track nextTrack;
            while(iter.hasNext()) {
                nextTrack = (Track) iter.next();
                try {
                    nextTrack.play();
                    System.out.println("---");
                } catch(PlayerException e) {
                    throw e;
                }
            }
        }
        System.out.println("--- Finished playing CD: " + getTitle() + " ---");
    }

    @Override
    public String toString() {
        return "CD - ID: " + getId() + " - Title: " + getTitle() +
                " - Category: " + getCategory() + " - Artist: " + artist +
                " - Director: " + getDirector() + " - Length: " + getLength() + "s" +
                " - Cost: " + getCost() + "$" + " - Tracks: " + tracks.size();
    }
}
