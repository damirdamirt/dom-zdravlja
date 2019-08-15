package domZdravlja;

import java.util.ArrayList;

import dao.LekarDao;
import dao.MedSestraDao;
import dao.PacijentDao;
import dao.RacunDao;
import dao.ZdrKnjizicaDao;
import model.Korisnik;
import model.Lekar;

public class DomZdravlja {
	
	private LekarDao lekarDao;
	private MedSestraDao sestraDao;
	private PacijentDao pacijentDao;
	private RacunDao racunDao;
	private ZdrKnjizicaDao knjizicaDao;
	
	private Korisnik logovaniKrisnik;
	
	public DomZdravlja () {
		lekarDao = new LekarDao();
		sestraDao = new MedSestraDao();
		pacijentDao = new PacijentDao();
		racunDao = new RacunDao();
		knjizicaDao = new ZdrKnjizicaDao();
		
	}
	
	
	
	public boolean login(String korisnickoIme, String lozinka) {
		ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
		korisnici.addAll(lekarDao.ucitajLekare());
		korisnici.addAll(sestraDao.ucitajSestre());
		korisnici.addAll(pacijentDao.ucitajPacijente());
		for (Korisnik korisnik : korisnici) {
			if (korisnik.getKorIme().equals(korisnickoIme) && korisnik.getLozinka().equals(lozinka)) {
				logovaniKrisnik = korisnik;
				return true;
			}
		}
		return false;
	}
	
	
	public Korisnik getLogovaniKorisnik() {
		return logovaniKrisnik;
	}
	
	public LekarDao getLekarDao() {
		return lekarDao;
	}
	
	public MedSestraDao getSestraDao() {
		return sestraDao;
	}
	
	public PacijentDao getPacijentDao() {
		return pacijentDao;
	}
	
	public RacunDao getRacunDao() {
		return racunDao;
	}
	
	public ZdrKnjizicaDao getKnjizicaDao() {
		return knjizicaDao;
	}

}
