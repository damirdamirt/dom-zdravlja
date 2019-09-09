package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import domZdravlja.DomZdravlja;
import gui.formeZaPrikaz.LekarProzor;
import gui.formeZaPrikaz.PacijentProzor;
import gui.formeZaPrikaz.PregledProzor;
import gui.formeZaPrikaz.RacunZaPregledProzor;
import gui.formeZaPrikaz.SestraProzor;

public class GlavniProzor extends JFrame {

	private JMenuBar mainMenu;
	private JMenu korisnikMenu;
	private JMenuItem lekaritem;
	private JMenuItem pacijentItem;
	private JMenuItem sestraItem;
	private JMenu pregledMenu;
	private JMenuItem prikaziPregledItem;
	private JMenuItem racunItem;

	private DomZdravlja domZdravlja;

	public GlavniProzor(DomZdravlja domZdravlja) {
		this.domZdravlja = domZdravlja;
		setTitle("Korisnik - " + domZdravlja.getLogovaniKorisnik().getIme() + " "
				+ domZdravlja.getLogovaniKorisnik().getPrezime());
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		initMenu();
		initActions();
	}

	public void initMenu() {
		this.mainMenu = new JMenuBar();
		this.korisnikMenu = new JMenu("Korisnik");
		this.lekaritem = new JMenuItem("Lekar");
		this.pacijentItem = new JMenuItem("Paciijent");
		this.sestraItem = new JMenuItem("Sestra");
		this.pregledMenu = new JMenu("Pregledi");
		this.prikaziPregledItem = new JMenuItem("Prikazi preglede");
		this.racunItem = new JMenuItem("Racun Pregleda");

		switch (domZdravlja.getLogovaniKorisnik().getUloga()) {
		case MED_SESTRA:
			this.korisnikMenu.add(lekaritem);
			this.korisnikMenu.add(pacijentItem);
			this.korisnikMenu.add(sestraItem);
			this.pregledMenu.add(racunItem);
			break;
		case PACIJENT:
			this.korisnikMenu.add(pacijentItem);
			break;
		case LEKAR:
			this.korisnikMenu.add(lekaritem);
		}

		this.pregledMenu.add(prikaziPregledItem);

		this.mainMenu.add(korisnikMenu);
		this.mainMenu.add(pregledMenu);

		setJMenuBar(this.mainMenu);

	}

	public void initActions() {
		pacijentItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PacijentProzor pp = new PacijentProzor(domZdravlja);
				pp.setVisible(true);
			}
		});
		lekaritem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LekarProzor lp = new LekarProzor(domZdravlja);
				lp.setVisible(true);
			}
		});
		sestraItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SestraProzor sp = new SestraProzor(domZdravlja);
				sp.setVisible(true);
			}
		});
		prikaziPregledItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PregledProzor ppr = new PregledProzor(domZdravlja);
				ppr.setVisible(true);
			}
		});
		racunItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RacunZaPregledProzor rzpr = new RacunZaPregledProzor(domZdravlja);
				rzpr.setVisible(true);
			}
		});
	}

}
