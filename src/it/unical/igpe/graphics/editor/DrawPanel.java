package it.unical.igpe.graphics.editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import it.unical.igpe.graphics.EditorPanel;
import it.unical.igpe.graphics.MenuPanel;

@SuppressWarnings("serial")
public class DrawPanel extends JPanel {
	List<ImagePoint> points = new ArrayList<ImagePoint>();
	Image paintImage = null;
	Character paintChar = null;
	Image background = null;
	
	public DrawPanel() {
		super();
		initGUI();
		initEH();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(background == null) {
			g.setColor(Color.CYAN);
			g.fillRect(0, 0, EditorPanel.widthPanel, MenuPanel.screenSize.height);
		} else {
			g.drawImage(background, 0, 0, EditorPanel.widthPanel, MenuPanel.screenSize.height, this);
		}
		
		for(int i=0; i<EditorPanel.widthPanel; i+=EditorPanel.slotDimension) {
			g.setColor(Color.WHITE);
			g.fillRect(i, 0, 1, EditorPanel.widthPanel);
			g.fillRect(0, i, EditorPanel.widthPanel, 1);
		}
		
		for(final ImagePoint ip : points) {
			if(ip.getFilename() != 'C' && ip.getFilename() != '@') {
				g.drawImage(ip.getImage(), ip.getPoint().x, ip.getPoint().y, EditorPanel.slotDimension, EditorPanel.slotDimension, this);
			} else {
				g.drawImage(ip.getImage(), ip.getPoint().x, (ip.getPoint().y - EditorPanel.slotDimension), EditorPanel.slotDimension, EditorPanel.slotDimension * 2, this);
			}
		}
	}
	
	private void initGUI() {
		
	}
	
	private void initEH() {
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Point p = getGrid2Point(e.getX(), e.getY());
				ImagePoint tmp = new ImagePoint(paintImage, p, paintChar);
				if(e.getButton() == MouseEvent.BUTTON1 && paintImage != null) {
					removeDuplicate(tmp);
					points.add(tmp);
				}
				if(e.getButton() == MouseEvent.BUTTON3) {
					removeDuplicate(tmp);
					points.remove(tmp);
				}
				repaint();
			}
		});
		
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point p = getGrid2Point(e.getX(), e.getY());
				ImagePoint tmp = new ImagePoint(paintImage, p, paintChar);
				if(SwingUtilities.isLeftMouseButton(e) && paintImage != null) {
					removeDuplicate(tmp);
					points.add(tmp);
				}
				if(SwingUtilities.isRightMouseButton(e)) {
					removeDuplicate(tmp);
					points.remove(tmp);
				}
				repaint();
			}
		});
	}
	
	private Point getGrid2Point(final int x, final int y) {
		int pX = x / EditorPanel.slotDimension;
		int pY = y / EditorPanel.slotDimension;
		pX *= EditorPanel.slotDimension;
		pY *= EditorPanel.slotDimension;
		return new Point(pX, pY);
	}
	
	private char getPoint2Grid(final int x, final int y) {
		char type = '-';
		for(final ImagePoint ip : points) {
			if(ip.getPoint().equals(new Point(x, y))) {
				type = ip.getFilename();
				break;
			}
		}
		return type;
	}
	
	private void removeDuplicate(final ImagePoint p) {
		for(final ImagePoint tmp : points) {
			if(tmp.equals(p)) {
				points.remove(tmp);
				return;
			}
			if((tmp.getFilename() == 'C' && paintChar == 'C') || (tmp.getFilename() == '@' && paintChar == '@')) {
				points.remove(tmp);
				return;
			}
		}
	}
	
	public String toString() {
		String map = "";
		for(int i=0; i<MenuPanel.screenSize.height; i+=EditorPanel.slotDimension) {
			for(int j=0; j<MenuPanel.screenSize.width; j+=EditorPanel.slotDimension) {
				if(j == (EditorPanel.slotDimension * 49)) {
					map += map.substring(map.length() - 1);
				} else {
					map += getPoint2Grid(j, i);
				}
			}
			map += "\r\n";
		}
		map = map.substring(0, map.length() - 1);
		return map;
	}
}
