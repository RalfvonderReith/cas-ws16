package haw.cas.praktikum.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import haw.cas.praktikum.parser.Obj.MObjekt;

//TODO:Serialisierung . . .
public class Auftragsgenerator extends MObjekt implements Auftraggeber, Runnable {

	private static final int MIN_VALUE_PER_GOOD = 50;
	private static final int MAX_VALUE_PER_GOOD = 100;
	private static final int MIN_GOODS_PER_TASK = 1;
	private static final int MAX_GOODS_PER_TASK = 20;
	private static final Random rnd = new Random();
	
	private int sleepTime;
	private static final int SLEEP_TIME_MAX = 5000;
	
    // TODO:Zustaendigkeit
    // ist ueber die Boersen geregelt, ein Verweis auf
    // das Strassennetz ist notwendig

    private int aktivitaetsrate;
    private List<Ort> zustaendigFuer;
 // private List<Object> zustaendigFuer;	
    private List<Boerse> zustaendigeBoersen;
 // private Ortsnetz orte;

    public Auftragsgenerator(String name, int aktivitaetsrate) {
        super(name);
        this.aktivitaetsrate = aktivitaetsrate;
        this.sleepTime = SLEEP_TIME_MAX / aktivitaetsrate;
        this.zustaendigFuer = new ArrayList<>();
        this.zustaendigeBoersen = new ArrayList<>();
    }
    
    public Auftragsgenerator(int aktivitaetsrate) {
        super();
        this.aktivitaetsrate = aktivitaetsrate;
        this.sleepTime = SLEEP_TIME_MAX / aktivitaetsrate;
        this.zustaendigFuer = new ArrayList<>();
        this.zustaendigeBoersen = new ArrayList<>();
    }

    public int getAktivitaetsrate() {
        return aktivitaetsrate;
    }

    public void setAktivitaetsrate(int aktivitaetsrate) {
        this.aktivitaetsrate = aktivitaetsrate;
    }

    public void bereichErweitern(Ort o) {
        zustaendigFuer.add(o);
    }

/*    public void bereichErweitern() {,		
        zustaendigFuer.add(orte.get());
    }*/

    public void bereichVerkleinern(Ort o) {
        zustaendigFuer.remove(o);
    }

    public void addBoerse(Boerse b) {
        zustaendigeBoersen.add(b);
    }

    public void removeBoerse(Boerse b) {
        zustaendigeBoersen.remove(b);
    }

    public List<Ort> zustaendigeOrte() {
        return zustaendigFuer;
    }

	@Override
	public void run() {
		while(true) {
			int goods = MIN_GOODS_PER_TASK + rnd.nextInt(MAX_GOODS_PER_TASK - MIN_GOODS_PER_TASK);
			int value = goods * (MIN_VALUE_PER_GOOD + rnd.nextInt(MAX_VALUE_PER_GOOD - MIN_VALUE_PER_GOOD));
			Ort startOrt = zustaendigFuer.get(rnd.nextInt(zustaendigFuer.size()));
			Ort endOrt;
			do {
				endOrt = zustaendigFuer.get(rnd.nextInt(zustaendigFuer.size()));
			} while(endOrt != startOrt);
			Boerse boerse = zustaendigeBoersen.get(rnd.nextInt(zustaendigeBoersen.size()));
			Auftrag neuerAuftrag = new Auftrag(startOrt, endOrt, value, goods, this);
			boerse.addAuftrag(neuerAuftrag);
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
    
    

}
