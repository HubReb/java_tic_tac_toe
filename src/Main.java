import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        boolean readable_int = false;
        int difficulty = 0;
        while (!readable_int) {
            out.print("Choose a difficulty: \n1) easy\n2) medium\n3) difficult\nYour choice: ");
            if (scanner.hasNextInt()) {
                int read_number = scanner.nextInt();
                if (read_number < 1 || read_number > 3) {
                    out.println("Please enter a valid number.");
                    continue;
                }
                difficulty = read_number;
                readable_int = true;
            }
            else {
                out.println("Please enter a valid number.");
                scanner.nextLine();
            }
        }
        Game tic_tac_toe = new Game(difficulty);
        tic_tac_toe.game_loop();
    }
}