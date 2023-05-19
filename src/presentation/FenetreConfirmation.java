package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreConfirmation extends JFrame implements ActionListener {
    private JButton btnOk;

    public FenetreConfirmation(String message) {
        setTitle("Erreur");
        setBounds(500, 500, 200, 125);
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());
        btnOk = new JButton("Ok");

        contentPane.add(new JLabel(message));
        contentPane.add(btnOk);

        btnOk.addActionListener(this);

        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }
}
