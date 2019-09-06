package gui.formaZaOperacije;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import domZdravlja.DomZdravlja;
import gui.formeZaPrikaz.PregledProzor;
import model.Lekar;
import model.MedSestra;
import model.Pacijent;
import model.Pregled;
import model.StatusPreg;
import model.UlogaKor;
import net.miginfocom.swing.MigLayout;

public class PregledForma extends JFrame {

	private JLabel lblPacijent = new JLabel("Pacijent");
	private JComboBox<Pacijent> cbPacijent = null;
	private JLabel lblLekar = new JLabel("Lekari");
	private JComboBox<Lekar> cbLekar = null;
	private JLabel lblTermin = new JLabel("Termin");
	private JTextField txttermin = new JTextField(20);
	private JLabel lblSoba = new JLabel("Soba");
	private JTextField txtSoba = new JTextField(20);
	private JLabel lblOpis = new JLabel("Opis");
	private JTextField txtOpis = new JTextField(40);
	private JLabel lblStatus = new JLabel("Status");
	private JComboBox<StatusPreg> cbStatus = new JComboBox<StatusPreg>();
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");

	private PregledProzor pregledProzor;
	private DomZdravlja domZdravlja;
	private Pregled pregled;

	public PregledForma(PregledProzor pregledProzor, DomZdravlja domZdravlja, Pregled pregled) {
		this.pregledProzor = pregledProzor;
		this.domZdravlja = domZdravlja;
		this.pregled = pregled;
		if (this.pregled == null) {
			setTitle("Dodavanje pregleda");
		} else {
			setTitle("Izmena podataka");
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

		popuniPolja();

		switch (domZdravlja.getLogovaniKorisnik().getUloga()) {
		case LEKAR: {
			add(lblPacijent);
			add(cbPacijent);
			cbPacijent.setEnabled(false);
			add(lblLekar);
			add(cbLekar);
			cbLekar.setEnabled(false);
			add(lblTermin);
			add(txttermin);
			txttermin.setEnabled(false);
			add(lblSoba);
			add(txtSoba);
			txtSoba.setEnabled(false);
			add(lblOpis);
			add(txtOpis);
			txtOpis.setEnabled(false);
			add(lblStatus);
			add(cbStatus);
			add(new JLabel());
			add(btnOk, "split 2");
			add(btnCancel);
		}
			break;
		case PACIJENT: {
			add(lblPacijent);
			add(cbPacijent);
			cbPacijent.setEnabled(false);
			add(lblLekar);
			add(cbLekar);
			cbLekar.setEnabled(false);
			add(lblTermin);
			add(txttermin);
			txttermin.setEnabled(false);
			add(lblSoba);
			add(txtSoba);
			txtSoba.setEnabled(false);
			add(lblOpis);
			add(txtOpis);
			add(lblStatus);
			add(cbStatus);
			add(new JLabel());
			add(btnOk, "split 2");
			add(btnCancel);
		}
			break;
		case MED_SESTRA: {
			add(lblPacijent);
			add(cbPacijent);
			add(lblLekar);
			add(cbLekar);
			add(lblTermin);
			add(txttermin);
			add(lblSoba);
			add(txtSoba);
			add(lblOpis);
			add(txtOpis);
			add(lblStatus);
			add(cbStatus);
			add(new JLabel());
			add(btnOk, "split 2");
			add(btnCancel);
		}
			break;
		}
	}

	private void popuniPolja() {
		if (this.pregled == null && domZdravlja.getLogovaniKorisnik().getUloga() == UlogaKor.PACIJENT) {
			Lekar[] imePrezimeLekara = { ((Pacijent) domZdravlja.getLogovaniKorisnik()).getIzabLekar() };
			cbLekar = new JComboBox<Lekar>(imePrezimeLekara);
			Pacijent[] imePrezimePacjenta = { (Pacijent) domZdravlja.getLogovaniKorisnik() };
			cbPacijent = new JComboBox<Pacijent>(imePrezimePacjenta);
			cbStatus.addItem(StatusPreg.ZATRAZEN);
		} else {
			switch (domZdravlja.getLogovaniKorisnik().getUloga()) {
			case LEKAR: {
				cbStatus.addItem(StatusPreg.OTKAZAN);
				cbStatus.addItem(StatusPreg.ZAVRSEN);
			}
				break;
			case PACIJENT: {
				if (this.pregled != null && this.pregled.getStatus() != StatusPreg.ZAVRSEN
						&& this.pregled.getStatus() != StatusPreg.OTKAZAN) {
					cbStatus.addItem(StatusPreg.OTKAZAN);
				} else if (this.pregled != null) {
					cbStatus.addItem(this.pregled.getStatus());
					cbStatus.setEnabled(false);
				}
				break;
			}
			case MED_SESTRA: {
				cbStatus.addItem(StatusPreg.ZAKAZAN);
			}
				break;
			}
			ArrayList<Lekar> listaLekara = domZdravlja.getLekarDao().ucitajLekare();
			Lekar[] imePrezimeLekara = listaLekara.toArray(new Lekar[listaLekara.size()]);
			cbLekar = new JComboBox<Lekar>(imePrezimeLekara);
			ArrayList<Pacijent> listaPacijenata = domZdravlja.getPacijentDao().ucitajPacijente();
			Pacijent[] imePrezimePacjenta = listaPacijenata.toArray(new Pacijent[listaPacijenata.size()]);
			cbPacijent = new JComboBox<Pacijent>(imePrezimePacjenta);
			if (this.pregled != null) {
				cbPacijent.setSelectedItem(this.pregled.getPacijent());
				cbLekar.setSelectedItem(this.pregled.getLekar());
				DateFormat formater = new SimpleDateFormat("dd.MM.yyyy HH:mm");
				if (pregled.getTermin() != null) {
					txttermin.setText(formater.format(this.pregled.getTermin()));
				}
				txtSoba.setText(this.pregled.getSoba());
				txtOpis.setText(this.pregled.getOpis());
				cbStatus.setSelectedItem(this.pregled.getStatus());
			}
		}
	}

	private void initActions() {
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validacija() == true) {
					Pacijent pacijent = (Pacijent) cbPacijent.getSelectedItem();
					Lekar lekar = (Lekar) cbLekar.getSelectedItem();
					DateFormat formater = new SimpleDateFormat("dd.MM.yyyy HH:mm");
					Date termin = null;
					try {
						if (!txttermin.getText().trim().equals("")) {
							termin = formater.parse(txttermin.getText().trim());
						}
					} catch (ParseException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Greska prilikom parsiranja datuma", "Greska",
								JOptionPane.WARNING_MESSAGE);
					}
					String soba = txtSoba.getText().trim();
					String opis = txtOpis.getText().trim();
					StatusPreg status = (StatusPreg) cbStatus.getSelectedItem();
					if (pregled == null) {
						Pregled pregled = new Pregled(pacijent, lekar, termin, soba, opis, status);
						domZdravlja.getPregledDao().upisiPregled(pregled);
					} else {
						pregled.setPacijent(pacijent);
						pregled.setLekar(lekar);
						pregled.setTermin(termin);
						pregled.setSoba(soba);
						pregled.setOpis(opis);
						pregled.setStatus(status);
						domZdravlja.getPregledDao().izmeniPregled(pregled);
					}
					PregledForma.this.dispose();
					PregledForma.this.setVisible(false);
					PregledForma.this.pregledProzor.punjenjePregledTabela();
				}
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PregledForma.this.setVisible(true);
				PregledForma.this.dispose();
			}
		});
	}

	private boolean validacijaVremenaLekara() {
		// Validacija da vremena lekara
		Lekar lekar = (Lekar) cbLekar.getSelectedItem();
		DateFormat formater = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		Date termin = null;
		try {
			if (!txttermin.getText().trim().equals("")) {
				termin = formater.parse(txttermin.getText().trim());
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Greska prilikom parsiranja datuma", "Greska",
					JOptionPane.WARNING_MESSAGE);
		}

		if (lekar != null && termin != null) {
			ArrayList<Pregled> pregledi = domZdravlja.getPregledDao().nadjiPregledePoKorImenuLekara(lekar.getKorIme());
			for (Pregled pregled : pregledi) {
				if (termin.getDay() == pregled.getTermin().getDay()
						&& termin.getMonth() == pregled.getTermin().getMonth()
						&& termin.getYear() == pregled.getTermin().getYear()
						&& termin.getHours() == pregled.getTermin().getHours()
						&& termin.getMinutes() <= (pregled.getTermin().getMinutes() + 15)) {
					return false;
				}
			}
		}
		return true;

	}

	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		switch (domZdravlja.getLogovaniKorisnik().getUloga()) {
		case PACIJENT: {
			if (txtOpis.getText().trim().equals("")) {
				poruka += "- Unesite opis\n";
				ok = false;
			}
			break;
		}
		case LEKAR: {
			break;
		}
		case MED_SESTRA: {
			if (txttermin.getText().trim().equals("")) {
				poruka += "- Unesite termin\n";
				ok = false;
			}
			if (txtSoba.getText().trim().equals("")) {
				poruka += "- Unesite Broj sobe\n";
				ok = false;
			}
			if (txtOpis.getText().trim().equals("")) {
				poruka += "- Unesite opis\n";
				ok = false;
			}

			if (!validacijaVremenaLekara()) {
				poruka += "- Doktor je zauzet narednih 15 min\n";
				ok = false;
			}

			if (ok == false) {
				JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
			}
			break;
		}
		}
		return ok;

	}
}
