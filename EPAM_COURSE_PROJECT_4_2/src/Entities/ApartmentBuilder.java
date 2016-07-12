package Entities;

/**
 * implements Builder Pattern to construct an Apartment
 */
public class ApartmentBuilder {
    private Category category = Category.DELUXE;
    private double pricePerNight = 500;
    private boolean includesToilet = true;
    private int beds = 4;
    private int numberOfRooms = 2;
    private boolean windowsToStreet = true;//or to the yard
    private boolean hasConditioner = true;

    /**
     * sets the {@code   category} field
     *
     * @return instance of ApartmentBuilder
     */
    public ApartmentBuilder buildCategory(Category category) {
        this.category = category;
        return this;
    }

    /**
     * sets the {@code   pricePerNight} field
     *
     * @return instance of ApartmentBuilder
     */
    public ApartmentBuilder buildPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
        return this;
    }

    /**
     * sets the {@code  includesToilet} field
     *
     * @return instance of ApartmentBuilder
     */
    public ApartmentBuilder buildIncludesToilet(boolean includesToilet) {
        this.includesToilet = includesToilet;
        return this;
    }

    /**
     * sets the {@code  beds} field
     *
     * @return instance of ApartmentBuilder
     */
    public ApartmentBuilder buildBeds(int beds) {
        this.beds = beds;
        return this;
    }

    /**
     * sets the {@code  numberOfRooms} field
     *
     * @return instance of ApartmentBuilder
     */
    public ApartmentBuilder buildNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
        return this;
    }

    /**
     * sets the {@code  windowsToStreet} field
     *
     * @return instance of ApartmentBuilder
     */
    public ApartmentBuilder buildWindowsToStreet(boolean windowsToStreet) {
        this.windowsToStreet = windowsToStreet;
        return this;
    }

    /**
     * sets the {@code  hasConditioner} field
     *
     * @return instance of ApartmentBuilder
     */
    public ApartmentBuilder buildHasConditioner(boolean hasConditioner) {
        this.hasConditioner = hasConditioner;
        return this;
    }

    public Apartment build() {
        Apartment apartment = new Apartment();
        apartment.setCategory(category);
        apartment.setPrice(pricePerNight);
        apartment.setIncludesToilet(includesToilet);
        apartment.setBeds(beds);
        apartment.setNumberOfRooms(numberOfRooms);
        apartment.setWindowsToStreet(windowsToStreet);
        apartment.setHasConditioner(hasConditioner);
        return apartment;
    }




}
