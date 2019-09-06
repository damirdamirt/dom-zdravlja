package gui.formeZaPrikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import domZdravlja.DomZdravlja;
import gui.formaZaOperacije.PregledForma;
import model.Lekar;
import model.Pacijent;
import model.Pregled;
import model.StatusPreg;
import model.UlogaKor;

public class PregledProzor extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();

	private JTable pregledTabela;

	private DomZdravlja domZdravlja;
	private Pregled pregled;

	public PregledProzor(DomZdravlja domZdravlja) {
		this.domZdravlja = domZdravlja;
		setTitle("Pregledi");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		punjenjePregledTabela();
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
		pregledTabela = new JTable();
		pregledTabela.setRowSelectionAllowed(true);
		pregledTabela.setColumnSelectionAllowed(false);
		pregledTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pregledTabela.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(pregledTabela);
		add(scrollPane, BorderLayout.CENTER);
	}

	public void punjenjePregledTabela() {
		ArrayList<Pregled> pregledi = null;

		switch (domZdravlja.getLogovaniKorisnik().getUloga()) {
		case MED_SESTRA:
			pregledi = this.domZdravlja.getPregledDao().ucitajPreglede();
			break;
		case PACIJENT:
			pregledi = this.domZdravlja.getPregledDao()
					.nadjiPregledePoKorImenuPacijenta(this.domZdravlja.getLogovaniKorisnik().getKorIme());
			break;
		case LEKAR:
			pregledi = this.domZdravlja.getPregledDao()
					.nadjiPregledePoKorImenuLekara(this.domZdravlja.getLogovaniKorisnik().getKorIme());
			break;
		}

		String[] zaglavlje = new String[] { "Pacijent", "Lekar", "ID", "Termin", "Soba", "Opis", "Status" };
		Object[][] podaci = new Object[pregledi.size()][zaglavlje.length];

		for (int i = 0; i < pregledi.size(); i++) {
			Pregled pregled = pregledi.get(i);

			if (pregled.getPacijent() != null) {
				podaci[i][0] = pregled.getPacijent().getIme() + " " + pregled.getPacijent().getPrezime();
			}
			if (pregled.getLekar() != null) {
				podaci[i][1] = pregled.getLekar().getIme() + " " + pregled.getLekar().getPrezime();
			}
			podaci[i][2] = pregled.getId();
			DateFormat formater = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			if (pregled.getTermin() == null) {
				podaci[i][3] = "";
			} else {
				podaci[i][3] = formater.format(pregled.getTermin());
			}
			podaci[i][4] = pregled.getSoba();
			podaci[i][5] = pregled.getOpis();
			podaci[i][6] = pregled.getStatus();
		}

		DefaultTableModel tablemodel = new DefaultTableModel(podaci, zaglavlje);
		pregledTabela.setModel(tablemodel);
	}

	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (domZdravlja.getLogovaniKorisnik().getUloga()) {
				case LEKAR: {
					JOptionPane.showMessageDialog(null, "Ne mozete koristiti ovu opciju", "Greska",
							JOptionPane.WARNING_MESSAGE);
				}
					break;
				case PACIJENT:
					PregledForma prf = new PregledForma(PregledProzor.this, domZdravlja, pregled);
					prf.setVisible(true);
					break;
				case MED_SESTRA:
					PregledForma pref = new PregledForma(PregledProzor.this, domZdravlja, pregled);
					pref.setVisible(true);
					break;
				}
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = pregledTabela.getSelectedRow();
				if (red == -1) {
					JOptionPane.showMessageDialog(null, "Niste selektovali red", "Greska", JOptionPane.WARNING_MESSAGE);
				} else {
					DefaultTableModel model = (DefaultTableModel) pregledTabela.getModel();
					String id = model.getValueAt(red, 2).toString();
					Pregled pregled = domZdravlja.getPregledDao().nadjiPregPoId(id);
					if (pregled != null) {
						PregledForma prf = new PregledForma(PregledProzor.this, domZdravlja, pregled);
						prf.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani pregled", "Greska",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (domZdravlja.getLogovaniKorisnik().getUloga()) {
				case LEKAR: {
					JOptionPane.showMessageDialog(null, "Ne mozete koristit ovu opciju", "Greska",
							JOptionPane.WARNING_MESSAGE);
					break;
				}
				case MED_SESTRA: {
					int red = pregledTabela.getSelectedRow();
					if (red == -1) {
						JOptionPane.showMessageDialog(null, "Niste selektovali red", "Greska",
								JOptionPane.WARNING_MESSAGE);
					} else {
						DefaultTableModel model = (DefaultTableModel) pregledTabela.getModel();
						String id = model.getValueAt(red, 2).toString();
						Pregled pregled = domZdravlja.getPregledDao().nadjiPregPoId(id);
						if (pregled != null) {
							int izbor = JOptionPane.showConfirmDialog(null,
									"Da li stvarno zelite da obrisete  pregled?", " - Brisanje",
									JOptionPane.YES_NO_OPTION);
							if (izbor == JOptionPane.YES_OPTION) {
								domZdravlja.getPregledDao().izbrisiPregled(pregled);
								model.removeRow(red);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani pregled", "Greska",
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
