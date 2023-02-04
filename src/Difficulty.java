import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Difficulty extends JDialog {
    private JPanel contentPane;
    private JButton buttonEasy;
    private JButton buttonChallenge;
    private JTextPane pleaseChooseADifficutlyTextArea;
    private JButton buttonHard;

    private int difficultyLevel;
    private PropertyChangeSupport support;

    public Difficulty() {
        setContentPane(contentPane);
        setModal(true);
        setMinimumSize(new Dimension(500, 200));
        getRootPane().setDefaultButton(buttonEasy);
        support = new PropertyChangeSupport(this);
        buttonEasy.addActionListener(e -> onEasy());
        pleaseChooseADifficutlyTextArea.setFont(new Font("Calibri", Font.PLAIN, 15));
        pleaseChooseADifficutlyTextArea.setText("Please choose a difficulty level:");
        pleaseChooseADifficutlyTextArea.setEditable(false);
        buttonChallenge.addActionListener(e -> onMedium());

        buttonHard .addActionListener(e -> onHard());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onEasy() {
        // add your code here
        support.firePropertyChange("difficultyLevel", difficultyLevel, 1);
        difficultyLevel = 1;
        dispose();
    }

    private void onMedium() {
        // add your code here
        support.firePropertyChange("difficultyLevel", difficultyLevel, 2);
        difficultyLevel = 2;
        dispose();
    }

    private void onHard() {
        // add your code here
        support.firePropertyChange("difficultyLevel", difficultyLevel, 3);
        difficultyLevel = 3;
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
    public  void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
