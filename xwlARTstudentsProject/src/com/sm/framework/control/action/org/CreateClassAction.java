package com.sm.framework.control.action.org;

// ����ʵ��Action�е�execute������CreateClassActionʵ���࣬��������ֺ͵�ַ��Ҫ�����ݿ�myMenu�е�FunctionClass�ж�Ӧ���ܴ������һ��
// ���ʵ�����Ӧ���ǵ���������༶��������ť����ʾҳ�湦��

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.sm.framework.model.dao.impl.classNameDAO;
import com.sm.framework.model.dao.impl.classNameDAOImpl;
import com.sm.framework.model.entity.className;
import com.sm.framework.view.LoginFrame;


public class CreateClassAction implements Action,ActionListener {
	
	private JPanel panelBody1 = null;
	private JPanel panelButton = null;
	
	private JLabel ClassId = null;                       // �༶����
	private JTextField ClassIn = null;                   // �༶����������
	
	private JButton buttonSubmit = null;                 // �ύ��ť
	private JButton buttonReset = null;                  // ��հ�ť
	
	// clear�����ǵ�����ð�ť֮����еĲ��裬����д����Ϣ���
	private void clear() {
		this.ClassIn.setText("");
	}
	
	private void init() {
		
		this.panelBody1.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.anchor = GridBagConstraints.EAST;
		
		this.ClassId = new JLabel("�༶����");
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.panelBody1.add(this.ClassId, constraints);
		this.ClassIn = new JTextField(40);
		constraints.gridx = 1;
		constraints.gridy = 0;
		this.panelBody1.add(this.ClassIn, constraints);
		
		
		this.panelButton = new JPanel();
		this.buttonSubmit = new JButton("����");
		this.buttonSubmit.addActionListener(this);
		this.buttonReset = new JButton("����");
		this.buttonReset.addActionListener(this);
		this.panelButton.setOpaque(false);
		this.panelButton.add(this.buttonSubmit);
		this.panelButton.add(this.buttonReset);
		constraints.gridx = 1;
		constraints.gridy = 1;
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
			String id = this.ClassIn.getText();                       // ��ȡ�����id
			if(id == null || (id.trim()).length() <= 0 ) {            //�п�
				JOptionPane.showMessageDialog(null, "������༶��");
				return;
		}
			className classname = new className();                    // ʵ����className�������setStudent_class����
			
			classname.setStudent_class(this.ClassIn.getText());
			
			classNameDAO dao = new classNameDAOImpl();                // ʵ����classNameDAO�ӿڣ��������addClass��������
			
			if(dao.addClass(classname)) {
				JOptionPane.showMessageDialog(null, "�����༶�ɹ�");
				this.clear();
			}
	}

}
	
}