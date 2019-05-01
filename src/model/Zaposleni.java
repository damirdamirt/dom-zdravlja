package model;

public abstract class Zaposleni extends Korisnik {
	
	protected double plata;
	protected Sluzba sluzba;
	
	
	public Zaposleni() {
		super();
		
		this.plata = 0;
		this.sluzba = null;
	}


	public Zaposleni(String ime, String prezime, String jmbg, String brTel, UlogaKor uloga, String adresa, String korIme,
			String lozinka, Pol pol, double plata, Sluzba sluzba) {
		super(ime, prezime, jmbg, brTel, uloga,adresa, korIme, lozinka, pol);
		this.plata = plata;
		this.sluzba = sluzba;
	}


	public double getPlata() {
		return plata;
	}


	public void setPlata(double plata) {
		this.plata = plata;
	}


	public Sluzba getSluzba() {
		return sluzba;
	}


	public void setSluzba(Sluzba sluzba) {
		this.sluzba = sluzba;
	}


	@Override
	public String toString() {
		return "\nZaposleni:" +
				"\nplata: " + plata + 
				"\nsluzba: " + sluzba + 
				"\nime: " + ime + 
				"\nprezime: " + prezime + 
				"\njmbg: "+ jmbg + 
				"\nbrTel: " + brTel + 
				"\nuloga: " + uloga + 
				"\nadresa: " + adresa + 
				"\nkorIme: " + korIme + 
				"\nlozinka: " + lozinka + 
				"\npol: " + pol;
	}


	
	
	
	
	
	

}
