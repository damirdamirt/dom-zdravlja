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
		return "\nPacijent:" + 
				"\nizabLekar: " + izabLekar + 
				"\nknjiz: " + knjiz + 
				"\nime: " + ime + 
				"\nprezime: " + prezime + 
				"\njmbg: " + jmbg + 
				"\nbrTel: " + brTel + 
				"\nuloga: " + uloga + 
				"\nadresa: " + adresa + 
				"\nkorIme: "+ korIme + 
				"\nlozinka: " + lozinka + 
				"\npol: " + pol;
	}


	
}
