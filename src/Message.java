import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField textField1;

    public GameOver(String message) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setMinimumSize(new Dimension(500, 200));
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        textField1.setText(message);
    }

    private void onOK() {
        // add your code here
        dispose();
    }
}
