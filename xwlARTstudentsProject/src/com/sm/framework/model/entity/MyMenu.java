package com.sm.framework.model.entity;

// ����Ŀ��ȡMVC�ֲ��д��������model���entity��MyMenu��̬�˵���ʵ���࣬������ӦmyMenu���ݿ����
// ֮����õ����е�get and set����

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
