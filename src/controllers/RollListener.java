package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import gui.AppFrame;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class RollListener implements ActionListener {

	private AppFrame frame;
	private GameEngine gameEngine;
	private Player player;

	public RollListener(AppFrame frame, GameEngine gameEngine) {
		this.frame = frame;
		this.gameEngine = gameEngine;
	}

	
	public void actionPerformed(ActionEvent e) {
		player = frame.getSelectedPlayer(gameEngine);
		//new thread to roll player after player placed bet
		if (player.getBet() == 0) {
			JOptionPane.showMessageDialog(frame, "Place a bet first.");
		} else {
			new Thread() {
				
				public void run() {
					gameEngine.rollPlayer(player, 1, 1000, 200);
					frame.resetHouse();
				}
			}.start();
		}

	}

}
