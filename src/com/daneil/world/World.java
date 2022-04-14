package com.daneil.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.daneil.entities.Coins;
import com.daneil.entities.Enemy;
import com.daneil.entities.Entity;
import com.daneil.entities.Player;
import com.daneil.main.Game;

public class World {

	public static Tile[] tiles;
	public static int WIDTH,HEIGHT;
	public static final int TILE_SIZE = 16;
	
		public World(String path){
			try {
				BufferedImage map = ImageIO.read(getClass().getResource(path));
				int[] pixels = new int[map.getWidth() * map.getHeight()];
				WIDTH = map.getWidth();
				HEIGHT = map.getHeight();
				tiles = new Tile[map.getWidth() * map.getHeight()];
				map.getRGB(0, 0, map.getWidth(), map.getHeight(),pixels, 0, map.getWidth());
				for(int xx = 0; xx < map.getWidth(); xx++){
					for(int yy = 0; yy < map.getHeight(); yy++){
						int pixelAtual = pixels[xx + (yy * map.getWidth())];
						
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR_1);
						
						//Floor
						if(pixelAtual == 0xFF000000){
							tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR_1);
						}
						
						else if(pixelAtual == 0xFF0C0C0C){
							tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR_2);
						}
						
						else if(pixelAtual == 0xFF0F0F0F){
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_FLOOR_3);
						}
						//Wall
						else if(pixelAtual == 0xFFFFFFFF){
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL_1);
							}
							
						else if(pixelAtual == 0xFFF4F4F4){
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL_2);
							}
						
						else if(pixelAtual == 0xFFEFEFEF){
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL_3);
							}
						
						else if(pixelAtual == 0xFFEAEAEA){
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL_4);
							}
						
						else if(pixelAtual == 0xFFEAEAE9){
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL_5);
							}
						
						else if(pixelAtual == 0xFFE5E5E5){
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL_6);
							}
						
						//Player
						else if(pixelAtual == 0xFF4800FF){
							Game.player.setX(xx*16);
							Game.player.setY(yy*16);
						}
						
						//Enemy
						else if(pixelAtual == 0xFFCCA05F){
							Enemy enemy = new Enemy(xx*16,yy*16,16,16,1,Entity.ENEMY1_R);
							Game.entities.add(enemy);
						}
						
						//Coin
						else if(pixelAtual == 0xFFFFFF6B){
							Coins coins = new Coins(xx*16,yy*16,16,16,1,Entity.COIN_1);
							Game.entities.add(coins);
							Player.maxCoins++;
						}
					}
				}
				} catch (IOException e) {
					e.printStackTrace();
					}
			}
		public static boolean isFree(int xnext,int ynext){
			int x1 = xnext / TILE_SIZE;
			int y1 = ynext / TILE_SIZE;
			
			int x2 = (xnext + TILE_SIZE - 1) / TILE_SIZE;
			int y2 = ynext / TILE_SIZE;
			
			int x3 = xnext / TILE_SIZE;
			int y3 = (ynext + TILE_SIZE - 1) / TILE_SIZE;
			
			int x4 = (xnext + TILE_SIZE - 1) / TILE_SIZE;
			int y4 = (ynext + TILE_SIZE - 1) / TILE_SIZE;
			
			return!((tiles[x1 + (y1 * World.WIDTH)] instanceof WallTile ||
					 tiles[x2 + (y2 * World.WIDTH)] instanceof WallTile ||
					 tiles[x3 + (y3 * World.WIDTH)] instanceof WallTile ||
					 tiles[x4 + (y4 * World.WIDTH)] instanceof WallTile));
		}
		
		public static void restartGame(){
			return;
		}
		
		
		public void render(Graphics g) {
			int xstart = Camera.x / TILE_SIZE;
			int ystart = Camera.y / TILE_SIZE;
			int xfinal = xstart + (Game.WIDTH / TILE_SIZE);
			int yfinal = ystart + (Game.HEIGHT / TILE_SIZE) + 1;

			for(int xx = xstart; xx <= xfinal; xx++){
				for(int yy = ystart; yy <= yfinal; yy++){
					if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
						continue;
					Tile tile = tiles[xx + (yy * WIDTH)];
					tile.render(g);
					
				}
			}
		}
}
