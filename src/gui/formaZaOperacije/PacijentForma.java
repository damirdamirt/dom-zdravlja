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

import domZdravlja.DomZdravlja;
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
	private JTextField txtKnjizicaKatOsiguranja = new JTextField(40);
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private DomZdravlja domZdravlja;
	private Pacijent pacijent;
	
	
	
	public PacijentForma(DomZdravlja domZdravlja, Pacijent pacijent) {
		this.domZdravlja = domZdravlja;
		this.pacijent = pacijent;
		if (this.pacijent == null) {
			setTitle("Dodavanje pacijenta");
		}else {
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
	
	
	if(this.pacijent != null) {
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
	add(txtKnjizicaKatOsiguranja);
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
		pfLozinka.setText(this.pacijent.getLozinka());
		cbPol.setSelectedItem(this.pacijent.getPol());
		cbLekar.setSelectedItem(this.pacijent.getIzabLekar());
		txtKnjizicaId.setText(this.pacijent.getKnjiz().getBroj());
		txtKnjizicaDatumIsteka.setText(String.valueOf(this.pacijent.getKnjiz().getDatumIsteka()));
		txtKnjizicaKatOsiguranja.setText(String.valueOf(this.pacijent.getKnjiz().getKatOsig()));
		
		
		
		
	}
	
	private void initActions() {
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
					JOptionPane.showMessageDialog(null, "Greska prilikom parsiranja datuma", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				int kategOsig = Integer.parseInt(txtKnjizicaKatOsiguranja.getText().trim());
				
				if (pacijent == null) {
					ZdrKnjiz zdrKnjizica = new ZdrKnjiz(broj,datumIsteka,kategOsig);
					domZdravlja.getKnjizicaDao().upisiKnjizicu(zdrKnjizica);
					pacijent = new Pacijent(ime, prezime, jmbg, brTel, uloga, adresa, korIme, lozinka, pol, lekar, zdrKnjizica);
					domZdravlja.getPacijentDao().upisiPacijenta(pacijent);
				}else {
					ZdrKnjiz zdrKnjizica = new ZdrKnjiz(broj,datumIsteka,kategOsig);
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
			}
		});
	}
	
}
