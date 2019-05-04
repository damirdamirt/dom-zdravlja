package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Pregled {
	
	private String id;
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
	


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	@Override
	public String toString() {
		
		DateFormat datum = new SimpleDateFormat("dd.MM.yyyy");
		return "pacijent: " + "id: " + id + " " + pacijent.getIme() + " " + pacijent.getPrezime() + 
				"\nlekar: " + lekar.getIme() + " " + lekar.getPrezime() + 
				"\ntermin: " + datum.format(termin) + 
				"\nsoba: " + soba +
				"\nopis: " + opis + 
				"\nstatus: " + status;
	}
	
	
	
	
	
	

}
