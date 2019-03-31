package it.unical.igpe.graphics.editor;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import it.unical.igpe.graphics.EditorPanel;
import it.unical.igpe.graphics.GameFrame;
import it.unical.igpe.graphics.ImageManager;
import it.unical.igpe.graphics.MenuPanel;
import it.unical.igpe.logic.LevelManager;

@SuppressWarnings("serial")
public class ToolsPanel extends JPanel {
	GameFrame gameFrame;
	DrawPanel drawPanel;
	LevelManager levelManager = new LevelManager();
	JPanel backgroundsPanel = new JPanel();
	
	JButton returnButton = new JButton(ImageManager.getArrowBack());
	JButton saveMap = new JButton(ImageManager.getSave());
	JButton loadMap = new JButton(ImageManager.getLoad());
	
	JFileChooser chooser = new JFileChooser();
	FileNameExtensionFilter nameFilter = new FileNameExtensionFilter("Crash Bandicoot MD Levels", "mdlvl");
	JComboBox<String> musicList = new JComboBox<String>(new String[] {
			"Select the music for this level", "Diggin It", "Hang Eight", "Hog Wild", "Rock It", "Skull Route"
	});
	
	String backgroundSelected = "None";
	
	Image crystalImage = ImageManager.getCrystal().getScaledInstance(EditorPanel.slotDimension, EditorPanel.slotDimension * 2, Image.SCALE_FAST);
	Image crashImage = ImageManager.getCrashBandicoot().getScaledInstance(EditorPanel.slotDimension, EditorPanel.slotDimension * 2, Image.SCALE_FAST);
	Image turtleImage = ImageManager.getTurtleDx().getImage().getScaledInstance(EditorPanel.slotDimension, EditorPanel.slotDimension * 2, Image.SCALE_FAST);
	
	Image grassImage = ImageManager.getGrassBlock().getScaledInstance(EditorPanel.slotDimension, EditorPanel.slotDimension, Image.SCALE_FAST);
	Image landImage = ImageManager.getLandBlock().getScaledInstance(EditorPanel.slotDimension, EditorPanel.slotDimension, Image.SCALE_FAST);
	Image waterImage = ImageManager.getWaterBlock().getImage().getScaledInstance(EditorPanel.slotDimension, EditorPanel.slotDimension, Image.SCALE_FAST);
	Image steelImage = ImageManager.getSteelBlock().getScaledInstance(EditorPanel.slotDimension, EditorPanel.slotDimension, Image.SCALE_FAST);
	
	Image wumpaImage = ImageManager.getWumpaBox().getScaledInstance(EditorPanel.slotDimension, EditorPanel.slotDimension, Image.SCALE_FAST);
	Image lifeImage = ImageManager.getLifeBox().getScaledInstance(EditorPanel.slotDimension, EditorPanel.slotDimension, Image.SCALE_FAST);
	Image nitroImage = ImageManager.getNitroBox().getScaledInstance(EditorPanel.slotDimension, EditorPanel.slotDimension, Image.SCALE_FAST);
	
	JButton crystalButton = new JButton(new ImageIcon(crystalImage));
	JButton crashButton = new JButton(new ImageIcon(crashImage));
	JButton turtleButton = new JButton(new ImageIcon(turtleImage));
	
	JButton grassButton = new JButton(new ImageIcon(grassImage));
	JButton landButton = new JButton(new ImageIcon(landImage));
	JButton waterButton = new JButton(new ImageIcon(waterImage));
	JButton steelButton = new JButton(new ImageIcon(steelImage));
	
	JButton wumpaButton = new JButton(new ImageIcon(wumpaImage));
	JButton lifeButton = new JButton(new ImageIcon(lifeImage));
	JButton nitroButton = new JButton(new ImageIcon(nitroImage));
	
	JButton gameBck1 = new JButton(new ImageIcon(ImageManager.getGameBackground1().getScaledInstance((MenuPanel.screenSize.width - 720) / 2, 200, Image.SCALE_FAST)));
	JButton gameBck2 = new JButton(new ImageIcon(ImageManager.getGameBackground2().getScaledInstance((MenuPanel.screenSize.width - 720) / 2, 200, Image.SCALE_FAST)));
	JButton gameBck3 = new JButton(new ImageIcon(ImageManager.getGameBackground3().getScaledInstance((MenuPanel.screenSize.width - 720) / 2, 200, Image.SCALE_FAST)));
	
	public ToolsPanel(final GameFrame gameFrame, final DrawPanel drawPanel) {
		super();
		this.gameFrame = gameFrame;
		this.drawPanel = drawPanel;
		initGUI();
		initEH();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		returnButton.setLocation(100 - (ImageManager.getArrowBack().getIconWidth() / 2), 10);
		saveMap.setLocation(100 - (ImageManager.getSave().getIconWidth() / 2), 150 - (ImageManager.getSave().getIconHeight() / 2));
		loadMap.setLocation(100 - (ImageManager.getLoad().getIconWidth() / 2), 250 - (ImageManager.getLoad().getIconHeight() / 2));
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 200, EditorPanel.heightPanel);
		
		g.setColor(Color.GRAY);
		g.fillRect(200, 0, 500, EditorPanel.heightPanel);
		musicList.setLocation(220, 10);
		
		crystalButton.setLocation((EditorPanel.slotDimension * 8) + 200, 80);
		crashButton.setLocation((EditorPanel.slotDimension * 8) + 200, (EditorPanel.slotDimension * 3) + 80);
		turtleButton.setLocation((EditorPanel.slotDimension * 9) + 200, (EditorPanel.slotDimension * 4) + 80);
		
		landButton.setLocation(210, 90);
		grassButton.setLocation(EditorPanel.slotDimension + 220, 90);
		waterButton.setLocation((EditorPanel.slotDimension * 2) + 230, 90);
		steelButton.setLocation((EditorPanel.slotDimension * 3) + 240, 90);
		
		wumpaButton.setLocation(210, EditorPanel.slotDimension + 120);
		lifeButton.setLocation(EditorPanel.slotDimension + 220, EditorPanel.slotDimension + 120);
		nitroButton.setLocation((EditorPanel.slotDimension * 2) + 230, EditorPanel.slotDimension + 120);
		
		g.setColor(Color.BLACK);
		g.drawString("World Blocks", 210, 80);
		g.drawString("World Boxs", 210, EditorPanel.slotDimension + 110);
		
		backgroundsPanel.setLocation(700, 0);
	}

	private void initGUI() {
		musicList.setPreferredSize(new Dimension(460, 50));
		this.add(musicList);
		
		// Return Button
		returnButton.setContentAreaFilled(false);
		returnButton.setOpaque(false);
		returnButton.setBorder(BorderFactory.createEmptyBorder());
		returnButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(returnButton);
		
		// Save Button
		saveMap.setContentAreaFilled(false);
		saveMap.setOpaque(false);
		saveMap.setBorder(BorderFactory.createEmptyBorder());
		saveMap.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(saveMap);
		
		// Load Button
		loadMap.setContentAreaFilled(false);
		loadMap.setOpaque(false);
		loadMap.setBorder(BorderFactory.createEmptyBorder());
		loadMap.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(loadMap);
		
		crystalButton.setContentAreaFilled(false);
		crystalButton.setOpaque(false);
		crystalButton.setBorder(BorderFactory.createEmptyBorder());
		crystalButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(crystalButton);
		
		crashButton.setContentAreaFilled(false);
		crashButton.setOpaque(false);
		crashButton.setBorder(BorderFactory.createEmptyBorder());
		crashButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(crashButton);
		
		turtleButton.setContentAreaFilled(false);
		turtleButton.setOpaque(false);
		turtleButton.setBorder(BorderFactory.createEmptyBorder());
		turtleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(turtleButton);
		
		grassButton.setContentAreaFilled(false);
		grassButton.setOpaque(false);
		grassButton.setBorder(BorderFactory.createEmptyBorder());
		grassButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(grassButton);
		
		landButton.setContentAreaFilled(false);
		landButton.setOpaque(false);
		landButton.setBorder(BorderFactory.createEmptyBorder());
		landButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(landButton);
		
		waterButton.setContentAreaFilled(false);
		waterButton.setOpaque(false);
		waterButton.setBorder(BorderFactory.createEmptyBorder());
		waterButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(waterButton);
		
		steelButton.setContentAreaFilled(false);
		steelButton.setOpaque(false);
		steelButton.setBorder(BorderFactory.createEmptyBorder());
		steelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(steelButton);
		
		wumpaButton.setContentAreaFilled(false);
		wumpaButton.setOpaque(false);
		wumpaButton.setBorder(BorderFactory.createEmptyBorder());
		wumpaButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(wumpaButton);
		
		lifeButton.setContentAreaFilled(false);
		lifeButton.setOpaque(false);
		lifeButton.setBorder(BorderFactory.createEmptyBorder());
		lifeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(lifeButton);
		
		nitroButton.setContentAreaFilled(false);
		nitroButton.setOpaque(false);
		nitroButton.setBorder(BorderFactory.createEmptyBorder());
		nitroButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(nitroButton);
		
		gameBck1.setContentAreaFilled(false);
		gameBck1.setOpaque(false);
		gameBck1.setBorder(BorderFactory.createEmptyBorder());
		gameBck1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backgroundsPanel.add(gameBck1);
		
		gameBck2.setContentAreaFilled(false);
		gameBck2.setOpaque(false);
		gameBck2.setBorder(BorderFactory.createEmptyBorder());
		gameBck2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backgroundsPanel.add(gameBck2);
		
		gameBck3.setContentAreaFilled(false);
		gameBck3.setOpaque(false);
		gameBck3.setBorder(BorderFactory.createEmptyBorder());
		gameBck3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backgroundsPanel.add(gameBck3);
		
		backgroundsPanel.setPreferredSize(new Dimension((MenuPanel.screenSize.width - 500), EditorPanel.heightPanel));
		this.add(backgroundsPanel);
	}

	private void initEH() {
		musicList.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(musicList.getItemAt(0).equals("Select the music for this level")) {
					musicList.removeItemAt(0);
				}
				MenuPanel.sound.switchMusic((String) musicList.getSelectedItem());
			}
		});
		
		returnButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(drawPanel.points.size() != 0) {
						int choose = JOptionPane.showConfirmDialog(gameFrame, "Vuoi salvare le modifiche effettuate?", "Attenzione", JOptionPane.YES_NO_OPTION);
						if(choose == JOptionPane.NO_OPTION) {
							drawPanel.points.clear();
						}
					}
					gameFrame.switchPanel(gameFrame.getMenuPanel());
				}
			}
		});
		
		saveMap.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1 && drawPanel.points.size() != 0) {
					chooser.setDialogTitle("Save this level");
					chooser.setFileFilter(nameFilter);
					int choose = chooser.showSaveDialog(gameFrame);
					if(choose == JFileChooser.APPROVE_OPTION) {
						String map = "Music:" + (String) musicList.getSelectedItem() + "\r\n";
						map += "Background:" + backgroundSelected + "\r\n\r\n";
						map += drawPanel.toString();
						levelManager.saveLevel(chooser.getSelectedFile(), map);
					}
				}
			}
		});
		
		loadMap.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					chooser.setDialogTitle("Load a level");
					chooser.setFileFilter(nameFilter);
					int choose = chooser.showOpenDialog(gameFrame);
					if(choose == JFileChooser.APPROVE_OPTION) {
						drawPanel.points.clear();
						levelManager.loadLevel(chooser.getSelectedFile());
						musicList.setSelectedItem(levelManager.getMusic());
						int i = 0, j = 0;
						for(final String mapLine : levelManager.getLevel()) {
							i = 0;
							for(final char mapChar : mapLine.toCharArray()) {
								final Point point = new Point((i * EditorPanel.slotDimension), (j * EditorPanel.slotDimension));
								ImagePoint ip = switchImagePoint(mapChar, point);
								if(ip != null) {
									drawPanel.points.add(ip);
								}
								i++;
							}
							j++;
						}
						drawPanel.repaint();
					}
				}
			}
		});
		
		crystalButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					drawPanel.paintImage = crystalImage;
					drawPanel.paintChar = 'C';
				}
			}
		});
		
		crashButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					drawPanel.paintImage = crashImage;
					drawPanel.paintChar = '@';
				}
			}
		});
		
		turtleButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					drawPanel.paintImage = turtleImage;
					drawPanel.paintChar = '_';
				}
			}
		});
		
		grassButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					drawPanel.paintImage = grassImage;
					drawPanel.paintChar = 'G';
				}
			}
		});
		
		landButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					drawPanel.paintImage = landImage;
					drawPanel.paintChar = 'L';
				}
			}
		});
		
		waterButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					drawPanel.paintImage = waterImage;
					drawPanel.paintChar = 'W';
				}
			}
		});
		
		steelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					drawPanel.paintImage = steelImage;
					drawPanel.paintChar = 'S';
				}
			}
		});
		
		wumpaButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					drawPanel.paintImage = wumpaImage;
					drawPanel.paintChar = '1';
				}
			}
		});
		
		lifeButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					drawPanel.paintImage = lifeImage;
					drawPanel.paintChar = '2';
				}
			}
		});
		
		nitroButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					drawPanel.paintImage = nitroImage;
					drawPanel.paintChar = '3';
				}
			}
		});
		
		gameBck1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					drawPanel.background = ImageManager.getGameBackground1();
					backgroundSelected = "Background 1";
					drawPanel.repaint();
				}
			}
		});
		
		gameBck2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					drawPanel.background = ImageManager.getGameBackground2();
					backgroundSelected = "Background 2";
					drawPanel.repaint();
				}
			}
		});
		
		gameBck3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					drawPanel.background = ImageManager.getGameBackground3();
					backgroundSelected = "Background 3";
					drawPanel.repaint();
				}
			}
		});
	}
	
	private ImagePoint switchImagePoint(final char imageType, final Point point) {
		ImagePoint tmp = null;
		switch(imageType) {
			case 'C':
				tmp = new ImagePoint(crystalImage, point, 'C');
				break;
			case '@':
				tmp = new ImagePoint(crashImage, point, '@');
				break;
			case '_':
				tmp = new ImagePoint(turtleImage, point, '_');
				break;
			
			case 'L':
				tmp = new ImagePoint(landImage, point, 'L');
				break;
			case 'G':
				tmp = new ImagePoint(grassImage, point, 'G');
				break;
			case 'W':
				tmp = new ImagePoint(waterImage, point, 'W');
				break;
			case 'S':
				tmp = new ImagePoint(steelImage, point, 'S');
				break;
				
			case '1':
				tmp = new ImagePoint(wumpaImage, point, '1');
				break;
			case '2':
				tmp = new ImagePoint(lifeImage, point, '2');
				break;
			case '3':
				tmp = new ImagePoint(nitroImage, point, '3');
				break;
			default:
				break;
		}
		return tmp;
	}
}
