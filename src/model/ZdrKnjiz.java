package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ZdrKnjiz {
	
	private String broj;
	private Date datumIsteka;
	private int katOsig;
	private boolean obrisan;
	
	
	public ZdrKnjiz() {

		this.broj = "";
		this.datumIsteka = null;
		this.katOsig = 0;
		this.obrisan = false;
		
	}


	public ZdrKnjiz(String broj, Date datumIsteka, int katOsig, boolean obrisan) {

		this.broj = broj;
		this.datumIsteka = datumIsteka;
		this.katOsig = katOsig;
		this.obrisan = obrisan;
	}


	public String getBroj() {
		return broj;
	}


	public void setBroj(String broj) {
		this.broj = broj;
	}


	public Date getDatumIsteka() {
		return datumIsteka;
	}


	public void setDatumIsteka(Date datumIsteka) {
		this.datumIsteka = datumIsteka;
	}


	public int getKatOsig() {
		return katOsig;
	}


	public void setKatOsig(int katOsig) {
		this.katOsig = katOsig;
	}
	

	public boolean isObrisan() {
		return obrisan;
	}


	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}


	@Override
	public String toString() {
		String funkcijaKnjizice;
		if (this.obrisan == true) {
			funkcijaKnjizice = "Da";
		}else {
			funkcijaKnjizice = "Ne";
		}
		
		DateFormat datum = new SimpleDateFormat("dd.MM.yyyy");
		return "\nbroj: " + broj + 
				", datumIsteka: " + datum.format(datumIsteka) + 
				", katOsig: " + katOsig +
				", Obrisana: " + funkcijaKnjizice;
	}
}
