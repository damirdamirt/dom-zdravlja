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


	public Pacijent( String ime, String prezime, String jmbg, String brTel, UlogaKor uloga, String adresa, String korIme,
			String lozinka, Pol pol, Lekar izabLekar, ArrayList<Pregled> pregledi, ZdrKnjiz knjiz) {
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
		
		String ispis = "Pacijent: \nIme i prezime: " + ime + " " + prezime +
						"\nJMBG broj: " + jmbg +
						"\nBroj Telefona: " + brTel +
						"\nUloga korisnika: " + uloga +
						"\nAdresa prebivalista: " + adresa +
						"\nVase korisnicko ime: " + korIme +
						"\nVasa lozinka: " + lozinka +
						"\nPol: " + pol + 
						"\nIzabrani lekar: " + izabLekar.getIme() + " " + izabLekar.getPrezime() +
						"\nPodaci knjizice: " + knjiz +
						"\n\nLista pregleda: "; 
					if (pregledi.isEmpty()) {
						ispis += "Nema pregleda";
					}
					for (Pregled pregled : pregledi) {
						ispis += "\n" + pregled.toString() + "\n"; 
					}
						return ispis;
	}
}
