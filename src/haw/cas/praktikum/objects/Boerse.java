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
    private HashMap<Auftrag, Gebot> auftragsliste = new HashMap<Auftrag, Gebot>();

    public Boerse(String name) {
        super(name);
    }

    @Override
    public String getID() {
        return super.getUID();
    }

    public void addAuftrag(Auftrag auftrag) {
        auftragsliste.put(auftrag, new Gebot(null, 0, auftrag, this));
    }

    public void removeAuftrag(Auftrag auftrag) {
        auftragsliste.remove(auftrag);
    }

    public int getGebot(Auftrag auftrag) {
    	return auftragsliste.get(auftrag).getWert();
    }
    
    public synchronized boolean biete(Auftrag auftrag, int wert, LKW bieter) {
    	Gebot altesGebot = auftragsliste.get(auftrag);
    	if(altesGebot != null && wert > altesGebot.getWert()) {
    		altesGebot.interrupt();
    		auftragsliste.put(auftrag, new Gebot(bieter, wert, auftrag, this));
    		return true;
    	}
    	return false;
    }
    
    public synchronized void versteigert(Auftrag auftrag) {
    	removeAuftrag(auftrag);
    	//TODO: packe auftrag irgendwo hin - z.b. in Umladebucht oder in Ladefläche von LKW
    }
    
    public List<Auftrag> bietetAn() {
        return new ArrayList<Auftrag>(auftragsliste.keySet());
    }

}