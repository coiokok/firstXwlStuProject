package com.sm.framework.control.action.org;

// ����ʵ��Action�е�execute������DeleteClassActionʵ���࣬��������ֺ͵�ַ��Ҫ�����ݿ�misFunction�е�FunctionClass�ж�Ӧ���ܴ������һ��
// ���ʵ�����Ӧ���ǵ����ɾ���༶��������ť����ʾҳ�湦��

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

import com.sm.framework.model.dao.impl.classNameDAO;
import com.sm.framework.model.dao.impl.classNameDAOImpl;
import com.sm.framework.model.entity.className;

public class DeleteClassAction implements Action, ActionListener {
	
	private JPanel panelWork = null;                   // ���ǹ����в������е�panelWork��Action�ӿ�ʵ�ֵ�execute������������panelWork

	private JPanel panelButton = null;                 // ��ť��
	
	private JTable table = null;                       // ����
	private DefaultTableModel tablemodel = null;       // DefaultTableModelʵ����tablemodel�����ܷ�������
	
	private JScrollPane scrollPane = null;             // ʵ����JScrollPane������ʾ��ͷ
	private String[] title = {"�༶��"};               // ��ͷ����
	private String[][] data = null;                                                // �����������
	private JButton buttonDelete = null;                                           // ɾ����ť
	private JButton buttonRefresh = null;                                          // ˢ�°�ť
	
	
	// ˢ�·���
	private void refresh() {
		this.table.removeAll();              // ��ձ������
		this.table.repaint();
		
		this.tablemodel = new DefaultTableModel(this.getData(new className()), this.title);        // ����ͷ������ʵ������tablemodel������getData������Ϊ�����OrgTypeû��ֵ���������ܲ��ҵķ�������ȫ�����ݶ��鵽
		this.table.setModel(this.tablemodel);                 // �����setModel�������øյ�tablemodel
	}
	
	
	// getData����������orgType���ش�����ݵ�����
	private String[][] getData(className classname) {
		String[][] data = null;
		classNameDAO dao = new classNameDAOImpl();
		
		List<className> list = dao.findByLike(classname);         // ���õ������ܲ��ҵķ���
		
		data = new String[list.size()][1];                    // ��������ݵ����鶨�峤�ȣ����ҵ������ݼ��ϳ��Ⱥ͹̶�����������1
		
		for(int i = 0; i < list.size(); i++) {
			data[i][0] = list.get(i).getStudent_class();
		}
		return data;
	}
	
	
	// ������棬Ҫ����ǩ��ʵ����Ȼ����Ӹղŵ����������
	private void init() {
//		this.panelWork.removeAll();
//		this.panelWork.repaint();
		this.panelWork.setLayout(new BorderLayout());
		
//		this.panelWork = new JPanel(new BorderLayout());                // ʵ����panelModfy������BorderLayout
		
		this.data = this.getData(new className());                         // ����getData��������className()ʵ��ȫ��
		this.tablemodel = new DefaultTableModel(this.data, this.title);  // Ȼ��ʵ�����������DefaultTableModel
		this.table = new JTable(this.tablemodel);                        // Ȼ��ʵ�������
		this.scrollPane = new JScrollPane(this.table);                   // ʵ����scrollPane��table���벻Ȼ�ῴ������ͷ
		
		this.panelWork.add(this.scrollPane, BorderLayout.CENTER);       // Ȼ��scrollPane��ӽ�panelModfy��border�м�
		
		this.panelButton = new JPanel();
		this.buttonDelete = new JButton("ɾ��");
		this.buttonDelete.addActionListener(this);
		this.buttonRefresh = new JButton("ˢ��");
		this.buttonRefresh.addActionListener(this);
		
		this.panelButton.add(this.buttonDelete);
		this.panelButton.add(this.buttonRefresh);
		this.panelWork.add(this.panelButton, BorderLayout.SOUTH);
	}

	
	// ʵ�ֽӿ�Action�е�execute���������빩������panelWork
	@Override
	public void execute(JPanel panelWork) {
		this.panelWork = panelWork;
		this.init();
	}

	
	// �¼���������
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.buttonRefresh) {               // ���ˢ�°�ť��ִ�б��ˢ�·���
			this.refresh();
		} 
		
		else if(e.getSource() == this.buttonDelete) {           // ���ɾ����ť
			int n = this.table.getSelectedRowCount();           // ���ñ���е�getSelectedRowCount���������ѡ�е�������ֵ��n
			if(n != 1) {
				JOptionPane.showMessageDialog(null, "��ѡ��һ�����ݣ�");
				return;
			}
			
			// ����ɾ������
			String classname = this.tablemodel.getValueAt(this.table.getSelectedRow(), 0).toString();          // ��������˼�ǻ�õ���еĵ�һ������Ҳ����idǿתΪ�ַ���
			
			classNameDAO dao = new classNameDAOImpl();        // ʵ����OrgTypeDAO���������removeɾ��������orgTypeId����
			if(dao.removeClass(classname)) {
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
				
				this.refresh();              // �ٵ���ˢ�·���
			}
		}
	}

}
