package haw.cas.praktikum.parser.prolog.generators;

import haw.cas.praktikum.objects.Auftrag;
import haw.cas.praktikum.objects.Boerse;
import haw.cas.praktikum.objects.Funk;
import haw.cas.praktikum.objects.Handelsregister;
import haw.cas.praktikum.objects.Konsortium;
import haw.cas.praktikum.objects.Services;
import haw.cas.praktikum.objects.Nachricht;
import haw.cas.praktikum.objects.SchwarzesBrett;
import haw.cas.praktikum.objects.Tankstelle;
import haw.cas.praktikum.objects.Umladebucht;
import haw.cas.praktikum.parser.Obj.MObjekt;
import haw.cas.praktikum.parser.configFile.ConfigFile;
import haw.cas.praktikum.parser.prolog.PrologGenerator;

import java.util.stream.Stream;

public class LocalServiceGenerator implements PrologGenerator {
	@Override
	public void generate(ConfigFile cf, Stream<MObjekt> os) {
		os.filter(s -> s instanceof Services).forEach( // Sektions nummer
															// Auslagern in
															// andere Klasse ?
				f -> {
					if (f instanceof Tankstelle) {
						cf.addToSection(1, "tankstelle(" + f.getUID() + ","
								+ ((Tankstelle) f).getKosten() + ","
								+ ((Tankstelle) f).getDauer() + ").");
						if (((Tankstelle) f).belegtDurch() != null) {
							cf.addToSection(3, "belegt(" + f.getUID() + ","
									+ ((Tankstelle) f).belegtDurch().getUID()
									+ ").");
						}
					} else if (f instanceof Handelsregister) {
						cf.addToSection(1, "handelsregister(" + f.getUID()
						+ ").");
						for(Konsortium k : ((Handelsregister)f).getEintraege()){
							cf.addToSection(2, "eingetragen("+ k.getUID() + "," + f.getUID() + ").");							
						}

					} else if (f instanceof Umladebucht) {
						cf.addToSection(1, "umladebucht(" + f.getUID()
								+ ").");

					} else if (f instanceof Funk) {
						cf.addToSection(1, "funk(" + f.getUID()
								+ ").");
					}else if (f instanceof Boerse) {
						cf.addToSection(1, "boerse(" + f.getUID()
								+ ").");
						if (!((Boerse) f).bietetAn().isEmpty()) {
							for (Auftrag a : ((Boerse) f).bietetAn()) {
								cf.addToSection(2, "storedIn(" + a.getUID() + ","
										+ ((Boerse) f).getUID() + ").");
							}
						}

					} else if (f instanceof SchwarzesBrett) {
						cf.addToSection(1, "schwarzesbrett(" + f.getUID()
								+ ").");
						if (((SchwarzesBrett) f).getNachrichten() != null) {
							for (Nachricht n : ((SchwarzesBrett) f).getNachrichten()) {
								cf.addToSection(2,"sbnachricht(" + n.getSB().getID()
												+ "," + n.getContent() + ","
												+ n.getErsteller().getUID()
												+ ","
												+ n.getErstellungsZeitpunkt()
												+ ").");
							}
						}
					}

					else {

						cf.addToSection(1, f.getClass().getName().toLowerCase()
								+ "(" + f.getUID() + ").");
					}
				});
	}
}
