package test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dao.LekarDao;
import dao.MedSestraDao;
import dao.PacijentDao;
import dao.PregledDao;
import dao.RacunDao;
import dao.ZdrKnjizicaDao;
import model.Lekar;
import model.MedSestra;
import model.Pacijent;
import model.Pol;
import model.Pregled;
import model.Racun;
import model.Sluzba;
import model.StatusPreg;
import model.UlogaKor;
import model.ZdrKnjiz;

//public class TestKlasa {
//
//	public static void main(String[] args) throws ParseException {
//		
//		
//		//Rad sa knjizicama
//		DateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
//		Date datumIsteka = formater.parse("13.05.2020");
//		ZdrKnjiz knjizica = new ZdrKnjiz("10", datumIsteka, 3, false);
//		ZdrKnjiz knjizica1 = new ZdrKnjiz("5", datumIsteka, 2, false);
//		ZdrKnjizicaDao knjiz = new ZdrKnjizicaDao();
//		knjiz.upisiKnjizicu(knjizica);
//		knjiz.upisiKnjizicu(knjizica1);
//		knjiz.ucitajZdrKnjizice();
//		ArrayList<ZdrKnjiz> knjizice = knjiz.getKnjizice();
//		System.out.print("\n------------- KNJIZICE -------------");
//		for (ZdrKnjiz zdrKnjiz : knjizice) {
//			System.out.println(zdrKnjiz);
//		}
//		System.out.println("------------------------------------------");
//		ZdrKnjiz izmenjenaKnjizica = knjizice.get(0);
//		izmenjenaKnjizica.setKatOsig(1);
//		knjiz.izmeniKnjizicu(izmenjenaKnjizica);
//		System.out.println("---------------- Knjizice posle izmene ----------------");
//		knjiz.ucitajZdrKnjizice();
//		ArrayList<ZdrKnjiz> knjizicePosleIzmene = knjiz.getKnjizice();
//		for (ZdrKnjiz zdrKnjiz : knjizicePosleIzmene) {
//			System.out.println(zdrKnjiz);
//		}
//		System.out.println("---------------------------------------");
//		
//		System.out.println("-------------- Prikaz knjizica posle brisanja--------------");
//		knjiz.izbrisiKnjizicu(izmenjenaKnjizica);
//		knjiz.ucitajZdrKnjizice();
//		ArrayList<ZdrKnjiz> knjizicePosleBrisanja = knjiz.getKnjizice();
//		for (ZdrKnjiz zdrKnjiz : knjizicePosleBrisanja) {
//			System.out.println(zdrKnjiz);
//			
//		}
//		
//		
//		//Rad sa sestrama
//		MedSestra sestra = new MedSestra("Jovana", "Jokanovic", "237599889775", "069/8227654", 
//										UlogaKor.MED_SESTRA, "Ilije Bircanina 10", 
//										"JovanaJ", "jovana456", Pol.ZENSKI, 50000, Sluzba.SLUZBA_OP_MED);
//		MedSestra sestra1 = new MedSestra("Maja", "Mihajlovic", "272765554344", "061/5651545", 
//										UlogaKor.MED_SESTRA, "Gavrila Principa6", 
//										"Maja", "maja555", Pol.ZENSKI, 60000, Sluzba.SLUZBA_OP_MED);
//		MedSestraDao ses = new MedSestraDao();
//		ses.upisiMedSestru(sestra);
//		ses.upisiMedSestru(sestra1);
//		ses.ucitajSestre();
//		
//		ArrayList<MedSestra> sestre = ses.getSestre();
//		System.out.println("\n------------- SESTRE -------------");
//		for (MedSestra medSestra : sestre) {
//			System.out.println(medSestra);
//		}
//		System.out.println("------------------------------------------");
//		
//		MedSestra izmenjenaSestra = sestre.get(0);
//		izmenjenaSestra.setIme("Milena");
//		ses.izmeniMedSestru(izmenjenaSestra);
//		System.out.println("-------------- Sestra posle izmene ---------------");
//		ses.ucitajSestre();
//		ArrayList<MedSestra> sestraPosleIzmene = ses.getSestre();
//		for (MedSestra medSestra : sestraPosleIzmene) {
//			System.out.println(medSestra);
//		}
//		System.out.println("------------------------------");
//		
//		
//		
//		ses.izbrisiMedSestru(sestra);
//		ses.ucitajSestre();
//		sestre = ses.getSestre();
//		System.out.println("\n------------- Sestre nakon brisanja -------------");
//		for (MedSestra medSestra : sestre) {
//			System.out.println(medSestra);
//		}
//		System.out.println("---------------------------");
//		
//		
//		
//		//Rad sa lekarima
//		ArrayList<Pregled> pregledi = new ArrayList<Pregled>();
//		Lekar lekar = new Lekar("Milos", "Jovanovic", "230992000452354", "064/2342456", UlogaKor.LEKAR, 
//								"Zmaj Jovina 46", "MilosJ", "MikiMiki", Pol.MUSKI, 90000, Sluzba.SLUZBA_OP_MED, 
//								"Pedijatar", pregledi, false);
//		Lekar lekar1 = new Lekar("Ivana", "Stupar", "13242498876288", "063/5465454", UlogaKor.LEKAR, 
//				"Bulevar Despota", "ivancis", "ikaika", Pol.ZENSKI, 100000, Sluzba.STOM_SLUZBA, 
//				"Stomatolog", pregledi, false);
//		LekarDao doca = new LekarDao();
//		doca.upisiLekara(lekar);
//		doca.upisiLekara(lekar1);
//		
//		
//		
//		
//		
//		//Rad sa pacijentima
//		Pacijent pacijent = new Pacijent("Nikola", "Grgic", "25099884476532", "061/2323681", 
//										UlogaKor.PACIJENT, "Sutjeska 15", "NikolaG", "nikonikola", Pol.MUSKI, 
//										lekar, pregledi, knjizica, false);
//		Pacijent pacijent1 = new Pacijent("Maja", "Nikolic", "2752293224466", "064/6465321", 
//										 UlogaKor.PACIJENT, "Stevana Mokranjca 3", "majamajce", "makimaki", Pol.ZENSKI, 
//										 lekar, pregledi, knjizica1, false);
//		PacijentDao pac = new PacijentDao();
//		pac.upisiPacijenta(pacijent);
//		pac.upisiPacijenta(pacijent1);
//		
//		
//		//Rad sa pregledima
//		DateFormat formater1 = new SimpleDateFormat("dd.MM.yyyy HH:mm");
//		Date termin = formater1.parse("13.05.2020 10:30"); 
//		Pregled pregled = new Pregled(pacijent, lekar, termin, "5a", "Bolovi u levom kolenu", 
//									StatusPreg.ZATRAZEN, false);
//		Pregled pregled1 = new Pregled(pacijent1, lekar1, termin, "10b", "Cesta vrtoglavica", 
//									StatusPreg.ZATRAZEN, false);
//		PregledDao preg = new PregledDao();
//		preg.upisiPregled(pregled);
//		preg.upisiPregled(pregled1);
//		pregledi.add(pregled);
//		pregledi.add(pregled1);
//		
//		preg.ucitajPreglede();
//		pregledi = preg.getPregledi();
//		System.out.println("---------- PREGLEDI ----------");
//		for (Pregled pregl : pregledi) {
//			System.out.println(pregl);
//		}
//		System.out.println("-----------------------------------");
//		
//		Pregled izmenjenPregled = pregledi.get(0);
//		izmenjenPregled.setOpis("Trne leva ruka");
//		preg.izmeniPregled(izmenjenPregled);
//		System.out.println("---------- Pregledi nakon izmene ------------");
//		preg.ucitajPreglede();
//		ArrayList<Pregled> preglediPosleIzmene = preg.getPregledi();
//		for (Pregled pregled2 : preglediPosleIzmene) {
//			System.out.println(pregled2);
//		}
//		System.out.println("--------------------------------");
//		
//		System.out.println("------------ Pregledi nakon brisanja -------------------");
//		preg.izbrisiPregled(izmenjenPregled);
//		preg.ucitajPreglede();
//		ArrayList<Pregled> preglediPosleBrisanja = preg.getPregledi();
//		for (Pregled pregled2 : preglediPosleBrisanja) {
//			System.out.println(pregled2);
//		}
//		
//		doca.ucitajLekare();
//		ArrayList<Lekar> lekari = doca.dajSveLekareSaPregledima();
//		System.out.println("\n------------- LEKARI -------------");
//		for (Lekar leka : lekari) {
//			System.out.println(leka);
//		}
//		System.out.println("------------------------------------------");
//		
//		Lekar izmenjenLekar = lekari.get(0);
//		izmenjenLekar.setAdresa("ive Lole Ribara 5");
//		doca.izmeniLekara(izmenjenLekar);
//		System.out.println("--------- Lekari nakon izmene ----------");
//		doca.ucitajLekare();
//		ArrayList<Lekar> lekariPosleIzmene = doca.dajSveLekareSaPregledima();
//		for (Lekar leka : lekariPosleIzmene) {
//			System.out.println(leka);
//		}
//		System.out.println("------------------------------------------");
//		
//		System.out.println("--------- Prikaz lekara posle brisanja ------------");
//		doca.izbrisiLekara(izmenjenLekar);
//		doca.ucitajLekare();
//		ArrayList<Lekar> lekariPosleBrisanja = doca.getLekari();
//		for (Lekar leka : lekariPosleBrisanja) {
//			System.out.println(leka);
//		}
//		System.out.println("------------------------------------------");
//		
//		
//		
//		pac.ucitajPacijente();
//		ArrayList<Pacijent> pacijenti = pac.dajSvePacijenteSaPregledima();
//		System.out.println("\n------------- PACIJENTI -------------");
//		for (Pacijent pacij : pacijenti) {
//			System.out.println(pacij);
//		}
//		System.out.println("------------------------------------------");
//		
//		Pacijent izmenjenPacijent = pacijenti.get(0);
//		izmenjenPacijent.setAdresa("Fruskogorska 15");
//		pac.izmeniPacijenta(izmenjenPacijent);
//		System.out.println("------------- Pacijent posle izmene --------------");
//		pac.ucitajPacijente();
//		ArrayList<Pacijent> pacijentiPosleIzmene = pac.dajSvePacijenteSaPregledima();
//		for (Pacijent pacij : pacijentiPosleIzmene) {
//			System.out.println(pacij);
//		}
//		System.out.println("---------------------------");
//		
//		System.out.println("------------- Prikaz Pacijenata posle brisanja ----------------");
//		pac.izbrisiPacijenta(izmenjenPacijent);
//		pac.ucitajPacijente();
//		ArrayList<Pacijent> pacijentiPosleBrisanja = pac.getPacijenti();
//		for (Pacijent packe : pacijentiPosleBrisanja) {
//			System.out.println(packe);
//		}
//	
//		
//		//Rad sa racunima
//		Racun racun = new Racun(pregled, 200);
//		RacunDao rac = new RacunDao();
//		rac.upisiRacun(racun);
//		rac.ucitajRacune();
//		ArrayList<Racun> racuni = rac.getRacuni();
//		System.out.print("\n------------- RACUNI -------------");
//		for (Racun racu : racuni) {
//			System.out.println(racu);
//		}
//		System.out.println("------------------------------------------");
//		
//		Racun izmenjenRacun = racuni.get(0);
//		izmenjenRacun.setIznos(400);
//		rac.izmeniRacun(izmenjenRacun);
//		System.out.println("--------- Racun posle izmene ------------");
//		rac.ucitajRacune();
//		ArrayList<Racun> racuniPosleIzmene = rac.getRacuni();
//		for (Racun ra : racuniPosleIzmene) {
//			System.out.println(ra);
//		}
//		System.out.println("----------------------------");
//		
//		rac.izbrisiRacun(racun);
//		rac.ucitajRacune();
//		racuni = rac.getRacuni();
//		System.out.println("------------ Racuni posle brisanja --------------");
//		for (Racun ra : racuniPosleIzmene) {
//			System.out.println(ra);
//		}
//		
//		
//	}	
//}
