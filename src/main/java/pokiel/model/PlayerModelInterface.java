package pokiel.model;

import java.util.List;

import pokiel.model.entity.Player;

public interface PlayerModelInterface {

	List<Player> getPlayerList();

	void setPlayerList(List<Player> playerList);

}