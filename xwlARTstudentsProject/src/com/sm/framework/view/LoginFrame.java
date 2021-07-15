package com.sm.framework.view;

// 这是供有权限的管理员登录的页面，根据MisUser数据库中存的对象进行登录

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sm.framework.model.dao.impl.MisUserDAO;
import com.sm.framework.model.dao.impl.MisUserDAOImpl;
import com.sm.framework.model.entity.MisUser;

public class LoginFrame extends JFrame implements ActionListener {     // 这个页面继承JFrame写页面，实现ActionListener接口添加方法
	
	private MisUser loginUser = null;                   // 这个loginUser是获取用户输入的用户名以及密码看是否在权限数据库中存在
	
	private JPanel panelBody = null;                    // 总的bodypanel块
	private JLabel labelTitle = null;                   // 标题panel
	private JLabel labelName = null;                    // 用户名
	private JTextField fieldName = null;                // 填写用户名文字域
	private JLabel labelPassword = null;                // 密码
	private JPasswordField fieldPassword = null;        // 填写密码文字域
	
	private JPanel panelButton = null;                  // 底部按钮的panel
	private JButton buttonLogin = null;                 // 登录按钮
	private JButton buttonReset = null;                 // 重置按钮
	
	
	// checkUser方法，传入一个MisUser实例，用来检测用户输入的用户名是否在数据库中还有是否正确，之后的登录事件中用到了
	private boolean checkUser(MisUser misUser) {
		boolean flag = false;
		MisUserDAO userDAO = new MisUserDAOImpl();           // 使用MisUser接口
		MisUser tempUser = userDAO.queryMisUser(misUser.getUserId());          // 使用里面的queryMisUser(String Id)的查找方式，获取之后用户输入的信息，通过getUserId获得其用户名，然后将用户名传入方法，将返回的对象获取给tempUser
		if(tempUser != null && tempUser.getUserPass().equals(misUser.getUserPass())) {          // 如果tempUser不为空也就是在数据库中找到了并且根据misUser的id找到的tempUser的密码和misUser密码相同即为登陆成功
			flag = true;
			this.loginUser = tempUser;           // 当前登录的loginUser就为找到的tempUser
		}
		return flag;
	}
	
	// clear方法是点击重置按钮之后进行的步骤，将填写的信息清空，之后的重置事件中用到了
	private void clear() {
		this.fieldName.setText("");
		this.fieldPassword.setText("");
	}
	
	
	// 窗体的编写
	private void init() {
		Container content = this.getContentPane();                       // 实例化ContentPane
		content.setLayout(new BorderLayout());                           // 并设置其为BorderLayout
		
		this.panelBody = new WelcomePanel();                             // 用背景panel实例化JPanel这样给窗体背景
		
		this.panelBody.setLayout(new GridBagLayout());                   // 通过setLayout方法设置GridBagLayout精确布局管理器
		this.panelBody.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(24, 108, 224)));    // 这是设置边框及颜色的代码，new Color( , , )是通过三元素进行设置
		
		content.add(this.panelBody, BorderLayout.CENTER);                // 将panelbody设进content
		
		GridBagConstraints constraints = new GridBagConstraints();       // 实例化GridBagConstraints精确布局管理器constraints放入
		
		this.labelTitle = new JLabel("学生信息管理系统登录");
		this.labelTitle.setForeground(Color.WHITE);                      // setForeground() 设置文字颜色的方法
		this.labelTitle.setFont(new Font("楷体", Font.BOLD, 18));        // setFont(new Font("字体", Font.样式, 字体大小))    设置字体样式的方法
		constraints.gridx = 1;
		constraints.gridy = 0;
		this.panelBody.add(this.labelTitle, constraints);
		
		// 下面这句代码之后的放入都是靠右对齐的，是为了让用户名和密码的Label贴紧文本框的
		constraints.anchor = GridBagConstraints.EAST;
		
		this.labelName = new JLabel("用 户 名:");
		this.labelName.setForeground(Color.WHITE);                      // setForeground() 设置文字颜色的方法
		this.labelName.setFont(new Font("楷体", Font.BOLD, 16));        // setFont(new Font("字体", Font.样式, 字体大小))    设置字体样式的方法
		constraints.gridx = 0;
		constraints.gridy = 1;
		this.panelBody.add(this.labelName, constraints);
		this.fieldName = new JTextField(16);
		constraints.gridx = 1;
		constraints.gridy = 1;
		this.panelBody.add(this.fieldName, constraints);
		
		this.labelPassword = new JLabel("密    码:");
		this.labelPassword.setForeground(Color.WHITE);                      // setForeground() 设置文字颜色的方法
		this.labelPassword.setFont(new Font("楷体", Font.BOLD, 16));        // setFont(new Font("字体", Font.样式, 字体大小))    设置字体样式的方法
		constraints.gridx = 0;
		constraints.gridy = 2;
		this.panelBody.add(this.labelPassword, constraints);
		this.fieldPassword = new JPasswordField(16);
		constraints.gridx = 1;
		constraints.gridy = 2;
		this.panelBody.add(this.fieldPassword, constraints);
		
		this.panelButton = new JPanel();
		this.panelButton.setOpaque(false);          // 设置按钮附近颜色透明成背景颜色
		this.buttonLogin = new JButton("登 录");                   // setForeground() 设置文字颜色的方法
		this.buttonLogin.setFont(new Font("黑体", Font.BOLD, 14));        // setFont(new Font("字体", Font.样式, 字体大小))    设置字体样式的方法
		this.buttonLogin.setActionCommand("login");   // setActionCommand为一个字符串之后在判断事件时用getActionCommand看是触发的哪个事件
		this.buttonLogin.addActionListener(this);     // 将buttonLogin添加进事件监听
		this.buttonReset = new JButton("重 置");
		this.buttonReset.setFont(new Font("黑体", Font.BOLD, 14));        // setFont(new Font("字体", Font.样式, 字体大小))    设置字体样式的方法
		this.buttonReset.addActionListener(this);
		this.buttonReset.setActionCommand("Reset");
		
		// 把两个按钮添加进panelButton然后添加进panelBody
		this.panelButton.add(this.buttonLogin);
		this.panelButton.add(this.buttonReset);
		constraints.gridx = 1;
		constraints.gridy = 3;
		this.panelBody.add(this.panelButton, constraints);
		
		
		this.setTitle("用户登录");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public LoginFrame() {
		this.init();
	}
	
	public static void main(String[] args) {
		LoginFrame loginFrame = new LoginFrame();
		loginFrame.setBounds(720, 350, 500, 320);
		loginFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("login".equals(e.getActionCommand())) {       // 这就是通过ActionCommand判断事件， 记住"常量".equals(变量)
			String id = this.fieldName.getText();        // 获取用户输入的用户名
			String password = new String(this.fieldPassword.getPassword());           // 获取用户输入的密码，别忘了强转成字符串
			
			if(id == null || id.length() <= 0 || password == null || password.length() <= 0) {      // 如果用户名或者密码有一项输入的是空的就不能再进行
				JOptionPane.showMessageDialog(this, "用户名或密码不能为空！");           
				return;
			}
			
			// 判断输入的用户名是否存在以及正确
			MisUser user = new MisUser();      // 实例化一个user以便存入刚输入的用户名和密码
			user.setUserId(id);
			user.setUserPass(password);
			// 调用本类开头声明的checkUser方法，将刚存好的user实例传进方法
			if(this.checkUser(user)) {
				JOptionPane.showMessageDialog(this, "登录成功！");      // 如果返回的flag为true则弹出主页面mainFrame
				MainFrame mainFrame = new MainFrame(this.loginUser);   // 并且弹出的主页面mainFrame实例化时同时传入刚登录的checkUser所获取的loginUser
				mainFrame.setBounds(410, 120, 1100, 800);
				mainFrame.setVisible(true);
				this.dispose();           // 使用dispose方法将当前的登录页面关闭
			} else {
				JOptionPane.showMessageDialog(this, "用户名或密码错误！");       // 如果checkUser方法返回的flag为false时则弹出用户名口令错误！
			}
		} else if("Reset".equals(e.getActionCommand())) {
			this.clear();
		}
	}

}
