package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.StyledEditorKit.BoldAction;

import domZdravlja.DomZdravlja;
import model.Korisnik;
import net.miginfocom.swing.MigLayout;

public class LoginProzor extends JFrame {

	private JLabel lblPoruka;
	private JLabel lblKorisnickoIme;
	private JTextField txtKorisnickoIme;
	private JLabel lblLozinka;
	private JPasswordField pfLozinka;

	private JButton btnOK;
	private JButton btnCancel;

	private DomZdravlja domZdravlja;

	public LoginProzor(DomZdravlja domZdravlja) {
		this.domZdravlja = domZdravlja;
		setTitle("Prijava");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		initGUI();
		initActions();
		pack();

	}

	private void initGUI() {

		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][]20[]");
		setLayout(layout);

		this.lblPoruka = new JLabel("Dobro dosli. Prijavite se");
		this.lblKorisnickoIme = new JLabel("Korisnicko ime");
		this.txtKorisnickoIme = new JTextField(20);
		this.lblLozinka = new JLabel("Lozinka");
		this.pfLozinka = new JPasswordField(20);
		this.btnOK = new JButton("OK");
		this.btnCancel = new JButton("Cancel");

		this.getRootPane().setDefaultButton(btnOK);

		add(lblPoruka, "span 2");
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblLozinka);
		add(pfLozinka);
		add(new JLabel());
		add(btnOK, "split2");
		add(btnCancel);
		
		
		///////////OBRISI
		txtKorisnickoIme.setText("Maja");
		pfLozinka.setText("maja555");

	}

	private void initActions() {

		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String korisnickoIme = txtKorisnickoIme.getText().trim();
				String lozinka = new String(pfLozinka.getPassword()).trim();
				if (korisnickoIme.equals("") || lozinka.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke");
				} else {
					boolean uspesnoLogovan = domZdravlja.login(korisnickoIme, lozinka);
					if (uspesnoLogovan == true) {
						LoginProzor.this.setVisible(false);
						LoginProzor.this.dispose();
						GlavniProzor prozor = new GlavniProzor(domZdravlja);
						prozor.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Pogresni login podaci");
					}
				}

			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginProzor.this.setVisible(true);
				LoginProzor.this.dispose();
			}
		});
	}
	

}
