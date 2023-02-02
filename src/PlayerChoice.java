public class PlayerChoice<U, V> {
    private final U player_sign;
    private final V player_field_choice;

    public PlayerChoice(U sign, V field_number){
        player_sign = sign;
        player_field_choice = field_number;
    }

    public V get_field_number() {
        return player_field_choice;
    }

    public U getPlayer_sign() {
        return player_sign;
    }
}
