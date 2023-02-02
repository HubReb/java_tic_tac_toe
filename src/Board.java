import java.util.Arrays;

public class Board {
    public static final int WIDTH = 3;
    public static final int LENGTH = 3;
    private final int[] field_placement = new int[9];
    public Board() {
        Arrays.fill(field_placement, 0);
    }
    public void set_field(int field_number, int player_number) throws AssertionError {
        if (9 < field_number || field_number < 0){
            throw new AssertionError();
        }
        field_placement[field_number] = player_number;
    }

    public  int get_field(int field_number) {
        if (9 < field_number || field_number < 0){
            throw new AssertionError();
        }
        return field_placement[field_number];
    }
}