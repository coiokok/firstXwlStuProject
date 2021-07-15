package com.sm.framework.control.action.org;

// ����ʵ��Action�е�execute������ModifyUserActionʵ���࣬��������ֺ͵�ַ��Ҫ�����ݿ�misFunction�е�FunctionClass�ж�Ӧ���ܴ������һ��
// ���ʵ�����Ӧ���ǵ��������ְ����������ť����ʾҳ�湦��

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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.sm.framework.model.dao.impl.MisUserDAO;
import com.sm.framework.model.dao.impl.MisUserDAOImpl;
import com.sm.framework.model.entity.MisUser;

public class ModifyUserAction implements Action, ActionListener {
	
	private JPanel panelWork = null;                   // ���ǹ����в������е�panelWork��Action�ӿ�ʵ�ֵ�execute������������panelWork
	
	private JPanel panelButton = null;                 // ��ť��
	
	private JTable table = null;                       // ����
	private DefaultTableModel tablemodel = null;       // DefaultTableModelʵ����tablemodel�����ܷ�������
	
	private JScrollPane scrollPane = null;             // ʵ����JScrollPane������ʾ��ͷ
	private String[] title = {"ְ������", "ְ���˺�", "ְ������", "ְ��Ȩ��"};               // ��ͷ����
	private String[][] data = null;                                                // �����������
	private JButton buttonModify = null;                                           // �޸İ�ť
	private JButton buttonRefresh = null;                                          // ˢ�°�ť
	
	
	// ˢ�·���
	private void refresh() {
		this.table.removeAll();              // ��ձ������
		this.table.repaint();
		
		this.tablemodel = new DefaultTableModel(this.getData(new MisUser()), this.title);        // ����ͷ������ʵ������tablemodel������getData������Ϊ�����OrgTypeû��ֵ���������ܲ��ҵķ�������ȫ�����ݶ��鵽
		this.table.setModel(this.tablemodel);                 // �����setModel�������øյ�tablemodel
	}
	
	
	// getData����������orgType���ش�����ݵ�����
	private String[][] getData(MisUser MisUser) {
		String[][] data = null;
		MisUserDAO dao = new MisUserDAOImpl();
		
		List<MisUser> list = dao.queryMisUser(MisUser);         // ���õ������ܲ��ҵķ���
		
		data = new String[list.size()][4];                    // ��������ݵ����鶨�峤�ȣ����ҵ������ݼ��ϳ��Ⱥ͹̶�����������4
		
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
		
//		this.panelWork = new JPanel(new BorderLayout());                // ʵ����panelModfy������BorderLayout
		
		this.data = this.getData(new MisUser());                         // ����getData��������OrgType()ʵ��ȫ��
		
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
			String UserId = this.tablemodel.getValueAt(this.table.getSelectedRow(), 1).toString();          // ��������˼�ǻ�õ���еĵڶ�������ǿҲ����idתΪ�ַ���
			
			ModifyDialog modifyDialog = new ModifyDialog(UserId, this);                  // Ȼ�����ʵ����ModifyDialog���ù��캯������orgTypeId
			
			modifyDialog.setBounds(510, 200, 900, 650);
			modifyDialog.setVisible(true);
		}
	}
	
	
	// ��дһ���ڲ�����ʾ��ϸ���޸�ҳ����н�һ���޸�
	public class ModifyDialog extends JDialog implements ActionListener {
		
		private MisUser MisUser = null;
		private ModifyUserAction modifyAction = null;         // ʵ�������ص��޸Ĳ�����ҳ��
		private JPanel panelBody = null; 
				
		private JLabel UserName = null;                       // ְ������
		private JTextField NameIn = null;                     // ��������������
		private JLabel UserId = null;                         // ְ���û���
		private JTextField IdIn = null;                       // �û�������������
		private JLabel UserPwd = null;                        // ְ������
		private JTextField PwdIn = null;                      // ��������������
		private JLabel UserRole = null;                       // ְ��Ȩ��
		private JTextField RoleIn = null;                     // Ȩ������������
		
		private JPanel buttonJPanle = null;
		private JButton saveOrgType = null;
		private JButton backOrgType = null;
		
		
		// ��д���췽��
		public ModifyDialog() {
			// TODO Auto-generated constructor stub
		}
		
		// ���췽������ղŵ���е�orgTypeId���е�ǰ��modifyAction�޸Ĳ���ҳ��
		public ModifyDialog(String UserId, ModifyUserAction modifyAction) {
			this.MisUser = new MisUserDAOImpl().queryMisUser(UserId);        // ���ýӿڵ�findById����ͨ�������id���ҵ���ǰ���в�����MisUser����
			this.modifyAction = modifyAction;
	
			this.init();
		}
		
		
		// ��ϸ�����޸Ĳ����Ĵ���
		private void init() {
			this.panelBody = (JPanel)this.getContentPane();
			
			this.panelBody.setLayout(new GridBagLayout());               // set��׼���ֹ�����
			GridBagConstraints constraints = new GridBagConstraints();
			
			
			this.UserName = new JLabel("ְ��������");
			constraints.gridx = 0;
			constraints.gridy = 0;
			this.panelBody.add(this.UserName, constraints);
			this.NameIn = new JTextField(40);
			constraints.gridx = 1;
			constraints.gridy = 0;
			this.NameIn.setText(this.MisUser.getUserName());
			this.panelBody.add(this.NameIn, constraints);
			
			this.UserId = new JLabel("ְ���˺ţ�");
			constraints.gridx = 0;
			constraints.gridy = 1;
			this.panelBody.add(this.UserId, constraints);
			this.IdIn = new JTextField(40);
			constraints.gridx = 1;
			constraints.gridy = 1;
			this.IdIn.setText(this.MisUser.getUserId());
			this.panelBody.add(this.IdIn, constraints);
			
			this.UserPwd = new JLabel("ְ�����룺");
			constraints.gridx = 0;
			constraints.gridy = 2;
			this.panelBody.add(this.UserPwd, constraints);
			this.PwdIn = new JTextField(40);
			constraints.gridx = 1;
			constraints.gridy = 2;
			this.PwdIn.setText(this.MisUser.getUserPass());
			this.panelBody.add(this.PwdIn, constraints);
			
			this.UserRole = new JLabel("ְ��Ȩ�ޣ�");
			constraints.gridx = 0;
			constraints.gridy = 3;
			this.panelBody.add(this.UserRole, constraints);
			this.RoleIn = new JTextField(40);
			constraints.gridx = 1;
			constraints.gridy = 3;
			this.RoleIn.setText(this.MisUser.getRoleId());
			this.panelBody.add(this.RoleIn, constraints);
			
						
			// �ײ���ťpanel����
			this.buttonJPanle = new JPanel();
			this.saveOrgType = new JButton("����");
			this.saveOrgType.addActionListener(this);
			this.backOrgType = new JButton("��ԭ");
			this.backOrgType.addActionListener(this);
			this.buttonJPanle.add(this.saveOrgType);
			this.buttonJPanle.add(this.backOrgType);
			constraints.gridx = 1;
			constraints.gridy = 4;
			this.panelBody.add(this.buttonJPanle, constraints);
			
			this.setTitle("�޸�ְ����Ϣ��ϸҳ��");
		}
		
		
		// �¼���������
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == this.saveOrgType) {           // ��������Ǳ��水ťʱ
				
				
				MisUser user = new MisUser();
				user.setUserName(this.NameIn.getText());
				user.setUserId(this.IdIn.getText());
				user.setUserPass(this.PwdIn.getText());
				user.setRoleId(this.RoleIn.getText());
				
				
				MisUserDAO dao = new MisUserDAOImpl();        
				if(dao.updateMisUser(user)) {
					JOptionPane.showMessageDialog(this, "�޸ĳɹ�");
					
					this.modifyAction.refresh();              // �ٵ����޸Ĳ���ҳ���ˢ�·���
					
					this.dispose();                         // �ر���ҳ�浫����Ӱ�������ɵĵ�¼ҳ��
				}
			} 
			else if(e.getSource() == this.backOrgType) {      // ��������ǻ�ԭ��ťʱ
				
				this.NameIn.setText(this.MisUser.getUserName());
				this.IdIn.setText(this.MisUser.getUserId());
				this.PwdIn.setText(this.MisUser.getUserPass());
				this.RoleIn.setText(this.MisUser.getRoleId());
							
				
				this.saveOrgType.doClick();                   // �������ݻ�ԭ֮���ٵ��һ�±��水ťʵ�ֻ�ԭ
			}
		}
	}

}
