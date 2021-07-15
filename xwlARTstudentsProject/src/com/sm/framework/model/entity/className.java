package com.sm.framework.model.entity;

//该项目采取MVC分层编写，本类是model层的entity的className班级信息的实体类，用来对应数据库数据的类，之后的java代码都是通过这个实体类再去操作数据库
//之后会用到1.构造方法   2.get and set方法   3.toString()方法   4.hashCode() and equals()方法


public class className {
	
	private String student_class = null;       // 所有的变量名要和数据库表里的列名一样

	public className() {
		
	}
	
	public className(String student_class) {
		super();
		this.student_class = student_class;
	}

	public String getStudent_class() {
		return student_class;
	}

	public void setStudent_class(String student_class) {
		this.student_class = student_class;
	}

	@Override
	public String toString() {
		return "className [student_class=" + student_class + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((student_class == null) ? 0 : student_class.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		className other = (className) obj;
		if (student_class == null) {
			if (other.student_class != null)
				return false;
		} else if (!student_class.equals(other.student_class))
			return false;
		return true;
	}
	
	
}
