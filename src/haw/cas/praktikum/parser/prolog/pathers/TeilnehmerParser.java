package haw.cas.praktikum.parser.prolog.pathers;

import haw.cas.praktikum.objects.Akteur;
import haw.cas.praktikum.objects.Konsortium;
import haw.cas.praktikum.objects.Services;
import haw.cas.praktikum.objects.Ort;
import haw.cas.praktikum.parser.MObjektRepository;
import haw.cas.praktikum.parser.prolog.PrologParser;

public class TeilnehmerParser implements PrologParser {

	@Override
	public void create(String[] param) {
		assert param.length == 2 : "the Parameter Length for an ort must be 1, but was "
				+ param.length;
		String akteurS = param[0];
		String konsS = param[1];

		Akteur akteur = (Akteur) MObjektRepository.get(akteurS);
		Konsortium kons = (Konsortium) MObjektRepository.get(konsS);

		kons.addTeilnehmer(akteur);
	}

}
