package com.sm.framework.model.util;    // model���е�util����

// ����Ŀ��ȡMVC�ֲ��д��������model���DButil�࣬����д������Ŀ�л��õ����й����ݿ�Ĳ�����  1,�������ݿ�     2,��������     3,��ѯ����     4,�ر����ݿ���ض���

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
	private Connection conn = null;
	private Statement sta = null;
	private ResultSet rs =  null;
	
	
	// getDbConnection�����ǻ�������ݿ��conn�����������Ͳ��ᶨ��һ�����ӣ����Ի��connȻ����ж�����ӷŽ�һ��List��
	private Connection getDbConnection() {
		Connection myConn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			myConn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=xwl", "sa", "sa");
			System.out.println("���ӳɹ�");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myConn;
	}
	
	
	// ��������д���췽������ʵ����DButil��ʱ�͵����������ݿ��getDbConnection������ȡ���ӣ�Ȼ�󽫻�õ�conn���List֮���ٽ������Ӳ���
	public DBUtil() {
		List<Connection> list = new ArrayList<Connection>();
		for(int i = 0; i < 1; i++) {         // iС�ڼ��������Ӽ��Σ����֮���õ�ʱ���ٸ��ģ�����Ϊ���ٶȸ���д��1
			list.add(this.getDbConnection());
		}
		this.conn = list.get(0);      // Ȼ���ó�List��ĵ�һ�����в���
	}
	
	
	// update�������ݿ����ݵķ��������÷���ʱ��sql���������ɸ�������
	public int update(String sql) {
		int n = -1;
		try {
			this.sta = this.conn.createStatement();
			n = this.sta.executeUpdate(sql);
			if(n > 0) {
				System.out.println("���³ɹ�");
			} else {
				System.out.println("����ʧ��");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}
	
	
	// queryʵ�����ݿ��ѯ�ķ��������ص���rsҲ����ResultSet����Ķ��������÷�����ʱ����rs���ڵ�������������
	public ResultSet query(String sql) {
		try {
			this.sta = this.conn.createStatement();
			this.rs = this.sta.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.rs;
	}
	
	
	// close�ǹر����ݿ�ķ���
	public void close() {
		try {
			if(this.rs != null) {
				this.rs.close();
				this.rs = null;
			}
			if(this.sta != null) {
				this.sta.close();
				this.sta = null;
			}
			if(this.conn != null) {
				this.conn.close();
				this.conn = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
