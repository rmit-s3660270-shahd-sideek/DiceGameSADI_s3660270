package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controllers.AddPlayerListener;
import model.interfaces.GameEngine;


public class FileMenu extends JMenuBar {

	private static final long serialVersionUID = 1L;
	
	private JMenu fileMenu = new JMenu("File");
	private JMenuItem saveGame = new JMenuItem("Save"); //this is just for show 
	private JMenuItem exitWindow = new JMenuItem("Exit"); //has an exit functionality

	public FileMenu(GameEngine gameEngine, AppFrame frame) {
		fileMenu.setFont (fileMenu.getFont ().deriveFont (20.0f));
		this.add(fileMenu);
		fileMenu.add(saveGame);
		fileMenu.add(exitWindow);

		//example taken from stackoverflow
		exitWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

}
