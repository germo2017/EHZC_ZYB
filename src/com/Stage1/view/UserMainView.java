package com.Stage1.view;

import java.util.Scanner;

import com.Stage1.tool.InputTool;


public class UserMainView extends View {

	@Override
	public View showView() {
		System.out.println("====================");
		System.out.println("��ӭ���ʶ����⳵");
		System.out.println("====================");
		System.out.println(" 1.��¼  2.ע��  3.�˳�");
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
			System.out.println("��ӭ�ٴη���,�ټ�!");
			break;
		default:			
			break;
		}	
		
		return null;
	}
}
