package com.sm.framework.model.dao.impl;

import java.util.List;

import com.sm.framework.model.entity.className;

//����Ŀ��ȡMVC�ֲ��д��������model���className�İ༶����dao�Ľӿڣ�������������Ŀ�л��õ������и������༶��صĲ������ݿ�ķ���
//����Ŀ�� 1.addClass���ӷ���    2.removeClassɾ������

public interface classNameDAO {

	public boolean addClass(className classObj);                   // insertMisUser��������һ��MisUser�����ʵ����������

	public boolean removeClass(String studentclass);               // deleteMisUser��������һ���ַ��͹���Աid����ɾ��
	
	public List<className> findByLike(className className);        // findByLike��������һ��className�����ʵ�������ĸ�������ֵ�����ĸ����Խ��в���
	
}
