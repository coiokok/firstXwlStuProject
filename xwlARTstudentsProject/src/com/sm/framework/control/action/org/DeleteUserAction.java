package com.sm.framework.control.action.org;

// 这是实现Action中的execute方法的DeleteUserAction实现类，该类的名字和地址需要跟数据库misFunction中的FunctionClass中对应功能存的数据一样
// 这个实现类对应的是点击“删除职工”操作按钮的显示页面功能

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sm.framework.model.dao.impl.MisUserDAO;
import com.sm.framework.model.dao.impl.MisUserDAOImpl;
import com.sm.framework.model.entity.MisUser;

public class DeleteUserAction implements Action, ActionListener {
	
	private JPanel panelWork = null;                   // 这是供所有操作进行的panelWork，Action接口实现的execute方法会调用这个panelWork

	private JPanel panelButton = null;                 // 按钮块
	
	private JTable table = null;                       // 表格块
	private DefaultTableModel tablemodel = null;       // DefaultTableModel实例化tablemodel，才能放入数据
	
	private JScrollPane scrollPane = null;             // 实例化JScrollPane才能显示表头
	private String[] title = {"职工姓名", "职工账号", "职工密码", "职工权限"};       // 表头数组
	private String[][] data = null;                                                // 表格数据数组
	private JButton buttonDelete = null;                                           // 删除按钮
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
		
//		this.panelWork = new JPanel(new BorderLayout());                 // 实例化panelModfy并设置BorderLayout
		
		this.data = this.getData(new MisUser());                        // 调用getData方法传入students()实现全查
		this.tablemodel = new DefaultTableModel(this.data, this.title);  // 然后实例化表格数据DefaultTableModel
		this.table = new JTable(this.tablemodel);                        // 然后实例化表格
		this.scrollPane = new JScrollPane(this.table);                   // 实例化scrollPane将table传入不然会看不到表头
		
		this.panelWork.add(this.scrollPane, BorderLayout.CENTER);        // 然后将scrollPane添加进panelModfy的border中间
		
		this.panelButton = new JPanel();
		this.buttonDelete = new JButton("删除");
		this.buttonDelete.addActionListener(this);
		this.buttonRefresh = new JButton("刷新");
		this.buttonRefresh.addActionListener(this);
		
		this.panelButton.add(this.buttonDelete);
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
		
		else if(e.getSource() == this.buttonDelete) {           // 点击删除按钮
			int n = this.table.getSelectedRowCount();           // 调用表格中的getSelectedRowCount方法，获得选中的行数赋值给n
			if(n != 1) {
				JOptionPane.showMessageDialog(null, "请选择一条数据！");
				return;
			}
			
			// 进行删除操作
			String UserId = this.tablemodel.getValueAt(this.table.getSelectedRow(), 1).toString();          // 这句代码意思是获得点击行的第二个数据强也就是id转为字符串
			
			MisUserDAO dao = new MisUserDAOImpl();   
			if(dao.deleteMisUser(UserId)) {
				JOptionPane.showMessageDialog(null, "删除成功");
				
				this.refresh();              // 再调用刷新方法
			}
		}
	}

}
