package model;

import java.util.Date;

public class Pregled {
	
	private Pacijent pacijent;
	private Lekar lekar;
	private Date termin;  
	private String soba;
	private String opis;
	private StatusPreg status;
	
	
	public Pregled() {
		
		this.pacijent = null;
		this.lekar = null;
		this.termin = null;
		this.soba = "";
		this.opis = "";
		this.status = null;
	}


	public Pregled(Pacijent pacijent, Lekar lekar, Date termin, String soba, String opis, StatusPreg status) {
		
		this.pacijent = pacijent;
		this.lekar = lekar;
		this.termin = termin;
		this.soba = soba;
		this.opis = opis;
		this.status = status;
	}


	public Pacijent getPacijent() {
		return pacijent;
	}


	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}


	public Lekar getLekar() {
		return lekar;
	}


	public void setLekar(Lekar lekar) {
		this.lekar = lekar;
	}


	public Date getTermin() {
		return termin;
	}


	public void setTermin(Date termin) {
		this.termin = termin;
	}


	public String getSoba() {
		return soba;
	}


	public void setSoba(String soba) {
		this.soba = soba;
	}


	public String getOpis() {
		return opis;
	}


	public void setOpis(String opis) {
		this.opis = opis;
	}


	public StatusPreg getStatus() {
		return status;
	}


	public void setStatus(StatusPreg status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "\nPregled:" + 
				"\npacijent: " + pacijent + 
				"\nlekar: " + lekar + 
				"\ntermin: " + termin + 
				"\nsoba: " + soba +
				"\nopis: " + opis + 
				"\nstatus: " + status;
	}
	
	
	
	
	
	

}
