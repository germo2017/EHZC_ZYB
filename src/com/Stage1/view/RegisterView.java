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
		System.out.println("======ע��=====>>>>");
		InputTool it = InputTool.getInstance();
		Scanner scan = it.getScanner();
		String userAccount = null;
		String userPwd = null;
		boolean isContinue = true;
		UserBiz ub = new UserBizImpl();

		do {
			System.out.println("�������û���(��ʽҪ��:1.����ĸ��д;2.ʹ����ĸ,���ֻ��»���;3.����6-16):");
			while (true) {
				userAccount = scan.next();
				if (checkFormat(userAccount)) {
					break;
				} else {
					System.out.println("�����õ��û���������������ʽҪ��,����������:");
					continue;
				}
			}

			System.out.println("����������(Ҫ��:1.����ĸ��д;2.ʹ����ĸ,���ֻ��»���;3.����6-16):");
			while (true) {
				userPwd = scan.next();
				if (checkFormat(userPwd)) {
					while (true) {
						System.out.println("��ȷ������:");
						String userPwd2 = scan.next();
						if (userPwd.equals(userPwd2)) {
							break;
						} else {
							System.out.println("���������õ����벻һ��,����������:");
						}
					}
					break;
				} else {
					System.out.println("�����õ����벻����������ʽҪ��,����������:");
					continue;
				}

			}
			if (ub.userRegister(userAccount, userPwd)) {
				System.out.println("ע��ɹ�!");
				Logger.info("���û�ע��ɹ�!");
				new LoginView().showView();
				isContinue = false;
			} else {
				System.out.println("�Ƿ�����ע��:Y/N?");
				if ("Y".equalsIgnoreCase(scan.next())) {
					isContinue = true;
				} else {
					System.out.println("��ӭ�ٴι���!");
					isContinue = false;
				}
			}
		} while (isContinue);

		return null;
	}

	/**
	 * ���Userע���˻��������Ƿ���ϸ�ʽ
	 * 
	 * @param contents����������
	 * @return
	 */
	public boolean checkFormat(String contents) {
		// ����
		String regEx = "[A-Z]{1,}[a-zA-z0-9_]{5,15}";
		// ����������ʽ
		Pattern pattern = Pattern.compile(regEx);
		// �ַ����Ƿ���������ʽ��ƥ��
		Matcher matcher = pattern.matcher(contents);
		return matcher.matches();
	}

}
