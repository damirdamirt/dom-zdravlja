package model;

public class Lekar extends Zaposleni {
	
	private String spec;

	public Lekar() {
		super();
		this.spec = "";
	}

	public Lekar(String spec, String ime, String prezime, String jmbg, String brTel, UlogaKor uloga, String adresa, String korIme,
			String lozinka, Pol pol, double plata, Sluzba sluzba) {
		super(plata, sluzba, ime, prezime, jmbg, brTel, uloga, adresa, korIme, lozinka, pol);
		this.spec = spec;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	@Override
	public String toString() {
		return "\nLekar:" +
				"\nspec: " + spec + 
				"\nplata: " + plata + 
				"\nsluzba: " + sluzba + 
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
