package com.sm.framework.control.action.org;

// 这是实现Action中的execute方法的CreateStuAction实现类，该类的名字和地址需要跟数据库myMenu中的FunctionClass中对应功能存的数据一样
// 这个实现类对应的是点击“新增学生信息”操作按钮的显示页面功能

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sm.framework.model.dao.impl.studentsDAO;
import com.sm.framework.model.dao.impl.studentsDAOImpl;
import com.sm.framework.model.entity.students;
import com.sm.framework.view.LoginFrame;


public class CreateStuAction implements Action,ActionListener {
	
	private JPanel panelBody1 = null;
	private JScrollPane scrollPaneDisc = null;           // 文本框
	private JPanel panelButton = null;
	
	private JLabel StuId = null;                         // 学生学号
	private JTextField StuIdIn = null;                   // 学生学号输入文字条
	private JLabel StuName = null;                       // 学生姓名
	private JTextField StuNameIn = null;                 // 学生姓名输入文字条
	private JLabel StuYuan = null;                       // 学生学院
	private JTextField StuYuanIn = null;                 // 学生学院文字条
	private JLabel StuClass = null;                      // 学生班级
	private JTextField StuClassIn = null;                // 学生班级输入文字条
	private JLabel StuRoom = null;                       // 学生宿舍
	private JTextField StuRoomIn = null;                 // 学生宿舍输入文字条
	private JLabel StuSex = null;                        // 学生性别
	private JTextField StuSexIn = null;                  // 学生性别输入文字条
	private JLabel StuSheng = null;                      // 学生省份
	private JTextField StuShengIn = null;                // 学生省份输入文字条
	private JLabel StuShi = null;                        // 学生城市
	private JTextField StuShiIn = null;                  // 学生城市输入文字条
	private JLabel StuPhone = null;                      // 学生电话
	private JTextField StuPhoneIn = null;                // 学生电话输入文字条
	private JLabel StuHome = null;                       // 学生住址
	private JTextArea StuHomeIn = null;                  // 学生住址输入文字条
	
	private JButton buttonSubmit = null;                 // 提交按钮
	private JButton buttonReset = null;                  // 清空按钮
	
	// clear方法是点击重置按钮之后进行的步骤，将填写的信息清空
	private void clear() {
		this.StuIdIn.setText("");
		this.StuNameIn.setText("");
		this.StuYuanIn.setText("");
		this.StuClassIn.setText("");
		this.StuRoomIn.setText("");
		this.StuSexIn.setText("");
		this.StuShengIn.setText("");
		this.StuShiIn.setText("");
		this.StuPhoneIn.setText("");
		this.StuHomeIn.setText("");
	}
	
	private void init() {
		
		this.panelBody1.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.anchor = GridBagConstraints.EAST;
		
		this.StuId = new JLabel("学       号：");
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.panelBody1.add(this.StuId, constraints);
		this.StuIdIn = new JTextField(50);
		constraints.gridx = 1;
		constraints.gridy = 0;
		this.panelBody1.add(this.StuIdIn, constraints);
		
		this.StuName = new JLabel("姓       名：");
		constraints.gridx = 0;
		constraints.gridy = 1;
		this.panelBody1.add(this.StuName, constraints);
		this.StuNameIn = new JTextField(50);
		constraints.gridx = 1;
		constraints.gridy = 1;
		this.panelBody1.add(this.StuNameIn, constraints);
		
		this.StuYuan = new JLabel("学       院：");
		constraints.gridx = 0;
		constraints.gridy = 2;
		this.panelBody1.add(this.StuYuan, constraints);
		this.StuYuanIn = new JTextField(50);
		constraints.gridx = 1;
		constraints.gridy = 2;
		this.panelBody1.add(this.StuYuanIn, constraints);
		
		this.StuClass = new JLabel("班       级：");
		constraints.gridx = 0;
		constraints.gridy = 3;
		this.panelBody1.add(this.StuClass, constraints);
		this.StuClassIn = new JTextField(50);
		constraints.gridx = 1;
		constraints.gridy = 3;
		this.panelBody1.add(this.StuClassIn, constraints);
		
		this.StuRoom = new JLabel("宿       舍：");
		constraints.gridx = 0;
		constraints.gridy = 4;
		this.panelBody1.add(this.StuRoom, constraints);
		this.StuRoomIn = new JTextField(50);
		constraints.gridx = 1;
		constraints.gridy = 4;
		this.panelBody1.add(this.StuRoomIn, constraints);
		
		this.StuSex = new JLabel("性       别：");
		constraints.gridx = 0;
		constraints.gridy = 5;
		this.panelBody1.add(this.StuSex, constraints);
		this.StuSexIn = new JTextField(50);
		constraints.gridx = 1;
		constraints.gridy = 5;
		this.panelBody1.add(this.StuSexIn, constraints);
		
		this.StuSheng = new JLabel("省       份：");
		constraints.gridx = 0;
		constraints.gridy = 6;
		this.panelBody1.add(this.StuSheng, constraints);
		this.StuShengIn = new JTextField(50);
		constraints.gridx = 1;
		constraints.gridy = 6;
		this.panelBody1.add(this.StuShengIn, constraints);
		
		this.StuShi = new JLabel("城       市：");
		constraints.gridx = 0;
		constraints.gridy = 7;
		this.panelBody1.add(this.StuShi, constraints);
		this.StuShiIn = new JTextField(50);
		constraints.gridx = 1;
		constraints.gridy = 7;
		this.panelBody1.add(this.StuShiIn, constraints);
		
		this.StuPhone = new JLabel("电       话：");
		constraints.gridx = 0;
		constraints.gridy = 8;
		this.panelBody1.add(this.StuPhone, constraints);
		this.StuPhoneIn = new JTextField(50);
		constraints.gridx = 1;
		constraints.gridy = 8;
		this.panelBody1.add(this.StuPhoneIn, constraints);
		
		this.StuHome = new JLabel("详细住址：");
		constraints.gridx = 0;
		constraints.gridy = 9;
		this.panelBody1.add(this.StuHome, constraints);
		this.StuHomeIn = new JTextArea("", 15, 50);
		this.scrollPaneDisc = new JScrollPane(this.StuHomeIn);
		constraints.gridx = 1;
		constraints.gridy = 9;
		this.panelBody1.add(this.scrollPaneDisc, constraints);
		
		
		this.panelButton = new JPanel();
		this.buttonSubmit = new JButton("新增");
		this.buttonSubmit.addActionListener(this);
		this.buttonReset = new JButton("重置");
		this.buttonReset.addActionListener(this);
		this.panelButton.setOpaque(false);
		this.panelButton.add(this.buttonSubmit);
		this.panelButton.add(this.buttonReset);
		constraints.gridx = 1;
		constraints.gridy = 10;
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
			String id = this.StuIdIn.getText();                         // 获取输入的学号
			String name = this.StuNameIn.getText();                     // 获取输入的姓名
			String yuan = this.StuYuanIn.getText();                     // 获取输入的学院
			String classname = this.StuClassIn.getText();               // 获取输入的班级
			String room = this.StuRoomIn.getText();                     // 获取输入的宿舍
			String sex = this.StuSexIn.getText();                       // 获取输入的性别
			String sheng = this.StuShengIn.getText();                   // 获取输入的省份
			String shi = this.StuShiIn.getText();                       // 获取输入的城市
			String phone = this.StuPhoneIn.getText();                   // 获取输入的电话
			String home = this.StuHomeIn.getText();                     // 获取输入的住址
			
			if(id == null || (id.trim()).length() <= 0 || name == null || (name.trim()).length() <= 0 || yuan == null || (yuan.trim()).length() <= 0 || sex == null || (sex.trim()).length() <= 0 ) {            //判空
				JOptionPane.showMessageDialog(null, "学号、姓名、学院、性别不能为空！");
				return;
		}
			students students = new students();                    // 实例化className用里面的setStudent_class方法
			
			students.setStudent_id(this.StuIdIn.getText());
			students.setStudent_name(this.StuNameIn.getText());
			students.setStudent_yuan(this.StuYuanIn.getText());
			students.setStudent_class(this.StuClassIn.getText());
			students.setStudent_room(this.StuRoomIn.getText());
			students.setStudent_sex(this.StuSexIn.getText());
			students.setStudent_citysheng(this.StuShengIn.getText());
			students.setStudent_cityshi(this.StuShiIn.getText());
			students.setStudent_phone(this.StuPhoneIn.getText());
			students.setStudent_home(this.StuHomeIn.getText());
			
			studentsDAO dao = new studentsDAOImpl();            
			
			if(dao.add(students)) {
				JOptionPane.showMessageDialog(null, "新增学生成功");
				this.clear();
			}
	}

}
	
}