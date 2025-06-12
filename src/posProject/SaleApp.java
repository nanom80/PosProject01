package posProject;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class SaleApp {

	private static final int List = 0;
	private static int sale;

	//public static void main(String[] args) throws IOException {
	public static void run(Scanner sc) throws IOException {
		
		// DAO 준비
		SaleDAO saleDAO = new SaleDAO();

		// 키보드 준비
		//Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("0.매출표 집계");
			System.out.println("1.카테고리별 매출표 확인");
			
			System.out.println("2.날짜별 매출표 확인");
			System.out.println("3.이전으로 돌아가기");
			System.out.print("입력> ");
			
			
			int sale = sc.nextInt();
			//sc.nextLine(); // 줄바꿈 처리
			if (sale == 0) {

				// DAO 호출
				saleDAO.DayInsert();
				
				String food = sc.nextLine();
				
				continue;  // 다시 매출 메뉴로
				
/*
				while (true) {
					if (food.equalsIgnoreCase("김치찌개") || food.equalsIgnoreCase("된장찌개")) {
						break;
					} else {
						System.out.println("다른 메뉴를 입력해주세요: ");
						food = sc.nextLine();
					}
				}

				String saleDate = LocalDate.now().toString();
				System.out.println("카테고리별 매출표: " + saleDate);
				
*/
			} else if (sale == 1) {

				// DAO 호출
				//List<SaleVO> result = saleDAO.saleselect();
				saleDAO.saleselect();
				
				sc.nextLine();
				
				/*
				while (true) {
					if (food.equalsIgnoreCase("김치찌개") || food.equalsIgnoreCase("된장찌개")) {
						break;
					} else {
						System.out.println("다른 메뉴를 입력해주세요: ");
						food = sc.nextLine();
					}
				}
				*/
				String saleDate = LocalDate.now().toString();
				System.out.println("카테고리별 매출표: " + saleDate);
				
				continue;  // 다시 매출 메뉴로

			} else if (sale == 2) {
				// <2.날짜별 매출표 확인>
				
				System.out.println("4.매출표확인 > 2.날짜별 매출표 확인");
				
				// DAO 호출
				saleDAO.Dayselect();

			    // 줄바꿈 처리: 이전 입력한 숫자 다음 줄을 소비
			    sc.nextLine();
			    
				continue;  // 다시 매출 메뉴로

			} else if (sale == 3) {
	                System.out.println("5.이전");
	                System.out.println("이전 메뉴로 돌아갑니다.");
	                break;
			} else {
				System.out.println("[다시 입력해 주세요]");
				System.out.println();
				
			}
			
		} // while 끝남

		// 스트림 닫기
		// br.close();
		// sc.close();
	}

}

