package pokiel.model.entity;

public enum VictoryCondition {
	HAUTEUR(0x1), PAIRE(0x2), DOUBLE_PAIRE(0x4), BRELAN(0x8), SUITE(0x10), COULEUR(0x20), FULL(0x40), CARRE(0x80),
	QUINTE_FLUSH(0x100), QUINTE_FLUSH_ROYALE(0X200);

	private final int value;

	VictoryCondition(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public boolean arePresent(int value) {
		return (value & this.value) == this.value;
	}

	public static String getTheVictoryCondition(int value) {
		String result = "No Victory";
		for (VictoryCondition condition : VictoryCondition.values()) {
			if (condition.arePresent(value))
				result = condition.toString();
		}
		return result;
	}
}
