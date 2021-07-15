package com.sm.framework.model.dao.impl;

// ����Ŀ��ȡMVC�ֲ��д��������model���dao�Ľӿڵ�ʵ����DAOImpl��ʵ��dao�ӿ��еķ���

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sm.framework.model.entity.MisUser;
import com.sm.framework.model.util.DBUtil;

public class MisUserDAOImpl implements MisUserDAO {
	
	@Override
	// insertMisUser���ӷ�����ʵ��
	public boolean insertMisUser(MisUser misUser) {
		
		boolean flag = false;
		
		// ��MisUserʵ�����е�get������ȡ��������MisUserʵ����ÿ������ֵ
		String userId = misUser.getUserId();
		String userName = misUser.getUserName();
		String userPass = misUser.getUserPass();
		String roleId = misUser.getRoleId();
		
		// ��дSQL�����������
		String sql = "insert into misUser(userId, userName, userPass, roleId)"
				+ "values('" + userId + "', '" + userName + "', '" + userPass + "', '" + roleId + "')";
		
		// ʵ����DBUtil��ʹ������ĸ��·���
		DBUtil dbUtil = new DBUtil();
		int n = dbUtil.update(sql);
		dbUtil.close();
		if(n > 0) {
			flag = true;
		}
		return flag;
	}
	
	@Override
	// deleteMisUser����id����ɾ���ķ�����ʵ��
	public boolean deleteMisUser(String userId) {
		boolean flag = false;
		
		// ���ݴ��������ַ���id��дSQL���ɾ��
		String sql = "delete from misUser where userId = '" + userId + "'";
		
		// ʵ����DBUtil��ʹ������ĸ��·���
		DBUtil dbUtil = new DBUtil();
		int n = dbUtil.update(sql);
		dbUtil.close();
		if(n > 0) {
			flag = true;
		}
		return flag;
	}
	
	@Override
	// updateMisUser����id�����޸��������ݵķ�����ʵ��
	public boolean updateMisUser(MisUser misUser) {
		
		boolean flag = false;
		
		// ��MisUserʵ�����е�get������ȡ��������MisUserʵ����ÿ������ֵ
		String userId = misUser.getUserId();
		String userName = misUser.getUserName();
		String userPass = misUser.getUserPass();
		String roleId = misUser.getRoleId();
		
		// ��дSQL������id�����������ݵ��޸�
		String sql = "update misUser set userName = '" + userName + "', userPass = '" + userPass + "', roleId = '" + roleId + "' where userid = '" + userId + "'" ;
		
		// ʵ����DBUtil��ʹ������ĸ��·���
		DBUtil dbUtil = new DBUtil();
		int n = dbUtil.update(sql);
		dbUtil.close();
		if(n > 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	// queryMisUser���ݸ���ģ�����ݽ��в�ѯ�ķ�����ʵ��
	public List<MisUser> queryMisUser(MisUser misUser) {
		// �÷������ص���һ��List���ϣ������Ƚ��з���Ϊstudents���ʵ����
		List<MisUser> list = new ArrayList<MisUser>();
		
		// Ȼ����MisUser���е�get������ȡ������ʱ����Ķ���set����
		String userId = misUser.getUserId();
		String userName = misUser.getUserName();
		String userPass = misUser.getUserPass();
		String roleId = misUser.getRoleId();
		
		// ����ƴ�ӵķ�����дSQL���
		String select = "select * from misUser";      // �ͷ�Ĳ�ѯ���select
		String where = " where 1=1";                   // �����ж�����where����䣬����1=1������������һ�����������䣬����������ζ��������֮���������䶼��and��ͷ�����Ͳ�����ֵ�һ�����ݲ��Ҿ���and��ͷ�����
		// �������Ǹ��ݸ������Ƿ���ڵ������ƴ�Ӳ�ѯ����
		if(userId != null && userId.length() > 0) {
			where = where + " and userId like '%" + userId + "%'";
		}
		if(userName != null && userName.length() > 0) {
			where = where + " and userName like '%" + userName + "%'";
		}
		if(userPass != null && userPass.length() > 0) {
			where = where + " and userPass like '%" + userPass + "%'";
		}
		if(roleId != null && roleId.length() > 0) {
			where = where + " and roleId like '%" + roleId + "%'";
		}
		String sql = select + where;
		
		// ʵ����DBUtil��ʹ������Ĳ�ѯ����
		DBUtil dbUtil = new DBUtil();
		ResultSet rs = dbUtil.query(sql);
		try {
			while(rs.next()) {
				// Ȼ��ʵ����MisUser����סһ��Ҫ��rs��ʵ����MisUser��Ȼ����������ݶ���һ������set�������������rs�и����ݵ�ֵset����ʵ������temp
				MisUser temp = new MisUser();
				temp.setUserId(rs.getString("userId"));
				temp.setUserName(rs.getString("userName"));
				temp.setUserPass(rs.getString("userPass"));
				temp.setRoleId(rs.getString("roleId"));
				list.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtil.close();
		}
		// ���ﴫ������һ���ӿڣ����÷�����ʱ�����temp���
		return list;
	}
	
	
	
	@Override
	// queryMisUser���ݴ�������id���в��ң������ҵ�����ʵ��
	public MisUser queryMisUser(String userId) {
		MisUser misUser = null;
		DBUtil dbUtil = new DBUtil();
		String sql = "SELECT * FROM misUser WHERE userId = '" + userId + "'";
		ResultSet rs = dbUtil.query(sql);
		try {
			while (rs.next()) {
				misUser = new MisUser();
				misUser.setUserId(rs.getString(1));
				misUser.setUserName(rs.getString(2));
				misUser.setUserPass(rs.getString(3));
				misUser.setRoleId(rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return misUser;
	}
	
}
