package haw.cas.praktikum.parser.prolog.pathers;

import haw.cas.praktikum.objects.Services;
import haw.cas.praktikum.objects.Ort;
import haw.cas.praktikum.parser.MObjektRepository;
import haw.cas.praktikum.parser.Obj.MObjekt;
import haw.cas.praktikum.parser.prolog.PrologParser;


/**
 * definiert wie folgt:
 * ort(name,
 * 
 **/
public class LinkLocalServiceParser implements PrologParser{
	@Override
	public void create(String[] param) {
		assert param.length == 2 : "the Parameter Length for an ort must be 1, but was " + param.length;
		String ortS = param[0];
		String service = param[1];
		
		MObjekt ort =  MObjektRepository.get(ortS);
		MObjekt localService =  MObjektRepository.get(service);
		
		assert (ort instanceof Ort) : "Not a valid Ort for a Link";
		assert (localService instanceof Services ) : "Not a valid LocalSer service";;
		
		((Ort)ort).addLocalService((Services)localService);
	}
}
