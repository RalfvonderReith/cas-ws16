package haw.cas.praktikum.objects;

import haw.cas.praktikum.parser.Obj.MObjekt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

//TODO:Serialisiuerng . . . vorerst nicht geplant
public class Boerse extends MObjekt implements Services {

    //TODO Geldtyp?!
	//TODO Klasse Versteigerung, um das ganze etwas sauberer zu trennen // separation of concerns
    private HashMap<Auftrag, Versteigerung> auftragsliste = new HashMap<Auftrag, Versteigerung>();
    private Versteigerung.Typ typ;
   
    public Boerse(String name, Versteigerung.Typ typ) {
        super(name);
        this.typ = typ;
    }
    
    @Override
    public String getID() {
        return super.getUID();
    }

    public boolean addAuftrag(Auftrag auftrag) {
        if(!auftrag.isLieferschein()) return false;
    	auftragsliste.put(auftrag, typ.neueVersteigerung(this, auftrag));
    	return true;
    }

    public void removeAuftrag(Auftrag auftrag) {
        auftragsliste.remove(auftrag);
    }

    public int getGebot(Auftrag auftrag) {
    	return auftragsliste.get(auftrag).getHoechstGebot();
    }
    
    public synchronized boolean biete(Auftrag auftrag, int wert, LKW bieter) {
    	return auftragsliste.get(auftrag).biete(wert, bieter);
    }
    
    public synchronized void versteigert(Auftrag auftrag) {
    	removeAuftrag(auftrag);
    	//TODO: packe auftrag in Ladefläche von LKW
    }
    
    public List<Auftrag> bietetAn() {
        return new ArrayList<Auftrag>(auftragsliste.keySet());
    }

}