package gui;

import javax.swing.SwingUtilities;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

public class GameEngineCallbackGUI implements GameEngineCallback {

	private AppFrame frame;

	public GameEngineCallbackGUI(AppFrame frame) {
		this.frame = frame;
	}

	@Override
	public void intermediateResult(final Player player, final DicePair dicePair, GameEngine gameEngine) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.updateMainPanel(player, dicePair);
			}
		});

	}

	@Override
	public void result(final Player player, final DicePair result, GameEngine gameEngine) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.updateMainPanel(player, result);

			}
		});
	}

	@Override
	public void intermediateHouseResult(final DicePair dicePair, final GameEngine gameEngine) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.updateHouse(dicePair, frame, gameEngine);
			}
		});

	}

	@Override
	public void houseResult(final DicePair result, final GameEngine gameEngine) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.updateHouse(result, frame, gameEngine);
				//update new points of player in drop down list 
				//not sure if its needed but including it anyway
				frame.updatePlayerList(gameEngine);
			}
		});
	}

}
