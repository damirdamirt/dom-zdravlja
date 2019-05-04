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

public class TestKlasa {

	public static void main(String[] args) throws ParseException {
		
		
		//Rad sa knjizicama
		DateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
		Date datumIsteka = formater.parse("13.05.2020");
		ZdrKnjiz knjizica = new ZdrKnjiz("10", datumIsteka, 3);
		ZdrKnjizicaDao knjiz = new ZdrKnjizicaDao();
		knjiz.upisiKnjizicu(knjizica);
		knjiz.ucitajZdrKnjizice();
		ArrayList<ZdrKnjiz> knjizice = knjiz.getKnjizice();
		System.out.print("\n------------- KNJIZICE -------------");
		for (ZdrKnjiz zdrKnjiz : knjizice) {
			System.out.println(zdrKnjiz);
		}
		System.out.println("------------------------------------------");
		
		
		
		//Rad sa sestrama
		MedSestra sestra = new MedSestra("Jovana", "Nikolc", "237599889775", "069/8227654", 
										UlogaKor.MED_SESTRA, "Ilije Bircanina 10", 
										"JovanaN", "Jovana456", Pol.ZENSKI, 50000, Sluzba.SLUZBA_OP_MED);
		MedSestraDao ses = new MedSestraDao();
		ses.upisiMedSestru(sestra);
		ses.ucitajSestre();
		ArrayList<MedSestra> sestre = ses.getSestre();
		System.out.println("\n------------- SESTRE -------------");
		for (MedSestra medSestra : sestre) {
			System.out.println(medSestra);
		}
		System.out.println("------------------------------------------");
		
		
		
		//Rad sa lekarima
		ArrayList<Pregled> pregledi = new ArrayList<Pregled>();
		Lekar lekar = new Lekar("Milos", "Jovanovic", "230992000452354", "064/2342456", UlogaKor.LEKAR, 
								"Zmaj Jovina 46", "MilosJ", "MikiMiki", Pol.MUSKI, 90000, Sluzba.SLUZBA_OP_MED, 
								"Pedijatar", pregledi);
		LekarDao doca = new LekarDao();
		doca.upisiLekara(lekar);
		
		
		
		
		//Rad sa pacijentima
		Pacijent pacijent = new Pacijent("Nikola", "Grgic", "25099884476532", "061/2323681", 
										UlogaKor.PACIJENT, "Sutjeska 15", "NikolaG", "nikonikola", Pol.MUSKI, 
										lekar, pregledi, knjizica);
		PacijentDao pac = new PacijentDao();
		pac.upisiPacijenta(pacijent);
		
		
		
		//Rad sa pregledima
		DateFormat formater1 = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		Date termin = formater1.parse("13.05.2020 10:30");
		Pregled pregled = new Pregled(pacijent, lekar, termin, "5a", "Bolovi u levom kolenu", 
									StatusPreg.ZATRAZEN);
		PregledDao preg = new PregledDao();
		preg.upisiPregled(pregled);
		pregledi.add(pregled);
		
		doca.ucitajLekare();
		ArrayList<Lekar> lekari = doca.dajSveLekareSaPregledima();
		System.out.println("\n------------- LEKARI -------------");
		for (Lekar leka : lekari) {
			System.out.println(leka);
		}
		System.out.println("------------------------------------------");
		
		pac.ucitajPacijente();
		ArrayList<Pacijent> pacijenti = pac.dajSvePacijenteSaPregledima();
		System.out.println("\n------------- PACIJENTI -------------");
		for (Pacijent pacij : pacijenti) {
			System.out.println(pacij);
		}
		System.out.println("------------------------------------------");
	
		
		//Rad sa racunima
		Racun racun = new Racun(pregled, 200);
		RacunDao rac = new RacunDao();
		rac.upisiRacun(racun);
		rac.ucitajRacune();
		ArrayList<Racun> racuni = rac.getRacuni();
		System.out.print("\n------------- RACUNI -------------");
		for (Racun racu : racuni) {
			System.out.println(racu);
		}
		System.out.println("------------------------------------------");
	}	
}
