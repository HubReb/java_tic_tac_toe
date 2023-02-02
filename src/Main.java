import java.text.MessageFormat;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        boolean readable_int = false;
        int difficulty = 0;
        int losses = 0;
        int victories = 0;
        int remis = 0;
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
        boolean play = true;
        while (play) {
            readable_int = false;
            Game tic_tac_toe = new Game(difficulty);
            int game_result = tic_tac_toe.game_loop();
            switch (game_result) {
                case Player.SIGN -> victories++;
                case AI.SIGN -> losses++;
                default -> remis++;
            }
            String score_message = MessageFormat.format(
                    "Your current score:\nLosses: {0}\nVictories: {1}\nRemis: {2}", losses, victories, remis
            );
            out.println(score_message);
            while (!readable_int) {
                out.print("Start a new game?\n1) Yes\n2) No\nYour choice: ");
                if (scanner.hasNextInt()) {
                    int read_number = scanner.nextInt();
                    if (read_number < 1 || read_number > 2) {
                        out.println("Please enter a valid number.");
                        continue;
                    }
                    if (read_number == 2) {
                        play = false;
                    }
                    readable_int = true;
                } else {
                    out.println("Please enter a valid number.");
                    scanner.nextLine();
                }
            }
        }
    }
}