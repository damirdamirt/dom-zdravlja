package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.MedSestra;
import model.Pol;
import model.Sluzba;
import model.UlogaKor;

public class MedSestraDao {
	
	private ArrayList<MedSestra> sestre = new ArrayList<MedSestra>();
	
	public void ucitajSestre() {
		
		sestre.clear();
		
		try {
			File file = new File ("src/fajlovi/sestre.txt");
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
				MedSestra sestra = new MedSestra(ime, prezime, jmbg, brTel, uloga, adresa, korIme, lozinka, p, plata, s);
				sestre.add(sestra);
				System.out.println(sestre);
			}
			reader.close();
			
		}catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja fajla");
			
			e.printStackTrace();
		}

	}
	
	public void upisiMedSestru(MedSestra ms) {
		
		try {
			File file = new File("src/fajlovi/sestre.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			String linija = ms.getIme() + "|" + ms.getPrezime() + "|" + ms.getJmbg() + "|" + ms.getBrTel() +
					"|" + ms.getUloga().toString() + "|" + ms.getAdresa() + "|" + ms.getKorIme() + "|" + ms.getLozinka() + "|" + 
					 ms.getPol().toString() + "|" + ms.getPlata() + "|" + ms.getSluzba().toString();
			writer.write(linija);
			writer.close();
		
		
		
		} catch (IOException e) {
			System.out.println("Greska prilikom upisa fajla");
			e.printStackTrace();
		}
		
		
	}

}