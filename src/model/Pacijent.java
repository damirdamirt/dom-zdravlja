package model;

public class Pacijent extends Korisnik {
	
	private Lekar izabLekar;
	private ZdrKnjiz knjiz;
	
	
	public Pacijent() {
		super();
		this.izabLekar = null;
		this.knjiz = null;
		
	}


	public Pacijent(Lekar izabLekar, ZdrKnjiz knjiz, String ime, String prezime, String jmbg, String brTel, UlogaKor uloga, String adresa, String korIme,
			String lozinka, Pol pol) {
		super(ime, prezime, jmbg, brTel, uloga, adresa, korIme, lozinka, pol);
		this.izabLekar = izabLekar;
		this.knjiz = knjiz;
		
	}


	public Lekar getIzabLekar() {
		return izabLekar;
	}


	public void setIzabLekar(Lekar izabLekar) {
		this.izabLekar = izabLekar;
	}


	public ZdrKnjiz getKnjiz() {
		return knjiz;
	}


	public void setKnjiz(ZdrKnjiz knjiz) {
		this.knjiz = knjiz;
	}


	@Override
	public String toString() {
		return "Pacijent [izabLekar=" + izabLekar + ", knjiz=" + knjiz + ", ime=" + ime + ", prezime=" + prezime
				+ ", jmbg=" + jmbg + ", brTel=" + brTel + ", uloga=" + uloga + ", adresa=" + adresa + ", korIme="
				+ korIme + ", lozinka=" + lozinka + ", pol=" + pol + "]";
	}


	
}
