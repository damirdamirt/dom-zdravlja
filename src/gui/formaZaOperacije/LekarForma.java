package gui.formaZaOperacije;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sun.scenario.animation.shared.InfiniteClipEnvelope;

import dao.LekarDao;
import domZdravlja.DomZdravlja;
import model.Lekar;
import model.Pol;
import model.Pregled;
import model.Sluzba;
import model.UlogaKor;
import net.miginfocom.swing.MigLayout;

public class LekarForma extends JFrame {
	
	private JLabel lblUloga = new JLabel("Lekar");
	private JLabel lblIme = new JLabel("Ime");
	private JTextField txtIme = new JTextField(20);
	private JLabel lblPrezime = new JLabel("Prezime");
	private JTextField txtPrezime = new JTextField(20);
	private JLabel lblJMBG = new JLabel("JMBG");
	private JTextField txtJMBG = new JTextField(20);
	private JLabel lblBrTelefona = new JLabel("Br. telefona");
	private JTextField txtBrTelefona = new JTextField(20);
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
	private JComboBox<Sluzba> cbSluzba = new JComboBox<Sluzba>();
	private JLabel lblSpecijalizacija = new JLabel("Specijalizacija");
	private JTextField txtSpecijalizacija = new JTextField(20);
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private DomZdravlja domZdravlja;
	private Lekar lekar;
	
	
	public LekarForma(DomZdravlja domZdravlja, Lekar lekar) {
		this.domZdravlja = domZdravlja;
		this.lekar = lekar;
		if (this.lekar == null) {
			setTitle("Dodavanje lekara");
		}else {
			setTitle("Izmena podataka - " + this.lekar.getKorIme());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initActions();
		pack();

	}
	
	private void initGUI() {
		
		MigLayout layout = new MigLayout("Wrap 2");
		setLayout(layout);
		
		if (this.lekar != null) {
			PopuniPolja();
		}
		
		cbSluzba.addItem(Sluzba.STOM_SLUZBA);
		cbSluzba.addItem(Sluzba.SLUZBA_OP_MED);
		cbSluzba.addItem(Sluzba.SLU_ZDR_ZAS_RAD);
		cbSluzba.addItem(Sluzba.SLU_ZDR_ZAS_DECE);
		
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
		add(lblSpecijalizacija);
		add(txtSpecijalizacija);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
		
	}
	
	private void PopuniPolja() {
		txtIme.setText(this.lekar.getIme());
		txtPrezime.setText(this.lekar.getPrezime());
		txtJMBG.setText(this.lekar.getJmbg());
		txtBrTelefona.setText(this.lekar.getBrTel());
		txtAdresa.setText(this.lekar.getAdresa());
		txtKorisnickoIme.setText(this.lekar.getKorIme());
		pfLozinka.setText(this.lekar.getLozinka());
		cbPol.setSelectedItem(this.lekar.getPol());
		txtPlata.setText(String.valueOf(this.lekar.getPlata()));
		cbSluzba.setSelectedItem(this.lekar.getSluzba());
		txtSpecijalizacija.setText(this.lekar.getSpec());
	}
	
	private void initActions() {
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				UlogaKor uloga = domZdravlja.getLogovaniKorisnik().getUloga().LEKAR;
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
				String spec = txtSpecijalizacija.getText().trim();
				if (lekar == null) {
					lekar = new Lekar(ime, prezime, jmbg, brTel, uloga, adresa, korIme, lozinka, pol, plata, sluzba, spec);
					domZdravlja.getLekarDao().upisiLekara(lekar);
				}else {
					lekar.setIme(ime);
					lekar.setPrezime(prezime);
					lekar.setJmbg(jmbg);
					lekar.setBrTel(brTel);
					lekar.setAdresa(adresa);
					lekar.setKorIme(korIme);
					lekar.setLozinka(lozinka);
					lekar.setPol(pol);
					lekar.setPlata(plata);
					lekar.setSluzba(sluzba);
					lekar.setSpec(spec);
					domZdravlja.getLekarDao().izmeniLekara(lekar);
				}
				LekarForma.this.dispose();
				LekarForma.this.setVisible(false);
			}
		});
		
		
		
	}
}
