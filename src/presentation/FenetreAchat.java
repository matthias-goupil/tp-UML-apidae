package presentation;

import controleur.ControleurVenteAchat;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreAchat extends JFrame implements ActionListener {

	private JButton btAchat;
	private JTextField txtQuantite;
	private JComboBox<String> combo;

	public FenetreAchat(String[] lesProduits) {

		setTitle("Achat");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btAchat = new JButton("Achat");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantit? achet?e"));
		contentPane.add(txtQuantite);
		contentPane.add(btAchat);

		btAchat.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String nomProduit = null;
		int quantite = -1;
		try {
			nomProduit = (String) combo.getSelectedItem();
			quantite = Integer.parseInt(txtQuantite.getText());
		} catch(Exception exception){}

		if(nomProduit != null){
			if(!ControleurVenteAchat.achatProduit(nomProduit, quantite)) {
				new FenetreErreur("Veuillez renseigner une quantité > 0 pour le produit : \""+nomProduit+ "\"");
			} else {
				new FenetreConfirmation("Vous venez d'acheter "+quantite+"x le produit "+nomProduit);
				this.dispose();
			}
		} else {
			new FenetreErreur("Aucun produit n'a été sélectionné");
		}

	}
}
