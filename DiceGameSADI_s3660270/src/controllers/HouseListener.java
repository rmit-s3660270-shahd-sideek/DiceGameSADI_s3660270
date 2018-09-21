package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import gui.AppFrame;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class HouseListener implements ActionListener {

	private GameEngine gameEngine;
	private AppFrame frame;

	public HouseListener(GameEngine gameEngine, AppFrame frame) {
		this.gameEngine = gameEngine;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// new thread to rollHouse if player have rolled dice
		Player player = frame.getSelectedPlayer(gameEngine);
		if (player.getRollResult() != null) {
			new Thread() {
				@Override
				public void run() {
					gameEngine.rollHouse(1, 1000, 200);
				}
			}.start();

		}
		else if (player.getRollResult() == null) {
			JOptionPane.showMessageDialog(frame, "The player has to roll first.");
		}
	}

}
