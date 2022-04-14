package com.daneil.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.daneil.main.Game;
import com.daneil.world.Camera;
import com.daneil.world.World;

public class Player extends Entity
{
	public boolean right,left;
	public double g = 2;
	public int dir = 1;
	public static double life = 100;
	
	public static int curCoins = 0;
	public static int maxCoins = 0;
	
	public boolean jump = false;
	public boolean isJumping = false;
	public int jumpH = 50;
	public int jumpFrames = 0;
	
	private int framesA = 0;
	private int maxFrames = 15;
	private int maxSprite = 6;
	private int curSprite = 0;
	
	private int framesAM = 0;
	private int maxFramesM = 15;
	private int maxSpriteM = 2;
	private int curSpriteM = 0;
	
	private boolean isMoving = false;
	
	public Player(int x, int y, int width, int height, double speed, BufferedImage sprite)
	{
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick()
	{
		depth = 2;
		if(World.isFree((int)x,(int)(y+g)) && isJumping == false)
		{
			y+=g;
			for(int i = 0; i < Game.entities.size(); i++)
			{
				Entity e = Game.entities.get(i);
				if(e instanceof Enemy)
				{
					if(Entity.isColliding(this, e))
					{
						isJumping = true;
						((Enemy)e).life--;
						if(((Enemy)e).life == 0)
							Game.entities.remove(i);
						break;
					}
				}
			}
		}
			
		if(right && World.isFree((int)(x+speed), (int)y))
		{
			isMoving = true;
			x+=speed;
			dir = 1;
		}
		
		else if(left && World.isFree((int)(x-speed), (int)y))
		{
			isMoving = true;
			x-=speed;
			dir = -1;
		}
		
		if(jump)
		{
			if(!World.isFree(this.getX(), this.getY()+1))
			{
				isJumping = true;
			}
			else
			{
				jump = false;
			}
		}
		
		if(isJumping)
		{
			if(World.isFree(this.getX(), this.getY()-2))
			{
				y-=2;
				jumpFrames+=2;
				if(jumpFrames == jumpH)
				{
					isJumping = false;
					jump = false;
					jumpFrames = 0;
				}
			}
			else
			{
				isJumping = false;
				jump = false;
				jumpFrames = 0;
			}
		}
		
		for(int i = 0; i < Game.entities.size(); i++)
		{
			Entity e = Game.entities.get(i);
			if(e instanceof Enemy)
			{
				if(Entity.isColliding(this, e))
				{
					life--;
				}
			}
		}
		
		for(int i = 0; i < Game.entities.size(); i++)
		{
			Entity e = Game.entities.get(i);
			if(e instanceof Coins)
			{
				if(Entity.isColliding(this, e))
				{
					Game.entities.remove(i);
					curCoins++;
					break;
				}
			}
		}
		
		Camera.x = Camera.clamp((int) x - Game.WIDTH / 2, 0, World.WIDTH *16 - Game.WIDTH);
		Camera.y = Camera.clamp((int) y - Game.HEIGHT / 2, 0, World.HEIGHT *16 - Game.HEIGHT);
	}
	
	public void render(Graphics g)
	{
		if(isMoving)
		{
			framesAM++;
			if(framesAM == maxFramesM)
			{
				curSpriteM++;
				framesAM = 0;
				if(curSpriteM == maxSpriteM)
					curSpriteM = 0;
			}
			
			if(dir == 1)
			{
				sprite = Entity.PLAYER_SPRITE_RIGHT_MOVING[curSpriteM];
			}
			else if(dir == -1)
			{
				sprite = Entity.PLAYER_SPRITE_LEFT_MOVING[curSpriteM];
			}
			isMoving = false;
		}
		else
		{
			
			framesA++;
			if(framesA == maxFrames)
			{
				curSprite++;
				framesA = 0;
				if(curSprite == maxSprite)
					curSprite = 0;
			}
			
			if(dir == 1)
			{
				sprite = Entity.PLAYER_SPRITE_RIGHT[curSprite];
			}
			else if(dir == -1)
			{
				sprite = Entity.PLAYER_SPRITE_LEFT[curSprite];
			}
		}
		super.render(g);
	}
}
