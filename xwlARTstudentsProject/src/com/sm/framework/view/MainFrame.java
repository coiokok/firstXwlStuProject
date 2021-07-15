package com.sm.framework.view;

// ���ǽ������й���ϵͳ������ҳ�棬����Mymenu���ݿ��д�Ķ�����ж�̬�˵�����

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.sm.framework.control.listener.MenuListener;
import com.sm.framework.model.dao.impl.MyMenuDAO;
import com.sm.framework.model.dao.impl.MyMenuDAOImpl;
import com.sm.framework.model.entity.MisUser;
import com.sm.framework.model.entity.MyMenu;

public class MainFrame extends JFrame implements ActionListener {
	private MisUser misUser = null;           // ����ڹ��췽���н��յ�¼���洫����loginUser
	
	private JMenuBar menuBar = null;          // �˵�ͣ��menuBar
	
	private JPanel panelBody = null;          // ����panel
	private JPanel panelWelcome = null;       // ���в�����panelWelcome
	
	private JToolBar toolBar = null;          // ���Ϸ��Ĺ�����
	private JLabel labelwelcome = null;       // �Ϸ��Ļ�ӭ����
	private JLabel labelText = null;          // �Ϸ�����ʾ��¼�û����ֵľ���
	private JButton buttonExit = null;        // �Ϸ����˳���ť
	private JButton buttonReLogin = null;     // �Ϸ������µ�¼��ť
	
	private JLabel labelState = null;         // �ײ���������Ϣ
	
	// createMenu��̬�ҿ��˵�����
	private void createMenu() {
		MyMenuDAO menuDAO = new MyMenuDAOImpl();       // ʵ����MyMenuDAO��������һ��findBySql������ͨ��sql�����в�ѯ�������֮�󷵻�
		String sql1 = "select * from myMenu where menuId like '__' and menuId in (select menuId from auth where roleId = '"+ misUser.getRoleId() +"')";       // ��仰��˼�ǲ���menuId������Ϊ��λ������Ϣ
		List<MyMenu> list1 = menuDAO.findBySql(sql1);        // ���ҵ������ݴ浽list1������
		
		for (MyMenu myMenu1 : list1) {           // ����list1
			JMenu tempMenu = new JMenu();        // ʵ����JMenu�����鵽����Ϣ�浽tempMenu�˵��У������и�ѡ�����˵�
			tempMenu.setActionCommand(myMenu1.getMenuId());        // setActionCommand���������¼���Ϊ�ù��������ݿ��е�Id
			tempMenu.setText(myMenu1.getMenuName());               // setText�������ò˵���
			
			
			String sql2 = "select * from myMenu where menuId like '" + myMenu1.getMenuId() + "__' and menuId in (select menuId from auth where roleId = '"+ misUser.getRoleId() +"')";   // Ƕ��ѭ�����Ҹ�ѭ���µ�id+��λ������Ϣ������1011,1012...�����ڸ������˵��У��ɲ����ٸ�ѡ�Ĳ˵�
			List<MyMenu> list2 = menuDAO.findBySql(sql2);          // ����findbysql�������ҵ������ݴ浽list2������
			for (MyMenu myMenu2 : list2) {        // ����list2
				JMenuItem tempItem = new JMenuItem();              // ʵ����tempItem����һ��JMenuItem��û�и�ѡ�Ĳ˵�
				tempItem.setActionCommand(myMenu2.getMenuId());    // setActionCommand���������¼���Ϊ�ù��������ݿ��е�Id,��֮��ĵ��������ʾ��ϸ����MenuListener�л��õ�
				tempItem.setText(myMenu2.getMenuName());
				
				tempItem.addActionListener(new MenuListener(this));    // �����еĲ˵�ѡ����Ӽ����¼�����Ϊ����MenuListener��ʵ�ֵ�����Ҫʵ����MenuListener�������
				
				tempMenu.add(tempItem);                 // ������õ��޸�ѡ�Ĳ˵���ӽ�tempMenu�������и�ѡ�����˵���ע���⻹����ѭ��Ƕ���У����ԻὫ10��20�ȿ�ͷ�Ķ�����һ��
			}
			
			
			this.menuBar.add(tempMenu);                 // ������Ӻõ����˵���ӽ�menuBar
		}
	}
	
	
	// ���巽��
	private void init() {
		this.menuBar = new JMenuBar();              // ʵ����menuBar
		
		// ����createMenu����
		this.createMenu();
		
		this.setJMenuBar(this.menuBar);
		
		// ����panelBody
		this.panelBody = (JPanel)this.getContentPane();
		this.panelBody.setLayout(new BorderLayout());
		
		// ������ҳ���Ϸ��Ļ�ӭ���
		this.toolBar = new JToolBar();    
		this.toolBar.setFloatable(false);    // ���ù����������ƶ�
		this.toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));      // ���ù��������ݾ���
		this.labelwelcome = new JLabel("��ӭ�㣺");
		this.labelText = new JLabel(this.misUser.getUserName());        // ��ȡ�����misUser��usernameȻ����ΪlabelText
		this.buttonExit = new JButton("�˳�");
		this.buttonExit.setActionCommand("exit");
		this.buttonExit.addActionListener(this);
		this.buttonReLogin = new JButton("���µ�¼");
		this.buttonReLogin.setActionCommand("relogin");
		this.buttonReLogin.addActionListener(this);
		this.toolBar.add(this.labelwelcome);
		this.toolBar.add(this.labelText);
		this.toolBar.add(this.buttonExit);
		this.toolBar.add(this.buttonReLogin);
		this.toolBar.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(125, 147, 147)));
		this.panelBody.add(this.toolBar, BorderLayout.NORTH);           // ����������ӽ�panelBody��boderLayout���Ϸ�
		
		// ������ҳ���м�Ĺ��ܲ�������
		this.panelWelcome = new JPanel();
		this.panelWelcome.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(125, 147, 147)));    // setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(24, 108, 224)));���ñ߿򷽷�
		this.panelWelcome.setBackground(new Color(236, 236, 236));
		this.panelBody.add(this.panelWelcome, BorderLayout.CENTER);     // ������������ӽ�panelBody��boderLayout���м�
		
		
		// ������ҳ���·���ˮӡ������Ϣ
		this.labelState = new JLabel("��������ѧԺѧ����Ϣ����ϵͳ        by:wyh", JLabel.CENTER);
		this.panelBody.add(this.labelState, BorderLayout.SOUTH);        // ��������Ϣ��ӽ�panelBody��boderLayout���·�
		
		// ���ô�����Ϣ
		this.setTitle("��������ѧԺѧ����Ϣ����ϵͳ������");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	// д���췽��
	public MainFrame() {
		// TODO Auto-generated constructor stub
	}
	public MainFrame(MisUser misUser) {   // ���յ�¼ҳ���loginUser�������������Ȼᴰ�巽�����õ���misUser��Ȼ����ô��巽��
		this.misUser = misUser;
		this.init();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if("exit".equals(e.getActionCommand())) {       // �����ͨ��ActionCommand�ж��¼�
			int n = JOptionPane.showConfirmDialog(this, "��ȷ��Ҫ�˳���");       // ѡ���JOptionPane.showConfirmDialog(���ĵ�����"��������������");   ѡ������ѡ��yes��no���ٸ���ѡ����yes����no����֮��Ĳ���
			if(n == JOptionPane.YES_OPTION) {
				System.exit(0);                         // �˳������Լ����򷽷�������������Ͳ�����дthis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}                          
		} else if("relogin".equals(e.getActionCommand())) {
			int n = JOptionPane.showConfirmDialog(this, "��ȷ��Ҫ���µ�¼��");
			if(n == JOptionPane.YES_OPTION) {
				LoginFrame loginFrame = new LoginFrame();
				loginFrame.setBounds(720, 350, 500, 320);
				loginFrame.setVisible(true);
				this.dispose();                         // �ر���ҳ�浫����Ӱ�������ɵĵ�¼ҳ��
			}   
		}
	}
}
