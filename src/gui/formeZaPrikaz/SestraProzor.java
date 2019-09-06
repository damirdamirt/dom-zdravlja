package gui.formeZaPrikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.sun.xml.internal.ws.api.policy.ModelGenerator;

import domZdravlja.DomZdravlja;
import gui.formaZaOperacije.SestraForma;
import model.MedSestra;
import model.Pol;
import model.Sluzba;
import model.UlogaKor;

public class SestraProzor extends JFrame {

	private JToolBar mainToolBar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();

	private JTable sestraTabela;

	private DomZdravlja domZdravlja;
	private MedSestra sestra;

	public SestraProzor(DomZdravlja domZdravlja) {
		this.domZdravlja = domZdravlja;
		setTitle("Sestra");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		punjenjeTabeleSestra();
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
		sestraTabela = new JTable();
		sestraTabela.setRowSelectionAllowed(true);
		sestraTabela.setColumnSelectionAllowed(false);
		sestraTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sestraTabela.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(sestraTabela);
		add(scrollPane, BorderLayout.CENTER);
	}

	public void punjenjeTabeleSestra() {
		String[] zaglavlje = new String[] { "Ime", "Prezime", "JMBG", "Br telefona", "Uloga", "Adresa",
				"Korisnicko ime", "Lozinka", "Pol", "Plata", "Sluzba" };
		Object[][] podaci = new Object[this.domZdravlja.getSestraDao().ucitajSestre().size()][zaglavlje.length];

		for (int i = 0; i < this.domZdravlja.getSestraDao().ucitajSestre().size(); i++) {
			MedSestra sestra = domZdravlja.getSestraDao().ucitajSestre().get(i);

			podaci[i][0] = sestra.getIme();
			podaci[i][1] = sestra.getPrezime();
			podaci[i][2] = sestra.getJmbg();
			podaci[i][3] = sestra.getBrTel();
			podaci[i][4] = sestra.getUloga();
			podaci[i][5] = sestra.getAdresa();
			podaci[i][6] = sestra.getKorIme();
			podaci[i][7] = sestra.getLozinka();
			podaci[i][8] = sestra.getPol();
			podaci[i][9] = sestra.getPlata();
			podaci[i][10] = sestra.getSluzba();
		}

		DefaultTableModel tableModel = new DefaultTableModel(podaci, zaglavlje);
		sestraTabela.setModel(tableModel);
	}

	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SestraForma sf = new SestraForma(SestraProzor.this, domZdravlja, sestra);
				sf.setVisible(true);
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = sestraTabela.getSelectedRow();
				if (red == -1) {
					JOptionPane.showMessageDialog(null, "Niste selektovali red", "Greska", JOptionPane.WARNING_MESSAGE);
				} else {
					DefaultTableModel model = (DefaultTableModel) sestraTabela.getModel();
					String korIme = model.getValueAt(red, 6).toString();
					MedSestra sestra = domZdravlja.getSestraDao().nadjiSestruPoKorImenu(korIme);
					if (sestra != null) {
						SestraForma sf = new SestraForma(SestraProzor.this, domZdravlja, sestra);
						sf.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranu sestru", "Greska",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = sestraTabela.getSelectedRow();
				if (red == -1) {
					JOptionPane.showMessageDialog(null, "Niste selektovali red", "Greska", JOptionPane.WARNING_MESSAGE);
				} else {
					DefaultTableModel model = (DefaultTableModel) sestraTabela.getModel();
					String korIme = model.getValueAt(red, 6).toString();
					MedSestra sestra = domZdravlja.getSestraDao().nadjiSestruPoKorImenu(korIme);
					if (sestra != null) {
						int izbor = JOptionPane.showConfirmDialog(null,
								"Da li stvarno zelite da obrisete odabranu sestru?", sestra.getIme() + " - Brisanje",
								JOptionPane.YES_NO_OPTION);
						if (izbor == JOptionPane.YES_OPTION) {
							domZdravlja.getSestraDao().izbrisiMedSestru(sestra);
							model.removeRow(red);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranu sestru", "Greska",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
	}

}
