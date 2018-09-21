package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

public class GameEngineImpl implements GameEngine {

	Map<String,Player> players =new HashMap<String,Player>();
	Collection<GameEngineCallback> gec = new ArrayList<GameEngineCallback>();
	GameEngine gameEngine;
	DicePair dicePair = null;



	@Override
	public boolean placeBet(Player player, int bet) {
		if (player.placeBet(bet) == true) {
			player.placeBet(bet);
			return true;
		}
		return false;
	}

	@Override
	public void rollPlayer(Player player, int initialDelay, int finalDelay, int delayIncrement) {
		// Loop when inital delay not over final delay, roll dice and invoke callbacks
		while (initialDelay < finalDelay) {
			DicePair dice=new DicePairImpl(rollDice().getDice1(),rollDice().getDice2(),rollDice().getNumFaces());
			// loop to invoke intermediate callback for each callback engine
			for (GameEngineCallback cb : gec) {
				if (cb != null) {
					cb.intermediateResult(player, dice, this);
				}
			}
		
			delay(delayIncrement);
		
			initialDelay += delayIncrement;
		}
		player.setRollResult(rollDice());
		
		for (GameEngineCallback cb : gec) {
			if (cb != null) {
				cb.result(player, player.getRollResult(), this);
			}
		}

	}

	@Override
	public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {
		DicePair dice;
		while (initialDelay < finalDelay) {
			dice=new DicePairImpl(rollDice().getDice1(),rollDice().getDice2(),rollDice().getNumFaces());
			for (GameEngineCallback cb : gec) {
				if (cb != null) {
					// loop to invoke intermediate house result callback for each callback engine
					cb.intermediateHouseResult(dice, this);
				}
			}
			// delay function to simulate dice rolling
			delay(delayIncrement);
			// increase increment
			initialDelay += delayIncrement;
		}
		// set house result for final roll
		dice=new DicePairImpl(rollDice().getDice1(),rollDice().getDice2(),rollDice().getNumFaces());
		calculateResult();
		for (GameEngineCallback cb : gec) {
			if (cb != null) {
				
				cb.houseResult(dice, this);
			}
		}
	}

	@Override
	public void addPlayer(Player player) {
		players.put(player.getPlayerId(), player);
	}

	@Override
	public Player getPlayer(String id) {
		Player player=players.get(id);
		return player;
	}

	@Override
	public boolean removePlayer(Player player) {
		players.remove(player.getPlayerId());
		// return false if no result
		return false;
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		// add gameEngine callbacks to GameEngineCallback collection
		this.gec.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		// remove gameEngine callbacks from GameEngineCallback collection
		for (GameEngineCallback cb : gec) {
			if (cb == gameEngineCallback) {
				this.gec.remove(gameEngineCallback);
				return true;
			}
		}
		// return false if no result
		return false;
	}
	
	//method that calculates the result of the rolling
		private void calculateResult() {
		
			 	Iterator it = this.players.entrySet().iterator();
			 	while(it.hasNext()){
			 	Map.Entry pair = (Map.Entry)it.next();
			 	Player player=(Player) pair.getValue();
				//logic for winner
			 	if(player.getBet()!=0) {
				if (getDiceTotal(player.getRollResult()) > getDiceTotal(dicePair)) {
					player.setPoints(player.getPoints() + player.getBet());
				}
				//logic for loser
				else if (getDiceTotal(player.getRollResult()) < getDiceTotal(dicePair)) {
					player.setPoints(player.getPoints() - player.getBet());
				}
				// logic for a draw
				else if (getDiceTotal(player.getRollResult()) == getDiceTotal(dicePair)) {
					player.setPoints(player.getPoints());
				}
			 	}
			 	}

		}

	@Override
	public Collection<Player> getAllPlayers() {

		Collection<Player> plyr = players.values();
		return plyr;
	}
	
	// private method to roll dice and return dicepair
		private DicePair rollDice() {
			int roll = 0;
			int dice1 = 0;
			int dice2 = 0;

			roll = (int) (NUM_FACES * Math.random()) + 1;
			dice1 = roll;
			roll = (int) (NUM_FACES * Math.random()) + 1;
			dice2 = roll;

			dicePair = new DicePairImpl(dice1, dice2, NUM_FACES);

			return dicePair;
		}
		

		// get total of 2 dice
		private int getDiceTotal(DicePair dice) {
			int total = dice.getDice1() + dice.getDice2();
			return total;
		}
		
		//method that delays the dice roll
		private void delay(int delay) {
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

}
