package com.Stage1.view;
import java.util.Scanner;
import com.Stage1.bizImpl.UserBizImpl;
import com.Stage1.tool.InputTool;

public class LoginView extends View {
	

	@Override
	public View showView() {
		System.out.println("======登录=====>>>>");
		InputTool it = InputTool.getInstance();
		Scanner scan = it.getScanner();
		System.out.println("用户名:");
		String userAccount = scan.next();
		System.out.println("密码:");
		String userPwd = scan.next();
		System.out.println("=================");

		UserBizImpl ub = new UserBizImpl();
		int loginCount = 1;
		while(true) {
			if (ub.userlogin(userAccount, userPwd)) {
				System.out.println("欢迎"+userAccount);
				loginCount = 1;
				//用户登录功能后,自动进入查询汽车页面，默认查询全部已上架汽车信息并显示
				//判断用户类型,普通用户进入普通用户操作菜单,管理员进入管理菜单
				if(ub.getU().getUserType()==2) {
					new UserView().showView();
				}else if(ub.getU().getUserType()==1) {
					new AdminView().showView();
				}else {
					;
				}
				break;
			} else if (loginCount<=2 && !ub.userlogin(userAccount, userPwd)){				
				System.out.println("登录失败,请重新登录,剩余登录次数:"+(3-loginCount));
				System.out.println("用户名:");
				userAccount = scan.next();
				System.out.println("密码:");
				userPwd = scan.next();
				loginCount++;
				continue;
			}else if(loginCount==3 && !ub.userlogin(userAccount, userPwd)){
				System.out.println("登录失败,请注册!");
				loginCount = 1;
				//登录三次失败, 跳转到注册页面
				new RegisterView().showView();
				break;
			}
			
		}
		return null;
		
	}
}
