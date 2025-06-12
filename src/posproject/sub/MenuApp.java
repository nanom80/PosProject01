package posproject.sub;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import posproject.TextUtil;
import posproject.dao.MenuDAO;
import posproject.vo.CategoryVO;
import posproject.vo.MenuVO;

public class MenuApp {

    //public static void main(String[] args) {
	public static void run(Scanner sc) throws IOException {
        //Scanner sc = new Scanner(System.in);
        MenuDAO menuDAO = new MenuDAO();

        while (true) {
            showMenuList(menuDAO);

            System.out.print("입력>_ ");
            String number = sc.nextLine();
            
            //메뉴 추가
            if (number.equals("1")) {
                System.out.println("[2. 메뉴관리 > 메뉴 추가하기]");

                while (true) {
                    System.out.print("카테고리> ");
                    String categoryName = sc.nextLine();
                    if (categoryName.equals("0")) break;

                    System.out.print("메뉴명> ");
                    String menuName = sc.nextLine();
                    if (menuName.equals("0")) break;

                    System.out.print("가격(단가)> ");
                    String unitPriceInput = sc.nextLine();
                    if (unitPriceInput.equals("0")) break;

                    int unitPrice = 0;
                    try {
                        unitPrice = Integer.parseInt(unitPriceInput);
                    } catch (NumberFormatException e) {
                        System.out.println("숫자를 입력해주세요.");
                        continue;
                    }

                    int categoryId = menuDAO.getCategoryIdByName(categoryName);
                    if (categoryId == -1) {
                        System.out.println("존재하지 않는 카테고리입니다.");
                        continue;
                    }

                    int result = menuDAO.menuInsert(menuName, unitPrice, "보임", "사용중", categoryId);
                    if (result > 0) {
                        System.out.println("메뉴가 등록되었습니다.");
                    } else {
                        System.out.println("메뉴 등록 실패.");
                    }

                    break;
                }
                
            //메뉴 삭제
            } else if(number.equals("2")) {            
            	System.out.println("[2. 메뉴관리 > 메뉴 삭제하기]");
            		
            	while(true) {
            		System.out.print("메뉴번호> ");
            		String menuIdInput = sc.nextLine();
            		if (menuIdInput.equals("0")) break;
            		
            		int menuId = 0;
            		try {
            		    menuId = Integer.parseInt(menuIdInput);
            		} catch (NumberFormatException e) {
            		    System.out.println("숫자를 입력해주세요.");
            		    continue;
            		}
            		
            		int result = menuDAO.menuDelete(menuId);
                    if (result > 0) {
                        System.out.println("메뉴가 삭제되었습니다");
                    } else {
                        System.out.println("메뉴 삭제 실패.");
                    }

                    break;
            	}
            	
            //메뉴 수정
            } else if(number.equals("3")) {
            	System.out.println("[2. 메뉴관리 > 메뉴 수정하기]");
            	
            	while(true) {
            		System.out.print("메뉴번호> ");
            		String menuIdInput = sc.nextLine();
            		if (menuIdInput.equals("0")) break;
            		
            		int menuId = 0;
            		try {
            			menuId = Integer.parseInt(menuIdInput);
            		} catch (NumberFormatException e) {
            			System.out.println("숫자를 입력해주세요.");
            			continue;
            		}
            		
            		System.out.print("변경할 카테고리> ");
            		String categoryName = sc.nextLine();
            		if(categoryName.equals("0")) break;
            		
            		System.out.print("변경할 메뉴명> ");
            		String menuName = sc.nextLine();
            		if (menuName.equals("0")) break;
            		
            		System.out.print("변경할 가격> ");
                    String unitPriceInput = sc.nextLine();
                    if (unitPriceInput.equals("0")) break;

                    int unitPrice = 0;
                    try {
                        unitPrice = Integer.parseInt(unitPriceInput);
                    } catch (NumberFormatException e) {
                        System.out.println("숫자를 입력해주세요.");
                        continue;
                    }
                    
                    
                    int categoryId = menuDAO.getCategoryIdByName(categoryName);
                    if (categoryId == -1) {
                        System.out.println("존재하지 않는 카테고리입니다.");
                        continue;
                    }
                    
                    int result = menuDAO.menuUpdate(menuId, menuName, unitPrice, categoryId);
                    if (result > 0) {
                        System.out.println("메뉴가 수정되었습니다.");
                    } else {
                        System.out.println("메뉴 수정 실패.");
                    }

                    break;
            		
            	}
            
            
            } else if (number.equals("4")) {
            	System.out.println("[2. 메뉴관리 > 메뉴 숨기기/보이기]");

                while (true) {
                    System.out.print("메뉴번호> ");
                    String menuIdInput = sc.nextLine();
                    if (menuIdInput.equals("0")) break;

                    int menuId = 0;
                    try {
                        menuId = Integer.parseInt(menuIdInput);
                    } catch (NumberFormatException e) {
                        System.out.println("숫자를 입력해주세요.");
                        continue;
                    }

                    // 전체 메뉴 리스트에서 해당 메뉴 정보 가져오기
                    List<MenuVO> menuList = menuDAO.menuList();
                    MenuVO selectedMenu = null;

                    for (MenuVO menu : menuList) {
                        if (menu.getMenuId() == menuId) {
                            selectedMenu = menu;
                            break;
                        }
                    }

                    if (selectedMenu == null) {
                        System.out.println("해당 메뉴가 존재하지 않습니다.");
                        continue;
                    }

                    String currentShow = selectedMenu.getMenuShow();
                    int result = menuDAO.toggleMenuShow(menuId, currentShow);
                    if (result > 0) {
                        String newShow = currentShow.equals("보임") ? "숨겨짐" : "숨겨짐";
                        System.out.println("메뉴가 '" + newShow + "' 상태로 변경되었습니다.");
                    } else {
                        System.out.println("상태 변경 실패.");
                    }

                    break;
                }
            
            
            } else if (number.equals("0")) {
                System.out.println("종료합니다");
                break;
            } else {
                System.out.println("올바른 번호를 입력해주세요.");
            }
        }

        //sc.close();
    }
	
    //------------------------------------------------------------
    public static void showMenuList(MenuDAO menuDAO) {
        while(true) {
        	List<MenuVO> menuList = menuDAO.menuList();
        	System.out.println("\n[2. 메뉴관리]");
	        //System.out.println("ID\t카테고리\t메뉴명\t단가\t상태");  // <-- 상태 컬럼 추가
	        
	        final int widthId = 6;
			final int widthName = 14;
			final int widthEmoji = 15;
			final int widthDesc = 10;
			final int widthShow = 10;
			
	        //System.out.println("[3.카테고리 관리]");
			System.out.println("----------------------------------------------------------------------");
			//System.out.println("|      |              |        |                                        |");
			System.out.println("|" + TextUtil.padRightDisplay("번호", widthId) + "|"
					+ TextUtil.padRightDisplay("카테고리명", widthName) + "|" + TextUtil.padRightDisplay("메뉴명", widthEmoji)
					+ "|" + TextUtil.padRightDisplay("단가", widthDesc) + "|" + TextUtil.padRightDisplay("상태", widthDesc) + "|");
			//System.out.println("|______|______________|________|________________________________________|");
			System.out.println("----------------------------------------------------------------------");
			
	        for (MenuVO menu : menuList) {
	        	
				System.out.println("|" + TextUtil.padRightDisplay(String.valueOf(menu.getMenuId()), widthId) + "|"
						+ TextUtil.padRightDisplay(menu.getCategoryName(), widthName) + "|"
						+ TextUtil.padRightDisplay(menu.getMenuName(), widthEmoji) + "|"
						+ TextUtil.padRightDisplay(menu.getUnitPrice(), widthDesc) + "|"
						+ TextUtil.padRightDisplay(menu.getMenuShow(), widthShow) + "|");
				
	        }
	        
	        System.out.println("----------------------------------------------------------------------");
	        //System.out.println("1.메뉴 추가하기    2.메뉴 삭제하기    3.메뉴 수정하기    4.메뉴숨기기/보이기    0.되돌아가기");
	        System.out.println("1.메뉴 추가하기");
	        System.out.println("2.메뉴 삭제하기");
	        System.out.println("3.메뉴 수정하기");
	        System.out.println("4.메뉴숨기기/보이기");
	        System.out.println("0.되돌아가기");
	        System.out.println("----------------------------------------------------------------------");
	        break;
        }
    }
}
