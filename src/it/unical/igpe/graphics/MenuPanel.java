package it.unical.igpe.graphics;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import it.unical.igpe.logic.SoundManager;

@SuppressWarnings("serial")
public class MenuPanel extends JPanel {
	GameFrame gameFrame;
	public static Toolkit toolkit = Toolkit.getDefaultToolkit();
	public static final Dimension screenSize = toolkit.getScreenSize();
	public static SoundManager sound = new SoundManager();
	
	JButton closeButton = new JButton(ImageManager.getClose());
	JButton singlePlayer = new JButton(ImageManager.getSinglePlayer());
	JButton multiPlayer = new JButton(ImageManager.getMultiPlayer());
	JButton mapEditor = new JButton(ImageManager.getMapEditor());
	JButton controls = new JButton(ImageManager.getControls());
	JButton exit = new JButton(ImageManager.getExit());
	
	public MenuPanel(final GameFrame gameFrame) {
		super();
		this.gameFrame = gameFrame;
		initGUI();
		initEH();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ImageManager.getBackground(), 0, 0, screenSize.width, screenSize.height, this);
		closeButton.setLocation(screenSize.width - 110, 10);
		singlePlayer.setLocation(100, (screenSize.height / 2) - 200);
		multiPlayer.setLocation(100, (screenSize.height / 2) - 100);
		mapEditor.setLocation(100, (screenSize.height / 2));
		controls.setLocation(100, (screenSize.height / 2) + 100);
		exit.setLocation(100, (screenSize.height / 2) + 200);
	}
	
	private void initGUI() {
		sound.startMusic(SoundManager.MENUTHEME);
		
		// Exit Button
		closeButton.setContentAreaFilled(false);
		closeButton.setOpaque(false);
		closeButton.setBorder(BorderFactory.createEmptyBorder());
		closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(closeButton);
		
		// SinglePlayer Button
		singlePlayer.setContentAreaFilled(false);
		singlePlayer.setOpaque(false);
		singlePlayer.setBorder(BorderFactory.createEmptyBorder());
		singlePlayer.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(singlePlayer);
		
		// MultiPlayer Button
		multiPlayer.setContentAreaFilled(false);
		multiPlayer.setOpaque(false);
		multiPlayer.setBorder(BorderFactory.createEmptyBorder());
		multiPlayer.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(multiPlayer);
		
		// Editor Button
		mapEditor.setContentAreaFilled(false);
		mapEditor.setOpaque(false);
		mapEditor.setBorder(BorderFactory.createEmptyBorder());
		mapEditor.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(mapEditor);
		
		// Controls Button
		controls.setContentAreaFilled(false);
		controls.setOpaque(false);
		controls.setBorder(BorderFactory.createEmptyBorder());
		controls.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(controls);
		
		// Exit Button
		exit.setContentAreaFilled(false);
		exit.setOpaque(false);
		exit.setBorder(BorderFactory.createEmptyBorder());
		exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(exit);
	}
	
	private void initEH() {
		closeButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					System.exit(0);
				}
			}
		});
		
		singlePlayer.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					gameFrame.switchPanel(gameFrame.getLevelChooserPanel());
				}
			}
		});
		
		multiPlayer.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					gameFrame.switchPanel(gameFrame.getMultiplayerPanel());
				}
			}
		});
		
		mapEditor.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					gameFrame.switchPanel(gameFrame.getEditorPanel());
				}
			}
		});
		
		controls.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					gameFrame.switchPanel(gameFrame.getControlsPanel());
				}
			}
		});
		
		exit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					System.exit(0);
				}
			}
		});
	}
}
