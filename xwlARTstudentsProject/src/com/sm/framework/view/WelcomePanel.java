package com.sm.framework.view;

// 这是设置背景图片的Jpanel，供登录页面LoginPanel直接实例化使用背景

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class WelcomePanel extends JPanel{
	private ImageIcon img = null;
	
	// 构造方法
	public WelcomePanel() {
		img = new ImageIcon("./img/login.png");        // 实例化img图片
	}

	// paintComponent设置背景的方法
	@Override
	protected void paintComponent(Graphics g) {          // g就相当于背景设置的画笔
		g.drawImage(this.img.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);              // g.drawImage(this.图片实例化的命.getImage(), x从哪开始, y从哪开始, this.getWidth(), this.getHeight(), this);
	}
	
	
}
