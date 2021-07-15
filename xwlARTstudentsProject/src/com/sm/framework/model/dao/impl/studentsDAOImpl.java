package com.sm.framework.model.dao.impl;

// ����Ŀ��ȡMVC�ֲ��д��������model���dao�Ľӿڵ�ʵ����DAOImpl��ʵ��dao�ӿ��еķ���

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sm.framework.model.entity.students;
import com.sm.framework.model.util.DBUtil;

public class studentsDAOImpl implements studentsDAO {

	@Override
	// add���ӷ�����ʵ��
	public boolean add(students students) {
		boolean flag = false;
		
		// ��students���е�get������ȡ��������studentʵ����ÿ������ֵ
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
		
		// ��дSQL�����������
		String sql = "insert into students(student_id, student_name, student_yuan, student_class, student_room, student_sex, student_citysheng, student_cityshi, student_phone, student_home)"
				+ "values('" + studentId + "', '" + studentName + "', '" + studentYuan + "', '" + studentClass + "', '" + studentRoom + "', '" + studentSex + "', '" + studentCitySheng + "', '" + studentCityShi + "', '" + studentPhone + "', '" + studentHome + "')";
		
		// ʵ����DBUtil��ʹ������ĸ��·���
		DBUtil dbUtil = new DBUtil();
		int n = dbUtil.update(sql);
		dbUtil.close();
		if(n > 0) {
			flag = true;
		}
		return flag;
	}

	
	@Override
	// remove����id����ɾ���ķ�����ʵ��
	public boolean remove(String studentId) {
		boolean flag = false;
		
		// ���ݴ��������ַ���id��дSQL���ɾ��
		String sql = "delete from students where student_id = '" + studentId + "'";
		
		// ʵ����DBUtil��ʹ������ĸ��·���
		DBUtil dbUtil = new DBUtil();
		int n = dbUtil.update(sql);
		dbUtil.close();
		if(n > 0) {
			flag = true;
		}
		return flag;
	}

	
	@Override
	// modify����id�����޸��������ݵķ�����ʵ��
	public boolean modify(students students) {
		boolean flag = false;
		
		// ��students���е�get������ȡ��������studentʵ����ÿ������ֵ
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
		
		// ��дSQL������id�����������ݵ��޸�
		String sql = "update students set student_name = '" + studentName + "', student_yuan = '" + studentYuan + "', student_class = '" + studentClass + "', student_room = '" + studentRoom + "', student_sex = '" + studentSex + "', student_citysheng = '" + studentCitySheng + "', student_cityshi = '" + studentCityShi + "', student_phone = '" + studentPhone + "', student_home = '" + studentHome + "' where student_id = '" + studentId + "'" ;
		
		// ʵ����DBUtil��ʹ������ĸ��·���
		DBUtil dbUtil = new DBUtil();
		int n = dbUtil.update(sql);
		dbUtil.close();
		if(n > 0) {
			flag = true;
		}
		return flag;
	}

	
	@Override
	// findById����id���в�ѯ�������ݵķ�����ʵ��
	public students findById(String studentId) {
		students student = null;
		
		// ��дSQL�����ݴ��������ַ���id�����������ݵĲ�ѯ
		String sql = "select * from students where student_id = '" + studentId + "'";
		
		// ʵ����DBUtil��ʹ������Ĳ�ѯ����
		DBUtil dbUtil = new DBUtil();
		ResultSet rs = dbUtil.query(sql);
		try {
			while(rs.next()) {
				// Ȼ��ʵ����students����set�������������rs�и����ݵ�ֵset����ʵ������student
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
		// ���ﴫ������һ��ʵ��������֮����÷�����ʱ����toString()����ǿתһ��
		return student;
	}

	
	@Override
	// findByLike���ݸ���ģ�����ݽ��в�ѯ�ķ�����ʵ��
	public List<students> findByLike(students students) {
		// �÷������ص���һ��List���ϣ������Ƚ��з���Ϊstudents���ʵ����
		List<students> list = new ArrayList<students>();
		
		// Ȼ����students���е�get������ȡ������ʱ����Ķ���set����
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
		
		// ����ƴ�ӵķ�����дSQL���
		String select = "select * from students";      // �ͷ�Ĳ�ѯ���select
		String where = " where 1=1";                   // �����ж�����where����䣬����1=1������������һ�����������䣬����������ζ��������֮���������䶼��and��ͷ�����Ͳ�����ֵ�һ�����ݲ��Ҿ���and��ͷ�����
		// �������Ǹ��ݸ������Ƿ���ڵ������ƴ�Ӳ�ѯ����
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
		
		// ʵ����DBUtil��ʹ������Ĳ�ѯ����
		DBUtil dbUtil = new DBUtil();
		ResultSet rs = dbUtil.query(sql);
		try {
			while(rs.next()) {
				// Ȼ��ʵ����students����סһ��Ҫ��rs��ʵ����students��Ȼ����������ݶ���һ������set�������������rs�и����ݵ�ֵset����ʵ������temp
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
		// ���ﴫ������һ���ӿڣ����÷�����ʱ�����temp���
		return list;
	}

}
