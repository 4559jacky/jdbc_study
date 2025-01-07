package com.gn.study.run;

import com.gn.study.view.ProjectMenu;

public class Run {
	// 실행 담당 -> 메인 메소드 반드시 포함
	public static void main(String[] args) {
		ProjectMenu pm = new ProjectMenu();
		pm.mainMenu();
	}
}