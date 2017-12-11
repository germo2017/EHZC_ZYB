package com.Stage1.Test;

import java.util.Scanner;

import com.Stage1.tool.InputTool;
import com.Stage1.view.AdminMainView;
import com.Stage1.view.UserMainView;
import com.Stage1.view.View;


public class Test {

	public static void main(String[] args) {		
		System.out.println("====================");
		System.out.println("     二嗨租车欢迎您!");
		System.out.println("====================");
		System.out.println("请选择用户类型 1.普通用户; 2.管理员:");
		InputTool it = InputTool.getInstance();		
		Scanner scan = it.getScanner();
		int choose = scan.nextInt();
		switch (choose) {
		case 1:
			View umv = new UserMainView();
			umv.showView();
			break;
		case 2:
			View amv = new AdminMainView();
			amv.showView();
			break;
		default:
			
			break;
		}
		scan.close();
	}


}
