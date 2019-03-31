package it.unical.igpe.graphics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	private JPanel multiplayerPanel = new MultiplayerPanel(this);
	private JPanel levelChooserPanel = new LevelChooserPanel(this);
	private JPanel singleplayerGamePanel = new SingleplayerGamePanel(this);
	private JPanel multiplayerGamePanel = new MultiplayerGamePanel(this);
	private JPanel menuPanel = new MenuPanel(this);
	private JPanel editorPanel = new EditorPanel(this);
	private JPanel controlsPanel = new ControlsPanel(this);
	
	public static Font font;
	
	public GameFrame() {
		super();
		this.setTitle("Crash Bandicoot MD");
		this.setSize(720, 480);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setContentPane(menuPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File(GameFrame.class.getResource("/resources/Font.ttf").getPath().replace("%20", " ").replace("file:", ""))).deriveFont(Font.BOLD, 100);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void switchPanel(final JPanel panel) {
		this.setContentPane(panel);
		this.invalidate();
		this.validate();
	}
	
	public JPanel getMultiplayerPanel() {
		return multiplayerPanel;
	}
	
	public JPanel getLevelChooserPanel() {
		return levelChooserPanel;
	}
	
	public JPanel getSingleplayerGamePanel() {
		return singleplayerGamePanel;
	}
	
	public JPanel getMultiplayerGamePanel() {
		return multiplayerGamePanel;
	}
	
	public JPanel getMenuPanel() {
		return menuPanel;
	}
	
	public JPanel getEditorPanel() {
		return editorPanel;
	}
	
	public JPanel getControlsPanel() {
		return controlsPanel;
	}
	
	public static void main(String[] args) {
		JFrame frame = new GameFrame();
		frame.setVisible(true);
	}
}
