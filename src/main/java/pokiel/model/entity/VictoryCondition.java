package pokiel.model.entity;

public enum VictoryCondition {
	Hauteur(0x1), 
	Paire(0x2), 
	DoublePaire(0x4), 
	Brelan(0x8), 
	Suite(0x10), 
	Couleur(0x20), 
	Full(0x40), 
	Carré(0x80), 
	QuinteFlush(0x100), 
	QuinteFlushRoyale(0X200);

	private final int value;
	
	VictoryCondition(int value) {
		this.value=value;
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean arePresent(int value) {
		return (value & this.value) == this.value;
	}
}
