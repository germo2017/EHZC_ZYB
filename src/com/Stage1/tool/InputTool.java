package com.Stage1.tool;

import java.util.Scanner;

public class InputTool {
	private static InputTool instance;
	private static Scanner mScanner;

	private InputTool() {
	}

	public static InputTool getInstance() {
		if (instance == null) {
			instance = new InputTool();
		}
		return instance;
	}

	// µ¥Àý
	public /* synchronized */ Scanner getScanner() {
		if (mScanner == null) {
			mScanner = new Scanner(System.in);
		}
		return mScanner;
	}
}