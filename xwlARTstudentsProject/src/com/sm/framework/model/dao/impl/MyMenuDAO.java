package com.sm.framework.model.dao.impl;

// 该项目采取MVC分层编写，本类是model层的MyMenu动态菜单的dao的接口，里面声明该项目中会用到的方法

import java.util.List;

import com.sm.framework.model.entity.MyMenu;

public interface MyMenuDAO {		// dao接口的命名是：所操作的表名+DAO
	
	public List<MyMenu> findBySql(String sql);           // 这是findBySql方法，在主页面中用来生成跟数据库连接的动态菜单，在主页面中会调用时给定一个sql查询语句
	
	public MyMenu findByMenuId(String menuId);           // findByMenuId方法传入menuId返回查到的MisFunction的集合
}
