package com.sm.framework.model.dao.impl;

// ����Ŀ��ȡMVC�ֲ��д��������model���dao�����࣬���Խӿ��еķ����Ƿ�ʵ��

import java.util.List;

import com.sm.framework.model.entity.MisUser;

public class MisUserDAOImplTest {

	public static void main(String[] args) {
		
		// ����insertMisUser��ӷ���
//		MisUser misuser = new MisUser("111", "111", "111", "111", "111", "111");
//		if(new MisUserDAOImpl().insertMisUser(misuser)) {
//			System.out.println("��ӳɹ���");
//		}
		
		
		
		// ����deleteMisUser����idɾ������
		if(new MisUserDAOImpl().deleteMisUser("111")) {
			System.out.println("ɾ���ɹ���");
		}
		
		
		
		// ����updateMisUser����id�޸��������ݵķ���
//		MisUser misuser = new MisUser("admin", "admin", "admin", "admin", "admin", "admin1");
//		if(new MisUserDAOImpl().updateMisUser(misuser)) {
//			System.out.println("�޸ĳɹ���");
//		}
		
	
		
		// ����queryMisUser����id��ѯ�������ݵķ���
//		MisUser misuser = new MisUserDAOImpl().queryMisUser("admin");
//		System.out.println(misuser.toString());

		
		
		// ����queryMisUser���ܲ�ѯ�ķ���,�÷������Ը��ݶ��ģ������Ϣ��������
		MisUser misuser = new MisUser();
		misuser.setUserName("yh");
		
		MisUserDAO dao = new MisUserDAOImpl();            // ʵ����MisUserDAO�ӿڣ�ʹ�������queryMisUser����
		List<MisUser> list = dao.queryMisUser(misuser);
		
		for (MisUser temp : list) {                // (MisUser temp : list) �Ǳ������list��MisUser���ʵ��������temp��ֵ
			System.out.println(temp.toString());
		}
	}

}
