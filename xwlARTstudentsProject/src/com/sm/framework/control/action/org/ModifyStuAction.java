package com.sm.framework.control.action.org;

// 这是实现Action中的execute方法的ModifyStuAction实现类，该类的名字和地址需要跟数据库misFunction中的FunctionClass中对应功能存的数据一样
// 这个实现类对应的是点击“修改学生信息”操作按钮的显示页面功能

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.sm.framework.model.dao.impl.studentsDAO;
import com.sm.framework.model.dao.impl.studentsDAOImpl;
import com.sm.framework.model.entity.students;

public class ModifyStuAction implements Action, ActionListener {
	
	private JPanel panelWork = null;                   // 这是供所有操作进行的panelWork，Action接口实现的execute方法会调用这个panelWork
	
	private JPanel panelButton = null;                 // 按钮块
	
	private JTable table = null;                       // 表格块
	private DefaultTableModel tablemodel = null;       // DefaultTableModel实例化tablemodel，才能放入数据
	
	private JScrollPane scrollPane = null;             // 实例化JScrollPane才能显示表头
	private String[] title = {"学号", "姓名", "学院", "班级", "宿舍", "性别", "省份", "城市", "电话", "住址"};               // 表头数组
	private String[][] data = null;                                                // 表格数据数组
	private JButton buttonModify = null;                                           // 修改按钮
	private JButton buttonRefresh = null;                                          // 刷新按钮
	
	
	// 刷新方法
	private void refresh() {
		this.table.removeAll();              // 清空表格内容
		this.table.repaint();
		
		this.tablemodel = new DefaultTableModel(this.getData(new students()), this.title);        // 将表头和数据实例化给tablemodel，调用getData方法因为传入的OrgType没有值，根据万能查找的方法就是全部数据都查到
		this.table.setModel(this.tablemodel);                 // 表格用setModel方法设置刚的tablemodel
	}
	
	
	// getData方法，传入orgType返回存放数据的数组
	private String[][] getData(students students) {
		String[][] data = null;
		studentsDAO dao = new studentsDAOImpl();
		
		List<students> list = dao.findByLike(students);         // 调用的是万能查找的方法
		
		data = new String[list.size()][10];                    // 给存放数据的数组定义长度，查找到的数据集合长度和固定的数据种类10
		
		for(int i = 0; i < list.size(); i++) {
			data[i][0] = list.get(i).getStudent_id();
			data[i][1] = list.get(i).getStudent_name();
			data[i][2] = list.get(i).getStudent_yuan();
			data[i][3] = list.get(i).getStudent_class();
			data[i][4] = list.get(i).getStudent_room();
			data[i][5] = list.get(i).getStudent_sex();
			data[i][6] = list.get(i).getStudent_citysheng();
			data[i][7] = list.get(i).getStudent_cityshi();
			data[i][8] = list.get(i).getStudent_phone();
			data[i][9] = list.get(i).getStudent_home();
		}
		return data;
	}
	
	
	// 整体界面，要将标签组实例化然后添加刚才的两个界面的
	private void init() {
//		this.panelWork.removeAll();
//		this.panelWork.repaint();
		this.panelWork.setLayout(new BorderLayout());
		
//		this.panelWork = new JPanel(new BorderLayout());                // 实例化panelModfy并设置BorderLayout
		
		this.data = this.getData(new students());                         // 调用getData方法传入OrgType()实现全查
		
		this.tablemodel = new DefaultTableModel(this.data, this.title);  // 然后实例化表格数据DefaultTableModel
		this.table = new JTable(this.tablemodel);                        // 然后实例化表格
		this.scrollPane = new JScrollPane(this.table);                   // 实例化scrollPane将table传入不然会看不到表头
		
		this.panelWork.add(this.scrollPane, BorderLayout.CENTER);       // 然后将scrollPane添加进panelModfy的border中间
		
		this.panelButton = new JPanel();
		this.buttonModify = new JButton("修改");
		this.buttonModify.addActionListener(this);
		this.buttonRefresh = new JButton("刷新");
		this.buttonRefresh.addActionListener(this);
		
		this.panelButton.add(this.buttonModify);
		this.panelButton.add(this.buttonRefresh);
		this.panelWork.add(this.panelButton, BorderLayout.SOUTH);
	}

	
	// 实现接口Action中的execute方法，传入供操作的panelWork
	@Override
	public void execute(JPanel panelWork) {
		this.panelWork = panelWork;
		this.init();
	}

	
	// 事件监听代码
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.buttonRefresh) {               // 点击刷新按钮，执行表格刷新方法
			this.refresh();
		} 
		
		else if(e.getSource() == this.buttonModify) {           // 点击修改按钮
			int n = this.table.getSelectedRowCount();           // 调用表格中的getSelectedRowCount方法，获得选中的行数赋值给n
			if(n != 1) {
				JOptionPane.showMessageDialog(null, "请选择一条数据！");
				return;
			}
			
			// 进行修改时弹出的新页面
			String stuId = this.tablemodel.getValueAt(this.table.getSelectedRow(), 0).toString();          // 这句代码意思是获得点击行的第一个数据强转为字符串然后赋值给orgTypeId
			
			ModifyDialog modifyDialog = new ModifyDialog(stuId, this);                  // 然后调用实例化ModifyDialog调用构造函数传入orgTypeId
			
			modifyDialog.setBounds(510, 200, 900, 650);
			modifyDialog.setVisible(true);
		}
	}
	
	
	// 编写一个内部类显示详细的修改页面进行进一步修改
	public class ModifyDialog extends JDialog implements ActionListener {
		
		private students students = null;
		private ModifyStuAction modifyAction = null;         // 实例化承载的修改操作的页面
		private JPanel panelBody = null;
		private JScrollPane scrollPaneDisc = null;           // 文本框
				
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
		
		private JPanel buttonJPanle = null;
		private JButton saveOrgType = null;
		private JButton backOrgType = null;
		
		
		// 重写构造方法
		public ModifyDialog() {
			// TODO Auto-generated constructor stub
		}
		
		// 构造方法传入刚才点击行的orgTypeId还有当前的modifyAction修改操作页面
		public ModifyDialog(String studentsId, ModifyStuAction modifyAction) {
			this.students = new studentsDAOImpl().findById(studentsId);        // 调用接口的findById方法通过传入的id查找到当前进行操作的students对象
			this.modifyAction = modifyAction;
	
			this.init();
		}
		
		
		// 详细进行修改操作的窗体
		private void init() {
			this.panelBody = (JPanel)this.getContentPane();
			
			this.panelBody.setLayout(new GridBagLayout());               // set精准布局管理器
			GridBagConstraints constraints = new GridBagConstraints();
			
			
			this.StuId = new JLabel("学       号：");
			constraints.gridx = 0;
			constraints.gridy = 0;
			this.panelBody.add(this.StuId, constraints);
			this.StuIdIn = new JTextField(50);
			constraints.gridx = 1;
			constraints.gridy = 0;
			this.StuIdIn.setEditable(false);                           // setEditable设置不可更改id框
			this.StuIdIn.setText(this.students.getStudent_id());       // 将当前students对象的id设成框内的默认内容
			this.panelBody.add(this.StuIdIn, constraints);
			
			
			this.StuName = new JLabel("姓       名：");
			constraints.gridx = 0;
			constraints.gridy = 1;
			this.panelBody.add(this.StuName, constraints);
			this.StuNameIn = new JTextField(50);
			constraints.gridx = 1;
			constraints.gridy = 1;
			this.StuNameIn.setText(this.students.getStudent_name());
			this.panelBody.add(this.StuNameIn, constraints);
			
			
			this.StuYuan = new JLabel("学       院：");
			constraints.gridx = 0;
			constraints.gridy = 2;
			this.panelBody.add(this.StuYuan, constraints);
			this.StuYuanIn = new JTextField(50);
			constraints.gridx = 1;
			constraints.gridy = 2;
			this.StuYuanIn.setText(this.students.getStudent_yuan());
			this.panelBody.add(this.StuYuanIn, constraints);
			
			
			this.StuClass = new JLabel("班       级：");
			constraints.gridx = 0;
			constraints.gridy = 3;
			this.panelBody.add(this.StuClass, constraints);
			this.StuClassIn = new JTextField(50);
			constraints.gridx = 1;
			constraints.gridy = 3;
			this.StuClassIn.setText(this.students.getStudent_class());
			this.panelBody.add(this.StuClassIn, constraints);
			
			
			this.StuRoom = new JLabel("宿       舍：");
			constraints.gridx = 0;
			constraints.gridy = 4;
			this.panelBody.add(this.StuRoom, constraints);
			this.StuRoomIn = new JTextField(50);
			constraints.gridx = 1;
			constraints.gridy = 4;
			this.StuRoomIn.setText(this.students.getStudent_room());
			this.panelBody.add(this.StuRoomIn, constraints);
			
			
			this.StuSex = new JLabel("性       别：");
			constraints.gridx = 0;
			constraints.gridy = 5;
			this.panelBody.add(this.StuSex, constraints);
			this.StuSexIn = new JTextField(50);
			constraints.gridx = 1;
			constraints.gridy = 5;
			this.StuSexIn.setText(this.students.getStudent_sex());
			this.panelBody.add(this.StuSexIn, constraints);
			
			
			this.StuSheng = new JLabel("省       份：");
			constraints.gridx = 0;
			constraints.gridy = 6;
			this.panelBody.add(this.StuSheng, constraints);
			this.StuShengIn = new JTextField(50);
			constraints.gridx = 1;
			constraints.gridy = 6;
			this.StuShengIn.setText(this.students.getStudent_citysheng());
			this.panelBody.add(this.StuShengIn, constraints);
			
			
			this.StuShi = new JLabel("城       市：");
			constraints.gridx = 0;
			constraints.gridy = 7;
			this.panelBody.add(this.StuShi, constraints);
			this.StuShiIn = new JTextField(50);
			constraints.gridx = 1;
			constraints.gridy = 7;
			this.StuShiIn.setText(this.students.getStudent_cityshi());
			this.panelBody.add(this.StuShiIn, constraints);
			
			
			this.StuPhone = new JLabel("电       话：");
			constraints.gridx = 0;
			constraints.gridy = 8;
			this.panelBody.add(this.StuPhone, constraints);
			this.StuPhoneIn = new JTextField(50);
			constraints.gridx = 1;
			constraints.gridy = 8;
			this.StuPhoneIn.setText(this.students.getStudent_phone());
			this.panelBody.add(this.StuPhoneIn, constraints);
			
			
			this.StuHome = new JLabel("详细住址：");
			constraints.gridx = 0;
			constraints.gridy = 9;
			this.panelBody.add(this.StuHome, constraints);
			this.StuHomeIn = new JTextArea(15, 50);
			this.StuHomeIn.setText(this.students.getStudent_home());
			this.scrollPaneDisc = new JScrollPane();
			this.scrollPaneDisc.getViewport().add(this.StuHomeIn);
			constraints.gridx = 1;
			constraints.gridy = 9;
			this.panelBody.add(this.scrollPaneDisc, constraints);
			
			
			// 底部按钮panel代码
			this.buttonJPanle = new JPanel();
			this.saveOrgType = new JButton("保存");
			this.saveOrgType.addActionListener(this);
			this.backOrgType = new JButton("还原");
			this.backOrgType.addActionListener(this);
			this.buttonJPanle.add(this.saveOrgType);
			this.buttonJPanle.add(this.backOrgType);
			constraints.gridx = 1;
			constraints.gridy = 10;
			this.panelBody.add(this.buttonJPanle, constraints);
			
			this.setTitle("修改学生信息详细页面");
		}
		
		
		// 事件监听代码
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == this.saveOrgType) {           // 当点击的是保存按钮时
				
				
				students stu = new students();
				stu.setStudent_id(this.StuIdIn.getText());
				stu.setStudent_name(this.StuNameIn.getText());
				stu.setStudent_yuan(this.StuYuanIn.getText());
				stu.setStudent_class(this.StuClassIn.getText());
				stu.setStudent_room(this.StuRoomIn.getText());
				stu.setStudent_sex(this.StuSexIn.getText());
				stu.setStudent_citysheng(this.StuShengIn.getText());
				stu.setStudent_cityshi(this.StuShiIn.getText());
				stu.setStudent_phone(this.StuPhoneIn.getText());
				stu.setStudent_home(this.StuHomeIn.getText());
				
				
				studentsDAO dao = new studentsDAOImpl();       
				if(dao.modify(stu)) {
					JOptionPane.showMessageDialog(this, "修改成功");
					
					this.modifyAction.refresh();              // 再调用修改操作页面的刷新方法
					
					this.dispose();                         // 关闭主页面但不会影响新生成的登录页面
				}
			} 
			else if(e.getSource() == this.backOrgType) {      // 当点击的是还原按钮时
				
				this.StuNameIn.setText(this.students.getStudent_name());
				this.StuYuanIn.setText(this.students.getStudent_yuan());
				this.StuClassIn.setText(this.students.getStudent_class());
				this.StuRoomIn.setText(this.students.getStudent_room());
				this.StuSexIn.setText(this.students.getStudent_sex());
				this.StuShengIn.setText(this.students.getStudent_citysheng());
				this.StuShiIn.setText(this.students.getStudent_cityshi());
				this.StuPhoneIn.setText(this.students.getStudent_sex());
				this.StuHomeIn.setText(this.students.getStudent_home());
							
				
				this.saveOrgType.doClick();                   // 框内内容还原之后再点击一下保存按钮实现还原
			}
		}
	}

}
