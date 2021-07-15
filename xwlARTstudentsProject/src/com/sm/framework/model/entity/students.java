package com.sm.framework.model.entity;

// ����Ŀ��ȡMVC�ֲ��д��������model���entity��studentsѧ����Ϣ��ʵ���࣬������Ӧ���ݿ����ݵ��֮࣬���java���붼��ͨ�����ʵ������ȥ�������ݿ�
// ֮����õ�1.���췽��   2.get and set����   3.toString()����   4.hashCode() and equals()����

public class students {    // ���������Ҫ�����ݿ����õı���һ��
	
	private String student_id = null;       // ���еı�����Ҫ�����ݿ���������һ��
	private String student_name = null;
	private String student_yuan = null;
	private String student_class = null;
	private String student_room = null;
	private String student_sex = null;
	private String student_citysheng = null;
	private String student_cityshi = null;
	private String student_phone = null;
	private String student_home = null;
	
	
	public students() {
		// TODO Auto-generated constructor stub
	}
	
	
	// ���췽��������ͨ���Ҽ�Source�е�using Fields
	public students(String student_id, String student_name, String student_yuan, String student_class,
			String student_room, String student_sex, String student_citysheng, String student_cityshi,
			String student_phone, String student_home) {
		this.student_id = student_id;
		this.student_name = student_name;
		this.student_yuan = student_yuan;
		this.student_class = student_class;
		this.student_room = student_room;
		this.student_sex = student_sex;
		this.student_citysheng = student_citysheng;
		this.student_cityshi = student_cityshi;
		this.student_phone = student_phone;
		this.student_home = student_home;
	}
	

	// �Ҽ�Source�е�get and set
	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public String getStudent_yuan() {
		return student_yuan;
	}

	public void setStudent_yuan(String student_yuan) {
		this.student_yuan = student_yuan;
	}

	public String getStudent_class() {
		return student_class;
	}

	public void setStudent_class(String student_class) {
		this.student_class = student_class;
	}

	public String getStudent_room() {
		return student_room;
	}

	public void setStudent_room(String student_room) {
		this.student_room = student_room;
	}

	public String getStudent_sex() {
		return student_sex;
	}

	public void setStudent_sex(String student_sex) {
		this.student_sex = student_sex;
	}

	public String getStudent_citysheng() {
		return student_citysheng;
	}

	public void setStudent_citysheng(String student_citysheng) {
		this.student_citysheng = student_citysheng;
	}

	public String getStudent_cityshi() {
		return student_cityshi;
	}

	public void setStudent_cityshi(String student_cityshi) {
		this.student_cityshi = student_cityshi;
	}

	public String getStudent_phone() {
		return student_phone;
	}

	public void setStudent_phone(String student_phone) {
		this.student_phone = student_phone;
	}

	public String getStudent_home() {
		return student_home;
	}

	public void setStudent_home(String student_home) {
		this.student_home = student_home;
	}

	
	// �Ҽ�Source�е�toString()����
	@Override
	public String toString() {
		return "students [student_id=" + student_id + ", student_name=" + student_name + ", student_yuan="
				+ student_yuan + ", student_class=" + student_class + ", student_room=" + student_room
				+ ", student_sex=" + student_sex + ", student_citysheng=" + student_citysheng + ", student_cityshi="
				+ student_cityshi + ", student_phone=" + student_phone + ", student_home=" + student_home + "]";
	}

	
	// �Ҽ�Source�е�hashCode() and equals()����
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((student_citysheng == null) ? 0 : student_citysheng.hashCode());
		result = prime * result + ((student_cityshi == null) ? 0 : student_cityshi.hashCode());
		result = prime * result + ((student_class == null) ? 0 : student_class.hashCode());
		result = prime * result + ((student_home == null) ? 0 : student_home.hashCode());
		result = prime * result + ((student_id == null) ? 0 : student_id.hashCode());
		result = prime * result + ((student_name == null) ? 0 : student_name.hashCode());
		result = prime * result + ((student_phone == null) ? 0 : student_phone.hashCode());
		result = prime * result + ((student_room == null) ? 0 : student_room.hashCode());
		result = prime * result + ((student_sex == null) ? 0 : student_sex.hashCode());
		result = prime * result + ((student_yuan == null) ? 0 : student_yuan.hashCode());
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
		students other = (students) obj;
		if (student_citysheng == null) {
			if (other.student_citysheng != null)
				return false;
		} else if (!student_citysheng.equals(other.student_citysheng))
			return false;
		if (student_cityshi == null) {
			if (other.student_cityshi != null)
				return false;
		} else if (!student_cityshi.equals(other.student_cityshi))
			return false;
		if (student_class == null) {
			if (other.student_class != null)
				return false;
		} else if (!student_class.equals(other.student_class))
			return false;
		if (student_home == null) {
			if (other.student_home != null)
				return false;
		} else if (!student_home.equals(other.student_home))
			return false;
		if (student_id == null) {
			if (other.student_id != null)
				return false;
		} else if (!student_id.equals(other.student_id))
			return false;
		if (student_name == null) {
			if (other.student_name != null)
				return false;
		} else if (!student_name.equals(other.student_name))
			return false;
		if (student_phone == null) {
			if (other.student_phone != null)
				return false;
		} else if (!student_phone.equals(other.student_phone))
			return false;
		if (student_room == null) {
			if (other.student_room != null)
				return false;
		} else if (!student_room.equals(other.student_room))
			return false;
		if (student_sex == null) {
			if (other.student_sex != null)
				return false;
		} else if (!student_sex.equals(other.student_sex))
			return false;
		if (student_yuan == null) {
			if (other.student_yuan != null)
				return false;
		} else if (!student_yuan.equals(other.student_yuan))
			return false;
		return true;
	}

}
