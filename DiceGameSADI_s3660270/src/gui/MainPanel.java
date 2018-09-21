package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.DicePairImpl;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.Player;


public class MainPanel extends JPanel {

	
	private static final long serialVersionUID = 1L;
	
	private JLabel firstDice = new JLabel("Dice #1: ");
	private JLabel secondDice = new JLabel("Dice #2: ");
	private JLabel rollSum = new JLabel("Total: ");
	private JLabel HouseDice1 = new JLabel("House #1: ");
	private JLabel HouseDice2 = new JLabel("House #2: ");
	private JLabel HouseSum = new JLabel("House Sum: ");
	private JLabel Result = new JLabel("Result: ");

	public MainPanel(GameEngine gameEngine, AppFrame frame) {
		
		firstDice.setFont (firstDice.getFont ().deriveFont (30.0f));
		secondDice.setFont (secondDice.getFont ().deriveFont (30.0f));
		rollSum.setFont (rollSum.getFont ().deriveFont (30.0f));
		HouseDice1.setFont (rollSum.getFont ().deriveFont (30.0f));
		HouseDice2.setFont (rollSum.getFont ().deriveFont (30.0f));
		HouseSum.setFont (rollSum.getFont ().deriveFont (30.0f));
		Result.setFont (rollSum.getFont ().deriveFont (30.0f));
		
		
		firstDice.setForeground(Color.red);
		secondDice.setForeground(Color.red);
		rollSum.setForeground(Color.red);
		HouseDice1.setForeground(Color.red);
		HouseDice2.setForeground(Color.red);
		HouseSum.setForeground(Color.red);
		Result.setForeground(Color.red);
		
		add(firstDice);
		add(secondDice);
		add(rollSum);
		add(HouseDice1);
		add(HouseDice2);
		add(HouseSum);
		add(Result);
		
		//this.add(rollSum, BorderLayout.CENTER);
		
		//Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
		//rollSum.setBorder(blackBorder);
		//HouseSum.setBorder(blackBorder);
		
		setLayout(new GridLayout(3, 2));
		
		

	}
	//update dice panel 
	public void updateMainPanel(Player player, DicePair dicePair) {
		//to handle new added player
		if (player.getRollResult() == null) {
			int dice1 = 0;
			int dice2 = 0;
			DicePair dice = new DicePairImpl(dice1, dice2, 6);
			player.setRollResult(dice);

			firstDice.setText("Dice #1: " + dice1);
			secondDice.setText("Dice #2: " + dice2);
			rollSum.setText("Total: " + (dice1 + dice2));
			resetHouse();
			
		}
		//if player has rolled
		else if (player.getRollResult() != null) {
			int dice1 = dicePair.getDice1();
			int dice2 = dicePair.getDice2();
			int sum = dice1 + dice2;
			firstDice.setText("Dice #1: " + dice1);
			secondDice.setText("Dice #2: " + dice2);
			rollSum.setText("Sum: " + sum);
		}
		//if house has roll, update result
		if (Integer.parseInt(HouseSum.getText().substring(10)) != 0) {
			updateResult();
		} else if (Integer.parseInt(HouseSum.getText().substring(10)) == 0
				&& Integer.parseInt(rollSum.getText().substring(5)) == 0) {
			Result.setText("Result: ");
		}

	}
	//update house dice on panel
	public void updateHouse(DicePair dicePair) {

		int dice1 = dicePair.getDice1();
		int dice2 = dicePair.getDice2();
		int sum = dice1 + dice2;
		HouseDice1.setText("House #1: " + dice1);
		HouseDice2.setText("House #2: " + dice2);
		HouseSum.setText("HouseSum: " + sum);
		updateResult();

	}
	//update results of rolls
	public void updateResult() {
		String result = "";
		if (Integer.parseInt(rollSum.getText().substring(5)) > Integer.parseInt(HouseSum.getText().substring(10))) {
			result = "Win!";
			Result.setText("Result: " + result);
		} else if (Integer.parseInt(rollSum.getText().substring(5)) < Integer.parseInt(HouseSum.getText().substring(10))) {
			result = "Lose..";
			Result.setText("Result: " + result);
		} else if (Integer.parseInt(rollSum.getText().substring(5)) == Integer.parseInt(HouseSum.getText().substring(10))) {
			result = "Tie";
			Result.setText("Result: " + result);
		}

	}
	
	public void resetHouse() {
		HouseDice1.setText("House# 1: 0");
		HouseDice2.setText("House #2: 0");
		HouseSum.setText("HouseSum: 0");
	}

}
