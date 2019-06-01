package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pregled {
	
	private String id;
	private Pacijent pacijent;
	private Lekar lekar;
	private Date termin;  
	private String soba;
	private String opis;
	private StatusPreg status;
	private boolean obrisan;
	
	
	public Pregled() {
		
		this.pacijent = null;
		this.lekar = null;
		this.termin = null;
		this.soba = "";
		this.opis = "";
		this.status = null;
		this.obrisan = false;
	}


	public Pregled(Pacijent pacijent, Lekar lekar, Date termin, String soba, String opis, 
					StatusPreg status, boolean obrisan) {
		
		this.pacijent = pacijent;
		this.lekar = lekar;
		this.termin = termin;
		this.soba = soba;
		this.opis = opis;
		this.status = status;
		this.obrisan = obrisan;
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
	
	
	public boolean isObrisan() {
		return obrisan;
	}


	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}


	@Override
	public String toString() {
		String funkcijaPregleda;
		if (this.obrisan == true) {
			funkcijaPregleda = "Da";
		}else {
			funkcijaPregleda = "Ne";
		}
		
		DateFormat datum = new SimpleDateFormat("dd.MM.yyyy");
		return "Pacijent: " + "id: " + id + " " + pacijent.getIme() + " " + pacijent.getPrezime() + 
				"\nLekar: " + lekar.getIme() + " " + lekar.getPrezime() + 
				"\nTermin: " + datum.format(termin) + 
				"\nSoba: " + soba +
				"\nOpis: " + opis + 
				"\nStatus: " + status +
				"\nObrisan: " + funkcijaPregleda;
	}
}
