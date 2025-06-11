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
			/*
			초기화면
			System.out.println("=======================================================");
			System.out.println("1.테이블 확인  2.메뉴관리  3.카테고리관리  4.매출표확인");
			System.out.println("=======================================================");
			System.out.print("입력>");
			*/
			System.out.println("1.카테고리별 매출표 확인");
			System.out.println("2.날짜별 매출표 확인");
			System.out.print("입력> ");
			
			int sale = sc.nextInt();
			sc.nextLine(); // 줄바꿈 처리

			if (sale == 1) {

				System.out.println("4.매출표확인 > 1.카테고리별 매출표 확인");
				System.out.println("");
				System.out.println("카테고리  "+"\t"+"  매출");
				System.out.println("분식류  "+"\t "+"   ");
				System.out.println("주류  "+"\t "+"   ");
				System.out.println("식사류 "+"\t "+"   ");
				System.out.println("=======================================");
				System.out.println("	  <조회되었습니다>    ");
				
				String food = sc.nextLine();
				sc.nextLine();

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

				
				
				// DAO 호출
				List<SaleVO> result = saleDAO.saleselect();

			} else if (sale == 2) {
				// <2.날짜별 매출표 확인>

				System.out.println("4.매출표확인 > 2.날짜별 매출표 확인");
				
				System.out.println("");
				System.out.println("날짜	"+"\t"+"매출");
				System.out.println("집계한 날짜:");
				System.out.println("01월 02일:");
				System.out.println("01월 03일:");
				System.out.println("====================================");
				System.out.println("	<조회되었습니다>	");
				
				int oneDaySale = sc.nextInt();
				sc.nextLine();
				
				System.out.println(oneDaySale+"");

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
