package it.unical.igpe.graphics;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.unical.igpe.logic.World;
import it.unical.igpe.logic.online.Client;
import it.unical.igpe.logic.online.Server;

@SuppressWarnings("serial")
public class MultiplayerPanel extends JPanel {
	GameFrame gameFrame;
	JButton returnButton = new JButton(ImageManager.getArrowBack());
	
	private JTextField nameHost = new JTextField();
	JButton search = new JButton("Search");
	public static boolean isMultiplayer = false;
	
	public MultiplayerPanel(final GameFrame gameFrame) {
		super();
		this.gameFrame = gameFrame;
		initGUI();
		initEH();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ImageManager.getBackground(), 0, 0, MenuPanel.screenSize.width, MenuPanel.screenSize.height, this);
		returnButton.setLocation(10, 10);
		nameHost.setLocation((MenuPanel.screenSize.width / 2) - 400, (MenuPanel.screenSize.height / 2) - 25);
		search.setLocation(100, 100);
	}
	
	private void initGUI() {
		// Back Button
		returnButton.setContentAreaFilled(false);
		returnButton.setOpaque(false);
		returnButton.setBorder(BorderFactory.createEmptyBorder());
		returnButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(returnButton);
		
		this.add(search);
		
		nameHost.setPreferredSize(new Dimension(800, 50));
		nameHost.setFont(new Font("SansSerif", Font.BOLD, 45));
		nameHost.setHorizontalAlignment(JTextField.CENTER);
		nameHost.setBounds(100, 200, 300, 800);
		this.add(nameHost);
	}
	
	private void initEH() {
		returnButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					isMultiplayer = false;
					gameFrame.switchPanel(gameFrame.getMenuPanel());
				}
			}
		});
		
		search.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(!nameHost.getText().equals("")) {
						isMultiplayer = true;
						if(!Client.connect(nameHost.getText())) {
							Server.initServer();
							gameFrame.switchPanel(gameFrame.getLevelChooserPanel());
						} else {
							World.selectLevel(LevelChooserPanel.class.getResource("/resources/levels/level1.mdlvl"));
							new Thread((Runnable) gameFrame.getMultiplayerGamePanel()).start();
							gameFrame.switchPanel(gameFrame.getMultiplayerGamePanel());
						}
					}
				}
			}
		});
	}
	
}
