package com.sm.framework.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sm.framework.model.entity.className;
import com.sm.framework.model.util.DBUtil;

public class classNameDAOImpl implements classNameDAO {

	@Override
	public boolean addClass(className classObj) {
		boolean flag = false;
		
		// 用MisUser实体类中的get方法获取传进来的MisUser实例的每个数据值
		String studentclass = classObj.getStudent_class();
		
		// 编写SQL语句新增数据
		String sql = "insert into className(student_class) values('" + studentclass + "')";
		
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
	public boolean removeClass(String studentclass) {
		boolean flag = false;
		
		// 根据传进来的字符串班级名编写SQL语句删除
		String sql = "delete from className where student_class = '" + studentclass + "'";
		
		// 实例化DBUtil，使用里面的更新方法
		DBUtil dbUtil = new DBUtil();
		int n = dbUtil.update(sql);
		dbUtil.close();
		if(n > 0) {
			flag = true;
		}
		return flag;
	}

	// findByLike方法传入一个orgType对象的实例根据哪个属性有值根据哪个属性进行查找
		@Override
		public List<className> findByLike(className className) {
			List<className> list = new ArrayList<className>();
			String student_class = className.getStudent_class();
			
			String select = "select * from className";
			String where = " where 1=1";
			if(student_class != null && student_class.length() > 0) {
				where = where + " and orgTypeId like '%" + student_class + "%'";
			}

			String sql = select + where;
			DBUtil dbUtil = new DBUtil();
			ResultSet rs = dbUtil.query(sql);
			try {
				while(rs.next()) {
					className temp = new className();
					temp.setStudent_class(rs.getString("student_class"));
					list.add(temp);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				dbUtil.close();
			}
			return list;
		}

}
