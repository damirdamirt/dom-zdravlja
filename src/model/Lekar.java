package model;

import java.util.ArrayList;

public class Lekar extends Zaposleni {
	
	private String spec;
	private ArrayList<Pregled> pregledi;
	private boolean obrisan;

	public Lekar() {
		super();
		this.spec = "";
		this.pregledi = new ArrayList<Pregled>();
		this.obrisan = false;
		
	}

	public Lekar(String ime, String prezime, String jmbg, String brTel, UlogaKor uloga, String adresa, String korIme,
			String lozinka, Pol pol, double plata, Sluzba sluzba, String spec, ArrayList<Pregled> pregledi, boolean obrisan) {
		super(ime, prezime, jmbg, brTel, uloga, adresa, korIme, lozinka, pol, plata, sluzba);
		this.spec = spec;
		this.pregledi = pregledi;
		this.obrisan = obrisan;
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
	
	

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	@Override
	public String toString() {
		String funkcijaLekara;
		if(this.obrisan == true) {
			funkcijaLekara = "Van radnog odnosa";
		}
		else {
			funkcijaLekara = "U radnom odnosu";
		}
		
		String ispis = "Lekar:" +
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
				"\nfunkcijaLekara: " + funkcijaLekara +
				"\nPregledi: ";
		if (pregledi.isEmpty())
			ispis += "Nema pregleda";
		for (Pregled pregled : pregledi) {
			ispis +="\n" + pregled.toString() + "\n";
		}
		return ispis;
	}

	
}
