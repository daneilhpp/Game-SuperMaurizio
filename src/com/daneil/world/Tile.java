package com.daneil.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.daneil.main.Game;

public class Tile{

	public static BufferedImage TILE_FLOOR_1 = Game.spritesheet.getSprite(0,0,16,16);
	public static BufferedImage TILE_FLOOR_2 = Game.spritesheet.getSprite(0,16,16,16);
	public static BufferedImage TILE_FLOOR_3 = Game.spritesheet.getSprite(0,32,16,16);
	public static BufferedImage TILE_WALL_1 = Game.spritesheet.getSprite(16,0,16,16);
	public static BufferedImage TILE_WALL_2 = Game.spritesheet.getSprite(16,16,16,16);
	public static BufferedImage TILE_WALL_3 = Game.spritesheet.getSprite(16,32,16,16);
	public static BufferedImage TILE_WALL_4 = Game.spritesheet.getSprite(16,48,16,16);
	public static BufferedImage TILE_WALL_5 = Game.spritesheet.getSprite(16,64,16,16);
	public static BufferedImage TILE_WALL_6 = Game.spritesheet.getSprite(16,80,16,16);
	
	private BufferedImage sprite;
	private int x,y;
	
	public Tile(int x, int y, BufferedImage sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g){
		g.drawImage(sprite , x - Camera.x,y - Camera.y , null);
	}
	
	
	
}
