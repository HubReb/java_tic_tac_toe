import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Gui extends JFrame{
    private JButton button0;
    private JButton button3;
    private JButton button1;
    private JButton button2;
    private JButton button8;
    private JButton button7;
    private JButton button5;
    private JButton button4;
    private JButton button6;
    private JPanel parent;
    private int chosenField = -1;
    private final JButton[] buttons;
    private PropertyChangeSupport support;

    private boolean userSelectedAction = false;
    public Gui() {
        super("Tic-Tac-Toe");
        setContentPane(parent);
        setMinimumSize(new Dimension(1000, 800));
        setLocationRelativeTo(parent);
        buttons = new JButton[]{button0, button1, button2, button3, button4, button5, button6, button7, button8};
        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.PLAIN, 40));
            button.setText("  ");
        }
        this.setVisible(true);
        button0.addActionListener(actionEvent -> saveChoice(0));
        button7.addActionListener(actionEvent -> saveChoice(7));
        button3.addActionListener(actionEvent -> saveChoice(3));
        button1.addActionListener(actionEvent -> saveChoice(1));
        button2.addActionListener(actionEvent -> saveChoice(2));
        button8.addActionListener(actionEvent -> saveChoice(8));
        button6.addActionListener(actionEvent -> saveChoice(6));
        button5.addActionListener(actionEvent -> saveChoice(5));
        button4.addActionListener(actionEvent -> saveChoice(4));
        support = new PropertyChangeSupport(this);

    }
    public void setPlayerSign(int field_number){
        if ((field_number >= 0) && (field_number < buttons.length)) {
            buttons[field_number].setText("X");
        }
    }
    public void setAISign(int field_number){
        if ((field_number >= 0) && (field_number < buttons.length)) {
            buttons[field_number].setText("O");

        }
    }
    public  void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    private void saveChoice(int number) {
        support.firePropertyChange("chosen_field", chosenField, number);
        chosenField = number;
    }
}
