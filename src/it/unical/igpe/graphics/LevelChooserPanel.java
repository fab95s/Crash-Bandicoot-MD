package it.unical.igpe.graphics;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import it.unical.igpe.logic.World;

@SuppressWarnings("serial")
public class LevelChooserPanel extends JPanel {
	GameFrame gameFrame;
	
	JFileChooser chooser = new JFileChooser();
	FileNameExtensionFilter nameFilter = new FileNameExtensionFilter("Crash Bandicoot MD Levels", "mdlvl");
	
	private JButton returnButton = new JButton(ImageManager.getArrowBack());
	private JButton level1 = new JButton(ImageManager.getLevel1());
	private JButton level2 = new JButton(ImageManager.getLevel2());
	private JButton level3 = new JButton(ImageManager.getLevel3());
	private JButton selectYourLevel = new JButton(ImageManager.getSelectYourLevel());
	
	public LevelChooserPanel(final GameFrame gameFrame) {
		super();
		this.gameFrame = gameFrame;
		initGUI();
		initEH();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ImageManager.getBackground(), 0, 0, MenuPanel.screenSize.width, MenuPanel.screenSize.height, this);
		g.drawImage(ImageManager.getSelectLevel(), (MenuPanel.screenSize.width / 2) - (ImageManager.getSelectLevel().getWidth(this) / 2), 150, this);
		
		returnButton.setLocation(10, 10);
		level1.setLocation((MenuPanel.screenSize.width / 2) - (ImageManager.getLevel1().getIconWidth() / 2) - 500, (MenuPanel.screenSize.height / 2) - (ImageManager.getLevel1().getIconHeight() / 2));
		level2.setLocation((MenuPanel.screenSize.width / 2) - (ImageManager.getLevel2().getIconWidth() / 2), (MenuPanel.screenSize.height / 2) - (ImageManager.getLevel2().getIconHeight() / 2));
		level3.setLocation((MenuPanel.screenSize.width / 2) - (ImageManager.getLevel3().getIconWidth() / 2) + 500, (MenuPanel.screenSize.height / 2) - (ImageManager.getLevel3().getIconHeight() / 2));
		selectYourLevel.setLocation((MenuPanel.screenSize.width / 2) - (ImageManager.getSelectYourLevel().getIconWidth() / 2), (MenuPanel.screenSize.height / 2) + 300);
	}
	
	private void initGUI() {
		// Back Button
		returnButton.setContentAreaFilled(false);
		returnButton.setOpaque(false);
		returnButton.setBorder(BorderFactory.createEmptyBorder());
		returnButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(returnButton);
		
		// Level 1 Button
		level1.setContentAreaFilled(false);
		level1.setOpaque(false);
		level1.setBorder(BorderFactory.createEmptyBorder());
		level1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(level1);
		
		// Level 2 Button
		level2.setContentAreaFilled(false);
		level2.setOpaque(false);
		level2.setBorder(BorderFactory.createEmptyBorder());
		level2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(level2);
		
		// Level 3 Button
		level3.setContentAreaFilled(false);
		level3.setOpaque(false);
		level3.setBorder(BorderFactory.createEmptyBorder());
		level3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(level3);
		
		// Select Your Level Button
		selectYourLevel.setContentAreaFilled(false);
		selectYourLevel.setOpaque(false);
		selectYourLevel.setBorder(BorderFactory.createEmptyBorder());
		selectYourLevel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(selectYourLevel);
	}
	
	private void initEH() {
		returnButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					gameFrame.switchPanel(gameFrame.getMenuPanel());
				}
			}
		});
		
		level1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					World.selectLevel(LevelChooserPanel.class.getResource("/resources/levels/level1.mdlvl"));
					if(MultiplayerPanel.isMultiplayer) {
						new Thread((Runnable) gameFrame.getMultiplayerGamePanel()).start();
						gameFrame.switchPanel(gameFrame.getMultiplayerGamePanel());
					} else {
						new Thread((Runnable) gameFrame.getSingleplayerGamePanel()).start();
						gameFrame.switchPanel(gameFrame.getSingleplayerGamePanel());
					}
				}
			}
		});
		
		level2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					World.selectLevel(LevelChooserPanel.class.getResource("/resources/levels/level2.mdlvl"));
					new Thread((Runnable) gameFrame.getSingleplayerGamePanel()).start();
					gameFrame.switchPanel(gameFrame.getSingleplayerGamePanel());
				}
			}
		});
		
		level3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					World.selectLevel(LevelChooserPanel.class.getResource("/resources/levels/level3.mdlvl"));
					new Thread((Runnable) gameFrame.getSingleplayerGamePanel()).start();
					gameFrame.switchPanel(gameFrame.getSingleplayerGamePanel());
				}
			}
		});
		
		selectYourLevel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					chooser.setDialogTitle("Load your personal level");
					chooser.setFileFilter(nameFilter);
					int choose = chooser.showOpenDialog(gameFrame);
					if(choose == JFileChooser.APPROVE_OPTION) {
						World.selectLevel(chooser.getSelectedFile());
						if(MultiplayerPanel.isMultiplayer) {
							new Thread((Runnable) gameFrame.getMultiplayerGamePanel()).start();
							gameFrame.switchPanel(gameFrame.getMultiplayerGamePanel());
						} else {
							new Thread((Runnable) gameFrame.getSingleplayerGamePanel()).start();
							gameFrame.switchPanel(gameFrame.getSingleplayerGamePanel());
						}
					}
				}
			}
		});
	}
	
}
