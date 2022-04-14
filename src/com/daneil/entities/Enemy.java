package com.daneil.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.daneil.world.World;

public class Enemy extends Entity
{
	
	public boolean right = true,left = false;
	
	public int life = 2;

	public Enemy(int x, int y, int width, int height, int speed, BufferedImage sprite) 
	{
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick()
	{
		if(World.isFree((int)x,(int)(y+1)))
		{
			y+=1;
		}
		else
		{
			if(right)
			{
				if(World.isFree((int)(x+speed), (int)y))
				{
					x+=speed;
					if(World.isFree((int)(x+16), (int)y+1))
					{
						right = false;
						left = true;
					}
				}
				else
				{
					right = false;
					left = true;
				}
			}
			
			if(left)
			{
				if(World.isFree((int)(x-speed), (int)y))
				{
					x-=speed;
					if(World.isFree((int)(x-16), (int)y+1))
					{
						right = true;
						left = false;
					}
				}
				else
				{
					right = true;
					left = false;
				}
			}
		}
	}
	
	public void render(Graphics g)
	{
		if(right)
			sprite = Entity.ENEMY1_R;
		else if(left)
			sprite = Entity.ENEMY1_L;
		
		super.render(g);
	}
}
	
