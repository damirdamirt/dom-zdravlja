package gui.formeZaPrikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import domZdravlja.DomZdravlja;
import gui.formaZaOperacije.LekarForma;
import gui.formaZaOperacije.PacijentForma;
import model.Lekar;
import model.Pacijent;
import model.Pol;
import model.Pregled;
import model.UlogaKor;
import model.ZdrKnjiz;

public class PacijentProzor extends JFrame {

	private JToolBar mainToolBar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();

	private JTable pacijentTabela;

	private DomZdravlja domZdravlja;
	private Pacijent pacijent;

	public PacijentProzor(DomZdravlja domZdravlja) {
		this.domZdravlja = domZdravlja;
		setTitle("Pacijent");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		PunjenjePacijentTabele();
		initActions();

	}

	private void initGUI() {
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/gui/slike/add.gif"));
		btnAdd.setIcon(addIcon);
		mainToolBar.add(btnAdd);
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/gui/slike/edit.gif"));
		btnEdit.setIcon(editIcon);
		mainToolBar.add(btnEdit);
		ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/gui/slike/remove.gif"));
		btnDelete.setIcon(deleteIcon);
		mainToolBar.add(btnDelete);
		add(mainToolBar, BorderLayout.NORTH);
		pacijentTabela = new JTable();
		pacijentTabela.setRowSelectionAllowed(true);
		pacijentTabela.setColumnSelectionAllowed(false);
		pacijentTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pacijentTabela.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(pacijentTabela);
		add(scrollPane, BorderLayout.CENTER);

	}

	public void PunjenjePacijentTabele() {
		String[] zaglavlje = new String[] { "Ime", "Prezime", "JMBG", "Br telefona", "Uloga", "Adresa",
				"Korisnicko ime", "Lozinka", "Pol", "Izabrani lekar", "Knjizica - ID", "Knjizica-datum isteka",
				"Knjizica-kat.osiguranja" };
		Object[][] podaci = new Object[this.domZdravlja.getPacijentDao().ucitajPacijente().size()][zaglavlje.length];

		if (domZdravlja.getLogovaniKorisnik().getUloga().equals(UlogaKor.PACIJENT)) {
			Pacijent pacijent = (Pacijent) domZdravlja.getLogovaniKorisnik();
			podaci[0][0] = pacijent.getIme();
			podaci[0][1] = pacijent.getPrezime();
			podaci[0][2] = pacijent.getJmbg();
			podaci[0][3] = pacijent.getBrTel();
			podaci[0][4] = pacijent.getUloga();
			podaci[0][5] = pacijent.getAdresa();
			podaci[0][6] = pacijent.getKorIme();
			podaci[0][7] = pacijent.getLozinka();
			podaci[0][8] = pacijent.getPol();
			podaci[0][9] = pacijent.getIzabLekar().getIme() + " " + pacijent.getIzabLekar().getPrezime();
			podaci[0][10] = pacijent.getKnjiz().getBroj();
			DateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
			podaci[0][11] = formater.format(pacijent.getKnjiz().getDatumIsteka());
			podaci[0][12] = pacijent.getKnjiz().getKatOsig();
		} else {
			for (int i = 0; i < this.domZdravlja.getPacijentDao().ucitajPacijente().size(); i++) {
				Pacijent pacijent = domZdravlja.getPacijentDao().ucitajPacijente().get(i);
				podaci[i][0] = pacijent.getIme();
				podaci[i][1] = pacijent.getPrezime();
				podaci[i][2] = pacijent.getJmbg();
				podaci[i][3] = pacijent.getBrTel();
				podaci[i][4] = pacijent.getUloga();
				podaci[i][5] = pacijent.getAdresa();
				podaci[i][6] = pacijent.getKorIme();
				podaci[i][7] = pacijent.getLozinka();
				podaci[i][8] = pacijent.getPol();
				if (pacijent.getIzabLekar() != null) {
					podaci[i][9] = pacijent.getIzabLekar().getIme() + " " + pacijent.getIzabLekar().getPrezime();
				}
				podaci[i][10] = pacijent.getKnjiz().getBroj();
				DateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
				podaci[i][11] = formater.format(pacijent.getKnjiz().getDatumIsteka());
				podaci[i][12] = pacijent.getKnjiz().getKatOsig();

			}
		}

		DefaultTableModel tableModel = new DefaultTableModel(podaci, zaglavlje);
		pacijentTabela.setModel(tableModel);
	}

	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (domZdravlja.getLogovaniKorisnik().getUloga()) {
				case PACIJENT: {
					JOptionPane.showMessageDialog(null, "Ne mozete koristiti ovu opciju", "Greska",
							JOptionPane.WARNING_MESSAGE);
				}
					break;
				case MED_SESTRA: {
					PacijentForma pf = new PacijentForma(PacijentProzor.this, domZdravlja, pacijent);
					pf.setVisible(true);
				}
					break;
				default:
					break;
				}
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (domZdravlja.getLogovaniKorisnik().getUloga()) {
				case PACIJENT: {
					JOptionPane.showMessageDialog(null, "Ne mozete koristiti ovu opciju", "Greska",
							JOptionPane.WARNING_MESSAGE);
				}
					break;
				case MED_SESTRA: {
					int red = pacijentTabela.getSelectedRow();
					if (red == -1) {
						JOptionPane.showMessageDialog(null, "Niste selektovali red", "Greska",
								JOptionPane.WARNING_MESSAGE);
					} else {
						DefaultTableModel model = (DefaultTableModel) pacijentTabela.getModel();
						String korIme = model.getValueAt(red, 6).toString();
						Pacijent pacijent = domZdravlja.getPacijentDao().nadjiPacPoKorImenu(korIme);
						if (pacijent != null) {
							PacijentForma pf = new PacijentForma(PacijentProzor.this, domZdravlja, pacijent);
							pf.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog lekara", "Greska",
									JOptionPane.WARNING_MESSAGE);
						}
					}
				}
					break;
				default:
					break;
				}
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (domZdravlja.getLogovaniKorisnik().getUloga()) {
				case PACIJENT: {
					JOptionPane.showMessageDialog(null, "Ne mozete koristiti ovu opciju", "Greska",
							JOptionPane.WARNING_MESSAGE);
				}
					break;
				case MED_SESTRA: {
					int red = pacijentTabela.getSelectedRow();
					if (red == -1) {
						JOptionPane.showMessageDialog(null, "Niste selektovali red", "Greska",
								JOptionPane.WARNING_MESSAGE);
					} else {
						DefaultTableModel model = (DefaultTableModel) pacijentTabela.getModel();
						String korIme = model.getValueAt(red, 6).toString();
						Pacijent pacijent = domZdravlja.getPacijentDao().nadjiPacPoKorImenu(korIme);
						if (pacijent != null) {
							int izbor = JOptionPane.showConfirmDialog(null,
									"Da li stvarno zelite da obrisete pacijenta?", pacijent.getIme() + " - Brisanje",
									JOptionPane.YES_NO_OPTION);
							if (izbor == JOptionPane.YES_OPTION) {
								domZdravlja.getPacijentDao().izbrisiPacijenta(pacijent);
								model.removeRow(red);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog pacijenta", "Greska",
									JOptionPane.WARNING_MESSAGE);
						}
					}
				}
					break;
				default:
					break;
				}
			}
		});
	}
}
