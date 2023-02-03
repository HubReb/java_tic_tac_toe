import java.util.concurrent.ThreadLocalRandom;

public class AI {
    public static final int SIGN = 2;
    int difficulty;
    public AI(int chosen_difficulty) {
        difficulty = chosen_difficulty;
    }

    private int random_move(Board board) {
        int random_field = ThreadLocalRandom.current().nextInt(9);
        if (board.get_field(random_field) == 0) {
            board.set_field(random_field, SIGN);
            return random_field;
        }
        return -1;
    }
    public int ai_move(Board board) {
        if ((difficulty == 1) || ThreadLocalRandom.current().nextInt(0, difficulty * 3) == 1) {
            int chosen_field = random_move(board);
            while (chosen_field < 0) {
                chosen_field = random_move(board);
            }
            return chosen_field;
        }
        if (board.get_field(4) == 0) {
            board.set_field(4, SIGN);
            return 4;
        }
        else {
            int two_in_row = check_two(board, false);
            if (two_in_row > -1) {
                board.set_field(two_in_row, SIGN);
                return two_in_row;
            }
            int two_in_columns = check_two(board, true);
            if (two_in_columns > -1){
                board.set_field(two_in_columns, SIGN);
                return two_in_columns;

            }
            int two_in_diagonals = check_diagonals(board);
            if (two_in_diagonals > -1) {
                board.set_field(two_in_diagonals, SIGN);
                return two_in_diagonals;
            }
            int empty_spot = -1;
            for (int i = 0; i < 9; i++){
                int current_field = board.get_field(i);
                if (current_field == SIGN) {
                    int next_field_to_take = get_available_adjacent(board, i);
                    if (next_field_to_take > -1) {
                        board.set_field(next_field_to_take, SIGN);
                        return next_field_to_take;
                    }
                }
                else if (board.get_field(i) == 0) {
                    empty_spot = i;

                }
            }
            board.set_field(empty_spot, SIGN);
            return empty_spot;
        }
    }

    private int get_available_adjacent(Board board, int field) {
        if (field == 4) {
            int[] next_fields = {2, 0, 6, 8};
            int next_field = 0;
            do {
                next_field = ThreadLocalRandom.current().nextInt(4);
            }while (board.get_field(next_fields[next_field]) != 0);
            return next_fields[next_field];
        }
        else {
            if (field + 3 < 9 && board.get_field(field + 3) == 0) {
                return field + 3;
            }
            if ((field - 2) > 0 && board.get_field(field - 3) == 0) {
                return field - 3;
            }
            if (((field + 1) / 3) == (field / 3) && (field + 1 < 9)) {
                if (board.get_field(field + 1) == 0) {
                    return field + 1;
                }
            }
            if (((field - 1) / 3) == (field / 3) && board.get_field(field - 1) == 0) {
                return field - 1;
            }
        }
        for (int i = 0; i < 9; i++)
            if (board.get_field(i) == 0) {
                return i;
            }
        return -1;
    }
    private int check_diagonals(Board board) {
        int[][] diagonal_indices = {{2,4,6},{0,4,8}};
        for (int[] diagonalIndex : diagonal_indices) {
            int free_cell = 0;
            int diagonal_count = 0;
            for (int j : diagonalIndex) {
                if (board.get_field(j) == 0) {
                    free_cell = j;
                } else {
                    diagonal_count++;
                }
            }
            if (diagonal_count == 2) {
                return free_cell;
            }
        }
        return  -1;
    }
    private int check_two(Board board, boolean column) {
        for (int i = 0; i < Board.WIDTH; i++) {
            int count = 0;
            int free_cell_index = 0;
            for (int j=0; j < Board.LENGTH; j++) {
                int index = column ? i + 3 * j : j + 3 * i;
                if (board.get_field(index) == 0) {
                    free_cell_index = index;
                } else if (board.get_field(index) == Player.SIGN){
                    count++;
                }
            }
            if (count == 2) {
                return free_cell_index;
            }
        }
        return  -1;
    }
}
