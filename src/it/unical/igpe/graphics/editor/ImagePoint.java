package it.unical.igpe.graphics.editor;

import java.awt.Image;
import java.awt.Point;

public class ImagePoint {
	private final Image image;
	private final Point point;
	private final Character filename;
	
	public ImagePoint(final Image image, final Point point, final Character filename) {
		this.image = image;
		this.point = point;
		this.filename = filename;
	}
	
	public Image getImage() {
		return image;
	}
	
	public Point getPoint() {
		return point;
	}
	
	public char getFilename() {
		return filename;
	}
	
	public boolean equals(final ImagePoint ip) {
		return this.point.equals(ip.getPoint());
	}
	
	public String toString() {
		return point.x + " " + point.y + " " + filename;
	}
}
