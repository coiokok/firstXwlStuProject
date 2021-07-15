package com.sm.framework.model.dao.impl;

import java.util.List;

import com.sm.framework.model.entity.className;

//该项目采取MVC分层编写，本类是model层的className的班级管理dao的接口，里面声明该项目中会用到的所有跟操作班级相关的操作数据库的方法
//该项目有 1.addClass增加方法    2.removeClass删除方法

public interface classNameDAO {

	public boolean addClass(className classObj);                   // insertMisUser方法传入一个MisUser对象的实例进行新增

	public boolean removeClass(String studentclass);               // deleteMisUser方法传入一个字符型管理员id进行删除
	
	public List<className> findByLike(className className);        // findByLike方法传入一个className对象的实例根据哪个属性有值根据哪个属性进行查找
	
}
