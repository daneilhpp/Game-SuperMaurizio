package com.daneil.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.daneil.entities.Player;

public class UI 
{

	public void render(Graphics g)
	{
		g.setColor(Color.gray);
		g.fillRect(13, 13, 204, 19);
		g.setColor(Color.red);
		g.fillRect(15, 15, 200, 15);
		g.setColor(Color.green);
		g.fillRect(15, 15, (int)((Player.life / 100) * 200), 15);
		
		g.setColor(Color.black);
		g.setFont(new Font("Arial",Font.BOLD,20));
		g.drawString("Coins: "+Player.curCoins+"/"+Player.maxCoins, 1080, 28);
	} 
}
