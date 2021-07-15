package com.sm.framework.control.action.org;

// 这是实现Action中的execute方法的CreateUserAction实现类，该类的名字和地址需要跟数据库myMenu中的FunctionClass中对应功能存的数据一样
// 这个实现类对应的是点击“新增职工”操作按钮的显示页面功能

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sm.framework.model.dao.impl.MisUserDAO;
import com.sm.framework.model.dao.impl.MisUserDAOImpl;
import com.sm.framework.model.entity.MisUser;


public class CreateUserAction implements Action,ActionListener {
	
	private JPanel panelBody1 = null;
	private JPanel panelButton = null;
	
	private JLabel UserName = null;                       // 职工姓名
	private JTextField NameIn = null;                     // 姓名输入文字条
	private JLabel UserId = null;                         // 职工用户名
	private JTextField IdIn = null;                       // 用户名输入文字条
	private JLabel UserPwd = null;                        // 职工密码
	private JTextField PwdIn = null;                      // 密码输入文字条
	private JLabel UserRole = null;                       // 职工权限
	private JTextField RoleIn = null;                     // 权限输入文字条
	
	private JButton buttonSubmit = null;                 // 提交按钮
	private JButton buttonReset = null;                  // 清空按钮
	
	// clear方法是点击重置按钮之后进行的步骤，将填写的信息清空
	private void clear() {
		this.NameIn.setText("");
		this.IdIn.setText("");
		this.PwdIn.setText("");
		this.RoleIn.setText("");
	}
	
	private void init() {
		
		this.panelBody1.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.anchor = GridBagConstraints.EAST;
		
		this.UserName = new JLabel("职工姓名：");
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.panelBody1.add(this.UserName, constraints);
		this.NameIn = new JTextField(40);
		constraints.gridx = 1;
		constraints.gridy = 0;
		this.panelBody1.add(this.NameIn, constraints);
		
		this.UserId = new JLabel("职工账号：");
		constraints.gridx = 0;
		constraints.gridy = 1;
		this.panelBody1.add(this.UserId, constraints);
		this.IdIn = new JTextField(40);
		constraints.gridx = 1;
		constraints.gridy = 1;
		this.panelBody1.add(this.IdIn, constraints);
		
		this.UserPwd = new JLabel("职工密码：");
		constraints.gridx = 0;
		constraints.gridy = 2;
		this.panelBody1.add(this.UserPwd, constraints);
		this.PwdIn = new JTextField(40);
		constraints.gridx = 1;
		constraints.gridy = 2;
		this.panelBody1.add(this.PwdIn, constraints);
		
		this.UserRole = new JLabel("职工权限：");
		constraints.gridx = 0;
		constraints.gridy = 3;
		this.panelBody1.add(this.UserRole, constraints);
		this.RoleIn = new JTextField(40);
		constraints.gridx = 1;
		constraints.gridy = 3;
		this.panelBody1.add(this.RoleIn, constraints);
		
		
		this.panelButton = new JPanel();
		this.buttonSubmit = new JButton("新增");
		this.buttonSubmit.addActionListener(this);
		this.buttonReset = new JButton("重置");
		this.buttonReset.addActionListener(this);
		this.panelButton.setOpaque(false);
		this.panelButton.add(this.buttonSubmit);
		this.panelButton.add(this.buttonReset);
		constraints.gridx = 1;
		constraints.gridy = 4;
		this.panelBody1.add(this.panelButton, constraints);

	}

	
	// 构造方法的编写，会直接将该页面显示在传入的panelWork页面
	@Override
	public void execute(JPanel panelWork) {
		this.panelBody1 = panelWork;
		this.init();
	}

	
	// 事件处理
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.buttonReset) {
			this.clear();
		}
		else if(e.getSource() == this.buttonSubmit) {
			
			String name = this.NameIn.getText();                       // 获取输入的姓名
			String id = this.IdIn.getText();                           // 获取输入的账号
			String pwd = this.PwdIn.getText();                         // 获取输入的密码
			String role = this.RoleIn.getText();                       // 获取输入的权限
			
			if(name == null || (name.trim()).length() <= 0 || id == null || (id.trim()).length() <= 0 || pwd == null || (pwd.trim()).length() <= 0 || role == null || (role.trim()).length() <= 0) {            //判空
				JOptionPane.showMessageDialog(null, "每个输入框都不能为空！");
				return;
		}
			MisUser MisUser = new MisUser();                           // 实例化MisUser用里面的set设置对象属性方法
			
			MisUser.setUserName(this.NameIn.getText());
			MisUser.setUserId(this.IdIn.getText());
			MisUser.setUserPass(this.PwdIn.getText());
			MisUser.setRoleId(this.RoleIn.getText());
			
			MisUserDAO dao = new MisUserDAOImpl();                     // 实例化MisUserDAO接口，用里面的insertMisUser新增方法
			
			if(dao.insertMisUser(MisUser)) {
				JOptionPane.showMessageDialog(null, "新增职工成功");
				this.clear();
			}
	}

}
	
}