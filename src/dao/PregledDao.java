package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jdk.jfr.events.FileWriteEvent;
import model.Lekar;
import model.Pacijent;
import model.Pregled;
import model.StatusPreg;

public class PregledDao {
	
	private ArrayList<Pregled> pregledi = new ArrayList<Pregled>();
	DateFormat datum = new SimpleDateFormat("dd.MM.yyyy HH:mm");
	
	public void ucitajPreglede() {
		pregledi.clear();
		try {
			File file = new File("src/fajlovi/pregledi.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String linija;
			while ((linija = reader.readLine()) !=null) {
				String[] podaci = linija.split("\\|");
				String korImePac = podaci[0];
				PacijentDao pac = new PacijentDao();
				Pacijent pacijent = pac.nadjiPacPoKorImenu(korImePac);
				String korImeLekara = podaci[1];
				LekarDao lek = new LekarDao();
				Lekar lekar = lek.nadjiLekaraPoKorImenu(korImeLekara);
				String termin = podaci[2];
				Date d = datum.parse(termin);
				String soba = podaci[3];
				String opis = podaci[4];
				String status = podaci[5];
				StatusPreg s = StatusPreg.valueOf(status);
				Pregled pregled = new Pregled(pacijent, lekar, d, soba, opis, s);
				pregledi.add(pregled);
		}
		reader.close();	
			
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja pregleda");
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("Greska prilikom parsiranja datuma");
			e.printStackTrace();
		}
		
	}
	
	public void upisiPregled(Pregled preg) {
		try {
			File file = new File("src/fajlovi/pregledi");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			String d = datum.format(preg.getTermin()); 
			String linija = preg.getPacijent().getKorIme() + "|" + preg.getLekar().getKorIme() + "|" +
							d + "|" + preg.getSoba() + "|" + preg.getOpis() + "|" + preg.getStatus().toString();
			writer.write(linija);
			writer.close();
			
		} catch (Exception e) {
			System.out.println("Greska prilikom upisa pregleda");
			e.printStackTrace();
		}
	}
}
