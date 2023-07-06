package pokiel.model.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import pokiel.model.PlayerModelInterface;

class GameTest {

	static Game game;

	@BeforeAll
	static void init() {
		game = new Game();
	}

	@Test
	void distribuerTest() {

		Mockery mockContext = new Mockery();

		final List<Player> players = new ArrayList<>();

		players.add(new Player("Joueur 1"));
		players.add(new Player("Joueur 2"));
		players.add(new Player("Joueur 3"));
		players.add(new Player("Joueur 4"));

		final PlayerModelInterface playerModel = mockContext.mock(PlayerModelInterface.class);

		mockContext.checking(new Expectations() {
			{
				exactly(2).of(playerModel).getPlayerList();
				will(returnValue(players));
			}
		});

		game.setPlayerModel(playerModel);

		game.distribuer();

		assertEquals(5, game.getGlobalHand().size());
		assertEquals(39, game.getDeck().sizeOfDeck());
	}

}
