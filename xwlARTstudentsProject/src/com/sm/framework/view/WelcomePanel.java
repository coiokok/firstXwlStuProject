package com.sm.framework.view;

// �������ñ���ͼƬ��Jpanel������¼ҳ��LoginPanelֱ��ʵ����ʹ�ñ���

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class WelcomePanel extends JPanel{
	private ImageIcon img = null;
	
	// ���췽��
	public WelcomePanel() {
		img = new ImageIcon("./img/login.png");        // ʵ����imgͼƬ
	}

	// paintComponent���ñ����ķ���
	@Override
	protected void paintComponent(Graphics g) {          // g���൱�ڱ������õĻ���
		g.drawImage(this.img.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);              // g.drawImage(this.ͼƬʵ��������.getImage(), x���Ŀ�ʼ, y���Ŀ�ʼ, this.getWidth(), this.getHeight(), this);
	}
	
	
}
