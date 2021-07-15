package com.sm.framework.model.util;

// 该项目是model层的DButil类的测试类test，这里调用DButil类里的所有方法进行测试操作

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtilTest {

	public static void main(String[] args) {
		
		// 实例化util类
		DBUtil dbUtil = new DBUtil();
		
		
		// 调用DBUtil里的更新方法完成的操作：
		// 1.新增
//		String sql = "insert into students(student_id, student_name, student_yuan, student_class ,student_room, student_sex, student_citysheng, student_cityshi, student_phone, student_home)"
//				+ " values('2505170113', '姜珊', '信息工程学院', '信科1701', '13-333', '女', '吉林省', '集安市', '15877393631', 'my heart')";
//		dbUtil.update(sql);
//		dbUtil.close();      // 调用关闭数据库方法
		
		
		// 2.删除
//		String sql = "delete from students where student_id = '2505170113'";
//		dbUtil.update(sql);
//		dbUtil.close();      // 调用关闭数据库方法
		
		
		// 3.修改
//		String sql = "update students set student_sex = '男' where student_name = '王钰涵'";
//		dbUtil.update(sql);
//		dbUtil.close();      // 调用关闭数据库方法
		
		
		
		// 调用DBUtil里的查询方法完成操作：
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
			dbUtil.close();      // 调用关闭数据库方法
		}
	}

}
