package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

public class AppFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private GameEngine gameEngine = new GameEngineImpl();
	private GameEngineCallback gameEngineCallImpl = new GameEngineCallbackImpl();
	private GameEngineCallback gameEngineCallGUI = new GameEngineCallbackGUI(this);
	
	
	private Window window = new Window();
	private FileMenu fileMenu = new FileMenu(this.gameEngine, this);
	private ToolBar toolBar = new ToolBar(this.gameEngine, this);
	private MainPanel mainPanel = new MainPanel(this.gameEngine, this);
	private StatusBar statusBar = new StatusBar(this.gameEngine, this);
	

	public AppFrame() {
		super("Casino Dice Game");
		setBounds(100, 100, 840, 680);
		setLocation(550,250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
	
		
		gameEngine.addGameEngineCallback(this.gameEngineCallImpl);
		gameEngine.addGameEngineCallback(this.gameEngineCallGUI);
		//adding all the components 
		this.getContentPane().setBackground(Color.red);
		this.setJMenuBar(fileMenu);
		this.add(toolBar, BorderLayout.NORTH);
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(statusBar, BorderLayout.SOUTH);
		this.setJMenuBar(fileMenu);
		
		setVisible(true);
	}

	

	

	public Player createPlayer(GameEngine gameEngine) {
		Player player = window.getPlayerId(gameEngine);
		return player;
	}

	public Player getSelectedPlayer(GameEngine gameEngine) {
		Player player = toolBar.getPlayer(gameEngine);
		return player;
	}

	public void placeBet(GameEngine gameEngine, Player player) {
		window.placeBet(gameEngine, player);
	}
	
	public void updatePlayerList(GameEngine gameEngine) {
		toolBar.addToList(gameEngine);
	}
	//set status bar
	public void setStatus1(GameEngine gameEngne) {
		Player player = getSelectedPlayer(gameEngne);
		statusBar.setStatus1(player);
	}
	
	public void updateMainPanel(Player player, DicePair dicePair) {

		mainPanel.updateMainPanel(player, dicePair);
	}

	public void updateHouse(DicePair dicePair, AppFrame frame, GameEngine gameEngine) {

		mainPanel.updateHouse(dicePair);
	}
	public void updateResult() {

		mainPanel.updateResult();

	}
	//method to reset the house die
	public void resetHouse() {
		mainPanel.resetHouse();
	}

}
