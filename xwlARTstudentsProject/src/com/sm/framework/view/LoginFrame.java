package com.sm.framework.view;

// ���ǹ���Ȩ�޵Ĺ���Ա��¼��ҳ�棬����MisUser���ݿ��д�Ķ�����е�¼

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sm.framework.model.dao.impl.MisUserDAO;
import com.sm.framework.model.dao.impl.MisUserDAOImpl;
import com.sm.framework.model.entity.MisUser;

public class LoginFrame extends JFrame implements ActionListener {     // ���ҳ��̳�JFrameдҳ�棬ʵ��ActionListener�ӿ���ӷ���
	
	private MisUser loginUser = null;                   // ���loginUser�ǻ�ȡ�û�������û����Լ����뿴�Ƿ���Ȩ�����ݿ��д���
	
	private JPanel panelBody = null;                    // �ܵ�bodypanel��
	private JLabel labelTitle = null;                   // ����panel
	private JLabel labelName = null;                    // �û���
	private JTextField fieldName = null;                // ��д�û���������
	private JLabel labelPassword = null;                // ����
	private JPasswordField fieldPassword = null;        // ��д����������
	
	private JPanel panelButton = null;                  // �ײ���ť��panel
	private JButton buttonLogin = null;                 // ��¼��ť
	private JButton buttonReset = null;                 // ���ð�ť
	
	
	// checkUser����������һ��MisUserʵ������������û�������û����Ƿ������ݿ��л����Ƿ���ȷ��֮��ĵ�¼�¼����õ���
	private boolean checkUser(MisUser misUser) {
		boolean flag = false;
		MisUserDAO userDAO = new MisUserDAOImpl();           // ʹ��MisUser�ӿ�
		MisUser tempUser = userDAO.queryMisUser(misUser.getUserId());          // ʹ�������queryMisUser(String Id)�Ĳ��ҷ�ʽ����ȡ֮���û��������Ϣ��ͨ��getUserId������û�����Ȼ���û������뷽���������صĶ����ȡ��tempUser
		if(tempUser != null && tempUser.getUserPass().equals(misUser.getUserPass())) {          // ���tempUser��Ϊ��Ҳ���������ݿ����ҵ��˲��Ҹ���misUser��id�ҵ���tempUser�������misUser������ͬ��Ϊ��½�ɹ�
			flag = true;
			this.loginUser = tempUser;           // ��ǰ��¼��loginUser��Ϊ�ҵ���tempUser
		}
		return flag;
	}
	
	// clear�����ǵ�����ð�ť֮����еĲ��裬����д����Ϣ��գ�֮��������¼����õ���
	private void clear() {
		this.fieldName.setText("");
		this.fieldPassword.setText("");
	}
	
	
	// ����ı�д
	private void init() {
		Container content = this.getContentPane();                       // ʵ����ContentPane
		content.setLayout(new BorderLayout());                           // ��������ΪBorderLayout
		
		this.panelBody = new WelcomePanel();                             // �ñ���panelʵ����JPanel���������屳��
		
		this.panelBody.setLayout(new GridBagLayout());                   // ͨ��setLayout��������GridBagLayout��ȷ���ֹ�����
		this.panelBody.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(24, 108, 224)));    // �������ñ߿���ɫ�Ĵ��룬new Color( , , )��ͨ����Ԫ�ؽ�������
		
		content.add(this.panelBody, BorderLayout.CENTER);                // ��panelbody���content
		
		GridBagConstraints constraints = new GridBagConstraints();       // ʵ����GridBagConstraints��ȷ���ֹ�����constraints����
		
		this.labelTitle = new JLabel("ѧ����Ϣ����ϵͳ��¼");
		this.labelTitle.setForeground(Color.WHITE);                      // setForeground() ����������ɫ�ķ���
		this.labelTitle.setFont(new Font("����", Font.BOLD, 18));        // setFont(new Font("����", Font.��ʽ, �����С))    ����������ʽ�ķ���
		constraints.gridx = 1;
		constraints.gridy = 0;
		this.panelBody.add(this.labelTitle, constraints);
		
		// ����������֮��ķ��붼�ǿ��Ҷ���ģ���Ϊ�����û����������Label�����ı����
		constraints.anchor = GridBagConstraints.EAST;
		
		this.labelName = new JLabel("�� �� ��:");
		this.labelName.setForeground(Color.WHITE);                      // setForeground() ����������ɫ�ķ���
		this.labelName.setFont(new Font("����", Font.BOLD, 16));        // setFont(new Font("����", Font.��ʽ, �����С))    ����������ʽ�ķ���
		constraints.gridx = 0;
		constraints.gridy = 1;
		this.panelBody.add(this.labelName, constraints);
		this.fieldName = new JTextField(16);
		constraints.gridx = 1;
		constraints.gridy = 1;
		this.panelBody.add(this.fieldName, constraints);
		
		this.labelPassword = new JLabel("��    ��:");
		this.labelPassword.setForeground(Color.WHITE);                      // setForeground() ����������ɫ�ķ���
		this.labelPassword.setFont(new Font("����", Font.BOLD, 16));        // setFont(new Font("����", Font.��ʽ, �����С))    ����������ʽ�ķ���
		constraints.gridx = 0;
		constraints.gridy = 2;
		this.panelBody.add(this.labelPassword, constraints);
		this.fieldPassword = new JPasswordField(16);
		constraints.gridx = 1;
		constraints.gridy = 2;
		this.panelBody.add(this.fieldPassword, constraints);
		
		this.panelButton = new JPanel();
		this.panelButton.setOpaque(false);          // ���ð�ť������ɫ͸���ɱ�����ɫ
		this.buttonLogin = new JButton("�� ¼");                   // setForeground() ����������ɫ�ķ���
		this.buttonLogin.setFont(new Font("����", Font.BOLD, 14));        // setFont(new Font("����", Font.��ʽ, �����С))    ����������ʽ�ķ���
		this.buttonLogin.setActionCommand("login");   // setActionCommandΪһ���ַ���֮�����ж��¼�ʱ��getActionCommand���Ǵ������ĸ��¼�
		this.buttonLogin.addActionListener(this);     // ��buttonLogin��ӽ��¼�����
		this.buttonReset = new JButton("�� ��");
		this.buttonReset.setFont(new Font("����", Font.BOLD, 14));        // setFont(new Font("����", Font.��ʽ, �����С))    ����������ʽ�ķ���
		this.buttonReset.addActionListener(this);
		this.buttonReset.setActionCommand("Reset");
		
		// ��������ť��ӽ�panelButtonȻ����ӽ�panelBody
		this.panelButton.add(this.buttonLogin);
		this.panelButton.add(this.buttonReset);
		constraints.gridx = 1;
		constraints.gridy = 3;
		this.panelBody.add(this.panelButton, constraints);
		
		
		this.setTitle("�û���¼");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public LoginFrame() {
		this.init();
	}
	
	public static void main(String[] args) {
		LoginFrame loginFrame = new LoginFrame();
		loginFrame.setBounds(720, 350, 500, 320);
		loginFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("login".equals(e.getActionCommand())) {       // �����ͨ��ActionCommand�ж��¼��� ��ס"����".equals(����)
			String id = this.fieldName.getText();        // ��ȡ�û�������û���
			String password = new String(this.fieldPassword.getPassword());           // ��ȡ�û���������룬������ǿת���ַ���
			
			if(id == null || id.length() <= 0 || password == null || password.length() <= 0) {      // ����û�������������һ��������ǿյľͲ����ٽ���
				JOptionPane.showMessageDialog(this, "�û��������벻��Ϊ�գ�");           
				return;
			}
			
			// �ж�������û����Ƿ�����Լ���ȷ
			MisUser user = new MisUser();      // ʵ����һ��user�Ա�����������û���������
			user.setUserId(id);
			user.setUserPass(password);
			// ���ñ��࿪ͷ������checkUser���������մ�õ�userʵ����������
			if(this.checkUser(user)) {
				JOptionPane.showMessageDialog(this, "��¼�ɹ���");      // ������ص�flagΪtrue�򵯳���ҳ��mainFrame
				MainFrame mainFrame = new MainFrame(this.loginUser);   // ���ҵ�������ҳ��mainFrameʵ����ʱͬʱ����յ�¼��checkUser����ȡ��loginUser
				mainFrame.setBounds(410, 120, 1100, 800);
				mainFrame.setVisible(true);
				this.dispose();           // ʹ��dispose��������ǰ�ĵ�¼ҳ��ر�
			} else {
				JOptionPane.showMessageDialog(this, "�û������������");       // ���checkUser�������ص�flagΪfalseʱ�򵯳��û����������
			}
		} else if("Reset".equals(e.getActionCommand())) {
			this.clear();
		}
	}

}
