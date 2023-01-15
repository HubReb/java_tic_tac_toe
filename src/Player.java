import java.util.Scanner;

import static java.lang.System.*;

public class Player {
    public static final int SIGN = 1;

    public Player() {
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
}
