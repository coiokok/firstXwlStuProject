package com.sm.framework.control.action.org;

// ����ʵ��Action�е�execute������CreateStuActionʵ���࣬��������ֺ͵�ַ��Ҫ�����ݿ�myMenu�е�FunctionClass�ж�Ӧ���ܴ������һ��
// ���ʵ�����Ӧ���ǵ��������ѧ����Ϣ��������ť����ʾҳ�湦��

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
	private JScrollPane scrollPaneDisc = null;           // �ı���
	private JPanel panelButton = null;
	
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
	
	private JButton buttonSubmit = null;                 // �ύ��ť
	private JButton buttonReset = null;                  // ��հ�ť
	
	// clear�����ǵ�����ð�ť֮����еĲ��裬����д����Ϣ���
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
		
		this.StuId = new JLabel("ѧ       �ţ�");
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.panelBody1.add(this.StuId, constraints);
		this.StuIdIn = new JTextField(50);
		constraints.gridx = 1;
		constraints.gridy = 0;
		this.panelBody1.add(this.StuIdIn, constraints);
		
		this.StuName = new JLabel("��       ����");
		constraints.gridx = 0;
		constraints.gridy = 1;
		this.panelBody1.add(this.StuName, constraints);
		this.StuNameIn = new JTextField(50);
		constraints.gridx = 1;
		constraints.gridy = 1;
		this.panelBody1.add(this.StuNameIn, constraints);
		
		this.StuYuan = new JLabel("ѧ       Ժ��");
		constraints.gridx = 0;
		constraints.gridy = 2;
		this.panelBody1.add(this.StuYuan, constraints);
		this.StuYuanIn = new JTextField(50);
		constraints.gridx = 1;
		constraints.gridy = 2;
		this.panelBody1.add(this.StuYuanIn, constraints);
		
		this.StuClass = new JLabel("��       ����");
		constraints.gridx = 0;
		constraints.gridy = 3;
		this.panelBody1.add(this.StuClass, constraints);
		this.StuClassIn = new JTextField(50);
		constraints.gridx = 1;
		constraints.gridy = 3;
		this.panelBody1.add(this.StuClassIn, constraints);
		
		this.StuRoom = new JLabel("��       �᣺");
		constraints.gridx = 0;
		constraints.gridy = 4;
		this.panelBody1.add(this.StuRoom, constraints);
		this.StuRoomIn = new JTextField(50);
		constraints.gridx = 1;
		constraints.gridy = 4;
		this.panelBody1.add(this.StuRoomIn, constraints);
		
		this.StuSex = new JLabel("��       ��");
		constraints.gridx = 0;
		constraints.gridy = 5;
		this.panelBody1.add(this.StuSex, constraints);
		this.StuSexIn = new JTextField(50);
		constraints.gridx = 1;
		constraints.gridy = 5;
		this.panelBody1.add(this.StuSexIn, constraints);
		
		this.StuSheng = new JLabel("ʡ       �ݣ�");
		constraints.gridx = 0;
		constraints.gridy = 6;
		this.panelBody1.add(this.StuSheng, constraints);
		this.StuShengIn = new JTextField(50);
		constraints.gridx = 1;
		constraints.gridy = 6;
		this.panelBody1.add(this.StuShengIn, constraints);
		
		this.StuShi = new JLabel("��       �У�");
		constraints.gridx = 0;
		constraints.gridy = 7;
		this.panelBody1.add(this.StuShi, constraints);
		this.StuShiIn = new JTextField(50);
		constraints.gridx = 1;
		constraints.gridy = 7;
		this.panelBody1.add(this.StuShiIn, constraints);
		
		this.StuPhone = new JLabel("��       ����");
		constraints.gridx = 0;
		constraints.gridy = 8;
		this.panelBody1.add(this.StuPhone, constraints);
		this.StuPhoneIn = new JTextField(50);
		constraints.gridx = 1;
		constraints.gridy = 8;
		this.panelBody1.add(this.StuPhoneIn, constraints);
		
		this.StuHome = new JLabel("��ϸסַ��");
		constraints.gridx = 0;
		constraints.gridy = 9;
		this.panelBody1.add(this.StuHome, constraints);
		this.StuHomeIn = new JTextArea("", 15, 50);
		this.scrollPaneDisc = new JScrollPane(this.StuHomeIn);
		constraints.gridx = 1;
		constraints.gridy = 9;
		this.panelBody1.add(this.scrollPaneDisc, constraints);
		
		
		this.panelButton = new JPanel();
		this.buttonSubmit = new JButton("����");
		this.buttonSubmit.addActionListener(this);
		this.buttonReset = new JButton("����");
		this.buttonReset.addActionListener(this);
		this.panelButton.setOpaque(false);
		this.panelButton.add(this.buttonSubmit);
		this.panelButton.add(this.buttonReset);
		constraints.gridx = 1;
		constraints.gridy = 10;
		this.panelBody1.add(this.panelButton, constraints);

	}

	
	// ���췽���ı�д����ֱ�ӽ���ҳ����ʾ�ڴ����panelWorkҳ��
	@Override
	public void execute(JPanel panelWork) {
		this.panelBody1 = panelWork;
		this.init();
	}

	
	// �¼�����
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.buttonReset) {
			this.clear();
		}
		else if(e.getSource() == this.buttonSubmit) {
			String id = this.StuIdIn.getText();                         // ��ȡ�����ѧ��
			String name = this.StuNameIn.getText();                     // ��ȡ���������
			String yuan = this.StuYuanIn.getText();                     // ��ȡ�����ѧԺ
			String classname = this.StuClassIn.getText();               // ��ȡ����İ༶
			String room = this.StuRoomIn.getText();                     // ��ȡ���������
			String sex = this.StuSexIn.getText();                       // ��ȡ������Ա�
			String sheng = this.StuShengIn.getText();                   // ��ȡ�����ʡ��
			String shi = this.StuShiIn.getText();                       // ��ȡ����ĳ���
			String phone = this.StuPhoneIn.getText();                   // ��ȡ����ĵ绰
			String home = this.StuHomeIn.getText();                     // ��ȡ�����סַ
			
			if(id == null || (id.trim()).length() <= 0 || name == null || (name.trim()).length() <= 0 || yuan == null || (yuan.trim()).length() <= 0 || sex == null || (sex.trim()).length() <= 0 ) {            //�п�
				JOptionPane.showMessageDialog(null, "ѧ�š�������ѧԺ���Ա���Ϊ�գ�");
				return;
		}
			students students = new students();                    // ʵ����className�������setStudent_class����
			
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
				JOptionPane.showMessageDialog(null, "����ѧ���ɹ�");
				this.clear();
			}
	}

}
	
}