package gui.formaZaOperacije;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.ZdrKnjizicaDao;
import domZdravlja.DomZdravlja;
import gui.formeZaPrikaz.PacijentProzor;
import model.KategOsiguranjaKnjizice;
import model.Lekar;
import model.Pacijent;
import model.Pol;
import model.Sluzba;
import model.StatusPreg;
import model.UlogaKor;
import model.ZdrKnjiz;
import net.miginfocom.swing.MigLayout;

public class PacijentForma extends JFrame {

	private JLabel lblIme = new JLabel("Ime");
	private JTextField txtIme = new JTextField(20);
	private JLabel lblPrezime = new JLabel("Prezime");
	private JTextField txtPrezime = new JTextField(20);
	private JLabel lblJMBG = new JLabel("JMBG");
	private JTextField txtJMBG = new JTextField(20);
	private JLabel lblBrTelefona = new JLabel("Br. telefona");
	private JTextField txtBrTelefona = new JTextField(20);
	private JLabel lblUloga = new JLabel("Pacijent");
	private JLabel lblAdresa = new JLabel("Adresa");
	private JTextField txtAdresa = new JTextField(20);
	private JLabel lblKorisnickoIme = new JLabel("Korisnicko ime");
	private JTextField txtKorisnickoIme = new JTextField(20);
	private JLabel lblLozinka = new JLabel("Lozinka");
	private JPasswordField pfLozinka = new JPasswordField(20);
	private JLabel lblPol = new JLabel("Pol");
	private JComboBox<Pol> cbPol = new JComboBox<Pol>(Pol.values());
	private JLabel lblLekar = new JLabel("Lekari");
	private JComboBox<Lekar> cbLekar = null;
	private JLabel lblKnjizicaId = new JLabel("Knjizica - ID");
	private JTextField txtKnjizicaId = new JTextField(20);
	private JLabel lblKnjizicaDatumIsteka = new JLabel("Knjizica - datum isteka");
	private JTextField txtKnjizicaDatumIsteka = new JTextField(40);
	private JLabel lblKnjizicaKatOsiguanja = new JLabel("Knjizica - kategorija osiguranja");
	private JComboBox<KategOsiguranjaKnjizice> cbKnjizicaKatOsiguranja = new JComboBox<KategOsiguranjaKnjizice>(
			KategOsiguranjaKnjizice.values());
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private PacijentProzor pacijentProzor;
	private DomZdravlja domZdravlja;
	private Pacijent pacijent;

	public PacijentForma(PacijentProzor pacijentProzor, DomZdravlja domZdravlja, Pacijent pacijent) {
		this.pacijentProzor = pacijentProzor;
		this.domZdravlja = domZdravlja;
		this.pacijent = pacijent;
		if (this.pacijent == null) {
			setTitle("Dodavanje pacijenta");
		} else {
			setTitle("Izmena podataka - " + this.pacijent.getKorIme());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initActions();
		pack();
	}

	private void initGUI() {

		MigLayout layout = new MigLayout("wrap 2");
		setLayout(layout);

		ArrayList<Lekar> listaLekara = domZdravlja.getLekarDao().ucitajLekare();
		Lekar[] imePrezimeLekara = listaLekara.toArray(new Lekar[listaLekara.size()]);
		cbLekar = new JComboBox<Lekar>(imePrezimeLekara);

		if (this.pacijent != null) {
			popuniPolja();
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
		add(lblLekar);
		add(cbLekar);
		add(lblKnjizicaId);
		add(txtKnjizicaId);
		add(lblKnjizicaDatumIsteka);
		add(txtKnjizicaDatumIsteka);
		add(lblKnjizicaKatOsiguanja);
		add(cbKnjizicaKatOsiguranja);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
	}

	private void popuniPolja() {
		txtIme.setText(this.pacijent.getIme());
		txtPrezime.setText(this.pacijent.getPrezime());
		txtJMBG.setText(this.pacijent.getJmbg());
		txtBrTelefona.setText(this.pacijent.getBrTel());
		txtAdresa.setText(this.pacijent.getAdresa());
		txtKorisnickoIme.setText(this.pacijent.getKorIme());
		txtKorisnickoIme.setEnabled(false);
		pfLozinka.setText(this.pacijent.getLozinka());
		cbPol.setSelectedItem(this.pacijent.getPol());
		cbLekar.setSelectedItem(this.pacijent.getIzabLekar());
		txtKnjizicaId.setText(this.pacijent.getKnjiz().getBroj());
		DateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
		txtKnjizicaDatumIsteka.setText(formater.format(this.pacijent.getKnjiz().getDatumIsteka()));
		cbKnjizicaKatOsiguranja.setSelectedItem(this.pacijent.getKnjiz());

	}

	private void initActions() {
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isEdit = false;
				if (pacijent != null) {
					isEdit = true;
				}
				if (validacija(isEdit) == true) {
					UlogaKor uloga = domZdravlja.getLogovaniKorisnik().getUloga().PACIJENT;
					String ime = txtIme.getText().trim();
					String prezime = txtPrezime.getText().trim();
					String jmbg = txtJMBG.getText().trim();
					String brTel = txtBrTelefona.getText().trim();
					String adresa = txtAdresa.getText().trim();
					String korIme = txtKorisnickoIme.getText().trim();
					String lozinka = new String(pfLozinka.getPassword()).trim();
					Pol pol = (Pol) cbPol.getSelectedItem();
					Lekar lekar = (Lekar) cbLekar.getSelectedItem();
					String broj = txtKnjizicaId.getText().trim();
					DateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
					Date datumIsteka = null;
					try {
						datumIsteka = formater.parse(txtKnjizicaDatumIsteka.getText().trim());
					} catch (ParseException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Greska prilikom parsiranja datuma", "Greska",
								JOptionPane.WARNING_MESSAGE);
					}
					KategOsiguranjaKnjizice kategOsig = (KategOsiguranjaKnjizice) cbKnjizicaKatOsiguranja
							.getSelectedItem();

					if (!isEdit) {
						ZdrKnjiz zdrKnjizica = new ZdrKnjiz(broj, datumIsteka, kategOsig);
						domZdravlja.getKnjizicaDao().upisiKnjizicu(zdrKnjizica);
						pacijent = new Pacijent(ime, prezime, jmbg, brTel, uloga, adresa, korIme, lozinka, pol, lekar,
								zdrKnjizica);
						domZdravlja.getPacijentDao().upisiPacijenta(pacijent);
					} else {
						ZdrKnjiz zdrKnjizica = new ZdrKnjiz(broj, datumIsteka, kategOsig);
						pacijent.setIme(ime);
						pacijent.setPrezime(prezime);
						pacijent.setJmbg(jmbg);
						pacijent.setBrTel(brTel);
						pacijent.setAdresa(adresa);
						pacijent.setKorIme(korIme);
						pacijent.setLozinka(lozinka);
						pacijent.setPol(pol);
						pacijent.setIzabLekar(lekar);
						pacijent.setKnjiz(zdrKnjizica);
						domZdravlja.getPacijentDao().izmeniPacijenta(pacijent);
					}
					PacijentForma.this.dispose();
					PacijentForma.this.setVisible(false);
					PacijentForma.this.pacijentProzor.punjenjePacijentTabele();
				}
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PacijentForma.this.setVisible(true);
				PacijentForma.this.dispose();
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
		if (txtKnjizicaId.getText().trim().equals("")) {
			poruka += "- Unesite broj knjizice\n";
			ok = false;

		} else {
			if (!isEdit) {
				String broj = txtKnjizicaId.getText().trim();
				if (((ZdrKnjizicaDao) domZdravlja.getKnjizicaDao()).validacijaIdknjizice(broj) == false) {
					poruka += "- broj vec postoji, unesite drugi\n";
					ok = false;
				}
			}
		}

		DateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
		try {
			formater.parse(txtKnjizicaDatumIsteka.getText().trim());
		} catch (ParseException e) {
			poruka += "- Unesite datum isteka\n";
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
