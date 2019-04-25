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
		return "Lekar [spec=" + spec + ", plata=" + plata + ", sluzba=" + sluzba + ", ime=" + ime + ", prezime="
				+ prezime + ", jmbg=" + jmbg + ", brTel=" + brTel + ", uloga=" + uloga + ", adresa=" + adresa
				+ ", korIme=" + korIme + ", lozinka=" + lozinka + ", pol=" + pol + "]";
	}

	
	
	
	

}
