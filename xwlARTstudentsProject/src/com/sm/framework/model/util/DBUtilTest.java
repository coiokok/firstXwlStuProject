package com.sm.framework.model.util;

// ����Ŀ��model���DButil��Ĳ�����test���������DButil��������з������в��Բ���

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtilTest {

	public static void main(String[] args) {
		
		// ʵ����util��
		DBUtil dbUtil = new DBUtil();
		
		
		// ����DBUtil��ĸ��·�����ɵĲ�����
		// 1.����
//		String sql = "insert into students(student_id, student_name, student_yuan, student_class ,student_room, student_sex, student_citysheng, student_cityshi, student_phone, student_home)"
//				+ " values('2505170113', '��ɺ', '��Ϣ����ѧԺ', '�ſ�1701', '13-333', 'Ů', '����ʡ', '������', '15877393631', 'my heart')";
//		dbUtil.update(sql);
//		dbUtil.close();      // ���ùر����ݿⷽ��
		
		
		// 2.ɾ��
//		String sql = "delete from students where student_id = '2505170113'";
//		dbUtil.update(sql);
//		dbUtil.close();      // ���ùر����ݿⷽ��
		
		
		// 3.�޸�
//		String sql = "update students set student_sex = '��' where student_name = '���ں�'";
//		dbUtil.update(sql);
//		dbUtil.close();      // ���ùر����ݿⷽ��
		
		
		
		// ����DBUtil��Ĳ�ѯ������ɲ�����
		String sql = "select * from students";
		ResultSet rs = dbUtil.query(sql);
		try {
			while(rs.next()) {
				for(int i = 0 ; i < 10 ; i++) {
					System.out.print(rs.getString(i+1));
					System.out.print("  ");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtil.close();      // ���ùر����ݿⷽ��
		}
	}

}
