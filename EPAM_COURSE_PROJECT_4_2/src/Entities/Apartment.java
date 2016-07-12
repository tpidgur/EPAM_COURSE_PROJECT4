package Entities;

/**
 * Defines the basic features of Apartment
 * @author Pidhurska Tetiana
 * @version 1 (created on 24.06.2016)
 */
public class Apartment {
    private int id;
    private Category category;
    private double price;
    private boolean hasToilet;
    private int beds;
    private int rooms;
    private boolean windowsToStreet;//or to the yard
    private boolean hasConditioner;

    public Apartment() {

    }

    public Apartment(Category category, double price, boolean hasToilet, int beds,
                     int rooms, boolean windowsToStreet, boolean hasConditioner) {
        this.category = category;
        this.price = price;
        this.hasToilet = hasToilet;
        this.beds = beds;
        this.rooms = rooms;
        this.windowsToStreet = windowsToStreet;
        this.hasConditioner = hasConditioner;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean includesToilet() {
        return hasToilet;
    }

    public void setIncludesToilet(boolean includesToilet) {
        this.hasToilet = includesToilet;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public int getNumberOfRooms() {
        return rooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.rooms = numberOfRooms;
    }

    public boolean windowsToStreet() {
        return windowsToStreet;
    }

    public void setWindowsToStreet(boolean windowsToStreet) {
        this.windowsToStreet = windowsToStreet;
    }

    public boolean hasConditioner() {
        return hasConditioner;
    }

    public void setHasConditioner(boolean hasConditioner) {
        this.hasConditioner = hasConditioner;
    }

    @Override
    public String toString() {
        return "\nApartment{" +
                "id=" + id +
                ", category=" + category +
                ", price=" + price +
                ", includesToilet=" + hasToilet +
                ", beds=" + beds +
                ", numberOfRooms=" + rooms +
                ", windowsToStreet=" + windowsToStreet +
                ", hasConditioner=" + hasConditioner +
                '}';
    }
}
