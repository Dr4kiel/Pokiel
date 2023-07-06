package pokiel.model;

import java.util.ArrayList;
import java.util.List;

import pokiel.model.entity.Player;

public class PlayerModel implements PlayerModelInterface {
	private final List<Player> playerList;
	
	public PlayerModel() {
		playerList = new ArrayList<>();
	}
	
	@Override
	public List<Player> getPlayerList() {
		return playerList;
	}

}
