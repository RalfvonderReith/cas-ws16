package haw.cas.praktikum.objects;

import haw.cas.praktikum.parser.Obj.MObjekt;

import java.util.ArrayList;
import java.util.List;

//TODO:Serialisiuerng . . . 
public class SchwarzesBrett extends MObjekt implements Services {

	private List<Nachricht> angepinnt;
	
	public SchwarzesBrett(String name){
		super(name);
		this.angepinnt = new ArrayList<>();
	}

	@Override
	public String getID() {		
		return super.getUID();
	}
	
	public void anpinnen(Nachricht n){
		angepinnt.add(n);
	}
	
	public List<Nachricht> getNachrichten(){
		return angepinnt;
	}

}