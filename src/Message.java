import javax.swing.*;
import java.awt.*;

public class Message extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextPane textPane1;


    public Message(String message) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setMinimumSize(new Dimension(500, 200));
        buttonOK.addActionListener(e -> onOK());
        textPane1.setText(message);
        textPane1.setFont(new Font("Calibri", Font.PLAIN, 15));
        textPane1.setEditable(false);
    }

    private void onOK() {
        // add your code here
        dispose();
    }
}
