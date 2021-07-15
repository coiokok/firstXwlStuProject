package com.sm.framework.view;

// 这是进行所有管理系统操作的页面，根据Mymenu数据库中存的对象进行动态菜单操作

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
	private MisUser misUser = null;           // 这个在构造方法中接收登录界面传来的loginUser
	
	private JMenuBar menuBar = null;          // 菜单停靠menuBar
	
	private JPanel panelBody = null;          // 整个panel
	private JPanel panelWelcome = null;       // 进行操作的panelWelcome
	
	private JToolBar toolBar = null;          // 最上方的工具栏
	private JLabel labelwelcome = null;       // 上方的欢迎句子
	private JLabel labelText = null;          // 上方的显示登录用户名字的句子
	private JButton buttonExit = null;        // 上方的退出按钮
	private JButton buttonReLogin = null;     // 上方的重新登录按钮
	
	private JLabel labelState = null;         // 底部的文字信息
	
	// createMenu动态挂靠菜单方法
	private void createMenu() {
		MyMenuDAO menuDAO = new MyMenuDAOImpl();       // 实例化MyMenuDAO，里面有一个findBySql方法，通过sql语句进行查询存入对象之后返回
		String sql1 = "select * from myMenu where menuId like '__' and menuId in (select menuId from auth where roleId = '"+ misUser.getRoleId() +"')";       // 这句话意思是查找menuId里所有为两位数的信息
		List<MyMenu> list1 = menuDAO.findBySql(sql1);        // 查找到的数据存到list1集合里
		
		for (MyMenu myMenu1 : list1) {           // 遍历list1
			JMenu tempMenu = new JMenu();        // 实例化JMenu，将查到的信息存到tempMenu菜单中，这是有复选的主菜单
			tempMenu.setActionCommand(myMenu1.getMenuId());        // setActionCommand方法设置事件名为该功能在数据库中的Id
			tempMenu.setText(myMenu1.getMenuName());               // setText方法设置菜单名
			
			
			String sql2 = "select * from myMenu where menuId like '" + myMenu1.getMenuId() + "__' and menuId in (select menuId from auth where roleId = '"+ misUser.getRoleId() +"')";   // 嵌套循环查找该循环下的id+两位数的信息，例：1011,1012...，挂在该下拉菜单中，成不可再复选的菜单
			List<MyMenu> list2 = menuDAO.findBySql(sql2);          // 调用findbysql方法查找到的数据存到list2集合里
			for (MyMenu myMenu2 : list2) {        // 遍历list2
				JMenuItem tempItem = new JMenuItem();              // 实例化tempItem这是一个JMenuItem，没有复选的菜单
				tempItem.setActionCommand(myMenu2.getMenuId());    // setActionCommand方法设置事件名为该功能在数据库中的Id,在之后的点击功能显示详细操作MenuListener中会用到
				tempItem.setText(myMenu2.getMenuName());
				
				tempItem.addActionListener(new MenuListener(this));    // 给所有的菜单选项添加监听事件，因为是在MenuListener中实现的所以要实例化MenuListener设监听器
				
				tempMenu.add(tempItem);                 // 将刚设好的无复选的菜单添加进tempMenu，就是有复选的主菜单，注意这还是在循环嵌套中，所以会将10或20等开头的都放在一起
			}
			
			
			this.menuBar.add(tempMenu);                 // 将刚添加好的主菜单添加进menuBar
		}
	}
	
	
	// 窗体方法
	private void init() {
		this.menuBar = new JMenuBar();              // 实例化menuBar
		
		// 调用createMenu方法
		this.createMenu();
		
		this.setJMenuBar(this.menuBar);
		
		// 设置panelBody
		this.panelBody = (JPanel)this.getContentPane();
		this.panelBody.setLayout(new BorderLayout());
		
		// 设置主页面上方的欢迎语句
		this.toolBar = new JToolBar();    
		this.toolBar.setFloatable(false);    // 设置工具栏不能移动
		this.toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));      // 设置工具栏内容居中
		this.labelwelcome = new JLabel("欢迎你：");
		this.labelText = new JLabel(this.misUser.getUserName());        // 获取传入的misUser的username然后设为labelText
		this.buttonExit = new JButton("退出");
		this.buttonExit.setActionCommand("exit");
		this.buttonExit.addActionListener(this);
		this.buttonReLogin = new JButton("重新登录");
		this.buttonReLogin.setActionCommand("relogin");
		this.buttonReLogin.addActionListener(this);
		this.toolBar.add(this.labelwelcome);
		this.toolBar.add(this.labelText);
		this.toolBar.add(this.buttonExit);
		this.toolBar.add(this.buttonReLogin);
		this.toolBar.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(125, 147, 147)));
		this.panelBody.add(this.toolBar, BorderLayout.NORTH);           // 将工具栏添加进panelBody的boderLayout的上方
		
		// 设置主页面中间的功能操作区域
		this.panelWelcome = new JPanel();
		this.panelWelcome.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(125, 147, 147)));    // setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(24, 108, 224)));设置边框方法
		this.panelWelcome.setBackground(new Color(236, 236, 236));
		this.panelBody.add(this.panelWelcome, BorderLayout.CENTER);     // 将操作区域添加进panelBody的boderLayout的中间
		
		
		// 设置主页面下方的水印文字信息
		this.labelState = new JLabel("西安文理学院学生信息管理系统        by:wyh", JLabel.CENTER);
		this.panelBody.add(this.labelState, BorderLayout.SOUTH);        // 将文字信息添加进panelBody的boderLayout的下方
		
		// 设置窗体信息
		this.setTitle("西安文理学院学生信息管理系统主界面");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	// 写构造方法
	public MainFrame() {
		// TODO Auto-generated constructor stub
	}
	public MainFrame(MisUser misUser) {   // 将刚登录页面的loginUser传进来，给到等会窗体方法会用到的misUser，然后调用窗体方法
		this.misUser = misUser;
		this.init();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if("exit".equals(e.getActionCommand())) {       // 这就是通过ActionCommand判断事件
			int n = JOptionPane.showConfirmDialog(this, "您确定要退出？");       // 选择框：JOptionPane.showConfirmDialog(在哪弹出，"弹出的问题内容");   选择框会有选项yes或no，再根据选的是yes还是no进行之后的操作
			if(n == JOptionPane.YES_OPTION) {
				System.exit(0);                         // 退出窗体以及程序方法，用这个方法就不用再写this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}                          
		} else if("relogin".equals(e.getActionCommand())) {
			int n = JOptionPane.showConfirmDialog(this, "您确定要重新登录？");
			if(n == JOptionPane.YES_OPTION) {
				LoginFrame loginFrame = new LoginFrame();
				loginFrame.setBounds(720, 350, 500, 320);
				loginFrame.setVisible(true);
				this.dispose();                         // 关闭主页面但不会影响新生成的登录页面
			}   
		}
	}
}
