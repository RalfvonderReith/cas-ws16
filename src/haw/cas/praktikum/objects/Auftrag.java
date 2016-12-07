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
	private Auftrag parent=null;
	private boolean isFinished = false;
	
	//TODO einbinden des Auftraggebers! und auszahlen des Geldes!
	//TODO einbinden Auftragnehmer
	private Auftraggeber auftraggeber;
	private Auftragbesitzer auftragbesitzer;
	
	
	private State state = State.LIEFERSCHEIN;
	

	public enum State {
			LIEFERSCHEIN, 
			WARENLIEFERUNG,
			CHECKLISTE
	}
	
	
	
	public Auftrag(String name, Ort startOrt, Ort endOrt, double gewinn, int menge, Auftraggeber auftraggeber){
		super(name);
		setStart(startOrt);
		setZiel(endOrt);
		setWert(gewinn);
		setMenge(menge);
		this.auftraggeber = auftraggeber;
		this.subAuftraege = new ArrayList<Auftrag>();
	}
	
	public Auftrag(Ort startOrt, Ort endOrt, double gewinn, int menge, Auftraggeber auftraggeber){
		setStart(startOrt);
		setZiel(endOrt);
		setWert(gewinn);
		setMenge(menge);
		setAuftraggeber(auftraggeber);
		this.subAuftraege = new ArrayList<Auftrag>();
	}

	//STATE METHODS
	public void getWarenLieferung() {
		if(!(state == State.CHECKLISTE))
			this.state = State.WARENLIEFERUNG;
	}

	public void getLieferschein(Ort currentLocation) {
		if(!(state == State.CHECKLISTE))
			startOrt = currentLocation;
			this.state = State.LIEFERSCHEIN;
	}
	
	public boolean isLieferschein() {
		return state == State.LIEFERSCHEIN;
	}
	

	public boolean isChecklist() {
		return state == State.CHECKLISTE;
	}
	

	public boolean isWarenLieferung() {
		return state == State.WARENLIEFERUNG;
	}
	//
	
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
	
	public Auftraggeber getAuftraggeber() {
		return auftraggeber;
	}

	private void setAuftraggeber(Auftraggeber auftraggeber) {
		if(auftraggeber == null) throw new NullPointerException();
		this.auftraggeber = auftraggeber;
	}
	
	public Auftragbesitzer getAuftragbesitzer() {
		return auftragbesitzer;
	}

	public void setAuftragbesitzer(Auftragbesitzer auftragbesitzer) {
		this.auftragbesitzer = auftragbesitzer;
	}
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
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
		if(isComplete()) {
			beendeAuftrag();
		}
	}

	//benachrichtige Auftraggeber, den Auftragnehmer zu bezahlen. 
	public void beendeAuftrag() {
		if(parent != null) {
			parent.removeSubAuftrag(this);
		}
		//TODO: AUTRAGNEHMER AUSZAHLEN
		//Auftraggeber.auszahlen(this.gewinn, )
	}
	
	public List<Auftrag> getAllSubAuftrag() {
		return new ArrayList<Auftrag>(subAuftraege);
	}
	
	//check if this task itself is done (ignoring subtasks)
	public boolean isFinished(Ort currentLocation) {
		//Aufträge werden bei Abschluss aus der Liste der Parent.subaufträge entfernt, deshalb prüfen auf subAuftraege.size() == 0
		isFinished = (endOrt == currentLocation);
		return isComplete();
	}
	
	//check if subtasks are finished
	public boolean subsFinished() {
		return subAuftraege.size() == 0;
	}
	
	//check if task and subtasks are finished
	public boolean isComplete() {
		return isFinished && subsFinished();
	}
	
	public List<Auftrag> splitAuftrag(Ort startOrt,double subGewinn1, double subGewinn2, int subMenge, Auftraggeber auftraggeber, Auftragbesitzer auftragbesitzer) {
		
		if(subGewinn1 <= 0 || subGewinn2 <= 0 || subMenge < 1 || subMenge > menge) return null;
		
		Auftrag subAuftrag1 = new Auftrag(startOrt, this.endOrt, subGewinn1, subMenge, auftraggeber);
		subAuftrag1.setAuftragbesitzer(auftragbesitzer);
		
		int submenge2 = this.menge-subMenge;
		Auftrag subAuftrag2 = new Auftrag(startOrt, this.endOrt, subGewinn2, submenge2, auftraggeber);
		subAuftrag2.setAuftragbesitzer(auftragbesitzer);
		
		//TODO: Auftraggeber und Auftragbesitzer zz schlechte impl ändern!!!
		
		
		this.addSubAuftrag(subAuftrag1);
		this.addSubAuftrag(subAuftrag2);
		
		this.setMenge(0);
		this.setState(State.CHECKLISTE);
		
		List<Auftrag> returnList = new ArrayList<>();
		
		
		returnList.add(this);
		returnList.add(subAuftrag1);
		returnList.add(subAuftrag2);
		
		return returnList;
	}
	
}