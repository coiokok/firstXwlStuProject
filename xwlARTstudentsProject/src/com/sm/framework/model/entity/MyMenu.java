package com.sm.framework.model.entity;

// 该项目采取MVC分层编写，本类是model层的entity的MyMenu动态菜单的实体类，用来对应myMenu数据库的类
// 之后会用到其中的get and set方法

public class MyMenu {
	private String menuId = null;
	private String menuName = null;
	private String functionClass = null;
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getfunctionClass() {
		return functionClass;
	}
	public void setfunctionClass(String functionClass) {
		this.functionClass = functionClass;
	}
}
