package it.unical.igpe.logic.online;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static Socket socket;
	private static ServerSocket serverSocket;
	private static DataOutputStream dos;
	private static DataInputStream dis;
	public static boolean accepted = false;
	
	public static void initServer() {
		try {
			serverSocket = new ServerSocket(7777);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void listenForServerRequest() {
		try {
			socket = serverSocket.accept();
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			accepted = true;
			System.out.println("Client has request to join, and we have accepted.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void sendData(final String level) {
		try {
			byte[] data = level.getBytes("UTF-8");
			dos.writeInt(data.length);
			dos.write(data);
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	public static Point reciveData() {
		try {
			return new Point(dis.readInt(), dis.readInt());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
