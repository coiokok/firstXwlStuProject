package com.sm.framework.model.dao.impl;

// ����Ŀ��ȡMVC�ֲ��д��������model���MisUser��Ȩ�޹���dao�Ľӿڣ�������������Ŀ�л��õ������и���Ȩ�޹���������صĲ������ݿ�ķ���
// ����Ŀ�� 1.add���ӷ���    2.removeɾ������    3.modify�޸ķ���   4.findById��ID���ҵķ���    5.findByLike���ܲ��ҵķ���

import java.util.List;

import com.sm.framework.model.entity.MisUser;

public interface MisUserDAO {		// dao�ӿڵ������ǣ��������ı���+DAO
	// ������ʵ�ֵ���ϸ�߼�˼·֮����Implʵ�����в���
	
	public boolean insertMisUser(MisUser misUser);                   // insertMisUser��������һ��MisUser�����ʵ����������

	public boolean deleteMisUser(String userId);                     // deleteMisUser��������һ���ַ��͹���Աid����ɾ��
 
	public boolean updateMisUser(MisUser misUser);                   // updateMisUser��������һ��MisUser�����ʵ��������id�����޸�

	public List<MisUser> queryMisUser(MisUser misUser);              // queryMisUser��������һ��MisUser�����ʵ�������ĸ�������ֵ�����ĸ����Խ��в���

	public MisUser queryMisUser(String userId);                      // queryMisUser��������һ���ַ��͹���Աid���в��ҷ��ز��ҵ�����ʵ��
	
}
