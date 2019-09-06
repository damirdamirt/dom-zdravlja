package gui.formaZaOperacije;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.LekarDao;
import domZdravlja.DomZdravlja;
import gui.formeZaPrikaz.SestraProzor;
import model.MedSestra;
import model.Pacijent;
import model.Pol;
import model.Sluzba;
import model.UlogaKor;
import net.miginfocom.swing.MigLayout;

public class SestraForma extends JFrame {

	private JLabel lblIme = new JLabel("Ime");
	private JTextField txtIme = new JTextField(20);
	private JLabel lblPrezime = new JLabel("Prezime");
	private JTextField txtPrezime = new JTextField(20);
	private JLabel lblJMBG = new JLabel("JMBG");
	private JTextField txtJMBG = new JTextField(20);
	private JLabel lblBrTelefona = new JLabel("Br. telefona");
	private JTextField txtBrTelefona = new JTextField(20);
	private JLabel lblUloga = new JLabel("Sestra");
	private JLabel lblAdresa = new JLabel("Adresa");
	private JTextField txtAdresa = new JTextField(20);
	private JLabel lblKorisnickoIme = new JLabel("Korisnicko ime");
	private JTextField txtKorisnickoIme = new JTextField(20);
	private JLabel lblLozinka = new JLabel("Lozinka");
	private JPasswordField pfLozinka = new JPasswordField(20);
	private JLabel lblPol = new JLabel("Pol");
	private JComboBox<Pol> cbPol = new JComboBox<Pol>(Pol.values());
	private JLabel lblPlata = new JLabel("Plata");
	private JTextField txtPlata = new JTextField(20);
	private JLabel lblSluzba = new JLabel("Sluzba");
	private JComboBox<Sluzba> cbSluzba = new JComboBox<Sluzba>(Sluzba.values());
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");

	private SestraProzor sestraProzor;
	private DomZdravlja domZdravlja;
	private MedSestra sestra;

	public SestraForma(SestraProzor sestraProzor, DomZdravlja domZdravlja, MedSestra sestra) {
		this.sestraProzor = sestraProzor;
		this.domZdravlja = domZdravlja;
		this.sestra = sestra;
		if (this.sestra == null) {
			setTitle("Dodavanje sestre");
		} else {
			setTitle("Izmena podataka - " + this.sestra.getKorIme());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initActions();
		pack();
	}

	public void initGUI() {

		MigLayout layout = new MigLayout("Wrap 2");
		setLayout(layout);

		if (this.sestra != null) {
			PopuniPolja();
		}

		add(lblUloga);
		add(new JLabel());
		add(lblIme);
		add(txtIme);
		add(lblPrezime);
		add(txtPrezime);
		add(lblJMBG);
		add(txtJMBG);
		add(lblBrTelefona);
		add(txtBrTelefona);
		add(lblAdresa);
		add(txtAdresa);
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblLozinka);
		add(pfLozinka);
		add(lblPol);
		add(cbPol);
		add(lblPlata);
		add(txtPlata);
		add(lblSluzba);
		add(cbSluzba);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);

	}

	private void PopuniPolja() {
		txtIme.setText(this.sestra.getIme());
		txtPrezime.setText(this.sestra.getPrezime());
		txtJMBG.setText(this.sestra.getJmbg());
		txtBrTelefona.setText(this.sestra.getBrTel());
		txtAdresa.setText(this.sestra.getAdresa());
		txtKorisnickoIme.setText(this.sestra.getKorIme());
		txtKorisnickoIme.setEnabled(false);
		pfLozinka.setText(this.sestra.getLozinka());
		cbPol.setSelectedItem(this.sestra.getPol());
		txtPlata.setText(String.valueOf(this.sestra.getPlata()));
		cbSluzba.setSelectedItem(this.sestra.getSluzba());
	}

	private void initActions() {
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isEdit = false;
				if (sestra != null) {
					isEdit = true;
				}
				if (validacija(isEdit) == true) {
					UlogaKor uloga = domZdravlja.getLogovaniKorisnik().getUloga().MED_SESTRA;
					String ime = txtIme.getText().trim();
					String prezime = txtPrezime.getText().trim();
					String jmbg = txtJMBG.getText().trim();
					String brTel = txtBrTelefona.getText().trim();
					String adresa = txtAdresa.getText().trim();
					String korIme = txtKorisnickoIme.getText().trim();
					String lozinka = new String(pfLozinka.getPassword()).trim();
					Pol pol = (Pol) cbPol.getSelectedItem();
					double plata = Double.parseDouble(txtPlata.getText().trim());
					Sluzba sluzba = (Sluzba) cbSluzba.getSelectedItem();
					if (!isEdit) {
						sestra = new MedSestra(ime, prezime, jmbg, brTel, uloga, adresa, korIme, lozinka, pol, plata,
								sluzba);
						domZdravlja.getSestraDao().upisiMedSestru(sestra);
					} else {
						sestra.setIme(ime);
						sestra.setPrezime(prezime);
						sestra.setJmbg(jmbg);
						sestra.setBrTel(brTel);
						sestra.setAdresa(adresa);
						sestra.setKorIme(korIme);
						sestra.setLozinka(lozinka);
						sestra.setPol(pol);
						sestra.setPlata(plata);
						sestra.setSluzba(sluzba);
						domZdravlja.getSestraDao().izmeniMedSestru(sestra);
					}
					SestraForma.this.dispose();
					SestraForma.this.setVisible(false);
					SestraForma.this.sestraProzor.punjenjeTabeleSestra();
				}
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SestraForma.this.setVisible(true);
				SestraForma.this.dispose();
			}
		});
	}

	private boolean validacija(boolean isEdit) {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";

		if (txtIme.getText().trim().equals("")) {
			poruka += "- Unesite ime\n";
			ok = false;
		}
		if (txtPrezime.getText().trim().equals("")) {
			poruka += "- Unesite prezime\n";
			ok = false;
		}

		if (txtJMBG.getText().trim().equals("")) {
			poruka += "- Unesite JMBG\n";
			ok = false;
		}

		if (txtBrTelefona.getText().trim().equals("")) {
			poruka += "- Unesite broj telefona\n";
			ok = false;
		}
		if (txtAdresa.getText().trim().equals("")) {
			poruka += "- Unesite adresu\n";
			ok = false;
		}
		if (txtKorisnickoIme.getText().trim().equals("")) {
			poruka += "- Unesite korisnicko ime\n";
			ok = false;
		}
		String lozinka = new String(pfLozinka.getPassword()).trim();
		if (lozinka.trim().equals("")) {
			poruka += "- Unesite lozinku\n";
			ok = false;
		}
		try {
			Double.parseDouble(txtPlata.getText().trim());
		} catch (NumberFormatException e) {
			poruka += "- Plata mora biti broj";
			ok = false;
		}
		if (!isEdit) {
			String korIme = txtKorisnickoIme.getText().trim();
			if ((domZdravlja.getLekarDao()).validacijaKorImenaLekar(korIme) == false) {
				poruka += "- Korisnicko ime vec postoji, unesite drugo.";
				ok = false;
			} else if ((domZdravlja.getPacijentDao()).validacijaKorImenaPacijent(korIme) == false) {
				poruka += "- Korisnicko ime vec postoji, unesite drugo.";
				ok = false;
			} else if ((domZdravlja.getSestraDao()).validacijaKorImenaSestra(korIme) == false) {
				poruka += "- Korisnicko ime vec postoji, unesite drugo.";
				ok = false;
			}
		}
		if (ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}

		return ok;
	}
}
