package Entities;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Reservation of the apartment
 *
 * @author Pidhurska Tetiana
 * @version 1 (created on 24.06.2016)
 */
public class Reservation {
    private int id_reservation;
    private Apartment apartment;
    private Client client;
    private LocalDate arrival;
    private LocalDate departure;
    private boolean payed;

    public Reservation() {
    }

    public Reservation(Apartment apartment, Client client, LocalDate arrival, LocalDate departure, boolean payed) {
        this.apartment = apartment;
        this.client = client;
        this.arrival = arrival;
        this.departure = departure;
        this.payed = payed;
    }

    public int getId() {
        return id_reservation;
    }

    public void setId(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    @Override
    public String toString() {
        return "\nReservation{" +
                "id_reservation=" + id_reservation +
                ", apartment=" + apartment +
                ", client=" + client +
                ", \narrival=" + arrival +
                ", departure=" + departure +
                ", payed=" + payed +
                '}';
    }
}
