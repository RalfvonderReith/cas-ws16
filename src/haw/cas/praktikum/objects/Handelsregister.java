package haw.cas.praktikum.objects;

import java.util.ArrayList;
import java.util.List;

import haw.cas.praktikum.parser.Obj.MObjekt;


//TODO:Serialisierung . . .
public class Handelsregister extends MObjekt implements Services{

    private List<Konsortium> eingetragen;

    public Handelsregister(String name) {
        super(name);
        this.eingetragen = new ArrayList<>();
    }

    public List<Konsortium> getEintraege() {
        return eingetragen;
    }

    public void setEintraege(List<Konsortium> eingetragen) {
        this.eingetragen = eingetragen;
    }

    public void addEintrag(Konsortium konsortium) {
        eingetragen.add(konsortium);
    }

    public void removeEintrag(Konsortium konsortium) {
        eingetragen.remove(konsortium);
    }

	@Override
	public String getID() {		
		return super.getUID();
	}

}