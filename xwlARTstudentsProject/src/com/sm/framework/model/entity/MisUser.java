package com.sm.framework.model.entity;

// ����Ŀ��ȡMVC�ֲ��д��������model���entity��MisUser�û�Ȩ�޵�ʵ���࣬������ӦmisUser���û�Ȩ�����ݿ����
// ֮����õ�1.���췽��   2.get and set����   3.toString()����   4.hashCode() and equals()����

public class MisUser {
	private String userId = null;
	private String userName = null;
	private String userPass = null;
	private String roleId = null;
	
	public MisUser() {
		// TODO Auto-generated constructor stub
	}
	
	// ���췽��������ͨ���Ҽ�Source�е�using Fields
	public MisUser(String userId, String userName, String userPass, String roleId) {
		this.userId = userId;
		this.userName = userName;
		this.userPass = userPass;
		this.roleId = roleId;
	}
	
	// �Ҽ�Source�е�get and set
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	// �Ҽ�Source�е�toString()����
	@Override
	public String toString() {
		return "MisUser [userId=" + userId + ", userName=" + userName + ", userPass=" + userPass + ", roleId=" + roleId + "]";
	}

	// �Ҽ�Source�е�hashCode() and equals()����
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userPass == null) ? 0 : userPass.hashCode());
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
		MisUser other = (MisUser) obj;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userPass == null) {
			if (other.userPass != null)
				return false;
		} else if (!userPass.equals(other.userPass))
			return false;
		return true;
	}
	
}
