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
import java.util.UUID;

import model.Lekar;
import model.Pacijent;
import model.Pregled;
import model.StatusPreg;

public class PregledDao {

	private ArrayList<Pregled> pregledi = new ArrayList<Pregled>();
	DateFormat datum = new SimpleDateFormat("dd.MM.yyyy HH:mm");

	public ArrayList<Pregled> ucitajPreglede() {
		pregledi.clear();
		try {
			File file = new File("src/fajlovi/pregledi.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String linija;
			while ((linija = reader.readLine()) != null) {
				String[] podaci = linija.split("\\|");
				String id = podaci[0];
				String korImePac = podaci[1];
				PacijentDao pac = new PacijentDao();
				Pacijent pacijent = pac.nadjiPacPoKorImenu(korImePac);
				String korImeLekara = podaci[2];
				LekarDao lek = new LekarDao();
				Lekar lekar = lek.nadjiLekaraPoKorImenu(korImeLekara);
				String termin = podaci[3];
				Date d;
				if (termin.equals("")) {
					d = null;
				} else {
					d = datum.parse(termin);
				}
				String soba = podaci[4];
				String opis = podaci[5];
				String status = podaci[6];
				StatusPreg s = StatusPreg.valueOf(status);
				Pregled pregled = new Pregled(pacijent, lekar, d, soba, opis, s);
				pregled.setId(id);
				pregledi.add(pregled);
			}
			reader.close();

		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja pregleda");
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("Greska prilikom parsiranja datuma");
			e.printStackTrace();

			return null;
		}
		return pregledi;

	}

	public ArrayList<Pregled> ucitajZavrsenePreglede() {
		ArrayList<Pregled> pregledi = ucitajPreglede();
		ArrayList<Pregled> zavrseniPregledi = new ArrayList<Pregled>();
		for (Pregled pregled : pregledi) {
			if (pregled.getStatus() == StatusPreg.ZAVRSEN) {
				zavrseniPregledi.add(pregled);
			}
		}
		return zavrseniPregledi;
	}

	public void upisiPregled(Pregled preg) {
		try {
			File file = new File("src/fajlovi/pregledi.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			if (preg.getId() == null) {
				String id = UUID.randomUUID().toString();
				preg.setId(id);
			}
			String d;
			if (preg.getTermin() == null) {
				d = "";
			} else {
				d = datum.format(preg.getTermin());
			}
			String linija = preg.getId() + "|" + preg.getPacijent().getKorIme() + "|" + preg.getLekar().getKorIme()
					+ "|" + d + "|" + preg.getSoba() + "|" + preg.getOpis() + "|" + preg.getStatus().toString() + "|"
					+ "\n";
			writer.write(linija);
			writer.close();

		} catch (Exception e) {
			System.out.println("Greska prilikom upisa pregleda");
			e.printStackTrace();
		}
	}

	public void upisiPreglede(ArrayList<Pregled> pregledi) {
		try {
			File file = new File("src/fajlovi/pregledi.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
			for (Pregled pre : pregledi) {
				if (pre.getId() == null) {
					String id = UUID.randomUUID().toString();
					pre.setId(id);
				}
				String datumPregleda;
				if (pre.getTermin() == null) {
					datumPregleda = "";
				} else {
					datumPregleda = datum.format(pre.getTermin());
				}
				String korisnickoImePacijenta;
				if (pre.getPacijent() == null) {
					korisnickoImePacijenta = "";
				} else {
					korisnickoImePacijenta = pre.getPacijent().getKorIme();
				}

				String korisnickoImeLekara;
				if (pre.getLekar() == null) {
					korisnickoImeLekara = "";
				} else {
					korisnickoImeLekara = pre.getLekar().getKorIme();
				}

				String linija = pre.getId() + "|" + korisnickoImePacijenta + "|" + korisnickoImeLekara + "|"
						+ datumPregleda + "|" + pre.getSoba() + "|" + pre.getOpis() + "|" + pre.getStatus().toString()
						+ "|" + "\n";
				writer.write(linija);
			}
			writer.close();

		} catch (Exception e) {
			System.out.println("Greska prilikom upisa pregleda");
			e.printStackTrace();
		}
	}

	public Pregled nadjiPregPoId(String id) {
		ucitajPreglede();

		Pregled trazeniPregled = null;
		for (Pregled pregled : pregledi) {
			if (id.equals(pregled.getId())) {
				trazeniPregled = pregled;
				break;
			}
		}
		return trazeniPregled;
	}

	public ArrayList<Pregled> nadjiPregledePoKorImenuLekara(String korImeLekara) {
		ucitajPreglede();

		ArrayList<Pregled> trazeniPreglediLekara = new ArrayList<Pregled>();
		for (Pregled pregled : pregledi) {
			if (pregled.getLekar() != null && korImeLekara.equals(pregled.getLekar().getKorIme())) {
				trazeniPreglediLekara.add(pregled);
			}
		}
		return trazeniPreglediLekara;
	}

	public ArrayList<Pregled> getPregledi() {
		return pregledi;
	}

	public ArrayList<Pregled> nadjiPregledePoKorImenuPacijenta(String korImePacijenta) {
		ucitajPreglede();

		ArrayList<Pregled> trazeniPreglediPacijenta = new ArrayList<Pregled>();
		for (Pregled pregled : pregledi) {
			if (korImePacijenta.equals(pregled.getPacijent().getKorIme())) {
				trazeniPreglediPacijenta.add(pregled);
			}
		}
		return trazeniPreglediPacijenta;
	}

	public void izmeniPregled(Pregled pregled) {
		ucitajPreglede();
		ArrayList<Pregled> preglediZaFajl = new ArrayList<Pregled>();
		for (Pregled postojeciPregled : pregledi) {
			if (postojeciPregled.getId().equals(pregled.getId())) {
				preglediZaFajl.add(pregled);
			} else {
				preglediZaFajl.add(postojeciPregled);
			}
		}
		upisiPreglede(preglediZaFajl);
	}

	public void izbrisiPregled(Pregled obrisaniPregled) {
		ucitajPreglede();
		ArrayList<Pregled> preostaliPregledi = new ArrayList<Pregled>();
		for (Pregled pregled : pregledi) {
			if (!obrisaniPregled.getId().equals(pregled.getId())) {
				preostaliPregledi.add(pregled);
			}
		}
		upisiPreglede(preostaliPregledi);
	}
}
