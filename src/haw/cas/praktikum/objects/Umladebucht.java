package haw.cas.praktikum.objects;

import java.util.List;

import haw.cas.praktikum.parser.Obj.MObjekt;

//TODO:Serialisiuerng . . . 
public class Umladebucht extends MObjekt implements LocalServices {
	public List<Auftrag> verfuegbar;
	
	public Umladebucht(String name){
		super(name);
	}

	@Override
	public String getID() {		
		return super.getUID();
	}
}