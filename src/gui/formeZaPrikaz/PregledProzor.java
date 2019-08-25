package gui.formeZaPrikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	private DefaultTableModel tablemodel;
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

	}

	private void punjenjePregledTabela() {
		ArrayList<Pregled> pregledi = null;

//		if(domZdravlja.getLogovaniKorisnik().getUloga().equals(UlogaKor.MED_SESTRA)) {
//			pregled = this.domZdravlja.getPregledDao().ucitajPreglede();
//		}

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

		String[] zaglavlje = new String[] {"Pacijent", "Lekar", "ID", "Termin", "Soba", "Opis", "Status" };
		Object[][] podaci = new Object[pregledi.size()][zaglavlje.length];

		for (int i = 0; i < pregledi.size(); i++) {
			Pregled pregled = pregledi.get(i);
			
			podaci[i][0] = pregled.getPacijent().getIme() + " " + pregled.getPacijent().getPrezime();
			podaci[i][1] = pregled.getLekar().getIme() + " " + pregled.getLekar().getPrezime();
			podaci[i][2] = pregled.getId();
			podaci[i][3] = pregled.getTermin();
			podaci[i][4] = pregled.getSoba();
			podaci[i][5] = pregled.getOpis();
			podaci[i][6] = pregled.getStatus();
		}

		tablemodel = new DefaultTableModel(podaci, zaglavlje);
		pregledTabela = new JTable(tablemodel);
		pregledTabela.setRowSelectionAllowed(true);
		pregledTabela.setColumnSelectionAllowed(false);
		pregledTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pregledTabela.setDefaultEditor(Object.class, null);

		JScrollPane scrollPane = new JScrollPane(pregledTabela);
		add(scrollPane, BorderLayout.CENTER);

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
					PregledForma prf = new PregledForma(domZdravlja, pregled);
					prf.setVisible(true);
					break;
				case MED_SESTRA:
					PregledForma pref = new PregledForma(domZdravlja, pregled);
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
					String id = model.getValueAt(red, 0).toString();
					Pregled pregled = domZdravlja.getPregledDao().nadjiPregPoId(id);
					if (pregled != null) {
						PregledForma prf = new PregledForma(domZdravlja, pregled);
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
						String id = model.getValueAt(red, 0).toString();
						Pregled pregled = domZdravlja.getPregledDao().nadjiPregPoId(id);
						if (pregled != null) {
							int izbor = JOptionPane.showConfirmDialog(null,
									"Da li stvarno zelite da obrisete  pregled?", " - Brisanje",
									JOptionPane.YES_NO_OPTION);
							if (izbor == JOptionPane.YES_OPTION) {
								domZdravlja.getPregledDao().izbrisiPregled(pregled);
								model.removeRow(red);
							}
						}else {
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
