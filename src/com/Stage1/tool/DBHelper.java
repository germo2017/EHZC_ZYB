package com.Stage1.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {
	private String driver = null;
	private String url = null;
	private String user = null;
	private String pwd = null;
	Connection mConnection;
	
	public Connection getConnection() {
		
		try {
			//1. 构建Properties对象
			Properties p = new Properties();
			//2. 提供File对象指向配置文件		
			File file = new File("config//config.properties");
			//3. 根据文件提供流对象
			InputStream is = new FileInputStream(file);
			//4. 加载流对象
			p.load(is);
			//5. 从文件中获取内容
			driver = p.getProperty("DRIVER");
			url = p.getProperty("URL");
			user = p.getProperty("USER");
			pwd = p.getProperty("PWD");
		
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		if(mConnection == null) {
			try {
				Class.forName(driver);
				mConnection = DriverManager.getConnection(url, user, pwd);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return mConnection;
	}
	
	
	
}