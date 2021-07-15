package com.sm.framework.control.listener;

// ����control���еĲ˵���������Ϊ�˵���˵��еĹ�����ʾ���Բ������ܵ�ҳ��

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.sm.framework.control.action.org.Action;
import com.sm.framework.model.dao.impl.MyMenuDAOImpl;
import com.sm.framework.model.entity.MyMenu;
import com.sm.framework.view.MainFrame;

public class MenuListener implements ActionListener { // �̳�ActionListenerȥ�����¼����������ü�����ȥʵ��MainFrame�Ĳ˵����ܵ���¼�
	
	private MainFrame mainFrame = null;               // ʵ����MainFrame��ҳ���ڹ��췽��ʱ����ղ���
	
	private JPanel panelBody = null;                  // panelBody���mainframe��ContentPane
	private JPanel panelWelcome = null;               // panelWelcome���panelBody��ԭmainframe���ĵڶ������ҳ�棬Ҳ�����м�Ĳ���ҳ��
	
	private JPanel panelWork = null;                  // panelWork���м���в�����ҳ��

	
	public MenuListener() {
		// TODO Auto-generated constructor stub
	}
	// ��д���췽����MainFrame����Ӽ����¼�
	public MenuListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		// TODO Auto-generated constructor stub
	}
	
	
	// �¼���������˵���ʾ���·���ϸ�������ܰ�ť���������������ڱ����ʵ�֣����Լ�����ʵ����ļ��������������getPanelTop����
	@Override
	public void actionPerformed(ActionEvent e) {
		this.panelBody = (JPanel)this.mainFrame.getContentPane();      // �����֮�����mainFrame����getContentPaneȻ��ǿתΪJPanel��ʽ����ǰ���panelBody
		
		Component[] components1 = this.panelBody.getComponents();      // panelBody��getComponents�����ӵ��������components1
		this.panelWelcome = (JPanel)components1[1];                    // components1[n]����֮ǰ��ӽ�panelBody(ԭmainFrame)�ĵ�n+1�����������ǰ���panelWelcome
		
		this.panelWelcome.removeAll();         // ��һ����Ŀ����Ϊ��ÿ�ε���˵����ܽ�ԭ���������ʾ��ҵ��رգ�������ʾ��ҵ���ص���һ��
		this.panelWelcome.repaint();           // ��һ����Ŀ����Ϊ��ÿ�ε���˵����ܽ�ԭ���������ʾ��ҵ��رգ�������ʾ��ҵ���ص���һ��
		
		this.panelWelcome.setLayout(new BorderLayout());               // ����panelWelcomeΪBorderLayout
		
		this.panelWork = new JPanel();
		this.panelWelcome.add(this.panelWork, BorderLayout.CENTER);    // ��panelWork�������ΪpanelWelcome��center
		
		String menuId = e.getActionCommand();                          // ��õ�ǰ����İ�ť��functionId����Ϊ֮ǰÿ����ϸ������ť��ActionCommand��������Ϊ�����Ǹ��Ե�FunctionId
		MyMenu menuTemp = new MyMenuDAOImpl().findByMenuId(menuId);    // ����MisFunctionDAOImpl�е�findByFunctionId�������ջ�õĵ����functionId���뷽��
		String functionName = menuTemp.getfunctionClass();             // ���ݿ���FunctionClass�����ǵ����ϸ������ť֮�����ʾ�ұ�ҳ���ʵ����ĵ�ַ����ֵ��functionName
		
		try {
			// ��̬���ص���䣨��Ҫ��������
			Class functionClass = Class.forName(functionName);         // ��ʽ��Class ������ = Class.forName(��ĵ�ַ�Լ���);
			Action action = (Action)functionClass.newInstance();       // newInstance()��������ʵ������forname�Ķ���functionClass��functionClass�����Ķ��ǽӿڵ�ʵ��������Ҫǿת��action
			
			action.execute(this.panelWork);                            // Ȼ�����action�ӿ��е�execute�ķ�������panelWorkʵ�����������ʾ��������������ÿ�ε���İ�ť��ͬ��ʵ������ͬ�ĵ�FunctionClass��Ҳ���ǲ�ͬ�Ľӿ�ʵ���࣬���磺���������ǡ����ӻ��������ôʵ��������OrgTypeCreateActionʵ���࣬��ʾ�ľ���OrgTypeCreateAction�Ĳ���ҳ�棩
			
			this.mainFrame.setVisible(true);                           // ˢ�����
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.mainFrame.setVisible(true);               // ��swing����µ����֮����Ҫ����ˢ�²����Ż���ʾ��setVisible(true)����ˢ�����
	}
	
	
}
