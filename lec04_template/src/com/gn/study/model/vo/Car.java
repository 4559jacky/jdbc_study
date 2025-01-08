package com.gn.study.model.vo;

public class Car {
	private int carNo;
	private String carModel;
	private int carPrice;
	private String carDate;
	
	public Car() {}
	public Car(String carModel, int carPrice, String carDate) {
		this.carModel = carModel;
		this.carPrice = carPrice;
		this.carDate = carDate;
	}
	public int getCarNo() {
		return carNo;
	}
	
	public void setCarNo(int carNo) {
		this.carNo = carNo;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public int getCarPrice() {
		return carPrice;
	}
	public void setCarPrice(int carPrice) {
		this.carPrice = carPrice;
	}
	public String getCarDate() {
		return carDate;
	}
	public void setCarDate(String carDate) {
		this.carDate = carDate;
	}
	
	@Override
	public String toString() {
		return "모델정보 [모델 번호 : " + carNo +
				", 모델 이름 : " + carModel +
				", 가격 : " + carPrice +
				", 출시일 : " + carDate + "]";
	}
	public Car(int carNo, String carModel, int carPrice, String carDate) {
		this.carNo = carNo;
		this.carModel = carModel;
		this.carPrice = carPrice;
		this.carDate = carDate;
	}
	
}
