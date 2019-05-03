package model;

import java.util.ArrayList;

public class Lekar extends Zaposleni {
	
	private String spec;
	private ArrayList<Pregled> pregledi;

	public Lekar() {
		super();
		this.spec = "";
		this.pregledi = new ArrayList<Pregled>();
		
	}

	public Lekar(String ime, String prezime, String jmbg, String brTel, UlogaKor uloga, String adresa, String korIme,
			String lozinka, Pol pol, double plata, Sluzba sluzba, String spec, ArrayList<Pregled> pregledi) {
		super(ime, prezime, jmbg, brTel, uloga, adresa, korIme, lozinka, pol, plata, sluzba);
		this.spec = spec;
		this.pregledi = pregledi;
	}
	

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public ArrayList<Pregled> getPregledi() {
		return pregledi;
	}

	public void setPregledi(ArrayList<Pregled> pregledi) {
		this.pregledi = pregledi;
	}

	@Override
	public String toString() {
		
		String ispis = "\nLekar:" +
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
				"\npol: " + pol +
				"\npregledi: ";
		if (pregledi.isEmpty())
			ispis += "Nema pregleda";
		for (Pregled pregled : pregledi) {
			ispis +="\n\n\t" + pregled.toString();
		}
		return ispis;
	}

	
}
