package com.sm.framework.model.dao.impl;

// 该项目采取MVC分层编写，本类是model层的dao测试类，测试接口中的方法是否都实现

import java.util.List;

import com.sm.framework.model.entity.students;

public class studentsDAOImplTest {

	public static void main(String[] args) {
		
		// 测试add添加方法
//		students student = new students("2507180201", "王未来", "信息工程学院", "软工1802", "2-159", "男", "广东省", "深圳市", "18791987706", "中心区最好的软件公司");
//		if(new studentsDAOImpl().add(student)) {
//			System.out.println("添加成功！");
//		}
		
		
		
		// 测试remove根据id删除方法
//		if(new studentsDAOImpl().remove("2507180206")) {
//			System.out.println("删除成功！");
//		}
		
		
		
		// 测试modify根据id修改其他数据的方法
//		students student = new students("2507180201", "王好未来", "信息工程学院", "软工1802", "2-159", "男", "广东省", "深圳市", "18791987706", "中心区最好的软件公司");
//		if(new studentsDAOImpl().modify(student)) {
//			System.out.println("修改成功！");
//		}
		
	
		
		// 测试findById根据id查询其他数据的方法
//		students student = new studentsDAOImpl().findById("2507180201");
//		System.out.println(student.toString());

		
		
		// 测试findByLike万能查询的方法,该方法可以根据多个模糊的信息查找数据
		students student = new students();
		student.setStudent_id("113");
		
		studentsDAO dao = new studentsDAOImpl();            // 实例化studentDAO接口，使用里面的findByLike方法
		List<students> list = dao.findByLike(student);
		
		for (students temp : list) {                // (students temp : list) 是遍历输出list中students类的实例化对象temp的值
			System.out.println(temp.toString());
		}
	}

}
