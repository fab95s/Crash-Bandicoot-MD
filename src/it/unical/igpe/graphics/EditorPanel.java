package it.unical.igpe.graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import it.unical.igpe.graphics.editor.DrawPanel;
import it.unical.igpe.graphics.editor.ToolsPanel;

@SuppressWarnings("serial")
public class EditorPanel extends JPanel {
	GameFrame gameFrame;
	
	JPanel center = new DrawPanel();
	JPanel bottom;
	JScrollPane scrollPane = new JScrollPane(center);
	
	public static final int slotDimension = (MenuPanel.screenSize.width / 50) + 1;
	public static final int widthPanel = slotDimension * 50;
	public static final int heightPanel = slotDimension * 8;
	
	public EditorPanel(final GameFrame gameFrame) {
		super();
		this.gameFrame = gameFrame;
		initGUI();
		initEH();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	private void initGUI() {
		bottom = new ToolsPanel(gameFrame, (DrawPanel) center);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.setLayout(new BorderLayout());
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(bottom, BorderLayout.SOUTH);
		center.setPreferredSize(new Dimension(widthPanel, MenuPanel.screenSize.height));
		bottom.setPreferredSize(new Dimension(MenuPanel.screenSize.width, (MenuPanel.screenSize.height + heightPanel) - MenuPanel.screenSize.height));
	}
	
	private void initEH() {
		
	}
}
