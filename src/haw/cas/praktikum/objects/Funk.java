package haw.cas.praktikum.objects;

import java.util.ArrayList;
import java.util.List;

import haw.cas.praktikum.parser.Obj.MObjekt;

//TODO:Serialisierung . . .
public class Funk extends MObjekt implements Services {
	
	private List<Ort> verfuegbar;
	//TODO : Fehlt hier nicht was?
	
	public Funk(String name){
		this(name,new ArrayList<>());
	}
	public Funk(String name, List<Ort> verfuegbareOrte){
		super(name);
		this.verfuegbar = verfuegbareOrte;
	}

	@Override
	public String getID() {		
		return super.getUID();
	}

	public List<Ort> getVerfuegbareOrte() {
		return verfuegbar;
	}

	public void addVerfuegbarenOrt(Ort o) {
		verfuegbar.add(o);
	}
}