package com.gn.study.view;

import java.util.List;
import java.util.Scanner;

import com.gn.study.controller.Controller;
import com.gn.study.model.vo.Car;

// 사용자가 보게될 화면 구성
// - 사용자에게 정보 입력받기
// - 결과 화면 출력
public class Menu {
	private Scanner sc = new Scanner(System.in);
	private Controller controller = new Controller();
	public void mainMenu() {
		while(true) {
			System.out.println("=== 자동차 정보 관리 시스템 ===");
			System.out.println("1. 추가");
			System.out.println("2. 목록 조회");
			System.out.println("3. 단일 조회");
			System.out.println("4. 수정");
			System.out.println("5. 삭제");
//			System.out.println("0. 시스템 종료");
			
			System.out.print("Option : ");
			int menu = sc.nextInt();
			sc.nextLine();
			switch(menu) {
				case 1 : insertCarOne(); break;
				case 2 : selectCarAll(); break;
				case 3 : selectCarByModel();break;
				case 4 : break;
				case 5 : break;
			}	
		}
	}
	
	// 자동차 추가
	public void insertCarOne() {
		System.out.println("*** 자동차 추가 ***");
		System.out.println("모델명, 가격, 출시일을 입력하세요.");
		System.out.println("다만, 출시일은 반드시 OOOO-OO-OO 형식으로 입력해주세요.");
		System.out.print("모델명 : ");
		String modelName = sc.nextLine();
		System.out.print("가격 : ");
		int price = sc.nextInt();
		sc.nextLine();
		System.out.print("출시일 정보를 입력하시겠습니까?(Y/N) : ");
		String dateFlag = sc.nextLine();
//		char dateFlag = sc.nextLine().charAt(0);
		String date = null;
		if("Y".equals(dateFlag)) {
			
			System.out.print("출시일 : ");
			date = sc.nextLine();
		}
		int result = controller.insertCarOne(modelName, price, date);
		dmlResultPrint(result, "자동차 추가");
	}
	
	// 자동차 목록 조회
	public void selectCarAll() {
		System.out.println("*** 자동차 목록 조회 ***");
		List<Car> list = controller.selectCarAll();
		printList(list);
	}
	
	// 자동차 단일 조회
	public void selectCarByModel() {
		System.out.println("*** 단일 조회 ***");
		System.out.println("검색 기준으로 삼고 싶은 항목을 선택하세요.");
		System.out.println("1. 번호 / 2. 모델명 / 3. 가격 / 4. 출시일");
		System.out.print("선택 : ");
		int option = sc.nextInt();
		switch(option) {
			case 1 : 
				System.out.println("번호를 기준으로 검색합니다.");
				System.out.print("번호 : ");
				int modelNo = sc.nextInt();
				List<Car> car1 = controller.selectCarByNo(modelNo);
				printList(car1);
			case 2 : 
				System.out.println("모델명을 기준으로 검색합니다.");
				System.out.print("모델명 : ");
				String modelName = sc.next();
				List<Car> car2 = controller.selectCarByModel(modelName);
				printList(car2);
			case 3 : 
				System.out.println("가격을 기준으로 검색합니다.");
				System.out.print("가격 : ");
				int modelPrice = sc.nextInt();
				List<Car> car3 = controller.selectCarByPrice(modelPrice);
				printList(car3);
			case 4 : 
				System.out.println("출시일을 기준으로 검색합니다.");
				System.out.print("출시일(OOOO-OO-OO 형식으로 입력해주세요.) : ");
				String modelDate = sc.next();
				List<Car> car4 = controller.selectCarByDate(modelDate);
				printList(car4);
		}
	}
	
	public void printList(List<Car> list) {
		if(list.isEmpty()) {
			System.out.println("조회된 결과가 없습니다.");
		} else {
			for(Car c : list) {
				System.out.println(c);
			}
		}
	}
	
	public void printList(Car car) {
		if(car != null) {
			System.out.println(car);
		} else {
			System.out.println("조회된 결과가 없습니다.");
		}
	}
	
	public void dmlResultPrint(int result, String menuName) {
		if(result > 0) System.out.println(menuName+"이(가) 정상 수행되었습니다.");
		else System.out.println(menuName+"중 오류가 발생하였습니다.");
	}
	
	
}
