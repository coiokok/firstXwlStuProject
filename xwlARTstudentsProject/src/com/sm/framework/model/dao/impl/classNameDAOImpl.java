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
		
		// ��MisUserʵ�����е�get������ȡ��������MisUserʵ����ÿ������ֵ
		String studentclass = classObj.getStudent_class();
		
		// ��дSQL�����������
		String sql = "insert into className(student_class) values('" + studentclass + "')";
		
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
	public boolean removeClass(String studentclass) {
		boolean flag = false;
		
		// ���ݴ��������ַ����༶����дSQL���ɾ��
		String sql = "delete from className where student_class = '" + studentclass + "'";
		
		// ʵ����DBUtil��ʹ������ĸ��·���
		DBUtil dbUtil = new DBUtil();
		int n = dbUtil.update(sql);
		dbUtil.close();
		if(n > 0) {
			flag = true;
		}
		return flag;
	}

	// findByLike��������һ��orgType�����ʵ�������ĸ�������ֵ�����ĸ����Խ��в���
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
