package model;

public enum KategOsiguranjaKnjizice {
	
	JEDAN(1), DVA(2), TRI(3);

    private int brojevi;
    
    private KategOsiguranjaKnjizice(int brojevi) {
    	this.brojevi = brojevi;
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
}


