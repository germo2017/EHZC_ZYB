package com.Stage1.view;

import java.sql.SQLException;
import java.util.Scanner;
import org.pmw.tinylog.Logger;
import com.Stage1.biz.CarBiz;
import com.Stage1.bizImpl.CarBizImpl;
import com.Stage1.tool.InputTool;

public class UserView{//extends View 因要传用户名,所以不继承

	
	public View showView(String userAccount){
		InputTool it = InputTool.getInstance();
		Scanner scan = it.getScanner();		
		CarBiz cb = new CarBizImpl();
		cb.showUserAllCar();
		System.out.println("\n"+"输入0  退出");
		System.out.println("输入1+汽车编号  租赁此编号汽车  如1+2");
		System.out.println("输入2+1  按价格降序排序|输入2+2  按价格升序排序");
		System.out.println("输入3+类型编号  按类型搜索");
		System.out.println("输入4+品牌编号  按品牌搜索");
		System.out.println("输入5  查看全部汽车");
		System.out.println("输入6  查看我的租车记录");
		System.out.println("输入7+汽车编号  还车");
		System.out.println("输入8+任意的车型名称/车品牌/大小类型(模糊查询)");
		
		try {
			cb.searchCar(scan.next(),userAccount);
		} catch (SQLException e) {
			Logger.error(e.getMessage());
		}
		
		
		
		return null;
	}
}
