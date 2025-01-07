package super_market.model.vo;

public class Buy {
	private int buy_no;
	private int user_no;
	private int prod_no;
	private int buy_amount;
	private String user_nickname;
	private String prod_name;

	public Buy() {}
	
	public Buy(int buy_no, int user_no, int prod_no, int buy_amount
			, String user_nickname, String prod_name) {
		this.buy_no = buy_no;
		this.user_no = user_no;
		this.prod_no = prod_no;
		this.buy_amount = buy_amount;
		this.user_nickname = user_nickname;
		this.prod_name = prod_name;
	}

	public int getBuy_no() {
		return buy_no;
	}

	public void setBuy_no(int buy_no) {
		this.buy_no = buy_no;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public int getProd_no() {
		return prod_no;
	}

	public void setProd_no(int prod_no) {
		this.prod_no = prod_no;
	}

	public int getBuy_amount() {
		return buy_amount;
	}

	public void setBuy_amount(int buy_amount) {
		this.buy_amount = buy_amount;
	}
	
	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	@Override
	public String toString() {
		return "판매현황 [구매자 닉네임 : " + user_nickname +
				", 제품 명 : " + prod_name +
				", 판매 개수 : " + buy_amount + "]";
	}

	
}
