package Entities;

import java.time.LocalDate;

/**
 * Subscriber fills the Registration Application in order to enumerate the requirements for the hotel
 *
 * @author Pidhurska Tetiana
 * @version 1 (created on 24.06.2016)
 */
public class Application {
    private Client client;
    private int id;
    private String desiderates;
    private int persons;
    private int children;
    private Category category;
    private LocalDate arrival;
    private LocalDate departure;

    public Application() {
    }

    public Application(Client client, String desiderates, int persons, int children, Category category,
                       LocalDate arrival, LocalDate departure) {
        this.client = client;
        this.desiderates = desiderates;
        this.persons = persons;
        this.children = children;
        this.category = category;
        this.arrival = arrival;
        this.departure = departure;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDesiderates() {
        return desiderates;
    }

    public void setDesiderates(String desiderates) {
        this.desiderates = desiderates;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getArrival() {
        return arrival;
    }

    public void setArrival(LocalDate arrival) {
        this.arrival = arrival;
    }

    public LocalDate getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDate departure) {
        this.departure = departure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "\nApplication{" +
                "id=" + id +
                ", client ID=" + client.getId() +
                ", desiderates='" + desiderates + '\'' +
                ", persons=" + persons +
                ", children=" + children +
                ", category=" + category +
                ", arrival=" + arrival +
                ", departure=" + departure +
                '}';
    }
}
