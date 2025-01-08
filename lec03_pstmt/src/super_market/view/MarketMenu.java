package super_market.view;

import java.util.List;
import java.util.Scanner;

import super_market.controller.MarketController;
import super_market.model.vo.Buy;
import super_market.model.vo.Product;
import super_market.model.vo.User;

public class MarketMenu {
	private Scanner sc = new Scanner(System.in);
	private MarketController mc = new MarketController();
	public void mainMenu() {
		System.out.println("슈퍼마켓에 어서오세요");
		while(true) {
			System.out.println("=== 회원 관리 프로그램 ===");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("0. 프로그램 종료");
			
			System.out.print("OPTION : ");
			int menu = sc.nextInt();
			sc.nextLine();
			switch(menu) {
				case 1 : createUser(); break;
				case 2 : login(); break;
				case 0 : System.out.println("잘가용~ 안농~~"); return;
				default : System.out.println("잘못된 번혼디용ㅠㅠ");
			}
		}
	}
	
	// 회원 가입
	public void createUser() {
		System.out.println("=== 회원가입 화면 ===");
		while(true) {
			System.out.print("아이디 : ");
			String id = sc.next();
			System.out.print("비밀번호 : ");
			String pw = sc.next();
			System.out.print("닉네임 : ");
			String nickname = sc.next();
			List<User> list = mc.searchUserAll();
			int cnt = 0;
			for(User u : list) {
				if(u.getUser_id().equals(id)) {
					cnt++;
				}
			}
			if(cnt > 0) {
				System.out.println("회원가입 실패 \n"+id+"는 이미 사용중인 아이디입니다. 다른 아이디를 입력해주세요.");
			} else {
				int result = mc.insertMember(id, pw, nickname);
				if(result > 0) {
					System.out.println("회원가입에 성공하였습니다.");
					break;
			}
			}
		}
	}
	
	// 로그인 하기
	public void login() {
		System.out.println("=== 로그인 화면 ===");
		System.out.print("아이디 : ");
		String id = sc.next();
		System.out.print("비밀번호 : ");
		String pw = sc.next();
		
		User user = mc.login(id, pw);
		if(user != null) {
			if(user.getUser_id().equals("admin")) {
				adminMenu(user);
			} else {
				userMenu(user);
			}
		} else {
			System.out.println("아이디 또는 비밀번호를 확인해주세요.");
		}
	}
	
	// 관리자 메뉴
	public void adminMenu(User user) {
		System.out.println(user.getUser_nickname()+"님, 환영합니다!");
		while(true) {
			System.out.println("=== 관리자 메뉴 ===");
			System.out.println("1. 제품 등록");
			System.out.println("2. 제품 입고");
			System.out.println("3. 판매 현황");
			System.out.println("0. 로그아웃");
			
			System.out.print("OPTION : ");
			int menu = sc.nextInt();
			switch(menu) {
				case 1 : insertProduct(); break;
				case 2 : addProduct(); break;
				case 3 : salesStatus(); break;
				case 0 : System.out.println("잘가용~ 안농~~"); return;
				default : System.out.println("잘못된 번혼디용ㅠㅠ");
			}
		}
	}
	
	// 제품 등록
	public void insertProduct() {
		System.out.println("=== 제품 등록 ===");
		System.out.println("제품의 이름과 가격, 입고 개수를 입력하세요.");
		System.out.print("이름 : ");
		String prodName = sc.next();
		System.out.print("가격 : ");
		int prodPrice = sc.nextInt();
		System.out.print("갯수 : ");
		int prodAmount = sc.nextInt();
		int result = mc.insertProduct(prodName, prodPrice, prodAmount);
		printResult(result, "제품 등록");
	}
	
	// 제품 입고
	public void addProduct() {
		System.out.println("=== 제품 입고 ===");
		System.out.println("제품 번호와 입고 개수를 입력하세요.");
		System.out.print("번호 : ");
		int prodNo = sc.nextInt();
		System.out.print("갯수 : ");
		int prodAmount = sc.nextInt();
		int result = mc.addProduct(prodNo, prodAmount);
		printResult(result, "제품 입고");
		
	}
	
	// 판매 현황
	public void salesStatus() {
		System.out.println("=== 판매 현황 ===");
		List<Buy> list = mc.salesStatus();
		if(list.isEmpty()) {
			System.out.println("조회된 정보가 없습니다.");
		} else {
			for(Buy b : list) {
				System.out.println(b);
			}
		}
		
	}
	
	
	// 결과를 출력하는 메소드
	public void printResult(int result, String menuName) {
		if(result > 0) {
			System.out.println(menuName+"이(가) 성공적으로 작동했습니다.");
		} else {
			System.out.println(menuName+"중 오류가 발생하였습니다.");
		}
	}
	
	
	
	
	// 사용자 메뉴
	public void userMenu(User user) {
		System.out.println(user.getUser_nickname()+"님, 환영합니다!");
		while(true) {
			System.out.println("=== 사용자 메뉴 ===");
			System.out.println("1. 제품 구매");
			System.out.println("2. 개인 정보 수정");
			System.out.println("3. 탈퇴");
			System.out.println("0. 로그아웃");
			
			System.out.print("OPTION : ");
			int menu = sc.nextInt();
			sc.nextLine();
			switch(menu) {
				case 1 : buyProduct(user); break;
				case 2 : editUserInfo(user); break;
				case 3 : deleteUser(user);
				case 0 : System.out.println("잘가용~ 안농~~"); return;
				default : System.out.println("잘못된 번혼디용ㅠㅠ");
			}
		}
	}
	
	// 제품 구매
	public void buyProduct(User user) {
		System.out.println("=== 제품 구매 ===");
		List<Product> list = mc.searchProductAll();
		if(list.isEmpty()) {
			System.out.println("조회된 정보가 없습니다.");
		} else {
			for(Product p : list) {
				System.out.println(p);
			}
		}
		System.out.println("구매하고자 하는 제품의 번호와 구매 개수를 입력해주세요.");
		System.out.print("번호 : ");
		int prodNo = sc.nextInt();
		System.out.print("갯수 : ");
		int prodAmount = sc.nextInt();
		int result = mc.buyProduct(user, prodNo, prodAmount);
		printResult(result, "제품 구매");
	}
	
	// 개인 정보 수정
	public void editUserInfo(User user) {
		System.out.println("=== 개인 정보 수정 ===");
		System.out.print("비밀번호를 입력하세요 : ");
		String password = sc.next();
		if(user.getUser_pw().equals(password)) {
			System.out.print("수정할 닉네임 : ");
			String nickname = sc.next();
			int result = mc.editUserInfo(user, nickname);
			printResult(result, "닉네임변경");
		} else {
			System.out.println("비밀번호가 일치하지 않습니다.");
		}
	}
	
	// 탈퇴
	public void deleteUser(User user) {
		System.out.println("=== 회원 탈퇴 ==");
		System.out.print("비밀번호를 입력하세요 : ");
		String password = sc.next();
		if(user.getUser_pw().equals(password)) {
			System.out.print("정말 탈퇴하시겠습니까?(Y/N) : ");
			String answer = sc.next();
			if("Y".equals(answer)) {
				int result = mc.deleteUser(user);
				printResult(result, "회원 탈퇴");
			} else {
				System.out.println("탈퇴를 취소하였습니다.");
			}
		} else {
			System.out.println("비밀번호가 일치하지 않습니다.");
		}
	}
	
	
	
}
