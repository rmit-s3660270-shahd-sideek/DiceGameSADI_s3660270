package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.AppFrame;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class SwitchPlayerListener implements ActionListener {
	private AppFrame frame;
	private GameEngine gameEngine;

	public SwitchPlayerListener(AppFrame frame, GameEngine gameEngine) {
		this.frame = frame;
		this.gameEngine = gameEngine;
	}

	
	public void actionPerformed(ActionEvent e) {
		Player player = frame.getSelectedPlayer(gameEngine);

		frame.setStatus1(gameEngine);
		frame.updateMainPanel(player, player.getRollResult());

	}

}
