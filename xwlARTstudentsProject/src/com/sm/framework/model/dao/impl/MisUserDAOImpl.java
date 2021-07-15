package com.sm.framework.model.dao.impl;

// 该项目采取MVC分层编写，本类是model层的dao的接口的实现类DAOImpl，实现dao接口中的方法

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sm.framework.model.entity.MisUser;
import com.sm.framework.model.util.DBUtil;

public class MisUserDAOImpl implements MisUserDAO {
	
	@Override
	// insertMisUser增加方法的实现
	public boolean insertMisUser(MisUser misUser) {
		
		boolean flag = false;
		
		// 用MisUser实体类中的get方法获取传进来的MisUser实例的每个数据值
		String userId = misUser.getUserId();
		String userName = misUser.getUserName();
		String userPass = misUser.getUserPass();
		String roleId = misUser.getRoleId();
		
		// 编写SQL语句新增数据
		String sql = "insert into misUser(userId, userName, userPass, roleId)"
				+ "values('" + userId + "', '" + userName + "', '" + userPass + "', '" + roleId + "')";
		
		// 实例化DBUtil，使用里面的更新方法
		DBUtil dbUtil = new DBUtil();
		int n = dbUtil.update(sql);
		dbUtil.close();
		if(n > 0) {
			flag = true;
		}
		return flag;
	}
	
	@Override
	// deleteMisUser根据id进行删除的方法的实现
	public boolean deleteMisUser(String userId) {
		boolean flag = false;
		
		// 根据传进来的字符串id编写SQL语句删除
		String sql = "delete from misUser where userId = '" + userId + "'";
		
		// 实例化DBUtil，使用里面的更新方法
		DBUtil dbUtil = new DBUtil();
		int n = dbUtil.update(sql);
		dbUtil.close();
		if(n > 0) {
			flag = true;
		}
		return flag;
	}
	
	@Override
	// updateMisUser根据id进行修改其他数据的方法的实现
	public boolean updateMisUser(MisUser misUser) {
		
		boolean flag = false;
		
		// 用MisUser实体类中的get方法获取传进来的MisUser实例的每个数据值
		String userId = misUser.getUserId();
		String userName = misUser.getUserName();
		String userPass = misUser.getUserPass();
		String roleId = misUser.getRoleId();
		
		// 编写SQL语句根据id进行其他数据的修改
		String sql = "update misUser set userName = '" + userName + "', userPass = '" + userPass + "', roleId = '" + roleId + "' where userid = '" + userId + "'" ;
		
		// 实例化DBUtil，使用里面的更新方法
		DBUtil dbUtil = new DBUtil();
		int n = dbUtil.update(sql);
		dbUtil.close();
		if(n > 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	// queryMisUser根据各种模糊数据进行查询的方法的实现
	public List<MisUser> queryMisUser(MisUser misUser) {
		// 该方法返回的是一个List集合，所以先进行范型为students类的实例化
		List<MisUser> list = new ArrayList<MisUser>();
		
		// 然后用MisUser类中的get方法获取各调用时输入的对象set数据
		String userId = misUser.getUserId();
		String userName = misUser.getUserName();
		String userPass = misUser.getUserPass();
		String roleId = misUser.getRoleId();
		
		// 运用拼接的方法编写SQL语句
		String select = "select * from misUser";      // 最开头的查询语句select
		String where = " where 1=1";                   // 这是判断条件where的语句，这里1=1的作用是这是一个恒成立的语句，所以无论如何都会成立，之后的所有语句都用and开头这样就不会出现第一个数据查找就以and开头的情况
		// 接下来是根据各数据是否存在的情况来拼接查询条件
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
		
		// 实例化DBUtil，使用里面的查询方法
		DBUtil dbUtil = new DBUtil();
		ResultSet rs = dbUtil.query(sql);
		try {
			while(rs.next()) {
				// 然后实例化MisUser，记住一定要在rs中实例化MisUser不然查出来的数据都会一样，用set方法将查出来的rs中各数据的值set给刚实例化的temp
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
		// 这里传出的是一个接口，调用方法的时候遍历temp输出
		return list;
	}
	
	
	
	@Override
	// queryMisUser根据传进来的id进行查找，返回找到的类实例
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
