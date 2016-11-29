package haw.cas.praktikum.objects;

import java.util.List;

import haw.cas.praktikum.parser.Obj.MObjekt;

//TODO:Serialisierung . . .
public class Umladebucht extends MObjekt implements Services {
	public List<Auftrag> verfuegbar;
	
	public Umladebucht(String name){
		super(name);
	}

	@Override
	public String getID() {		
		return super.getUID();
	}
}