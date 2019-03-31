package it.unical.igpe.logic.online;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import it.unical.igpe.graphics.MultiplayerGamePanel;

public class Client {
	private static Socket socket;
	private static DataOutputStream dos;
	private static DataInputStream dis;
	
	public static boolean connect(final String ip) {
		try {
			socket = new Socket(ip, 7777);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			Server.accepted = true;
		} catch (IOException e) {
			System.out.println("Unable to connect to the address: " + ip + ":7777 | Starting a server.");
			return false;
		}
		System.out.println("Successfully connected to the server.");
		MultiplayerGamePanel.clientConnected = true;
		return true;
	}
	
	public static void sendData(final int x, final int y) {
		try {
			dos.writeInt(x);
			dos.writeInt(y);
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String reciveDataLevel() {
		String level = null;
		try {
			int lenght = dis.readInt();
			byte[] data = new byte[lenght];
			dis.readFully(data);
			level = new String(data, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return level;
	}
	
	public static Point reciveData() {
		try {
			int x = dis.readInt();
			int y = dis.readInt();
			return new Point(x, y);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
