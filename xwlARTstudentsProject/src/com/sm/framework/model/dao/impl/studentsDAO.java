package com.sm.framework.model.dao.impl;

// ����Ŀ��ȡMVC�ֲ��д��������model���students��ѧ����Ϣ����dao�Ľӿڣ�������������Ŀ�л��õ������и�ѧ����Ϣ����������صĲ������ݿ�ķ���
// ����Ŀ�� 1.add���ӷ���    2.removeɾ������    3.modify�޸ķ���   4.findById��ID���ҵķ���    5.findByLike���ܲ��ҵķ���

import java.util.List;

import com.sm.framework.model.entity.students;

public interface studentsDAO {     // dao�ӿڵ������ǣ��������ı���+DAO
	// ������ʵ�ֵ���ϸ�߼�˼·֮����Implʵ�����в���
	
	public boolean add(students students);                   // add��������һ��students�����ʵ����������
	
	public boolean remove(String studentId);                 // remove��������һ���ַ���ѧ��id����ɾ��
	 
	public boolean modify(students students);                // modify��������һ��students�����ʵ��������id�����޸�
	
	public students findById(String studentId);              // findById��������һ���ַ���ѧ��id���в���
	
	public List<students> findByLike(students students);     // findByLike��������һ��students�����ʵ�������ĸ�������ֵ�����ĸ����Խ��в���

}
