package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Lekar;
import model.Pacijent;
import model.Pol;
import model.Pregled;
import model.UlogaKor;
import model.ZdrKnjiz;

public class PacijentDao {
	
	private ArrayList<Pacijent> pacijenti = new ArrayList<Pacijent>();
	
	public ArrayList<Pacijent> dajSvePacijenteSaPregledima() {
		ucitajPacijente();
		for (Pacijent pacijent : pacijenti) {
			PregledDao pregDao = new PregledDao();
			ArrayList<Pregled> pregledi = pregDao.nadjiPregledePoKorImenuPacijenta(pacijent.getKorIme());
			pacijent.setPregledi(pregledi);
		}
		return pacijenti;
	}
	
	public void ucitajPacijente() {
		
		pacijenti.clear();
		
		try {
			File file = new File("src/fajlovi/pacijenti.txt"); 
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String linija;
			while ((linija = reader.readLine()) != null) {
				String[] podaci = linija.split("\\|");
				String ime = podaci[0];
				String prezime = podaci[1];
				String jmbg = podaci[2];
				String brTel = podaci[3];
				String uloga = podaci[4];
				UlogaKor ul = UlogaKor.valueOf(uloga);
				String adresa = podaci[5];
				String korIme = podaci[6];
				String lozinka = podaci[7];
				String pol = podaci [8];
				Pol p = Pol.valueOf(pol);
				String korImeLekara = podaci[9];
				LekarDao lek = new LekarDao();
				Lekar lekar = lek.nadjiLekaraPoKorImenu(korImeLekara);
				String brZdrKnj = podaci[10];
				ZdrKnjizicaDao knj = new ZdrKnjizicaDao();
				ZdrKnjiz knjiz = knj.nadjiZdrKnjPoBroju(brZdrKnj);
				Pacijent pacijent = new Pacijent(ime, prezime, jmbg, brTel, ul, adresa, korIme, lozinka, p, lekar, 
												new ArrayList<Pregled>(), knjiz);
				pacijenti.add(pacijent);
			}
			reader.close();
			
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja pacijenata");
			e.printStackTrace();
		}
	}
	
	public void upisiPacijenta(Pacijent pac) {
		try {
			File file = new File("src/fajlovi/pacijenti.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			String linija = pac.getIme() + "|" + pac.getPrezime() + "|" + pac.getJmbg() + "|" + pac.getBrTel() + "|" +
							pac.getUloga().toString() + "|" + pac.getAdresa() + "|" + pac.getKorIme() + "|" +
							pac.getLozinka() + "|" + pac.getPol().toString() + "|" + pac.getIzabLekar().getKorIme() + "|" +
							pac.getKnjiz().getBroj() + "\n";
			writer.write(linija);
			writer.close();
		
		} catch (IOException e) {
			System.out.println("Greska prilikom upisa pacijenta");
			e.printStackTrace();
		}
	}
	
	public Pacijent nadjiPacPoKorImenu(String korIme) {
		ucitajPacijente();
		
		Pacijent trazeniPac = null;
		for (Pacijent pacijent : pacijenti) {
			if (korIme.equals(pacijent.getKorIme())) {
				trazeniPac = pacijent;
				break;
			}
		}
		return trazeniPac;
		
	}
	public ArrayList<Pacijent> getPacijenti() {
		return pacijenti;
	}
}
