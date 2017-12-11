package com.Stage1.view;

import java.util.Scanner;

import com.Stage1.tool.InputTool;


public class UserMainView extends View {

	@Override
	public View showView() {
		System.out.println("====================");
		System.out.println("欢迎访问二嗨租车");
		System.out.println("====================");
		System.out.println(" 1.登录  2.注册  3.退出");
		InputTool it = InputTool.getInstance();		
		Scanner scan = it.getScanner();
		int choose = scan.nextInt();
		switch (choose) {
		case 1:
			View lv = new LoginView();
			lv.showView();
			break;
		case 2:
			View rv = new RegisterView();
			rv.showView();
			break;
		case 3:
			System.out.println("欢迎再次访问,再见!");
			break;
		default:			
			break;
		}	
		
		return null;
	}
}
