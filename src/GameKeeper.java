import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.MessageFormat;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class GameKeeper implements PropertyChangeListener {
    int difficulty;
    int losses;
    int victories;
    int remis;
    public GameKeeper() {
        difficulty = 0;
        losses = 0;
        victories = 0;
        remis = 0;
    }
    public void game() {
        Scanner scanner = new Scanner(in);
        boolean readable_int = false;
        boolean play = true;
        while (play) {
            Difficulty difficulty_chooser = new Difficulty();
            difficulty_chooser.addPropertyChangeListener(this);
            difficulty_chooser.pack();
            difficulty_chooser.setVisible(true);
            while (difficulty == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            /*while (!readable_int) {


                out.print("Choose a difficulty: \n1) easy\n2) medium\n3) difficult\nYour choice: ");
                /*if (scanner.hasNextInt()) {
                    int read_number = scanner.nextInt();
                    if (read_number < 1 || read_number > 3) {
                        out.println("Please enter a valid number.");
                        continue;
                    }
                    this.difficulty = read_number;
                    readable_int = true;
                }
                else {
                    out.println("Please enter a valid number.");
                    scanner.nextLine();
                }
            }*/
            //readable_int = false;
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
                tic_tac_toe.close();
            }
            readable_int = false;
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        this.difficulty = (int) propertyChangeEvent.getNewValue();
    }
}
