package haw.cas.praktikum.objects;

import java.util.ArrayList;
import java.util.List;

import haw.cas.praktikum.parser.Obj.MObjekt;


//%auftrag(ID,ID-ort-Start,ID-ort-Ende,double-Gewinn,int-menge)
//TODO:Serialisierung . . .
public class Auftrag extends MObjekt {
	private Ort startOrt;
	private Ort endOrt;
	private double gewinn;
	private int menge;
	private List<Auftrag> subAuftraege;
	Auftrag parent=null;
	
	//TODO einbinden des Auftraggebers! und auszahlen des Geldes!
	Auftraggeber auftraggeber;
	
	public Auftrag(String name, Ort startOrt, Ort endOrt, double gewinn, int menge){
		super(name);
		setStart(startOrt);
		setZiel(endOrt);
		setWert(gewinn);
		setMenge(menge);
		this.subAuftraege = new ArrayList<Auftrag>();
	}

	public Ort getStart() {
		return startOrt;
	}

	private void setStart(Ort start) {
		if(start == null) throw new NullPointerException();
		this.startOrt = start;
	}

	public Ort getZiel() {
		return endOrt;
	}

	private void setZiel(Ort ziel) {
		if(ziel == null) throw new NullPointerException();
		this.endOrt = ziel;
	}

	public Double getWert() {
		return gewinn;
	}

	private void setWert(double wert) {
		if(wert < 0) throw new IllegalArgumentException("value >= 0 expected");
		this.gewinn = wert;
	}

	public int getMenge() {
		return menge;
	}

	private void setMenge(int menge) {
		if(menge < 1) throw new IllegalArgumentException("value > 0 expected");
		this.menge = menge;
	}

	public void addSubAuftrag(Auftrag auftrag) {
		auftrag.parent=this;
		subAuftraege.add(auftrag);
	}

	public void removeSubAuftrag(Auftrag auftrag) {
		subAuftraege.remove(auftrag);
	}

	public List<Auftrag> getAllSubAuftrag() {
		return new ArrayList<Auftrag>(subAuftraege);
	}
	
	public boolean isFinished(Ort currentLocation) {
		//Aufträge werden bei Abschluss aus der Liste der Parent.subaufträge entfernt, deshalb prüfen auf subAuftraege.size() == 0
		return (endOrt == currentLocation && subAuftraege.size() == 0);
	}
	
	public Auftrag splitAuftrag(String subName, double subGewinn, int subMenge) {
		if(subGewinn < 0 || subMenge < 1 || subMenge > menge) return null;
		Auftrag subAuftrag = new Auftrag(subName, startOrt, endOrt, subGewinn, subMenge);
		this.addSubAuftrag(subAuftrag);
		this.setMenge(this.getMenge() - subMenge);
		return subAuftrag;
	}
}