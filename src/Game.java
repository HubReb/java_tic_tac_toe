import java.awt.desktop.SystemEventListener;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Game {
    public Board board;
    public Player player;
    private final AI ai;

    public Game(int difficulty) {
        this.board = new Board();
        this.player = new Player();
        this.ai = new AI(difficulty);
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
                if (board.get_field(i * Board.LENGTH + j) == 0) {
                    return false;
                }
            i++;
        }
        return true;
    }
    private int determine_winner(int chosen_field) {
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
        return -1;
    }
    public void print_board() {
        System.out.println("-------------");
        System.out.print("| ");
        for (int i = 0; i < 9; i++) {
            switch (board.get_field(i)) {
                case 0 -> System.out.print("  | ");
                case Player.SIGN -> System.out.print("X | ");
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

    public int game_loop() {
        while (true) {
            print_board();

            int player_choice = player.choose_field();
            switch (board.get_field(player_choice)) {
                case Player.SIGN -> {
                    System.err.println("You have already taken this field!");
                    continue;
                }
                case AI.SIGN -> {
                    System.err.println("Your opponent has already taken this field!");
                    continue;
                }
                default -> {
                    board.set_field(player_choice, Player.SIGN);
                    int winner_after_player_move = determine_winner(player_choice);
                    if (winner_after_player_move > 0) {
                        return determine_final_state(winner_after_player_move);
                    }
                }
            }
            if (determine_remis()) {
                System.out.println("Remis!");
                return 3;
            }
            int marked_field = ai.ai_move(board);
            if (determine_remis()) {
                System.out.println("Remis!");
                return 3;
            }
            int winner = determine_winner(marked_field);
            if (winner > 0) {
                print_board();
                return determine_final_state(winner);
            }
        }
    }

    private int determine_final_state(int winner_after_player_move) {
        print_board();
        if (winner_after_player_move == Player.SIGN) {
            System.out.println("You have won!");
            return 1;
        }
        System.out.println("You have lost!");
        return 2;
    }
}
