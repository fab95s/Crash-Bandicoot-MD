package it.unical.igpe.logic.interfaces;

import it.unical.igpe.logic.objects.Coco;
import it.unical.igpe.logic.objects.Crash;
import it.unical.igpe.logic.objects.Turtle;
import it.unical.igpe.logic.objects.blocks.GrassBlock;
import it.unical.igpe.logic.objects.blocks.LandBlock;
import it.unical.igpe.logic.objects.blocks.SteelBlock;
import it.unical.igpe.logic.objects.blocks.WaterBlock;
import it.unical.igpe.logic.objects.boxs.LifeBox;
import it.unical.igpe.logic.objects.boxs.NitroBox;
import it.unical.igpe.logic.objects.boxs.WumpaBox;

public enum ObjectChar {
	EMPTY('-'), CRYSTAL('C'),
	GRASSBLOCK('G'), LANDBLOCK('L'), WATERBLOCK('W'), STEELBLOCK('S'),
	WUMPABOX('1'), LIFEBOX('2'), NITROBOX('3'),
	CRASH('@'), COCO('#'), TARTLE('_');
	private final char c;
	
	private ObjectChar(final char c) {
		this.c = c;
	}
	
	public static ObjectChar toObjectChar(final char c) {
		switch(c) {
			case 'C':
				return CRYSTAL;
			
			case 'G':
				return GRASSBLOCK;
			case 'L':
				return LANDBLOCK;
			case 'W':
				return WATERBLOCK;
			case 'S':
				return STEELBLOCK;
			
			case '1':
				return WUMPABOX;
			case '2':
				return LIFEBOX;
			case '3':
				return NITROBOX;
			
			case '@':
				return CRASH;
			case '#':
				return COCO;
			case '_':
				return TARTLE;
			
			default:
				return EMPTY;
		}
	}
	
	public static ObjectChar toObjectChar(final StaticObject o) {
		if(o instanceof GrassBlock) {
			return GRASSBLOCK;
		}
		if(o instanceof LandBlock) {
			return LANDBLOCK;
		}
		if(o instanceof WaterBlock) {
			return WATERBLOCK;
		}
		if(o instanceof SteelBlock) {
			return STEELBLOCK;
		}
		
		if(o instanceof WumpaBox) {
			return WUMPABOX;
		}
		if(o instanceof LifeBox) {
			return LIFEBOX;
		}
		if(o instanceof NitroBox) {
			return NITROBOX;
		}
		
		if(o instanceof Crash) {
			return CRASH;
		}
		if(o instanceof Coco) {
			return COCO;
		}
		if(o instanceof Turtle) {
			return TARTLE;
		}
		
		return EMPTY;
	}
	
	public char toChar() {
		return c;
	}
}
