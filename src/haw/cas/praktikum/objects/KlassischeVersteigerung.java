package haw.cas.praktikum.objects;

public class KlassischeVersteigerung implements Versteigerung {

	private int TIME_TO_BID = 5000; // 5 sekunden 
	private Gebot hoechstGebot = new Gebot(null, 0);
	private Boerse boerse;
	private Auftrag auftrag;
	private long lastBid = -1;
	
	private Thread timer = new Thread(new Runnable() {
	    @Override
	    public void run() {
	    	try {
	    		while(true) {
		    		if(!(lastBid == -1)) {
			    		if(System.currentTimeMillis() > lastBid + TIME_TO_BID) {
			    			versteigert();
			    			timer.interrupt();
			    		}
		    		}
		    		Thread.sleep(100);
	    		}
	    	} catch (InterruptedException e) {
	    		
	    	}
	    }
	});
	
	public KlassischeVersteigerung(Boerse boerse, Auftrag auftrag) {
		this.boerse = boerse;
		this.auftrag = auftrag;
		
		timer.start();
	}
	
	@Override
	public boolean biete(int wert, LKW bieter) {
    	if(hoechstGebot != null && wert > hoechstGebot.getWert() && bieter != null) {
    		hoechstGebot = new Gebot(bieter, wert);
    		lastBid = System.currentTimeMillis();
    		return true;
    	}
    	return false;
	}
	
	public int getHoechstGebot() {
    	return hoechstGebot.getWert();
    }
	
	private void versteigert() {
		boerse.versteigert(auftrag);
	}

}
