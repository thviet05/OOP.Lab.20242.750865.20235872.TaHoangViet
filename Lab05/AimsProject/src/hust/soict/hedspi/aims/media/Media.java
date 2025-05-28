package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.InvalidMediaFieldException;
import java.util.Comparator;
import java.util.Objects;


public abstract class Media implements Comparable<Media> {
    private int id;
    private String title;
    private String category;
    private float cost;
    private static int nbMedia = 0; // Để tạo ID tự động

    // Comparators (Thêm ở Mục 17)
    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();


    // Constructors
    public Media() {
        this.id = ++nbMedia;
    }

    public Media(String title) throws InvalidMediaFieldException {
        this.id = ++nbMedia;
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidMediaFieldException("ERROR: Media title cannot be empty");
        }
        this.title = title;
    }

    public Media(String title, String category, float cost) throws InvalidMediaFieldException {
        this.id = ++nbMedia; // Gán ID tự động tăng
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidMediaFieldException("ERROR: Media title cannot be empty");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new InvalidMediaFieldException("ERROR: Media category cannot be empty");
        }
        if (cost < 0) {
            throw new InvalidMediaFieldException("ERROR: Media cost cannot be negative");
        }
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public Media(int id, String title, String category, float cost) throws InvalidMediaFieldException {
        if (id <= 0) {
            throw new InvalidMediaFieldException("ERROR: Media ID must be positive");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidMediaFieldException("ERROR: Media title cannot be empty");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new InvalidMediaFieldException("ERROR: Media category cannot be empty");
        }
        if (cost < 0) {
            throw new InvalidMediaFieldException("ERROR: Media cost cannot be negative");
        }
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
        if (id > nbMedia) nbMedia = id;
    }


    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public float getCost() {
        return cost;
    }


    public void setTitle(String title) throws InvalidMediaFieldException {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidMediaFieldException("ERROR: Media title cannot be empty");
        }
        this.title = title;
    }

    public void setCategory(String category) throws InvalidMediaFieldException {
        if (category == null || category.trim().isEmpty()) {
            throw new InvalidMediaFieldException("ERROR: Media category cannot be empty");
        }
        this.category = category;
    }

    public void setCost(float cost) throws InvalidMediaFieldException {
        if (cost < 0) {
            throw new InvalidMediaFieldException("ERROR: Media cost cannot be negative");
        }
        this.cost = cost;
    }


    // Phương thức equals (Thêm ở Mục 15)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Media)) return false;
        Media media = (Media) o;
        return Float.compare(media.getCost(), getCost()) == 0 &&
               Objects.equals(getTitle().toLowerCase(), media.getTitle().toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle().toLowerCase(), getCost());
    }

    @Override
    public String toString() {
        return "Media - ID: " + id + " - Title: " + title + " - Category: " + category + " - Cost: " + cost + "$";
    }

    @Override
    public int compareTo(Media media) {
        if (media == null) {
            throw new NullPointerException("Cannot compare with null object");
        }

        // First compare by title
        int titleComparison = this.getTitle().toLowerCase().compareTo(media.getTitle().toLowerCase());
        if (titleComparison != 0) {
            return titleComparison;
        }

        // If titles are equal, compare by cost
        return Float.compare(this.getCost(), media.getCost());
    }
}
