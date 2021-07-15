package com.sm.framework.model.dao.impl;

// 该项目采取MVC分层编写，本类是model层的dao测试类，测试接口中的方法是否都实现

import java.util.List;

import com.sm.framework.model.entity.MisUser;

public class MisUserDAOImplTest {

	public static void main(String[] args) {
		
		// 测试insertMisUser添加方法
//		MisUser misuser = new MisUser("111", "111", "111", "111", "111", "111");
//		if(new MisUserDAOImpl().insertMisUser(misuser)) {
//			System.out.println("添加成功！");
//		}
		
		
		
		// 测试deleteMisUser根据id删除方法
		if(new MisUserDAOImpl().deleteMisUser("111")) {
			System.out.println("删除成功！");
		}
		
		
		
		// 测试updateMisUser根据id修改其他数据的方法
//		MisUser misuser = new MisUser("admin", "admin", "admin", "admin", "admin", "admin1");
//		if(new MisUserDAOImpl().updateMisUser(misuser)) {
//			System.out.println("修改成功！");
//		}
		
	
		
		// 测试queryMisUser根据id查询其他数据的方法
//		MisUser misuser = new MisUserDAOImpl().queryMisUser("admin");
//		System.out.println(misuser.toString());

		
		
		// 测试queryMisUser万能查询的方法,该方法可以根据多个模糊的信息查找数据
		MisUser misuser = new MisUser();
		misuser.setUserName("yh");
		
		MisUserDAO dao = new MisUserDAOImpl();            // 实例化MisUserDAO接口，使用里面的queryMisUser方法
		List<MisUser> list = dao.queryMisUser(misuser);
		
		for (MisUser temp : list) {                // (MisUser temp : list) 是遍历输出list中MisUser类的实例化对象temp的值
			System.out.println(temp.toString());
		}
	}

}
