package pokiel.model.entity;

public enum VictoryCondition {
	Hauteur(0x1), Paire(0x2), DoublePaire(0x4), Brelan(0x8), Suite(0x10), Couleur(0x11), Full(0x12), Carré(0x14), QuinteFlush(0x18), QuinteFlushRoyale(0X20);

	private final int value;
	
	VictoryCondition(int value) {
		this.value=value;
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean arePresent(int value) {
		return (this.value & value) == this.value;
	}
}
