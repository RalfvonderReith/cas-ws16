package haw.cas.praktikum.objects;

import java.util.ArrayList;
import java.util.List;

import haw.cas.praktikum.parser.Obj.MObjekt;

//TODO:Serialisierung . . .
public class Ort extends MObjekt {

    private List<Strasse> angebunden;
    private List<Services> localServices;

    public Ort(String name) {
        this(name, new ArrayList<>(), new ArrayList<>());
    }

    private Ort(String name, List<Strasse> angebunden, List<Services> localServices) {
        super(name);
        this.angebunden = angebunden;
        this.localServices = localServices;
    }

    public List<Strasse> getAngebunden() {
        return angebunden;
    }

    public void setAngebunden(List<Strasse> angebunden) {
        this.angebunden = angebunden;
    }

    public void addLocalService(Services localService) {
        localServices.add(localService);
    }

    public void removeLocalService(Services localService) {
        localServices.remove(localService);
    }

    public void addStrasse(Strasse strasse) {
        angebunden.add(strasse);
    }

    public void removeStrasse(Strasse strasse) {
        angebunden.remove(strasse);
    }

    public String getName() {
        return super.getUID();
    }

    public void setName(String name) {
        super.updateKey(name);
    }

    public List<Services> getLocalServices() {
        return localServices;
    }

    public void setLocalServices(List<Services> localServices) {
        this.localServices = localServices;
    }
}