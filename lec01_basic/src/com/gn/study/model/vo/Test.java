package com.gn.study.model.vo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test {
	private int testNo;
	private String testName;
	private LocalDateTime testDate;
	
	public Test() {}
	
	public Test(int testNo, String testName, LocalDateTime testDate) {
		this.testNo = testNo;
		this.testName = testName;
		this.testDate = testDate;
	}
	public int getTestNo() {
		return testNo;
	}
	public void setTestNo(int testNo) {
		this.testNo = testNo;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public LocalDateTime getTestDate() {
		return testDate;
	}
	public void setTestDate(LocalDateTime testDate) {
		this.testDate = testDate;
	}

	@Override
	public String toString() {
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String result1 = dtf1.format(testDate);
		return "번호:" + testNo + ", 이름:" + testName + ", 등록일:" + result1;
	}

	
}
