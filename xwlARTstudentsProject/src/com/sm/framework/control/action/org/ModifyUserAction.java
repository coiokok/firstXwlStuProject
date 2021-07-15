package com.sm.framework.control.action.org;

// 这是实现Action中的execute方法的ModifyUserAction实现类，该类的名字和地址需要跟数据库misFunction中的FunctionClass中对应功能存的数据一样
// 这个实现类对应的是点击“管理职工”操作按钮的显示页面功能

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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.sm.framework.model.dao.impl.MisUserDAO;
import com.sm.framework.model.dao.impl.MisUserDAOImpl;
import com.sm.framework.model.entity.MisUser;

public class ModifyUserAction implements Action, ActionListener {
	
	private JPanel panelWork = null;                   // 这是供所有操作进行的panelWork，Action接口实现的execute方法会调用这个panelWork
	
	private JPanel panelButton = null;                 // 按钮块
	
	private JTable table = null;                       // 表格块
	private DefaultTableModel tablemodel = null;       // DefaultTableModel实例化tablemodel，才能放入数据
	
	private JScrollPane scrollPane = null;             // 实例化JScrollPane才能显示表头
	private String[] title = {"职工姓名", "职工账号", "职工密码", "职工权限"};               // 表头数组
	private String[][] data = null;                                                // 表格数据数组
	private JButton buttonModify = null;                                           // 修改按钮
	private JButton buttonRefresh = null;                                          // 刷新按钮
	
	
	// 刷新方法
	private void refresh() {
		this.table.removeAll();              // 清空表格内容
		this.table.repaint();
		
		this.tablemodel = new DefaultTableModel(this.getData(new MisUser()), this.title);        // 将表头和数据实例化给tablemodel，调用getData方法因为传入的OrgType没有值，根据万能查找的方法就是全部数据都查到
		this.table.setModel(this.tablemodel);                 // 表格用setModel方法设置刚的tablemodel
	}
	
	
	// getData方法，传入orgType返回存放数据的数组
	private String[][] getData(MisUser MisUser) {
		String[][] data = null;
		MisUserDAO dao = new MisUserDAOImpl();
		
		List<MisUser> list = dao.queryMisUser(MisUser);         // 调用的是万能查找的方法
		
		data = new String[list.size()][4];                    // 给存放数据的数组定义长度，查找到的数据集合长度和固定的数据种类4
		
		for(int i = 0; i < list.size(); i++) {
			data[i][0] = list.get(i).getUserName();
			data[i][1] = list.get(i).getUserId();
			data[i][2] = list.get(i).getUserPass();
			data[i][3] = list.get(i).getRoleId();
		}
		return data;
	}
	
	
	// 整体界面，要将标签组实例化然后添加刚才的两个界面的
	private void init() {
//		this.panelWork.removeAll();
//		this.panelWork.repaint();
		this.panelWork.setLayout(new BorderLayout());
		
//		this.panelWork = new JPanel(new BorderLayout());                // 实例化panelModfy并设置BorderLayout
		
		this.data = this.getData(new MisUser());                         // 调用getData方法传入OrgType()实现全查
		
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
			String UserId = this.tablemodel.getValueAt(this.table.getSelectedRow(), 1).toString();          // 这句代码意思是获得点击行的第二个数据强也就是id转为字符串
			
			ModifyDialog modifyDialog = new ModifyDialog(UserId, this);                  // 然后调用实例化ModifyDialog调用构造函数传入orgTypeId
			
			modifyDialog.setBounds(510, 200, 900, 650);
			modifyDialog.setVisible(true);
		}
	}
	
	
	// 编写一个内部类显示详细的修改页面进行进一步修改
	public class ModifyDialog extends JDialog implements ActionListener {
		
		private MisUser MisUser = null;
		private ModifyUserAction modifyAction = null;         // 实例化承载的修改操作的页面
		private JPanel panelBody = null; 
				
		private JLabel UserName = null;                       // 职工姓名
		private JTextField NameIn = null;                     // 姓名输入文字条
		private JLabel UserId = null;                         // 职工用户名
		private JTextField IdIn = null;                       // 用户名输入文字条
		private JLabel UserPwd = null;                        // 职工密码
		private JTextField PwdIn = null;                      // 密码输入文字条
		private JLabel UserRole = null;                       // 职工权限
		private JTextField RoleIn = null;                     // 权限输入文字条
		
		private JPanel buttonJPanle = null;
		private JButton saveOrgType = null;
		private JButton backOrgType = null;
		
		
		// 重写构造方法
		public ModifyDialog() {
			// TODO Auto-generated constructor stub
		}
		
		// 构造方法传入刚才点击行的orgTypeId还有当前的modifyAction修改操作页面
		public ModifyDialog(String UserId, ModifyUserAction modifyAction) {
			this.MisUser = new MisUserDAOImpl().queryMisUser(UserId);        // 调用接口的findById方法通过传入的id查找到当前进行操作的MisUser对象
			this.modifyAction = modifyAction;
	
			this.init();
		}
		
		
		// 详细进行修改操作的窗体
		private void init() {
			this.panelBody = (JPanel)this.getContentPane();
			
			this.panelBody.setLayout(new GridBagLayout());               // set精准布局管理器
			GridBagConstraints constraints = new GridBagConstraints();
			
			
			this.UserName = new JLabel("职工姓名：");
			constraints.gridx = 0;
			constraints.gridy = 0;
			this.panelBody.add(this.UserName, constraints);
			this.NameIn = new JTextField(40);
			constraints.gridx = 1;
			constraints.gridy = 0;
			this.NameIn.setText(this.MisUser.getUserName());
			this.panelBody.add(this.NameIn, constraints);
			
			this.UserId = new JLabel("职工账号：");
			constraints.gridx = 0;
			constraints.gridy = 1;
			this.panelBody.add(this.UserId, constraints);
			this.IdIn = new JTextField(40);
			constraints.gridx = 1;
			constraints.gridy = 1;
			this.IdIn.setText(this.MisUser.getUserId());
			this.panelBody.add(this.IdIn, constraints);
			
			this.UserPwd = new JLabel("职工密码：");
			constraints.gridx = 0;
			constraints.gridy = 2;
			this.panelBody.add(this.UserPwd, constraints);
			this.PwdIn = new JTextField(40);
			constraints.gridx = 1;
			constraints.gridy = 2;
			this.PwdIn.setText(this.MisUser.getUserPass());
			this.panelBody.add(this.PwdIn, constraints);
			
			this.UserRole = new JLabel("职工权限：");
			constraints.gridx = 0;
			constraints.gridy = 3;
			this.panelBody.add(this.UserRole, constraints);
			this.RoleIn = new JTextField(40);
			constraints.gridx = 1;
			constraints.gridy = 3;
			this.RoleIn.setText(this.MisUser.getRoleId());
			this.panelBody.add(this.RoleIn, constraints);
			
						
			// 底部按钮panel代码
			this.buttonJPanle = new JPanel();
			this.saveOrgType = new JButton("保存");
			this.saveOrgType.addActionListener(this);
			this.backOrgType = new JButton("还原");
			this.backOrgType.addActionListener(this);
			this.buttonJPanle.add(this.saveOrgType);
			this.buttonJPanle.add(this.backOrgType);
			constraints.gridx = 1;
			constraints.gridy = 4;
			this.panelBody.add(this.buttonJPanle, constraints);
			
			this.setTitle("修改职工信息详细页面");
		}
		
		
		// 事件监听代码
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == this.saveOrgType) {           // 当点击的是保存按钮时
				
				
				MisUser user = new MisUser();
				user.setUserName(this.NameIn.getText());
				user.setUserId(this.IdIn.getText());
				user.setUserPass(this.PwdIn.getText());
				user.setRoleId(this.RoleIn.getText());
				
				
				MisUserDAO dao = new MisUserDAOImpl();        
				if(dao.updateMisUser(user)) {
					JOptionPane.showMessageDialog(this, "修改成功");
					
					this.modifyAction.refresh();              // 再调用修改操作页面的刷新方法
					
					this.dispose();                         // 关闭主页面但不会影响新生成的登录页面
				}
			} 
			else if(e.getSource() == this.backOrgType) {      // 当点击的是还原按钮时
				
				this.NameIn.setText(this.MisUser.getUserName());
				this.IdIn.setText(this.MisUser.getUserId());
				this.PwdIn.setText(this.MisUser.getUserPass());
				this.RoleIn.setText(this.MisUser.getRoleId());
							
				
				this.saveOrgType.doClick();                   // 框内内容还原之后再点击一下保存按钮实现还原
			}
		}
	}

}
