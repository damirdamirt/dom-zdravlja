package gui.formeZaPrikaz;

import java.awt.BorderLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import domZdravlja.DomZdravlja;
import model.Pregled;
import model.Racun;

public class RacunZaPregledProzor extends JFrame {

	private JTable racunTabela;

	private DomZdravlja domZdravlja;

	public RacunZaPregledProzor(DomZdravlja domZdravlja) {
		this.domZdravlja = domZdravlja;
		setTitle("Racun za pregled");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		punjenjeRacunTabela();
	}

	private void initGUI() {
		racunTabela = new JTable();
		racunTabela.setRowSelectionAllowed(true);
		racunTabela.setColumnSelectionAllowed(false);
		racunTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		racunTabela.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(racunTabela);
		add(scrollPane, BorderLayout.CENTER);
	}

	public void punjenjeRacunTabela() {
		ArrayList<Pregled> pregledi = this.domZdravlja.getPregledDao().ucitajZavrsenePreglede();

		String[] zaglavlje = new String[] { "Pacijent", "Lekar", "ID", "Termin", "Soba", "Opis", "Status", "Iznos" };
		Object[][] podaci = new Object[pregledi.size()][zaglavlje.length];

		for (int i = 0; i < pregledi.size(); i++) {
			Pregled pregled = pregledi.get(i);

			podaci[i][0] = pregled.getPacijent();
			podaci[i][1] = pregled.getLekar();
			podaci[i][2] = pregled.getId();
			DateFormat formater = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			podaci[i][3] = formater.format(pregled.getTermin());
			podaci[i][4] = pregled.getSoba();
			podaci[i][5] = pregled.getOpis();
			podaci[i][6] = pregled.getStatus();
			if (pregled.getPacijent() != null) {
				podaci[i][7] = pregled.getPacijent().getKnjiz().getKatOsig().getCena();
			}
		}

		DefaultTableModel tableModel = new DefaultTableModel(podaci, zaglavlje);
		racunTabela.setModel(tableModel);
	}
}
