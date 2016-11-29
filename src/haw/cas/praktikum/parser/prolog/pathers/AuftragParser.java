package haw.cas.praktikum.parser.prolog.pathers;

import haw.cas.praktikum.objects.Akteur;
import haw.cas.praktikum.objects.Auftrag;
import haw.cas.praktikum.objects.Ort;
import haw.cas.praktikum.parser.MObjektRepository;
import haw.cas.praktikum.parser.prolog.PrologParser;

public class AuftragParser implements PrologParser {

	//%auftrag(ID,ID-ort-Start,ID-ort-Ende,double-Gewinn,int-menge)
	@Override
	public void create(String[] param) {
		assert param.length == 5 : "the Parameter Length for an ort must be 3, but was " + param.length;
		String name = param[0];
		String startO = param[1];
		String endO = param[2];
		String gewinnS = param[3];
		String mengeS = param[4];
		
		Ort startpos = (Ort) MObjektRepository.get(startO);
		Ort endpos = (Ort) MObjektRepository.get(endO);
		Double gewinn = Double.parseDouble(gewinnS);
		int menge = Integer.parseInt(mengeS);
		
		//new Auftrag(name, startpos, endpos, gewinn, menge);
	}

}
