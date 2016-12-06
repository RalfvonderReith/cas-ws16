package haw.cas.praktikum.objects;

public interface Versteigerung {
	
	public enum Typ {
		KLASSISCH {
			@Override
			public Versteigerung neueVersteigerung(Boerse boerse, Auftrag auftrag) {
				return new KlassischeVersteigerung(boerse, auftrag);
			}
		};
		
		public abstract Versteigerung neueVersteigerung(Boerse boerse, Auftrag auftrag);
	}
	
	public boolean biete(int wert, LKW bieter);
	
	public int getHoechstGebot();
}
