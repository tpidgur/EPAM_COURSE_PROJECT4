package MVC;

import Entities.Apartment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * @author Pidhurska Tetiana
 * @version 1 (created on 24.06.2016)
 */
public class Controller {

    Model model;
    View view;
    Scanner sc = new Scanner(System.in);

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * utility method
     */
    public void processUser() {
        System.out.println(model.isApartmentFree(inputApartmentId(),
                inputDate(View.START_DATE), inputDate(View.FINISH_DATE)));


    }


    /**
     * Text scanner which can parse int values
     *
     * @return int value
     */
    public int inputIntValueWithScanner(String message) {
        view.printMessage(message);
        while (!sc.hasNextInt()) {
            view.printMessage(view.WRONG_INPUT_INT_DATA);
            sc.next();
        }
        return sc.nextInt();
    }

    /**
     * Text scanner which can parse String values
     *
     * @return String value
     */
    public String inputString() {
        view.printMessage(view.INPUT_DATE);
        while (!sc.hasNext()) {
            view.printMessage(view.WRONG_INPUT_DATE + view.INPUT_DATE);
            sc.next();
        }
        return sc.next();
    }

    /**
     * Text scanner which can parse LocalDate in format yyyy-MM-dd
     *
     * @param message - informs about the kind of data to be entered
     * @return LocalDate
     */
    public LocalDate inputDate(String message) {
        while (true) {
            view.printMessage(view.INPUT_DATE + message);
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(sc.next(), formatter);
                return localDate;
            } catch (DateTimeParseException ex) {
                view.printMessage(view.WRONG_INPUT_DATE);
            }
        }
    }

    /**
     * parses int value of Apartment Id and checks whether such Id exists
     *
     * @return int Apartment Id
     */
    public int inputApartmentId() {
        int id;
        while (model.getApartment(id = inputIntValueWithScanner(View.INPUT_APARTMENT_ID)) == null) {
            view.printMessage(view.WRONG_APARTMENT_ID);
        }
        return id;
    }
}
