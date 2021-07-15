package com.sm.framework.model.dao.impl;

// 该项目采取MVC分层编写，本类是model层的dao的接口的实现类DAOImpl，实现dao接口中的方法

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sm.framework.model.entity.MyMenu;
import com.sm.framework.model.util.DBUtil;

public class MyMenuDAOImpl implements MyMenuDAO {

	@Override
	public List<MyMenu> findBySql(String sql) {            // 返回的是一个存有MyMenu对象的集合
		List<MyMenu> list = new ArrayList<MyMenu>();       // 实例化存MyMenu对象的集合
		DBUtil dbUtil = new DBUtil();                      // 实例化DBUtil类使用其中的查询方法
		ResultSet rs = dbUtil.query(sql);                  // 实例化rs用DBUtil的查询方法
		
		try {
			while(rs.next()) {
				MyMenu temp = new MyMenu();               
				temp.setMenuId(rs.getString(1));
				temp.setMenuName(rs.getString(2));
				list.add(temp);                            // 将查到的对象依次放入集合中
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtil.close();
		}
		return list;
	}

	// findByMenuId方法，根据点击的功能的menuId进行查找细分操作
		@Override
		public MyMenu findByMenuId(String menuId) {
			MyMenu tempMenu = null;
			String sql = "select * from myMenu where menuId = '" + menuId + "'";        // 查询语句：select * from 表名 where 数据名 = '" + java中的变量名 + "'
			DBUtil dbUtil = new DBUtil();                                               // 实例化DBUtil进行操作
			ResultSet rs = dbUtil.query(sql);                                           // 调用DBUtil其中的查询query方法，将sql语句传入
			try {
				while(rs.next()) {
					// 查到之后实例化存放的MisFunction对象然后将查到的数据set到tempFunction中，然后添加至list中
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
