package com.sm.framework.control.action.org;

import java.awt.Font;

// ����ʵ��Action�е�execute������AboutActionʵ���࣬��������ֺ͵�ַ��Ҫ�����ݿ�myMenu�е�FunctionClass�ж�Ӧ���ܴ������һ��
// ���ʵ�����Ӧ���ǵ�������ڡ�������ť����ʾҳ�湦��

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ContentAction implements Action {
	
	private JPanel panelBody1 = null;
	
	private JLabel firstlabel = null;                  
	private JLabel secondlabel = null;
	private JLabel thirdlabel = null;
	private JLabel returnlabel1 = null;
	private JLabel returnlabel2 = null;
	
	
	private void init() {
		
		this.panelBody1.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.anchor = GridBagConstraints.EAST;
		
		this.firstlabel = new JLabel("���������ϵ QQ��1553687934      ");
		this.firstlabel.setFont(new Font("����", Font.BOLD, 24));
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.panelBody1.add(this.firstlabel, constraints);
		
		this.returnlabel1 = new JLabel("             ");
		constraints.gridx = 0;
		constraints.gridy = 1;
		this.panelBody1.add(this.returnlabel1, constraints);
		
		this.secondlabel = new JLabel("����csdn��������ϸ��Ŀ���ܽ�����������");
		this.secondlabel.setFont(new Font("����", Font.BOLD, 24));
		constraints.gridx = 0;
		constraints.gridy = 2;
		this.panelBody1.add(this.secondlabel, constraints);
		
		this.returnlabel2 = new JLabel("             ");
		constraints.gridx = 0;
		constraints.gridy = 3;
		this.panelBody1.add(this.returnlabel2, constraints);
		
		this.thirdlabel = new JLabel("https://blog.csdn.net/coiokok    ");
		this.thirdlabel.setFont(new Font("����", Font.BOLD, 24));
		constraints.gridx = 0;
		constraints.gridy = 4;
		this.panelBody1.add(this.thirdlabel, constraints);
		
		}

	
	// ���췽���ı�д����ֱ�ӽ���ҳ����ʾ�ڴ����panelWorkҳ��
	@Override
	public void execute(JPanel panelWork) {
		this.panelBody1 = panelWork;
		this.init();
	}
	
}