package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import jdk.jfr.events.FileWriteEvent;
import model.Pregled;
import model.Racun;

public class RacunDao {
	
	private ArrayList<Racun> racuni = new ArrayList<Racun>();
	
	public void ucitajRacune() {
		racuni.clear();
		
		try {
			File file = new File("src/fajlovi/racuni.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String linija;
			while ((linija = reader.readLine()) !=null) {
				String[] podaci = linija.split("\\|");
				String id = podaci[0];
				PregledDao preg = new PregledDao();
				Pregled pregled = preg.nadjiPregPoId(id);
				String iznosS= podaci[1];
				double iznos = Double.parseDouble(iznosS);
				Racun racun = new Racun(pregled, iznos);
				racuni.add(racun);
			}
			reader.close();
			
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja racuna");
			e.printStackTrace();
		}
		
	}
	
	public void upisiRacun(Racun rac) {
		try {
			File file = new File("src/fajlovi/racuni.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			String linija = rac.getPregled().getId() + "|" + rac.getIznos() + "\n";
			writer.write(linija);
			writer.close();
			
		} catch (IOException e) {
			System.out.println("Greska prilikom upisa racuna");
			e.printStackTrace();
		}
	}
	
	public ArrayList<Racun> getRacuni() {
		return racuni;
	}
}
