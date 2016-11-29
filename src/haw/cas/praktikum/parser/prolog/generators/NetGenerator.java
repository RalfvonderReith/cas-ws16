package haw.cas.praktikum.parser.prolog.generators;

import java.util.stream.Stream;

import haw.cas.praktikum.objects.Ereignis;
import haw.cas.praktikum.objects.Services;
import haw.cas.praktikum.objects.Ort;
import haw.cas.praktikum.objects.Strasse;
import haw.cas.praktikum.parser.Obj.MObjekt;
import haw.cas.praktikum.parser.configFile.ConfigFile;
import haw.cas.praktikum.parser.prolog.PrologGenerator;

public class NetGenerator implements PrologGenerator {
//TODO: vileicht f�r typen registriern ? (Observer)
//TODO: Reinvol vileicht automatioch durch dependencies managen
	
	@Override
	public void generate(ConfigFile cf, Stream<MObjekt> os) {
		os	.filter( s -> s instanceof Ort)
			.forEach(						//Sektions nummer Auslagern in andere Klasse ?
					ort -> {
						cf.addToSection(1, "ort("+ort.getUID()+").");
						for(Strasse s : ((Ort)ort).getAngebunden()){
							cf.addToSection(2, "strasse("+s.getStart().getUID()+","+s.getEnde().getUID()+","+ s.getKosten()+ ").");
							for(Ereignis e : s.getEreignise()){
								cf.addToSection(3, "ereignis("	+s.getStart().getUID()+","
															 	+s.getEnde().getUID()+","
														 		+e.getKosten()+ ","
															 	+e.getEnde()+").");
							}
						}
						for(Services l : ((Ort)ort).getLocalServices()){
							cf.addToSection(2, "linkLocal(" + ort.getUID() +"," + l.getID() + ").");
						}
					}
			);
	}
}
