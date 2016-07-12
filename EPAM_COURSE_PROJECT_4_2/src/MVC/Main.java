package MVC;

/**
 * Entry point of the program
 *
 * @author Pidhurska Tetiana
 * @version 1 (created on 24.06.2016)
 */
public class Main {
    public static void main(String[] args) {
        // Initialization
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
        // Run
        controller.processUser();
    }
}
