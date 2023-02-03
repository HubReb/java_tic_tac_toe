import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NewGame extends JDialog {
    private JPanel contentPane;
    private JButton buttonYes;
    private JButton buttonNo;

    private boolean newGame;
    private PropertyChangeSupport support;
    public NewGame() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonYes);
        setMinimumSize(new Dimension(500, 200));
        support = new PropertyChangeSupport(this);
        buttonYes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonNo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        support.firePropertyChange("newGame", newGame, true);
        newGame = true;
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        support.firePropertyChange("newGame", true, false);     // TODO: find better method than ugly hack: need to pretend old value was new for firePropertyChange to fire
        newGame = false;
        dispose();
    }

    public static void main(String[] args) {
        NewGame dialog = new NewGame();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public  void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
