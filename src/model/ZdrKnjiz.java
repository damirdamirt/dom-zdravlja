package model;

import java.util.Date;

public class ZdrKnjiz {
	
	private String broj;
	private Date datumIsteka;
	private int katOsig;
	
	
	public ZdrKnjiz() {
		super();
		this.broj = "";
		this.datumIsteka = null;
		this.katOsig = 0;
		
	}


	public ZdrKnjiz(String broj, Date datumIsteka, int katOsig) {
		super();
		this.broj = broj;
		this.datumIsteka = datumIsteka;
		this.katOsig = katOsig;
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


	@Override
	public String toString() {
		return "\nZdrKnjiz:" + 
				"\nbroj: " + broj + 
				"\ndatumIsteka: " + datumIsteka + 
				"\nkatOsig: " + katOsig;
	}
	
	
	
	
	
	

}
