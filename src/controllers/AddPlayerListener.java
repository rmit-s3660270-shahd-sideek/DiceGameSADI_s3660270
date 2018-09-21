package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.AppFrame;
import model.interfaces.GameEngine;

public class AddPlayerListener implements ActionListener {

	private AppFrame frame;
	private GameEngine gameEngine;

	public AddPlayerListener(GameEngine gameEngine, AppFrame frame) {
		this.frame = frame;
		this.gameEngine = gameEngine;
	}

	
	// a listener that adds to the list of players in the collection 
	public void actionPerformed(ActionEvent e) {
		gameEngine.addPlayer(frame.createPlayer(gameEngine));
		frame.updatePlayerList(gameEngine);
	}

}
