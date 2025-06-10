package posProject;

import java.util.Scanner;
import java.io.IOException;
import java.time.LocalDate;

public class OrderApp {
	
	public static void main(String[] args) throws IOException {
		
		// DAO 준비
		OrderDAO orderDAO = new OrderDAO();
		
		//키보드 준비
		Scanner sc = new Scanner(System.in);
		
		while (true) {
            System.out.println("**************************************");
            System.out.println("*           주문 프로그램             *");
            System.out.println("**************************************");
            
            orderDAO.menuSelect();
            
            System.out.println("1. 주문 등록");
            System.out.println("2. 주문 목록 확인");
            System.out.println("3. 종료");
            System.out.print("> 메뉴번호: ");
            
            int menuNum = sc.nextInt();
            sc.nextLine(); // 줄바꿈 처리

            if (menuNum == 1) {
            	
            	System.out.println("<1. 주문 등록>");
            	
            	System.out.print("테이블번호(1~6): ");
                int tableId;
                while (true) {
                    tableId = sc.nextInt();
                    if (tableId >= 1 && tableId <= 6) {
                        break;
                    } else {
                        System.out.println("테이블번호를 다시 입력해주세요(1~6)");
                    }
                }
                sc.nextLine();
                
                System.out.print("메뉴번호: ");
                int menuId = sc.nextInt();
                sc.nextLine();
                
                System.out.print("수량: ");
                int count = sc.nextInt();
                sc.nextLine();
                
                System.out.print("결제여부(Y/N): ");
                String payment = sc.nextLine();

                while (true) {
                    if (payment.equalsIgnoreCase("Y") || payment.equalsIgnoreCase("N")) {
                        break;
                    } else {
                        System.out.print("결제여부를 다시 입력해주세요(Y/N): ");
                        payment = sc.nextLine();
                    }
                }

                String payDate = LocalDate.now().toString();
                System.out.println("결제일자는 오늘: " + payDate);
                

                // DAO 호출
                int result = orderDAO.orderInsert(count, payment, payDate, tableId, menuId);

                if (result > 0) {
                    System.out.println("주문이 성공적으로 등록되었습니다.\n");
                } else {
                    System.out.println("주문 등록에 실패했습니다.\n");
                }

            } else if (menuNum == 2) {
                System.out.println("<2. 주문 목록 확인>");

                orderDAO.orderSelect();
                
            } else if (menuNum == 3) {
                System.out.println("<3. 종료>");
                System.out.println("프로그램을 종료합니다.");
                break;

            } else {
                System.out.println("유효하지 않은 메뉴번호입니다. 다시 입력해주세요.\n");
            }
        }

        // 키보드 해제
        sc.close();
        
	}

}


