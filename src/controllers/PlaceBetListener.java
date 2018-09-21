package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.AppFrame;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class PlaceBetListener implements ActionListener {

	private AppFrame frame;
	private GameEngine gameEngine;

	public PlaceBetListener(AppFrame frame, GameEngine gameEngine) {
		this.frame = frame;
		this.gameEngine = gameEngine;
	}

	
	public void actionPerformed(ActionEvent e) {
		Player player = frame.getSelectedPlayer(gameEngine);
		frame.placeBet(gameEngine, player);
		frame.setStatus1(gameEngine);
	}

}
