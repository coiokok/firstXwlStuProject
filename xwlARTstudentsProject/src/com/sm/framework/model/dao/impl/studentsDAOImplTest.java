package com.sm.framework.model.dao.impl;

// ����Ŀ��ȡMVC�ֲ��д��������model���dao�����࣬���Խӿ��еķ����Ƿ�ʵ��

import java.util.List;

import com.sm.framework.model.entity.students;

public class studentsDAOImplTest {

	public static void main(String[] args) {
		
		// ����add��ӷ���
//		students student = new students("2507180201", "��δ��", "��Ϣ����ѧԺ", "��1802", "2-159", "��", "�㶫ʡ", "������", "18791987706", "��������õ������˾");
//		if(new studentsDAOImpl().add(student)) {
//			System.out.println("��ӳɹ���");
//		}
		
		
		
		// ����remove����idɾ������
//		if(new studentsDAOImpl().remove("2507180206")) {
//			System.out.println("ɾ���ɹ���");
//		}
		
		
		
		// ����modify����id�޸��������ݵķ���
//		students student = new students("2507180201", "����δ��", "��Ϣ����ѧԺ", "��1802", "2-159", "��", "�㶫ʡ", "������", "18791987706", "��������õ������˾");
//		if(new studentsDAOImpl().modify(student)) {
//			System.out.println("�޸ĳɹ���");
//		}
		
	
		
		// ����findById����id��ѯ�������ݵķ���
//		students student = new studentsDAOImpl().findById("2507180201");
//		System.out.println(student.toString());

		
		
		// ����findByLike���ܲ�ѯ�ķ���,�÷������Ը��ݶ��ģ������Ϣ��������
		students student = new students();
		student.setStudent_id("113");
		
		studentsDAO dao = new studentsDAOImpl();            // ʵ����studentDAO�ӿڣ�ʹ�������findByLike����
		List<students> list = dao.findByLike(student);
		
		for (students temp : list) {                // (students temp : list) �Ǳ������list��students���ʵ��������temp��ֵ
			System.out.println(temp.toString());
		}
	}

}
