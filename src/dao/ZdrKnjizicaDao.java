package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.ZdrKnjiz;

public class ZdrKnjizicaDao {
	
	private ArrayList<ZdrKnjiz> knjizice = new ArrayList<ZdrKnjiz>();
	
	DateFormat datum = new SimpleDateFormat("dd.MM.yyyy");
	
	public void ucitajZdrKnjizice() {
		
		knjizice.clear();
		
		try {
			File file = new File("src/fajlovi/knjizice.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String linija;
			while ((linija = reader.readLine()) !=null) {
				String[] podaci = linija.split("\\|");
				String broj = podaci[0];
				String datumString = podaci[1];
				Date datumIsteka = datum.parse(datumString); 
				int katOsig = Integer.parseInt(podaci[2]);
				ZdrKnjiz knjizica = new ZdrKnjiz(broj, datumIsteka, katOsig);
				knjizice.add(knjizica);
				
				
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja knjizica.");
			e.printStackTrace();
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
