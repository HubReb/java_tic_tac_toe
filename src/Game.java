import java.util.Arrays;

public class Game {
    private final Board board;
    private final Player player;
    private final AI ai;
    private final Field REMIS = Field.REMIS;
    private  final Gui graphicalInterface;
    public Game(int difficulty) {
        board = new Board();
        graphicalInterface = new Gui();
        player = new Player();
        graphicalInterface.addPropertyChangeListener(player);
        ai = new AI(difficulty);
    }

    private boolean check_three(int start_index, int step_size) {
        if (board.get_field(start_index) == board.get_field(start_index + 2 * step_size)) {
            return board.get_field(start_index + step_size) == board.get_field(start_index + 2 * step_size);
        }
        return false;
    }

    private boolean determine_remis() {
        int i = 0;
        while (i < Board.LENGTH) {
            for (int j = 0; j < Board.WIDTH; j++)
                if (board.get_field(i * Board.LENGTH + j) == Field.FREE) {
                    return false;
                }
            i++;
        }
        return true;
    }
    private Field determine_winner(int chosen_field) {
        int[] row_start = {0, 3, 6};
        for (int i : row_start) {
            if (chosen_field >= i && chosen_field <= (i+3)) {
                if (check_three(i, 1)) {
                    return board.get_field(i);
                }
            }
        }
        int[] column_start = {0, 1, 2};
        for (int i : column_start) {
            int[] column_numbers = {i, i+3, i+6};
            if (Arrays.stream(column_numbers).filter(j -> j == chosen_field).count() == 1) {
                if (check_three(i, 3)) {
                    return board.get_field(i);
                }
            }
        }
        // check diagonals
        if (check_three(0, 4)) {
            return board.get_field(0);
        }
        if (check_three(2, 2)) {
            return board.get_field(2);
        }
        return Field.FREE;
    }
    public void print_board() {
        System.out.println("-------------");
        System.out.print("| ");
        for (int i = 0; i < 9; i++) {
            switch (board.get_field(i)) {
                case FREE -> System.out.print("  | ");
                case CROSS -> System.out.print("X | ");
                default -> System.out.print("O | ");
            }
            if ((i > 0) && ((i + 1) % 3 == 0)) {
                System.out.println();
                if (i < 8) {
                    System.out.println("-------------");
                    System.out.print("| ");
                }

            }
        }
        System.out.println("-------------");
    }

    public Field game_loop() {
        while (true) {
            //print_board();

            //int player_choice = player.choose_field();
            int player_choice = player.choose_field_gui();
            switch (board.get_field(player_choice)) {
                case CROSS-> {
                    Message message = new Message("You have already taken this field!");
                    message.pack();
                    message.setLocationRelativeTo(null);
                    message.setVisible(true);
                    //System.err.println("You have already taken this field!");
                    player.ressetChosenField();
                    continue;
                }
                case CIRCLE -> {
                    Message message = new Message("Your opponent has already taken this field!");
                    message.pack();
                    message.setLocationRelativeTo(null);
                    message.setVisible(true);
                    player.ressetChosenField();
                    continue;
                }
                default -> {
                    board.set_field(player_choice, Field.CROSS);
                    //System.out.println(player_choice);
                    graphicalInterface.setPlayerSign(player_choice);
                    Field winner_after_player_move = determine_winner(player_choice);
                    if (winner_after_player_move != Field.FREE) {
                        return determine_final_state(winner_after_player_move);
                    }
                }
            }
            if (determine_remis()) {
                return REMIS;
            }
            int marked_field = ai.ai_move(board);
            System.out.println(marked_field);
            graphicalInterface.setAISign(marked_field);
            if (determine_remis()) {
              //  System.out.println("Remis!");
                return REMIS;
            }
            Field winner = determine_winner(marked_field);
            if (winner != Field.FREE) {
               // print_board();
                return determine_final_state(winner);
            }
            player.ressetChosenField();
        }
    }

    private Field determine_final_state(Field winner_after_player_move) {
        //print_board();
        if (winner_after_player_move == Player.SIGN) {
            //System.out.println("You have won!");
            return Player.SIGN;
        }
        //System.out.println("You have lost!");
        return AI.SIGN;
    }

    public  void close() {
        graphicalInterface.dispose();
    }
}
