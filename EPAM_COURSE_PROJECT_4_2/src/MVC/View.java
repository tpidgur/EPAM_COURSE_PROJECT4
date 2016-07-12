package MVC;

/**
 * View generates an output to the user.
 *
 * @author Pidhurska Tetiana
 * @version 1 (created on 24.06.2016)
 */
public class View {
    // Text's constants
    public static final String INPUT_INT_DATA = "Input INT value = ";
    public static final String INPUT_DATE = "Input DATE in format 2015-10-23";
    public static final String START_DATE = " (start date)";
    public static final String FINISH_DATE = " (finish date)";
    public static final String WRONG_INPUT_DATE = "Wrong input date!  Repeat please! ";
    public static final String WRONG_INPUT_INT_DATA = "Wrong input! Repeat please! ";
    public static final String INPUT_APARTMENT_ID= "Input Apartment Id = ";
    public static final String WRONG_APARTMENT_ID= "Wrong input apartment Id! Repeat please! ";
    /**
     * prints 1 String
     *
     * @param message
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * prints 1-to many strings
     *
     * @param message
     */
    public void concatenationAndPrint(String... message) {
        StringBuilder concatString = new StringBuilder();
        for (String v : message) {
            concatString = concatString.append(v);
        }
        printMessage(new String(concatString));
    }

}
