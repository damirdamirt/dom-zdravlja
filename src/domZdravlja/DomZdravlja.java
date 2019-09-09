package domZdravlja;

import java.util.ArrayList;

import dao.LekarDao;
import dao.MedSestraDao;
import dao.PacijentDao;
import dao.PregledDao;
import dao.ZdrKnjizicaDao;
import model.Korisnik;
import model.Lekar;

public class DomZdravlja {

	private LekarDao lekarDao;
	private MedSestraDao sestraDao;
	private PacijentDao pacijentDao;
	private ZdrKnjizicaDao knjizicaDao;
	private PregledDao pregledDao;

	private ArrayList<Korisnik> korisnici;
	private Korisnik logovaniKrisnik;

	public DomZdravlja() {
		lekarDao = new LekarDao();
		sestraDao = new MedSestraDao();
		pacijentDao = new PacijentDao();
		knjizicaDao = new ZdrKnjizicaDao();
		pregledDao = new PregledDao();

	}

	public boolean login(String korisnickoIme, String lozinka) {
		korisnici = new ArrayList<Korisnik>();
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

	public ArrayList<Korisnik> getKorisnici() {
		return korisnici;
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

	public ZdrKnjizicaDao getKnjizicaDao() {
		return knjizicaDao;
	}

	public PregledDao getPregledDao() {
		return pregledDao;
	}

}
