import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Scanner;

import static java.lang.System.*;
import static java.lang.Thread.sleep;

public class Player  implements PropertyChangeListener {
    public static final int SIGN = 1;
    private int userChosenField = -1;
    public Player() {

    }

    public int choose_field_gui() {
        while (userChosenField == -1) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return userChosenField;
    }

    public void  ressetChosenField() {
        userChosenField = -1;
    }

    public int choose_field() {
        int field_number = -1;
        Scanner scanner = new Scanner(in);
        boolean readable_int = false;
        while (!readable_int) {
            out.print("Choose a field: ");
            if (scanner.hasNextInt()) {
                int read_number = scanner.nextInt();
                if (read_number < 1 || read_number > 9) {
                    out.println("Please enter a valid number.");
                    continue;
                }
                field_number = read_number;
                readable_int = true;
            }
            else {
                out.println("Please enter a valid number.");
                scanner.nextLine();
            }
        }
        return field_number - 1;
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        this.userChosenField = (int) propertyChangeEvent.getNewValue();
    }
}
