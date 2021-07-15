package com.sm.framework.model.util;    // model层中的util部分

// 该项目采取MVC分层编写，本类是model层的DButil类，这里写所有项目中会用到的有关数据库的操作；  1,连接数据库     2,更新数据     3,查询数据     4,关闭数据库相关对象

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
	
	
	// getDbConnection方法是活动连接数据库的conn方法，这样就不会定死一次连接，可以获得conn然后进行多次连接放进一个List中
	private Connection getDbConnection() {
		Connection myConn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			myConn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=xwl", "sa", "sa");
			System.out.println("连接成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myConn;
	}
	
	
	// 这是在重写构造方法，当实例化DButil类时就调用连接数据库的getDbConnection方法获取连接，然后将获得的conn存进List之后再进行连接操作
	public DBUtil() {
		List<Connection> list = new ArrayList<Connection>();
		for(int i = 0; i < 1; i++) {         // i小于几就是连接几次，这个之后用的时候再更改，现在为了速度更快写成1
			list.add(this.getDbConnection());
		}
		this.conn = list.get(0);      // 然后拿出List里的第一个进行操作
	}
	
	
	// update更新数据库数据的方法，调用方法时传sql语句进来即可更新数据
	public int update(String sql) {
		int n = -1;
		try {
			this.sta = this.conn.createStatement();
			n = this.sta.executeUpdate(sql);
			if(n > 0) {
				System.out.println("更新成功");
			} else {
				System.out.println("更新失败");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}
	
	
	// query实现数据库查询的方法，返回的是rs也就是ResultSet对象的东西，调用方法的时候获得rs再在调用类中完成输出
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
	
	
	// close是关闭数据库的方法
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
