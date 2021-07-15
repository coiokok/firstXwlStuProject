package com.sm.framework.control.action.org;

// ����ʵ��Action�е�execute������ModifyStuActionʵ���࣬��������ֺ͵�ַ��Ҫ�����ݿ�misFunction�е�FunctionClass�ж�Ӧ���ܴ������һ��
// ���ʵ�����Ӧ���ǵ�����޸�ѧ����Ϣ��������ť����ʾҳ�湦��

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
	
	private JPanel panelWork = null;                   // ���ǹ����в������е�panelWork��Action�ӿ�ʵ�ֵ�execute������������panelWork
	
	private JPanel panelButton = null;                 // ��ť��
	
	private JTable table = null;                       // ����
	private DefaultTableModel tablemodel = null;       // DefaultTableModelʵ����tablemodel�����ܷ�������
	
	private JScrollPane scrollPane = null;             // ʵ����JScrollPane������ʾ��ͷ
	private String[] title = {"ѧ��", "����", "ѧԺ", "�༶", "����", "�Ա�", "ʡ��", "����", "�绰", "סַ"};               // ��ͷ����
	private String[][] data = null;                                                // �����������
	private JButton buttonModify = null;                                           // �޸İ�ť
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
		
//		this.panelWork = new JPanel(new BorderLayout());                // ʵ����panelModfy������BorderLayout
		
		this.data = this.getData(new students());                         // ����getData��������OrgType()ʵ��ȫ��
		
		this.tablemodel = new DefaultTableModel(this.data, this.title);  // Ȼ��ʵ�����������DefaultTableModel
		this.table = new JTable(this.tablemodel);                        // Ȼ��ʵ�������
		this.scrollPane = new JScrollPane(this.table);                   // ʵ����scrollPane��table���벻Ȼ�ῴ������ͷ
		
		this.panelWork.add(this.scrollPane, BorderLayout.CENTER);       // Ȼ��scrollPane��ӽ�panelModfy��border�м�
		
		this.panelButton = new JPanel();
		this.buttonModify = new JButton("�޸�");
		this.buttonModify.addActionListener(this);
		this.buttonRefresh = new JButton("ˢ��");
		this.buttonRefresh.addActionListener(this);
		
		this.panelButton.add(this.buttonModify);
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
		
		else if(e.getSource() == this.buttonModify) {           // ����޸İ�ť
			int n = this.table.getSelectedRowCount();           // ���ñ���е�getSelectedRowCount���������ѡ�е�������ֵ��n
			if(n != 1) {
				JOptionPane.showMessageDialog(null, "��ѡ��һ�����ݣ�");
				return;
			}
			
			// �����޸�ʱ��������ҳ��
			String stuId = this.tablemodel.getValueAt(this.table.getSelectedRow(), 0).toString();          // ��������˼�ǻ�õ���еĵ�һ������ǿתΪ�ַ���Ȼ��ֵ��orgTypeId
			
			ModifyDialog modifyDialog = new ModifyDialog(stuId, this);                  // Ȼ�����ʵ����ModifyDialog���ù��캯������orgTypeId
			
			modifyDialog.setBounds(510, 200, 900, 650);
			modifyDialog.setVisible(true);
		}
	}
	
	
	// ��дһ���ڲ�����ʾ��ϸ���޸�ҳ����н�һ���޸�
	public class ModifyDialog extends JDialog implements ActionListener {
		
		private students students = null;
		private ModifyStuAction modifyAction = null;         // ʵ�������ص��޸Ĳ�����ҳ��
		private JPanel panelBody = null;
		private JScrollPane scrollPaneDisc = null;           // �ı���
				
		private JLabel StuId = null;                         // ѧ��ѧ��
		private JTextField StuIdIn = null;                   // ѧ��ѧ������������
		private JLabel StuName = null;                       // ѧ������
		private JTextField StuNameIn = null;                 // ѧ����������������
		private JLabel StuYuan = null;                       // ѧ��ѧԺ
		private JTextField StuYuanIn = null;                 // ѧ��ѧԺ������
		private JLabel StuClass = null;                      // ѧ���༶
		private JTextField StuClassIn = null;                // ѧ���༶����������
		private JLabel StuRoom = null;                       // ѧ������
		private JTextField StuRoomIn = null;                 // ѧ����������������
		private JLabel StuSex = null;                        // ѧ���Ա�
		private JTextField StuSexIn = null;                  // ѧ���Ա�����������
		private JLabel StuSheng = null;                      // ѧ��ʡ��
		private JTextField StuShengIn = null;                // ѧ��ʡ������������
		private JLabel StuShi = null;                        // ѧ������
		private JTextField StuShiIn = null;                  // ѧ����������������
		private JLabel StuPhone = null;                      // ѧ���绰
		private JTextField StuPhoneIn = null;                // ѧ���绰����������
		private JLabel StuHome = null;                       // ѧ��סַ
		private JTextArea StuHomeIn = null;                  // ѧ��סַ����������
		
		private JPanel buttonJPanle = null;
		private JButton saveOrgType = null;
		private JButton backOrgType = null;
		
		
		// ��д���췽��
		public ModifyDialog() {
			// TODO Auto-generated constructor stub
		}
		
		// ���췽������ղŵ���е�orgTypeId���е�ǰ��modifyAction�޸Ĳ���ҳ��
		public ModifyDialog(String studentsId, ModifyStuAction modifyAction) {
			this.students = new studentsDAOImpl().findById(studentsId);        // ���ýӿڵ�findById����ͨ�������id���ҵ���ǰ���в�����students����
			this.modifyAction = modifyAction;
	
			this.init();
		}
		
		
		// ��ϸ�����޸Ĳ����Ĵ���
		private void init() {
			this.panelBody = (JPanel)this.getContentPane();
			
			this.panelBody.setLayout(new GridBagLayout());               // set��׼���ֹ�����
			GridBagConstraints constraints = new GridBagConstraints();
			
			
			this.StuId = new JLabel("ѧ       �ţ�");
			constraints.gridx = 0;
			constraints.gridy = 0;
			this.panelBody.add(this.StuId, constraints);
			this.StuIdIn = new JTextField(50);
			constraints.gridx = 1;
			constraints.gridy = 0;
			this.StuIdIn.setEditable(false);                           // setEditable���ò��ɸ���id��
			this.StuIdIn.setText(this.students.getStudent_id());       // ����ǰstudents�����id��ɿ��ڵ�Ĭ������
			this.panelBody.add(this.StuIdIn, constraints);
			
			
			this.StuName = new JLabel("��       ����");
			constraints.gridx = 0;
			constraints.gridy = 1;
			this.panelBody.add(this.StuName, constraints);
			this.StuNameIn = new JTextField(50);
			constraints.gridx = 1;
			constraints.gridy = 1;
			this.StuNameIn.setText(this.students.getStudent_name());
			this.panelBody.add(this.StuNameIn, constraints);
			
			
			this.StuYuan = new JLabel("ѧ       Ժ��");
			constraints.gridx = 0;
			constraints.gridy = 2;
			this.panelBody.add(this.StuYuan, constraints);
			this.StuYuanIn = new JTextField(50);
			constraints.gridx = 1;
			constraints.gridy = 2;
			this.StuYuanIn.setText(this.students.getStudent_yuan());
			this.panelBody.add(this.StuYuanIn, constraints);
			
			
			this.StuClass = new JLabel("��       ����");
			constraints.gridx = 0;
			constraints.gridy = 3;
			this.panelBody.add(this.StuClass, constraints);
			this.StuClassIn = new JTextField(50);
			constraints.gridx = 1;
			constraints.gridy = 3;
			this.StuClassIn.setText(this.students.getStudent_class());
			this.panelBody.add(this.StuClassIn, constraints);
			
			
			this.StuRoom = new JLabel("��       �᣺");
			constraints.gridx = 0;
			constraints.gridy = 4;
			this.panelBody.add(this.StuRoom, constraints);
			this.StuRoomIn = new JTextField(50);
			constraints.gridx = 1;
			constraints.gridy = 4;
			this.StuRoomIn.setText(this.students.getStudent_room());
			this.panelBody.add(this.StuRoomIn, constraints);
			
			
			this.StuSex = new JLabel("��       ��");
			constraints.gridx = 0;
			constraints.gridy = 5;
			this.panelBody.add(this.StuSex, constraints);
			this.StuSexIn = new JTextField(50);
			constraints.gridx = 1;
			constraints.gridy = 5;
			this.StuSexIn.setText(this.students.getStudent_sex());
			this.panelBody.add(this.StuSexIn, constraints);
			
			
			this.StuSheng = new JLabel("ʡ       �ݣ�");
			constraints.gridx = 0;
			constraints.gridy = 6;
			this.panelBody.add(this.StuSheng, constraints);
			this.StuShengIn = new JTextField(50);
			constraints.gridx = 1;
			constraints.gridy = 6;
			this.StuShengIn.setText(this.students.getStudent_citysheng());
			this.panelBody.add(this.StuShengIn, constraints);
			
			
			this.StuShi = new JLabel("��       �У�");
			constraints.gridx = 0;
			constraints.gridy = 7;
			this.panelBody.add(this.StuShi, constraints);
			this.StuShiIn = new JTextField(50);
			constraints.gridx = 1;
			constraints.gridy = 7;
			this.StuShiIn.setText(this.students.getStudent_cityshi());
			this.panelBody.add(this.StuShiIn, constraints);
			
			
			this.StuPhone = new JLabel("��       ����");
			constraints.gridx = 0;
			constraints.gridy = 8;
			this.panelBody.add(this.StuPhone, constraints);
			this.StuPhoneIn = new JTextField(50);
			constraints.gridx = 1;
			constraints.gridy = 8;
			this.StuPhoneIn.setText(this.students.getStudent_phone());
			this.panelBody.add(this.StuPhoneIn, constraints);
			
			
			this.StuHome = new JLabel("��ϸסַ��");
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
			
			
			// �ײ���ťpanel����
			this.buttonJPanle = new JPanel();
			this.saveOrgType = new JButton("����");
			this.saveOrgType.addActionListener(this);
			this.backOrgType = new JButton("��ԭ");
			this.backOrgType.addActionListener(this);
			this.buttonJPanle.add(this.saveOrgType);
			this.buttonJPanle.add(this.backOrgType);
			constraints.gridx = 1;
			constraints.gridy = 10;
			this.panelBody.add(this.buttonJPanle, constraints);
			
			this.setTitle("�޸�ѧ����Ϣ��ϸҳ��");
		}
		
		
		// �¼���������
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == this.saveOrgType) {           // ��������Ǳ��水ťʱ
				
				
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
					JOptionPane.showMessageDialog(this, "�޸ĳɹ�");
					
					this.modifyAction.refresh();              // �ٵ����޸Ĳ���ҳ���ˢ�·���
					
					this.dispose();                         // �ر���ҳ�浫����Ӱ�������ɵĵ�¼ҳ��
				}
			} 
			else if(e.getSource() == this.backOrgType) {      // ��������ǻ�ԭ��ťʱ
				
				this.StuNameIn.setText(this.students.getStudent_name());
				this.StuYuanIn.setText(this.students.getStudent_yuan());
				this.StuClassIn.setText(this.students.getStudent_class());
				this.StuRoomIn.setText(this.students.getStudent_room());
				this.StuSexIn.setText(this.students.getStudent_sex());
				this.StuShengIn.setText(this.students.getStudent_citysheng());
				this.StuShiIn.setText(this.students.getStudent_cityshi());
				this.StuPhoneIn.setText(this.students.getStudent_sex());
				this.StuHomeIn.setText(this.students.getStudent_home());
							
				
				this.saveOrgType.doClick();                   // �������ݻ�ԭ֮���ٵ��һ�±��水ťʵ�ֻ�ԭ
			}
		}
	}

}
