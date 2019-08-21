package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
	
	public void upisiRacune(ArrayList<Racun> racuni) {
		try {
			File file = new File("src/fajlovi/racuni.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
			for (Racun rac : racuni) {
				String linija = rac.getPregled().getId() + "|" + rac.getIznos() + "\n";
				writer.write(linija);
			}
			writer.close();
			
		} catch (IOException e) {
			System.out.println("Greska prilikom upisa racuna");
			e.printStackTrace();
		}
	}
	public ArrayList<Racun> getRacuni() {
		return racuni;
	}
	
	public void izmeniRacun(Racun racun) {
		ucitajRacune();
		ArrayList<Racun> racuniZaFajl = new ArrayList<Racun>();
		for (Racun postojeciRacun : racuni) {
			if (postojeciRacun.getPregled().getId().equals(racun.getPregled().getId())) {
				racuniZaFajl.add(racun);
			}else {
				racuniZaFajl.add(postojeciRacun);
			}
		}
		upisiRacune(racuniZaFajl);
	}
	public void izbrisiRacun(Racun obrisaniRacun) {
		ucitajRacune();
		ArrayList<Racun> preostaliRacuni = new ArrayList<Racun>();
		for (Racun racun : racuni) {
			if (!obrisaniRacun.getPregled().getId().equals(racun.getPregled().getId())) {
				preostaliRacuni.add(racun);
			}
		}
		upisiRacune(preostaliRacuni);
	}
}
