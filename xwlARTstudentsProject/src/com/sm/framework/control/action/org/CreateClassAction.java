package com.sm.framework.control.action.org;

// 这是实现Action中的execute方法的CreateClassAction实现类，该类的名字和地址需要跟数据库myMenu中的FunctionClass中对应功能存的数据一样
// 这个实现类对应的是点击“新增班级”操作按钮的显示页面功能

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.sm.framework.model.dao.impl.classNameDAO;
import com.sm.framework.model.dao.impl.classNameDAOImpl;
import com.sm.framework.model.entity.className;
import com.sm.framework.view.LoginFrame;


public class CreateClassAction implements Action,ActionListener {
	
	private JPanel panelBody1 = null;
	private JPanel panelButton = null;
	
	private JLabel ClassId = null;                       // 班级文字
	private JTextField ClassIn = null;                   // 班级输入文字条
	
	private JButton buttonSubmit = null;                 // 提交按钮
	private JButton buttonReset = null;                  // 清空按钮
	
	// clear方法是点击重置按钮之后进行的步骤，将填写的信息清空
	private void clear() {
		this.ClassIn.setText("");
	}
	
	private void init() {
		
		this.panelBody1.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.anchor = GridBagConstraints.EAST;
		
		this.ClassId = new JLabel("班级名：");
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.panelBody1.add(this.ClassId, constraints);
		this.ClassIn = new JTextField(40);
		constraints.gridx = 1;
		constraints.gridy = 0;
		this.panelBody1.add(this.ClassIn, constraints);
		
		
		this.panelButton = new JPanel();
		this.buttonSubmit = new JButton("新增");
		this.buttonSubmit.addActionListener(this);
		this.buttonReset = new JButton("重置");
		this.buttonReset.addActionListener(this);
		this.panelButton.setOpaque(false);
		this.panelButton.add(this.buttonSubmit);
		this.panelButton.add(this.buttonReset);
		constraints.gridx = 1;
		constraints.gridy = 1;
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
			String id = this.ClassIn.getText();                       // 获取输入的id
			if(id == null || (id.trim()).length() <= 0 ) {            //判空
				JOptionPane.showMessageDialog(null, "请输入班级名");
				return;
		}
			className classname = new className();                    // 实例化className用里面的setStudent_class方法
			
			classname.setStudent_class(this.ClassIn.getText());
			
			classNameDAO dao = new classNameDAOImpl();                // 实例化classNameDAO接口，用里面的addClass新增方法
			
			if(dao.addClass(classname)) {
				JOptionPane.showMessageDialog(null, "新增班级成功");
				this.clear();
			}
	}

}
	
}