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

import model.ZdrKnjiz;

public class ZdrKnjizicaDao {
	
	private ArrayList<ZdrKnjiz> knjizice = new ArrayList<ZdrKnjiz>();
	DateFormat d = new SimpleDateFormat("dd.MM.yyyy");
	
	
	public void ucitajZdrKnjizice() {
		knjizice.clear();
		
		try {
			File file = new File("src/fajlovi/knjizice.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String linija;
			while ((linija = reader.readLine()) !=null) {
				String[] podaci = linija.split("\\|");
				String broj = podaci[0];
				String datum = podaci[1];
				Date datumIsteka = d.parse(datum); 
				int katOsig = Integer.parseInt(podaci[2]);
				String zKnjizica = podaci[3];
				boolean obrisanaKnj = Boolean.valueOf(zKnjizica);
				ZdrKnjiz knjizica = new ZdrKnjiz(broj, datumIsteka, katOsig, obrisanaKnj);
				knjizice.add(knjizica);
				
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja knjizica.");
			e.printStackTrace();
			
		} catch (ParseException e) {
			System.out.println("Greska prilikom ispisa datuma.");
			e.printStackTrace();
		}
	}
	
	public void upisiKnjizicu(ZdrKnjiz knjiz) {
		
		try {
			File file = new File("src/fajlovi/knjizice.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			String datum = d.format(knjiz.getDatumIsteka());
			String funkcija = String.valueOf(knjiz.isObrisan());
			String linija = knjiz.getBroj() + "|" + datum + "|" + knjiz.getKatOsig() + "|" + funkcija + "\n";
			writer.write(linija);
			writer.close();
			
			
		} catch (IOException e) {
			System.out.println("Greska prilikom upisa u fajl");
			e.printStackTrace();
		}
	}
		
		public void upisiKnjizice(ArrayList<ZdrKnjiz> knjizice) {
			
			try {
				File file = new File("src/fajlovi/knjizice.txt");
				BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
				for (ZdrKnjiz knj : knjizice) {
					String datum = d.format(knj.getDatumIsteka());
					String funkcija = String.valueOf(knj.isObrisan());
					String linija = knj.getBroj() + "|" + datum + "|" + knj.getKatOsig() + "|" + funkcija + "\n";
					writer.write(linija);
			}
				writer.close();
				
			} catch (IOException e) {
				System.out.println("Greska prilikom upisa u fajl");
				e.printStackTrace();
			}
	}
	
	public ZdrKnjiz nadjiZdrKnjPoBroju(String broj) {
		ucitajZdrKnjizice();
		
		ZdrKnjiz trazenaknjiz = null;
		for (ZdrKnjiz zdrKnjiz : knjizice) {
			if (broj.equals(zdrKnjiz.getBroj())) {
				trazenaknjiz = zdrKnjiz;
				break;
			}
		}
		return trazenaknjiz;
	}
	

	public ArrayList<ZdrKnjiz> getKnjizice(){
		return knjizice;
	}
	
	public void izmeniKnjizicu(ZdrKnjiz knjizica) {
		ucitajZdrKnjizice();
		ArrayList<ZdrKnjiz> knjiziceZaFajl = new ArrayList<ZdrKnjiz>();
		for (ZdrKnjiz postojecaKnjizica : knjizice) {
			if (postojecaKnjizica.getBroj().equals(knjizica.getBroj())) {
				knjiziceZaFajl.add(knjizica);
			}else { 
				knjiziceZaFajl.add(postojecaKnjizica);
			}
		}
		upisiKnjizice(knjiziceZaFajl);
	}
	
	public void izbrisiKnjizicu(ZdrKnjiz knjizica ) {
		ucitajZdrKnjizice();
		for (ZdrKnjiz obrisanaKnjizica : knjizice) {
			if(obrisanaKnjizica.getBroj().equals(knjizica.getBroj())) {
				obrisanaKnjizica.setObrisan(true);
			}
		}
		upisiKnjizice(knjizice);
	}
}
