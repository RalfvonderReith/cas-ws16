package haw.cas.praktikum.objects;

public class Gebot extends Thread {
	private final LKW bieter;
	//TODO: Geldtyp
	private final int wert;
	
	public Gebot(LKW bieter, int wert) {
		this.bieter = bieter; 
		this.wert = wert;
		this.start();
	}
	
	public LKW getBieter() {
		return bieter;
	}
	
	public int getWert() {
		return wert;
	}
}
