package com.gn.study.controller;

import java.util.List;

import com.gn.study.model.service.Service;
import com.gn.study.model.vo.Car;

// View로부터 전달받은 데이터 가공 -> Service 전달
public class Controller {
	private Service service = new Service();
	public int insertCarOne(String modelName, int price, String date) {
		Car car = new Car(modelName, price, date);
		int result = service.insertCarOne(car);
		return result;
	}
	
	public List<Car> selectCarAll() {
		List<Car> list = service.selectCarAll();
		return list;
	}
	
	// 번호 기준
	public List<Car> selectCarByNo(int modelNo) {
		List<Car> car = service.selectCarByNo(modelNo);
		return car;
	}
	
	// 모델명 기준
	public List<Car> selectCarByModel(String modelName) {
		List<Car> car = service.selectCarByModel(modelName);
		return car;
	}
	
	// 가격 기준
	public List<Car> selectCarByPrice(int modelPrice) {
		List<Car> car = service.selectCarByPrice(modelPrice);
		return car;
	}
	
	// 출시일 기준
	public List<Car> selectCarByDate(String modelDate) {
		List<Car> car = service.selectCarByDate(modelDate);
		return car;
	}
	
}
