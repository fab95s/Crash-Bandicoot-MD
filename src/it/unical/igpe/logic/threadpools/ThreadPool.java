package it.unical.igpe.logic.threadpools;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import it.unical.igpe.graphics.SingleplayerGamePanel;
import it.unical.igpe.logic.objects.Turtle;

public class ThreadPool {
	private ThreadPoolTurtle turtles = new ThreadPoolTurtle();
	
	private void sleepQuietly() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void addTurtle(final Turtle turtle) {
		if(turtles.getThread() == null) {
			turtles.setThread(new Thread(() -> {
				while(SingleplayerGamePanel.stopThread) {
					synchronized (turtles) {
						if(!SingleplayerGamePanel.pause) {
							turtles.getList().forEach(ta -> ta.update());
						}
					}
					sleepQuietly();
				}
			}));
			synchronized (turtles) {
				turtles.getList().add(turtle);
			}
			turtles.getThread().start();
		} else {
			synchronized (turtles) {
				turtles.getList().add(turtle);
			}
		}
	}
	
	public List<Turtle> getTurtles(){
		return turtles.getList();
	}
	
	public void initTurtles() {
		turtles.setThread(null);
		turtles.setList(new CopyOnWriteArrayList<Turtle>());
	}
	
}
