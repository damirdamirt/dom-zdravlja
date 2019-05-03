package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Racun {
	
	private Pregled pregled;
	private double iznos;
	
	
	public Racun() {
		
		this.pregled = null;
		this.iznos = 0;
	}


	public Racun(Pregled pregled, double iznos) {
		
		this.pregled = pregled;
		this.iznos = iznos;
	}


	public Pregled getPregled() {
		return pregled;
	}


	public void setPregled(Pregled pregled) {
		this.pregled = pregled;
	}


	public double getIznos() {
		return iznos;
	}


	public void setIznos(double iznos) {
		this.iznos = iznos;
	}


	@Override
	public String toString() {
		DateFormat date = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		return "\nRacun:" +
				"\npregled: " + date.format(pregled.getTermin()) +
				"\niznos: " + iznos;
	}
	
	
	
	
	
	
	
	

}
