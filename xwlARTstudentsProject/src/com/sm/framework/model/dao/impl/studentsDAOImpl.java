package com.sm.framework.model.dao.impl;

// 该项目采取MVC分层编写，本类是model层的dao的接口的实现类DAOImpl，实现dao接口中的方法

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sm.framework.model.entity.students;
import com.sm.framework.model.util.DBUtil;

public class studentsDAOImpl implements studentsDAO {

	@Override
	// add增加方法的实现
	public boolean add(students students) {
		boolean flag = false;
		
		// 用students类中的get方法获取传进来的student实例的每个数据值
		String studentId = students.getStudent_id();
		String studentName = students.getStudent_name();
		String studentYuan = students.getStudent_yuan();
		String studentClass = students.getStudent_class();
		String studentRoom = students.getStudent_room();
		String studentSex = students.getStudent_sex();
		String studentCitySheng = students.getStudent_citysheng();
		String studentCityShi = students.getStudent_cityshi();
		String studentPhone = students.getStudent_phone();
		String studentHome = students.getStudent_home();
		
		// 编写SQL语句新增数据
		String sql = "insert into students(student_id, student_name, student_yuan, student_class, student_room, student_sex, student_citysheng, student_cityshi, student_phone, student_home)"
				+ "values('" + studentId + "', '" + studentName + "', '" + studentYuan + "', '" + studentClass + "', '" + studentRoom + "', '" + studentSex + "', '" + studentCitySheng + "', '" + studentCityShi + "', '" + studentPhone + "', '" + studentHome + "')";
		
		// 实例化DBUtil，使用里面的更新方法
		DBUtil dbUtil = new DBUtil();
		int n = dbUtil.update(sql);
		dbUtil.close();
		if(n > 0) {
			flag = true;
		}
		return flag;
	}

	
	@Override
	// remove根据id进行删除的方法的实现
	public boolean remove(String studentId) {
		boolean flag = false;
		
		// 根据传进来的字符串id编写SQL语句删除
		String sql = "delete from students where student_id = '" + studentId + "'";
		
		// 实例化DBUtil，使用里面的更新方法
		DBUtil dbUtil = new DBUtil();
		int n = dbUtil.update(sql);
		dbUtil.close();
		if(n > 0) {
			flag = true;
		}
		return flag;
	}

	
	@Override
	// modify根据id进行修改其他数据的方法的实现
	public boolean modify(students students) {
		boolean flag = false;
		
		// 用students类中的get方法获取传进来的student实例的每个数据值
		String studentId = students.getStudent_id();
		String studentName = students.getStudent_name();
		String studentYuan = students.getStudent_yuan();
		String studentClass = students.getStudent_class();
		String studentRoom = students.getStudent_room();
		String studentSex = students.getStudent_sex();
		String studentCitySheng = students.getStudent_citysheng();
		String studentCityShi = students.getStudent_cityshi();
		String studentPhone = students.getStudent_phone();
		String studentHome = students.getStudent_home();
		
		// 编写SQL语句根据id进行其他数据的修改
		String sql = "update students set student_name = '" + studentName + "', student_yuan = '" + studentYuan + "', student_class = '" + studentClass + "', student_room = '" + studentRoom + "', student_sex = '" + studentSex + "', student_citysheng = '" + studentCitySheng + "', student_cityshi = '" + studentCityShi + "', student_phone = '" + studentPhone + "', student_home = '" + studentHome + "' where student_id = '" + studentId + "'" ;
		
		// 实例化DBUtil，使用里面的更新方法
		DBUtil dbUtil = new DBUtil();
		int n = dbUtil.update(sql);
		dbUtil.close();
		if(n > 0) {
			flag = true;
		}
		return flag;
	}

	
	@Override
	// findById根据id进行查询其他数据的方法的实现
	public students findById(String studentId) {
		students student = null;
		
		// 编写SQL语句根据传进来的字符串id进行其他数据的查询
		String sql = "select * from students where student_id = '" + studentId + "'";
		
		// 实例化DBUtil，使用里面的查询方法
		DBUtil dbUtil = new DBUtil();
		ResultSet rs = dbUtil.query(sql);
		try {
			while(rs.next()) {
				// 然后实例化students，用set方法将查出来的rs中各数据的值set给刚实例化的student
				student = new students();
				student.setStudent_id(rs.getString("student_id"));
				student.setStudent_name(rs.getString("student_name"));
				student.setStudent_yuan(rs.getString("student_yuan"));
				student.setStudent_class(rs.getString("student_class"));
				student.setStudent_room(rs.getString("student_room"));
				student.setStudent_sex(rs.getString("student_sex"));
				student.setStudent_citysheng(rs.getString("student_citysheng"));
				student.setStudent_cityshi(rs.getString("student_cityshi"));
				student.setStudent_phone(rs.getString("student_phone"));
				student.setStudent_home(rs.getString("student_home"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtil.close();
		}
		// 这里传出的是一个实例化对象，之后调用方法的时候用toString()方法强转一下
		return student;
	}

	
	@Override
	// findByLike根据各种模糊数据进行查询的方法的实现
	public List<students> findByLike(students students) {
		// 该方法返回的是一个List集合，所以先进行范型为students类的实例化
		List<students> list = new ArrayList<students>();
		
		// 然后用students类中的get方法获取各调用时输入的对象set数据
		String studentId = students.getStudent_id();
		String studentName = students.getStudent_name();
		String studentYuan = students.getStudent_yuan();
		String studentClass = students.getStudent_class();
		String studentRoom = students.getStudent_room();
		String studentSex = students.getStudent_sex();
		String studentCitySheng = students.getStudent_citysheng();
		String studentCityShi = students.getStudent_cityshi();
		String studentPhone = students.getStudent_phone();
		String studentHome = students.getStudent_home();
		
		// 运用拼接的方法编写SQL语句
		String select = "select * from students";      // 最开头的查询语句select
		String where = " where 1=1";                   // 这是判断条件where的语句，这里1=1的作用是这是一个恒成立的语句，所以无论如何都会成立，之后的所有语句都用and开头这样就不会出现第一个数据查找就以and开头的情况
		// 接下来是根据各数据是否存在的情况来拼接查询条件
		if(studentId != null && studentId.length() > 0) {
			where = where + " and student_id like '%" + studentId + "%'";
		}
		if(studentName != null && studentName.length() > 0) {
			where = where + " and student_name like '%" + studentName + "%'";
		}
		if(studentYuan != null && studentYuan.length() > 0) {
			where = where + " and student_yuan like '%" + studentYuan + "%'";
		}
		if(studentClass != null && studentClass.length() > 0) {
			where = where + " and student_class like '%" + studentClass + "%'";
		}
		if(studentRoom != null && studentRoom.length() > 0) {
			where = where + " and student_room like '%" + studentRoom + "%'";
		}
		if(studentSex != null && studentSex.length() > 0) {
			where = where + " and student_sex like '%" + studentSex + "%'";
		}
		if(studentCitySheng != null && studentCitySheng.length() > 0) {
			where = where + " and student_citySheng like '%" + studentCitySheng + "%'";
		}
		if(studentCityShi != null && studentCityShi.length() > 0) {
			where = where + " and student_cityShi like '%" + studentCityShi + "%'";
		}
		if(studentPhone != null && studentPhone.length() > 0) {
			where = where + " and student_phone like '%" + studentPhone + "%'";
		}
		if(studentHome != null && studentHome.length() > 0) {
			where = where + " and student_home like '%" + studentHome + "%'";
		}
		String sql = select + where;
		
		// 实例化DBUtil，使用里面的查询方法
		DBUtil dbUtil = new DBUtil();
		ResultSet rs = dbUtil.query(sql);
		try {
			while(rs.next()) {
				// 然后实例化students，记住一定要在rs中实例化students不然查出来的数据都会一样，用set方法将查出来的rs中各数据的值set给刚实例化的temp
				students temp = new students();
				temp.setStudent_id(rs.getString("student_id"));
				temp.setStudent_name(rs.getString("student_name"));
				temp.setStudent_yuan(rs.getString("student_yuan"));
				temp.setStudent_class(rs.getString("student_class"));
				temp.setStudent_room(rs.getString("student_room"));
				temp.setStudent_sex(rs.getString("student_sex"));
				temp.setStudent_citysheng(rs.getString("student_citysheng"));
				temp.setStudent_cityshi(rs.getString("student_cityshi"));
				temp.setStudent_phone(rs.getString("student_phone"));
				temp.setStudent_home(rs.getString("student_home"));
				list.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtil.close();
		}
		// 这里传出的是一个接口，调用方法的时候遍历temp输出
		return list;
	}

}
