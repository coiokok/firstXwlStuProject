package com.sm.framework.control.action.org;

// ����ʵ��Action�е�execute������CreateUserActionʵ���࣬��������ֺ͵�ַ��Ҫ�����ݿ�myMenu�е�FunctionClass�ж�Ӧ���ܴ������һ��
// ���ʵ�����Ӧ���ǵ��������ְ����������ť����ʾҳ�湦��

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sm.framework.model.dao.impl.MisUserDAO;
import com.sm.framework.model.dao.impl.MisUserDAOImpl;
import com.sm.framework.model.entity.MisUser;


public class CreateUserAction implements Action,ActionListener {
	
	private JPanel panelBody1 = null;
	private JPanel panelButton = null;
	
	private JLabel UserName = null;                       // ְ������
	private JTextField NameIn = null;                     // ��������������
	private JLabel UserId = null;                         // ְ���û���
	private JTextField IdIn = null;                       // �û�������������
	private JLabel UserPwd = null;                        // ְ������
	private JTextField PwdIn = null;                      // ��������������
	private JLabel UserRole = null;                       // ְ��Ȩ��
	private JTextField RoleIn = null;                     // Ȩ������������
	
	private JButton buttonSubmit = null;                 // �ύ��ť
	private JButton buttonReset = null;                  // ��հ�ť
	
	// clear�����ǵ�����ð�ť֮����еĲ��裬����д����Ϣ���
	private void clear() {
		this.NameIn.setText("");
		this.IdIn.setText("");
		this.PwdIn.setText("");
		this.RoleIn.setText("");
	}
	
	private void init() {
		
		this.panelBody1.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.anchor = GridBagConstraints.EAST;
		
		this.UserName = new JLabel("ְ��������");
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.panelBody1.add(this.UserName, constraints);
		this.NameIn = new JTextField(40);
		constraints.gridx = 1;
		constraints.gridy = 0;
		this.panelBody1.add(this.NameIn, constraints);
		
		this.UserId = new JLabel("ְ���˺ţ�");
		constraints.gridx = 0;
		constraints.gridy = 1;
		this.panelBody1.add(this.UserId, constraints);
		this.IdIn = new JTextField(40);
		constraints.gridx = 1;
		constraints.gridy = 1;
		this.panelBody1.add(this.IdIn, constraints);
		
		this.UserPwd = new JLabel("ְ�����룺");
		constraints.gridx = 0;
		constraints.gridy = 2;
		this.panelBody1.add(this.UserPwd, constraints);
		this.PwdIn = new JTextField(40);
		constraints.gridx = 1;
		constraints.gridy = 2;
		this.panelBody1.add(this.PwdIn, constraints);
		
		this.UserRole = new JLabel("ְ��Ȩ�ޣ�");
		constraints.gridx = 0;
		constraints.gridy = 3;
		this.panelBody1.add(this.UserRole, constraints);
		this.RoleIn = new JTextField(40);
		constraints.gridx = 1;
		constraints.gridy = 3;
		this.panelBody1.add(this.RoleIn, constraints);
		
		
		this.panelButton = new JPanel();
		this.buttonSubmit = new JButton("����");
		this.buttonSubmit.addActionListener(this);
		this.buttonReset = new JButton("����");
		this.buttonReset.addActionListener(this);
		this.panelButton.setOpaque(false);
		this.panelButton.add(this.buttonSubmit);
		this.panelButton.add(this.buttonReset);
		constraints.gridx = 1;
		constraints.gridy = 4;
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
			
			String name = this.NameIn.getText();                       // ��ȡ���������
			String id = this.IdIn.getText();                           // ��ȡ������˺�
			String pwd = this.PwdIn.getText();                         // ��ȡ���������
			String role = this.RoleIn.getText();                       // ��ȡ�����Ȩ��
			
			if(name == null || (name.trim()).length() <= 0 || id == null || (id.trim()).length() <= 0 || pwd == null || (pwd.trim()).length() <= 0 || role == null || (role.trim()).length() <= 0) {            //�п�
				JOptionPane.showMessageDialog(null, "ÿ������򶼲���Ϊ�գ�");
				return;
		}
			MisUser MisUser = new MisUser();                           // ʵ����MisUser�������set���ö������Է���
			
			MisUser.setUserName(this.NameIn.getText());
			MisUser.setUserId(this.IdIn.getText());
			MisUser.setUserPass(this.PwdIn.getText());
			MisUser.setRoleId(this.RoleIn.getText());
			
			MisUserDAO dao = new MisUserDAOImpl();                     // ʵ����MisUserDAO�ӿڣ��������insertMisUser��������
			
			if(dao.insertMisUser(MisUser)) {
				JOptionPane.showMessageDialog(null, "����ְ���ɹ�");
				this.clear();
			}
	}

}
	
}