package it.unical.igpe.graphics;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import it.unical.igpe.logic.SoundManager;
import it.unical.igpe.logic.online.Client;
import it.unical.igpe.logic.online.Server;
import it.unical.igpe.managers.GameManager;

@SuppressWarnings("serial")
public class MultiplayerGamePanel extends GamePanel implements Runnable {
	GameFrame gameFrame;
	private boolean stop = true;
	private boolean endGif = false;
	private boolean getInfo = false;
	public static boolean stopThread = true;
	public static boolean pause = false;
	public static boolean onCrystal = false;
	public static int brokenBoxes = 0;
	
	public static boolean clientConnected = false;
	private Point crash = new Point();
	private Point coco = new Point();
	
	private final GameManager gameManager = new GameManager();
	private List<KeyEvent> keyPressed = new ArrayList<KeyEvent>();
	
	private JButton resume = new JButton(ImageManager.getResume());
	private JButton backToMenu = new JButton(ImageManager.getBackToMenu());
	private JButton exit = new JButton(ImageManager.getExit());
	
	private ImageIcon crashWoah = new ImageIcon(ImageManager.getCrashWoah().getImage().getScaledInstance(MenuPanel.screenSize.width, MenuPanel.screenSize.height, Image.SCALE_FAST));
	
	public MultiplayerGamePanel(final GameFrame gameFrame) {
		super();
		this.gameFrame = gameFrame;
		initGUI();
		initEH();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(Server.accepted || clientConnected) {
			render(g);
		} else {
			g.drawImage(ImageManager.getBackground(), 0, 0, MenuPanel.screenSize.width, MenuPanel.screenSize.height, this);
			g.drawImage(ImageManager.getWaitForAnotherPlayer(), (MenuPanel.screenSize.width / 2) - (ImageManager.getWaitForAnotherPlayer().getWidth(this) / 2), (MenuPanel.screenSize.height / 2) - (ImageManager.getWaitForAnotherPlayer().getHeight(this) / 2), this);
		}
	}
	
	private void render(Graphics g) {
		if(gameManager.getWorld().getLevel().getBackground().equals("None")) {
			g.setColor(Color.CYAN);
			g.fillRect(0, 0, MenuPanel.screenSize.width, MenuPanel.screenSize.height);
		} else {
			switch(gameManager.getWorld().getLevel().getBackground()) {
			case "Background 1":
				g.drawImage(ImageManager.getGameBackground1(), 0, 0, MenuPanel.screenSize.width, MenuPanel.screenSize.height, this);
				break;
			case "Background 2":
				g.drawImage(ImageManager.getGameBackground2(), 0, 0, MenuPanel.screenSize.width, MenuPanel.screenSize.height, this);
				break;
			case "Background 3":
				g.drawImage(ImageManager.getGameBackground3(), 0, 0, MenuPanel.screenSize.width, MenuPanel.screenSize.height, this);
				break;
			}
		}
		gameManager.getWorld().getCrystal().draw(g);
		gameManager.getWorld().getCrash().draw(g, this);
		gameManager.getWorld().getCoco().draw(g);
		
		gameManager.getTurtle().draw(g, this);
		
		gameManager.getWumpaBox().draw(g);
		gameManager.getLifeBox().draw(g);
		gameManager.getNitroBox().draw(g);
		
		gameManager.getGrassBlock().draw(g);
		gameManager.getLandBlock().draw(g);
		gameManager.getWaterBlock().draw(g, this);
		gameManager.getSteelBlock().draw(g);
		
		if(getInfo) {
			g.setFont(GameFrame.font);
			g.setColor(new Color(246, 140, 26, 230));
			g.drawImage(ImageManager.getWumpaFruit(), 30, 30, 100, 100, this);
			g.drawString("" + gameManager.getWorld().getCrash().getWumpaFruits(), 160, 120);
			g.drawImage(ImageManager.getCrashLife(), 30, 130, 100, 100, this);
			g.drawString("" + gameManager.getWorld().getCrash().getLives(), 160, 225);
			g.drawImage(ImageManager.getBox(), 30, 230, 100, 100, this);
			g.drawString("" + brokenBoxes, 160, 320);
		}
		
		if(endGif) {
			crashWoah.paintIcon(this, g, 0, 0);
			sleepQuietly();
		}
		
		if(onCrystal) {
			g.drawImage(ImageManager.getEndLevel(), ((MenuPanel.screenSize.width / 2) - (ImageManager.getEndLevel().getWidth(this) / 2)), 200, this);
		}
		
		if(pause) {
			resume.setVisible(true);
			backToMenu.setVisible(true);
			exit.setVisible(true);
			g.setColor(new Color(0, 0, 0, 120));
			g.fillRect(0, 0, MenuPanel.screenSize.width, MenuPanel.screenSize.height);
			g.drawImage(ImageManager.getPause(), ((MenuPanel.screenSize.width / 2) - (ImageManager.getPause().getWidth(this) / 2) + 100), ((MenuPanel.screenSize.height / 2) - 300), this);
			ImageManager.getAkuAku().paintIcon(this, g, 200, ((MenuPanel.screenSize.height / 2) - 500));
			resume.setLocation(((MenuPanel.screenSize.width / 2) - (ImageManager.getResume().getIconWidth() / 2)), ((MenuPanel.screenSize.height / 2) + 100));
			backToMenu.setLocation(((MenuPanel.screenSize.width / 2) - (ImageManager.getBackToMenu().getIconWidth() / 2)), ((MenuPanel.screenSize.height / 2) + 200));
			exit.setLocation(((MenuPanel.screenSize.width / 2) - (ImageManager.getExit().getIconWidth() / 2)), ((MenuPanel.screenSize.height / 2) + 300));
		} else {
			resume.setVisible(false);
			backToMenu.setVisible(false);
			exit.setVisible(false);
		}
	}
	
	private void initGUI() {
		// Resume Button
		resume.setContentAreaFilled(false);
		resume.setOpaque(false);
		resume.setBorder(BorderFactory.createEmptyBorder());
		resume.setCursor(new Cursor(Cursor.HAND_CURSOR));
		resume.setVisible(false);
		this.add(resume);
		
		// Back to Menu Button
		backToMenu.setContentAreaFilled(false);
		backToMenu.setOpaque(false);
		backToMenu.setBorder(BorderFactory.createEmptyBorder());
		backToMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backToMenu.setVisible(false);
		this.add(backToMenu);
		
		// Exit Button
		exit.setContentAreaFilled(false);
		exit.setOpaque(false);
		exit.setBorder(BorderFactory.createEmptyBorder());
		exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		exit.setVisible(false); 
		this.add(exit);
	}
	
	private void initEH() {
		this.addKeyListener(new KeyAdapter() {
			public synchronized void keyPressed(KeyEvent e) {
				stop = false;
				if(e.getKeyChar() != ' ' && e.getKeyChar() != 's' && e.getKeyChar() != 'i' && e.getKeyCode() != KeyEvent.VK_ESCAPE) {
					keyPressed.add(e);
				}
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					pause = !pause;
				}
				if(onCrystal && e.getKeyChar() == 'e') {
					gameManager.setEnd(true);
				}
				synchronized (this) {
					if(!clientConnected) {
						switch(e.getKeyChar()) {
						case 'i':
							if(!getInfo) {
								new Thread(() -> {
									try {
										getInfo = true;
										Thread.sleep(2500);
										getInfo = false;
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
								}).start();
							}
							break;
						case ' ':
							if(!gameManager.getWorld().getCrash().isJumping()) {
								new Thread(() -> {
									try {
										for(int i=1; i<=20; i++) {
											if(!gameManager.getWorld().getCrash().isFalling()) {
												gameManager.getWorld().getCrash().setFalling(false);
												gameManager.getWorld().getCrash().setVelY(-4);
												gameManager.getWorld().getCrash().update();
												Thread.sleep(10);
											}
										}
										gameManager.getWorld().getCrash().setJumping(true);
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
								}).start();
							}
							break;
						case 's':
							if(!gameManager.getWorld().getCrash().isRotation()) {
								new Thread(() -> {
									try {
										gameManager.getWorld().getCrash().setRotation(true);
										gameManager.getWorld().getSounds().startMusic(SoundManager.SPIN);
										Thread.sleep(800);
										gameManager.getWorld().getCrash().setRotation(false);
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
								}).start();
							}
							break;
						}
					} else {
						switch(e.getKeyChar()) {
						case 'i':
							if(!getInfo) {
								new Thread(() -> {
									try {
										getInfo = true;
										Thread.sleep(2500);
										getInfo = false;
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
								}).start();
							}
							break;
						case ' ':
							if(!gameManager.getWorld().getCoco().isJumping()) {
								new Thread(() -> {
									try {
										for(int i=1; i<=20; i++) {
											if(!gameManager.getWorld().getCoco().isFalling()) {
												gameManager.getWorld().getCoco().setFalling(false);
												gameManager.getWorld().getCoco().setVelY(-4);
												gameManager.getWorld().getCoco().update();
												Thread.sleep(10);
											}
										}
										gameManager.getWorld().getCoco().setJumping(true);
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
								}).start();
							}
							break;
						case 's':
							if(!gameManager.getWorld().getCoco().isRotation()) {
								new Thread(() -> {
									try {
										gameManager.getWorld().getCoco().setRotation(true);
										gameManager.getWorld().getSounds().startMusic(SoundManager.SPIN);
										Thread.sleep(800);
										gameManager.getWorld().getCoco().setRotation(false);
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
								}).start();
							}
							break;
						}
					}
				}
			}
			
			public synchronized void keyReleased(KeyEvent e) {
				stop = true;
				if(e.getKeyChar() != ' ' && e.getKeyChar() != 's' && e.getKeyChar() != 'i' && e.getKeyCode() != KeyEvent.VK_ESCAPE) {
					keyPressed.remove(e);
				}
				keyPressed.clear();
			}
		});
		
		resume.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					pause = false;
				}
			}
		});
		
		backToMenu.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					gameManager.setEnd(true);
					MenuPanel.sound.switchMusic("Menu Theme");
					gameFrame.switchPanel(gameFrame.getMenuPanel());
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
	
	private synchronized void handleActions() {
		if(!endGif && gameManager.gameOver()) {
			endGif = true;
			MenuPanel.sound.stopMusic();
			MenuPanel.sound.startMusic(SoundManager.CRASH_WOAH);
		}
		
		synchronized (keyPressed) {
			if(!clientConnected) {
				for(KeyEvent lastKey : keyPressed) {
					switch(lastKey.getKeyChar()) {
					case 'a':
						gameManager.getWorld().getCrash().setVelocity(-3, 0);
						break;
					case 'd':
						gameManager.getWorld().getCrash().setVelocity(3, 0);
						break;
					default:
						gameManager.getWorld().getCrash().setVelocity(0, 0);
						break;
					}
				}
				if(stop) {
					gameManager.getWorld().getCrash().setVelocity(0, 0);
				}
				gameManager.getWorld().getCoco().setX(coco.x);
				gameManager.getWorld().getCoco().setY(coco.y);
			} else {
				for(KeyEvent lastKey : keyPressed) {
					switch(lastKey.getKeyChar()) {
					case 'a':
						gameManager.getWorld().getCoco().setVelocity(-3, 0);
						break;
					case 'd':
						gameManager.getWorld().getCoco().setVelocity(3, 0);
						break;
					default:
						gameManager.getWorld().getCoco().setVelocity(0, 0);
						break;
					}
				}
				if(stop) {
					gameManager.getWorld().getCoco().setVelocity(0, 0);
				}
				System.out.println("Crash X:" + crash.x + " - Y:" + crash.y);
				gameManager.getWorld().getCrash().setX(crash.x);
				gameManager.getWorld().getCrash().setY(crash.y);
			}
		}
	}
	
	private void sleepQuietly() {
		if(stopThread) {
			stopThread = false;
			new Thread(() -> {
				try {
					Thread.sleep(7300);
					MenuPanel.sound.switchMusic("Menu Theme");
					gameFrame.switchPanel(gameFrame.getLevelChooserPanel());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		}
	}
	
	private void initOnline() {
		if(!Server.accepted && !clientConnected) {
			Server.listenForServerRequest();
			String level = "";
			for(final String str : gameManager.getWorld().getLevel().getLevel()) {
				level += str;
			}
			Server.sendData(level);
		}
	}
	
	private void init() {
		gameManager.getWorld().getEnemys().initTurtles();
		stop = true;
		pause = false;
		stopThread = true;
		endGif = false;
		getInfo = false;
		onCrystal = false;
		brokenBoxes = 0;
		keyPressed.clear();
		initOnline();
		gameManager.startGame();
		if(!gameManager.getWorld().getLevel().getMusic().equals("Select the music for this level")) {
			MenuPanel.sound.switchMusic(gameManager.getWorld().getLevel().getMusic());
		} else {
			MenuPanel.sound.stopMusic();
		}
	}
	
	@Override
	public void run() {
		init();
		while(!gameManager.gameOver()) {
			this.requestFocusInWindow();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(!pause) {
				if(!clientConnected) {
					Server.sendData(gameManager.getWorld().getCrash().getX(), gameManager.getWorld().getCrash().getY());
					coco = Server.reciveData();
				} else {
					Client.sendData(gameManager.getWorld().getCoco().getX(), gameManager.getWorld().getCoco().getY());
					crash = Client.reciveData();
				}
				gameManager.update();
				handleActions();
			}
			this.repaint();
		}
	}
}
