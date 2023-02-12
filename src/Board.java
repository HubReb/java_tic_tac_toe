import java.util.Arrays;

enum Field {
    CROSS,
    CIRCLE,
    FREE,
    REMIS
}
public class Board {
    public static final int WIDTH = 3;
    public static final int LENGTH = 3;
    private final Field[] field_placement = new Field[9];
    public Board() {
        Arrays.fill(field_placement, Field.FREE);
    }
    public void set_field(int field_number, Field sign) throws AssertionError {
        if (9 < field_number || field_number < 0){
            throw new AssertionError();
        }
        field_placement[field_number] = sign;
    }

    public  Field get_field(int field_number) {
        if (9 < field_number || field_number < 0){
            throw new AssertionError();
        }
        return field_placement[field_number];
    }
}