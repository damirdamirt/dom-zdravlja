package model;

public abstract class Zaposleni extends Korisnik {
	
	protected double plata;
	protected Sluzba sluzba;
	
	
	public Zaposleni() {
		super();
		
		this.plata = 0;
		this.sluzba = null;
	}


	public Zaposleni(double plata, Sluzba sluzba, String ime, String prezime, String jmbg, String brTel, UlogaKor uloga, String adresa, String korIme,
			String lozinka, Pol pol) {
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
		return "Zaposleni [plata=" + plata + ", sluzba=" + sluzba + ", ime=" + ime + ", prezime=" + prezime + ", jmbg="
				+ jmbg + ", brTel=" + brTel + ", uloga=" + uloga + ", adresa=" + adresa + ", korIme=" + korIme
				+ ", lozinka=" + lozinka + ", pol=" + pol + "]";
	}


	
	
	
	
	

}
