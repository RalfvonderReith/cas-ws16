package haw.cas.praktikum.objects;

import java.security.Timestamp;

import haw.cas.praktikum.parser.Obj.MObjekt;
import haw.cas.praktikum.zeittyp.ZeitTyp;


//TODO:Serialisierung . . .
public class Tankstelle extends MObjekt implements Services {

	private Double preis; 
	private ZeitTyp kostenZeit;
	private belegung used;
	private static class belegung{
		belegung(LKW who){
			belegtDurch=who;
			start=ZeitTyp.now();
		}
		public LKW belegtDurch;
		public ZeitTyp start;			
	}
	
	
	
	public Tankstelle(String name, ZeitTyp tankdauer ,double preis) {
		super(name);
		this.preis = preis;
		this.kostenZeit = tankdauer;
	}

	public double getKosten(){
		return preis;
	}
	
	public ZeitTyp getDauer(){
		return kostenZeit;
	}
	
	public LKW belegtDurch(){
		if(used==null)return null;
		return used.belegtDurch;
	}
	
	public void setBelegt(LKW who){
		if(who==null)used=null;
		else{
			used=new belegung(who);
		}
	}

	@Override
	public String getID() {
		return super.getUID();
	}

}