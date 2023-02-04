import javax.swing.*;
import java.awt.*;

import java.text.MessageFormat;
import java.util.HashMap;

public class ScoreDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextPane textPane1;


    public ScoreDialog(HashMap<String, Integer> scores) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setMinimumSize(new Dimension(500, 200));
        int victories = scores.get("victories");
        int losses = scores.get("losses");
        int remis = scores.get("remis");
        buttonOK.addActionListener(e -> onOK());
        String score_message = MessageFormat.format(
                "Your current score is:\n\nLosses: {0}\nVictories: {1}\nRemis: {2}", losses, victories, remis
        );
        textPane1.setFont(new Font("Calibri", Font.PLAIN, 15));
        textPane1.setText(score_message);
        textPane1.setEditable(false);

    }

    private void onOK() {
        // add your code here
        dispose();
    }
}
