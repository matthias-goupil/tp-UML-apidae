package presentation;

import controleur.ControleurNouveauSuppression;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreNouveauProduit extends JFrame implements ActionListener {

	private JTextField txtPrixHT;
	private JTextField txtNom;
	private JTextField txtQte;
//	private JComboBox<String> combo;
	private JButton btValider;

//	public FenetreNouveauProduit(String[] lesCategories) {
	public FenetreNouveauProduit() {	

		setTitle("Creation Produit");
		setBounds(500, 500, 200, 250);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());

		JLabel labNom = new JLabel("Nom produit");
		JLabel labPrixHT = new JLabel("Prix Hors Taxe");
		JLabel labQte = new JLabel("Quantit? en stock");
//		JLabel labCategorie = new JLabel("Categorie");
		contentPane.add(labNom);
		txtNom = new JTextField(15);
		contentPane.add(txtNom);
		contentPane.add(labPrixHT);
		txtPrixHT = new JTextField(15);
		contentPane.add(txtPrixHT);
		contentPane.add(labQte);
		txtQte = new JTextField(15);
		contentPane.add(txtQte);

//		combo = new JComboBox<String>(lesCategories);
//		combo.setPreferredSize(new Dimension(100, 20));
//		contentPane.add(labCategorie);
//		contentPane.add(combo);

		
		btValider = new JButton("Valider");
		contentPane.add(btValider);

		btValider.addActionListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String nom = null;
		Double prix = null;
		int qte = -1;
		try {
			nom = txtNom.getText();
			prix = Double.parseDouble(txtPrixHT.getText());
			qte = Integer.parseInt(txtQte.getText());
		} catch (Exception exception) {}
		if((nom != "" || nom != null) && prix != null){
			if(!ControleurNouveauSuppression.nouveauProduit(nom,prix,qte)){
				new FenetreErreur("Le format des données renseignées est mal interprété, veillez les changer ");
			} else {
				new FenetreConfirmation("Le produit " + nom + "a bien été créé");
				this.dispose();
			}
		} else {
			new FenetreErreur("Le format des données renseignées est mal interprété, veillez les changer");
		}

	}

}