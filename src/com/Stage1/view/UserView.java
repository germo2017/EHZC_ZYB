package com.Stage1.view;

import java.sql.SQLException;
import java.util.Scanner;
import org.pmw.tinylog.Logger;
import com.Stage1.biz.CarBiz;
import com.Stage1.bizImpl.CarBizImpl;
import com.Stage1.tool.InputTool;

public class UserView{//extends View ��Ҫ���û���,���Բ��̳�

	
	public View showView(String userAccount){
		InputTool it = InputTool.getInstance();
		Scanner scan = it.getScanner();		
		CarBiz cb = new CarBizImpl();
		cb.showUserAllCar();
		System.out.println("\n"+"����0  �˳�");
		System.out.println("����1+�������  ���޴˱������  ��1+2");
		System.out.println("����2+1  ���۸�������|����2+2  ���۸���������");
		System.out.println("����3+���ͱ��  ����������");
		System.out.println("����4+Ʒ�Ʊ��  ��Ʒ������");
		System.out.println("����5  �鿴ȫ������");
		System.out.println("����6  �鿴�ҵ��⳵��¼");
		System.out.println("����7+�������  ����");
		System.out.println("����8+����ĳ�������/��Ʒ��/��С����(ģ����ѯ)");
		
		try {
			cb.searchCar(scan.next(),userAccount);
		} catch (SQLException e) {
			Logger.error(e.getMessage());
		}
		
		
		
		return null;
	}
}
