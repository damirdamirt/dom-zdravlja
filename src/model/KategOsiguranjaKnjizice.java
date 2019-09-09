package model;

public enum KategOsiguranjaKnjizice {
	
	JEDAN(1,300), DVA(2,50), TRI(3,0);

    private int brojevi;
    private int cena;
    
    private KategOsiguranjaKnjizice(int brojevi, int cena) {
    	this.brojevi = brojevi;
    	this.cena = cena;
    }
    
    public static KategOsiguranjaKnjizice getOsiguranje(int brojevi) {
    	
    	switch (brojevi) {
		case 1:
			return JEDAN;
	
		case 2:
			return DVA;
		
		case 3:
			return TRI;
		}
    	return JEDAN;
    }
    
 
    public int getBroj() {
        return brojevi;
    }
    public int getCena() {
    	return cena;
    }
}


