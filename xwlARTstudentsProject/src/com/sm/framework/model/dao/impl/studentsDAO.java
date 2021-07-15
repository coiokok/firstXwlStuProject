package com.sm.framework.model.dao.impl;

// 该项目采取MVC分层编写，本类是model层的students的学生信息管理dao的接口，里面声明该项目中会用到的所有跟学生信息管理需求相关的操作数据库的方法
// 该项目有 1.add增加方法    2.remove删除方法    3.modify修改方法   4.findById用ID查找的方法    5.findByLike万能查找的方法

import java.util.List;

import com.sm.framework.model.entity.students;

public interface studentsDAO {     // dao接口的命名是：所操作的表名+DAO
	// 各方法实现的详细逻辑思路之后在Impl实现类中阐述
	
	public boolean add(students students);                   // add方法传入一个students对象的实例进行新增
	
	public boolean remove(String studentId);                 // remove方法传入一个字符型学生id进行删除
	 
	public boolean modify(students students);                // modify方法传入一个students对象的实例根据其id进行修改
	
	public students findById(String studentId);              // findById方法传入一个字符型学生id进行查找
	
	public List<students> findByLike(students students);     // findByLike方法传入一个students对象的实例根据哪个属性有值根据哪个属性进行查找

}
