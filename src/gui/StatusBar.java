package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.interfaces.GameEngine;
import model.interfaces.Player;


public class StatusBar extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel leftLabel = new JLabel("", JLabel.LEFT);
	private JLabel rightLabel = new JLabel("", JLabel.CENTER);

	public StatusBar(GameEngine gameEngine, AppFrame frame) {
		setLayout(new GridLayout(1, 2));
		
		leftLabel.setFont (leftLabel.getFont ().deriveFont (15.0f));
		rightLabel.setFont (rightLabel.getFont ().deriveFont (15.0f));
		
		Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
		leftLabel.setBorder(blackBorder);
		rightLabel.setBorder(blackBorder);

		this.add(leftLabel);
		this.add(rightLabel);

	}
	//this method updates the status bar 
	public void setStatus1(Player player) {
		rightLabel.setText("Bet: " + Integer.toString(player.getBet()));
		leftLabel.setText(player.toString());
	}

}
