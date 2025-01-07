package super_market.model.vo;

public class Product {
	private int prod_no;
	private String prod_name;
	private int prod_price;
	private int prod_amount;
	
	public Product() {}
	
	public Product(String prod_name, int prod_price) {
		this.prod_name = prod_name;
		this.prod_price = prod_price;
	}

	public Product(String prod_name, int prod_price, int prod_amount) {
		this.prod_name = prod_name;
		this.prod_price = prod_price;
		this.prod_amount = prod_amount;
	}
	
	public Product(int prod_no, String prod_name, int prod_price, int prod_amount) {
		this.prod_no = prod_no;
		this.prod_name = prod_name;
		this.prod_price = prod_price;
		this.prod_amount = prod_amount;
	}

	public int getProd_no() {
		return prod_no;
	}

	public void setProd_no(int prod_no) {
		this.prod_no = prod_no;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public int getProd_price() {
		return prod_price;
	}

	public void setProd_price(int prod_price) {
		this.prod_price = prod_price;
	}

	public int getProd_amount() {
		return prod_amount;
	}

	public void setProd_amount(int prod_amount) {
		this.prod_amount = prod_amount;
	}

	@Override
	public String toString() {
		return "상품 정보 [상품 번호 : " + prod_no +
				", 상품 이름 : " + prod_name +
				", 상품 가격 : " + prod_price +
				", 남은 갯수 : " + prod_amount + "]";
	}
	
	
	
	
}
