package com.Stage1.view;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pmw.tinylog.Logger;

import com.Stage1.biz.UserBiz;
import com.Stage1.bizImpl.UserBizImpl;
import com.Stage1.tool.InputTool;

public class RegisterView extends View {

	@Override
	public View showView() {
		System.out.println("======注册=====>>>>");
		InputTool it = InputTool.getInstance();
		Scanner scan = it.getScanner();
		String userAccount = null;
		String userPwd = null;
		boolean isContinue = true;
		UserBiz ub = new UserBizImpl();

		do {
			System.out.println("请设置用户名(格式要求:1.首字母大写;2.使用字母,数字或下划线;3.长度6-16):");
			while (true) {
				userAccount = scan.next();
				if (checkFormat(userAccount)) {
					break;
				} else {
					System.out.println("您设置的用户名不满足上述格式要求,请重新设置:");
					continue;
				}
			}

			System.out.println("请设置密码(要求:1.首字母大写;2.使用字母,数字或下划线;3.长度6-16):");
			while (true) {
				userPwd = scan.next();
				if (checkFormat(userPwd)) {
					while (true) {
						System.out.println("请确认密码:");
						String userPwd2 = scan.next();
						if (userPwd.equals(userPwd2)) {
							break;
						} else {
							System.out.println("您两次设置的密码不一致,请重新设置:");
						}
					}
					break;
				} else {
					System.out.println("您设置的密码不满足上述格式要求,请重新设置:");
					continue;
				}

			}
			if (ub.userRegister(userAccount, userPwd)) {
				System.out.println("注册成功!");
				Logger.info("新用户注册成功!");
				new LoginView().showView();
				isContinue = false;
			} else {
				System.out.println("是否重新注册:Y/N?");
				if ("Y".equalsIgnoreCase(scan.next())) {
					isContinue = true;
				} else {
					System.out.println("欢迎再次光临!");
					isContinue = false;
				}
			}
		} while (isContinue);

		return null;
	}

	/**
	 * 检查User注册账户和密码是否符合格式
	 * 
	 * @param contents待检查的内容
	 * @return
	 */
	public boolean checkFormat(String contents) {
		// 规则
		String regEx = "[A-Z]{1,}[a-zA-z0-9_]{5,15}";
		// 编译正则表达式
		Pattern pattern = Pattern.compile(regEx);
		// 字符串是否与正则表达式相匹配
		Matcher matcher = pattern.matcher(contents);
		return matcher.matches();
	}

}
