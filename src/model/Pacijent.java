package model;

import java.util.ArrayList;

public class Pacijent extends Korisnik {
	
	private Lekar izabLekar;
	private ZdrKnjiz knjiz;
	private ArrayList<Pregled> pregledi;
	
	
	public Pacijent() {
		super();
		this.izabLekar = null;
		this.knjiz = null;
		this.pregledi = new ArrayList<Pregled>();
		
	}


	public Pacijent(Lekar izabLekar, ArrayList<Pregled> pregledi, ZdrKnjiz knjiz, String ime, String prezime, String jmbg, String brTel, UlogaKor uloga, String adresa, String korIme,
			String lozinka, Pol pol) {
		super(ime, prezime, jmbg, brTel, uloga, adresa, korIme, lozinka, pol);
		this.izabLekar = izabLekar;
		this.knjiz = knjiz;
		this.pregledi = pregledi;
		
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


	public ArrayList<Pregled> getPregledi() {
		return pregledi;
	}


	public void setPregledi(ArrayList<Pregled> pregledi) {
		this.pregledi = pregledi;
	}


	@Override
	public String toString() {
	
		String ispis = "\nPacijent:" + 
				"\nizabLekar: " + izabLekar.getIme() + " " + izabLekar.getPrezime() + 
				"\nknjizica: " + knjiz + 
				"\nime: " + ime + 
				"\nprezime: " + prezime + 
				"\njmbg: " + jmbg + 
				"\nbrTel: " + brTel + 
				"\nuloga: " + uloga + 
				"\nadresa: " + adresa + 
				"\nkorIme: "+ korIme + 
				"\nlozinka: " + lozinka + 
				"\npol: " + pol +
				"\npregledi: ";
		
		if (pregledi.isEmpty()) {
			System.out.println("Nema pregleda");
		}
		for (Pregled pregled : pregledi) {
			ispis += "\n" + pregled.toString(); 
		}
		
		return ispis;
	
}


	
}
