package model;

public class MedSestra extends Zaposleni {
	
	public MedSestra() {
		super();
	}

	public MedSestra(String ime, String prezime, String jmbg, String brTel, UlogaKor uloga,
			String adresa, String korIme, String lozinka, Pol pol, double plata, Sluzba sluzba) {
		super(ime, prezime, jmbg, brTel, uloga, adresa, korIme, lozinka, pol, plata, sluzba);
		
	}

	@Override
	public String toString() {
		return "MedSestra:" + 
				"\nplata: " + plata + 
				"\nsluzba: " + sluzba + 
				"\nime: "  +  ime + 
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
