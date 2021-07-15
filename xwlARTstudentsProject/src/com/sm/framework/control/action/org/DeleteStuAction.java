package com.sm.framework.control.action.org;

// ����ʵ��Action�е�execute������DeleteStuActionʵ���࣬��������ֺ͵�ַ��Ҫ�����ݿ�misFunction�е�FunctionClass�ж�Ӧ���ܴ������һ��
// ���ʵ�����Ӧ���ǵ����ɾ��ѧ����Ϣ��������ť����ʾҳ�湦��

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

import com.sm.framework.model.dao.impl.studentsDAO;
import com.sm.framework.model.dao.impl.studentsDAOImpl;
import com.sm.framework.model.entity.students;

public class DeleteStuAction implements Action, ActionListener {
	
	private JPanel panelWork = null;                   // ���ǹ����в������е�panelWork��Action�ӿ�ʵ�ֵ�execute������������panelWork

	private JPanel panelButton = null;                 // ��ť��
	
	private JTable table = null;                       // ����
	private DefaultTableModel tablemodel = null;       // DefaultTableModelʵ����tablemodel�����ܷ�������
	
	private JScrollPane scrollPane = null;             // ʵ����JScrollPane������ʾ��ͷ
	private String[] title = {"ѧ��", "����", "ѧԺ", "�༶", "����", "�Ա�", "ʡ��", "����", "�绰", "סַ"};               // ��ͷ����
	private String[][] data = null;                                                // �����������
	private JButton buttonDelete = null;                                           // ɾ����ť
	private JButton buttonRefresh = null;                                          // ˢ�°�ť
	
	
	// ˢ�·���
	private void refresh() {
		this.table.removeAll();              // ��ձ������
		this.table.repaint();
		
		this.tablemodel = new DefaultTableModel(this.getData(new students()), this.title);        // ����ͷ������ʵ������tablemodel������getData������Ϊ�����OrgTypeû��ֵ���������ܲ��ҵķ�������ȫ�����ݶ��鵽
		this.table.setModel(this.tablemodel);                 // �����setModel�������øյ�tablemodel
	}
	
	
	// getData����������orgType���ش�����ݵ�����
	private String[][] getData(students students) {
		String[][] data = null;
		studentsDAO dao = new studentsDAOImpl();
		
		List<students> list = dao.findByLike(students);         // ���õ������ܲ��ҵķ���
		
		data = new String[list.size()][10];                    // ��������ݵ����鶨�峤�ȣ����ҵ������ݼ��ϳ��Ⱥ͹̶�����������10
		
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
	
	
	// ������棬Ҫ����ǩ��ʵ����Ȼ����Ӹղŵ����������
	private void init() {
//		this.panelWork.removeAll();
//		this.panelWork.repaint();
		this.panelWork.setLayout(new BorderLayout());
		
//		this.panelWork = new JPanel(new BorderLayout());                 // ʵ����panelModfy������BorderLayout
		
		this.data = this.getData(new students());                        // ����getData��������students()ʵ��ȫ��
		this.tablemodel = new DefaultTableModel(this.data, this.title);  // Ȼ��ʵ�����������DefaultTableModel
		this.table = new JTable(this.tablemodel);                        // Ȼ��ʵ�������
		this.scrollPane = new JScrollPane(this.table);                   // ʵ����scrollPane��table���벻Ȼ�ῴ������ͷ
		
		this.panelWork.add(this.scrollPane, BorderLayout.CENTER);        // Ȼ��scrollPane��ӽ�panelModfy��border�м�
		
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
			String classname = this.tablemodel.getValueAt(this.table.getSelectedRow(), 0).toString();          // ��������˼�ǻ�õ���еĵ�һ������ǿҲ����idתΪ�ַ���
			
			studentsDAO dao = new studentsDAOImpl();   
			if(dao.remove(classname)) {
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
				
				this.refresh();              // �ٵ���ˢ�·���
			}
		}
	}

}
