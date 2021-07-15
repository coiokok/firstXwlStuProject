package com.sm.framework.model.dao.impl;

// ����Ŀ��ȡMVC�ֲ��д��������model���MyMenu��̬�˵���dao�Ľӿڣ�������������Ŀ�л��õ��ķ���

import java.util.List;

import com.sm.framework.model.entity.MyMenu;

public interface MyMenuDAO {		// dao�ӿڵ������ǣ��������ı���+DAO
	
	public List<MyMenu> findBySql(String sql);           // ����findBySql����������ҳ�����������ɸ����ݿ����ӵĶ�̬�˵�������ҳ���л����ʱ����һ��sql��ѯ���
	
	public MyMenu findByMenuId(String menuId);           // findByMenuId��������menuId���ز鵽��MisFunction�ļ���
}
