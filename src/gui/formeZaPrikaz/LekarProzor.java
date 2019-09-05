package gui.formeZaPrikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import model.Korisnik;
import model.Lekar;
import model.Pol;
import model.Pregled;
import model.Sluzba;
import model.UlogaKor;
import sun.rmi.log.LogOutputStream;

public class LekarProzor extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();

	private DefaultTableModel tablemodel;
	private JTable lekarTabela;

	private DomZdravlja domZdravlja;
	private Lekar lekar;

	public LekarProzor(DomZdravlja domZdravlja) {
		this.domZdravlja = domZdravlja;
		setTitle("Lekar");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		punjenjeLekarTabele();
		initActions();
	}

	private void initGUI() {
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/gui/slike/add.gif"));
		btnAdd.setIcon(addIcon);
		mainToolbar.add(btnAdd);
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/gui/slike/edit.gif"));
		btnEdit.setIcon(editIcon);
		mainToolbar.add(btnEdit);
		ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/gui/slike/remove.gif"));
		btnDelete.setIcon(deleteIcon);
		mainToolbar.add(btnDelete);
		add(mainToolbar, BorderLayout.NORTH);

	}

	private void punjenjeLekarTabele() {
		String[] zaglavlje = new String[] { "Ime", "Prezime", "JMBG", "Br. telefona", "Uloga", "Adresa",
				"Korisnicko ime", "Lozinka", "Pol", "Plata", "Sluzba", "Specijalizacija" };

		Object[][] podaci = new Object[this.domZdravlja.getLekarDao().ucitajLekare().size()][zaglavlje.length];

		if (domZdravlja.getLogovaniKorisnik().getUloga().equals(UlogaKor.LEKAR)) {
			Lekar lekar = (Lekar) domZdravlja.getLogovaniKorisnik();
			podaci[0][0] = lekar.getIme();
			podaci[0][1] = lekar.getPrezime();
			podaci[0][2] = lekar.getJmbg();
			podaci[0][3] = lekar.getBrTel();
			podaci[0][4] = lekar.getUloga();
			podaci[0][5] = lekar.getAdresa();
			podaci[0][6] = lekar.getKorIme();
			podaci[0][7] = lekar.getLozinka();
			podaci[0][8] = lekar.getPol();
			podaci[0][9] = lekar.getPlata();
			podaci[0][10] = lekar.getSluzba();
			podaci[0][11] = lekar.getSpec();
		} else {
			for (int i = 0; i < this.domZdravlja.getLekarDao().ucitajLekare().size(); i++) {
				Lekar lekar = domZdravlja.getLekarDao().ucitajLekare().get(i);
				podaci[i][0] = lekar.getIme();
				podaci[i][1] = lekar.getPrezime();
				podaci[i][2] = lekar.getJmbg();
				podaci[i][3] = lekar.getBrTel();
				podaci[i][4] = lekar.getUloga();
				podaci[i][5] = lekar.getAdresa();
				podaci[i][6] = lekar.getKorIme();
				podaci[i][7] = lekar.getLozinka();
				podaci[i][8] = lekar.getPol();
				podaci[i][9] = lekar.getPlata();
				podaci[i][10] = lekar.getSluzba();
				podaci[i][11] = lekar.getSpec();
			}
		}

		tablemodel = new DefaultTableModel(podaci, zaglavlje);
		lekarTabela = new JTable(tablemodel);
		lekarTabela.setRowSelectionAllowed(true);
		lekarTabela.setColumnSelectionAllowed(false);
		lekarTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lekarTabela.setDefaultEditor(Object.class, null);

		JScrollPane scrollPane = new JScrollPane(lekarTabela);
		add(scrollPane, BorderLayout.CENTER);
	}

	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (domZdravlja.getLogovaniKorisnik().getUloga()) {
				case LEKAR: {
					JOptionPane.showMessageDialog(null, "Ne mozete koristiti ovu opciju");
				}
					break;
				case MED_SESTRA: {
					LekarForma lf = new LekarForma(domZdravlja, lekar);
					lf.setVisible(true);
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
				case LEKAR: {
					JOptionPane.showMessageDialog(null, "Ne mozete koristiti ovu opciju", "Greska",
							JOptionPane.WARNING_MESSAGE);
				}
					break;
				case MED_SESTRA: {
					int red = lekarTabela.getSelectedRow();
					if (red == -1) {
						JOptionPane.showMessageDialog(null, "Niste selektovali red", "Greska",
								JOptionPane.WARNING_MESSAGE);
					} else {
						DefaultTableModel model = (DefaultTableModel) lekarTabela.getModel();
						String korIme = model.getValueAt(red, 6).toString();
						Lekar lekar = domZdravlja.getLekarDao().nadjiLekaraPoKorImenu(korIme);
						if (lekar != null) {
							LekarForma lf = new LekarForma(domZdravlja, lekar);
							lf.setVisible(true);
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
				case LEKAR: {
					JOptionPane.showMessageDialog(null, "Ne mozete koristiti ovu opciju", "Greska",
							JOptionPane.WARNING_MESSAGE);
				}
					break;
				case MED_SESTRA: {
					int red = lekarTabela.getSelectedRow();
					if (red == -1) {
						JOptionPane.showMessageDialog(null, "Niste selektovali red", "Greska",
								JOptionPane.WARNING_MESSAGE);
					} else {
						DefaultTableModel model = (DefaultTableModel) lekarTabela.getModel();
						String korIme = model.getValueAt(red, 6).toString();
						Lekar lekar = domZdravlja.getLekarDao().nadjiLekaraPoKorImenu(korIme);
						if (lekar != null) {
							int izbor = JOptionPane.showConfirmDialog(null, "Da li stvarno zelte da obrisete lekara?",
									lekar.getIme() + " - Brisanje", JOptionPane.YES_NO_OPTION);
							if (izbor == JOptionPane.YES_OPTION) {
								domZdravlja.getLekarDao().izbrisiLekara(lekar);
								model.removeRow(red);
							}
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

	}
}
