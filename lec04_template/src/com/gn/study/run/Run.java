package com.gn.study.run;

import com.gn.study.view.Menu;

// 애플리케이션 실행 -> 메인 메소드
public class Run {
	// View 호출
	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.mainMenu();
	}
}
