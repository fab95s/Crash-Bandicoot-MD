package it.unical.igpe.graphics;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import it.unical.igpe.logic.KeyController;

@SuppressWarnings("serial")
public class ControlsPanel extends JPanel {
	GameFrame gameFrame;
	JButton returnButton = new JButton(ImageManager.getArrowBack());
	KeyController keyController = new KeyController();
	Image controls = ImageManager.getKeyboard();
	
	JButton test = new JButton("Cambio UP " + (char) KeyController.UP);
	
	public ControlsPanel(final GameFrame gameFrame) {
		super();
		this.gameFrame = gameFrame;
		initGUI();
		initEH();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ImageManager.getBackground(), 0, 0, MenuPanel.screenSize.width, MenuPanel.screenSize.height, this);
		g.drawImage(controls, (MenuPanel.screenSize.width / 2) - 300, (MenuPanel.screenSize.height / 2) - 105, this);
		returnButton.setLocation(10, 10);
	}
	
	private void initGUI() {
		// Back Button
		returnButton.setContentAreaFilled(false);
		returnButton.setOpaque(false);
		returnButton.setBorder(BorderFactory.createEmptyBorder());
		returnButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(returnButton);
		
		this.add(test);
	}
	
	private void initEH() {
		returnButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					gameFrame.switchPanel(gameFrame.getMenuPanel());
				}
			}
		});
		
		test.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				keyController.setUp(e.getKeyCode());
				keyController.updateControls();
				test.setText("Cambio UP " + (char) e.getKeyChar());
				//test.removeKeyListener(this);
			}
		});
	}
}
