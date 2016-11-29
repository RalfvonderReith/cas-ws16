package haw.cas.praktikum.objects;

public class Gebot extends Thread {
	private static final int timeout = 5000;
	private final LKW bieter;
	//TODO: Geldtyp
	private final int wert;
	private final Auftrag auftrag;
	private final Boerse boerse; 
	
	public Gebot(LKW bieter, int wert, Auftrag auftrag, Boerse boerse) {
		this.bieter = bieter; 
		this.wert = wert;
		this.boerse = boerse;
		this.auftrag = auftrag;
		this.start();
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(5000);
			boerse.versteigert(auftrag);
		} catch(InterruptedException e) {
			//überboten oder fehler
		}
	}
	
	public LKW getBieter() {
		return bieter;
	}
	
	public int getWert() {
		return wert;
	}
}
