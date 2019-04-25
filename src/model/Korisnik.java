package model;

public abstract class Korisnik {
	
	protected String ime;
	protected String prezime;
	protected String jmbg;
	protected String brTel;
	protected UlogaKor uloga;
	protected String adresa;
	protected String korIme;
	protected String lozinka;
	protected Pol pol;
	
	
	public Korisnik() {
		
		this.ime = "";
		this.prezime = "";
		this.jmbg = "";
		this.brTel = "";
		this.uloga = null;
		this.adresa = "";
		this.korIme = "";
		this.lozinka = "";
		this.pol = null;
		
	
		
	}


	public Korisnik(String ime, String prezime, String jmbg, String brTel, UlogaKor uloga, String adresa, String korIme,
			String lozinka, Pol pol) {
		
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.brTel = brTel;
		this.uloga = uloga;
		this.adresa = adresa;
		this.korIme = korIme;
		this.lozinka = lozinka;
		this.pol = pol;
	}


	public String getIme() {
		return ime;
	}


	public void setIme(String ime) {
		this.ime = ime;
	}


	public String getPrezime() {
		return prezime;
	}


	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}


	public String getJmbg() {
		return jmbg;
	}


	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}


	public String getBrTel() {
		return brTel;
	}


	public void setBrTel(String brTel) {
		this.brTel = brTel;
	}


	public UlogaKor getUloga() {
		return uloga;
	}


	public void setUloga(UlogaKor uloga) {
		this.uloga = uloga;
	}


	public String getAdresa() {
		return adresa;
	}


	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}


	public String getKorIme() {
		return korIme;
	}


	public void setKorIme(String korIme) {
		this.korIme = korIme;
	}


	public String getLozinka() {
		return lozinka;
	}


	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}


	public Pol getPol() {
		return pol;
	}


	public void setPol(Pol pol) {
		this.pol = pol;
	}


	@Override
	public String toString() {
		return "\nkorisnik:" + 
				"\nime: " + ime + 
				"\nprezime: " + prezime + 
				"\njmbg: " + jmbg + 
				"\nbrTel: " + brTel + 
				"\nuloga: " + uloga + 
				"\nadresa: " + adresa + 
				"\nkorIme: " + korIme + 
				"\nlozinka: " + lozinka + 
				"\npol: " + pol;
	}
	
	
	
	
	
	
	
}
