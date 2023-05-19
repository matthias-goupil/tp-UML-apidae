package presentation;

import controleur.ControleurVenteAchat;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreVente extends JFrame implements ActionListener {

	private JButton btVente;
	private JTextField txtQuantite;
	private JComboBox<String> combo;

	public FenetreVente(String[] lesProduits) {
		setTitle("Vente");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btVente = new JButton("Vente");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantit? vendue"));
		contentPane.add(txtQuantite);
		contentPane.add(btVente);

		btVente.addActionListener(this);
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
			if(!ControleurVenteAchat.venteProduit(nomProduit, quantite)) {
				new FenetreErreur("Veuillez renseigner une quantité > 0 pour le produit : \""+nomProduit+ "\"");
			} else {
				new FenetreConfirmation("Vous venez de vendre "+quantite+"x le produit "+nomProduit);
				this.dispose();
			}
		} else {
			new FenetreErreur("Aucun produit n'a été sélectionné");
		}

	}

}
