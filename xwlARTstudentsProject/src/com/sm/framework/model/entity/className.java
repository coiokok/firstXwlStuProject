package com.sm.framework.model.entity;

//����Ŀ��ȡMVC�ֲ��д��������model���entity��className�༶��Ϣ��ʵ���࣬������Ӧ���ݿ����ݵ��֮࣬���java���붼��ͨ�����ʵ������ȥ�������ݿ�
//֮����õ�1.���췽��   2.get and set����   3.toString()����   4.hashCode() and equals()����


public class className {
	
	private String student_class = null;       // ���еı�����Ҫ�����ݿ���������һ��

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
