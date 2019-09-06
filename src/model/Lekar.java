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

	public Lekar(String ime, String prezime, String jmbg, String brTel, UlogaKor uloga, String adresa, String korIme,
			String lozinka, Pol pol, double plata, Sluzba sluzba, String spec) {
		super(ime, prezime, jmbg, brTel, uloga, adresa, korIme, lozinka, pol, plata, sluzba);
		this.spec = spec;
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
	public boolean equals(Object obj) {
		if (obj instanceof Lekar) {
			Lekar lekar = (Lekar) obj;
			if (korIme.equals(lekar.getKorIme())) {
				return true;
			} else {
				return false;
			}
		} else {
			return super.equals(obj);
		}
	}

	@Override
	public String toString() {
		return ime + " " + prezime;
	}
}
