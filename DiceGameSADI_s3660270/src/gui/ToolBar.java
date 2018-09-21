package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;

import controllers.HouseListener;
import controllers.AddPlayerListener;
import controllers.PlaceBetListener;
import controllers.RollListener;
import controllers.SwitchPlayerListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;


public class ToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;
	
	private JButton addPlayerBtn = new JButton("Add player");
	private JButton betBtn = new JButton("Bet");
	private JButton rollBtn = new JButton("Roll");
	private JButton rollHouseBtn = new JButton("Roll House");
	private JComboBox<String> players = new JComboBox<String>();
	SwitchPlayerListener switchPlayerListener;

	public ToolBar(GameEngine gameEngine, AppFrame frame) {
		
		addPlayerBtn.setPreferredSize(new Dimension(100, 50));
		betBtn.setPreferredSize(new Dimension(100, 50));
		rollBtn.setPreferredSize(new Dimension(100, 50));
		rollHouseBtn.setPreferredSize(new Dimension(100, 50));
		players.setPreferredSize(new Dimension(300, 50));
		
		add(addPlayerBtn);
		add(betBtn);
		add(rollBtn);
		add(rollHouseBtn);
		add(players);

		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//all the listeners 
		AddPlayerListener addPlayerListener = new AddPlayerListener(gameEngine, frame);
		addPlayerBtn.addActionListener(addPlayerListener);

		PlaceBetListener placeBetListener = new PlaceBetListener(frame, gameEngine);
		betBtn.addActionListener(placeBetListener);

		RollListener rollListener = new RollListener(frame, gameEngine);
		rollBtn.addActionListener(rollListener);

		HouseListener houseListener = new HouseListener(gameEngine, frame);
		rollHouseBtn.addActionListener(houseListener);
		
		switchPlayerListener = new SwitchPlayerListener(frame, gameEngine);
		players.addActionListener(switchPlayerListener);

	}

	public void addToList(GameEngine gameEngine) {
		players.removeActionListener(switchPlayerListener);
		players.removeAllItems();
		Collection<Player> Players = gameEngine.getAllPlayers();
		Player player;
		Iterator<Player> it = Players.iterator();
		while (it.hasNext()) {
			player = it.next();
			players.addItem(player.toString());
		}

		players.addActionListener(switchPlayerListener);
	}

	public Player getPlayer(GameEngine gameEngine) {
		String player = (String) players.getSelectedItem();
		Player gamer;
		player.toString();
		Collection<Player> plyr = gameEngine.getAllPlayers();
		Iterator<Player> it = plyr.iterator();
		while (it.hasNext()) {
			Player test = it.next();
			if (test.toString().equals(player)) {
				gamer = test;
				return gamer;
			}
		}
		return null;
	}

}
