package gui;

import java.util.Collection;
import javax.swing.JOptionPane;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;


public class Window extends JOptionPane {
	
	private static final long serialVersionUID = 1L;

	
	public Player getPlayerId(GameEngine gameEngine) {
		Collection<Player> players = gameEngine.getAllPlayers();
		if (players.isEmpty()) {
			String id = "1";
			String playerName = JOptionPane.showInputDialog(null, "Player Name:");
			String playerPoints = JOptionPane.showInputDialog(null, "Player starting points:");
			int points = Integer.parseInt(playerPoints);
			Player player = new SimplePlayer(id, playerName, points);
			return player;
		} else {
			//if game has player
			int id = 0;
			id = players.size() + 1;
			String playerName = JOptionPane.showInputDialog(null, "Player Name:");
			String playerPoints = JOptionPane.showInputDialog(null, "Player starting points:");
			int points = Integer.parseInt(playerPoints);
			Player player = new SimplePlayer(Integer.toString(id), playerName, points);
			return player;
		}

	}
	// placeBet method from gameEngine
	public void placeBet(GameEngine gameEngine, Player player) {
		boolean retry = true;
		while (retry == true) {
			String bet = JOptionPane.showInputDialog(null, "Enter bet amount:");
			int bets = 0;
			if (bet != null) {
				try {
					bets = Integer.parseInt(bet);
					if (gameEngine.placeBet(player, bets) == true) {
						retry = false;
					} else {
						JOptionPane.showMessageDialog(null, "Not enough Points!");
					}
				} catch (NumberFormatException ie) {
					JOptionPane.showMessageDialog(null, "That is not a number!");
					retry = true;
				}
			}
			retry = false;
		}

	}

}
