package com.sm.framework.control.action.org;

// 这是实现Action中的execute方法的ShowStuAction实现类，该类的名字和地址需要跟数据库misFunction中的FunctionClass中对应功能存的数据一样
// 这个实现类对应的是点击“显示学生信息”操作按钮的显示页面功能

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sm.framework.model.dao.impl.studentsDAO;
import com.sm.framework.model.dao.impl.studentsDAOImpl;
import com.sm.framework.model.entity.students;

public class ShowStuAction implements Action {
	
	private JPanel panelWork = null;                   // 这是供所有操作进行的panelWork，Action接口实现的execute方法会调用这个panelWork
	
	private JTable table = null;                       // 表格块
	private DefaultTableModel tablemodel = null;       // DefaultTableModel实例化tablemodel，才能放入数据
	
	private JScrollPane scrollPane = null;             // 实例化JScrollPane才能显示表头
	private String[] title = {"学号", "姓名", "学院", "班级", "宿舍", "性别", "省份", "城市", "电话", "住址"};               // 表头数组
	private String[][] data = null;                                                // 表格数据数组
	
		
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
		
//		this.panelWork = new JPanel(new BorderLayout());                 // 实例化panelModfy并设置BorderLayout
		
		this.data = this.getData(new students());                        // 调用getData方法传入students()实现全查
		this.tablemodel = new DefaultTableModel(this.data, this.title);  // 然后实例化表格数据DefaultTableModel
		this.table = new JTable(this.tablemodel);                        // 然后实例化表格
		this.scrollPane = new JScrollPane(this.table);                   // 实例化scrollPane将table传入不然会看不到表头
		
		this.panelWork.add(this.scrollPane, BorderLayout.CENTER);        // 然后将scrollPane添加进panelModfy的border中间
		
	}

	
	// 实现接口Action中的execute方法，传入供操作的panelWork
	@Override
	public void execute(JPanel panelWork) {
		this.panelWork = panelWork;
		this.init();
	}

}
