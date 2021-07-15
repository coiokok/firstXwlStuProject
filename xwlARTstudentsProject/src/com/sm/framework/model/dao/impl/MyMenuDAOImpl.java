package com.sm.framework.model.dao.impl;

// ����Ŀ��ȡMVC�ֲ��д��������model���dao�Ľӿڵ�ʵ����DAOImpl��ʵ��dao�ӿ��еķ���

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sm.framework.model.entity.MyMenu;
import com.sm.framework.model.util.DBUtil;

public class MyMenuDAOImpl implements MyMenuDAO {

	@Override
	public List<MyMenu> findBySql(String sql) {            // ���ص���һ������MyMenu����ļ���
		List<MyMenu> list = new ArrayList<MyMenu>();       // ʵ������MyMenu����ļ���
		DBUtil dbUtil = new DBUtil();                      // ʵ����DBUtil��ʹ�����еĲ�ѯ����
		ResultSet rs = dbUtil.query(sql);                  // ʵ����rs��DBUtil�Ĳ�ѯ����
		
		try {
			while(rs.next()) {
				MyMenu temp = new MyMenu();               
				temp.setMenuId(rs.getString(1));
				temp.setMenuName(rs.getString(2));
				list.add(temp);                            // ���鵽�Ķ������η��뼯����
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtil.close();
		}
		return list;
	}

	// findByMenuId���������ݵ���Ĺ��ܵ�menuId���в���ϸ�ֲ���
		@Override
		public MyMenu findByMenuId(String menuId) {
			MyMenu tempMenu = null;
			String sql = "select * from myMenu where menuId = '" + menuId + "'";        // ��ѯ��䣺select * from ���� where ������ = '" + java�еı����� + "'
			DBUtil dbUtil = new DBUtil();                                               // ʵ����DBUtil���в���
			ResultSet rs = dbUtil.query(sql);                                           // ����DBUtil���еĲ�ѯquery��������sql��䴫��
			try {
				while(rs.next()) {
					// �鵽֮��ʵ������ŵ�MisFunction����Ȼ�󽫲鵽������set��tempFunction�У�Ȼ�������list��
					tempMenu = new MyMenu();
					tempMenu.setMenuId(rs.getString(1));
					tempMenu.setMenuName(rs.getString(2));
					tempMenu.setfunctionClass(rs.getString(3));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				dbUtil.close();
			}
			return tempMenu;
		}
		
}
