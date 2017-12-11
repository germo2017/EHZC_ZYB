package com.Stage1.view;
import java.util.Scanner;
import com.Stage1.bizImpl.UserBizImpl;
import com.Stage1.tool.InputTool;

public class LoginView extends View {
	

	@Override
	public View showView() {
		System.out.println("======��¼=====>>>>");
		InputTool it = InputTool.getInstance();
		Scanner scan = it.getScanner();
		System.out.println("�û���:");
		String userAccount = scan.next();
		System.out.println("����:");
		String userPwd = scan.next();
		System.out.println("=================");

		UserBizImpl ub = new UserBizImpl();
		int loginCount = 1;
		while(true) {
			if (ub.userlogin(userAccount, userPwd)) {
				System.out.println("��ӭ"+userAccount);
				loginCount = 1;
				//�û���¼���ܺ�,�Զ������ѯ����ҳ�棬Ĭ�ϲ�ѯȫ�����ϼ�������Ϣ����ʾ
				//�ж��û�����,��ͨ�û�������ͨ�û������˵�,����Ա�������˵�
				if(ub.getU().getUserType()==2) {
					new UserView().showView();
				}else if(ub.getU().getUserType()==1) {
					new AdminView().showView();
				}else {
					;
				}
				break;
			} else if (loginCount<=2 && !ub.userlogin(userAccount, userPwd)){				
				System.out.println("��¼ʧ��,�����µ�¼,ʣ���¼����:"+(3-loginCount));
				System.out.println("�û���:");
				userAccount = scan.next();
				System.out.println("����:");
				userPwd = scan.next();
				loginCount++;
				continue;
			}else if(loginCount==3 && !ub.userlogin(userAccount, userPwd)){
				System.out.println("��¼ʧ��,��ע��!");
				loginCount = 1;
				//��¼����ʧ��, ��ת��ע��ҳ��
				new RegisterView().showView();
				break;
			}
			
		}
		return null;
		
	}
}
