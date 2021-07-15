package com.sm.framework.control.action.org;

import java.awt.Font;

// 这是实现Action中的execute方法的AboutAction实现类，该类的名字和地址需要跟数据库myMenu中的FunctionClass中对应功能存的数据一样
// 这个实现类对应的是点击“关于”操作按钮的显示页面功能

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutAction implements Action {
	
	private JPanel panelBody1 = null;
	
	private JLabel firstlabel = null;                  
	private JLabel secondlabel = null;
	private JLabel returnlabel = null;
	
	
	private void init() {
		
		this.panelBody1.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.anchor = GridBagConstraints.EAST;
		
		this.firstlabel = new JLabel("本项目由 wyh 于 2021.07.14 完成");
		this.firstlabel.setFont(new Font("楷体", Font.BOLD, 24));
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.panelBody1.add(this.firstlabel, constraints);
		
		this.returnlabel = new JLabel("             ");
		constraints.gridx = 0;
		constraints.gridy = 1;
		this.panelBody1.add(this.returnlabel, constraints);
		
		this.secondlabel = new JLabel("可用于学校学生信息管理     ");
		this.secondlabel.setFont(new Font("楷体", Font.BOLD, 24));
		constraints.gridx = 0;
		constraints.gridy = 2;
		this.panelBody1.add(this.secondlabel, constraints);
		
		}

	
	// 构造方法的编写，会直接将该页面显示在传入的panelWork页面
	@Override
	public void execute(JPanel panelWork) {
		this.panelBody1 = panelWork;
		this.init();
	}
	
}