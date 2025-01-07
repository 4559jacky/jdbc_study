package com.gn.study.view;

import java.util.List;
import java.util.Scanner;

import com.gn.study.controller.ProjectController;
import com.gn.study.model.vo.ProjectVo;

public class ProjectMenu {
	// 입력과 출력 화면
	private Scanner sc = new Scanner(System.in);
	private ProjectController pc = new ProjectController();
	public void mainMenu() {
		while(true) {
			System.out.println("=== (주)ABC 프로젝트 관리 시스템 ===");
			System.out.println("1. 프로젝트 추가");
			System.out.println("2. 프로젝트 전체 조회");
			System.out.println("3. 프로젝트 이름 검색");
			System.out.println("4. 프로젝트 담당자 검색");
			System.out.println("5. 프로젝트 정보 수정");
			System.out.println("6. 프로젝트 삭제");
			System.out.println("0. 종료");
			
			System.out.print("메뉴 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			switch(menu) {
				case 1 : createProject(); break;
				case 2 : showProjectAll(); break;
				case 3 : searchByProjectName(); break;
				case 4 : searchByProjectManagerName(); break;
				case 5 : editProjectOne(); break;
				case 6 : deleteProjectOne(); break;
				case 0 : System.out.println("이용해주셔서 감사합니다.");return;
				default : System.out.println("메뉴를 잘못 입력하셨습니다.");
			}	
		}
	}
	
	public void deleteProjectOne() {
		// 전체 목록 조회, 삭제하고자 하는 번호 입력받아서 -> 삭제 처리
		System.out.println("*** 프로젝트 삭제 ***");
		List<ProjectVo> list = pc.selectProjectAll();
		printListProject(list);
		System.out.println("삭제하고자 하는 프로젝트 번호를 입력하시면, 삭제해드립니다.");
		System.out.print("번호 : ");
		int projectNo = sc.nextInt();
		int result = pc.deleteProjectOne(projectNo);
		printProjectResult(result, "프로젝트 삭제");
	}
	
	// 프로젝트 생성
	public void createProject() {
		System.out.println("*** 프로젝트 추가 ***");
		System.out.println("프로젝트명과 담장자 이름을 입력하세요.");
		System.out.print("프로젝트명 : ");
		String projectName = sc.nextLine();
		System.out.print("담당자 이름 : ");
		String managerName = sc.nextLine();
		int result = pc.insertProjectOne(projectName, managerName);
		printProjectResult(result, "프로젝트 추가");
	}
	
	public void printListProject(List<ProjectVo> list) {
		if(list.isEmpty()) {
			System.out.println("조회된 정보가 없습니다.");
		} else {
			for(ProjectVo p : list) {
				System.out.println(p);
			}
		}
	}
	
	// 프로젝트 담당자 이름으로 검색
	public void searchByProjectManagerName() {
		System.out.println("*** 담당자 이름 검색 ***");
		System.out.println("담당자의 이름을 입력하시면, 해당 담당자가 담당하고 있는 프로젝트 정보를 조회해드립니다.");
		System.out.print("담당자 이름 : ");
		String managerName = sc.nextLine();
		List<ProjectVo> list = pc.searchByProjectManagerName(managerName);
		System.out.println("※ "+managerName+"님이 담당하는 프로젝트 ※");
		printListProject(list);
	}

	public void printProjectResult(int result, String menuName) {
		if(result > 0) System.out.println(menuName+"이(가) 정상적으로 완료되었습니다.");
		else System.out.println(menuName+"중 오류가 발생하였습니다.");
	}
	
	public void editProjectOne() {
		System.out.println("*** 프로젝트 정보 수정 ***");
		List<ProjectVo> list = pc.selectProjectAll();
		printListProject(list);
		System.out.println("수정하고자 하는 프로젝트 번호와 새로운 프로젝트명을 입력하시면, 수정해드립니다.");
		System.out.print("번호 : ");
		int projectNo = sc.nextInt();
		sc.nextLine();
		System.out.print("새로운 프로젝트명 : ");
		String projectName = sc.nextLine();
		
		int result = pc.updateProjectOne(projectNo, projectName);
		printProjectResult(result, "프로젝트 정보 수정");
	}
	
	
	// 프로젝트 이름으로 검색
	public void searchByProjectName() {
		System.out.println("*** 프로젝트 이름 검색 ***");
		System.out.println("프로젝트 이름을 일부 입력하시면, 관련 프로젝트 정보를 조회해드립니다.");
		System.out.print("프로젝트 이름 : ");
		String projectName = sc.nextLine();
		List<ProjectVo> list = pc.selectProjectAllByName(projectName);
		printListProject(list);
	}
	
	// 모든 프로젝트 출력
	public void showProjectAll() {
		System.out.println("*** 프로젝트 전체 조회 ***");
		List<ProjectVo> list = pc.selectProjectAll();
		printListProject(list);
	}
	
	
	
	
}
