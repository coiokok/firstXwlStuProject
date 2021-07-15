package com.sm.framework.control.action.org;

// ����ʵ��Action�е�execute������ShowUserActionʵ���࣬��������ֺ͵�ַ��Ҫ�����ݿ�misFunction�е�FunctionClass�ж�Ӧ���ܴ������һ��
// ���ʵ�����Ӧ���ǵ������ʾְ����������ť����ʾҳ�湦��

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sm.framework.model.dao.impl.MisUserDAO;
import com.sm.framework.model.dao.impl.MisUserDAOImpl;
import com.sm.framework.model.entity.MisUser;

public class ShowUserAction implements Action {
	
	private JPanel panelWork = null;                   // ���ǹ����в������е�panelWork��Action�ӿ�ʵ�ֵ�execute������������panelWork
	
	private JTable table = null;                       // ����
	private DefaultTableModel tablemodel = null;       // DefaultTableModelʵ����tablemodel�����ܷ�������
	
	private JScrollPane scrollPane = null;             // ʵ����JScrollPane������ʾ��ͷ
	private String[] title = {"ְ������", "ְ���˺�", "ְ������", "ְ��Ȩ��"};               // ��ͷ����
	private String[][] data = null;                                                // �����������
	
		
	// getData����������orgType���ش�����ݵ�����
	private String[][] getData(MisUser MisUser) {
		String[][] data = null;
		MisUserDAO dao = new MisUserDAOImpl();
		
		List<MisUser> list = dao.queryMisUser(MisUser);         // ���õ������ܲ��ҵķ���
		
		data = new String[list.size()][4];                      // ��������ݵ����鶨�峤�ȣ����ҵ������ݼ��ϳ��Ⱥ͹̶�����������10
		
		for(int i = 0; i < list.size(); i++) {
			data[i][0] = list.get(i).getUserName();
			data[i][1] = list.get(i).getUserId();
			data[i][2] = list.get(i).getUserPass();
			data[i][3] = list.get(i).getRoleId();
		}
		return data;
	}
	
	
	// ������棬Ҫ����ǩ��ʵ����Ȼ����Ӹղŵ����������
	private void init() {
//		this.panelWork.removeAll();
//		this.panelWork.repaint();
		this.panelWork.setLayout(new BorderLayout());
		
//		this.panelWork = new JPanel(new BorderLayout());                 // ʵ����panelModfy������BorderLayout
		
		this.data = this.getData(new MisUser());                         // ����getData��������MisUser()ʵ��ȫ��
		this.tablemodel = new DefaultTableModel(this.data, this.title);  // Ȼ��ʵ�����������DefaultTableModel
		this.table = new JTable(this.tablemodel);                        // Ȼ��ʵ�������
		this.scrollPane = new JScrollPane(this.table);                   // ʵ����scrollPane��table���벻Ȼ�ῴ������ͷ
		
		this.panelWork.add(this.scrollPane, BorderLayout.CENTER);        // Ȼ��scrollPane��ӽ�panelModfy��border�м�
		
	}

	
	// ʵ�ֽӿ�Action�е�execute���������빩������panelWork
	@Override
	public void execute(JPanel panelWork) {
		this.panelWork = panelWork;
		this.init();
	}

}
