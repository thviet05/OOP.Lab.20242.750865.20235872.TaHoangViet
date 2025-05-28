package hust.soict.hedspi.aims.media;

import java.util.Comparator;
import java.util.Objects;


public abstract class Media {
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

    public Media(String title) {
        this.id = ++nbMedia;
        this.title = title;
    }

    public Media(String title, String category, float cost) {
        this.id = ++nbMedia; // Gán ID tự động tăng
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public Media(int id, String title, String category, float cost) {
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


    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }


    // Phương thức equals (Thêm ở Mục 15)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Media)) return false;
        Media media = (Media) o;
        return Objects.equals(getTitle().toLowerCase(), media.getTitle().toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle().toLowerCase());
    }

    @Override
    public String toString() {
        return "Media - ID: " + id + " - Title: " + title + " - Category: " + category + " - Cost: " + cost + "$";
    }
}

