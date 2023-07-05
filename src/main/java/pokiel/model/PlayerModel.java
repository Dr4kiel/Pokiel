package pokiel.model;

import java.util.ArrayList;
import java.util.List;

import pokiel.model.entity.Player;

public class PlayerModel implements PlayerModelInterface {
	private List<Player> playerList;
	
	public PlayerModel() {
		playerList = new ArrayList<Player>();
	}
	
	@Override
	public List<Player> getPlayerList() {
		return playerList;
	}
	
	@Override
	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}
}
