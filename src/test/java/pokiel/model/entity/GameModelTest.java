package pokiel.model.entity;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pokiel.model.GameModel;
import pokiel.model.PlayerModel;
import pokiel.model.exception.NoPlayerInTheModelException;
import pokiel.model.exception.UnimplementedEquality;

class GameModelTest {

	private GameModel gameModel;

	@BeforeEach
	void init() {

		PlayerModel playerModel = new PlayerModel();

		playerModel.getPlayerList().add(new Player("Test 1"));
		playerModel.getPlayerList().add(new Player("Test 2"));
		playerModel.getPlayerList().add(new Player("Test 3"));

		gameModel = new GameModel(playerModel);
	}

	@Test
	void testerNouvellePartie() {
		try {
			gameModel.nouvellePartie();
		} catch (NoPlayerInTheModelException | UnimplementedEquality e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
