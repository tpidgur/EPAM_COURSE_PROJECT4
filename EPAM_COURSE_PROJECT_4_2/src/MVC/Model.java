package MVC;

import Entities.Apartment;
import Entities.ApartmentBuilder;
import Entities.Category;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * @author Pidhurska Tetiana
 * @version 1 (created on 24.06.2016)
 */
public class Model {
    /**
     * list of all apartments of the hotel
     */
    private static List<Apartment> apartList = new LinkedList<>();
    /**
     * a map with key=apartment;value=list of reserved days
     */
    private static Map<Apartment, List<LocalDate>> timeTable = new HashMap<>();

    static {
        generateApartments();
        fillTimeTableWithApartm();
    }


    /**
     * makes a reservation for an apartment  from startDate to finishDate
     *
     * @param apartmentId
     * @param startDate
     * @param finishDate
     * @return boolean result: true if the reservation has been added, else false
     */
    public boolean addReservation(int apartmentId, LocalDate startDate, LocalDate finishDate) {
        if (!isApartmentFree(apartmentId, startDate, finishDate)) return false;
        List<LocalDate> dates = timeTable.get(getApartment(apartmentId));
        timeTable.get(getApartment(apartmentId)).addAll(datesToList(startDate, finishDate));
        return true;
    }

    /**
     * returns collection of days between startDate and finishDate
     *
     * @param startDate  - date starting from which (inclusively) days are added to the collection
     * @param finishDate -  date till which (inclusively) the dates are added to the collection
     * @return the List<LocalDate> collection of days
     */
//    public List<LocalDate> datesToList(LocalDate startDate, LocalDate finishDate) {
//        finishDate=finishDate.plusDays(1);
//        List<LocalDate> daysRange = Stream.iterate(startDate, date -> date.plusDays(1)).
//                limit(Period.between( startDate,finishDate).getDays()).collect(Collectors.toList());
//        return daysRange;
//    }
    public List<LocalDate> datesToList(LocalDate startDate, LocalDate finishDate) {
        long daysBetween = DAYS.between(startDate, finishDate.plusDays(1));
        List<LocalDate> daysRange = Stream.iterate(startDate, date -> date.plusDays(1)).
                limit(daysBetween).collect(Collectors.toList());
        return daysRange;
    }

    /**
     * returns an apartment on its Id or null if there is no such apartment
     *
     * @param apartmentId int value of apartment's id
     * @return the Apartment object
     */
    public Apartment getApartment(int apartmentId) {
        Iterator<Apartment> it = apartList.iterator();
        while (it.hasNext()) {
            Apartment ap = it.next();
            if (ap.getId() == apartmentId) {
                return ap;
            }
        }
        return null;
    }

    /**
     * checks whether the current apartment is free starting from the start date till the finish date
     *
     * @param apartmentId - int id of the apartment
     * @param start       -starting from this date
     * @param finish      - ending on this date
     * @return true if apartment is free on the mentioned list of dates, else false
     */
    public boolean isApartmentFree(int apartmentId, LocalDate start, LocalDate finish) {
        Apartment apartment = getApartment(apartmentId);
        Iterator<Map.Entry<Apartment, List<LocalDate>>> it = timeTable.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Apartment, List<LocalDate>> pair = (Map.Entry) it.next();
            if (pair.getKey().equals(apartment)) {
                List<LocalDate> reserved = pair.getValue();
                if (!notOverlap(reserved, start, finish)) return false;
            }
        }
        return true;
    }

    /**
     * checks whether the current Apartment's Date List doesn't overlap with the client's request of reservation
     * starting from the   start fay and ending on the finish day
     *
     * @param dateList List of reservation dates of the specific apartment
     * @param start    the day to start from
     * @param finish   day to finish on
     * @return true if the range between start and finish doesn't overlap with the  dateList
     */
    public boolean notOverlap(List<LocalDate> dateList, LocalDate start, LocalDate finish) {
        if (dateList == null) {
            return true;
        } else {
            List<LocalDate> newReservation = datesToList(start, finish);
            newReservation.retainAll(dateList);//retain elements that are in both newReservation and dateList
            return (newReservation.size() == 0); // if size == 0, i.e. there are no same elements in dateList
        }
    }

    /**
     * builds the list of all apartments in the hotel
     */

    public static void generateApartments() {
        for (int i = 0; i < 5; i++) {
            apartList.add(new ApartmentBuilder().build());
            apartList.add(new ApartmentBuilder().buildCategory(Category.SEMIDELUXE)
                    .buildPricePerNight(400)
                    .buildBeds(2)
                    .buildNumberOfRooms(1)
                    .build());
            apartList.add(new ApartmentBuilder().buildCategory(Category.ECONOM)
                    .buildPricePerNight(300)
                    .buildBeds(2)
                    .buildNumberOfRooms(1)
                    .buildWindowsToStreet(false)
                    .buildHasConditioner(false)
                    .build());
            apartList.add(new ApartmentBuilder().buildCategory(Category.ECONOM)
                    .buildPricePerNight(200)
                    .buildBeds(1)
                    .buildNumberOfRooms(1)
                    .buildWindowsToStreet(false)
                    .buildHasConditioner(false)
                    .build());
        }
    }

    /**
     * fills the empty timeTable with key apartment and value=null(empty dates)
     */
    public static void fillTimeTableWithApartm() {
        Iterator<Apartment> itr = apartList.iterator();
        while (itr.hasNext()) {
            Apartment apartment = itr.next();
            timeTable.put(apartment, null);
        }
    }

    public static Map<Apartment, List<LocalDate>> getTimeTable() {
        return timeTable;
    }

    public static List<Apartment> getApartList() {
        return apartList;
    }
}
