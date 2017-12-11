package com.Stage1.Test;

import java.util.Scanner;

import com.Stage1.tool.InputTool;
import com.Stage1.view.AdminMainView;
import com.Stage1.view.UserMainView;
import com.Stage1.view.View;


public class Test {

	public static void main(String[] args) {		
		System.out.println("====================");
		System.out.println("     �����⳵��ӭ��!");
		System.out.println("====================");
		System.out.println("��ѡ���û����� 1.��ͨ�û�; 2.����Ա:");
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
