package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreErreur extends JFrame implements ActionListener{

    private JButton btnRetour;

    public FenetreErreur(String message) {
        setTitle("Erreur");
        setBounds(500, 500, 200, 125);
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());
        btnRetour = new JButton("Retour");

        contentPane.add(new JLabel(message));
        contentPane.add(btnRetour);

        btnRetour.addActionListener(this);

        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }
}
