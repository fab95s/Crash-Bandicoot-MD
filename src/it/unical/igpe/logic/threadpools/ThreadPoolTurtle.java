package it.unical.igpe.logic.threadpools;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import it.unical.igpe.logic.objects.Turtle;

public class ThreadPoolTurtle {
	private Thread thread;
	private List<Turtle> list = new CopyOnWriteArrayList<Turtle>();
	
	public Thread getThread() {
		return thread;
	}
	
	public void setThread(final Thread thread) {
		this.thread = thread;
	}
	
	public List<Turtle> getList() {
		return list;
	}
	
	public void setList(final List<Turtle> list) {
		this.list = list;
	}
	
}
