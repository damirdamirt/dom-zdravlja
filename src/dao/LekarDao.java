package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Lekar;
import model.Pol;
import model.Sluzba;
import model.UlogaKor;

public class LekarDao {
	
	private ArrayList<Lekar> lekari = new ArrayList<Lekar>();
	
	public void ucitajLekare() {
		
		lekari.clear();
		
		try {
			File file = new File("src/fajlovi/lekari.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String linija;
			while ((linija = reader.readLine()) !=null) {
				String[] podaci = linija.split("\\|");
				String ime = podaci[0];
				String prezime = podaci[1];
				String jmbg = podaci[2];
				String brTel = podaci[3];
				String ulogaKor = podaci[4];
				UlogaKor uloga = UlogaKor.valueOf(ulogaKor);
				String adresa = podaci[5];
				String korIme = podaci[6];
				String lozinka = podaci[7];
				String pol = podaci[8];
				Pol p = Pol.valueOf(pol);
				double plata = Double.parseDouble(podaci[9]);
				String sluzba = podaci[10];
				Sluzba s = Sluzba.valueOf(sluzba);
				String spec = podaci[11];
				Lekar lekar = new Lekar(ime, prezime, jmbg, brTel, uloga, adresa, korIme, lozinka, p, plata, s, spec);
				lekari.add(lekar);
				
			}
			reader.close();
			
		}catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja Fajla.");
			
			e.printStackTrace();
		}
	}
	
	
	public void upisiLekara(Lekar lek) {
		try {
			File file = new File("src/fajlovi/lekari.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			String linija = lek.getIme() + "|" + lek.getPrezime() + "|" + lek.getJmbg() + "|" + lek.getBrTel() +
							"|" + lek.getUloga().toString() + "|" + lek.getAdresa() + "|" + lek.getKorIme() +
							"|" + lek.getLozinka() + "|" + lek.getPol().toString() + "|" + lek.getPlata() +
							"|" + lek.getSluzba().toString() + "|" + lek.getSpec();
			writer.write(linija);
			writer.close();
			
		} catch (IOException e) {
			System.out.println("Greska prilikom upisa lekara");
			e.printStackTrace();
		}
	}
	
	public Lekar nadjiLekaraPoKorImenu(String korIme) {
		
		ucitajLekare();
		
		Lekar trazeniLekar = null; 
		for (Lekar lekar : lekari) {
			if (korIme.equals(lekar.getKorIme())) {
				trazeniLekar = lekar;
				break;
			}
		}
		return trazeniLekar;
		
	}
	

}