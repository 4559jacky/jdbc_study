package super_market.controller;

import java.util.List;

import super_market.model.dao.MarketDao;
import super_market.model.vo.Buy;
import super_market.model.vo.Product;
import super_market.model.vo.User;

public class MarketController {
	private MarketDao md = new MarketDao(); 
	// 전체 회원 조회
	public List<User> searchUserAll() {
		List<User> list = md.searchUserAll();
		return list;
	}
	
	// 회원가입
	public int insertMember(String id, String pw, String nickname) {
		User u = new User(id, pw, nickname);
		int result = md.insertMember(u);
		return result;
	}
	
	// 로그인
	public User login(String id, String pw) {
		User user = md.login(id, pw);
		return user;
	}
	
	// 제품 등록
	public int insertProduct(String prodName, int prodPrice, int prodAmount) {
		int result = md.insertProduct(prodName, prodPrice, prodAmount);
		return result;
	}
	
	// 제품 입고
	public int addProduct(int prodNo, int prodAmount) {
		int result = md.addProduct(prodNo, prodAmount);
		return result;
	}
	
	
	// 판매 현황
	public List<Buy> salesStatus() {
		List<Buy> list = md.salesStatus();
		return list;
	}
	
	// 전체 제품 검색
	public List<Product> searchProductAll() {
		List<Product> list = md.searchProductAll();
		return list;
	}
	
	// 제품 구매하기
	public int buyProduct(User user, int prodNo, int prodAmount) {
		int result = md.buyProduct(user, prodNo, prodAmount);
		return result;
	}
	
	// 닉네임변경
	public int editUserInfo(User user, String nickname) {
		int result = md.editUserInfo(user, nickname);
		return result;
	}
	
	// 회원 탈퇴
	public int deleteUser(User user) {
		int result = md.deleteUser(user);
		return result;
	}
	
	
	
	
}
