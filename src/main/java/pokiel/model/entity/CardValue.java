package pokiel.model.entity;

public enum CardValue {
	Deux(2),
	Trois(3),
	Quatre(4),
	Cinq(5),
	Six(6),
	Sept(7),
	Huit(8),
	Neuf(9),
	Dix(10),
	Valet(11),
	Dame(12),
	Roi(13),
	As(14);
	
	private int valeur;
	
	private CardValue(int valeur) {
		this.valeur = valeur;
	}
	
	public int getValeur() {
		return valeur;
	}
}
