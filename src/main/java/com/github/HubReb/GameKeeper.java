package com.github.HubReb;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
//import java.util.Scanner;

//import static java.lang.System.in;


public class GameKeeper implements PropertyChangeListener {
    int difficulty;
    int losses;
    int victories;
    int remis;
    boolean playDecision = false;
    boolean keepPlaying = true;
    public GameKeeper() {
        difficulty = 0;
        losses = 0;
        victories = 0;
        remis = 0;
    }
    public void game() {
        //Scanner scanner = new Scanner(in);
        //boolean readable_int = false;
        //boolean play = true;
       // while (play) {
        while (keepPlaying) {
            Difficulty difficultyChooser = new Difficulty();
            difficultyChooser.addPropertyChangeListener(this);
            difficultyChooser.pack();
            difficultyChooser.setLocationRelativeTo(null);
            difficultyChooser.setVisible(true);
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
            Field game_result = tic_tac_toe.game_loop();
            switch (game_result) {
                case CROSS-> {
                    victories++;
                    showGameOverMessage("You have won!");
                }
                case CIRCLE -> {
                    losses++;
                    showGameOverMessage("You have lost!");
                }
                default -> {
                    remis++;
                    showGameOverMessage("Remis!");
                }
            }
            HashMap<String, Integer> scoreMap = new HashMap<>();
            scoreMap.put("victories", victories);
            scoreMap.put("losses", losses);
            scoreMap.put("remis", remis);
            ScoreDialog scoreDialog = new ScoreDialog(scoreMap);
            scoreDialog.pack();
            scoreDialog.setLocationRelativeTo(null);
            scoreDialog.setVisible(true);
            //out.println(score_message);
            NewGame newGameSelector = new NewGame();
            newGameSelector.addPropertyChangeListener(this);
            newGameSelector.pack();
            newGameSelector.setLocationRelativeTo(null);
            newGameSelector.setVisible(true);
            while (!playDecision) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            playDecision = false;
            tic_tac_toe.close();
            /*while (!readable_int) {
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
             */
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        String propertyName = propertyChangeEvent.getPropertyName();
        if (propertyName.equals("difficultyLevel")) {
            this.difficulty = (int) propertyChangeEvent.getNewValue();
        }
        else if (propertyName.equals("newGame")) {
            this.playDecision = true;
            this.keepPlaying = (boolean) propertyChangeEvent.getNewValue();
        }
    }
    private void showGameOverMessage(String message) {
        Message gameOver = new Message(message);
        gameOver.pack();
        gameOver.setVisible(true);
        gameOver.setLocationRelativeTo(null);
    }
}
